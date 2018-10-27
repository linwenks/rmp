package com.rmp.api.controller.customer;

import static com.rmp.api.util.MsgEnum.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rmp.api.base.controller.BaseApiController;
import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.model.ReqBean;
import com.rmp.api.base.model.RespBean;
import com.rmp.api.base.util.ReqUtil;
import com.rmp.api.base.util.RespUtil;
import com.rmp.api.model.CustomerHobbyBean;
import com.rmp.api.service.customer.CustomerHobbyService;
import com.rmp.api.util.UserUtil;

/**
 * 客户 兴趣爱好 json controller
 * @author linw
 *
 */
@RestController("api_customer_CustomerHobbyController")
@RequestMapping(value = "/api/customer/hobby", method = RequestMethod.POST, produces="application/json;charset=utf-8")
public class CustomerHobbyController extends BaseApiController {

	@Autowired
	private CustomerHobbyService customerHobbyService;
	
	/**
	 * 客户 兴趣爱好 修改
	 * 
     * @api {post} /api/customer/hobby/update 客户 兴趣爱好 修改
     * @apiDescription 客户 兴趣爱好 修改
     * @apiName customer_hobby_update
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"customerHobbyBean":{"customerId":2,"interest":"1","diet":"1,3","taste":null}}
     * 
     */
	@RequestMapping(value = "/update")
	public RespBean update(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		CustomerHobbyBean customerHobbyBean = reqBean.getCustomerHobbyBean();
		if (customerHobbyBean == null) AppException.toThrow(MSG_00003);
		customerHobbyBean.setUserId(UserUtil.getCurrentUserId(request));
		customerHobbyService.exe("update", customerHobbyBean);
		return RespUtil.build(request);
	}
}