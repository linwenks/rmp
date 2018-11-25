package com.rmp.api.util;

import static com.rmp.api.util.MsgEnum.*;

import org.apache.commons.lang.StringUtils;

import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.util.BaseUtil;
import com.rmp.api.model.CustomerBean;
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
	
	/**
	 * 检查 地址
	 * @param address
	 */
	public static void checkAddress(String address) {
		int addressMaxLength = 100;
		if (!StringUtils.isEmpty(address) && address.length() > addressMaxLength) AppException.toThrow(MSG_02009, String.valueOf(addressMaxLength));
	}
	
	public static void assembly(CustomerBean bean) {
		if (bean != null) {
			if (bean.getAreaId() != null) {
				bean.setAreaNameAll(AreaUtil.getNameAll(bean.getAreaId()));
			}
			if (!StringUtils.isEmpty(bean.getHeadPic()) && !bean.getHeadPic().startsWith("http")) {
				bean.setHeadPic("https://img.rmp.com" + bean.getHeadPic());    // 获取图片域名
			}
			if (!StringUtils.isEmpty(bean.getPinyin())) {
				bean.setPinyinFirst(bean.getPinyin().substring(0, 1));
			}
		}
	}
}