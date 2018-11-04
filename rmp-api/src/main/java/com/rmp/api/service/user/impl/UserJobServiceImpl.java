package com.rmp.api.service.user.impl;

import static com.rmp.api.util.MsgEnum.*;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.service.impl.BaseServiceImpl;
import com.rmp.api.model.UserBean;
import com.rmp.api.model.UserJobBean;
import com.rmp.api.service.user.UserJobService;
import com.rmp.api.service.user.UserService;
import com.rmp.api.util.constant.Constant;
import com.rmp.common.util.DateUtil;
import com.rmp.common.util.PatternUtil;
import com.rmp.info.mapper.UserJobMapper;
import com.rmp.info.model.UserJob;
import com.rmp.info.model.UserJobCriteria;

/**
 * 用户 工作 service impl
 * @author linw
 *
 */
@Service
public class UserJobServiceImpl extends BaseServiceImpl implements UserJobService {
	
	@Autowired
	private UserJobMapper userJobMapper;
	@Autowired
	private UserService userService;

	@Override
	public Class<?> getModelClass() {
		return UserJob.class;
	}

	@Override
	public Class<?> getBeanClass() {
		return UserJobBean.class;
	}

	@Override
	public Class<?> getCriteriaClass() {
		return UserJobCriteria.class;
	}

	@Override
	public Object getMapper() {
		return userJobMapper;
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
		UserJobCriteria.Criteria criteriaTmp = (UserJobCriteria.Criteria) criteria;
		UserJobBean beanTmp = (UserJobBean) bean;
		criteriaTmp.andIsDeleteEqualTo(Constant.DELETE_N);
		if (beanTmp.getId() != null) {
			criteriaTmp.andIdEqualTo(beanTmp.getId());
		}
		if (beanTmp.getUserId() != null) {
			criteriaTmp.andUserIdEqualTo(beanTmp.getUserId());
		}
		if (beanTmp.getIsDelete() != null) {
			criteriaTmp.andIsDeleteEqualTo(beanTmp.getIsDelete());
		}
	}
	
	private void update(Map<String, Object> map) {
		if (CollectionUtils.isEmpty(map)) AppException.toThrow(MSG_00003);
		UserBean userBean = (UserBean) map.get("userBean");
		UserJobBean userJobBean = (UserJobBean) map.get("userJobBean");
		if (userBean == null) AppException.toThrow(MSG_00003);
		if (userJobBean == null) AppException.toThrow(MSG_00003);
		
		Long userId = userJobBean.getUserId();
		
		Integer industry = userJobBean.getIndustry();
		String companyName = userJobBean.getCompanyName();
		String departmentName = userJobBean.getDepartmentName();
		Integer position = userJobBean.getPosition();
		Long phone = userJobBean.getPhone();
		Long areaId = userJobBean.getAreaId();
		String address = userJobBean.getAddress();
		
		if (userId == null) AppException.toThrow(MSG_00003);
		int companyNameMaxLength = 100;
		if (!StringUtils.isEmpty(companyName) && companyName.length() > companyNameMaxLength) AppException.toThrow(MSG_01024, String.valueOf(companyName));
		int departmentNameMaxLength = 100;
		if (!StringUtils.isEmpty(departmentName) && departmentName.length() > departmentNameMaxLength) AppException.toThrow(MSG_01025, String.valueOf(departmentName));
		if (phone != null && !PatternUtil.matchesMobilePhone(phone.toString())) AppException.toThrow(MSG_01027);
		int addressMaxLength = 100;
		if (!StringUtils.isEmpty(address) && address.length() > addressMaxLength) AppException.toThrow(MSG_01026, String.valueOf(address));
		
		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		
		UserJobBean userJobBeanTmp = new UserJobBean();
		userJobBeanTmp.setUserId(userId);
		userJobBeanTmp = selectOne(userJobBeanTmp);
		if (userJobBeanTmp != null) {
			userJobBeanTmp.setIndustry(industry);
			userJobBeanTmp.setCompanyName(companyName);
			userJobBeanTmp.setDepartmentName(departmentName);
			userJobBeanTmp.setPosition(position);
			userJobBeanTmp.setPhone(phone);
			userJobBeanTmp.setAreaId(areaId);
			userJobBeanTmp.setAddress(address);
			userJobBeanTmp.setUpdateTime(nowDateLong);
			updatePkVer(userJobBeanTmp);
		} else {
			userJobBeanTmp = new UserJobBean();
			userJobBeanTmp.setUserId(userId);
			userJobBeanTmp.setIndustry(industry);
			userJobBeanTmp.setCompanyName(companyName);
			userJobBeanTmp.setDepartmentName(departmentName);
			userJobBeanTmp.setPosition(position);
			userJobBeanTmp.setPhone(phone);
			userJobBeanTmp.setAreaId(areaId);
			userJobBeanTmp.setAddress(address);
			userJobBeanTmp.setCreateTime(nowDateLong);
			insertSel(userJobBeanTmp);
		}
		
		// 修改
		userBean.setId(userId);
		userService.exe("updateAll", userBean);
	}
}