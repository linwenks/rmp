package com.rmp.api.util;

import static com.rmp.api.util.constant.Constant.SysCode.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import com.rmp.api.model.CustomerHobbyBean;

public class CustomerHobbyUtil {

	public static void assembly(CustomerHobbyBean bean) {
		if (bean != null) {
			if (!StringUtils.isEmpty(bean.getInterest())) {
				List<String> keyList = Arrays.asList(StringUtils.split(bean.getInterest(), ","));
				bean.setInterestKeyList(keyList);
				bean.setInterestValueList(keyList.stream().map(key -> {return SysCodeUtil.getValue(CUSTOMER_HOBBY_INTEREST, key);}).collect(Collectors.toList()));
			}
			if (!StringUtils.isEmpty(bean.getDiet())) {
				List<String> keyList = Arrays.asList(StringUtils.split(bean.getDiet(), ","));
				bean.setDietKeyList(keyList);
				bean.setDietValueList(keyList.stream().map(key -> {return SysCodeUtil.getValue(CUSTOMER_HOBBY_DIET, key);}).collect(Collectors.toList()));
			}
			if (!StringUtils.isEmpty(bean.getTaste())) {
				List<String> keyList = Arrays.asList(StringUtils.split(bean.getTaste(), ","));
				bean.setTasteKeyList(keyList);
				bean.setTasteValueList(keyList.stream().map(key -> {return SysCodeUtil.getValue(CUSTOMER_HOBBY_TASTE, key);}).collect(Collectors.toList()));
			}
		}
	}
}
