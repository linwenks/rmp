package com.rmp.api.model;

import com.rmp.info.model.CustomerMemorialDay;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerMemorialDayBean extends CustomerMemorialDay {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2088956218423446207L;
	
	private Long userId;
	
	/**
     * 类型 value
     */
    private String occurTypeValue;

    /**
     * 提前类型 value
     */
    private String advanceTypeValue;
}