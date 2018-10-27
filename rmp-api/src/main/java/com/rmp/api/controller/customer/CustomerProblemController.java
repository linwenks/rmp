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
import com.rmp.api.model.CustomerBean;
import com.rmp.api.model.CustomerProblemBean;
import com.rmp.api.service.customer.CustomerProblemService;
import com.rmp.api.service.customer.CustomerService;
import com.rmp.api.util.UserUtil;

/**
 * 客户 可能问题 json controller
 * @author linw
 *
 */
@RestController("api_customer_CustomerProblemController")
@RequestMapping(value = "/api/customer/problem", method = RequestMethod.POST, produces="application/json;charset=utf-8")
public class CustomerProblemController extends BaseApiController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private CustomerProblemService customerProblemService;
	
	/**
	 * 客户 可能问题 查询
	 * 
     * @api {post} /api/customer/problem/get 客户 可能问题 查询
     * @apiDescription 客户 可能问题 查询
     * @apiName customer_problem_get
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"customerProblemBean":{"customerId":2}}
     * 
     * @apiSuccessExample {json} 成功返回-示例:
     * 		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"msgs":[],"msg":{},"state":"0","data":{"customerProblemBean":{"health":"1","life":"1,3","remark":"xxxxxxxxxTTT"}}}
     */
	@RequestMapping(value = "/get")
	public RespBean get(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		CustomerProblemBean customerProblemBean = reqBean.getCustomerProblemBean();
		if (customerProblemBean == null) AppException.toThrow(MSG_00003);
		Long customerId = customerProblemBean.getCustomerId();
		if (customerId == null) AppException.toThrow(MSG_00003);
		
		CustomerBean customerBean = new CustomerBean();
		customerBean.setId(customerId);
		customerBean.setUserId(UserUtil.getCurrentUserId(request));
		Long count = customerService.selectCount(customerBean);
		if (count <= 0) AppException.toThrow(MSG_00003);
		
		CustomerProblemBean customerProblemBeanResult = null;
		CustomerProblemBean customerProblemBeanTmp = customerProblemService.selectOne(customerProblemBean);
		if (customerProblemBeanTmp != null) {
			customerProblemBeanResult = new CustomerProblemBean();
			customerProblemBeanResult.setHealth(customerProblemBeanTmp.getHealth());
			customerProblemBeanResult.setLife(customerProblemBeanTmp.getLife());
			customerProblemBeanResult.setRemark(customerProblemBeanTmp.getRemark());
		}
		return RespUtil.build(request).putData("customerProblemBean", customerProblemBeanResult);
	}
	
	/**
	 * 客户 可能问题 修改
	 * 
     * @api {post} /api/customer/problem/update 客户 可能问题 修改
     * @apiDescription 客户 可能问题 修改
     * @apiName customer_problem_update
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"customerProblemBean":{"customerId":2,"health":"1","life":"1,3","remark":"xxxxxxxxxTTT"}}
     * 
     */
	@RequestMapping(value = "/update")
	public RespBean update(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		CustomerProblemBean customerProblemBean = reqBean.getCustomerProblemBean();
		if (customerProblemBean == null) AppException.toThrow(MSG_00003);
		customerProblemBean.setUserId(UserUtil.getCurrentUserId(request));
		customerProblemService.exe("update", customerProblemBean);
		return RespUtil.build(request);
	}
}