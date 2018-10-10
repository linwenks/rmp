package com.rmp.api.base.exception;

import java.util.ArrayList;
import java.util.List;

import com.rmp.api.base.model.MsgBean;

/**
 * 应用异常
 * @author linw
 *
 */
public class AppException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1222233942643293408L;
	
	// 消息 map
	protected List<MsgBean> msgList = new ArrayList<>();
	
	public AppException() {
		super();
	}
	
	public AppException(Throwable throwable) {
		super(throwable);
	}
	
	public AppException(Throwable throwable, String code) {
		super(throwable);
		msgList.add(new MsgBean(code));
	}
	
	public AppException(Throwable throwable, List<MsgBean> msgList) {
		super(throwable);
		this.msgList.addAll(msgList);
	}
	
	public AppException(Throwable throwable, String code, String[] params) {
		super(throwable);
		msgList.add(new MsgBean(code, params));
	}
	
	public AppException(Throwable throwable, String code, String[] params, String field) {
		super(throwable);
		msgList.add(new MsgBean(code, params, field));
	}
	
	
	public AppException(String code) {
		super();
		msgList.add(new MsgBean(code));
	}
	
	public AppException(List<MsgBean> msgList) {
		super();
		this.msgList.addAll(msgList);
	}
	
	public AppException(String code, String[] params) {
		super();
		msgList.add(new MsgBean(code, params));
	}
	
	public AppException(String code, String[] params, String field) {
		super();
		msgList.add(new MsgBean(code, params, field));
	}
	
	
	public AppException addMsg(String code) {
		msgList.add(new MsgBean(code));
		return this;
	}
	
	public AppException addMsg(String code, String[] params) {
		msgList.add(new MsgBean(code, params));
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
}
