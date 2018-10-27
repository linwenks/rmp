package com.rmp.info.mapper;

import com.rmp.info.model.SysCode;
import com.rmp.info.model.SysCodeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysCodeMapper {
    long countByExample(SysCodeCriteria example);

    int deleteByExample(SysCodeCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(SysCode record);

    int insertSelective(SysCode record);

    List<SysCode> selectByExample(SysCodeCriteria example);

    SysCode selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysCode record, @Param("example") SysCodeCriteria example);

    int updateByExample(@Param("record") SysCode record, @Param("example") SysCodeCriteria example);

    int updateByPrimaryKeySelective(SysCode record);

    int updateByPrimaryKey(SysCode record);

    int insertBatch(@Param("recordColl") java.util.Collection<SysCode> recordColl);

    int insertBatchSel(@Param("recordColl") java.util.Collection<SysCode> recordColl);

    SysCode selectByExampleForOne(SysCodeCriteria example);

    int updateByPrimaryKeySelectiveVer(SysCode record);

    int updateByPrimaryKeyVer(SysCode record);
}