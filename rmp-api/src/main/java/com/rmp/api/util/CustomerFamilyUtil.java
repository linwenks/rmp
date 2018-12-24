package com.rmp.api.util;

import static com.rmp.api.util.MsgEnum.*;
import static com.rmp.api.util.constant.Constant.SysCode.*;

import org.apache.commons.lang.StringUtils;

import com.rmp.api.base.exception.AppException;
import com.rmp.api.model.CustomerFamilyBean;
import com.rmp.common.util.DateUtil;
import com.rmp.common.util.PatternUtil;

public class CustomerFamilyUtil {
	
	/**
	 * 检查 真实姓名
	 * @param realName
	 */
	public static void checkRealName(String realName) {
		if (StringUtils.isEmpty(realName)) AppException.toThrow(MSG_02014);
		int realNameMax = 10;
		if (realName.length() > realNameMax) AppException.toThrow(MSG_02015, String.valueOf(realNameMax));
	}

	/**
	 * 检查 手机
	 * @param phone
	 */
	public static void checkPhone(Long phone) {
		if (phone != null && !PatternUtil.matchesMobilePhone(phone.toString())) AppException.toThrow(MSG_02016);
	}
	
	/**
	 * 检查 地址
	 * @param address
	 */
	public static void checkAddress(String address) {
		int addressMaxLength = 100;
		if (!StringUtils.isEmpty(address) && address.length() > addressMaxLength) AppException.toThrow(MSG_02017, String.valueOf(addressMaxLength));
	}
	
	/**
	 * 检查 年龄
	 * @param phone
	 */
	public static void checkAge(Integer age) {
		if (age != null && age < 0) AppException.toThrow(MSG_02019);
	}
	
	/**
	 * 检查 生日
	 * @param phone
	 */
	public static void checkBirthday(Integer birthday) {
		if (birthday != null && birthday.toString().length() != 8) AppException.toThrow(MSG_02028);
	}
	
	
	public static Integer getYear(Integer age) {
		Integer year = null;
		if (age != null) {
			Integer nowYear = Integer.valueOf(DateUtil.formatDate(DateUtil.now(), DateUtil.yyyy));
			year = nowYear - age;
		}
		return year;
	}
	
	public static Integer getAge(Integer year) {
		Integer age = null;
		if (year != null) {
			Integer nowYear = Integer.valueOf(DateUtil.formatDate(DateUtil.now(), DateUtil.yyyy));
			age = nowYear - year;
		}
		return age;
	}
	

	public static void assembly(CustomerFamilyBean bean) {
		if (bean != null) {
			if (bean.getRelationship() != null) {
				bean.setRelationshipValue(SysCodeUtil.getValue(CUSTOMER, CUSTOMER_FAMILY, CUSTOMER_FAMILY_RELATIONSHIP, bean.getRelationship().toString()));
			}
			if (bean.getArea() != null) {
			//	bean.setAreaNameAll(AreaUtil.getNameAll(bean.getAreaId()));
				bean.setAreaNameAll(bean.getArea());
			}
			if (bean.getBirthday() != null) {
				int year = Integer.parseInt(bean.getBirthday().toString().substring(0, 4));
				bean.setAge(getAge(year));
				bean.setBirthdayStr(DateUtil.formatDate(DateUtil.parseDate(bean.getBirthday().toString(), DateUtil.yyyyMMdd)));
			}
		}
	}
}