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
import com.rmp.api.model.CustomerFamilyBean;
import com.rmp.api.service.customer.CustomerFamilyService;
import com.rmp.api.service.customer.CustomerService;
import com.rmp.api.util.CustomerFamilyUtil;
import com.rmp.api.util.constant.Constant;
import com.rmp.common.util.DateUtil;
import com.rmp.info.mapper.CustomerFamilyMapper;
import com.rmp.info.model.CustomerFamily;
import com.rmp.info.model.CustomerFamilyCriteria;

/**
 * 客户 家庭 service impl
 * @author linw
 *
 */
@Service
public class CustomerFamilyServiceImpl extends BaseServiceImpl<CustomerFamily, CustomerFamilyBean, CustomerFamilyCriteria> implements CustomerFamilyService {
	
	@Autowired
	private CustomerFamilyMapper customerFamilyMapper;
	@Autowired
	private CustomerService customerService;
	
	@Override
	public CustomerFamilyMapper mapper() {
		return customerFamilyMapper;
	}
	
	@Override
	public Object exe(String cmd, Object obj) {
		try {
			switch (cmd) {
			case "save": save((CustomerFamilyBean) obj);break;
			case "update": update((CustomerFamilyBean) obj);break;
			case "delete": deleteCustom((CustomerFamilyBean) obj);break;
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
	protected void where(Object criteria, CustomerFamilyBean bean) {
		if (bean == null) {
			return;
		}
		CustomerFamilyCriteria.Criteria criteriaTmp = (CustomerFamilyCriteria.Criteria) criteria;
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
	
	private void save(CustomerFamilyBean customerFamilyBean) {
		if (customerFamilyBean == null) AppException.toThrow(MSG_00003);
		Long userId = customerFamilyBean.getUserId();
		Long customerId = customerFamilyBean.getCustomerId();
		
		Integer relationship = customerFamilyBean.getRelationship();
		String realName = StringUtils.trim(customerFamilyBean.getRealName());
		
		Long phone = customerFamilyBean.getPhone();
		Integer birthday = customerFamilyBean.getBirthday();
		Long areaId = customerFamilyBean.getAreaId();
		String address = StringUtils.trim(customerFamilyBean.getAddress());
		Integer age = customerFamilyBean.getAge();
		
		if (userId == null) AppException.toThrow(MSG_00003);
		if (customerId == null) AppException.toThrow(MSG_00003);
		if (relationship == null) AppException.toThrow(MSG_02018);
		CustomerFamilyUtil.checkRealName(realName);
		CustomerFamilyUtil.checkPhone(phone);
		CustomerFamilyUtil.checkAddress(address);
		CustomerFamilyUtil.checkAge(age);
		
		CustomerBean customerBeanTmp = new CustomerBean();
		customerBeanTmp.setId(customerId);
		customerBeanTmp.setUserId(userId);
		customerBeanTmp = customerService.selectOne(customerBeanTmp);
		if (customerBeanTmp == null) AppException.toThrow(MSG_00003);

		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		
		if (age != null) {
			birthday = CustomerFamilyUtil.getYear(age);
		}
		
		CustomerFamilyBean customerFamilyBeanTmp = CustomerFamilyBean.builder()
		.customerId(customerId)
		.realName(realName)
		.phone(phone)
		.relationship(relationship)
		.birthday(birthday)
		.areaId(areaId)
		.address(address)
		.createTime(nowDateLong)
		.build();
		insertSel(customerFamilyBeanTmp);
		
		// 修改
		customerBeanTmp.setUpdateTime(nowDateLong);
		customerService.exe(UPDATE_PK_SEl_VER, customerBeanTmp);
		
		BeanUtils.copyProperties(customerFamilyBeanTmp, customerFamilyBean);
	}
	
	private void update(CustomerFamilyBean customerFamilyBean) {
		if (customerFamilyBean == null) AppException.toThrow(MSG_00003);
		Long id = customerFamilyBean.getId();
		Long userId = customerFamilyBean.getUserId();
		Long customerId = customerFamilyBean.getCustomerId();
		
		Integer relationship = customerFamilyBean.getRelationship();
		String realName = StringUtils.trim(customerFamilyBean.getRealName());
		
		Long phone = customerFamilyBean.getPhone();
		Integer birthday = customerFamilyBean.getBirthday();
		Long areaId = customerFamilyBean.getAreaId();
		String address = StringUtils.trim(customerFamilyBean.getAddress());
		Integer age = customerFamilyBean.getAge();
		
		if (id == null) AppException.toThrow(MSG_00003);
		if (userId == null) AppException.toThrow(MSG_00003);
		if (customerId == null) AppException.toThrow(MSG_00003);
		if (relationship == null) AppException.toThrow(MSG_02018);
		CustomerFamilyUtil.checkRealName(realName);
		CustomerFamilyUtil.checkPhone(phone);
		CustomerFamilyUtil.checkAddress(address);
		CustomerFamilyUtil.checkAge(age);
		
		CustomerBean customerBeanTmp = CustomerBean.builder().id(customerId).userId(userId).build();
		customerBeanTmp = customerService.selectOne(customerBeanTmp);
		if (customerBeanTmp == null) AppException.toThrow(MSG_00003);

		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		
		if (age != null) {
			birthday = CustomerFamilyUtil.getYear(age);
		}
		
		CustomerFamilyBean customerFamilyBeanTmp = CustomerFamilyBean.builder().id(id).customerId(customerId).build();
		customerFamilyBeanTmp = selectOne(customerFamilyBeanTmp);
		if (customerFamilyBeanTmp == null) AppException.toThrow(MSG_00003);
		customerFamilyBeanTmp.setRealName(realName);
		customerFamilyBeanTmp.setPhone(phone);
		customerFamilyBeanTmp.setRelationship(relationship);
		customerFamilyBeanTmp.setBirthday(birthday);
		customerFamilyBeanTmp.setAreaId(areaId);
		customerFamilyBeanTmp.setAddress(address);
		customerFamilyBeanTmp.setUpdateTime(nowDateLong);
		updatePkVer(customerFamilyBeanTmp);
		
		// 修改
		customerBeanTmp.setUpdateTime(nowDateLong);
		customerService.exe(UPDATE_PK_SEl_VER, customerBeanTmp);
		
		BeanUtils.copyProperties(customerFamilyBeanTmp, customerFamilyBean);
	}
	
	private void deleteCustom(CustomerFamilyBean customerFamilyBean) {
		if (customerFamilyBean == null) AppException.toThrow(MSG_00003);
		Long id = customerFamilyBean.getId();
		Long userId = customerFamilyBean.getUserId();
		Long customerId = customerFamilyBean.getCustomerId();
		
		if (id == null) AppException.toThrow(MSG_00003);
		if (userId == null) AppException.toThrow(MSG_00003);
		if (customerId == null) AppException.toThrow(MSG_00003);
		
		CustomerBean customerBeanTmp = CustomerBean.builder().id(customerId).userId(userId).build();
		customerBeanTmp = customerService.selectOne(customerBeanTmp);
		if (customerBeanTmp == null) AppException.toThrow(MSG_00003);

		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		
		CustomerFamilyBean customerFamilyBeanTmp = CustomerFamilyBean.builder().id(id).customerId(customerId).build();
		customerFamilyBeanTmp = selectOne(customerFamilyBeanTmp);
		if (customerFamilyBeanTmp == null) AppException.toThrow(MSG_00003);
		customerFamilyBeanTmp.setIsDelete(Constant.DELETE_Y);
		customerFamilyBeanTmp.setUpdateTime(nowDateLong);
		updatePkSelVer(customerFamilyBeanTmp);
		
		// 修改
		customerBeanTmp.setUpdateTime(nowDateLong);
		customerService.exe(UPDATE_PK_SEl_VER, customerBeanTmp);
	}
}