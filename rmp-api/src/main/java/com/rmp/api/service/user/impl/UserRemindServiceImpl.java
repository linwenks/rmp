package com.rmp.api.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmp.api.base.service.impl.BaseServiceImpl;
import com.rmp.api.mapper.UserRemindMapperCustom;
import com.rmp.api.model.UserRemindBean;
import com.rmp.api.service.user.UserRemindService;
import com.rmp.common.page.QueryPage;
import com.rmp.info.mapper.UserRemindMapper;
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
	public List<UserRemindBean> selectListCustom(QueryPage queryPage, UserRemindBean userRemindBean) {
		return userRemindMapperCustom.selectList(queryPage, userRemindBean);
	}
}