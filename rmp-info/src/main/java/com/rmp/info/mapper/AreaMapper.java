package com.rmp.info.mapper;

import com.rmp.info.model.Area;
import com.rmp.info.model.AreaCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AreaMapper {
    long countByExample(AreaCriteria example);

    int deleteByExample(AreaCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(Area record);

    int insertSelective(Area record);

    List<Area> selectByExample(AreaCriteria example);

    Area selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Area record, @Param("example") AreaCriteria example);

    int updateByExample(@Param("record") Area record, @Param("example") AreaCriteria example);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);

    int insertBatch(@Param("recordColl") java.util.Collection<Area> recordColl);

    int insertBatchSel(@Param("recordColl") java.util.Collection<Area> recordColl);

    Area selectByExampleForOne(AreaCriteria example);

    int updateByPrimaryKeySelectiveVer(Area record);

    int updateByPrimaryKeyVer(Area record);
}