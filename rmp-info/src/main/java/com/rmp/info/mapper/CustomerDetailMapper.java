package com.rmp.info.mapper;

import com.rmp.info.base.mapper.BaseMapper;
import com.rmp.info.model.CustomerDetail;
import com.rmp.info.model.CustomerDetailCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerDetailMapper extends BaseMapper<CustomerDetail, CustomerDetailCriteria> {
}