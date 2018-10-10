package com.rmp.api.util;

import static com.rmp.api.util.constant.Constant.Msg.Api.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息 工具
 * @author linw
 *
 */
public class MsgUtil {
	
	@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	private static final Map<String, String> map = new HashMap() {{
		put(_00002, "系统繁忙，请稍候再试！");
		put(_00003, "数据为空");
		put(_00004, "数据异常");
		put(_00005, "数据转换JSON异常");
		put(_00006, "token为空");
		put(_00007, "token不存在");
		put(_00008, "redis事务失败");
		put(_00009, "版本号为空");
		put(_00010, "数据已跟新");
		
		// 登录注册。。。
		put(_01000, "账号为空");
		
	}};
	
	/**
	 * 获取 value
	 * @param code
	 * @return
	 */
	public static String getJsonValue(String code) {
		return map.getOrDefault(code, "");
	}
}