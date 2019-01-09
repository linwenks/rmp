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
import com.rmp.api.model.UserRemindBean;
import com.rmp.api.service.customer.CustomerFamilyService;
import com.rmp.api.service.customer.CustomerService;
import com.rmp.api.service.user.UserRemindService;
import com.rmp.api.util.CustomerFamilyUtil;
import com.rmp.api.util.UserRemindUtil;
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
	@Autowired
	private UserRemindService userRemindService;
	
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
		String area = StringUtils.trim(customerFamilyBean.getArea());
		String address = StringUtils.trim(customerFamilyBean.getAddress());
		Integer age = customerFamilyBean.getAge();
		
		if (userId == null) AppException.toThrow(MSG_00003);
		if (customerId == null) AppException.toThrow(MSG_00003);
		if (relationship == null) AppException.toThrow(MSG_02018);
		CustomerFamilyUtil.checkRealName(realName);
		CustomerFamilyUtil.checkPhone(phone);
		CustomerFamilyUtil.checkAddress(address);
		CustomerFamilyUtil.checkAge(age);
		CustomerFamilyUtil.checkBirthday(birthday);
		
		CustomerBean customerBeanTmp = new CustomerBean();
		customerBeanTmp.setId(customerId);
		customerBeanTmp.setUserId(userId);
		customerBeanTmp = customerService.selectOne(customerBeanTmp);
		if (customerBeanTmp == null) AppException.toThrow(MSG_00003);

		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		/*
		if (age != null) {
			birthday = CustomerFamilyUtil.getYear(age);
		}
		*/
		CustomerFamilyBean customerFamilyBeanTmp = CustomerFamilyBean.builder()
		.customerId(customerId)
		.realName(realName)
		.phone(phone)
		.relationship(relationship)
		.birthday(birthday)
		.area(area)
		.address(address)
		.createTime(nowDateLong)
		.build();
		insertSel(customerFamilyBeanTmp);
		
		// 添加 提醒
		int maxDay = UserRemindUtil.MAX_DAY;
		for (int i=0; i<=maxDay; i++) {
			Date nowDateTmp = DateUtil.changeDays(nowDate, i);
			int ymdTmp = Integer.parseInt(DateUtil.formatDate(nowDateTmp, DateUtil.yyyyMMdd));
			
			for (int j=0; i+j<=maxDay; j++) {
				userRemindService.exe("insertBy2", UserRemindBean.builder().userId(userId).typeId(customerFamilyBeanTmp.getId()).advanceDate(ymdTmp).advanceDay(j).build());
			}
		}
		
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
		String area = StringUtils.trim(customerFamilyBean.getArea());
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
		CustomerFamilyUtil.checkBirthday(birthday);
		
		CustomerBean customerBeanTmp = CustomerBean.builder().id(customerId).userId(userId).build();
		customerBeanTmp = customerService.selectOne(customerBeanTmp);
		if (customerBeanTmp == null) AppException.toThrow(MSG_00003);

		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		/*
		if (age != null) {
			birthday = CustomerFamilyUtil.getYear(age);
		}
		*/
		CustomerFamilyBean customerFamilyBeanTmp = CustomerFamilyBean.builder().id(id).customerId(customerId).build();
		customerFamilyBeanTmp = selectOne(customerFamilyBeanTmp);
		if (customerFamilyBeanTmp == null) AppException.toThrow(MSG_00003);
		customerFamilyBeanTmp.setRealName(realName);
		customerFamilyBeanTmp.setPhone(phone);
		customerFamilyBeanTmp.setRelationship(relationship);
		customerFamilyBeanTmp.setBirthday(birthday);
		customerFamilyBeanTmp.setArea(area);
		customerFamilyBeanTmp.setAddress(address);
		customerFamilyBeanTmp.setUpdateTime(nowDateLong);
		updatePkVer(customerFamilyBeanTmp);
		
		// 删除 提醒
		userRemindService.exe(DELETE, UserRemindBean.builder().userId(userId).type(2).typeId(customerFamilyBeanTmp.getId()).build());
		
		// 添加 提醒
		int maxDay = UserRemindUtil.MAX_DAY;
		for (int i=0; i<=maxDay; i++) {
			Date nowDateTmp = DateUtil.changeDays(nowDate, i);
			int ymdTmp = Integer.parseInt(DateUtil.formatDate(nowDateTmp, DateUtil.yyyyMMdd));
			
			for (int j=0; i+j<=maxDay; j++) {
				userRemindService.exe("insertBy2", UserRemindBean.builder().userId(userId).typeId(customerFamilyBeanTmp.getId()).advanceDate(ymdTmp).advanceDay(j).build());
			}
		}
		
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
		
		// 删除 提醒
		userRemindService.exe(DELETE, UserRemindBean.builder().userId(userId).type(2).typeId(customerFamilyBeanTmp.getId()).build());
		
		// 修改
		customerBeanTmp.setUpdateTime(nowDateLong);
		customerService.exe(UPDATE_PK_SEl_VER, customerBeanTmp);
	}
}