package com.rmp.info.mapper;

import com.rmp.info.base.mapper.BaseMapper;
import com.rmp.info.model.User;
import com.rmp.info.model.UserCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User, UserCriteria> {
}