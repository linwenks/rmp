package com.rmp.auto.base.exception;

import java.util.ArrayList;
import java.util.List;

import com.rmp.auto.base.model.MsgBean;
import com.rmp.auto.util.constan.MsgEnum;

import lombok.Getter;
import lombok.Setter;

/**
 * 应用异常
 * @author linw
 *
 */
@Getter
@Setter
public class AppException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1222233942643293408L;
	
	// 消息 map
	protected List<MsgBean> msgList = new ArrayList<>();
	
	private AppException() {
		super();
	}
	
	private AppException(Throwable throwable) {
		super(throwable);
	}
	
	private AppException(Throwable throwable, MsgEnum msgEnum) {
		super(throwable);
		msgList.add(MsgBean.build(msgEnum));
	}
	
	private AppException(Throwable throwable, List<MsgBean> msgList) {
		super(throwable);
		msgList.addAll(msgList);
	}
	
	private AppException(Throwable throwable, MsgEnum msgEnum, String[] params) {
		super(throwable);
		msgList.add(MsgBean.build(msgEnum, params));
	}
	
	private AppException(Throwable throwable, MsgEnum msgEnum, String[] params, String field) {
		super(throwable);
		msgList.add(MsgBean.build(msgEnum, params, field));
	}
	
	
	private AppException(MsgEnum msgEnum) {
		super();
		msgList.add(MsgBean.build(msgEnum));
	}
	
	private AppException(List<MsgBean> msgList) {
		super();
		msgList.addAll(msgList);
	}
	
	private AppException(MsgEnum msgEnum, String[] params) {
		super();
		msgList.add(MsgBean.build(msgEnum, params));
	}
	
	private AppException(MsgEnum msgEnum, String[] params, String field) {
		super();
		msgList.add(MsgBean.build(msgEnum, params, field));
	}
	
	
	
	// build
	public static AppException toThrow() {
		throw new AppException();
	}
	
	public static AppException toThrow(Throwable throwable) {
		throw new AppException(throwable);
	}
	
	public static AppException toThrow(MsgEnum msgEnum) {
		throw new AppException(msgEnum);
	}
	
	public static AppException toThrow(MsgEnum msgEnum, String param) {
		throw new AppException(msgEnum, new String[] {param});
	}
	
	public static AppException toThrow(MsgEnum msgEnum, String[] params) {
		throw new AppException(msgEnum, params);
	}
	
	
	public AppException msgEnum(MsgEnum msgEnum) {
		msgList.add(MsgBean.build(msgEnum));
		return this;
	}
}