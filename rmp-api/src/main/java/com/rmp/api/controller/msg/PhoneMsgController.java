package com.rmp.api.controller.msg;

import static com.rmp.api.util.MsgEnum.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rmp.api.base.controller.BaseApiController;
import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.model.ReqBean;
import com.rmp.api.base.model.RespBean;
import com.rmp.api.base.util.ReqUtil;
import com.rmp.api.base.util.RespUtil;
import com.rmp.api.model.PhoneMsgBean;
import com.rmp.api.service.msg.PhoneMsgService;
import com.rmp.api.util.constant.Constant;

/**
 * 手机短信 json controller
 * @author linw
 *
 */
@Controller("api_msg_PhoneMsgController")
@RequestMapping(value = "/api/msg/phoneMsg", method = RequestMethod.POST, produces="application/json;charset=utf-8")
public class PhoneMsgController extends BaseApiController {


	@Autowired
	private PhoneMsgService phoneMsgService;
	
	/**
	 * 发送短信 注册
	 * 
     * @api {post} /api/msg/phoneMsgJson/sendRegister 发送短信 注册
     * @apiDescription 发送短信 注册
     * @apiName msg_phoneMsg_sendRegister
     * @apiGroup group_user
     * @apiVersion 1.0.0
     * 
     * @apiParam (PhoneMsgBean) {Object} phoneMsgBean 手机短信 bean
     * @apiParam (PhoneMsgBean) {Long} phoneMsgBean.phone 手机号
     * 
     * @apiParamExample {json} 请求-示例: 
			{"header":{"token":"aaaaa"},"phoneMsgBean":{"phone":15123815000}}
	 * 
     * @apiSuccessExample {json} 成功返回-示例:
     *		{"header":{"token":"aaaaa"},"msgs":[],"msg":{}}
     */
	@RequestMapping(value = "/sendRegister")
	@ResponseBody
	public RespBean sendRegister(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckToken(body, request);
		
		PhoneMsgBean phoneMsgBean = reqBean.getPhoneMsgBean();
		if (phoneMsgBean == null) throw AppException.build(MSG_00003);
		phoneMsgBean.setType(Constant.PhoneMsg.Type._1);
		phoneMsgService.exe("send", phoneMsgBean);
		return RespUtil.build(request);
	}
	
	/**
	 * 发送短信 找回密码
	 * 
     * @api {post} /api/msg/phoneMsgJson/sendRetrievePwd 发送短信 找回密码
     * @apiDescription 发送短信 注找回密码
     * @apiName msg_phoneMsg_sendRetrievePwd
     * @apiGroup group_user
     * @apiVersion 1.0.0
     * 
     * @apiParam (PhoneMsgBean) {Object} phoneMsgBean 手机短信 bean
     * @apiParam (PhoneMsgBean) {Long} phoneMsgBean.phone 手机号
     * 
     * @apiParamExample {json} 请求-示例: 
			{"header":{"token":"aaaaa"},"phoneMsgBean":{"phone":15123815000}}
	 * 
     * @apiSuccessExample {json} 成功返回-示例:
     *		{"header":{"token":"aaaaa"},"msgs":[],"msg":{}}
     */
	@RequestMapping(value = "/sendRetrievePwd")
	@ResponseBody
	public RespBean sendRetrievePwd(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckToken(body, request);
		
		PhoneMsgBean phoneMsgBean = reqBean.getPhoneMsgBean();
		if (phoneMsgBean == null) throw AppException.build(MSG_00003);
		phoneMsgBean.setType(Constant.PhoneMsg.Type._2);
		phoneMsgService.exe("send", phoneMsgBean);
		return RespUtil.build(request);
	}
}