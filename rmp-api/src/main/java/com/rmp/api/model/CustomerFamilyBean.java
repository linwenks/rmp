package com.rmp.api.model;

import com.rmp.info.model.CustomerFamily;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class CustomerFamilyBean extends CustomerFamily {
	
	@Builder
	public CustomerFamilyBean(Long id, Long customerId, Integer relationship, String realName, Integer birthday,
			Long phone, Long areaId, String address, Integer isDelete, Integer version, Long createTime,
			Long updateTime
			, Long userId, String areaNameAll, String relationshipValue, Integer age) {
		super(id, customerId, relationship, realName, birthday, phone, areaId, address, isDelete, version, createTime,
				updateTime);
		this.userId = userId;
		this.areaNameAll = areaNameAll;
		this.relationshipValue = relationshipValue;
		this.age = age;
	}
	
	private Long userId;
	
	/**
     * 区域 名称 all
     */
    private String areaNameAll;
    
    /**
     * 关系 value
     */
    private String relationshipValue;
    
    /**
     * 年龄
     */
    private Integer age;
}