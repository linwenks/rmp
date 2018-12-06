package com.rmp.api.util;

import static com.rmp.api.util.constant.Constant.SysCode.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.rmp.api.model.CustomerProblemBean;
import com.rmp.api.model.SysCodeBean;

public class CustomerProblemUtil {

	public static void assembly(CustomerProblemBean bean) {
		if (bean != null) {
			if (!StringUtils.isEmpty(bean.getHealth())) {
				List<String> keyList = Arrays.asList(StringUtils.split(bean.getHealth(), ","));
				if (!CollectionUtils.isEmpty(keyList)) {
					bean.setHealthCodeList(keyList.stream().map(key -> SysCodeBean.builder().key(key).value(SysCodeUtil.getValue(CUSTOMER, CUSTOMER_PROBLEM, CUSTOMER_PROBLEM_HEALTH, key)).build()).collect(Collectors.toList()));
				}
			}
			if (!StringUtils.isEmpty(bean.getLife())) {
				List<String> keyList = Arrays.asList(StringUtils.split(bean.getLife(), ","));
				if (!CollectionUtils.isEmpty(keyList)) {
					bean.setLifeCodeList(keyList.stream().map(key -> SysCodeBean.builder().key(key).value(SysCodeUtil.getValue(CUSTOMER, CUSTOMER_PROBLEM, CUSTOMER_PROBLEM_LIFE, key)).build()).collect(Collectors.toList()));
				}
			}
		}
	}
}