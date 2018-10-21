package com.rmp.info.mapper;

import com.rmp.info.model.PhoneMsg;
import com.rmp.info.model.PhoneMsgCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PhoneMsgMapper {
    long countByExample(PhoneMsgCriteria example);

    int deleteByExample(PhoneMsgCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(PhoneMsg record);

    int insertSelective(PhoneMsg record);

    List<PhoneMsg> selectByExample(PhoneMsgCriteria example);

    PhoneMsg selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PhoneMsg record, @Param("example") PhoneMsgCriteria example);

    int updateByExample(@Param("record") PhoneMsg record, @Param("example") PhoneMsgCriteria example);

    int updateByPrimaryKeySelective(PhoneMsg record);

    int updateByPrimaryKey(PhoneMsg record);

    int insertBatch(@Param("recordColl") java.util.Collection<PhoneMsg> recordColl);

    int insertBatchSel(@Param("recordColl") java.util.Collection<PhoneMsg> recordColl);

    PhoneMsg selectByExampleForOne(PhoneMsgCriteria example);

    int updateByPrimaryKeySelectiveVer(PhoneMsg record);

    int updateByPrimaryKeyVer(PhoneMsg record);
}