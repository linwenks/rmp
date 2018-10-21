package com.rmp.api.service.user.impl;

import static com.rmp.api.util.MsgEnum.*;

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
import com.rmp.api.model.PhoneMsgBean;
import com.rmp.api.model.UserBean;
import com.rmp.api.service.msg.PhoneMsgService;
import com.rmp.api.service.user.UserService;
import com.rmp.api.util.UserUtil;
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
public class UserServiceImpl extends BaseServiceImpl implements UserService {
	
	@Autowired
	private BaseShardedJedisPoolDao baseShardedJedisPoolDao;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private PhoneMsgService phoneMsgService;
	
	@Override
	public Class<?> getModelClass() {
		return User.class;
	}

	@Override
	public Class<?> getModelBeanClass() {
		return UserBean.class;
	}

	@Override
	public Class<?> getCriteriaClass() {
		return UserCriteria.class;
	}

	@Override
	public Object getMapper() {
		return userMapper;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object exe(String cmd, Object obj) {
		try {
			switch (cmd) {
			case "initialize": initialize((UserBean) obj);break;
			case "login": login((UserBean) obj);break;
			case "register": register((Map<String, Object>) obj);break;
			case "retrievePwd": retrievePwd((Map<String, Object>) obj);break;
			default: return super.exe(cmd, obj);
			}
		} catch (AppException e) {
			throw e;
		} catch (Exception e) {
			throw AppException.build(e);
		}
		return null;
	}
	
	@Override
	protected void where(Object criteria, Object bean) {
		if (bean == null) {
			return;
		}
		UserCriteria.Criteria criteriaTmp = (UserCriteria.Criteria) criteria;
		UserBean beanTmp = (UserBean) bean;
		if (beanTmp.getId() != null) {
			criteriaTmp.andIdEqualTo(beanTmp.getId());
		}
		if (!StringUtils.isEmpty(beanTmp.getLoginName())) {
			criteriaTmp.andLoginNameEqualTo(beanTmp.getLoginName());
		}
		if (!StringUtils.isEmpty(beanTmp.getToken())) {
			criteriaTmp.andTokenEqualTo(beanTmp.getToken());
		}
		if (!StringUtils.isEmpty(beanTmp.getWxId())) {
			criteriaTmp.andWxIdEqualTo(beanTmp.getWxId());
		}
		if (beanTmp.getStatus() != null) {
			criteriaTmp.andStatusEqualTo(beanTmp.getStatus());
		}
		if (beanTmp.getIsDelete() != null) {
			criteriaTmp.andIsDeleteEqualTo(beanTmp.getIsDelete());
		}
	}
	
	/**
	 * 初始化
	 * @param userBean
	 */
	private void initialize(UserBean userBean) {
		Long nowDateLong = DateUtil.nowSecondFormat();
		
		String wxId = userBean.getWxId();
		String nickName = StringUtils.trim(userBean.getNickName());
		String headPic = StringUtils.trim(userBean.getHeadPic());
		
		String token = UuidUtil.generateUUID();
		
		UserBean userBeanTmp = new UserBean();
		userBeanTmp.setWxId(wxId);
		userBeanTmp = selectOne(userBeanTmp);
		if (userBeanTmp != null) {
			userBeanTmp.setNickName(nickName);
			userBeanTmp.setHeadPic(headPic);
			userBeanTmp.setUpdateTime(nowDateLong);
			userBeanTmp.setToken(token);
			updatePkSelVer(userBeanTmp);
		} else {
			// 初始化用户
			userBeanTmp = new UserBean();
			userBeanTmp.setLoginName(wxId);
			userBeanTmp.setLoginPwd("0");
			userBeanTmp.setWxId(wxId);
			userBeanTmp.setNickName(nickName);
			userBeanTmp.setHeadPic(headPic);
			userBeanTmp.setCreateTime(nowDateLong);
			userBeanTmp.setToken(token);
			insertSel(userBeanTmp);
		}
		BeanUtils.copyProperties(userBeanTmp, userBean);
	}
	
	/**
	 * 登录
	 * @param userBean
	 * @throws Exception 
	 */
	private void login(UserBean userBean) throws Exception {
		Long nowDateLong = DateUtil.nowSecondFormat();
		
		if (userBean == null) throw AppException.build(MSG_00003);
		String loginName = StringUtils.trim(userBean.getLoginName());
		String loginPwd = StringUtils.trim(userBean.getLoginPwd());
		
		UserUtil.checkLoginName(loginName);
		UserUtil.checkLoginPwd(loginPwd);
		
		UserBean userBeanTmp = new UserBean();
		userBeanTmp.setLoginName(loginName);
		userBeanTmp = selectOne(userBeanTmp);
		UserUtil.checkUser(userBeanTmp);
		
		String loginPwdOld = userBeanTmp.getLoginPwd();
		if (!DigestUtils.sha1Hex(loginPwd).equals(loginPwdOld)) throw AppException.build(MSG_01006);
		
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
				if (CollectionUtils.isEmpty(tx.exec())) throw AppException.build(MSG_00008);
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
		if (CollectionUtils.isEmpty(param)) throw AppException.build(MSG_00003);
		UserBean userBean = (UserBean) param.get("userBean");
		PhoneMsgBean phoneMsgBean = (PhoneMsgBean) param.get("phoneMsgBean");
		
		if (userBean == null) throw AppException.build(MSG_00003);
		if (phoneMsgBean == null) throw AppException.build(MSG_00003);
		String token = StringUtils.trim(userBean.getToken());
		String loginName = StringUtils.trim(userBean.getLoginName());
		String loginPwd = StringUtils.trim(userBean.getLoginPwd());
		
		UserUtil.checkToken(token);
		UserUtil.checkLoginName(loginName);
		UserUtil.checkLoginPwd(loginPwd);
		
		String code = StringUtils.trim(phoneMsgBean.getCode());
		if (StringUtils.isEmpty(code)) throw AppException.build(MSG_01011);
		else if (code.length() != Constant.PhoneMsg.LENGTH) throw AppException.build(MSG_01012);
		
		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		Long craeteTimeStart = DateUtil.formatDate2Long(DateUtil.changeSecond(nowDate, -Constant.PhoneMsg.EFFECTIVE_TIME));
		
		PhoneMsgBean phoneMsgBeanTmp = new PhoneMsgBean();
		phoneMsgBeanTmp.setCode(code);
		phoneMsgBeanTmp.setPhone(Long.valueOf(loginName));
		phoneMsgBeanTmp.setType(Constant.PhoneMsg.Type._1);
		phoneMsgBeanTmp.setStatus(Constant.PhoneMsg.Status._0);
		phoneMsgBeanTmp.setCreateTimeStart(craeteTimeStart);
		phoneMsgBeanTmp = phoneMsgService.selectOne(phoneMsgBeanTmp);
		if (phoneMsgBeanTmp == null) throw AppException.build(MSG_01012);
		// 修改验证码状态
		phoneMsgBeanTmp.setStatus(Constant.PhoneMsg.Status._1);
		phoneMsgBeanTmp.setUpdateTime(nowDateLong);
		phoneMsgService.exe(UPDATE_PK_SEl_VER, phoneMsgBeanTmp);
		
		// 查询用户是否注册
		UserBean userBeanTmp = new UserBean();
		userBeanTmp.setLoginName(loginName);
		userBeanTmp = selectOne(userBeanTmp);
		if (userBeanTmp != null) throw AppException.build(MSG_01009);
		
		userBeanTmp = new UserBean();
		userBeanTmp.setToken(token);
		userBeanTmp = selectOne(userBeanTmp);
		if (userBeanTmp == null) throw AppException.build(MSG_00007);
		if (!Constant.User.Status._0.equals(userBeanTmp.getStatus())) throw AppException.build(MSG_01009);
		if (Constant.DELETE_Y.equals(userBeanTmp.getIsDelete())) throw AppException.build(MSG_01017);
		
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
				if (CollectionUtils.isEmpty(tx.exec())) throw AppException.build(MSG_00008);
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
		if (CollectionUtils.isEmpty(param)) throw AppException.build(MSG_00003);
		UserBean userBean = (UserBean) param.get("userBean");
		PhoneMsgBean phoneMsgBean = (PhoneMsgBean) param.get("phoneMsgBean");

		if (userBean == null) throw AppException.build(MSG_00003);
		if (phoneMsgBean == null) throw AppException.build(MSG_00003);
		String loginName = StringUtils.trim(userBean.getLoginName());
		String loginPwd = StringUtils.trim(userBean.getLoginPwd());
		
		UserUtil.checkLoginName(loginName);
		UserUtil.checkLoginPwd(loginPwd);
		
		String code = StringUtils.trim(phoneMsgBean.getCode());
		if (StringUtils.isEmpty(code)) throw AppException.build(MSG_01011);
		else if (code.length() != Constant.PhoneMsg.LENGTH) throw AppException.build(MSG_01012);
		
		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		Long craeteTimeStart = DateUtil.nowLong2(DateUtil.changeSecond(nowDate, -Constant.PhoneMsg.EFFECTIVE_TIME));


		PhoneMsgBean phoneMsgBeanTmp = new PhoneMsgBean();
		phoneMsgBeanTmp.setCode(code);
		phoneMsgBeanTmp.setPhone(Long.valueOf(loginName));
		phoneMsgBeanTmp.setType(Constant.PhoneMsg.Type._2);
		phoneMsgBeanTmp.setStatus(Constant.PhoneMsg.Status._0);
		phoneMsgBeanTmp.setCreateTimeStart(craeteTimeStart);
		phoneMsgBeanTmp = phoneMsgService.selectOne(phoneMsgBeanTmp);
		if (phoneMsgBeanTmp == null) throw AppException.build(MSG_01012);
		// 修改验证码状态
		phoneMsgBeanTmp.setStatus(Constant.PhoneMsg.Status._1);
		phoneMsgBeanTmp.setUpdateTime(nowDateLong);
		phoneMsgService.exe(UPDATE_PK_SEl_VER, phoneMsgBeanTmp);
		
		// 查询用户是否注册
		UserBean userBeanTmp = new UserBean();
		userBeanTmp.setLoginName(loginName);
		userBeanTmp = selectOne(userBeanTmp);
		UserUtil.checkUser(userBeanTmp);
		
		userBeanTmp.setLoginPwd(DigestUtils.sha1Hex(loginPwd));
		userBeanTmp.setUpdateTime(nowDateLong);
		updatePkSelVer(userBeanTmp);
	}
}