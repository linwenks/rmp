package com.rmp.api.base.util;

import javax.servlet.http.HttpServletRequest;

import com.rmp.api.base.model.HeaderBean;
import com.rmp.api.base.model.ReqBean;
import com.rmp.api.util.constant.Constant;
import com.rmp.common.util.JsonUtil;

public class ReqUtil {

	public static ReqBean build(String body, HttpServletRequest request) {
		ReqBean reqBean = JsonUtil.fromJson(body, ReqBean.class);
		if (reqBean != null) {
			HeaderBean headerBean = reqBean.getHeaderBean();
			if (headerBean != null) {
				String token = headerBean.getToken();
			}
			request.setAttribute(Constant.CURRENT_REQUEST_HEADER, headerBean);
		}
		return reqBean;
	}
}
