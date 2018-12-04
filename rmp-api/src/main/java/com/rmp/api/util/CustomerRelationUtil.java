package com.rmp.api.util;

import static com.rmp.api.util.constant.Constant.SysCode.*;

import com.rmp.api.model.CustomerRelationBean;

public class CustomerRelationUtil {

	public static void assembly(CustomerRelationBean bean) {
		if (bean != null) {
			if (bean.getRelationship() != null) {
				bean.setRelationshipValue(SysCodeUtil.getValue(CUSTOMER, CUSTOMER_RELATION, CUSTOMER_RELATION_RELATIONSHIP, bean.getRelationship().toString()));
			}
			if (bean.getIntimacy() != null) {
				bean.setIntimacyValue(SysCodeUtil.getValue(CUSTOMER, CUSTOMER_RELATION, CUSTOMER_RELATION_INTIMACY, bean.getIntimacy().toString()));
			}
			if (bean.getImportance() != null) {
				bean.setImportanceValue(SysCodeUtil.getValue(CUSTOMER, CUSTOMER_RELATION, CUSTOMER_RELATION_IMPORTANCE, bean.getImportance().toString()));
			}
		}
	}
}