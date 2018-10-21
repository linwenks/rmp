package com.rmp.api.service.msg.impl;

import static com.rmp.api.util.MsgEnum.*;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.service.impl.BaseServiceImpl;
import com.rmp.api.model.PhoneMsgBean;
import com.rmp.api.model.UserBean;
import com.rmp.api.service.msg.PhoneMsgService;
import com.rmp.api.service.user.UserService;
import com.rmp.api.util.UserUtil;
import com.rmp.api.util.constant.Constant;
import com.rmp.common.util.DateUtil;
import com.rmp.common.util.PatternUtil;
import com.rmp.common.util.RandomUtil;
import com.rmp.info.mapper.PhoneMsgMapper;
import com.rmp.info.model.PhoneMsg;
import com.rmp.info.model.PhoneMsgCriteria;

/**
 * 手机短信 service impl
 * @author linw
 *
 */
@Service
public class PhoneMsgServiceImpl extends BaseServiceImpl implements PhoneMsgService {
	
	@Autowired
	private PhoneMsgMapper phoneMsgMapper;
	@Autowired
	private UserService userService;

	@Override
	public Class<?> getModelClass() {
		return PhoneMsg.class;
	}

	@Override
	public Class<?> getModelBeanClass() {
		return PhoneMsgBean.class;
	}

	@Override
	public Class<?> getCriteriaClass() {
		return PhoneMsgCriteria.class;
	}

	@Override
	public Object getMapper() {
		return phoneMsgMapper;
	}
	
	@Override
	public Object exe(String cmd, Object obj) {
		try {
			switch (cmd) {
			case "send": send((PhoneMsgBean) obj); break;
			default: return super.exe(cmd, obj);
			}
		} catch (AppException e) {
			throw e;
		} catch (Exception e) {
			throw new AppException(e);
		}
		return null;
	}
	
	/**
	 * 发送短信
	 * @param phoneMsgBean
	 */
	private void send(PhoneMsgBean phoneMsgBean) {
		if (phoneMsgBean == null) throw AppException.build(MSG_00003);
		
		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		Long craeteTimeStart = DateUtil.formatDate2Long(DateUtil.changeSecond(nowDate, -Constant.PhoneMsg.SEND_INTERVAL_TIME));
		
		Integer type = phoneMsgBean.getType();
		if (type == null) throw AppException.build(MSG_00003);
		Long phone = phoneMsgBean.getPhone();
		if (phone == null) throw AppException.build(MSG_01014);
		if (!PatternUtil.matchesMobilePhone(phone.toString())) throw AppException.build(MSG_01015);
		
		PhoneMsgBean phoneMsgBeanTmp = new PhoneMsgBean();
		phoneMsgBeanTmp.setPhone(phone);
		phoneMsgBeanTmp.setType(type);
		phoneMsgBeanTmp.setCreateTimeStart(craeteTimeStart);
		phoneMsgBeanTmp = this.selectOne(phoneMsgBeanTmp);
		if (phoneMsgBeanTmp != null) throw AppException.build(MSG_01007);
		
		// 验证账号
		UserBean userBean = new UserBean();
		userBean.setLoginName(phone.toString());
		userBean = userService.selectOne(userBean);
		if (Constant.PhoneMsg.Type._1 == type.intValue()) {
			if (userBean != null) throw AppException.build(MSG_01009);
		} else if (Constant.PhoneMsg.Type._2 == type.intValue()) {
			if (userBean == null) throw AppException.build(MSG_01002);
			UserUtil.checkUser(userBean);
		}
		
		String code = RandomUtil.getRandomCode(Constant.PhoneMsg.LENGTH);
		
		phoneMsgBean.setCreateTime(nowDateLong);
		phoneMsgBean.setCode(code);
		phoneMsgBean.setContent(code);
		this.insertSel(phoneMsgBean);
		/*
		// 请求短信接口，如果发送失败，抛出异常
		Map<String, Object> result = SmsUtil.send(phone.toString(), code);
//		Map<String, Object> result = new HashMap<>();
		result.put("statusCode", 200);
		result.put("responseContent", "{\"ret\":0,\"msg\":\"Success\"}");
		log.info(" sms send result : " + result);
		if (CollectionUtils.isEmpty(result)) throw AppException.build(MSG_00002);
		Integer statusCode = (Integer) result.get("statusCode");
		if (statusCode == null || statusCode.intValue() != 200) throw AppException.build(MSG_00002);
		String responseContent = (String) result.get("responseContent");
		if (StringUtils.isEmpty(responseContent)) throw AppException.build(MSG_00002);
		Map<String, String> resMap = JsonUtil.fromJson(responseContent, Map.class);
		if (CollectionUtils.isEmpty(resMap)) throw AppException.build(MSG_00002);
		Object ret = resMap.get("ret");
		if (ret == null) {
			log.error(" sms send result : " + result);
			throw AppException.build(MSG_00002);
		} else if (0 != Double.valueOf(ret.toString()).intValue()) {
			log.error(" sms send result : " + result);
			throw AppException.build(MSG_00002);
		}
		*/
	}

	@Override
	protected void where(Object criteria, Object bean) {
		if (bean == null) {
			return;
		}
		PhoneMsgCriteria.Criteria criteriaTmp = (PhoneMsgCriteria.Criteria) criteria;
		PhoneMsgBean beanTmp = (PhoneMsgBean) bean;
		if (beanTmp.getId() != null) {
			criteriaTmp.andIdEqualTo(beanTmp.getId());
		}
		if (beanTmp.getType() != null) {
			criteriaTmp.andTypeEqualTo(beanTmp.getType());
		}
		if (beanTmp.getStatus() != null) {
			criteriaTmp.andStatusEqualTo(beanTmp.getStatus());
		}
		if (beanTmp.getPhone() != null) {
			criteriaTmp.andPhoneEqualTo(beanTmp.getPhone());
		}
		if (beanTmp.getCreateTimeStart() != null) {
			criteriaTmp.andCreateTimeGreaterThan(beanTmp.getCreateTimeStart());
		}
		if (beanTmp.getCode() != null) {
			criteriaTmp.andCodeEqualTo(beanTmp.getCode());
		}
	}
}