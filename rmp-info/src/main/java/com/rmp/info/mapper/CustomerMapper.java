package com.rmp.info.mapper;

import com.rmp.info.model.Customer;
import com.rmp.info.model.CustomerCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerMapper {
    long countByExample(CustomerCriteria example);

    int deleteByExample(CustomerCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(Customer record);

    int insertSelective(Customer record);

    List<Customer> selectByExample(CustomerCriteria example);

    Customer selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Customer record, @Param("example") CustomerCriteria example);

    int updateByExample(@Param("record") Customer record, @Param("example") CustomerCriteria example);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    int insertBatch(@Param("recordColl") java.util.Collection<Customer> recordColl);

    int insertBatchSel(@Param("recordColl") java.util.Collection<Customer> recordColl);

    Customer selectByExampleForOne(CustomerCriteria example);

    int updateByPrimaryKeySelectiveVer(Customer record);

    int updateByPrimaryKeyVer(Customer record);
}