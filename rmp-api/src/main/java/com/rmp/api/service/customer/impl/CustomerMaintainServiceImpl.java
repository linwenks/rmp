package com.rmp.api.service.customer.impl;

import static com.rmp.api.util.MsgEnum.*;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.service.impl.BaseServiceImpl;
import com.rmp.api.model.CustomerBean;
import com.rmp.api.model.CustomerMaintainBean;
import com.rmp.api.service.customer.CustomerMaintainService;
import com.rmp.api.service.customer.CustomerService;
import com.rmp.api.util.constant.Constant;
import com.rmp.common.util.DateUtil;
import com.rmp.info.mapper.CustomerMaintainMapper;
import com.rmp.info.model.CustomerMaintain;
import com.rmp.info.model.CustomerMaintainCriteria;

/**
 * 客户 维护设置 service impl
 * @author linw
 *
 */
@Service
public class CustomerMaintainServiceImpl extends BaseServiceImpl<CustomerMaintain, CustomerMaintainBean, CustomerMaintainCriteria> implements CustomerMaintainService {
	
	@Autowired
	private CustomerMaintainMapper customerMaintainMapper;
	@Autowired
	private CustomerService customerService;
	
	@Override
	public CustomerMaintainMapper mapper() {
		return customerMaintainMapper;
	}
	
	@Override
	public Object exe(String cmd, Object obj) {
		try {
			switch (cmd) {
			case "update": update((CustomerMaintainBean) obj);break;
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
	protected void where(Object criteria, CustomerMaintainBean bean) {
		if (bean == null) {
			return;
		}
		CustomerMaintainCriteria.Criteria criteriaTmp = (CustomerMaintainCriteria.Criteria) criteria;
		criteriaTmp.andIsDeleteEqualTo(Constant.DELETE_N);
		if (bean.getId() != null) {
			criteriaTmp.andIdEqualTo(bean.getId());
		}
		if (bean.getCustomerId() != null) {
			criteriaTmp.andCustomerIdEqualTo(bean.getCustomerId());
		}
		if (bean.getIsDelete() != null) {
			criteriaTmp.andIsDeleteEqualTo(bean.getIsDelete());
		}
	}
	
	private void update(CustomerMaintainBean customerMaintainBean) {
		if (customerMaintainBean == null) AppException.toThrow(MSG_00003);
		Long userId = customerMaintainBean.getUserId();
		Long customerId = customerMaintainBean.getCustomerId();
		
		Integer maintain = customerMaintainBean.getMaintain();
		
		if (userId == null) AppException.toThrow(MSG_00003);
		if (customerId == null) AppException.toThrow(MSG_00003);
		if (maintain == null) AppException.toThrow(MSG_02025);
		
		CustomerBean customerBeanTmp = new CustomerBean();
		customerBeanTmp.setId(customerId);
		customerBeanTmp.setUserId(userId);
		customerBeanTmp = customerService.selectOne(customerBeanTmp);
		if (customerBeanTmp == null) AppException.toThrow(MSG_00003);

		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		
		CustomerMaintainBean customerMaintainBeanTmp = new CustomerMaintainBean();
		customerMaintainBeanTmp.setCustomerId(customerId);
		customerMaintainBeanTmp = selectOne(customerMaintainBeanTmp);
		if (customerMaintainBeanTmp != null) {
			customerMaintainBeanTmp.setMaintain(maintain);
			customerMaintainBeanTmp.setUpdateTime(nowDateLong);
			updatePkSelVer(customerMaintainBeanTmp);
		} else {
			customerMaintainBeanTmp = new CustomerMaintainBean();
			customerMaintainBeanTmp.setCustomerId(customerId);
			customerMaintainBeanTmp.setMaintain(maintain);
			customerMaintainBeanTmp.setCreateTime(nowDateLong);
			insertSel(customerMaintainBeanTmp);
		}
		
		// 修改
		customerBeanTmp.setUpdateTime(nowDateLong);
		customerService.exe(UPDATE_PK_SEl_VER, customerBeanTmp);
	}
}