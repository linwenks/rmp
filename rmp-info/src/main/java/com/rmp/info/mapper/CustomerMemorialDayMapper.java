package com.rmp.info.mapper;

import com.rmp.info.model.CustomerMemorialDay;
import com.rmp.info.model.CustomerMemorialDayCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerMemorialDayMapper {
    long countByExample(CustomerMemorialDayCriteria example);

    int deleteByExample(CustomerMemorialDayCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(CustomerMemorialDay record);

    int insertSelective(CustomerMemorialDay record);

    List<CustomerMemorialDay> selectByExample(CustomerMemorialDayCriteria example);

    CustomerMemorialDay selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CustomerMemorialDay record, @Param("example") CustomerMemorialDayCriteria example);

    int updateByExample(@Param("record") CustomerMemorialDay record, @Param("example") CustomerMemorialDayCriteria example);

    int updateByPrimaryKeySelective(CustomerMemorialDay record);

    int updateByPrimaryKey(CustomerMemorialDay record);

    int insertBatch(@Param("recordColl") java.util.Collection<CustomerMemorialDay> recordColl);

    int insertBatchSel(@Param("recordColl") java.util.Collection<CustomerMemorialDay> recordColl);

    CustomerMemorialDay selectByExampleForOne(CustomerMemorialDayCriteria example);

    int updateByPrimaryKeySelectiveVer(CustomerMemorialDay record);

    int updateByPrimaryKeyVer(CustomerMemorialDay record);
}