package com.rmp.api.service.customer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.service.impl.BaseServiceImpl;
import com.rmp.api.model.CustomerRelationBean;
import com.rmp.api.service.customer.CustomerRelationService;
import com.rmp.info.mapper.CustomerRelationMapper;
import com.rmp.info.model.CustomerRelation;
import com.rmp.info.model.CustomerRelationCriteria;

/**
 * 客户 关系 service impl
 * @author linw
 *
 */
@Service
public class CustomerRelationServiceImpl extends BaseServiceImpl implements CustomerRelationService {
	
	@Autowired
	private CustomerRelationMapper customerRelationMapper;

	@Override
	public Class<?> getModelClass() {
		return CustomerRelation.class;
	}

	@Override
	public Class<?> getBeanClass() {
		return CustomerRelationBean.class;
	}

	@Override
	public Class<?> getCriteriaClass() {
		return CustomerRelationCriteria.class;
	}

	@Override
	public Object getMapper() {
		return customerRelationMapper;
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
		CustomerRelationCriteria.Criteria criteriaTmp = (CustomerRelationCriteria.Criteria) criteria;
		CustomerRelationBean beanTmp = (CustomerRelationBean) bean;
		if (beanTmp.getId() != null) {
			criteriaTmp.andIdEqualTo(beanTmp.getId());
		}
	}
}