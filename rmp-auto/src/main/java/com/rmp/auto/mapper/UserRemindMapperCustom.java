package com.rmp.auto.mapper;

import org.apache.ibatis.annotations.Param;

import com.rmp.auto.model.UserRemindBean;

public interface UserRemindMapperCustom {
	
	public int truncate();
	
	public int insertBy1ToYmd(@Param("userRemindBean") UserRemindBean userRemindBean);
	
	public int insertBy1ToMd(@Param("userRemindBean")UserRemindBean userRemindBean);
	
	public int insertBy1ToD(@Param("userRemindBean")UserRemindBean userRemindBean);
	
	public int insertBy1ToW(@Param("userRemindBean")UserRemindBean userRemindBean);
	
	public int insertBy2(@Param("userRemindBean")UserRemindBean userRemindBean);
	
	public int insertBy3(@Param("userRemindBean")UserRemindBean userRemindBean);
	
	public int insertBy4(@Param("userRemindBean")UserRemindBean userRemindBean);
}