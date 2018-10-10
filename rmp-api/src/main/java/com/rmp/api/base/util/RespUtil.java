package com.rmp.api.base.util;

import javax.servlet.http.HttpServletRequest;

import com.rmp.api.base.model.HeaderBean;
import com.rmp.api.base.model.RespBean;
import com.rmp.api.util.constant.Constant;

public class RespUtil {

	public static RespBean build(HttpServletRequest request) {
		RespBean respBean = new RespBean();
		respBean.setState(Constant.Msg.SUCCESS);
		respBean.setHeader((HeaderBean) request.getAttribute(Constant.CURRENT_REQUEST_HEADER));
		return respBean;
	}
}
