package com.rmp.api.util;

import com.rmp.api.model.UserRemindBean;

public class UserRemindUtil {

	public static void assembly(UserRemindBean bean) {
		if (bean != null) {
			if (bean.getAdvanceDay() != null) {
				String advanceDayStr = bean.getAdvanceDay().toString() + "天后";
				switch (bean.getAdvanceDay()) {
				case 1:
					advanceDayStr = "明天"; break;
				case 2:
					advanceDayStr = "后台"; break;
				default:
					break;
				}
				bean.setAdvanceDayStr(advanceDayStr);
			}
			UserRemindUtil.assembly(bean);
			CustomerUtil.assembly(bean.getCustomerBean());
		}
	}
}