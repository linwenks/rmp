package com.rmp.info.mapper;

import com.rmp.info.base.mapper.BaseMapper;
import com.rmp.info.model.CustomerJob;
import com.rmp.info.model.CustomerJobCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerJobMapper extends BaseMapper<CustomerJob, CustomerJobCriteria> {
}