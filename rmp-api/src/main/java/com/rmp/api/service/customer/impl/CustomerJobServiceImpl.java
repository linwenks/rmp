package com.rmp.api.service.customer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.service.impl.BaseServiceImpl;
import com.rmp.api.model.CustomerJobBean;
import com.rmp.api.service.customer.CustomerJobService;
import com.rmp.info.mapper.CustomerJobMapper;
import com.rmp.info.model.CustomerJob;
import com.rmp.info.model.CustomerJobCriteria;

/**
 * 客户 工作 service impl
 * @author linw
 *
 */
@Service
public class CustomerJobServiceImpl extends BaseServiceImpl implements CustomerJobService {
	
	@Autowired
	private CustomerJobMapper customerJobMapper;

	@Override
	public Class<?> getModelClass() {
		return CustomerJob.class;
	}

	@Override
	public Class<?> getBeanClass() {
		return CustomerJobBean.class;
	}

	@Override
	public Class<?> getCriteriaClass() {
		return CustomerJobCriteria.class;
	}

	@Override
	public Object getMapper() {
		return customerJobMapper;
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
		CustomerJobCriteria.Criteria criteriaTmp = (CustomerJobCriteria.Criteria) criteria;
		CustomerJobBean beanTmp = (CustomerJobBean) bean;
		if (beanTmp.getId() != null) {
			criteriaTmp.andIdEqualTo(beanTmp.getId());
		}
	}
}