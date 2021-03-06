package com.rmp.api.service.customer.impl;

import static com.rmp.api.util.MsgEnum.*;

import java.util.Date;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.service.impl.BaseServiceImpl;
import com.rmp.api.model.CustomerBean;
import com.rmp.api.model.CustomerProblemBean;
import com.rmp.api.model.SysCodeBean;
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
public class CustomerProblemServiceImpl extends BaseServiceImpl<CustomerProblem, CustomerProblemBean, CustomerProblemCriteria> implements CustomerProblemService {
	
	@Autowired
	private CustomerProblemMapper customerProblemMapper;
	@Autowired
	private CustomerService customerService;
	
	@Override
	public CustomerProblemMapper mapper() {
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
			AppException.toThrow(e);
		}
		return null;
	}
	
	@Override
	protected void where(Object criteria, CustomerProblemBean bean) {
		if (bean == null) {
			return;
		}
		CustomerProblemCriteria.Criteria criteriaTmp = (CustomerProblemCriteria.Criteria) criteria;
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
	
	private void update(CustomerProblemBean customerProblemBean) {
		if (customerProblemBean == null) AppException.toThrow(MSG_00003);
		Long userId = customerProblemBean.getUserId();
		Long customerId = customerProblemBean.getCustomerId();
		/*
		String health = StringUtils.trim(customerProblemBean.getHealth());
		String life = StringUtils.trim(customerProblemBean.getLife());
		*/
		String health = null;
		String life = null;
		if (!CollectionUtils.isEmpty(customerProblemBean.getHealthCodeList())) {
			health = customerProblemBean.getHealthCodeList().stream().map(SysCodeBean::getKey).filter(StringUtils::isNotEmpty).distinct().collect(Collectors.joining(","));
		}
		if (!CollectionUtils.isEmpty(customerProblemBean.getLifeCodeList())) {
			life = customerProblemBean.getLifeCodeList().stream().map(SysCodeBean::getKey).filter(StringUtils::isNotEmpty).distinct().collect(Collectors.joining(","));
		}
		String remark = StringUtils.trim(customerProblemBean.getRemark());
		
		if (userId == null) AppException.toThrow(MSG_00003);
		if (customerId == null) AppException.toThrow(MSG_00003);
		int remarkMaxLength = 100;
		if (!StringUtils.isEmpty(remark) && remark.length() > remarkMaxLength) AppException.toThrow(MSG_02008, String.valueOf(remarkMaxLength));
		
		CustomerBean customerBeanTmp = CustomerBean.builder().id(customerId).userId(userId).build();
		customerBeanTmp = customerService.selectOne(customerBeanTmp);
		if (customerBeanTmp == null) AppException.toThrow(MSG_00003);

		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		
		CustomerProblemBean customerProblemBeanTmp = CustomerProblemBean.builder().customerId(customerId).build();
		customerProblemBeanTmp = selectOne(customerProblemBeanTmp);
		if (customerProblemBeanTmp != null) {
			customerProblemBeanTmp.setHealth(health);
			customerProblemBeanTmp.setLife(life);
			customerProblemBeanTmp.setRemark(remark);
			customerProblemBeanTmp.setUpdateTime(nowDateLong);
			updatePkVer(customerProblemBeanTmp);
		} else {
			customerProblemBeanTmp = CustomerProblemBean.builder()
			.customerId(customerId)
			.health(health)
			.life(life)
			.remark(remark)
			.createTime(nowDateLong)
			.build();
			insertSel(customerProblemBeanTmp);
		}
		
		// 修改
		customerBeanTmp.setUpdateTime(nowDateLong);
		customerService.exe(UPDATE_PK_SEl_VER, customerBeanTmp);
	}
}