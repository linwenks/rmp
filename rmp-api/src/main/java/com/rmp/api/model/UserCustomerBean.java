package com.rmp.api.model;

import com.rmp.info.model.UserCustomer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCustomerBean extends UserCustomer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2088956218423446207L;
	
	private CustomerBean customerBean;
}