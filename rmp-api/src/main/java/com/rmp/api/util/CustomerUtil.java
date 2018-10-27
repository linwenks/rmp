package com.rmp.api.util;

import static com.rmp.api.util.MsgEnum.*;

import org.apache.commons.lang.StringUtils;

import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.util.BaseUtil;
import com.rmp.common.util.PatternUtil;

public class CustomerUtil extends BaseUtil {
	
	/**
	 * 检查 真实姓名
	 * @param realName
	 */
	public static void checkRealName(String realName) {
		if (StringUtils.isEmpty(realName)) AppException.toThrow(MSG_02000);
		int realNameMax = 10;
		if (realName.length() > realNameMax) AppException.toThrow(MSG_02001, String.valueOf(realNameMax));
	}

	/**
	 * 检查 手机
	 * @param phone
	 */
	public static void checkPhone(Long phone) {
		if (phone == null) AppException.toThrow(MSG_02002);
		if (!PatternUtil.matchesMobilePhone(phone.toString())) AppException.toThrow(MSG_02003);
	}
}