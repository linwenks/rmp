package com.rmp.api.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.service.impl.BaseServiceImpl;
import com.rmp.api.model.UserCustomerBean;
import com.rmp.api.service.user.UserCustomerService;
import com.rmp.api.util.constant.Constant;
import com.rmp.info.mapper.UserCustomerMapper;
import com.rmp.info.model.UserCustomer;
import com.rmp.info.model.UserCustomerCriteria;

/**
 * 用户 客户 关联 service impl
 * @author linw
 *
 */
@Service
public class UserCustomerServiceImpl extends BaseServiceImpl implements UserCustomerService {
	
	@Autowired
	private UserCustomerMapper userCustomerMapper;
	
	@Override
	public Class<?> getModelClass() {
		return UserCustomer.class;
	}

	@Override
	public Class<?> getBeanClass() {
		return UserCustomerBean.class;
	}

	@Override
	public Class<?> getCriteriaClass() {
		return UserCustomerCriteria.class;
	}

	@Override
	public Object getMapper() {
		return userCustomerMapper;
	}
	
	@Override
	public Object exe(String cmd, Object obj) {
		try {
			switch (cmd) {
			case "": break;
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
	protected void where(Object criteria, Object bean) {
		if (bean == null) {
			return;
		}
		UserCustomerCriteria.Criteria criteriaTmp = (UserCustomerCriteria.Criteria) criteria;
		UserCustomerBean beanTmp = (UserCustomerBean) bean;
		criteriaTmp.andIsDeleteEqualTo(Constant.DELETE_N);
		if (beanTmp.getId() != null) {
			criteriaTmp.andIdEqualTo(beanTmp.getId());
		}
		if (beanTmp.getUserId() != null) {
			criteriaTmp.andUserIdEqualTo(beanTmp.getUserId());
		}
		if (beanTmp.getCustomerId() != null) {
			criteriaTmp.andCustomerIdEqualTo(beanTmp.getCustomerId());
		}
		if (beanTmp.getIsDelete() != null) {
			criteriaTmp.andIsDeleteEqualTo(beanTmp.getIsDelete());
		}
	}
}