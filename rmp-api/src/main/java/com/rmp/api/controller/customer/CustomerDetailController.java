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
import com.rmp.api.model.CustomerDetailBean;
import com.rmp.api.service.customer.CustomerDetailService;
import com.rmp.api.service.customer.CustomerService;
import com.rmp.api.util.CustomerDetailUtil;
import com.rmp.api.util.UserUtil;

/**
 * 客户 明细 json controller
 * @author linw
 *
 */
@RestController("api_customer_CustomerDetailController")
@RequestMapping(value = "/api/customer/detail", method = RequestMethod.POST, produces="application/json;charset=utf-8")
public class CustomerDetailController extends BaseApiController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private CustomerDetailService customerDetailService;
	
	/**
	 * 客户 明细 查询
	 * 
     * @api {post} /api/customer/detail/get 客户 明细 查询
     * @apiDescription 客户 明细 查询
     * @apiName customer_detail_get
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParam (CustomerDetailBean) {Object} customerDetailBean 明细 bean
     * @apiParam (CustomerDetailBean) {Long} customerDetailBean.customerId 客户ID
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"customerDetailBean":{"customerId":2}}
     * 
     * @apiSuccess (data) {Object} customerDetailBean 明细 bean
	 * @apiSuccess (data) {String} customerDetailBean.remark 备注
     * 
     * @apiSuccessExample {json} 成功返回-示例:
     * 		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"msgs":[],"msg":{},"state":"0","data":{"customerDetailBean":{"remark":"xxxxxxxxxTTT"}}}
     */
	@RequestMapping(value = "/get")
	public RespBean get(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		CustomerDetailBean customerDetailBean = reqBean.getCustomerDetailBean();
		if (customerDetailBean == null) AppException.toThrow(MSG_00003);
		Long customerId = customerDetailBean.getCustomerId();
		if (customerId == null) AppException.toThrow(MSG_00003);
		
		CustomerBean customerBean = CustomerBean.builder().id(customerId).userId(UserUtil.getCurrentUserId(request)).build();
		Long count = customerService.selectCount(customerBean);
		if (count <= 0) AppException.toThrow(MSG_00003);
		
		CustomerDetailBean customerDetailBeanResult = null;
		CustomerDetailBean customerDetailBeanTmp = customerDetailService.selectOne(customerDetailBean);
		if (customerDetailBeanTmp != null) {
			customerDetailBeanResult = CustomerDetailBean.builder()
			.remark(customerDetailBeanTmp.getRemark())
			.build();
			CustomerDetailUtil.assembly(customerDetailBeanResult);
		}
		return RespUtil.build(request).putData("customerDetailBean", customerDetailBeanResult);
	}
	
	/**
	 * 客户 明细 修改
	 * 
     * @api {post} /api/customer/detail/update 客户 明细 修改
     * @apiDescription 客户 明细 修改
     * @apiName customer_detail_update
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParam (data) {Object} customerDetailBean 明细 bean
     * @apiParam (data) {Long} customerDetailBean.customerId 客户ID
	 * @apiParam (data) {String} [customerDetailBean.remark] 备注
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"customerDetailBean":{"customerId":2,"remark":"xxxxxxxxxTTT"}}
     * 
     */
	@RequestMapping(value = "/update")
	public RespBean update(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		CustomerDetailBean customerDetailBean = reqBean.getCustomerDetailBean();
		if (customerDetailBean == null) AppException.toThrow(MSG_00003);
		customerDetailBean.setUserId(UserUtil.getCurrentUserId(request));
		customerDetailService.exe("update", customerDetailBean);
		return RespUtil.build(request);
	}
}