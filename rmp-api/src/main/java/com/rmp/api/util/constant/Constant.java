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
	
	// redis
	public static final class Redis {
		public static final class Sharded1 {
			public static final String NAME1 = "redis1";
		}
		public static final class User {
			public static final int INDEX = 1;
			public static final String KEY = "rmp_";
			public static final int SECONDS = 24 * 60 * 60;
		}
	}

	// 当前登录用户
	public static final String CURRENT_LOGIN_USER = "CURRENT_LOGIN_USER";
	
	// 当前请求header
	public static final String CURRENT_REQUEST_HEADER = "CURRENT_REQUEST_HEADER";
	
	// 是否删除
	public final static Integer DELETE_N = 0;    // 否
	public final static Integer DELETE_Y = 1;    // 是
	
	// 消息
	public static final class Msg {
		
		public static final String SUCCESS = "0";
		public static final String ERROR = "1";
	}
	
	// 手机短信
	public static final class PhoneMsg {
		
		public static final int SEND_INTERVAL_TIME = 1 * 60;    // 发送短信间隔时间（秒）
		public static final int EFFECTIVE_TIME = 10 * 60;    // 短信有效时间（秒）
		public static final int LENGTH = 6;    // 验证码长度
		
		public static final class Type {
			
			public static final int _1 = 1    // 注册
								, _2 = 2    // 找回密码
								;
		}
		
		public static final class Status {
			
			public static final int _0 = 0    // 未使用
								, _1 = 1;    // 已使用
		}
	}
	
	
	// 用户
	public static final class User {
		// 状态
		public static final class Status {
			public final static Integer 
				  _0 = 0    // 未注册
				, _1 = 1    // 已注册
				;
				
		}
	}
}