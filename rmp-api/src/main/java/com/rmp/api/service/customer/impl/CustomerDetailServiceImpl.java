package com.rmp.api.service.customer.impl;

import static com.rmp.api.util.MsgEnum.*;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.service.impl.BaseServiceImpl;
import com.rmp.api.model.CustomerBean;
import com.rmp.api.model.CustomerDetailBean;
import com.rmp.api.service.customer.CustomerDetailService;
import com.rmp.api.service.customer.CustomerService;
import com.rmp.api.util.constant.Constant;
import com.rmp.common.util.DateUtil;
import com.rmp.info.mapper.CustomerDetailMapper;
import com.rmp.info.model.CustomerDetail;
import com.rmp.info.model.CustomerDetailCriteria;

/**
 * 客户 明细 service impl
 * @author linw
 *
 */
@Service
public class CustomerDetailServiceImpl extends BaseServiceImpl<CustomerDetail, CustomerDetailBean, CustomerDetailCriteria> implements CustomerDetailService {
	
	@Autowired
	private CustomerDetailMapper customerDetailMapper;
	@Autowired
	private CustomerService customerService;
	
	@Override
	public CustomerDetailMapper mapper() {
		return customerDetailMapper;
	}
	
	@Override
	public Object exe(String cmd, Object obj) {
		try {
			switch (cmd) {
			case "update": update((CustomerDetailBean) obj);break;
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
	protected void where(Object criteria, CustomerDetailBean bean) {
		if (bean == null) {
			return;
		}
		CustomerDetailCriteria.Criteria criteriaTmp = (CustomerDetailCriteria.Criteria) criteria;
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
	
	private void update(CustomerDetailBean customerDetailBean) {
		if (customerDetailBean == null) AppException.toThrow(MSG_00003);
		Long userId = customerDetailBean.getUserId();
		Long customerId = customerDetailBean.getCustomerId();
		
		String remark = StringUtils.trim(customerDetailBean.getRemark());
		
		if (userId == null) AppException.toThrow(MSG_00003);
		if (customerId == null) AppException.toThrow(MSG_00003);
		int remarkMaxLength = 150;
		if (!StringUtils.isEmpty(remark) && remark.length() > remarkMaxLength) AppException.toThrow(MSG_02030, String.valueOf(remarkMaxLength));
		
		CustomerBean customerBeanTmp = CustomerBean.builder().id(customerId).userId(userId).build();
		customerBeanTmp = customerService.selectOne(customerBeanTmp);
		if (customerBeanTmp == null) AppException.toThrow(MSG_00003);

		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		
		CustomerDetailBean customerDetailBeanTmp = CustomerDetailBean.builder().customerId(customerId).build();
		customerDetailBeanTmp = selectOne(customerDetailBeanTmp);
		if (customerDetailBeanTmp != null) {
			customerDetailBeanTmp.setRemark(remark);
			customerDetailBeanTmp.setUpdateTime(nowDateLong);
			updatePkVer(customerDetailBeanTmp);
		} else {
			customerDetailBeanTmp = CustomerDetailBean.builder()
			.customerId(customerId)
			.remark(remark)
			.createTime(nowDateLong)
			.build();
			insertSel(customerDetailBeanTmp);
		}
		
		// 修改
		customerBeanTmp.setUpdateTime(nowDateLong);
		customerService.exe(UPDATE_PK_SEl_VER, customerBeanTmp);
	}
}