package com.rmp.api.controller.customer;

import static com.rmp.api.util.MsgEnum.MSG_00003;

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
	 * 客户 关系 查询
	 * 
     * @api {post} /api/customer/relation/get 客户 关系 查询
     * @apiDescription 客户 关系 查询
     * @apiName customer_relation_get
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"customerRelationBean":{"customerId":2}}
     * 
     * @apiSuccessExample {json} 成功返回-示例:
     * 		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"msgs":[],"msg":{},"state":"0","data":{"customerRelationBean":{"relationship":0,"intimacy":0,"importance":0}}}
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