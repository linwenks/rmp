package com.rmp.api.util.constant;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rmp.api.util.SysCodeUtil;
import com.rmp.common.util.DateUtil;

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
	
	public final static String WX_APPID = "wxf7012b6ecbc653fa";
	public final static String WX_SECRET = "adf4fbac3190f3125c4e807f4908084d";
	
	public static String urlJscode2session(String jsCode) {
		return "https://api.weixin.qq.com/sns/jscode2session?appid=" + WX_APPID + "&secret=" + WX_SECRET + "&js_code=" + jsCode + "&grant_type=authorization_code";
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
	
	
	public static final String UPLOAD_TOP_PATH = "UPLOAD_TOP_PATH";    // 上传顶级路径
	public static final String UPLOAD_USER_HEAD_PIC_PATH = "UPLOAD_USER_HEAD_PIC_PATH";    // 上传用户头像路径
	public static final String UPLOAD_USER_HEAD_PIC_PATH_TMP = "UPLOAD_USER_HEAD_PIC_PATH_TMP";    // 上传用户头像路径临时
	public static final String UPLOAD_CUSTOMER_HEAD_PIC_PATH = "UPLOAD_CUSTOMER_HEAD_PIC_PATH";    // 上传客户头像路径
	public static final String UPLOAD_CUSTOMER_HEAD_PIC_PATH_TMP = "UPLOAD_CUSTOMER_HEAD_PIC_PATH_TMP";    // 上传客户头像路径临时
	
	// 图片 域名
	private static final String IMG_DOMAIN = "IMG_DOMAIN";
	public static String imgDomain() {
		return SysCodeUtil.getValue(IMG_DOMAIN);
	}
	
	// 上传
	public static final String UPLOAD_TMP = "/tmp";
	
	public static String uploadTopPath() {
		return SysCodeUtil.getValue(UPLOAD_TOP_PATH);
	}
	
	public static String uploadPath(String path, String pattern) {
		String str1 = SysCodeUtil.getValue(UPLOAD_TOP_PATH, path);
		pattern = StringUtils.trim(pattern);
		String str2 = "";
		if (!StringUtils.isEmpty(pattern)) {
			str2 = "/" + DateUtil.formatDate(DateUtil.now(), pattern);
		}
		return str1 + str2;
	}
	
	// 是否删除
	public final static Integer DELETE_N = 0;    // 否
	public final static Integer DELETE_Y = 1;    // 是
	
	// 消息
	public static final class Msg {
		
		public static final String SUCCESS = "0";
		public static final String ERROR = "1";
	}
	
	// 系统 code
	public static final class SysCode {
		
		public static final String CUSTOMER	= "CUSTOMER";    // 客户

		public static final String CUSTOMER_RELATION = "CUSTOMER_RELATION";    // 关系
		public static final String CUSTOMER_RELATION_RELATIONSHIP = "CUSTOMER_RELATION_RELATIONSHIP";    // 关系
		public static final String CUSTOMER_RELATION_INTIMACY = "CUSTOMER_RELATION_INTIMACY";    // 亲密
		public static final String CUSTOMER_RELATION_IMPORTANCE = "CUSTOMER_RELATION_IMPORTANCE";    // 重要

		public static final String CUSTOMER_PROBLEM	= "CUSTOMER_PROBLEM";    // 问题
		public static final String CUSTOMER_PROBLEM_HEALTH = "CUSTOMER_PROBLEM_HEALTH";    // 健康
		public static final String CUSTOMER_PROBLEM_LIFE = "CUSTOMER_PROBLEM_LIFE";    // 生活

		public static final String CUSTOMER_HOBBY = "CUSTOMER_HOBBY";    // 兴趣爱好
		public static final String CUSTOMER_HOBBY_INTEREST = "CUSTOMER_HOBBY_INTEREST";    // 兴趣
		public static final String CUSTOMER_HOBBY_DIET = "CUSTOMER_HOBBY_DIET";    // 	饮食
		public static final String CUSTOMER_HOBBY_TASTE = "CUSTOMER_HOBBY_TASTE";    // 口味

		public static final String CUSTOMER_JOB = "CUSTOMER_JOB";    // 工作
		public static final String CUSTOMER_JOB_INDUSTRY = "CUSTOMER_JOB_INDUSTRY";    // 行业
		public static final String CUSTOMER_JOB_POSITION = "CUSTOMER_JOB_POSITION";    // 职位
		
		public static final String CUSTOMER_FAMILY = "CUSTOMER_FAMILY";    // 家庭
		public static final String CUSTOMER_FAMILY_RELATIONSHIP = "CUSTOMER_FAMILY_RELATIONSHIP";    // 关系
		
		public static final String CUSTOMER_MEMORIAL_DAY = "CUSTOMER_MEMORIAL_DAY";    // 纪念日
		public static final String CUSTOMER_MEMORIAL_DAY_OCCUR_TYPE = "CUSTOMER_MEMORIAL_DAY_OCCUR_TYPE";    // 发生类型
		public static final String CUSTOMER_MEMORIAL_DAY_ADVANCE_TYPE = "CUSTOMER_MEMORIAL_DAY_ADVANCE_TYPE";    // 提前类型
		
		public static final String CUSTOMER_MAINTAIN = "CUSTOMER_MAINTAIN";    // 维护设置
		public static final String CUSTOMER_MAINTAIN_MAINTAIN = "CUSTOMER_MAINTAIN_MAINTAIN";    // 维护
		
	}
	
	// 手机短信
	public static final class PhoneMsg {
		
		public static final int SEND_INTERVAL_TIME = 1 * 60;    // 发送短信间隔时间（秒）
		public static final int EFFECTIVE_TIME = 10 * 60;    // 短信有效时间（秒）
		public static final int LENGTH = 6;    // 验证码长度
		
		public static final class Type {
			
			public static final int _1 = 1    // 注册
								, _2 = 2    // 找回密码
								, _3 = 3    // 修改手机号
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
	
	// 用户 客户
	public static final class Customer {
		
		public static final String HEAD_PIC = "/head_pic/default.jpg";    // 头像 默认
	}
}