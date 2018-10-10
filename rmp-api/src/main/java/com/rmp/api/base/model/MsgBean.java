package com.rmp.api.base.model;

import com.rmp.api.util.MsgUtil;

import lombok.Getter;

@Getter
public class MsgBean {
	
	private String code;
	
	private String field;
	
	private String des;
	
	public MsgBean() {
	}
	
	public MsgBean(String code) {
		this(code, null, null);
	}
	
	public MsgBean(String code, String field) {
		this(code, null, field);
	}
	
	public MsgBean(String code, String[] params) {
		this(code, params, null);
	}
	
	public MsgBean(String code, String[] params, String field) {
		this.code = code;
		this.field = field;
		
		des = MsgUtil.getJsonValue(code);
		if (params != null && params.length > 0) {
			for (int i=0; i<params.length; i++) {
				des = des.replaceAll("\\{" + i + "\\}", params[i]);
			}
		}
	}
}