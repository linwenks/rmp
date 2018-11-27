package com.rmp.api.controller.customer;

import static com.rmp.api.util.MsgEnum.*;
import static com.rmp.api.util.constant.Constant.SysCode.*;

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
import com.rmp.api.model.CustomerMaintainBean;
import com.rmp.api.service.customer.CustomerMaintainService;
import com.rmp.api.service.customer.CustomerService;
import com.rmp.api.util.CustomerMaintainUtil;
import com.rmp.api.util.SysCodeUtil;
import com.rmp.api.util.UserUtil;

/**
 * 客户 维护设置 json controller
 * @author linw
 *
 */
@RestController("api_customer_CustomerMaintainController")
@RequestMapping(value = "/api/customer/maintain", method = RequestMethod.POST, produces="application/json;charset=utf-8")
public class CustomerMaintainController extends BaseApiController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private CustomerMaintainService customerMaintainService;
	
	/**
	 * 客户 维护设置 配置
	 * 
     * @api {post} /api/customer/maintain/config 客户 维护设置 配置
     * @apiDescription 客户 维护设置 配置
     * @apiName customer_maintain_config
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"}}
     * 
     * @apiSuccess (data) {List} maintainCodeList 维护设置 code list
     * 
     * @apiSuccessExample {json} 成功返回-示例:
     * 		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"msgs":[],"msg":{},"state":"0","data":{"maintainCodeList":[{"id":168,"key":"0","value":"手动","pid":167,"sort":0},{"id":169,"key":"1","value":"自动","pid":167,"sort":1}]}}
     */
	@RequestMapping(value = "/config")
	public RespBean config(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqUtil.buildCheckLogin(body, request);
		return RespUtil.build(request)
				.putData("maintainCodeList", SysCodeUtil.getChildSimple(CUSTOMER, CUSTOMER_MAINTAIN, CUSTOMER_MAINTAIN_MAINTAIN));
	}
	
	/**
	 * 客户 维护设置 查询
	 * 
     * @api {post} /api/customer/maintain/get 客户 维护设置 查询
     * @apiDescription 客户 维护设置 查询
     * @apiName customer_maintain_get
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParam (CustomerMaintainBean) {Object} customerMaintainBean 工作 bean
     * @apiParam (CustomerMaintainBean) {Long} customerMaintainBean.customerId 客户ID
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"customerMaintainBean":{"customerId":2}}
     * 
     * @apiSuccess (data) {Object} customerMaintainBean 客户 bean
	 * @apiSuccess (data) {Integer} customerMaintainBean.maintain 维护设置
	 * @apiSuccess (data) {String} customerMaintainBean.maintainValue 维护设置 值
     * 
     * @apiSuccessExample {json} 成功返回-示例:
     * 		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"msgs":[],"msg":{},"state":"0","data":{"customerMaintainBean":{"maintainValue":"手动","maintain":0}}}
     */
	@RequestMapping(value = "/get")
	public RespBean get(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		CustomerMaintainBean customerMaintainBean = reqBean.getCustomerMaintainBean();
		if (customerMaintainBean == null) AppException.toThrow(MSG_00003);
		Long customerId = customerMaintainBean.getCustomerId();
		if (customerId == null) AppException.toThrow(MSG_00003);
		
		CustomerBean customerBean = CustomerBean.builder().id(customerId).userId(UserUtil.getCurrentUserId(request)).build();
		Long count = customerService.selectCount(customerBean);
		if (count <= 0) AppException.toThrow(MSG_00003);
		
		CustomerMaintainBean customerMaintainBeanResult = null;
		CustomerMaintainBean customerMaintainBeanTmp = customerMaintainService.selectOne(customerMaintainBean);
		if (customerMaintainBeanTmp != null) {
			customerMaintainBeanResult = CustomerMaintainBean.builder()
			.maintain(customerMaintainBeanTmp.getMaintain())
			.build();
			CustomerMaintainUtil.assembly(customerMaintainBeanResult);
		}
		return RespUtil.build(request).putData("customerMaintainBean", customerMaintainBeanResult);
	}
	
	/**
	 * 客户 维护设置 修改
	 * 
     * @api {post} /api/customer/maintain/update 客户 维护设置 修改
     * @apiDescription 客户 维护设置 修改
     * @apiName customer_maintain_update
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParam (CustomerMaintainBean) {Object} customerMaintainBean 工作 bean
     * @apiParam (CustomerMaintainBean) {Long} customerMaintainBean.customerId 客户ID
	 * @apiParam (CustomerMaintainBean) {Integer} customerMaintainBean.maintain 维护
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"customerMaintainBean":{"maintain":0}}
     * 
     */
	@RequestMapping(value = "/update")
	public RespBean update(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		CustomerMaintainBean customerMaintainBean = reqBean.getCustomerMaintainBean();
		if (customerMaintainBean == null) AppException.toThrow(MSG_00003);
		customerMaintainBean.setUserId(UserUtil.getCurrentUserId(request));
		customerMaintainService.exe("update", customerMaintainBean);
		return RespUtil.build(request);
	}
}