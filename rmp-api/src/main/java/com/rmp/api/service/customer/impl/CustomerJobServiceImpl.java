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
public class CustomerJobServiceImpl extends BaseServiceImpl<CustomerJob, CustomerJobBean, CustomerJobCriteria> implements CustomerJobService {
	
	@Autowired
	private CustomerJobMapper customerJobMapper;
	@Autowired
	private CustomerService customerService;
	
	@Override
	public CustomerJobMapper mapper() {
		return customerJobMapper;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object exe(String cmd, Object obj) {
		try {
			switch (cmd) {
			case "update": updateCustom((Map<String, Object>) obj);break;
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
	protected void where(Object criteria, CustomerJobBean bean) {
		if (bean == null) {
			return;
		}
		CustomerJobCriteria.Criteria criteriaTmp = (CustomerJobCriteria.Criteria) criteria;
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
	
	private void updateCustom(Map<String, Object> map) {
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
		String area = StringUtils.trim(customerJobBean.getArea());
		String address = StringUtils.trim(customerJobBean.getAddress());
		
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
			CustomerBean customerBeanTmp = CustomerBean.builder().id(customerId).userId(userId).build();
			customerBeanTmp = customerService.selectOne(customerBeanTmp);
			if (customerBeanTmp == null) AppException.toThrow(MSG_00003);
			
			CustomerJobBean customerJobBeanTmp = CustomerJobBean.builder().customerId(customerId).build();
			customerJobBeanTmp = selectOne(customerJobBeanTmp);
			if (customerJobBeanTmp != null) {
				customerJobBeanTmp.setIndustry(industry);
				customerJobBeanTmp.setCompanyName(companyName);
				customerJobBeanTmp.setDepartmentName(departmentName);
				customerJobBeanTmp.setPosition(position);
				customerJobBeanTmp.setPhone(phone);
				customerJobBeanTmp.setArea(area);
				customerJobBeanTmp.setAddress(address);
				customerJobBeanTmp.setUpdateTime(nowDateLong);
				updatePkVer(customerJobBeanTmp);
			} else {
				customerJobBeanTmp = CustomerJobBean.builder()
				.customerId(customerId)
				.industry(industry)
				.companyName(companyName)
				.departmentName(departmentName)
				.position(position)
				.phone(phone)
				.area(area)
				.address(address)
				.createTime(nowDateLong)
				.build();
				insertSel(customerJobBeanTmp);
			}
			
			// 修改
			customerBean.setId(customerId);
			customerService.exe("updateAll", customerBean);
			
		} else {
			// 新增
			customerService.exe("saveAll", customerBean);
			customerId = customerBean.getId();
			
			CustomerJobBean customerJobBeanTmp = CustomerJobBean.builder()
			.customerId(customerId)
			.industry(industry)
			.companyName(companyName)
			.departmentName(departmentName)
			.position(position)
			.phone(phone)
			.area(area)
			.address(address)
			.createTime(nowDateLong)
			.build();
			insertSel(customerJobBeanTmp);
		}
	}
}