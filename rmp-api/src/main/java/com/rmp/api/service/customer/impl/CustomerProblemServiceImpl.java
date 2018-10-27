package com.rmp.api.service.customer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.service.impl.BaseServiceImpl;
import com.rmp.api.model.CustomerProblemBean;
import com.rmp.api.service.customer.CustomerProblemService;
import com.rmp.info.mapper.CustomerProblemMapper;
import com.rmp.info.model.CustomerProblem;
import com.rmp.info.model.CustomerProblemCriteria;

/**
 * 客户 可能问题 service impl
 * @author linw
 *
 */
@Service
public class CustomerProblemServiceImpl extends BaseServiceImpl implements CustomerProblemService {
	
	@Autowired
	private CustomerProblemMapper customerProblemMapper;

	@Override
	public Class<?> getModelClass() {
		return CustomerProblem.class;
	}

	@Override
	public Class<?> getBeanClass() {
		return CustomerProblemBean.class;
	}

	@Override
	public Class<?> getCriteriaClass() {
		return CustomerProblemCriteria.class;
	}

	@Override
	public Object getMapper() {
		return customerProblemMapper;
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
		CustomerProblemCriteria.Criteria criteriaTmp = (CustomerProblemCriteria.Criteria) criteria;
		CustomerProblemBean beanTmp = (CustomerProblemBean) bean;
		if (beanTmp.getId() != null) {
			criteriaTmp.andIdEqualTo(beanTmp.getId());
		}
	}
}