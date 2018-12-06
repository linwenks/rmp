package com.rmp.api.service.customer.impl;

import static com.rmp.api.util.MsgEnum.*;

import java.util.Date;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.service.impl.BaseServiceImpl;
import com.rmp.api.model.CustomerBean;
import com.rmp.api.model.CustomerHobbyBean;
import com.rmp.api.model.SysCodeBean;
import com.rmp.api.service.customer.CustomerHobbyService;
import com.rmp.api.service.customer.CustomerService;
import com.rmp.api.util.constant.Constant;
import com.rmp.common.util.DateUtil;
import com.rmp.info.mapper.CustomerHobbyMapper;
import com.rmp.info.model.CustomerHobby;
import com.rmp.info.model.CustomerHobbyCriteria;

/**
 * 客户 兴趣爱好 service impl
 * @author linw
 *
 */
@Service
public class CustomerHobbyServiceImpl extends BaseServiceImpl<CustomerHobby, CustomerHobbyBean, CustomerHobbyCriteria> implements CustomerHobbyService {
	
	@Autowired
	private CustomerHobbyMapper customerHobbyMapper;
	@Autowired
	private CustomerService customerService;
	
	@Override
	public CustomerHobbyMapper mapper() {
		return customerHobbyMapper;
	}
	
	@Override
	public Object exe(String cmd, Object obj) {
		try {
			switch (cmd) {
			case "update": update((CustomerHobbyBean) obj);break;
			default: return super.exe(cmd, obj);
			}
		} catch (AppException e) {
			throw e;
		} catch (Exception e) {
			AppException.toThrow(e);
		}
		return null;
	}
	
	@Override
	protected void where(Object criteria, CustomerHobbyBean bean) {
		if (bean == null) {
			return;
		}
		CustomerHobbyCriteria.Criteria criteriaTmp = (CustomerHobbyCriteria.Criteria) criteria;
		criteriaTmp.andIsDeleteEqualTo(Constant.DELETE_N);
		if (bean.getId() != null) {
			criteriaTmp.andIdEqualTo(bean.getId());
		}
		if (bean.getCustomerId() != null) {
			criteriaTmp.andCustomerIdEqualTo(bean.getCustomerId());
		}
		if (bean.getIsDelete() != null) {
			criteriaTmp.andIsDeleteEqualTo(bean.getIsDelete());
		}
	}
	
	private void update(CustomerHobbyBean customerHobbyBean) {
		if (customerHobbyBean == null) AppException.toThrow(MSG_00003);
		Long userId = customerHobbyBean.getUserId();
		Long customerId = customerHobbyBean.getCustomerId();
		
		String interest = null;
		String diet = null;
		String taste = null;
		if (!CollectionUtils.isEmpty(customerHobbyBean.getInterestCodeList())) {
			interest = customerHobbyBean.getInterestCodeList().stream().map(SysCodeBean::getKey).filter(StringUtils::isNotEmpty).distinct().collect(Collectors.joining(","));
		}
		if (!CollectionUtils.isEmpty(customerHobbyBean.getDietCodeList())) {
			diet = customerHobbyBean.getDietCodeList().stream().map(SysCodeBean::getKey).filter(StringUtils::isNotEmpty).distinct().collect(Collectors.joining(","));
		}
		if (!CollectionUtils.isEmpty(customerHobbyBean.getTasteCodeList())) {
			taste = customerHobbyBean.getTasteCodeList().stream().map(SysCodeBean::getKey).filter(StringUtils::isNotEmpty).distinct().collect(Collectors.joining(","));
		}
		/*
		String interest = StringUtils.trim(customerHobbyBean.getInterest());
		String diet = StringUtils.trim(customerHobbyBean.getDiet());
		String taste = StringUtils.trim(customerHobbyBean.getTaste());
		*/
		if (userId == null) AppException.toThrow(MSG_00003);
		if (customerId == null) AppException.toThrow(MSG_00003);
		
		CustomerBean customerBeanTmp = CustomerBean.builder().id(customerId).userId(userId).build();
		customerBeanTmp = customerService.selectOne(customerBeanTmp);
		if (customerBeanTmp == null) AppException.toThrow(MSG_00003);

		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		
		CustomerHobbyBean customerHobbyBeanTmp = CustomerHobbyBean.builder().customerId(customerId).build();
		customerHobbyBeanTmp = selectOne(customerHobbyBeanTmp);
		if (customerHobbyBeanTmp != null) {
			customerHobbyBeanTmp.setInterest(interest);
			customerHobbyBeanTmp.setDiet(diet);
			customerHobbyBeanTmp.setTaste(taste);
			customerHobbyBeanTmp.setUpdateTime(nowDateLong);
			updatePkVer(customerHobbyBeanTmp);
		} else {
			customerHobbyBeanTmp = CustomerHobbyBean.builder()
			.customerId(customerId)
			.interest(interest)
			.diet(diet)
			.taste(taste)
			.createTime(nowDateLong)
			.build();
			insertSel(customerHobbyBeanTmp);
		}
		
		// 修改
		customerBeanTmp.setUpdateTime(nowDateLong);
		customerService.exe(UPDATE_PK_SEl_VER, customerBeanTmp);
	}
}