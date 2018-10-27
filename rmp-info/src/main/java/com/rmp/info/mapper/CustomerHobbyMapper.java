package com.rmp.info.mapper;

import com.rmp.info.model.CustomerHobby;
import com.rmp.info.model.CustomerHobbyCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerHobbyMapper {
    long countByExample(CustomerHobbyCriteria example);

    int deleteByExample(CustomerHobbyCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(CustomerHobby record);

    int insertSelective(CustomerHobby record);

    List<CustomerHobby> selectByExample(CustomerHobbyCriteria example);

    CustomerHobby selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CustomerHobby record, @Param("example") CustomerHobbyCriteria example);

    int updateByExample(@Param("record") CustomerHobby record, @Param("example") CustomerHobbyCriteria example);

    int updateByPrimaryKeySelective(CustomerHobby record);

    int updateByPrimaryKey(CustomerHobby record);

    int insertBatch(@Param("recordColl") java.util.Collection<CustomerHobby> recordColl);

    int insertBatchSel(@Param("recordColl") java.util.Collection<CustomerHobby> recordColl);

    CustomerHobby selectByExampleForOne(CustomerHobbyCriteria example);

    int updateByPrimaryKeySelectiveVer(CustomerHobby record);

    int updateByPrimaryKeyVer(CustomerHobby record);
}