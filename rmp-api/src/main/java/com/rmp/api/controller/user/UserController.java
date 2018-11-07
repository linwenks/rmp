package com.rmp.api.controller.user;

import static com.rmp.api.util.MsgEnum.*;

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
import com.rmp.api.base.controller.BaseApiController;
import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.model.HeaderBean;
import com.rmp.api.base.model.ReqBean;
import com.rmp.api.base.model.RespBean;
import com.rmp.api.base.util.ReqUtil;
import com.rmp.api.base.util.RespUtil;
import com.rmp.api.model.UserBean;
import com.rmp.api.service.user.UserService;
import com.rmp.api.util.UserUtil;
import com.rmp.api.util.constant.Constant;
import com.rmp.common.http.HttpUtil;
import com.rmp.common.util.JsonUtil;

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
	 * @apiSuccess (data) {Object} userBean 用户 bean
	 * @apiSuccess (data) {String} userBean.token token
	 * @apiSuccess (data) {Integer=0,1} userBean.status 状态<br/>0:未注册<br/>1:已注册
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
		
		String url = Constant.urlJscode2session(jsCode);
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
		if (StringUtils.isEmpty(session_key)) {
			log.error(" error UserController login : session_key is null ");
			throw new AppException(MSG_00002);
		}
		
		// 初始化
		UserBean userBeanTmp = new UserBean();
		userBeanTmp.setWxId(openid);
		userBeanTmp.setNickName(userBean.getNickName());
		userBeanTmp.setHeadPic(userBean.getHeadPic());
		userBeanTmp.setWxSessionKey(session_key);
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
	 * 绑定 微信手机
	 * 
     * @api {post} /api/user/user/bindWxPhone 绑定 微信手机
     * @apiDescription 绑定 微信手机
     * @apiName user_user_bindWxPhone
     * @apiGroup group_user
     * @apiVersion 1.0.0
     * 
     * @apiParam (WxPhoneNumberReqBean) {Object} userBean 微信手机 bean
     * @apiParam (WxPhoneNumberReqBean) {String} userBean.encryptedData 
     * @apiParam (WxPhoneNumberReqBean) {String} userBean.iv 
     * 
     * @apiParamExample {json} 请求-示例: 
	 *		{"header":{"token":"b1e00042ab8a4296aa62c09b28a3c547"},"wxPhoneNumberReqBean":{"encryptedData":"xxxxxxxxxxxxxxx","iv":"yyyyyyyyyyyy"}}
	 * 
	 * @apiSuccess (data) {Object} userBean 用户 bean
	 * @apiSuccess (data) {Long} userBean.phone 手机号
	 * 
     * @apiSuccessExample {json} 成功返回-示例:
	 *		{"header":{"token":"b1e00042ab8a4296aa62c09b28a3c547"},"msgs":[],"msg":{},"state":"0","data":{"userBean":{"phone":"15100000000"}}}
     *
     */
	@RequestMapping(value = "/bindWxPhone")
	@ResponseBody
	public RespBean bindWxPhone(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		
		String token = reqBean.getHeader().getToken();
		
		UserBean userBean = new UserBean();
		userBean.setToken(token);
		userService.exe("bindWxPhone", ImmutableMap.of("userBean", userBean, "wxPhoneNumberReqBean", reqBean.getWxPhoneNumberReqBean()));
		
		UserBean userBeanTmp2 = new UserBean();
		userBeanTmp2.setPhone(userBean.getPhone());
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
	 * @apiSuccess (data) {Object} userBean 用户 bean
	 * @apiSuccess (data) {String} userBean.token token
	 * @apiSuccess (data) {String} userBean.nickName 昵称
     * @apiSuccess (data) {String} userBean.headPic 头像
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
	 * 注册
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
	 * @apiSuccess (data) {Object} userBean 用户 bean
	 * @apiSuccess (data) {String} userBean.token token
	 * @apiSuccess (data) {String} userBean.nickName 昵称
     * @apiSuccess (data) {String} userBean.headPic 头像
	 * 
     * @apiSuccessExample {json} 成功返回-示例:
	 *		{"header":{"token":"b1e00042ab8a4296aa62c09b28a3c547"},"msgs":[],"msg":{},"state":"0","data":{"userBean":{"nickName":"ttt","headPic":"http://wx.com/tx.jpg","token":"b1e00042ab8a4296aa62c09b28a3c547"}}}
     *
     */
	@RequestMapping(value = "/register")
	@ResponseBody
	public RespBean register(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
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
     * @apiName user_user_retrievePwd
     * @apiGroup group_user
     * @apiVersion 1.0.0
     * 
     * @apiParam (UserBean) {Object} userBean 用户 bean
     * @apiParam (UserBean) {String} userBean.loginName 登录账号
     * @apiParam (UserBean) {String} userBean.loginPwd 登录密码
     * 
     * @apiParam (PhoneMsgBean) {Object} phoneMsgBean 手机短信 bean
     * @apiParam (PhoneMsgBean) {String} phoneMsgBean.code 随机码
     * 
     * @apiParamExample {json} 请求-示例: 
			{"userBean":{"loginName":"15123815032","loginPwd":"123456"},"phoneMsgBean": {"code":"777777"}}
	 * 
     * @apiSuccessExample {json} 成功返回-示例:
			{"msgs":[],"msg":{},"state":"0","data":{}}
     *
     */
	@RequestMapping(value = "/retrievePwd")
	@ResponseBody
	public RespBean retrievePwd(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.build(body, request);
		
		Map<String, Object> param = ImmutableMap.of("userBean", reqBean.getUserBean(), "phoneMsgBean", reqBean.getPhoneMsgBean());
		userService.exe("retrievePwd", param);
		return RespUtil.build(request);
	}
	
	
	/**
	 * 修改 头像
	 * 
     * @api {post} /api/user/user/updateHeadPic 修改 头像
     * @apiDescription 修改 头像
     * @apiName user_user_updateHeadPic
     * @apiGroup group_user
     * @apiVersion 1.0.0
     * 
     * @apiParam (UserBean) {Object} userBean 用户 bean
     * @apiParam (UserBean) {String} userBean.headPic 头像
     * 
     * @apiParamExample {json} 请求-示例: 
	 *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"userBean":{"headPic":"/xxx/xxx.jpg"}}
	 * 
     */
	@RequestMapping(value = "/updateHeadPic")
	@ResponseBody
	public RespBean updateHeadPic(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		UserBean userBean = reqBean.getUserBean();
		userBean.setId(UserUtil.getCurrentUserId(request));
		userService.exe("updateHeadPic", userBean);
		return RespUtil.build(request);
	}
	
	/**
	 * 修改 昵称
	 * 
     * @api {post} /api/user/user/updateNickName 修改 昵称
     * @apiDescription 修改 昵称
     * @apiName user_user_updateNickName
     * @apiGroup group_user
     * @apiVersion 1.0.0
     * 
     * @apiParam (UserBean) {Object} userBean 用户 bean
     * @apiParam (UserBean) {String} userBean.nickName 昵称
     * 
     * @apiParamExample {json} 请求-示例: 
	 *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"userBean":{"nickName":"xxx"}}
	 * 
     */
	@RequestMapping(value = "/updateNickName")
	@ResponseBody
	public RespBean updateNickName(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		UserBean userBean = reqBean.getUserBean();
		userBean.setId(UserUtil.getCurrentUserId(request));
		userService.exe("updateNickName", userBean);
		return RespUtil.build(request);
	}
	
	/**
	 * 修改 手机
	 * 
     * @api {post} /api/user/user/updatePhone 修改 手机
     * @apiDescription 修改 昵称
     * @apiName user_user_updatePhone
     * @apiGroup group_user
     * @apiVersion 1.0.0
     * 
     * @apiParam (UserBean) {Object} userBean 用户 bean
     * @apiParam (UserBean) {Long} userBean.phone 手机
     * 
     * @apiParam (PhoneMsgBean) {Object} phoneMsgBean 手机短信 bean
     * @apiParam (PhoneMsgBean) {String{6}} phoneMsgBean.code 随机码
     * 
     * @apiParamExample {json} 请求-示例: 
	 *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"userBean":{"phone":15111111111},"phoneMsgBean":{"code":"666666"}}
	 * 
     */
	@RequestMapping(value = "/updatePhone")
	@ResponseBody
	public RespBean updatePhone(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		UserBean userBean = reqBean.getUserBean();
		userBean.setId(UserUtil.getCurrentUserId(request));
		userService.exe("updatePhone", ImmutableMap.of("userBean", userBean, "phoneMsgBean", reqBean.getPhoneMsgBean()));
		return RespUtil.build(request);
	}
	
	/**
	 * 查询
	 * 
     * @api {post} /api/user/user/get 查询
     * @apiDescription 查询
     * @apiName user_user_get
     * @apiGroup group_user
     * @apiVersion 1.0.0
     * 
     * @apiSuccess (data) {Object} userBean 客户 bean
	 * @apiSuccess (data) {String} userBean.realName 真实姓名
	 * @apiSuccess (data) {Long} userBean.phone 手机
	 * @apiSuccess (data) {Integer} userBean.sex 性别<br/>0:女<br/>1:男
	 * @apiSuccess (data) {String} userBean.headPic 头像
	 * @apiSuccess (data) {Long} userBean.areaId 区域ID
	 * @apiSuccess (data) {String} userBean.areaNameAll 区域全称
     * @apiSuccess (data) {String} userBean.address 地址
     * 
     * @apiSuccessExample {json} 成功返回-示例:
     * 		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"msgs":[],"msg":{},"state":"0","data":{"userBean":{"areaNameAll":"江苏省泰州市","realName":"ttt","phone":15111111111,"sex":0,"headPic":"/xxx/pic.jpg","areaId":321200,"address":"aaaaaaaaaaaaaa"}}}
     */
	@RequestMapping(value = "/get")
	@ResponseBody
	public RespBean get(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqUtil.buildCheckLogin(body, request);
		
		UserBean userBean = new UserBean();
		userBean.setId(UserUtil.getCurrentUserId(request));
		userBean = userService.selectOne(userBean);
		
		UserBean userBeanResult = new UserBean();
		userBeanResult.setRealName(userBean.getRealName());
		userBeanResult.setPhone(userBean.getPhone());
		userBeanResult.setBirthday(userBean.getBirthday());
		userBeanResult.setSex(userBean.getSex());
		userBeanResult.setAreaId(userBean.getAreaId());
		userBeanResult.setAddress(userBean.getAddress());
		userBeanResult.setHeadPic(userBean.getHeadPic());
		UserUtil.assembly(userBeanResult);
		return RespUtil.build(request).putData("userBean", userBeanResult);
	}
}