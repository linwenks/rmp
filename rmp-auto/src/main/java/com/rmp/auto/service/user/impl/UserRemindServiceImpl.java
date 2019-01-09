package com.rmp.auto.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmp.auto.base.exception.AppException;
import com.rmp.auto.base.service.impl.BaseServiceImpl;
import com.rmp.auto.mapper.UserRemindMapperCustom;
import com.rmp.auto.model.UserRemindBean;
import com.rmp.auto.service.user.UserRemindService;
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
	public Object exe(String cmd, Object obj) {
		try {
			switch (cmd) {
			case "truncate":          return userRemindMapperCustom.truncate();
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
}