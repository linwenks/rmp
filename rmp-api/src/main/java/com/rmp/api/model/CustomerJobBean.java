package com.rmp.api.model;

import com.rmp.info.model.CustomerJob;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerJobBean extends CustomerJob {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2088956218423446207L;
	
	private Long userId;
	
	/**
     * 区域 名称 all
     */
    private String areaNameAll;
}