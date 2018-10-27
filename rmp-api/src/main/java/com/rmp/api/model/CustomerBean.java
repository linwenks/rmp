package com.rmp.api.model;

import com.rmp.info.model.Customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerBean extends Customer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2088956218423446207L;
	
	private Long idNot;
}