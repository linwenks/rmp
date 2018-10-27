package com.rmp.api.service.customer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.service.impl.BaseServiceImpl;
import com.rmp.api.model.CustomerMaintainBean;
import com.rmp.api.service.customer.CustomerMaintainService;
import com.rmp.info.mapper.CustomerMaintainMapper;
import com.rmp.info.model.CustomerMaintain;
import com.rmp.info.model.CustomerMaintainCriteria;

/**
 * 客户 维护设置 service impl
 * @author linw
 *
 */
@Service
public class CustomerMaintainServiceImpl extends BaseServiceImpl implements CustomerMaintainService {
	
	@Autowired
	private CustomerMaintainMapper customerMaintainMapper;

	@Override
	public Class<?> getModelClass() {
		return CustomerMaintain.class;
	}

	@Override
	public Class<?> getBeanClass() {
		return CustomerMaintainBean.class;
	}

	@Override
	public Class<?> getCriteriaClass() {
		return CustomerMaintainCriteria.class;
	}

	@Override
	public Object getMapper() {
		return customerMaintainMapper;
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
		CustomerMaintainCriteria.Criteria criteriaTmp = (CustomerMaintainCriteria.Criteria) criteria;
		CustomerMaintainBean beanTmp = (CustomerMaintainBean) bean;
		if (beanTmp.getId() != null) {
			criteriaTmp.andIdEqualTo(beanTmp.getId());
		}
	}
}