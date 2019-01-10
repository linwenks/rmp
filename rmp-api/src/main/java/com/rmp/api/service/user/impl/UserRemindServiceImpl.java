package com.rmp.api.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.service.impl.BaseServiceImpl;
import com.rmp.api.mapper.UserRemindMapperCustom;
import com.rmp.api.model.UserRemindBean;
import com.rmp.api.model.UserRemindBean;
import com.rmp.api.service.user.UserRemindService;
import com.rmp.api.util.constant.Constant;
import com.rmp.common.page.QueryPage;
import com.rmp.info.mapper.UserRemindMapper;
import com.rmp.info.model.UserRemindCriteria;
import com.rmp.info.model.UserRemind;
import com.rmp.info.model.UserRemindCriteria;

@Service
public class UserRemindServiceImpl extends BaseServiceImpl<UserRemind, UserRemindBean, UserRemindCriteria> implements UserRemindService {

	@Autowired
	private UserRemindMapper userRemindMapper;
	@Autowired
	private UserRemindMapperCustom userRemindMapperCustom;
	
	@Override
	public UserRemindMapper mapper() {
		return userRemindMapper;
	}

	@Override
	public Object exe(String cmd, Object obj) {
		try {
			switch (cmd) {
			case "insertBy1ToYmd":    return userRemindMapperCustom.insertBy1ToYmd((UserRemindBean) obj);
			case "insertBy1ToMd":     return userRemindMapperCustom.insertBy1ToMd((UserRemindBean) obj);
			case "insertBy1ToD":      return userRemindMapperCustom.insertBy1ToD((UserRemindBean) obj);
			case "insertBy1ToW":      return userRemindMapperCustom.insertBy1ToW((UserRemindBean) obj);
			case "insertBy2":         return userRemindMapperCustom.insertBy2((UserRemindBean) obj);
			case "insertBy3":         return userRemindMapperCustom.insertBy3((UserRemindBean) obj);
			case "insertBy4":         return userRemindMapperCustom.insertBy4((UserRemindBean) obj);
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
	protected void where(Object criteria, UserRemindBean bean) {
		if (bean == null) {
			return;
		}
		UserRemindCriteria.Criteria criteriaTmp = (UserRemindCriteria.Criteria) criteria;
		criteriaTmp.andIsDeleteEqualTo(Constant.DELETE_N);
		if (bean.getId() != null) {
			criteriaTmp.andIdEqualTo(bean.getId());
		}
		if (bean.getUserId() != null) {
			criteriaTmp.andUserIdEqualTo(bean.getUserId());
		}
		if (bean.getType() != null) {
			criteriaTmp.andTypeEqualTo(bean.getType());
		}
		if (bean.getTypeId() != null) {
			criteriaTmp.andTypeIdEqualTo(bean.getTypeId());
		}
	}
	

	@Override
	public List<UserRemindBean> selectListCustom(QueryPage queryPage, UserRemindBean userRemindBean) {
		return userRemindMapperCustom.selectList(queryPage, userRemindBean);
	}
}