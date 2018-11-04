package com.rmp.api.util;

import static com.rmp.api.util.MsgEnum.*;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

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
	 * 检查 手机
	 * @param phone
	 */
	public static void checkPhone(Long phone) {
		if (phone == null) throw new AppException(MSG_01021);
		String phoneStr = phone.toString();
		if (!PatternUtil.matchesMobilePhone(phoneStr)) throw new AppException(MSG_01022);
	}
	
	/**
	 * 检查 密码
	 * @param loginPwd
	 */
	public static void checkLoginPwd(String loginPwd) {
		if (StringUtils.isEmpty(loginPwd)) throw new AppException(MSG_01003);
		else if (loginPwd.length() < 6 || loginPwd.length() > 15) throw new AppException(MSG_01004, new String[]{"6", "15"});
		else if (!PatternUtil.matchesEnAndNum(loginPwd)) throw new AppException(MSG_01005);
	}
	
	/**
	 * 检查 用户
	 * @param userBean
	 */
	public static void checkUser(UserBean userBean) {
		if (userBean == null) AppException.toThrow(MSG_01002);
		if (Constant.User.Status._0.equals(userBean.getStatus())) AppException.toThrow(MSG_01006);
		if (Constant.DELETE_Y.equals(userBean.getIsDelete())) AppException.toThrow(MSG_01017);
	}
	
	/**
	 * 检查 昵称
	 * @param nickName
	 */
	public static void checkNickName(String nickName) {
		nickName = StringUtils.trim(nickName);
		if (StringUtils.isEmpty(nickName)) throw new AppException(MSG_01018);
		int nickNameLengthMin = 2;
		int nickNameLengthMax = 10;
		if (nickName.length() < nickNameLengthMin || nickName.length() > 10) throw new AppException(MSG_01019, new String[]{String.valueOf(nickNameLengthMin), String.valueOf(nickNameLengthMax)});
	}
	
	/**
	 * 检查 头像
	 * @param nickName
	 */
	public static void checkHeadPic(String nickName) {
		nickName = StringUtils.trim(nickName);
		if (StringUtils.isEmpty(nickName)) throw new AppException(MSG_01020);
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
	
	/**
	 * 用户 bean
	 * @param userMap
	 * @return
	 */
	public static UserBean rBean(Map<String, String> userMap) {
		UserBean userBean = null;
		if (!CollectionUtils.isEmpty(userMap)) {
			userBean = new UserBean();
			userBean.setId(userMap.get("id") != null ? Long.valueOf(userMap.get("id")) : null);
			userBean.setLoginName(userMap.get("loginName") != null ? userMap.get("loginName") : null);
			userBean.setNickName(userMap.get("nickName") != null ? userMap.get("nickName") : null);
			userBean.setHeadPic(userMap.get("headPic") != null ? userMap.get("headPic") : null);
			
		}
		return userBean;
	}
	
	/**
	 * 设置 当前用户
	 * @param userBean
	 * @param request
	 */
	public static void setCurrentUser(UserBean userBean, HttpServletRequest request) {
		request.setAttribute(Constant.CURRENT_LOGIN_USER, userBean);
	}
	
	/**
	 * 获取 当前用户
	 * @param request
	 */
	public static UserBean getCurrentUser(HttpServletRequest request) {
		return (UserBean) request.getAttribute(Constant.CURRENT_LOGIN_USER);
	}
	
	/**
	 * 获取 当前用户 ID
	 * @param request
	 */
	public static Long getCurrentUserId(HttpServletRequest request) {
		UserBean userBean = getCurrentUser(request);
		if (userBean != null) {
			return userBean.getId();
		}
		return null;
	}
}
