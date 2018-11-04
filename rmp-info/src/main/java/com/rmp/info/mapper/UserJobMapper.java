package com.rmp.info.mapper;

import com.rmp.info.model.UserJob;
import com.rmp.info.model.UserJobCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserJobMapper {
    long countByExample(UserJobCriteria example);

    int deleteByExample(UserJobCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(UserJob record);

    int insertSelective(UserJob record);

    List<UserJob> selectByExample(UserJobCriteria example);

    UserJob selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserJob record, @Param("example") UserJobCriteria example);

    int updateByExample(@Param("record") UserJob record, @Param("example") UserJobCriteria example);

    int updateByPrimaryKeySelective(UserJob record);

    int updateByPrimaryKey(UserJob record);

    int insertBatch(@Param("recordColl") java.util.Collection<UserJob> recordColl);

    int insertBatchSel(@Param("recordColl") java.util.Collection<UserJob> recordColl);

    UserJob selectByExampleForOne(UserJobCriteria example);

    int updateByPrimaryKeySelectiveVer(UserJob record);

    int updateByPrimaryKeyVer(UserJob record);
}