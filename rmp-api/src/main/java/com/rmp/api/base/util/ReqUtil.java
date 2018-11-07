package com.rmp.api.base.util;

import static com.rmp.api.util.MsgEnum.*;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.model.HeaderBean;
import com.rmp.api.base.model.ReqBean;
import com.rmp.api.model.UserBean;
import com.rmp.api.util.UserUtil;
import com.rmp.api.util.constant.Constant;
import com.rmp.common.util.JsonUtil;

@Component
public class ReqUtil extends BaseUtil {
	
	public ReqUtil() {
		super();
	}
	
	public static ReqBean build(String body, HttpServletRequest request, boolean isCheckToken, boolean isCheckLogin) {
		ReqBean reqBean = JsonUtil.fromJson(body, ReqBean.class);
		if (reqBean != null) {
			HeaderBean headerBean = reqBean.getHeader();
			if (headerBean != null) {
				if (isCheckToken) {
					String token = StringUtils.trim(headerBean.getToken());
					if (StringUtils.isEmpty(token)) AppException.toThrow(MSG_00006);
					
					if (isCheckLogin) {
						String key = UserUtil.rKey(token);
						Integer index = Constant.Redis.User.INDEX;
						
						Map<String, String> userMap = jedisDao.hgetAll(index, key);
						if (CollectionUtils.isEmpty(userMap)) {
	
							UserBean userBeanTmp = new UserBean();
							userBeanTmp.setToken(token);
							userBeanTmp = userService.selectOne(userBeanTmp);
							if (userBeanTmp == null) AppException.toThrow(MSG_00007);
							UserUtil.checkUser(userBeanTmp);
							
							userMap = UserUtil.rMap(userBeanTmp);
							jedisDao.hmset(index, key, userMap);
						}
	
						// 存入 request
						UserUtil.setCurrentUser(UserUtil.rBean(userMap), request);
						
						jedisDao.expire(index, key, Constant.Redis.User.SECONDS);
					}
				}
			}
			request.setAttribute(Constant.CURRENT_REQUEST_HEADER, headerBean);
		}
		return reqBean;
	}
	
	public static ReqBean build(String body, HttpServletRequest request) {
		return build(body, request, false, false);
	}
	/*
	public static ReqBean buildCheckToken(String body, HttpServletRequest request) {
		return build(body, request, true, false);
	}
	*/
	public static ReqBean buildCheckLogin(String body, HttpServletRequest request) {
		return build(body, request, true, true);
	}
}
