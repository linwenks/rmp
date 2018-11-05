package com.rmp.api.model;

import com.rmp.info.model.UserJob;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserJobBean extends UserJob {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2088956218423446207L;
	
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