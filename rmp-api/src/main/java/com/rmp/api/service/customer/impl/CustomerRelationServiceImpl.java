package com.rmp.api.service.customer.impl;

import static com.rmp.api.util.MsgEnum.*;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.service.impl.BaseServiceImpl;
import com.rmp.api.model.CustomerBean;
import com.rmp.api.model.CustomerRelationBean;
import com.rmp.api.service.customer.CustomerRelationService;
import com.rmp.api.service.customer.CustomerService;
import com.rmp.api.util.constant.Constant;
import com.rmp.common.util.DateUtil;
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
	@Autowired
	private CustomerService customerService;

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
			case "update": update((CustomerRelationBean) obj);break;
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
	
	private void update(CustomerRelationBean customerRelationBean) {
		if (customerRelationBean == null) AppException.toThrow(MSG_00003);
		Long userId = customerRelationBean.getUserId();
		Long customerId = customerRelationBean.getCustomerId();
		
		Integer relationship = customerRelationBean.getRelationship();
		Integer intimacy = customerRelationBean.getIntimacy();
		Integer importance = customerRelationBean.getImportance();
		
		if (userId == null) AppException.toThrow(MSG_00003);
		if (customerId == null) AppException.toThrow(MSG_00003);
		if (relationship == null) AppException.toThrow(MSG_02005);
		if (intimacy == null) AppException.toThrow(MSG_02006);
		if (importance == null) AppException.toThrow(MSG_02007);
		
		CustomerBean customerBeanTmp = new CustomerBean();
		customerBeanTmp.setId(customerId);
		customerBeanTmp.setUserId(userId);
		customerBeanTmp = customerService.selectOne(customerBeanTmp);
		if (customerBeanTmp == null) AppException.toThrow(MSG_00003);

		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		
		CustomerRelationBean customerRelationBeanTmp = new CustomerRelationBean();
		customerRelationBeanTmp.setCustomerId(customerId);
		customerRelationBeanTmp = selectOne(customerRelationBeanTmp);
		if (customerRelationBeanTmp != null) {
			customerRelationBeanTmp.setRelationship(relationship);
			customerRelationBeanTmp.setIntimacy(intimacy);
			customerRelationBeanTmp.setImportance(importance);
			customerRelationBeanTmp.setUpdateTime(nowDateLong);
			updatePkSelVer(customerRelationBeanTmp);
		} else {
			customerRelationBeanTmp = new CustomerRelationBean();
			customerRelationBeanTmp.setCustomerId(customerId);
			customerRelationBeanTmp.setRelationship(relationship);
			customerRelationBeanTmp.setIntimacy(intimacy);
			customerRelationBeanTmp.setImportance(importance);
			customerRelationBeanTmp.setCreateTime(nowDateLong);
			insertSel(customerRelationBeanTmp);
		}
		
		// 修改
		customerBeanTmp.setUpdateTime(nowDateLong);
		customerService.exe(UPDATE_PK_SEl_VER, customerBeanTmp);
	}
}