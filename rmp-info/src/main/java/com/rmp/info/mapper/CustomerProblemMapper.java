package com.rmp.info.mapper;

import com.rmp.info.model.CustomerProblem;
import com.rmp.info.model.CustomerProblemCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerProblemMapper {
    long countByExample(CustomerProblemCriteria example);

    int deleteByExample(CustomerProblemCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(CustomerProblem record);

    int insertSelective(CustomerProblem record);

    List<CustomerProblem> selectByExample(CustomerProblemCriteria example);

    CustomerProblem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CustomerProblem record, @Param("example") CustomerProblemCriteria example);

    int updateByExample(@Param("record") CustomerProblem record, @Param("example") CustomerProblemCriteria example);

    int updateByPrimaryKeySelective(CustomerProblem record);

    int updateByPrimaryKey(CustomerProblem record);

    int insertBatch(@Param("recordColl") java.util.Collection<CustomerProblem> recordColl);

    int insertBatchSel(@Param("recordColl") java.util.Collection<CustomerProblem> recordColl);

    CustomerProblem selectByExampleForOne(CustomerProblemCriteria example);

    int updateByPrimaryKeySelectiveVer(CustomerProblem record);

    int updateByPrimaryKeyVer(CustomerProblem record);
}