package com.rmp.api.service.customer.impl;

import static com.rmp.api.util.MsgEnum.*;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.service.impl.BaseServiceImpl;
import com.rmp.api.model.CustomerBean;
import com.rmp.api.model.CustomerRelationBean;
import com.rmp.api.service.customer.CustomerRelationService;
import com.rmp.api.service.customer.CustomerService;
import com.rmp.api.util.constant.Constant;
import com.rmp.common.util.DateUtil;
import com.rmp.info.mapper.CustomerRelationMapper;
import com.rmp.info.model.CustomerRelation;
import com.rmp.info.model.CustomerRelationCriteria;

/**
 * 客户 关系 service impl
 * @author linw
 *
 */
@Service
public class CustomerRelationServiceImpl extends BaseServiceImpl<CustomerRelation, CustomerRelationBean, CustomerRelationCriteria> implements CustomerRelationService {
	
	@Autowired
	private CustomerRelationMapper customerRelationMapper;
	@Autowired
	private CustomerService customerService;
	
	@Override
	public CustomerRelationMapper mapper() {
		return customerRelationMapper;
	}
	
	@Override
	public Object exe(String cmd, Object obj) {
		try {
			switch (cmd) {
			case "update": update((CustomerRelationBean) obj);break;
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
	protected void where(Object criteria, CustomerRelationBean bean) {
		if (bean == null) {
			return;
		}
		CustomerRelationCriteria.Criteria criteriaTmp = (CustomerRelationCriteria.Criteria) criteria;
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
	
	private void update(CustomerRelationBean customerRelationBean) {
		if (customerRelationBean == null) AppException.toThrow(MSG_00003);
		Long userId = customerRelationBean.getUserId();
		Long customerId = customerRelationBean.getCustomerId();
		
		Integer relationship = customerRelationBean.getRelationship();
		Integer intimacy = customerRelationBean.getIntimacy();
		Integer importance = customerRelationBean.getImportance();
		
		if (userId == null) AppException.toThrow(MSG_00003);
		if (customerId == null) AppException.toThrow(MSG_00003);
		if (relationship == null) AppException.toThrow(MSG_02005);
		if (intimacy == null) AppException.toThrow(MSG_02006);
		if (importance == null) AppException.toThrow(MSG_02007);
		
		CustomerBean customerBeanTmp = CustomerBean.builder().id(customerId).userId(userId).build();
		customerBeanTmp = customerService.selectOne(customerBeanTmp);
		if (customerBeanTmp == null) AppException.toThrow(MSG_00003);

		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		
		CustomerRelationBean customerRelationBeanTmp = CustomerRelationBean.builder().customerId(customerId).build();
		customerRelationBeanTmp = selectOne(customerRelationBeanTmp);
		if (customerRelationBeanTmp != null) {
			customerRelationBeanTmp.setRelationship(relationship);
			customerRelationBeanTmp.setIntimacy(intimacy);
			customerRelationBeanTmp.setImportance(importance);
			customerRelationBeanTmp.setUpdateTime(nowDateLong);
			updatePkSelVer(customerRelationBeanTmp);
		} else {
			customerRelationBeanTmp = CustomerRelationBean.builder()
			.customerId(customerId)
			.relationship(relationship)
			.intimacy(intimacy)
			.importance(importance)
			.createTime(nowDateLong)
			.build();
			insertSel(customerRelationBeanTmp);
		}
		
		// 修改
		customerBeanTmp.setUpdateTime(nowDateLong);
		customerService.exe(UPDATE_PK_SEl_VER, customerBeanTmp);
	}
}