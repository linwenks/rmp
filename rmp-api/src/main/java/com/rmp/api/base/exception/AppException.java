package com.rmp.api.base.exception;

import java.util.ArrayList;
import java.util.List;

import com.rmp.api.base.model.MsgBean;
import com.rmp.api.util.MsgEnum;

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
	
	protected MsgEnum msgEnum;
	
	public AppException() {
		super();
	}
	
	public AppException(Throwable throwable) {
		super(throwable);
	}
	
	public AppException(Throwable throwable, MsgEnum msgEnum) {
		super(throwable);
		msgList.add(new MsgBean(msgEnum));
	}
	
	public AppException(Throwable throwable, List<MsgBean> msgList) {
		super(throwable);
		this.msgList.addAll(msgList);
	}
	
	public AppException(Throwable throwable, MsgEnum msgEnum, String[] params) {
		super(throwable);
		msgList.add(new MsgBean(msgEnum, params));
	}
	
	public AppException(Throwable throwable, MsgEnum msgEnum, String[] params, String field) {
		super(throwable);
		msgList.add(new MsgBean(msgEnum, params, field));
	}
	
	
	public AppException(MsgEnum msgEnum) {
		super();
		msgList.add(new MsgBean(msgEnum));
	}
	
	public AppException(List<MsgBean> msgList) {
		super();
		this.msgList.addAll(msgList);
	}
	
	public AppException(MsgEnum msgEnum, String[] params) {
		super();
		msgList.add(new MsgBean(msgEnum, params));
	}
	
	public AppException(MsgEnum msgEnum, String[] params, String field) {
		super();
		msgList.add(new MsgBean(msgEnum, params, field));
	}
	
	
	public AppException addMsg(MsgEnum msgEnum) {
		msgList.add(new MsgBean(msgEnum));
		return this;
	}
	
	public AppException addMsg(MsgEnum msgEnum, String[] params) {
		msgList.add(new MsgBean(msgEnum, params));
		return this;
	}
	
	public AppException addMsg(List<MsgBean> msgList) {
		this.msgList.addAll(msgList);
		return this;
	}
	
	public AppException setMsg(List<MsgBean> msgList) {
		this.msgList.addAll(msgList);
		return this;
	}

	public List<MsgBean> getMsgList() {
		return msgList;
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
		msgList.add(new MsgBean(msgEnum));
		return this;
	}
}
