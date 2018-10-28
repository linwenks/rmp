package com.rmp.api.service.customer.impl;

import static com.rmp.api.util.MsgEnum.*;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.service.impl.BaseServiceImpl;
import com.rmp.api.model.CustomerBean;
import com.rmp.api.model.CustomerMemorialDayBean;
import com.rmp.api.service.customer.CustomerMemorialDayService;
import com.rmp.api.service.customer.CustomerService;
import com.rmp.api.util.CustomerMemorialDayUtil;
import com.rmp.api.util.constant.Constant;
import com.rmp.common.util.DateUtil;
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
	@Autowired
	private CustomerService customerService;
	
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
			case "save": save((CustomerMemorialDayBean) obj);break;
			case "update": update((CustomerMemorialDayBean) obj);break;
			case "delete": delete((CustomerMemorialDayBean) obj);break;
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
	
	private void save(CustomerMemorialDayBean customerMemorialDayBean) {
		if (customerMemorialDayBean == null) AppException.toThrow(MSG_00003);
		Long userId = customerMemorialDayBean.getUserId();
		Long customerId = customerMemorialDayBean.getCustomerId();
		
		String name = StringUtils.trim(customerMemorialDayBean.getName());
		Integer occurType = customerMemorialDayBean.getOccurType();
		Integer occurDate = customerMemorialDayBean.getOccurDate();
		Integer advanceType = customerMemorialDayBean.getAdvanceType();
		
		if (userId == null) AppException.toThrow(MSG_00003);
		if (customerId == null) AppException.toThrow(MSG_00003);
		CustomerMemorialDayUtil.checkName(name);
		if (occurType == null) AppException.toThrow(MSG_02022);
		if (occurDate == null) AppException.toThrow(MSG_02023);
		if (advanceType == null) AppException.toThrow(MSG_02024);
		
		CustomerBean customerBeanTmp = new CustomerBean();
		customerBeanTmp.setId(customerId);
		customerBeanTmp.setUserId(userId);
		customerBeanTmp = customerService.selectOne(customerBeanTmp);
		if (customerBeanTmp == null) AppException.toThrow(MSG_00003);

		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		
		CustomerMemorialDayBean customerMemorialDayBeanTmp = new CustomerMemorialDayBean();
		customerMemorialDayBeanTmp.setCustomerId(customerId);
		customerMemorialDayBeanTmp.setName(name);
		customerMemorialDayBeanTmp.setOccurType(occurType);
		customerMemorialDayBeanTmp.setOccurDate(occurDate);
		customerMemorialDayBeanTmp.setAdvanceType(advanceType);
		customerMemorialDayBeanTmp.setCreateTime(nowDateLong);
		insertSel(customerMemorialDayBeanTmp);
		
		// 修改
		customerBeanTmp.setUpdateTime(nowDateLong);
		customerService.exe(UPDATE_PK_SEl_VER, customerBeanTmp);
		
		BeanUtils.copyProperties(customerMemorialDayBeanTmp, customerMemorialDayBean);
	}
	
	private void update(CustomerMemorialDayBean customerMemorialDayBean) {
		if (customerMemorialDayBean == null) AppException.toThrow(MSG_00003);
		Long id = customerMemorialDayBean.getId();
		Long userId = customerMemorialDayBean.getUserId();
		Long customerId = customerMemorialDayBean.getCustomerId();
		
		String name = StringUtils.trim(customerMemorialDayBean.getName());
		Integer occurType = customerMemorialDayBean.getOccurType();
		Integer occurDate = customerMemorialDayBean.getOccurDate();
		Integer advanceType = customerMemorialDayBean.getAdvanceType();
		
		if (id == null) AppException.toThrow(MSG_00003);
		if (userId == null) AppException.toThrow(MSG_00003);
		if (customerId == null) AppException.toThrow(MSG_00003);
		CustomerMemorialDayUtil.checkName(name);
		if (occurType == null) AppException.toThrow(MSG_02022);
		if (occurDate == null) AppException.toThrow(MSG_02023);
		if (advanceType == null) AppException.toThrow(MSG_02024);
		
		CustomerBean customerBeanTmp = new CustomerBean();
		customerBeanTmp.setId(customerId);
		customerBeanTmp.setUserId(userId);
		customerBeanTmp = customerService.selectOne(customerBeanTmp);
		if (customerBeanTmp == null) AppException.toThrow(MSG_00003);

		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		
		CustomerMemorialDayBean customerMemorialDayBeanTmp = new CustomerMemorialDayBean();
		customerMemorialDayBeanTmp.setId(id);
		customerMemorialDayBeanTmp.setCustomerId(customerId);
		customerMemorialDayBeanTmp = selectOne(customerMemorialDayBeanTmp);
		if (customerMemorialDayBeanTmp == null) AppException.toThrow(MSG_00003);
		customerMemorialDayBeanTmp.setName(name);
		customerMemorialDayBeanTmp.setOccurType(occurType);
		customerMemorialDayBeanTmp.setOccurDate(occurDate);
		customerMemorialDayBeanTmp.setAdvanceType(advanceType);
		customerMemorialDayBeanTmp.setUpdateTime(nowDateLong);
		updatePkVer(customerMemorialDayBeanTmp);
		
		// 修改
		customerBeanTmp.setUpdateTime(nowDateLong);
		customerService.exe(UPDATE_PK_SEl_VER, customerBeanTmp);
		
		BeanUtils.copyProperties(customerMemorialDayBeanTmp, customerMemorialDayBean);
	}
	
	private void delete(CustomerMemorialDayBean customerMemorialDayBean) {
		if (customerMemorialDayBean == null) AppException.toThrow(MSG_00003);
		Long id = customerMemorialDayBean.getId();
		Long userId = customerMemorialDayBean.getUserId();
		Long customerId = customerMemorialDayBean.getCustomerId();
		
		if (id == null) AppException.toThrow(MSG_00003);
		if (userId == null) AppException.toThrow(MSG_00003);
		if (customerId == null) AppException.toThrow(MSG_00003);
		
		CustomerBean customerBeanTmp = new CustomerBean();
		customerBeanTmp.setId(customerId);
		customerBeanTmp.setUserId(userId);
		customerBeanTmp = customerService.selectOne(customerBeanTmp);
		if (customerBeanTmp == null) AppException.toThrow(MSG_00003);

		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		
		CustomerMemorialDayBean customerMemorialDayBeanTmp = new CustomerMemorialDayBean();
		customerMemorialDayBeanTmp.setId(id);
		customerMemorialDayBeanTmp.setCustomerId(customerId);
		customerMemorialDayBeanTmp = selectOne(customerMemorialDayBeanTmp);
		if (customerMemorialDayBeanTmp == null) AppException.toThrow(MSG_00003);
		customerMemorialDayBeanTmp.setIsDelete(Constant.DELETE_Y);
		customerMemorialDayBeanTmp.setUpdateTime(nowDateLong);
		updatePkSelVer(customerMemorialDayBeanTmp);
		
		// 修改
		customerBeanTmp.setUpdateTime(nowDateLong);
		customerService.exe(UPDATE_PK_SEl_VER, customerBeanTmp);
	}
}