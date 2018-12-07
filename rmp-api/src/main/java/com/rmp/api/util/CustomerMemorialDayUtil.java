package com.rmp.api.util;

import static com.rmp.api.util.MsgEnum.*;
import static com.rmp.api.util.constant.Constant.SysCode.*;

import org.apache.commons.lang.StringUtils;

import com.rmp.api.base.exception.AppException;
import com.rmp.api.model.CustomerMemorialDayBean;
import com.rmp.common.util.DateUtil;

public class CustomerMemorialDayUtil {

	/**
	 * 检查 名称
	 * @param realName
	 */
	public static void checkName(String name) {
		if (StringUtils.isEmpty(name)) AppException.toThrow(MSG_02020);
		int nameMax = 10;
		if (name.length() > nameMax) AppException.toThrow(MSG_02021, String.valueOf(nameMax));
	}
	
	public static void assembly(CustomerMemorialDayBean bean) {
		if (bean != null) {
			if (bean.getAdvanceType() != null) {
				bean.setAdvanceTypeValue(SysCodeUtil.getValue(CUSTOMER, CUSTOMER_MEMORIAL_DAY, CUSTOMER_MEMORIAL_DAY_ADVANCE_TYPE, bean.getAdvanceType().toString()));
			}
			if (bean.getOccurType() != null) {
				bean.setOccurTypeValue(SysCodeUtil.getValue(CUSTOMER, CUSTOMER_MEMORIAL_DAY, CUSTOMER_MEMORIAL_DAY_OCCUR_TYPE, bean.getOccurType().toString()));
			}
			if (bean.getOccurDate() != null && bean.getOccurType() != null) {
				switch (bean.getOccurType()) {
				case 1:
					bean.setOccurDateStr(DateUtil.formatDate(DateUtil.parseDate(bean.getOccurDate().toString(), DateUtil.yyyyMMdd)));
					break;
				case 2:
					bean.setOccurDateStr(DateUtil.formatDate(DateUtil.parseDate(bean.getOccurDate().toString(), DateUtil.MMdd)));
					break;
				default:
					break;
				}
			}
		}
	}
}