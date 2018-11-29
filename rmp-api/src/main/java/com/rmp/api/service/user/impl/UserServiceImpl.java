package com.rmp.api.service.user.impl;

import static com.rmp.api.util.MsgEnum.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.rmp.api.base.dao.redis.BaseShardedJedisPoolDao;
import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.service.impl.BaseServiceImpl;
import com.rmp.api.model.UserBean;
import com.rmp.api.model.WxPhoneNumberReqBean;
import com.rmp.api.model.WxPhoneNumberRespBean;
import com.rmp.api.model.PhoneMsgBean;
import com.rmp.api.service.msg.PhoneMsgService;
import com.rmp.api.service.user.UserService;
import com.rmp.api.util.UserUtil;
import com.rmp.api.util.WxUtil;
import com.rmp.api.util.constant.Constant;
import com.rmp.common.util.DateUtil;
import com.rmp.common.util.UuidUtil;
import com.rmp.info.mapper.UserMapper;
import com.rmp.info.model.User;
import com.rmp.info.model.UserCriteria;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.Transaction;

/**
 * 用户 service impl
 * @author linw
 *
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserBean, UserCriteria> implements UserService {
	
	@Autowired
	private BaseShardedJedisPoolDao baseShardedJedisPoolDao;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private PhoneMsgService phoneMsgService;

	@Override
	public UserMapper mapper() {
		return userMapper;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object exe(String cmd, Object obj) {
		try {
			switch (cmd) {
			case "initialize": initialize((UserBean) obj);break;
			case "bindWxPhone": bindWxPhone((Map<String, Object>) obj);break;
			case "login": login((UserBean) obj);break;
			case "register": register((Map<String, Object>) obj);break;
			case "retrievePwd": retrievePwd((Map<String, Object>) obj);break;
			case "updateNickName": updateNickName((UserBean) obj);break;
			case "updateHeadPic": updateHeadPic((UserBean) obj);break;
			case "updatePhone": updatePhone((Map<String, Object>) obj);break;
			case "updateAll": updateAll((UserBean) obj);break;
			default: return super.exe(cmd, obj);
			}
		} catch (AppException e) {
			throw e;
		} catch (Exception e) {
			AppException.toThrow(e);
		}
		return null;
	}
	
	@Override
	protected void where(Object criteria, UserBean bean) {
		if (bean == null) {
			return;
		}
		UserCriteria.Criteria criteriaTmp = (UserCriteria.Criteria) criteria;
		if (bean.getId() != null) {
			criteriaTmp.andIdEqualTo(bean.getId());
		}
		if (!StringUtils.isEmpty(bean.getLoginName())) {
			criteriaTmp.andLoginNameEqualTo(bean.getLoginName());
		}
		if (!StringUtils.isEmpty(bean.getToken())) {
			criteriaTmp.andTokenEqualTo(bean.getToken());
		}
		if (!StringUtils.isEmpty(bean.getWxId())) {
			criteriaTmp.andWxIdEqualTo(bean.getWxId());
		}
		if (bean.getStatus() != null) {
			criteriaTmp.andStatusEqualTo(bean.getStatus());
		}
		if (bean.getIsDelete() != null) {
			criteriaTmp.andIsDeleteEqualTo(bean.getIsDelete());
		}
		if (bean.getIdNotEqualTo() != null) {
			criteriaTmp.andIdNotEqualTo(bean.getIdNotEqualTo());
		}
	}
	
	/**
	 * 初始化
	 * @param userBean
	 * @throws Exception 
	 */
	private void initialize(UserBean userBean) throws Exception {
		Long nowDateLong = DateUtil.nowSecondFormat();
		
		String wxId = userBean.getWxId();
		String nickName = StringUtils.trim(userBean.getNickName());
		String headPic = StringUtils.trim(userBean.getHeadPic());
		String wxSessionKey = StringUtils.trim(userBean.getWxSessionKey());
		
		String tokenOld = null;
		String token = UuidUtil.generateUUID();
		
		UserBean userBeanTmp = UserBean.builder().wxId(wxId).build();
		userBeanTmp = selectOne(userBeanTmp);
		if (userBeanTmp != null) {
			
			tokenOld = userBeanTmp.getToken();
			
			userBeanTmp.setNickName(nickName);
			userBeanTmp.setHeadPic(headPic);
			userBeanTmp.setUpdateTime(nowDateLong);
			userBeanTmp.setToken(token);
			userBeanTmp.setWxSessionKey(wxSessionKey);
			updatePkSelVer(userBeanTmp);
		} else {
			// 初始化用户
			userBeanTmp = UserBean.builder()
			.loginName(wxId)
			.loginPwd("0")
			.wxId(wxId)
			.nickName(nickName)
			.headPic(headPic)
			.createTime(nowDateLong)
			.token(token)
			.wxSessionKey(wxSessionKey)
			.build();
			insertSel(userBeanTmp);
		}
		BeanUtils.copyProperties(userBeanTmp, userBean);
		
		// 加入redis
		String key = UserUtil.rKey(token);
		String keyOld = UserUtil.rKey(tokenOld);
		
		int index = Constant.Redis.User.INDEX;
		try (ShardedJedis shardedJedis = baseShardedJedisPoolDao.getShardedJedis();
				Jedis jedis = shardedJedis.getShard(Constant.Redis.Sharded1.NAME1);) {
			jedis.select(index);
			jedis.watch(key);
			
			Boolean existsOld = false;
			if (!StringUtils.isEmpty(keyOld)) {
				existsOld = jedis.exists(keyOld);
			}
			if (existsOld) {
				jedis.watch(keyOld);
			}
			
			try (Transaction tx = jedis.multi();) {
				// 存在登陆信息
				if (existsOld) {
					tx.rename(keyOld, key);    // 替换之前的key
				} else {
					tx.hmset(key, UserUtil.rMap(userBeanTmp));
				}
				tx.expire(key, Constant.Redis.User.SECONDS);
				if (CollectionUtils.isEmpty(tx.exec())) AppException.toThrow(MSG_00008);
			} catch (Exception e) {
				throw e;
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 绑定 微信手机
	 * @param param
	 * @throws Exception 
	 */
	private void bindWxPhone(Map<String, Object> param) throws Exception {
		if (CollectionUtils.isEmpty(param)) AppException.toThrow(MSG_00003);
		UserBean userBean = (UserBean) param.get("userBean");
		WxPhoneNumberReqBean wxPhoneNumberReqBean = (WxPhoneNumberReqBean) param.get("wxPhoneNumberReqBean");
		
		if (userBean == null) AppException.toThrow(MSG_00003);
		if (wxPhoneNumberReqBean == null) AppException.toThrow(MSG_00003);
		
		String token = StringUtils.trim(userBean.getToken());
		String encryptedData = StringUtils.trim(wxPhoneNumberReqBean.getEncryptedData());
		String iv = StringUtils.trim(wxPhoneNumberReqBean.getIv());
		if (StringUtils.isEmpty(token)) AppException.toThrow(MSG_00003);
		if (StringUtils.isEmpty(encryptedData)) AppException.toThrow(MSG_00003);
		if (StringUtils.isEmpty(iv)) AppException.toThrow(MSG_00003);
		
		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		
		UserBean userBeanTmp = UserBean.builder().token(token).build();
		userBeanTmp = selectOne(userBeanTmp);
		if (userBeanTmp == null) AppException.toThrow(MSG_00007);
		if (!Constant.User.Status._0.equals(userBeanTmp.getStatus())) AppException.toThrow(MSG_01009);
		if (Constant.DELETE_Y.equals(userBeanTmp.getIsDelete())) AppException.toThrow(MSG_01017);
		if (Constant.User.Status._1.equals(userBeanTmp.getStatus())) AppException.toThrow(MSG_01031);
		
		String wxSessionKey = userBeanTmp.getWxSessionKey();
		if (StringUtils.isEmpty(wxSessionKey)) AppException.toThrow(MSG_01032);
		
		WxPhoneNumberRespBean wsxPhoneNumberRespBean = WxUtil.getData(encryptedData, wxSessionKey, iv);
		if (wsxPhoneNumberRespBean == null) AppException.toThrow(MSG_01033);
		String phoneNumber = wsxPhoneNumberRespBean.getPhoneNumber();
		if (StringUtils.isEmpty(phoneNumber)) AppException.toThrow(MSG_01034);
		
		// 查询用户是否注册
		UserBean userBeanTmp2 = UserBean.builder().loginName(phoneNumber).idNotEqualTo(userBeanTmp.getId()).build();
		userBeanTmp2 = selectOne(userBeanTmp2);
		if (userBeanTmp2 != null) AppException.toThrow(MSG_01009);
		
		userBeanTmp.setLoginName(phoneNumber);
		userBeanTmp.setStatus(Constant.User.Status._1);
		userBeanTmp.setPhone(Long.valueOf(phoneNumber));
		userBeanTmp.setUpdateTime(nowDateLong);
		userBeanTmp.setLastLoginTime(nowDateLong);
		updatePkSelVer(userBeanTmp);
		
		BeanUtils.copyProperties(userBeanTmp, userBean);
		
		// 加入redis
		String key = UserUtil.rKey(token);
		int index = Constant.Redis.User.INDEX;
		try (ShardedJedis shardedJedis = baseShardedJedisPoolDao.getShardedJedis();
				Jedis jedis = shardedJedis.getShard(Constant.Redis.Sharded1.NAME1);) {
			jedis.select(index);
			jedis.watch(key);
			try (Transaction tx = jedis.multi();) {
				tx.hmset(key, UserUtil.rMap(userBeanTmp));
				tx.expire(key, Constant.Redis.User.SECONDS);
				if (CollectionUtils.isEmpty(tx.exec())) AppException.toThrow(MSG_00008);
			} catch (Exception e) {
				throw e;
			}
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	/**
	 * 登录
	 * @param userBean
	 * @throws Exception 
	 */
	private void login(UserBean userBean) throws Exception {
		Long nowDateLong = DateUtil.nowSecondFormat();
		
		if (userBean == null) AppException.toThrow(MSG_00003);
		String loginName = StringUtils.trim(userBean.getLoginName());
		String loginPwd = StringUtils.trim(userBean.getLoginPwd());
		
		UserUtil.checkLoginName(loginName);
		UserUtil.checkLoginPwd(loginPwd);
		
		UserBean userBeanTmp = UserBean.builder().loginName(loginName).build();
		userBeanTmp = selectOne(userBeanTmp);
		UserUtil.checkUser(userBeanTmp);
		
		String loginPwdOld = userBeanTmp.getLoginPwd();
		if (!DigestUtils.sha1Hex(loginPwd).equals(loginPwdOld)) AppException.toThrow(MSG_01006);
		
		String token = UuidUtil.generateUUID();
		String tokenOld = userBeanTmp.getToken();
		
		userBeanTmp.setToken(token);
		userBeanTmp.setUpdateTime(nowDateLong);
		userBeanTmp.setLastLoginTime(nowDateLong);
		updatePkSelVer(userBeanTmp);
		
		BeanUtils.copyProperties(userBeanTmp, userBean);
		
		// 加入redis
		String key = UserUtil.rKey(token);
		String keyOld = UserUtil.rKey(tokenOld);
		
		int index = Constant.Redis.User.INDEX;
		try (ShardedJedis shardedJedis = baseShardedJedisPoolDao.getShardedJedis();
				Jedis jedis = shardedJedis.getShard(Constant.Redis.Sharded1.NAME1);) {
			jedis.select(index);
			jedis.watch(key);
			
			Boolean existsOld = false;
			if (!StringUtils.isEmpty(keyOld)) {
				existsOld = jedis.exists(keyOld);
			}
			if (existsOld) {
				jedis.watch(keyOld);
			}
			
			try (Transaction tx = jedis.multi();) {
				// 存在登陆信息
				if (existsOld) {
					tx.rename(keyOld, key);    // 替换之前的key
				} else {
					tx.hmset(key, UserUtil.rMap(userBeanTmp));
				}
				tx.expire(key, Constant.Redis.User.SECONDS);
				if (CollectionUtils.isEmpty(tx.exec())) AppException.toThrow(MSG_00008);
			} catch (Exception e) {
				throw e;
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 注册
	 * @param param
	 * @throws Exception 
	 */
	private void register(Map<String, Object> param) throws Exception {
		if (CollectionUtils.isEmpty(param)) AppException.toThrow(MSG_00003);
		UserBean userBean = (UserBean) param.get("userBean");
		PhoneMsgBean phoneMsgBean = (PhoneMsgBean) param.get("phoneMsgBean");
		
		if (userBean == null) AppException.toThrow(MSG_00003);
		if (phoneMsgBean == null) AppException.toThrow(MSG_00003);
		String token = StringUtils.trim(userBean.getToken());
		String loginName = StringUtils.trim(userBean.getLoginName());
		String loginPwd = StringUtils.trim(userBean.getLoginPwd());
		
		UserUtil.checkToken(token);
		UserUtil.checkLoginName(loginName);
		UserUtil.checkLoginPwd(loginPwd);
		
		String code = StringUtils.trim(phoneMsgBean.getCode());
		if (StringUtils.isEmpty(code)) AppException.toThrow(MSG_01011);
		else if (code.length() != Constant.PhoneMsg.LENGTH) AppException.toThrow(MSG_01012);
		
		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		Long craeteTimeStart = DateUtil.formatDate2Long(DateUtil.changeSecond(nowDate, -Constant.PhoneMsg.EFFECTIVE_TIME));
		
		PhoneMsgBean phoneMsgBeanTmp = PhoneMsgBean.builder()
		.code(code)
		.phone(Long.valueOf(loginName))
		.type(Constant.PhoneMsg.Type._1)
		.status(Constant.PhoneMsg.Status._0)
		.createTimeStart(craeteTimeStart)
		.build();
		phoneMsgBeanTmp = phoneMsgService.selectOne(phoneMsgBeanTmp);
		if (phoneMsgBeanTmp == null) AppException.toThrow(MSG_01012);
		// 修改验证码状态
		phoneMsgBeanTmp.setStatus(Constant.PhoneMsg.Status._1);
		phoneMsgBeanTmp.setUpdateTime(nowDateLong);
		phoneMsgService.exe(UPDATE_PK_SEl_VER, phoneMsgBeanTmp);
		
		// 查询用户是否注册
		UserBean userBeanTmp = UserBean.builder().loginName(loginName).build();
		userBeanTmp = selectOne(userBeanTmp);
		if (userBeanTmp != null) AppException.toThrow(MSG_01009);
		
		userBeanTmp = UserBean.builder().token(token).build();
		userBeanTmp = selectOne(userBeanTmp);
		if (userBeanTmp == null) AppException.toThrow(MSG_00007);
		if (!Constant.User.Status._0.equals(userBeanTmp.getStatus())) AppException.toThrow(MSG_01009);
		if (Constant.DELETE_Y.equals(userBeanTmp.getIsDelete())) AppException.toThrow(MSG_01017);
		
		userBeanTmp.setLoginName(loginName);
		userBeanTmp.setLoginPwd(DigestUtils.sha1Hex(loginPwd));
		userBeanTmp.setStatus(Constant.User.Status._1);
		userBeanTmp.setPhone(Long.valueOf(loginName));
		userBeanTmp.setUpdateTime(nowDateLong);
		userBeanTmp.setLastLoginTime(nowDateLong);
		updatePkSelVer(userBeanTmp);
		
		// redis
		BeanUtils.copyProperties(userBeanTmp, userBean);
		
		// 加入redis
		String key = UserUtil.rKey(token);
		int index = Constant.Redis.User.INDEX;
		try (ShardedJedis shardedJedis = baseShardedJedisPoolDao.getShardedJedis();
				Jedis jedis = shardedJedis.getShard(Constant.Redis.Sharded1.NAME1);) {
			jedis.select(index);
			jedis.watch(key);
			try (Transaction tx = jedis.multi();) {
				tx.hmset(key, UserUtil.rMap(userBeanTmp));
				tx.expire(key, Constant.Redis.User.SECONDS);
				if (CollectionUtils.isEmpty(tx.exec())) AppException.toThrow(MSG_00008);
			} catch (Exception e) {
				throw e;
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 找回密码
	 * @param param
	 * @return
	 */
	private void retrievePwd(Map<String, Object> param) {
		if (CollectionUtils.isEmpty(param)) AppException.toThrow(MSG_00003);
		UserBean userBean = (UserBean) param.get("userBean");
		PhoneMsgBean phoneMsgBean = (PhoneMsgBean) param.get("phoneMsgBean");

		if (userBean == null) AppException.toThrow(MSG_00003);
		if (phoneMsgBean == null) AppException.toThrow(MSG_00003);
		String loginName = StringUtils.trim(userBean.getLoginName());
		String loginPwd = StringUtils.trim(userBean.getLoginPwd());
		
		UserUtil.checkLoginName(loginName);
		UserUtil.checkLoginPwd(loginPwd);
		
		String code = StringUtils.trim(phoneMsgBean.getCode());
		if (StringUtils.isEmpty(code)) AppException.toThrow(MSG_01011);
		else if (code.length() != Constant.PhoneMsg.LENGTH) AppException.toThrow(MSG_01012);
		
		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		Long craeteTimeStart = DateUtil.nowLong2(DateUtil.changeSecond(nowDate, -Constant.PhoneMsg.EFFECTIVE_TIME));


		PhoneMsgBean phoneMsgBeanTmp = PhoneMsgBean.builder()
		.code(code)
		.phone(Long.valueOf(loginName))
		.type(Constant.PhoneMsg.Type._2)
		.status(Constant.PhoneMsg.Status._0)
		.createTimeStart(craeteTimeStart)
		.build();
		phoneMsgBeanTmp = phoneMsgService.selectOne(phoneMsgBeanTmp);
		if (phoneMsgBeanTmp == null) AppException.toThrow(MSG_01012);
		// 修改验证码状态
		phoneMsgBeanTmp.setStatus(Constant.PhoneMsg.Status._1);
		phoneMsgBeanTmp.setUpdateTime(nowDateLong);
		phoneMsgService.exe(UPDATE_PK_SEl_VER, phoneMsgBeanTmp);
		
		// 查询用户是否注册
		UserBean userBeanTmp = UserBean.builder().loginName(loginName).build();
		userBeanTmp = selectOne(userBeanTmp);
		UserUtil.checkUser(userBeanTmp);
		
		userBeanTmp.setLoginPwd(DigestUtils.sha1Hex(loginPwd));
		userBeanTmp.setUpdateTime(nowDateLong);
		updatePkSelVer(userBeanTmp);
	}
	
	/**
	 * 修改 昵称
	 * @param userBean
	 * @throws Exception 
	 */
	private void updateNickName(UserBean userBean) throws Exception {
		if (userBean == null) AppException.toThrow(MSG_00003);
		Long id = userBean.getId();
		String nickName = StringUtils.trim(userBean.getNickName());
		
		if (id == null) AppException.toThrow(MSG_00003);
		UserUtil.checkNickName(nickName);
		
		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		
		UserBean userBeanTmp = selectById(id);
		UserUtil.checkUser(userBeanTmp);
		
		userBeanTmp.setNickName(nickName);
		userBeanTmp.setUpdateTime(nowDateLong);
		updatePkSelVer(userBeanTmp);
		
		// redis
		BeanUtils.copyProperties(userBeanTmp, userBean);
		
		// 加入redis
		String token = userBeanTmp.getToken();
		String key = UserUtil.rKey(token);
		int index = Constant.Redis.User.INDEX;
		try (ShardedJedis shardedJedis = baseShardedJedisPoolDao.getShardedJedis();
				Jedis jedis = shardedJedis.getShard(Constant.Redis.Sharded1.NAME1);) {
			jedis.select(index);
			jedis.watch(key);
			try (Transaction tx = jedis.multi();) {
				tx.hset(key, "nickName", nickName);
				tx.expire(key, Constant.Redis.User.SECONDS);
				if (CollectionUtils.isEmpty(tx.exec())) AppException.toThrow(MSG_00008);
			} catch (Exception e) {
				throw e;
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 修改 头像
	 * @param userBean
	 * @throws Exception 
	 */
	private void updateHeadPic(UserBean userBean) throws Exception {
		if (userBean == null) AppException.toThrow(MSG_00003);
		Long id = userBean.getId();
		String headPic = StringUtils.trim(userBean.getHeadPic());
		if (StringUtils.isEmpty(headPic)) AppException.toThrow(MSG_00003);
		
		boolean isMove = false;
		String headPicNew = null;
		String headPicOld = null;
		if (headPic.startsWith(Constant.imgDomain())) {
			headPic = headPic.replaceAll(Constant.imgDomain(), "");
			if (headPic.startsWith(Constant.UPLOAD_TMP)) {
				isMove = true;
				headPicOld = headPic;
				headPic = headPic.replaceAll(Constant.UPLOAD_TMP, "");
				headPicNew = headPic;
			}
		}
		
		if (id == null) AppException.toThrow(MSG_00003);
		UserUtil.checkHeadPic(headPic);
		
		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		
		UserBean userBeanTmp = selectById(id);
		UserUtil.checkUser(userBeanTmp);
		
		userBeanTmp.setHeadPic(headPic);
		userBeanTmp.setUpdateTime(nowDateLong);
		updatePkSelVer(userBeanTmp);
		
		// redis
		BeanUtils.copyProperties(userBeanTmp, userBean);
		
		// 加入redis
		String token = userBeanTmp.getToken();
		String key = UserUtil.rKey(token);
		int index = Constant.Redis.User.INDEX;
		try (ShardedJedis shardedJedis = baseShardedJedisPoolDao.getShardedJedis();
				Jedis jedis = shardedJedis.getShard(Constant.Redis.Sharded1.NAME1);) {
			jedis.select(index);
			jedis.watch(key);
			try (Transaction tx = jedis.multi();) {
				tx.hset(key, "headPic", headPic);
				tx.expire(key, Constant.Redis.User.SECONDS);
				if (CollectionUtils.isEmpty(tx.exec())) AppException.toThrow(MSG_00008);
			} catch (Exception e) {
				throw e;
			}
		} catch (Exception e) {
			throw e;
		}
		
		// 移动文件
		if (isMove) {
			Files.createDirectories(Paths.get(Constant.uploadTopPath() + headPicNew.substring(0, headPicNew.lastIndexOf("/"))));
			Files.move(Paths.get(Constant.uploadTopPath() + headPicOld), Paths.get(Constant.uploadTopPath() + headPicNew));    //移动文件（即复制并删除源文件）
		}
	}
	
	/**
	 * 修改 手机
	 * @param param
	 * @throws Exception 
	 */
	private void updatePhone(Map<String, Object> param) throws Exception {
		if (CollectionUtils.isEmpty(param)) AppException.toThrow(MSG_00003);
		UserBean userBean = (UserBean) param.get("userBean");
		PhoneMsgBean phoneMsgBean = (PhoneMsgBean) param.get("phoneMsgBean");
		
		if (userBean == null) AppException.toThrow(MSG_00003);
		if (phoneMsgBean == null) AppException.toThrow(MSG_00003);
		Long id = userBean.getId();
		Long phone = userBean.getPhone();
		
		if (id == null) AppException.toThrow(MSG_00003);
		UserUtil.checkPhone(phone);
		
		String code = StringUtils.trim(phoneMsgBean.getCode());
		if (StringUtils.isEmpty(code)) AppException.toThrow(MSG_01011);
		else if (code.length() != Constant.PhoneMsg.LENGTH) AppException.toThrow(MSG_01012);
		
		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		Long craeteTimeStart = DateUtil.formatDate2Long(DateUtil.changeSecond(nowDate, -Constant.PhoneMsg.EFFECTIVE_TIME));
		
		PhoneMsgBean phoneMsgBeanTmp = PhoneMsgBean.builder()
		.code(code)
		.phone(phone)
		.type(Constant.PhoneMsg.Type._3)
		.status(Constant.PhoneMsg.Status._0)
		.createTimeStart(craeteTimeStart)
		.build();
		phoneMsgBeanTmp = phoneMsgService.selectOne(phoneMsgBeanTmp);
		if (phoneMsgBeanTmp == null) AppException.toThrow(MSG_01012);
		// 修改验证码状态
		phoneMsgBeanTmp.setStatus(Constant.PhoneMsg.Status._1);
		phoneMsgBeanTmp.setUpdateTime(nowDateLong);
		phoneMsgService.exe(UPDATE_PK_SEl_VER, phoneMsgBeanTmp);
		
		// 查询用户是否注册
		UserBean userBeanTmp = selectById(id);
		UserUtil.checkUser(userBeanTmp);
		if (userBeanTmp.getLoginName().equals(phone.toString())) AppException.toThrow(MSG_01023);
		
		UserBean userBeanTmp2 = UserBean.builder().loginName(phone.toString()).isDelete(Constant.DELETE_N).build();
		userBeanTmp2 = selectOne(userBeanTmp2);
		if (userBeanTmp2 != null) AppException.toThrow(MSG_01023);
		
		userBeanTmp.setLoginName(phone.toString());
		userBeanTmp.setPhone(phone);
		userBeanTmp.setUpdateTime(nowDateLong);
		updatePkSelVer(userBeanTmp);
		
		// redis
		BeanUtils.copyProperties(userBeanTmp, userBean);
		
		// 加入redis
		String token = userBeanTmp.getToken();
		String key = UserUtil.rKey(token);
		int index = Constant.Redis.User.INDEX;
		try (ShardedJedis shardedJedis = baseShardedJedisPoolDao.getShardedJedis();
				Jedis jedis = shardedJedis.getShard(Constant.Redis.Sharded1.NAME1);) {
			jedis.select(index);
			jedis.watch(key);
			try (Transaction tx = jedis.multi();) {
				tx.hset(key, "loginName", phone.toString());
				tx.expire(key, Constant.Redis.User.SECONDS);
				if (CollectionUtils.isEmpty(tx.exec())) AppException.toThrow(MSG_00008);
			} catch (Exception e) {
				throw e;
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 修改 全部
	 * @param userBean
	 */
	private void updateAll(UserBean userBean) {
		if (userBean == null) AppException.toThrow(MSG_00003);
		Long id = userBean.getId();
		String realName = StringUtils.trim(userBean.getRealName());

		Integer birthday = userBean.getBirthday();
		Integer sex = userBean.getSex();
		Long areaId = userBean.getAreaId();
		String address = StringUtils.trim(userBean.getAddress());
		String headPic = StringUtils.trim(userBean.getHeadPic());
		
		if (id == null) AppException.toThrow(MSG_00003);
		UserUtil.checkRealName(realName);
		UserUtil.checkAddress(address);
		
		UserBean userBeanTmp = UserBean.builder().id(id).build();
		userBeanTmp = selectOne(userBeanTmp);
		if (userBeanTmp == null) AppException.toThrow(MSG_00003);
		
		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		
		// 修改
		userBeanTmp.setRealName(realName);
		userBeanTmp.setHeadPic(headPic);
		userBeanTmp.setBirthday(birthday);
		userBeanTmp.setSex(sex);
		userBeanTmp.setAreaId(areaId);
		userBeanTmp.setAddress(address);
		userBeanTmp.setUpdateTime(nowDateLong);
		updatePkVer(userBeanTmp);
		
		BeanUtils.copyProperties(userBeanTmp, userBean);
	}
}