package com.rmp.api.service.user.impl;

import static com.rmp.api.util.MsgEnum.*;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.service.impl.BaseServiceImpl;
import com.rmp.api.model.UserBean;
import com.rmp.api.model.UserHobbyBean;
import com.rmp.api.service.user.UserHobbyService;
import com.rmp.api.service.user.UserService;
import com.rmp.api.util.constant.Constant;
import com.rmp.common.util.DateUtil;
import com.rmp.info.mapper.UserHobbyMapper;
import com.rmp.info.model.UserHobby;
import com.rmp.info.model.UserHobbyCriteria;

/**
 * 用户 兴趣爱好 service impl
 * @author linw
 *
 */
@Service
public class UserHobbyServiceImpl extends BaseServiceImpl implements UserHobbyService {
	
	@Autowired
	private UserHobbyMapper userHobbyMapper;
	@Autowired
	private UserService userService;

	@Override
	public Class<?> getModelClass() {
		return UserHobby.class;
	}

	@Override
	public Class<?> getBeanClass() {
		return UserHobbyBean.class;
	}

	@Override
	public Class<?> getCriteriaClass() {
		return UserHobbyCriteria.class;
	}

	@Override
	public Object getMapper() {
		return userHobbyMapper;
	}
	
	@Override
	public Object exe(String cmd, Object obj) {
		try {
			switch (cmd) {
			case "update": update((UserHobbyBean) obj);break;
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
		UserHobbyCriteria.Criteria criteriaTmp = (UserHobbyCriteria.Criteria) criteria;
		UserHobbyBean beanTmp = (UserHobbyBean) bean;
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
	
	private void update(UserHobbyBean userHobbyBean) {
		if (userHobbyBean == null) AppException.toThrow(MSG_00003);
		Long userId = userHobbyBean.getUserId();
		
		String interest = StringUtils.trim(userHobbyBean.getInterest());
		String diet = StringUtils.trim(userHobbyBean.getDiet());
		String taste = StringUtils.trim(userHobbyBean.getTaste());
		
		if (userId == null) AppException.toThrow(MSG_00003);
		
		UserBean userBeanTmp = new UserBean();
		userBeanTmp.setId(userId);
		userBeanTmp = userService.selectOne(userBeanTmp);
		if (userBeanTmp == null) AppException.toThrow(MSG_00003);

		Date nowDate = DateUtil.now();
		Long nowDateLong = DateUtil.formatDate2Long(nowDate);
		
		UserHobbyBean userHobbyBeanTmp = new UserHobbyBean();
		userHobbyBeanTmp.setUserId(userId);
		userHobbyBeanTmp = selectOne(userHobbyBeanTmp);
		if (userHobbyBeanTmp != null) {
			userHobbyBeanTmp.setInterest(interest);
			userHobbyBeanTmp.setDiet(diet);
			userHobbyBeanTmp.setTaste(taste);
			userHobbyBeanTmp.setUpdateTime(nowDateLong);
			updatePkVer(userHobbyBeanTmp);
		} else {
			userHobbyBeanTmp = new UserHobbyBean();
			userHobbyBeanTmp.setUserId(userId);
			userHobbyBeanTmp.setInterest(interest);
			userHobbyBeanTmp.setDiet(diet);
			userHobbyBeanTmp.setTaste(taste);
			userHobbyBeanTmp.setCreateTime(nowDateLong);
			insertSel(userHobbyBeanTmp);
		}
		
		// 修改
		userBeanTmp.setUpdateTime(nowDateLong);
		userService.exe(UPDATE_PK_SEl_VER, userBeanTmp);
	}
}