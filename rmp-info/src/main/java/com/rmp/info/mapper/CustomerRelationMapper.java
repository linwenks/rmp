package com.rmp.info.mapper;

import com.rmp.info.model.CustomerRelation;
import com.rmp.info.model.CustomerRelationCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerRelationMapper {
    long countByExample(CustomerRelationCriteria example);

    int deleteByExample(CustomerRelationCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(CustomerRelation record);

    int insertSelective(CustomerRelation record);

    List<CustomerRelation> selectByExample(CustomerRelationCriteria example);

    CustomerRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CustomerRelation record, @Param("example") CustomerRelationCriteria example);

    int updateByExample(@Param("record") CustomerRelation record, @Param("example") CustomerRelationCriteria example);

    int updateByPrimaryKeySelective(CustomerRelation record);

    int updateByPrimaryKey(CustomerRelation record);

    int insertBatch(@Param("recordColl") java.util.Collection<CustomerRelation> recordColl);

    int insertBatchSel(@Param("recordColl") java.util.Collection<CustomerRelation> recordColl);

    CustomerRelation selectByExampleForOne(CustomerRelationCriteria example);

    int updateByPrimaryKeySelectiveVer(CustomerRelation record);

    int updateByPrimaryKeyVer(CustomerRelation record);
}