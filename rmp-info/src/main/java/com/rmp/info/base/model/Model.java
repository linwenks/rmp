package com.rmp.info.base.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Model implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private transient String orderBy;
}