package com.rmp.api.service.customer.impl;

import static com.rmp.api.util.MsgEnum.*;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.service.impl.BaseServiceImpl;
import com.rmp.api.model.CustomerBean;
import com.rmp.api.model.CustomerHobbyBean;
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
public class CustomerHobbyServiceImpl extends BaseServiceImpl implements CustomerHobbyService {
	
	@Autowired
	private CustomerHobbyMapper customerHobbyMapper;
	@Autowired
	private CustomerService customerService;

	@Override
	public Class<?> getModelClass() {
		return CustomerHobby.class;
	}

	@Override
	public Class<?> getBeanClass() {
		return CustomerHobbyBean.class;
	}

	@Override
	public Class<?> getCriteriaClass() {
		return CustomerHobbyCriteria.class;
	}

	@Override
	public Object getMapper() {
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
			throw new AppException(e);
		}
		return null;
	}
	
	@Override
	protected void where(Object criteria, Object bean) {
		if (bean == null) {
			return;
		}
		CustomerHobbyCriteria.Criteria criteriaTmp = (CustomerHobbyCriteria.Criteria) criteria;
		CustomerHobbyBean beanTmp = (CustomerHobbyBean) bean;
		criteriaTmp.andIsDeleteEqualTo(Constant.DELETE_N);
		if (beanTmp.getId() != null) {
			criteriaTmp.andIdEqualTo(beanTmp.getId());
		}
		if (beanTmp.getCustomerId() != null) {
			criteriaTmp.andCustomerIdEqualTo(beanTmp.getCustomerId());
		}
		if (beanTmp.getIsDelete() != null) {
			criteriaTmp.andIsDeleteEqualTo(beanTmp.getIsDelete());
		}
	}
	
	private void update(CustomerHobbyBean customerHobbyBean) {
		if (customerHobbyBean == null) AppException.toThrow(MSG_00003);
		Long userId = customerHobbyBean.getUserId();
		Long customerId = customerHobbyBean.getCustomerId();
		
		String interest = StringUtils.trim(customerHobbyBean.getInterest());
		String diet = StringUtils.trim(customerHobbyBean.getDiet());
		String taste = StringUtils.trim(customerHobbyBean.getTaste());
		
		if (userId == null) AppException.toThrow(MSG_00003);
		if (customerId == null) AppException.toThrow(MSG_00003);
		
		CustomerBean customerBeanTmp = new CustomerBean();
		customerBeanTmp.setId(customerId);
		customerBeanTmp.setUserId(userId);
		customerBeanTmp = customerService.selectOne(customerBeanTmp);
		if (customerBeanTmp == null) AppException.toThrow(MSG_00003);

		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		
		CustomerHobbyBean customerHobbyBeanTmp = new CustomerHobbyBean();
		customerHobbyBeanTmp.setCustomerId(customerId);
		customerHobbyBeanTmp = selectOne(customerHobbyBeanTmp);
		if (customerHobbyBeanTmp != null) {
			customerHobbyBeanTmp.setInterest(interest);
			customerHobbyBeanTmp.setDiet(diet);
			customerHobbyBeanTmp.setTaste(taste);
			customerHobbyBeanTmp.setUpdateTime(nowDateLong);
			updatePkVer(customerHobbyBeanTmp);
		} else {
			customerHobbyBeanTmp = new CustomerHobbyBean();
			customerHobbyBeanTmp.setCustomerId(customerId);
			customerHobbyBeanTmp.setInterest(interest);
			customerHobbyBeanTmp.setDiet(diet);
			customerHobbyBeanTmp.setTaste(taste);
			customerHobbyBeanTmp.setCreateTime(nowDateLong);
			insertSel(customerHobbyBeanTmp);
		}
		
		// 修改
		customerBeanTmp.setUpdateTime(nowDateLong);
		customerService.exe(UPDATE_PK_SEl_VER, customerBeanTmp);
	}
}