package com.rmp.api.service.user;

import java.util.List;

import com.rmp.api.base.service.BaseService;
import com.rmp.api.model.UserRemindBean;
import com.rmp.common.page.QueryPage;

public interface UserRemindService extends BaseService<UserRemindBean> {

	public List<UserRemindBean> selectListCustom(QueryPage queryPage, UserRemindBean userRemindBean);
}
