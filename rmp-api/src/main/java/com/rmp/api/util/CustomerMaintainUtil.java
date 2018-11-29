package com.rmp.api.util;

import static com.rmp.api.util.constant.Constant.SysCode.*;

import com.rmp.api.model.CustomerMaintainBean;

public class CustomerMaintainUtil {

	public static void assembly(CustomerMaintainBean bean) {
		if (bean != null) {
			if (bean.getMaintain() != null) {
				bean.setMaintainValue(SysCodeUtil.getValue(CUSTOMER_MAINTAIN_MAINTAIN, bean.getMaintain().toString()));
			}
		}
	}
}