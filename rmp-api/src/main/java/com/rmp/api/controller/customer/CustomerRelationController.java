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
import com.rmp.api.model.CustomerRelationBean;
import com.rmp.api.service.customer.CustomerRelationService;
import com.rmp.api.service.customer.CustomerService;
import com.rmp.api.util.CustomerRelationUtil;
import com.rmp.api.util.SysCodeUtil;
import com.rmp.api.util.UserUtil;

/**
 * 客户 关系 json controller
 * @author linw
 *
 */
@RestController("api_customer_CustomerRelationController")
@RequestMapping(value = "/api/customer/relation", method = RequestMethod.POST, produces="application/json;charset=utf-8")
public class CustomerRelationController extends BaseApiController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private CustomerRelationService customerRelationService;
	
	/**
	 * 客户 关系 配置
	 * 
     * @api {post} /api/customer/relation/config 客户 关系 配置
     * @apiDescription 客户 关系 配置
     * @apiName customer_relation_config
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"}}
     * 
     * @apiSuccess (data) {List} importanceCodeList 关系 code list
     * @apiSuccess (data) {List} intimacyCodeList 亲密 code list
     * @apiSuccess (data) {List} relationshipCodeList 重要 code list
     * 
     * @apiSuccessExample {json} 成功返回-示例:
     * 		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"msgs":[],"msg":{},"state":"0","data":{"intimacyCodeList":[{"id":14,"key":"0","value":"不详","pid":13,"sort":0},{"id":15,"key":"1","value":"很亲近","pid":13,"sort":1},{"id":16,"key":"2","value":"一般亲近","pid":13,"sort":2},{"id":17,"key":"3","value":"正常交往","pid":13,"sort":3},{"id":18,"key":"4","value":"点头之交","pid":13,"sort":4}],"relationshipCodeList":[{"id":4,"key":"0","value":"其他","pid":3,"sort":0},{"id":5,"key":"1","value":"家人","pid":3,"sort":1},{"id":6,"key":"2","value":"亲戚","pid":3,"sort":2},{"id":7,"key":"3","value":"朋友","pid":3,"sort":3},{"id":8,"key":"4","value":"同学","pid":3,"sort":4},{"id":9,"key":"5","value":"同事","pid":3,"sort":5},{"id":10,"key":"6","value":"客户","pid":3,"sort":6},{"id":11,"key":"7","value":"熟人","pid":3,"sort":7},{"id":12,"key":"8","value":"陌生人","pid":3,"sort":8}],"importanceCodeList":[{"id":20,"key":"0","value":"不重要","pid":19,"sort":0},{"id":21,"key":"1","value":"重要","pid":19,"sort":1},{"id":22,"key":"2","value":"非常重要（vip）","pid":19,"sort":2}]}}
     */
	@RequestMapping(value = "/config")
	public RespBean config(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqUtil.buildCheckLogin(body, request);
		return RespUtil.build(request)
				.putData("importanceCodeList", SysCodeUtil.getListSimple(CUSTOMER_RELATION_IMPORTANCE))
				.putData("intimacyCodeList", SysCodeUtil.getListSimple(CUSTOMER_RELATION_INTIMACY))
				.putData("relationshipCodeList", SysCodeUtil.getListSimple(CUSTOMER_RELATION_RELATIONSHIP));
	}
	
	/**
	 * 客户 关系 查询
	 * 
     * @api {post} /api/customer/relation/get 客户 关系 查询
     * @apiDescription 客户 关系 查询
     * @apiName customer_relation_get
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParam (CustomerRelationBean) {Object} customerRelationBean 工作 bean
     * @apiParam (CustomerRelationBean) {Long} customerRelationBean.customerId 客户ID
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"customerRelationBean":{"customerId":2}}
     * 
     * @apiSuccess (data) {Object} customerRelationBean 客户 bean
	 * @apiSuccess (data) {Integer} customerRelationBean.importance 关系
	 * @apiSuccess (data) {String} customerRelationBean.importanceValue 关系 值
	 * @apiSuccess (data) {Integer} customerRelationBean.intimacy 亲密
	 * @apiSuccess (data) {String} customerRelationBean.intimacyValue 亲密 值
	 * @apiSuccess (data) {Integer} customerRelationBean.relationship 重要
	 * @apiSuccess (data) {String} customerRelationBean.relationshipValue 重要 值
     * 
     * @apiSuccessExample {json} 成功返回-示例:
     * 		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"msgs":[],"msg":{},"state":"0","data":{"customerRelationBean":{"relationshipValue":"其他","intimacyValue":"不详","importanceValue":"不重要","relationship":0,"intimacy":0,"importance":0}}}
     */
	@RequestMapping(value = "/get")
	public RespBean get(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		CustomerRelationBean customerRelationBean = reqBean.getCustomerRelationBean();
		if (customerRelationBean == null) AppException.toThrow(MSG_00003);
		Long customerId = customerRelationBean.getCustomerId();
		if (customerId == null) AppException.toThrow(MSG_00003);
		
		CustomerBean customerBean = new CustomerBean();
		customerBean.setId(customerId);
		customerBean.setUserId(UserUtil.getCurrentUserId(request));
		Long count = customerService.selectCount(customerBean);
		if (count <= 0) AppException.toThrow(MSG_00003);
		
		CustomerRelationBean customerRelationBeanResult = null;
		CustomerRelationBean customerRelationBeanTmp = customerRelationService.selectOne(customerRelationBean);
		if (customerRelationBeanTmp != null) {
			customerRelationBeanResult = new CustomerRelationBean();
			
			customerRelationBeanResult.setRelationship(customerRelationBeanTmp.getRelationship());
			customerRelationBeanResult.setIntimacy(customerRelationBeanTmp.getIntimacy());
			customerRelationBeanResult.setImportance(customerRelationBeanTmp.getImportance());
			
			CustomerRelationUtil.assembly(customerRelationBeanResult);
		}
		return RespUtil.build(request).putData("customerRelationBean", customerRelationBeanResult);
	}
	
	/**
	 * 客户 关系 修改
	 * 
     * @api {post} /api/customer/relation/update 客户 关系 修改
     * @apiDescription 客户 关系 修改
     * @apiName customer_relation_update
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParam (CustomerRelationBean) {Object} customerRelationBean 工作 bean
     * @apiParam (CustomerRelationBean) {Long} customerRelationBean.customerId 客户ID
	 * @apiParam (CustomerRelationBean) {Integer} customerRelationBean.importance 关系
	 * @apiParam (CustomerRelationBean) {Integer} customerRelationBean.intimacy 亲密
	 * @apiParam (CustomerRelationBean) {Integer} customerRelationBean.relationship 重要
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"customerRelationBean":{"customerId":2,"relationship":0,"intimacy":0,"importance":0}}
     * 
     */
	@RequestMapping(value = "/update")
	public RespBean update(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		CustomerRelationBean customerRelationBean = reqBean.getCustomerRelationBean();
		if (customerRelationBean == null) AppException.toThrow(MSG_00003);
		customerRelationBean.setUserId(UserUtil.getCurrentUserId(request));
		customerRelationService.exe("update", customerRelationBean);
		return RespUtil.build(request);
	}
}