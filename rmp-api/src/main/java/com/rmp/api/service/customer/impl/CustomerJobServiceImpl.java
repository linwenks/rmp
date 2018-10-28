package com.rmp.api.service.customer.impl;

import static com.rmp.api.util.MsgEnum.*;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.service.impl.BaseServiceImpl;
import com.rmp.api.model.CustomerBean;
import com.rmp.api.model.CustomerJobBean;
import com.rmp.api.service.customer.CustomerJobService;
import com.rmp.api.service.customer.CustomerService;
import com.rmp.api.util.constant.Constant;
import com.rmp.common.util.DateUtil;
import com.rmp.common.util.PatternUtil;
import com.rmp.info.mapper.CustomerJobMapper;
import com.rmp.info.model.CustomerJob;
import com.rmp.info.model.CustomerJobCriteria;

/**
 * 客户 工作 service impl
 * @author linw
 *
 */
@Service
public class CustomerJobServiceImpl extends BaseServiceImpl implements CustomerJobService {
	
	@Autowired
	private CustomerJobMapper customerJobMapper;
	@Autowired
	private CustomerService customerService;

	@Override
	public Class<?> getModelClass() {
		return CustomerJob.class;
	}

	@Override
	public Class<?> getBeanClass() {
		return CustomerJobBean.class;
	}

	@Override
	public Class<?> getCriteriaClass() {
		return CustomerJobCriteria.class;
	}

	@Override
	public Object getMapper() {
		return customerJobMapper;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object exe(String cmd, Object obj) {
		try {
			switch (cmd) {
			case "update": update((Map<String, Object>) obj);break;
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
		CustomerJobCriteria.Criteria criteriaTmp = (CustomerJobCriteria.Criteria) criteria;
		CustomerJobBean beanTmp = (CustomerJobBean) bean;
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
	
	private void update(Map<String, Object> map) {
		if (CollectionUtils.isEmpty(map)) AppException.toThrow(MSG_00003);
		CustomerBean customerBean = (CustomerBean) map.get("customerBean");
		CustomerJobBean customerJobBean = (CustomerJobBean) map.get("customerJobBean");
		if (customerBean == null) AppException.toThrow(MSG_00003);
		if (customerJobBean == null) AppException.toThrow(MSG_00003);
		
		Long userId = customerJobBean.getUserId();
		Long customerId = customerJobBean.getCustomerId();
		
		Integer industry = customerJobBean.getIndustry();
		String companyName = customerJobBean.getCompanyName();
		String departmentName = customerJobBean.getDepartmentName();
		Integer position = customerJobBean.getPosition();
		Long phone = customerJobBean.getPhone();
		Long areaId = customerJobBean.getAreaId();
		String address = customerJobBean.getAddress();
		
		if (userId == null) AppException.toThrow(MSG_00003);
		int companyNameMaxLength = 100;
		if (!StringUtils.isEmpty(companyName) && companyName.length() > companyNameMaxLength) AppException.toThrow(MSG_02010, String.valueOf(companyName));
		int departmentNameMaxLength = 100;
		if (!StringUtils.isEmpty(departmentName) && departmentName.length() > departmentNameMaxLength) AppException.toThrow(MSG_02011, String.valueOf(departmentName));
		if (phone != null && !PatternUtil.matchesMobilePhone(phone.toString())) AppException.toThrow(MSG_02013);
		int addressMaxLength = 100;
		if (!StringUtils.isEmpty(address) && address.length() > addressMaxLength) AppException.toThrow(MSG_02012, String.valueOf(address));
		
		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		
		if (customerId != null) {
			CustomerBean customerBeanTmp = new CustomerBean();
			customerBeanTmp.setId(customerId);
			customerBeanTmp.setUserId(userId);
			customerBeanTmp = customerService.selectOne(customerBeanTmp);
			if (customerBeanTmp == null) AppException.toThrow(MSG_00003);
			
			CustomerJobBean customerJobBeanTmp = new CustomerJobBean();
			customerJobBeanTmp.setCustomerId(customerId);
			customerJobBeanTmp = selectOne(customerJobBeanTmp);
			if (customerJobBeanTmp != null) {
				customerJobBeanTmp.setIndustry(industry);
				customerJobBeanTmp.setCompanyName(companyName);
				customerJobBeanTmp.setDepartmentName(departmentName);
				customerJobBeanTmp.setPosition(position);
				customerJobBeanTmp.setPhone(phone);
				customerJobBeanTmp.setAreaId(areaId);
				customerJobBeanTmp.setAddress(address);
				customerJobBeanTmp.setUpdateTime(nowDateLong);
				updatePkVer(customerJobBeanTmp);
			} else {
				customerJobBeanTmp = new CustomerJobBean();
				customerJobBeanTmp.setCustomerId(customerId);
				customerJobBeanTmp.setIndustry(industry);
				customerJobBeanTmp.setCompanyName(companyName);
				customerJobBeanTmp.setDepartmentName(departmentName);
				customerJobBeanTmp.setPosition(position);
				customerJobBeanTmp.setPhone(phone);
				customerJobBeanTmp.setAreaId(areaId);
				customerJobBeanTmp.setAddress(address);
				customerJobBeanTmp.setCreateTime(nowDateLong);
				insertSel(customerJobBeanTmp);
			}
			
			// 修改
			customerBean.setId(customerId);
			customerService.exe("updateAll", customerBean);
			
		} else {
			// 新增
			customerService.exe("saveAll", customerBean);
			customerId = customerBean.getId();
			
			CustomerJobBean customerJobBeanTmp = new CustomerJobBean();
			customerJobBeanTmp.setCustomerId(customerId);
			customerJobBeanTmp.setIndustry(industry);
			customerJobBeanTmp.setCompanyName(companyName);
			customerJobBeanTmp.setDepartmentName(departmentName);
			customerJobBeanTmp.setPosition(position);
			customerJobBeanTmp.setPhone(phone);
			customerJobBeanTmp.setAreaId(areaId);
			customerJobBeanTmp.setAddress(address);
			customerJobBeanTmp.setCreateTime(nowDateLong);
			insertSel(customerJobBeanTmp);
		}
	}
}