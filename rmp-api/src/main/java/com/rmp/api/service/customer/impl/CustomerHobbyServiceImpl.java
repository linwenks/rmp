package com.rmp.api.service.customer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.service.impl.BaseServiceImpl;
import com.rmp.api.model.CustomerHobbyBean;
import com.rmp.api.service.customer.CustomerHobbyService;
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
		CustomerHobbyCriteria.Criteria criteriaTmp = (CustomerHobbyCriteria.Criteria) criteria;
		CustomerHobbyBean beanTmp = (CustomerHobbyBean) bean;
		if (beanTmp.getId() != null) {
			criteriaTmp.andIdEqualTo(beanTmp.getId());
		}
	}
}