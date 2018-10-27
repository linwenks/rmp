package com.rmp.info.mapper;

import com.rmp.info.model.CustomerFamily;
import com.rmp.info.model.CustomerFamilyCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerFamilyMapper {
    long countByExample(CustomerFamilyCriteria example);

    int deleteByExample(CustomerFamilyCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(CustomerFamily record);

    int insertSelective(CustomerFamily record);

    List<CustomerFamily> selectByExample(CustomerFamilyCriteria example);

    CustomerFamily selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CustomerFamily record, @Param("example") CustomerFamilyCriteria example);

    int updateByExample(@Param("record") CustomerFamily record, @Param("example") CustomerFamilyCriteria example);

    int updateByPrimaryKeySelective(CustomerFamily record);

    int updateByPrimaryKey(CustomerFamily record);

    int insertBatch(@Param("recordColl") java.util.Collection<CustomerFamily> recordColl);

    int insertBatchSel(@Param("recordColl") java.util.Collection<CustomerFamily> recordColl);

    CustomerFamily selectByExampleForOne(CustomerFamilyCriteria example);

    int updateByPrimaryKeySelectiveVer(CustomerFamily record);

    int updateByPrimaryKeyVer(CustomerFamily record);
}