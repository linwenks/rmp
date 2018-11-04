package com.rmp.info.mapper;

import com.rmp.info.model.UserHobby;
import com.rmp.info.model.UserHobbyCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserHobbyMapper {
    long countByExample(UserHobbyCriteria example);

    int deleteByExample(UserHobbyCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(UserHobby record);

    int insertSelective(UserHobby record);

    List<UserHobby> selectByExample(UserHobbyCriteria example);

    UserHobby selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserHobby record, @Param("example") UserHobbyCriteria example);

    int updateByExample(@Param("record") UserHobby record, @Param("example") UserHobbyCriteria example);

    int updateByPrimaryKeySelective(UserHobby record);

    int updateByPrimaryKey(UserHobby record);

    int insertBatch(@Param("recordColl") java.util.Collection<UserHobby> recordColl);

    int insertBatchSel(@Param("recordColl") java.util.Collection<UserHobby> recordColl);

    UserHobby selectByExampleForOne(UserHobbyCriteria example);

    int updateByPrimaryKeySelectiveVer(UserHobby record);

    int updateByPrimaryKeyVer(UserHobby record);
}