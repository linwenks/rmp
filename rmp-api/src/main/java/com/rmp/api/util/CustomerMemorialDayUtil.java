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
	
	/**
	 * 检查
	 * @param occurType 发生类型
	 * @param occurDate 关系日期
	 */
	public static void checkOccur(Integer occurType, Integer occurDate) {
		if (occurType == null) AppException.toThrow(MSG_02022);
		if (occurDate == null) AppException.toThrow(MSG_02023);
		
		switch (occurType) {
		case 1:
			if (occurDate.toString().length() != 8) AppException.toThrow(MSG_02027);
			break;
		case 2:
			if (occurDate.toString().length() < 3 || occurDate.toString().length() > 4) AppException.toThrow(MSG_02027);
			break;
		case 3:
			if (occurDate < 1 || occurDate > 31) AppException.toThrow(MSG_02027);
			break;
		case 4:
			if (occurDate < 1 || occurDate > 7) AppException.toThrow(MSG_02027);
			break;
		default:
			AppException.toThrow(MSG_02026);
		}
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
					String occurDate = bean.getOccurDate().toString();
					if (occurDate.length() == 3) {
						occurDate = "0" + occurDate;
					}
					bean.setOccurDateStr("每年" + DateUtil.formatDate(DateUtil.parseDate(occurDate, DateUtil.MMdd), "MM月dd号"));
					break;
				case 3:
					bean.setOccurDateStr("每月" + bean.getOccurDate().toString() + "号");
					break;
				case 4:
					String occurDateStr = "";
					switch (bean.getOccurDate()) {
					case 1: occurDateStr = "星期一"; break;
					case 2: occurDateStr = "星期二"; break;
					case 3: occurDateStr = "星期三"; break;
					case 4: occurDateStr = "星期四"; break;
					case 5: occurDateStr = "星期五"; break;
					case 6: occurDateStr = "星期六"; break;
					case 7: occurDateStr = "星期日"; break;
					default:
						break;
					}
					bean.setOccurDateStr(occurDateStr);
					break;
				default:
					bean.setOccurDateStr(bean.getOccurDate().toString());
					break;
				}
			}
		}
	}
}