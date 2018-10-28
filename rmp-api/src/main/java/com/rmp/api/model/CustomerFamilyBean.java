package com.rmp.api.model;

import com.rmp.info.model.CustomerFamily;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerFamilyBean extends CustomerFamily {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2088956218423446207L;
	
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