package com.rmp.api.model;

import com.rmp.info.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBean extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2088956218423446207L;
	
	/**
	 * 微信 js code
	 */
	private String jsCode;
}