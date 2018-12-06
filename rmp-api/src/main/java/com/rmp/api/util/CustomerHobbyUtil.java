package com.rmp.api.util;

import static com.rmp.api.util.constant.Constant.SysCode.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.rmp.api.model.CustomerHobbyBean;
import com.rmp.api.model.SysCodeBean;

public class CustomerHobbyUtil {

	public static void assembly(CustomerHobbyBean bean) {
		if (bean != null) {
			if (!StringUtils.isEmpty(bean.getInterest())) {
				List<String> keyList = Arrays.asList(StringUtils.split(bean.getInterest(), ","));
				if (!CollectionUtils.isEmpty(keyList)) {
					bean.setInterestSysCodeBeanList(keyList.stream().map(key -> SysCodeBean.builder().key(key).value(SysCodeUtil.getValue(CUSTOMER, CUSTOMER_HOBBY, CUSTOMER_HOBBY_INTEREST, key)).build()).collect(Collectors.toList()));
				}
			}
			if (!StringUtils.isEmpty(bean.getDiet())) {
				List<String> keyList = Arrays.asList(StringUtils.split(bean.getDiet(), ","));
				if (!CollectionUtils.isEmpty(keyList)) {
					bean.setDietSysCodeBeanList(keyList.stream().map(key -> SysCodeBean.builder().key(key).value(SysCodeUtil.getValue(CUSTOMER, CUSTOMER_HOBBY, CUSTOMER_HOBBY_DIET, key)).build()).collect(Collectors.toList()));
				}
			}
			if (!StringUtils.isEmpty(bean.getTaste())) {
				List<String> keyList = Arrays.asList(StringUtils.split(bean.getTaste(), ","));
				if (!CollectionUtils.isEmpty(keyList)) {
					bean.setTasteSysCodeBeanList(keyList.stream().map(key -> SysCodeBean.builder().key(key).value(SysCodeUtil.getValue(CUSTOMER, CUSTOMER_HOBBY, CUSTOMER_HOBBY_TASTE, key)).build()).collect(Collectors.toList()));
				}
			}
		}
	}
}