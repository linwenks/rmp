package com.rmp.api.util;

import static com.rmp.api.util.constant.Constant.SysCode.*;

import com.rmp.api.model.CustomerJobBean;

public class CustomerJobUtil {

	public static void assembly(CustomerJobBean bean) {
		if (bean != null) {
			if (bean.getIndustry() != null) {
				bean.setIndustryValue(SysCodeUtil.getValue(CUSTOMER, CUSTOMER_JOB, CUSTOMER_JOB_INDUSTRY, bean.getIndustry().toString()));
			}
			if (bean.getPosition() != null) {
				bean.setPositionValue(SysCodeUtil.getValue(CUSTOMER, CUSTOMER_JOB, CUSTOMER_JOB_POSITION, bean.getPosition().toString()));
			}
			if (bean.getArea() != null) {
			//	bean.setAreaNameAll(AreaUtil.getNameAll(bean.getAreaId()));
				bean.setAreaNameAll(bean.getArea());
			}
		}
	}
}