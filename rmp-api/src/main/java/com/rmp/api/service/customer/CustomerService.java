package com.rmp.api.service.customer;


import java.util.List;

import com.rmp.api.base.service.BaseService;
import com.rmp.api.model.CustomerBean;
import com.rmp.common.page.QueryPage;

/**
 * 客户 service
 * @author linw
 *
 */
public interface CustomerService extends BaseService<CustomerBean> {
	
	public List<CustomerBean> selectListCustom(QueryPage queryPage, CustomerBean customerBean);
}