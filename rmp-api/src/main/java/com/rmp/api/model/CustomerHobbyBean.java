package com.rmp.api.model;

import com.rmp.info.model.CustomerHobby;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerHobbyBean extends CustomerHobby {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2088956218423446207L;
	
	private Long userId;
}