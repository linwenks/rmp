package com.rmp.auto.mapper;

import com.rmp.auto.model.UserRemindBean;

public interface UserRemindMapperCustom {
	
	public int insertBy1ToYmd(UserRemindBean userRemindBean);
	
	public int insertBy1ToYmdNow(UserRemindBean userRemindBean);
	
	public int insertBy1ToMd(UserRemindBean userRemindBean);
	
	public int insertBy1ToMdNow(UserRemindBean userRemindBean);
	
	public int insertBy1ToD(UserRemindBean userRemindBean);
	
	public int insertBy1ToDNow(UserRemindBean userRemindBean);
	
	public int insertBy1ToW(UserRemindBean userRemindBean);
	
	public int insertBy1ToWNow(UserRemindBean userRemindBean);
	
	public int insertBy2(UserRemindBean userRemindBean);
	
	public int insertBy2Now(UserRemindBean userRemindBean);
	
	public int insertBy3(UserRemindBean userRemindBean);
	
	public int insertBy3Now(UserRemindBean userRemindBean);
}