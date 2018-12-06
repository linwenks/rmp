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
import com.rmp.api.model.CustomerProblemBean;
import com.rmp.api.service.customer.CustomerProblemService;
import com.rmp.api.service.customer.CustomerService;
import com.rmp.api.util.CustomerProblemUtil;
import com.rmp.api.util.SysCodeUtil;
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
	 * 客户 可能问题 配置
	 * 
     * @api {post} /api/customer/problem/config 客户 可能问题 配置
     * @apiDescription 客户 可能问题 配置
     * @apiName customer_problem_config
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"}}
     *
     * @apiSuccess (data) {List} healthCodeList 健康 code list
     * @apiSuccess (data) {List} lifeCodeList 生活 code list
     * 
     * @apiSuccessExample {json} 成功返回-示例:
     * 		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"msgs":[],"msg":{},"state":"0","data":{"healthCodeList":[{"id":25,"key":"1","value":"心脏病","pid":24,"sort":1},{"id":26,"key":"2","value":"动脉硬化","pid":24,"sort":2},{"id":27,"key":"3","value":"高血压","pid":24,"sort":3},{"id":28,"key":"4","value":"高血脂","pid":24,"sort":4},{"id":29,"key":"5","value":"肠胃病","pid":24,"sort":5},{"id":30,"key":"6","value":"糖尿病","pid":24,"sort":6},{"id":31,"key":"7","value":"关节炎","pid":24,"sort":7},{"id":32,"key":"8","value":"肥胖症","pid":24,"sort":8},{"id":33,"key":"9","value":"胆结石","pid":24,"sort":9},{"id":34,"key":"10","value":"肾病","pid":24,"sort":10},{"id":35,"key":"11","value":"精神问题","pid":24,"sort":11},{"id":36,"key":"12","value":"脸部痘痕","pid":24,"sort":12},{"id":37,"key":"13","value":"五官瑕疵","pid":24,"sort":13}],"lifeCodeList":[{"id":39,"key":"1","value":"资金缺乏","pid":38,"sort":1},{"id":40,"key":"2","value":"寻找工作","pid":38,"sort":2},{"id":41,"key":"3","value":"事业发展","pid":38,"sort":3},{"id":42,"key":"4","value":"感情困扰","pid":38,"sort":4},{"id":43,"key":"5","value":"子女学习","pid":38,"sort":5},{"id":44,"key":"6","value":"法律问题","pid":38,"sort":6},{"id":45,"key":"7","value":"税务","pid":38,"sort":7}]}}
     */
	@RequestMapping(value = "/config")
	public RespBean config(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqUtil.buildCheckLogin(body, request);
		return RespUtil.build(request)
				.putData("healthCodeList", SysCodeUtil.getChildSimple(CUSTOMER, CUSTOMER_PROBLEM, CUSTOMER_PROBLEM_HEALTH))
				.putData("lifeCodeList", SysCodeUtil.getChildSimple(CUSTOMER, CUSTOMER_PROBLEM, CUSTOMER_PROBLEM_LIFE));
	}
	
	/**
	 * 客户 可能问题 查询
	 * 
     * @api {post} /api/customer/problem/get 客户 可能问题 查询
     * @apiDescription 客户 可能问题 查询
     * @apiName customer_problem_get
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParam (CustomerProblemBean) {Object} customerProblemBean 可能问题 bean
     * @apiParam (CustomerProblemBean) {Long} customerProblemBean.customerId 客户ID
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"customerProblemBean":{"customerId":2}}
     * 
     * @apiSuccess (data) {Object} customerProblemBean 可能问题 bean
	 * @apiSuccess (data) {String} customerProblemBean.health 健康
	 * @apiSuccess (data) {SysCodeBean} customerProblemBean.healthCodeList 健康 code list
	 * @apiSuccess (data) {String} customerProblemBean.life 生活
	 * @apiSuccess (data) {SysCodeBean} customerProblemBean.lifeCodeList 生活 code list
	 * @apiSuccess (data) {String} customerProblemBean.remark 备注
     * 
     * @apiSuccessExample {json} 成功返回-示例:
     * 		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"msgs":[],"msg":{},"state":"0","data":{"customerProblemBean":{"healthValueList":[{"key":"1","value":"心脏病"}],"lifeValueList":[{"key":"1","value":"资金缺乏"},{"key":"3","value":"事业发展"}],"health":"1","life":"1,3","remark":"xxxxxxxxxTTT"}}}
     */
	@RequestMapping(value = "/get")
	public RespBean get(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		CustomerProblemBean customerProblemBean = reqBean.getCustomerProblemBean();
		if (customerProblemBean == null) AppException.toThrow(MSG_00003);
		Long customerId = customerProblemBean.getCustomerId();
		if (customerId == null) AppException.toThrow(MSG_00003);
		
		CustomerBean customerBean = CustomerBean.builder().id(customerId).userId(UserUtil.getCurrentUserId(request)).build();
		Long count = customerService.selectCount(customerBean);
		if (count <= 0) AppException.toThrow(MSG_00003);
		
		CustomerProblemBean customerProblemBeanResult = null;
		CustomerProblemBean customerProblemBeanTmp = customerProblemService.selectOne(customerProblemBean);
		if (customerProblemBeanTmp != null) {
			customerProblemBeanResult = CustomerProblemBean.builder()
			.health(customerProblemBeanTmp.getHealth())
			.life(customerProblemBeanTmp.getLife())
			.remark(customerProblemBeanTmp.getRemark())
			.build();
			CustomerProblemUtil.assembly(customerProblemBeanResult);
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
     * @apiParam (data) {Object} customerProblemBean 可能问题 bean
     * @apiParam (data) {Long} customerProblemBean。customerId 客户ID
	 * @apiParam (data) {SysCodeBean} [customerProblemBean.healtCodeList] 健康
	 * @apiParam (data) {SysCodeBean} [customerProblemBean.lifeCodeList] 生活
	 * @apiParam (data) {String} [customerProblemBean.remark] 备注
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"customerProblemBean":{"customerId":2,"healthValueList":[{"key":"1"}],"lifeValueList":[{"key":"1"},{"key":"3"}],"remark":"xxxxxxxxxTTT"}}
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