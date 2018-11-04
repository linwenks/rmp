package com.rmp.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rmp.api.model.CustomerBean;
import com.rmp.common.page.QueryPage;

public interface CustomerMapperCustom {
    
	public List<CustomerBean> selectList(@Param("queryPage") QueryPage queryPage, @Param("customerBean") CustomerBean customerBean);
    
}