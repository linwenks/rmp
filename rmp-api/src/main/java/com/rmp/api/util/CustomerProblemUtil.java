package com.rmp.api.util;

import static com.rmp.api.util.constant.Constant.SysCode.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import com.rmp.api.model.CustomerProblemBean;

public class CustomerProblemUtil {

	public static void assembly(CustomerProblemBean bean) {
		if (bean != null) {
			if (!StringUtils.isEmpty(bean.getHealth())) {
				List<String> keyList = Arrays.asList(StringUtils.split(bean.getHealth(), ","));
				bean.setHealthKeyList(keyList);
				bean.setHealthValueList(keyList.stream().map(key -> {return SysCodeUtil.getValue(CUSTOMER, CUSTOMER_PROBLEM, CUSTOMER_PROBLEM_HEALTH, key);}).collect(Collectors.toList()));
			}
			if (!StringUtils.isEmpty(bean.getLife())) {
				List<String> keyList = Arrays.asList(StringUtils.split(bean.getLife(), ","));
				bean.setLifeKeyList(keyList);
				bean.setLifeValueList(keyList.stream().map(key -> {return SysCodeUtil.getValue(CUSTOMER, CUSTOMER_PROBLEM, CUSTOMER_PROBLEM_LIFE, key);}).collect(Collectors.toList()));
			}
		}
	}
}