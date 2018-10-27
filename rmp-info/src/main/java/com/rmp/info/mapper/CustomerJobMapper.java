package com.rmp.info.mapper;

import com.rmp.info.model.CustomerJob;
import com.rmp.info.model.CustomerJobCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerJobMapper {
    long countByExample(CustomerJobCriteria example);

    int deleteByExample(CustomerJobCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(CustomerJob record);

    int insertSelective(CustomerJob record);

    List<CustomerJob> selectByExample(CustomerJobCriteria example);

    CustomerJob selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CustomerJob record, @Param("example") CustomerJobCriteria example);

    int updateByExample(@Param("record") CustomerJob record, @Param("example") CustomerJobCriteria example);

    int updateByPrimaryKeySelective(CustomerJob record);

    int updateByPrimaryKey(CustomerJob record);

    int insertBatch(@Param("recordColl") java.util.Collection<CustomerJob> recordColl);

    int insertBatchSel(@Param("recordColl") java.util.Collection<CustomerJob> recordColl);

    CustomerJob selectByExampleForOne(CustomerJobCriteria example);

    int updateByPrimaryKeySelectiveVer(CustomerJob record);

    int updateByPrimaryKeyVer(CustomerJob record);
}