package com.rmp.api.service.customer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.service.impl.BaseServiceImpl;
import com.rmp.api.model.CustomerFamilyBean;
import com.rmp.api.service.customer.CustomerFamilyService;
import com.rmp.info.mapper.CustomerFamilyMapper;
import com.rmp.info.model.CustomerFamily;
import com.rmp.info.model.CustomerFamilyCriteria;

/**
 * 客户 家庭 service impl
 * @author linw
 *
 */
@Service
public class CustomerFamilyServiceImpl extends BaseServiceImpl implements CustomerFamilyService {
	
	@Autowired
	private CustomerFamilyMapper customerFamilyMapper;

	@Override
	public Class<?> getModelClass() {
		return CustomerFamily.class;
	}

	@Override
	public Class<?> getBeanClass() {
		return CustomerFamilyBean.class;
	}

	@Override
	public Class<?> getCriteriaClass() {
		return CustomerFamilyCriteria.class;
	}

	@Override
	public Object getMapper() {
		return customerFamilyMapper;
	}
	
	@Override
	public Object exe(String cmd, Object obj) {
		try {
			switch (cmd) {
			case "save": break;
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
		CustomerFamilyCriteria.Criteria criteriaTmp = (CustomerFamilyCriteria.Criteria) criteria;
		CustomerFamilyBean beanTmp = (CustomerFamilyBean) bean;
		if (beanTmp.getId() != null) {
			criteriaTmp.andIdEqualTo(beanTmp.getId());
		}
	}
}