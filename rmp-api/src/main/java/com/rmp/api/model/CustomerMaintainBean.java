package com.rmp.api.model;

import com.rmp.info.model.CustomerMaintain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerMaintainBean extends CustomerMaintain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2088956218423446207L;
	
	private Long userId;
	
	/**
	 * 维护 value
	 */
	private String maintainValue;
}