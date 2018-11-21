package com.rmp.info.mapper;

import com.rmp.info.base.mapper.BaseMapper;
import com.rmp.info.model.Customer;
import com.rmp.info.model.CustomerCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerMapper extends BaseMapper<Customer, CustomerCriteria> {
}