package com.rmp.api.service.customer.impl;

import static com.rmp.api.util.MsgEnum.*;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.service.impl.BaseServiceImpl;
import com.rmp.api.model.CustomerBean;
import com.rmp.api.model.CustomerProblemBean;
import com.rmp.api.service.customer.CustomerProblemService;
import com.rmp.api.service.customer.CustomerService;
import com.rmp.api.util.constant.Constant;
import com.rmp.common.util.DateUtil;
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
	@Autowired
	private CustomerService customerService;

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
			case "update": update((CustomerProblemBean) obj);break;
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
	
	private void update(CustomerProblemBean customerProblemBean) {
		if (customerProblemBean == null) AppException.toThrow(MSG_00003);
		Long userId = customerProblemBean.getUserId();
		Long customerId = customerProblemBean.getCustomerId();
		
		String health = StringUtils.trim(customerProblemBean.getHealth());
		String life = StringUtils.trim(customerProblemBean.getLife());
		String remark = StringUtils.trim(customerProblemBean.getRemark());
		
		if (userId == null) AppException.toThrow(MSG_00003);
		if (customerId == null) AppException.toThrow(MSG_00003);
		int remarkMaxLength = 100;
		if (!StringUtils.isEmpty(remark) && remark.length() > remarkMaxLength) AppException.toThrow(MSG_02008, String.valueOf(remarkMaxLength));
		
		CustomerBean customerBeanTmp = new CustomerBean();
		customerBeanTmp.setId(customerId);
		customerBeanTmp.setUserId(userId);
		customerBeanTmp = customerService.selectOne(customerBeanTmp);
		if (customerBeanTmp == null) AppException.toThrow(MSG_00003);

		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		
		CustomerProblemBean customerProblemBeanTmp = new CustomerProblemBean();
		customerProblemBeanTmp.setCustomerId(customerId);
		customerProblemBeanTmp = selectOne(customerProblemBeanTmp);
		if (customerProblemBeanTmp != null) {
			customerProblemBeanTmp.setHealth(health);
			customerProblemBeanTmp.setLife(life);
			customerProblemBeanTmp.setRemark(remark);
			customerProblemBeanTmp.setUpdateTime(nowDateLong);
			updatePkVer(customerProblemBeanTmp);
		} else {
			customerProblemBeanTmp = new CustomerProblemBean();
			customerProblemBeanTmp.setCustomerId(customerId);
			customerProblemBeanTmp.setHealth(health);
			customerProblemBeanTmp.setLife(life);
			customerProblemBeanTmp.setRemark(remark);;
			customerProblemBeanTmp.setCreateTime(nowDateLong);
			insertSel(customerProblemBeanTmp);
		}
		
		// 修改
		customerBeanTmp.setUpdateTime(nowDateLong);
		customerService.exe(UPDATE_PK_SEl_VER, customerBeanTmp);
	}
}