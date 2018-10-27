package com.rmp.info.mapper;

import com.rmp.info.model.UserCustomer;
import com.rmp.info.model.UserCustomerCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserCustomerMapper {
    long countByExample(UserCustomerCriteria example);

    int deleteByExample(UserCustomerCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(UserCustomer record);

    int insertSelective(UserCustomer record);

    List<UserCustomer> selectByExample(UserCustomerCriteria example);

    UserCustomer selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserCustomer record, @Param("example") UserCustomerCriteria example);

    int updateByExample(@Param("record") UserCustomer record, @Param("example") UserCustomerCriteria example);

    int updateByPrimaryKeySelective(UserCustomer record);

    int updateByPrimaryKey(UserCustomer record);

    int insertBatch(@Param("recordColl") java.util.Collection<UserCustomer> recordColl);

    int insertBatchSel(@Param("recordColl") java.util.Collection<UserCustomer> recordColl);

    UserCustomer selectByExampleForOne(UserCustomerCriteria example);

    int updateByPrimaryKeySelectiveVer(UserCustomer record);

    int updateByPrimaryKeyVer(UserCustomer record);
}