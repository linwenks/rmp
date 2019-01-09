package com.rmp.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rmp.api.model.UserRemindBean;
import com.rmp.common.page.QueryPage;

public interface UserRemindMapperCustom {
	
	public List<UserRemindBean> selectList(@Param("queryPage") QueryPage queryPagen, @Param("userRemindBean") UserRemindBean userRemindBean);

	public int insertBy1ToYmd(@Param("userRemindBean") UserRemindBean userRemindBean);
	
	public int insertBy1ToMd(@Param("userRemindBean")UserRemindBean userRemindBean);
	
	public int insertBy1ToD(@Param("userRemindBean")UserRemindBean userRemindBean);
	
	public int insertBy1ToW(@Param("userRemindBean")UserRemindBean userRemindBean);
	
	public int insertBy2(@Param("userRemindBean")UserRemindBean userRemindBean);
	
	public int insertBy3(@Param("userRemindBean")UserRemindBean userRemindBean);
	
	public int insertBy4(@Param("userRemindBean")UserRemindBean userRemindBean);
}