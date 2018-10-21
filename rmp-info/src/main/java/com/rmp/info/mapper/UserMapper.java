package com.rmp.info.mapper;

import com.rmp.info.model.User;
import com.rmp.info.model.UserCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    long countByExample(UserCriteria example);

    int deleteByExample(UserCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserCriteria example);

    User selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserCriteria example);

    int updateByExample(@Param("record") User record, @Param("example") UserCriteria example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int insertBatch(@Param("recordColl") java.util.Collection<User> recordColl);

    int insertBatchSel(@Param("recordColl") java.util.Collection<User> recordColl);

    User selectByExampleForOne(UserCriteria example);

    int updateByPrimaryKeySelectiveVer(User record);

    int updateByPrimaryKeyVer(User record);
}