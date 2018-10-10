package com.rmp.api.util.constant;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 常量
 * @author linw
 *
 */
public final class Constant {
	
	protected static Logger log = LoggerFactory.getLogger(Constant.class);
	
	private static final ResourceBundle rb = PropertyResourceBundle.getBundle("rmp");
	
	private Constant() {
		
	}
	

	// 当前登录用户
	public static final String CURRENT_LOGIN_USER = "CURRENT_LOGIN_USER";
	
	// 当前请求header
	public static final String CURRENT_REQUEST_HEADER = "CURRENT_REQUEST_HEADER";
	
	// 消息
	public static final class Msg {
		
		public static final String SUCCESS = "0";
		public static final String ERROR = "1";
		
		public static final class Api {
			
			public final static String 
				  _00002 = "00002"    // 系统繁忙，请稍后再试
				, _00003 = "00003"    // 数据为空
				, _00004 = "00004"    // 数据异常
				, _00005 = "00005"    // 数据转换JSON异常
		
				, _00006 = "00006"    // token为空
				, _00007 = "00007"    // token不存在
				
				, _00008 = "00008"    // redis事务失败
				
				, _00009 = "00009"    // 版本号为空
				, _00010 = "00010"    // 数据已跟新
				
				
				, _01000 = "01000"    // 登录账号为空
				
				;
				
		}
	}
}