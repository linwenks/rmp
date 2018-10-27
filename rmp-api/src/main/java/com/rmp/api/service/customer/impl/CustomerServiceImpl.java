package com.rmp.api.service.customer.impl;

import static com.rmp.api.util.MsgEnum.*;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.service.impl.BaseServiceImpl;
import com.rmp.api.model.CustomerBean;
import com.rmp.api.service.customer.CustomerService;
import com.rmp.api.util.CustomerUtil;
import com.rmp.api.util.constant.Constant;
import com.rmp.common.util.DateUtil;
import com.rmp.common.util.PinYinUtil;
import com.rmp.info.mapper.CustomerMapper;
import com.rmp.info.model.Customer;
import com.rmp.info.model.CustomerCriteria;

/**
 * 客户 service impl
 * @author linw
 *
 */
@Service
public class CustomerServiceImpl extends BaseServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public Class<?> getModelClass() {
		return Customer.class;
	}

	@Override
	public Class<?> getBeanClass() {
		return CustomerBean.class;
	}

	@Override
	public Class<?> getCriteriaClass() {
		return CustomerCriteria.class;
	}

	@Override
	public Object getMapper() {
		return customerMapper;
	}
	
	@Override
	public Object exe(String cmd, Object obj) {
		try {
			switch (cmd) {
			case "save": save((CustomerBean) obj);break;
			case "update": update((CustomerBean) obj);break;
			case "delete": delete((CustomerBean) obj);break;
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
		CustomerCriteria.Criteria criteriaTmp = (CustomerCriteria.Criteria) criteria;
		CustomerBean beanTmp = (CustomerBean) bean;
		criteriaTmp.andIsDeleteEqualTo(Constant.DELETE_N);
		if (beanTmp.getId() != null) {
			criteriaTmp.andIdEqualTo(beanTmp.getId());
		}
		if (beanTmp.getIdNot() != null) {
			criteriaTmp.andIdNotEqualTo(beanTmp.getIdNot());
		}
		if (beanTmp.getUserId() != null) {
			criteriaTmp.andUserIdEqualTo(beanTmp.getUserId());
		}
		if (beanTmp.getIsDelete() != null) {
			criteriaTmp.andIsDeleteEqualTo(beanTmp.getIsDelete());
		}
		if (beanTmp.getPhone() != null) {
			criteriaTmp.andPhoneEqualTo(beanTmp.getPhone());
		}
	}
	
	private void save(CustomerBean customerBean) {
		if (customerBean == null) AppException.toThrow(MSG_00003);
		Long userId = customerBean.getUserId();
		String realName = StringUtils.trim(customerBean.getRealName());
		Long phone = customerBean.getPhone();
		
		if (userId == null) AppException.toThrow(MSG_00003);
		CustomerUtil.checkRealName(realName);
		CustomerUtil.checkPhone(phone);
		
		CustomerBean customerBeanTmp = new CustomerBean();
		customerBeanTmp.setUserId(userId);
		customerBeanTmp.setPhone(phone);
		List<CustomerBean> customerBeanTmpList = selectList(null, customerBeanTmp);
		if (!CollectionUtils.isEmpty(customerBeanTmpList)) AppException.toThrow(MSG_02004);
		
		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		
		// 添加
		customerBeanTmp = new CustomerBean();
		customerBeanTmp.setUserId(userId);
		customerBeanTmp.setRealName(realName);
		customerBeanTmp.setPinyin(PinYinUtil.toPinYinLast(realName));
		customerBeanTmp.setPhone(phone);
		customerBeanTmp.setHeadPic(Constant.Customer.HEAD_PIC);
		customerBeanTmp.setCreateTime(nowDateLong);
		insertSel(customerBeanTmp);
	}
	
	private void update(CustomerBean customerBean) {
		if (customerBean == null) AppException.toThrow(MSG_00003);
		Long id = customerBean.getId();
		Long userId = customerBean.getUserId();
		String realName = StringUtils.trim(customerBean.getRealName());
		Long phone = customerBean.getPhone();
		
		if (id == null) AppException.toThrow(MSG_00003);
		if (userId == null) AppException.toThrow(MSG_00003);
		CustomerUtil.checkRealName(realName);
		CustomerUtil.checkPhone(phone);
		
		CustomerBean customerBeanTmp = new CustomerBean();
		customerBeanTmp.setId(id);
		customerBeanTmp = selectOne(customerBeanTmp);
		if (customerBeanTmp == null) AppException.toThrow(MSG_00003);
		
		CustomerBean customerBeanTmp2 = new CustomerBean();
		customerBeanTmp2.setIdNot(id);
		customerBeanTmp2.setUserId(userId);
		customerBeanTmp2.setPhone(phone);
		List<CustomerBean> customerBeanTmp2List = selectList(null, customerBeanTmp2);
		if (!CollectionUtils.isEmpty(customerBeanTmp2List)) AppException.toThrow(MSG_02004);
		
		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		
		// 修改
		customerBeanTmp.setRealName(realName);
		customerBeanTmp.setPinyin(PinYinUtil.toPinYinLast(realName));
		customerBeanTmp.setPhone(phone);
		customerBeanTmp.setUpdateTime(nowDateLong);
		updatePkSelVer(customerBeanTmp);
	}
	
	private void delete(CustomerBean customerBean) {
		if (customerBean == null) AppException.toThrow(MSG_00003);
		Long id = customerBean.getId();
		Long userId = customerBean.getUserId();
		
		if (id == null) AppException.toThrow(MSG_00003);
		if (userId == null) AppException.toThrow(MSG_00003);
		
		CustomerBean customerBeanTmp = new CustomerBean();
		customerBeanTmp.setId(id);
		customerBeanTmp.setUserId(userId);
		customerBeanTmp = selectOne(customerBeanTmp);
		if (customerBeanTmp == null) AppException.toThrow(MSG_00003);
		
		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		
		// 修改
		customerBeanTmp.setIsDelete(Constant.DELETE_Y);
		customerBeanTmp.setUpdateTime(nowDateLong);
		updatePkSelVer(customerBeanTmp);
		
		// 删除全部
	}
}