package com.rmp.api.model;

import com.rmp.info.model.CustomerJob;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class CustomerJobBean extends CustomerJob {
	
	@Builder
	public CustomerJobBean(Long id, Long customerId, Integer industry, String companyName, String departmentName,
			Integer position, Long phone, Long areaId, String address, Integer isDelete, Integer version,
			Long createTime, Long updateTime
			, Long userId, String areaNameAll, String industryValue, String positionValue) {
		super(id, customerId, industry, companyName, departmentName, position, phone, areaId, address, isDelete, version,
				createTime, updateTime);
		this.userId = userId;
		this.areaNameAll = areaNameAll;
		this.industryValue = industryValue;
		this.positionValue = positionValue;
	}
	
	private Long userId;
	
	/**
     * 区域 名称 all
     */
    private String areaNameAll;
    
    /**
     * 行业 value
     */
    private String industryValue;
    
    /**
     * 职位 value
     */
    private String positionValue;
}