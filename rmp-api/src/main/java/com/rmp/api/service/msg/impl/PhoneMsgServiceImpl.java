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
public class PhoneMsgServiceImpl extends BaseServiceImpl<PhoneMsg, PhoneMsgBean, PhoneMsgCriteria> implements PhoneMsgService {
	
	@Autowired
	private PhoneMsgMapper phoneMsgMapper;
	@Autowired
	private UserService userService;
	
	@Override
	public PhoneMsgMapper mapper() {
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
			AppException.toThrow(e);
		}
		return null;
	}
	
	/**
	 * 发送短信
	 * @param phoneMsgBean
	 */
	private void send(PhoneMsgBean phoneMsgBean) {
		if (phoneMsgBean == null) AppException.toThrow(MSG_00003);
		
		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		Long craeteTimeStart = DateUtil.formatDate2Long(DateUtil.changeSecond(nowDate, -Constant.PhoneMsg.SEND_INTERVAL_TIME));
		
		Integer type = phoneMsgBean.getType();
		if (type == null) AppException.toThrow(MSG_00003);
		Long phone = phoneMsgBean.getPhone();
		if (phone == null) AppException.toThrow(MSG_01014);
		if (!PatternUtil.matchesMobilePhone(phone.toString())) AppException.toThrow(MSG_01015);
		
		PhoneMsgBean phoneMsgBeanTmp = new PhoneMsgBean();
		phoneMsgBeanTmp.setPhone(phone);
		phoneMsgBeanTmp.setType(type);
		phoneMsgBeanTmp.setCreateTimeStart(craeteTimeStart);
		phoneMsgBeanTmp = this.selectOne(phoneMsgBeanTmp);
		if (phoneMsgBeanTmp != null) AppException.toThrow(MSG_01013);
		
		// 验证账号
		UserBean userBean = new UserBean();
		userBean.setLoginName(phone.toString());
		userBean = userService.selectOne(userBean);
		if (Constant.PhoneMsg.Type._1 == type.intValue()) {
			if (userBean != null) AppException.toThrow(MSG_01009);
		} else if (Constant.PhoneMsg.Type._2 == type.intValue()) {
			if (userBean == null) AppException.toThrow(MSG_01002);
			UserUtil.checkUser(userBean);
		} else if (Constant.PhoneMsg.Type._3 == type.intValue()) {
			if (userBean == null) AppException.toThrow(MSG_00003);
			UserUtil.checkUser(userBean);
			
			if (userBean.getLoginName().equals(phone.toString())) AppException.toThrow(MSG_01023);
			
			UserBean userBeanTmp = new UserBean();
			userBeanTmp.setLoginName(phone.toString());
			userBeanTmp.setIsDelete(Constant.DELETE_N);
			userBeanTmp = userService.selectOne(userBeanTmp);
			if (userBeanTmp != null) AppException.toThrow(MSG_01023);
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
		if (CollectionUtils.isEmpty(result)) AppException.toThrow(MSG_00002);
		Integer statusCode = (Integer) result.get("statusCode");
		if (statusCode == null || statusCode.intValue() != 200) AppException.toThrow(MSG_00002);
		String responseContent = (String) result.get("responseContent");
		if (StringUtils.isEmpty(responseContent)) AppException.toThrow(MSG_00002);
		Map<String, String> resMap = JsonUtil.fromJson(responseContent, Map.class);
		if (CollectionUtils.isEmpty(resMap)) AppException.toThrow(MSG_00002);
		Object ret = resMap.get("ret");
		if (ret == null) {
			log.error(" sms send result : " + result);
			AppException.toThrow(MSG_00002);
		} else if (0 != Double.valueOf(ret.toString()).intValue()) {
			log.error(" sms send result : " + result);
			AppException.toThrow(MSG_00002);
		}
		*/
	}

	@Override
	protected void where(Object criteria, PhoneMsgBean bean) {
		if (bean == null) {
			return;
		}
		PhoneMsgCriteria.Criteria criteriaTmp = (PhoneMsgCriteria.Criteria) criteria;
		if (bean.getId() != null) {
			criteriaTmp.andIdEqualTo(bean.getId());
		}
		if (bean.getType() != null) {
			criteriaTmp.andTypeEqualTo(bean.getType());
		}
		if (bean.getStatus() != null) {
			criteriaTmp.andStatusEqualTo(bean.getStatus());
		}
		if (bean.getPhone() != null) {
			criteriaTmp.andPhoneEqualTo(bean.getPhone());
		}
		if (bean.getCreateTimeStart() != null) {
			criteriaTmp.andCreateTimeGreaterThan(bean.getCreateTimeStart());
		}
		if (bean.getCode() != null) {
			criteriaTmp.andCodeEqualTo(bean.getCode());
		}
	}
}