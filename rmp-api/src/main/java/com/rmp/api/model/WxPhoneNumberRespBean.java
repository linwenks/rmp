package com.rmp.api.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WxPhoneNumberRespBean implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5925057910510454264L;

	private String phoneNumber;
	
	private String purePhoneNumber;
	
	private String countryCode;
}