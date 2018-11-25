package com.rmp.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rmp.api.model.UserRemindBean;
import com.rmp.common.page.QueryPage;

public interface UserRemindMapperCustom {
	
	public List<UserRemindBean> selectList(@Param("queryPage") QueryPage queryPagen, @Param("userRemindBean") UserRemindBean userRemindBean);
}