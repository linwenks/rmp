package com.rmp.api.base.model;

import com.rmp.api.util.MsgEnum;

import lombok.Getter;

@Getter
public class MsgBean {
	
	private String code;
	
	private String field;
	
	private String des;
	
	private MsgBean() {
		
	}
	
	private MsgBean(MsgEnum msgEnum, String[] params, String field) {
		if (msgEnum == null) return;
		
		this.code = msgEnum.getKey();
		this.field = field;
		
		this.des = msgEnum.getValue();
		if (params != null && params.length > 0) {
			for (int i=0; i<params.length; i++) {
				des = des.replaceAll("\\{" + i + "\\}", params[i]);
			}
		}
	}
	
	// build
	public static MsgBean build() {
		return new MsgBean();
	}
	
	public static MsgBean build(MsgEnum msgEnum) {
		return build(msgEnum, null, null);
	}
	
	public static MsgBean build(MsgEnum msgEnum, String field) {
		return build(msgEnum, null, field);
	}
	
	public static MsgBean build(MsgEnum msgEnum, String[] params) {
		return build(msgEnum, params, null);
	}
	
	public static MsgBean build(MsgEnum msgEnum, String[] params, String field) {
		return new MsgBean(msgEnum, params, field);
	}
}