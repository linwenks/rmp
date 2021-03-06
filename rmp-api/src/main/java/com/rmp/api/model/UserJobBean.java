package com.rmp.api.model;

import com.rmp.info.model.UserJob;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class UserJobBean extends UserJob {
	
	@Builder
	public UserJobBean(Long id, Long userId, Integer industry, String companyName, String departmentName,
			Integer position, String office, Long phone, String area, String address, Integer isDelete, Integer version,
			Long createTime, Long updateTime
			, String areaNameAll, String industryValue, String positionValue) {
		super(id, userId, industry, companyName, departmentName, position, office, phone, area, address, isDelete, version,
				createTime, updateTime);
		this.areaNameAll = areaNameAll;
		this.industryValue = industryValue;
	}

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