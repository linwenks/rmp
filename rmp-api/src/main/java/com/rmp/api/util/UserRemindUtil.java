package com.rmp.api.util;

import com.rmp.api.model.UserRemindBean;
import com.rmp.common.util.DateUtil;

public class UserRemindUtil {
	
	public static final int MAX_DAY = 13;

	public static void assembly(UserRemindBean bean) {
		if (bean != null) {
			if (bean.getAdvanceDay() != null) {
				String advanceDayStr = bean.getAdvanceDay().toString() + "天后";
				switch (bean.getAdvanceDay()) {
				case 0:
					advanceDayStr = "今天"; break;
				case 1:
					advanceDayStr = "明天"; break;
				case 2:
					advanceDayStr = "后台"; break;
				default:
					break;
				}
				bean.setAdvanceDayStr(advanceDayStr);
			}
			if (bean.getRemindDate() != null) {
				bean.setRemindDateStr(DateUtil.formatDate(DateUtil.parseDate(bean.getRemindDate().toString(), DateUtil.yyyyMMdd), DateUtil.YYYY_MM_DD));
			}
			CustomerUtil.assembly(bean.getCustomerBean());
		}
	}
}