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
public class UserJobServiceImpl extends BaseServiceImpl<UserJob, UserJobBean, UserJobCriteria> implements UserJobService {
	
	@Autowired
	private UserJobMapper userJobMapper;
	@Autowired
	private UserService userService;
	
	@Override
	public UserJobMapper mapper() {
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
			AppException.toThrow(e);
		}
		return null;
	}
	
	@Override
	protected void where(Object criteria, UserJobBean bean) {
		if (bean == null) {
			return;
		}
		UserJobCriteria.Criteria criteriaTmp = (UserJobCriteria.Criteria) criteria;
		criteriaTmp.andIsDeleteEqualTo(Constant.DELETE_N);
		if (bean.getId() != null) {
			criteriaTmp.andIdEqualTo(bean.getId());
		}
		if (bean.getUserId() != null) {
			criteriaTmp.andUserIdEqualTo(bean.getUserId());
		}
		if (bean.getIsDelete() != null) {
			criteriaTmp.andIsDeleteEqualTo(bean.getIsDelete());
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
		
		UserJobBean userJobBeanTmp = UserJobBean.builder().userId(userId).build();
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
			userJobBeanTmp = UserJobBean.builder()
			.userId(userId)
			.industry(industry)
			.companyName(companyName)
			.departmentName(departmentName)
			.position(position)
			.phone(phone)
			.areaId(areaId)
			.address(address)
			.createTime(nowDateLong)
			.build();
			insertSel(userJobBeanTmp);
		}
		
		// 修改
		userBean.setId(userId);
		userService.exe("updateAll", userBean);
	}
}