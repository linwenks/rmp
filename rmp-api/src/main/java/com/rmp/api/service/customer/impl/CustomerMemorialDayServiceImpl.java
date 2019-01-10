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
import com.rmp.api.model.UserRemindBean;
import com.rmp.api.service.customer.CustomerMemorialDayService;
import com.rmp.api.service.customer.CustomerService;
import com.rmp.api.service.user.UserRemindService;
import com.rmp.api.util.CustomerMemorialDayUtil;
import com.rmp.api.util.UserRemindUtil;
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
public class CustomerMemorialDayServiceImpl extends BaseServiceImpl<CustomerMemorialDay, CustomerMemorialDayBean, CustomerMemorialDayCriteria> implements CustomerMemorialDayService {
	
	@Autowired
	private CustomerMemorialDayMapper customerMemorialDayMapper;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private UserRemindService userRemindService;
	
	@Override
	public CustomerMemorialDayMapper mapper() {
		return customerMemorialDayMapper;
	}
	
	@Override
	public Object exe(String cmd, Object obj) {
		try {
			switch (cmd) {
			case "save": save((CustomerMemorialDayBean) obj);break;
			case "update": update((CustomerMemorialDayBean) obj);break;
			case "delete": deleteCustom((CustomerMemorialDayBean) obj);break;
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
	protected void where(Object criteria, CustomerMemorialDayBean bean) {
		if (bean == null) {
			return;
		}
		CustomerMemorialDayCriteria.Criteria criteriaTmp = (CustomerMemorialDayCriteria.Criteria) criteria;
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
		CustomerMemorialDayUtil.checkOccur(occurType, occurDate);
		if (advanceType == null) AppException.toThrow(MSG_02024);
		
		CustomerBean customerBeanTmp = CustomerBean.builder().id(customerId).userId(userId).build();
		customerBeanTmp = customerService.selectOne(customerBeanTmp);
		if (customerBeanTmp == null) AppException.toThrow(MSG_00003);

		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		
		CustomerMemorialDayBean customerMemorialDayBeanTmp = CustomerMemorialDayBean.builder()
		.customerId(customerId)
		.name(name)
		.occurType(occurType)
		.occurDate(occurDate)
		.advanceType(advanceType)
		.createTime(nowDateLong)
		.build();
		insertSel(customerMemorialDayBeanTmp);
		
		// 添加 提醒
		int maxDay = UserRemindUtil.MAX_DAY;
		for (int i=0; i<=maxDay; i++) {
			Date nowDateTmp = DateUtil.changeDays(nowDate, i);
			int ymdTmp = Integer.parseInt(DateUtil.formatDate(nowDateTmp, DateUtil.yyyyMMdd));
			
			for (int j=0; i+j<=maxDay; j++) {
				String cmd = null;
				switch (occurType) {
				case 1: cmd = "insertBy1ToYmd"; break;
				case 2: cmd = "insertBy1ToMd"; break;
				case 3: cmd = "insertBy1ToD"; break;
				case 4: cmd = "insertBy1ToW"; break;
				default:
					break;
				}
				userRemindService.exe(cmd, UserRemindBean.builder().userId(userId).typeId(customerMemorialDayBeanTmp.getId()).advanceDate(ymdTmp).advanceDay(j).build());
			}
		}
		
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
		CustomerMemorialDayUtil.checkOccur(occurType, occurDate);
		if (advanceType == null) AppException.toThrow(MSG_02024);
		
		CustomerBean customerBeanTmp = CustomerBean.builder().id(customerId).userId(userId).build();
		customerBeanTmp = customerService.selectOne(customerBeanTmp);
		if (customerBeanTmp == null) AppException.toThrow(MSG_00003);

		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		
		CustomerMemorialDayBean customerMemorialDayBeanTmp = CustomerMemorialDayBean.builder().id(id).customerId(customerId).build();
		customerMemorialDayBeanTmp = selectOne(customerMemorialDayBeanTmp);
		if (customerMemorialDayBeanTmp == null) AppException.toThrow(MSG_00003);
		customerMemorialDayBeanTmp.setName(name);
		customerMemorialDayBeanTmp.setOccurType(occurType);
		customerMemorialDayBeanTmp.setOccurDate(occurDate);
		customerMemorialDayBeanTmp.setAdvanceType(advanceType);
		customerMemorialDayBeanTmp.setUpdateTime(nowDateLong);
		updatePkVer(customerMemorialDayBeanTmp);

		// 删除 提醒
		userRemindService.exe(DELETE_NOT_E, UserRemindBean.builder().userId(userId).type(1).typeId(customerMemorialDayBeanTmp.getId()).build());
		
		// 添加 提醒
		int maxDay = UserRemindUtil.MAX_DAY;
		for (int i=0; i<=maxDay; i++) {
			Date nowDateTmp = DateUtil.changeDays(nowDate, i);
			int ymdTmp = Integer.parseInt(DateUtil.formatDate(nowDateTmp, DateUtil.yyyyMMdd));
			
			for (int j=0; i+j<=maxDay; j++) {
				String cmd = null;
				switch (occurType) {
				case 1: cmd = "insertBy1ToYmd"; break;
				case 2: cmd = "insertBy1ToMd"; break;
				case 3: cmd = "insertBy1ToD"; break;
				case 4: cmd = "insertBy1ToW"; break;
				default:
					break;
				}
				userRemindService.exe(cmd, UserRemindBean.builder().userId(userId).typeId(customerMemorialDayBeanTmp.getId()).advanceDate(ymdTmp).advanceDay(j).build());
			}
		}
		
		// 修改
		customerBeanTmp.setUpdateTime(nowDateLong);
		customerService.exe(UPDATE_PK_SEl_VER, customerBeanTmp);
		
		BeanUtils.copyProperties(customerMemorialDayBeanTmp, customerMemorialDayBean);
	}
	
	private void deleteCustom(CustomerMemorialDayBean customerMemorialDayBean) {
		if (customerMemorialDayBean == null) AppException.toThrow(MSG_00003);
		Long id = customerMemorialDayBean.getId();
		Long userId = customerMemorialDayBean.getUserId();
		Long customerId = customerMemorialDayBean.getCustomerId();
		
		if (id == null) AppException.toThrow(MSG_00003);
		if (userId == null) AppException.toThrow(MSG_00003);
		if (customerId == null) AppException.toThrow(MSG_00003);
		
		CustomerBean customerBeanTmp = CustomerBean.builder().id(customerId).userId(userId).build();
		customerBeanTmp = customerService.selectOne(customerBeanTmp);
		if (customerBeanTmp == null) AppException.toThrow(MSG_00003);

		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		
		CustomerMemorialDayBean customerMemorialDayBeanTmp = CustomerMemorialDayBean.builder().id(id).customerId(customerId).build();
		customerMemorialDayBeanTmp = selectOne(customerMemorialDayBeanTmp);
		if (customerMemorialDayBeanTmp == null) AppException.toThrow(MSG_00003);
		customerMemorialDayBeanTmp.setIsDelete(Constant.DELETE_Y);
		customerMemorialDayBeanTmp.setUpdateTime(nowDateLong);
		updatePkSelVer(customerMemorialDayBeanTmp);
		
		// 删除 提醒
		userRemindService.exe(DELETE_NOT_E, UserRemindBean.builder().userId(userId).type(1).typeId(customerMemorialDayBeanTmp.getId()).build());
		
		// 修改
		customerBeanTmp.setUpdateTime(nowDateLong);
		customerService.exe(UPDATE_PK_SEl_VER, customerBeanTmp);
	}
}