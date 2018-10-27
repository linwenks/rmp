package com.rmp.api.model;

import java.util.List;
import java.util.Map;

import com.rmp.info.model.SysCode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysCodeBean extends SysCode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2088956218423446207L;
	
	private SysCodeBean parent;
	
	private List<SysCodeBean> childList;
	
	private Map<String, SysCodeBean> childMap;
}