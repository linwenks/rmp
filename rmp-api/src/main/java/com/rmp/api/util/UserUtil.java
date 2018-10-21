package com.rmp.api.util;

import static com.rmp.api.util.MsgEnum.*;

import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Maps;
import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.util.BaseUtil;
import com.rmp.api.model.UserBean;
import com.rmp.api.util.constant.Constant;
import com.rmp.common.util.PatternUtil;

public class UserUtil extends BaseUtil {
	
	/**
	 * 检查 token
	 * @param token
	 */
	public static void checkToken(String token) {
		token = StringUtils.trim(token);
		if (StringUtils.isEmpty(token)) throw new AppException(MSG_00006);
	}

	/**
	 * 检查 登录名
	 * @param loginName
	 */
	public static void checkLoginName(String loginName) {
		loginName = StringUtils.trim(loginName);
		if (StringUtils.isEmpty(loginName)) throw new AppException(MSG_01000);
		else if (!PatternUtil.matchesMobilePhone(loginName)) throw new AppException(MSG_01001);
	}
	
	/**
	 * 检查 密码
	 * @param loginName
	 */
	public static void checkLoginPwd(String loginPwd) {
		if (StringUtils.isEmpty(loginPwd)) throw new AppException(MSG_01003);
		else if (loginPwd.length() < 6 || loginPwd.length() > 15) throw new AppException(MSG_01004, new String[]{"6", "15"});
		else if (!PatternUtil.matchesEnAndNum(loginPwd)) throw new AppException(MSG_01005);
	}
	
	/**
	 * 检查 用户
	 * @param loginName
	 */
	public static void checkUser(UserBean userBean) {
		if (userBean == null) throw AppException.build(MSG_01002);
		if (Constant.User.Status._0.equals(userBean.getStatus())) throw AppException.build(MSG_01006);
		if (Constant.DELETE_Y.equals(userBean.getIsDelete())) throw AppException.build(MSG_01017);
	}
	
	// ================================================== redis ==================================================
	/**
	 * 用户 key
	 * @param token
	 * @return
	 */
	public static String rKey(String token) {
		return Constant.Redis.User.KEY + token;
	}
	
	/**
	 * 用户 map
	 * @param userBean
	 * @return
	 */
	public static Map<String, String> rMap(UserBean userBean) {
		Map<String, String> beanMap = null;
		if (userBean != null) {
			beanMap = Maps.newHashMap();
			beanMap.put("id", userBean.getId() == null ? "" : userBean.getId().toString());
			beanMap.put("loginName", userBean.getLoginName() == null ? "" : userBean.getLoginName());
			beanMap.put("nickName", userBean.getNickName() == null ? "" : userBean.getNickName());
			beanMap.put("headPic", userBean.getHeadPic() == null ? "" : userBean.getHeadPic());
		}
		return beanMap;
	}
}
