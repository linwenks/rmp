package com.rmp.api.base.util;

import static com.rmp.api.util.MsgEnum.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.model.HeaderBean;
import com.rmp.api.base.model.ReqBean;
import com.rmp.api.util.constant.Constant;
import com.rmp.common.util.JsonUtil;

public class ReqUtil {
	
	public static ReqBean build(String body, HttpServletRequest request, boolean isCheckToken) {
		ReqBean reqBean = JsonUtil.fromJson(body, ReqBean.class);
		if (reqBean != null) {
			HeaderBean headerBean = reqBean.getHeader();
			if (headerBean != null) {
				if (isCheckToken) {
					String token = StringUtils.trim(headerBean.getToken());
					if (StringUtils.isEmpty(token)) throw AppException.build(MSG_00006);
				}
			}
			request.setAttribute(Constant.CURRENT_REQUEST_HEADER, headerBean);
		}
		return reqBean;
	}
	
	public static ReqBean build(String body, HttpServletRequest request) {
		return build(body, request, false);
	}
	
	public static ReqBean buildCheckToken(String body, HttpServletRequest request) {
		return build(body, request, true);
	}
	
	
}
