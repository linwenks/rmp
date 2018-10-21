package com.rmp.api.controller.user;

import static com.rmp.api.util.MsgEnum.*;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.rmp.api.base.controller.BaseApiController;
import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.model.HeaderBean;
import com.rmp.api.base.model.ReqBean;
import com.rmp.api.base.model.RespBean;
import com.rmp.api.base.util.ReqUtil;
import com.rmp.api.base.util.RespUtil;
import com.rmp.api.model.AreaBean;
import com.rmp.api.model.UserBean;
import com.rmp.api.service.user.UserService;
import com.rmp.api.util.AreaUtil;
import com.rmp.api.util.constant.Constant;
import com.rmp.common.http.HttpUtil;
import com.rmp.common.util.JsonUtil;
import com.rmp.info.model.User;

/**
 * 地域 json controller
 * @author linw
 *
 */
@Controller("api_user_UserController")
@RequestMapping(value = "/api/user/user", method = RequestMethod.POST, produces="application/json;charset=utf-8")
public class UserController extends BaseApiController {

	@Autowired
	private UserService userService;
	
	/**
	 * 初始化
	 * 
     * @api {post} /api/user/user/initialize 初始化
     * @apiDescription 初始化
     * @apiName user_user_initialize
     * @apiGroup group_user
     * @apiVersion 1.0.0
     * 
     * @apiParam (UserBean) {Object} userBean 用户 bean
     * @apiParam (UserBean) {String} userBean.jsCode 微信jsCode
     * @apiParam (UserBean) {String} userBean.nickName 昵称
     * @apiParam (UserBean) {String} userBean.headPic 头像
     * 
     * @apiParamExample {json} 请求-示例: 
	 *		{"userBean":{"nickName":"哈哈","headPic":"http://wx.com/tx.jpg","jsCode":"aaaaaa"}}
	 * 
	 * @apiSuccess (UserBean) {Object} userBean 用户 bean
	 * @apiSuccess (UserBean) {String} userBean.token token
	 * @apiSuccess (UserBean) {Integer} userBean.status 状态<br/>0:未注册<br/>1:已注册
	 * 
     * @apiSuccessExample {json} 成功返回-示例:
	 *		{"header":{"token":"b1e00042ab8a4296aa62c09b28a3c547"},"msgs":[],"msg":{},"state":"0","data":{"userBean":{"token":"b1e00042ab8a4296aa62c09b28a3c547","status":0}}}
     *
     */
	@RequestMapping(value = "/initialize")
	@ResponseBody
	public RespBean initialize(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.build(body, request);
		UserBean userBean = reqBean.getUserBean();
		if (userBean == null) throw new AppException(MSG_00003);
		
		String jsCode = userBean.getJsCode();
		if (StringUtils.isEmpty(jsCode)) throw new AppException(MSG_00003);
		
		String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wx8fea9abfd68d6cbf&secret=1b49f1a276222ace79c4c911b1395089&js_code=" + jsCode + "&grant_type=authorization_code";
		HttpUtil httpUtil = new HttpUtil();
		httpUtil.setConnectTimeout(5000);
		httpUtil.setSocketTimeout(5000);
		Map<String, Object> result = httpUtil.sendGetRequest(url);
		Integer statusCode = (Integer) result.get("statusCode");
		if (statusCode == null || statusCode.intValue() != 200) {
			log.error(" error UserController login : statusCode[" + statusCode + "]");
			throw new AppException(MSG_00002);
		}
		String responseContent = (String) result.get("responseContent");
		if (responseContent == null) {
			log.error(" error UserController login : responseContent[" + responseContent + "]");
			throw new AppException(MSG_00002);
		}
		Map<String, Object> responseContentMap = JsonUtil.fromJson(responseContent, Map.class);
		String session_key = (String) responseContentMap.get("session_key");
		String openid = (String) responseContentMap.get("openid");
		if (StringUtils.isEmpty(openid)) {
			log.error(" error UserController login : openid is null ");
			throw new AppException(MSG_00002);
		}
		
		// 初始化
		UserBean userBeanTmp = new UserBean();
		userBeanTmp.setWxId(openid);
		userBeanTmp.setNickName(userBean.getNickName());
		userBeanTmp.setHeadPic(userBean.getHeadPic());
		userService.exe("initialize", userBeanTmp);
		
		String token = userBeanTmp.getToken();
		
		HeaderBean headerBean = reqBean.getHeader();
		if (headerBean == null) headerBean = new HeaderBean();
		headerBean.setToken(token);
		request.setAttribute(Constant.CURRENT_REQUEST_HEADER, headerBean);
		
		UserBean userBeanTmp2 = new UserBean();
		userBeanTmp2.setStatus(userBeanTmp.getStatus());
		userBeanTmp2.setToken(token);
		return RespUtil.build(request).putData("userBean", userBeanTmp2);
	}
	
	/**
	 * 登录
	 * 
     * @api {post} /api/user/user/login 登录
     * @apiDescription 登录
     * @apiName user_user_login
     * @apiGroup group_user
     * @apiVersion 1.0.0
     * 
     * @apiParam (UserBean) {Object} userBean 用户 bean
     * @apiParam (UserBean) {String} userBean.loginName 登录名
     * @apiParam (UserBean) {String} userBean.loginPwd 登录密码
     * 
     * @apiParamExample {json} 请求-示例: 
	 *		{"userBean":{"loginName":"15123815032","loginPwd":"123456"}}
	 * 
	 * @apiSuccess (UserBean) {Object} userBean 用户 bean
	 * @apiSuccess (UserBean) {String} userBean.token token
	 * @apiSuccess (UserBean) {String} userBean.nickName 昵称
     * @apiSuccess (UserBean) {String} userBean.headPic 头像
	 * 
     * @apiSuccessExample {json} 成功返回-示例:
	 *		{"header":{"token":"a074f3b898d24b5285b96a93f85d9edf"},"msgs":[],"msg":{},"state":"0","data":{"userBean":{"nickName":"ttt","headPic":"http://wx.com/tx.jpg","token":"a074f3b898d24b5285b96a93f85d9edf"}}}
     *
     */
	@RequestMapping(value = "/login")
	@ResponseBody
	public RespBean login(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.build(body, request);
		
		UserBean userBean = reqBean.getUserBean();
		userService.exe("login", userBean);
		
		String token = userBean.getToken();

		HeaderBean headerBean = reqBean.getHeader();
		if (headerBean == null) headerBean = new HeaderBean();
		headerBean.setToken(token);
		request.setAttribute(Constant.CURRENT_REQUEST_HEADER, headerBean);
		
		UserBean userBeanTmp = new UserBean();
		userBeanTmp.setNickName(userBean.getNickName());
		userBeanTmp.setHeadPic(userBean.getHeadPic());
		userBeanTmp.setToken(token);
		return RespUtil.build(request).putData("userBean", userBeanTmp);
	}
	
	/**
	 * 登录
	 * 
     * @api {post} /api/user/user/register 注册
     * @apiDescription 注册
     * @apiName user_user_register
     * @apiGroup group_user
     * @apiVersion 1.0.0
     * 
     * @apiParam (UserBean) {Object} userBean 用户 bean
     * @apiParam (UserBean) {String{11}} userBean.loginName 登录名
     * @apiParam (UserBean) {String{6..15}} userBean.loginPwd 登录密码
     * 
     * @apiParam (PhoneMsgBean) {Object} phoneMsgBean 手机短信 bean
     * @apiParam (PhoneMsgBean) {String{6}} phoneMsgBean.code 随机码
     * 
     * @apiParamExample {json} 请求-示例: 
	 *		{"header":{"token":"b1e00042ab8a4296aa62c09b28a3c547"},"userBean":{"loginName":"15123815032","loginPwd":"123456"},"phoneMsgBean":{"code":"666666"}}
	 * 
	 * @apiSuccess (UserBean) {Object} userBean 用户 bean
	 * @apiSuccess (UserBean) {String} userBean.token token
	 * @apiSuccess (UserBean) {String} userBean.nickName 昵称
     * @apiSuccess (UserBean) {String} userBean.headPic 头像
	 * 
     * @apiSuccessExample {json} 成功返回-示例:
	 *		{"header":{"token":"b1e00042ab8a4296aa62c09b28a3c547"},"msgs":[],"msg":{},"state":"0","data":{"userBean":{"nickName":"ttt","headPic":"http://wx.com/tx.jpg","token":"b1e00042ab8a4296aa62c09b28a3c547"}}}
     *
     */
	@RequestMapping(value = "/register")
	@ResponseBody
	public RespBean register(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckToken(body, request);
		String token = reqBean.getHeader().getToken();
		
		UserBean userBean = reqBean.getUserBean();
		userBean.setToken(token);
		userService.exe("register", ImmutableMap.of("userBean", userBean, "phoneMsgBean", reqBean.getPhoneMsgBean()));
		
		UserBean userBeanTmp = new UserBean();
		userBeanTmp.setNickName(userBean.getNickName());
		userBeanTmp.setHeadPic(userBean.getHeadPic());
		userBeanTmp.setToken(token);
		return RespUtil.build(request).putData("userBean", userBeanTmp);
	}
	
	/**
	 * 找回密码
	 * 
     * @api {post} /api/user/user/retrievePwd 找回密码
     * @apiDescription 找回密码
     * @apiName user_retrievePwd
     * @apiGroup group_user
     * @apiVersion 1.0.0
     * 
     * @apiParam (userBean) {Object} userBean 用户 bean
     * @apiParam (userBean) {String} userBean.loginName 登录账号
     * @apiParam (userBean) {String} userBean.loginPwd 登录密码
     * 
     * @apiParam (PhoneMsgBean) {Object} phoneMsgBean 手机短信 bean
     * @apiParam (PhoneMsgBean) {String} phoneMsgBean.code 随机码
     * 
     * @apiParamExample {json} 请求-示例: 
			{"header":{"token":"aaaaa"},"userBean":{"loginName":"15123815032","loginPwd":"123456"},"phoneMsgBean": {"code":"4985"}}
	 * 
     * @apiSuccessExample {json} 成功返回-示例:
			{"msgList":[],"state":"0","dataMap":{}}
     *
     */
	@RequestMapping(value = "/retrievePwd")
	@ResponseBody
	public RespBean retrievePwd(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckToken(body, request);

		UserBean userBean = reqBean.getUserBean();
		userBean.setToken(reqBean.getHeader().getToken());
		
		Map<String, Object> param = ImmutableMap.of("userBean", userBean, "phoneMsgBean", reqBean.getPhoneMsgBean());
		userService.exe("retrievePwd", param);
		return RespUtil.build(request);
	}
}