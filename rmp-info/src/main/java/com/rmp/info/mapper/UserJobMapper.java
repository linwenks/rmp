package com.rmp.info.mapper;

import com.rmp.info.base.mapper.BaseMapper;
import com.rmp.info.model.UserJob;
import com.rmp.info.model.UserJobCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserJobMapper extends BaseMapper<UserJob, UserJobCriteria> {
}