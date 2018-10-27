package com.rmp.api.service.customer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.service.impl.BaseServiceImpl;
import com.rmp.api.model.CustomerMemorialDayBean;
import com.rmp.api.service.customer.CustomerMemorialDayService;
import com.rmp.info.mapper.CustomerMemorialDayMapper;
import com.rmp.info.model.CustomerMemorialDay;
import com.rmp.info.model.CustomerMemorialDayCriteria;

/**
 * 客户 纪念日 service impl
 * @author linw
 *
 */
@Service
public class CustomerMemorialDayServiceImpl extends BaseServiceImpl implements CustomerMemorialDayService {
	
	@Autowired
	private CustomerMemorialDayMapper customerMemorialDayMapper;

	@Override
	public Class<?> getModelClass() {
		return CustomerMemorialDay.class;
	}

	@Override
	public Class<?> getBeanClass() {
		return CustomerMemorialDayBean.class;
	}

	@Override
	public Class<?> getCriteriaClass() {
		return CustomerMemorialDayCriteria.class;
	}

	@Override
	public Object getMapper() {
		return customerMemorialDayMapper;
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
		CustomerMemorialDayCriteria.Criteria criteriaTmp = (CustomerMemorialDayCriteria.Criteria) criteria;
		CustomerMemorialDayBean beanTmp = (CustomerMemorialDayBean) bean;
		if (beanTmp.getId() != null) {
			criteriaTmp.andIdEqualTo(beanTmp.getId());
		}
	}
}