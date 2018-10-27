package com.rmp.info.mapper;

import com.rmp.info.model.CustomerMaintain;
import com.rmp.info.model.CustomerMaintainCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerMaintainMapper {
    long countByExample(CustomerMaintainCriteria example);

    int deleteByExample(CustomerMaintainCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(CustomerMaintain record);

    int insertSelective(CustomerMaintain record);

    List<CustomerMaintain> selectByExample(CustomerMaintainCriteria example);

    CustomerMaintain selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CustomerMaintain record, @Param("example") CustomerMaintainCriteria example);

    int updateByExample(@Param("record") CustomerMaintain record, @Param("example") CustomerMaintainCriteria example);

    int updateByPrimaryKeySelective(CustomerMaintain record);

    int updateByPrimaryKey(CustomerMaintain record);

    int insertBatch(@Param("recordColl") java.util.Collection<CustomerMaintain> recordColl);

    int insertBatchSel(@Param("recordColl") java.util.Collection<CustomerMaintain> recordColl);

    CustomerMaintain selectByExampleForOne(CustomerMaintainCriteria example);

    int updateByPrimaryKeySelectiveVer(CustomerMaintain record);

    int updateByPrimaryKeyVer(CustomerMaintain record);
}