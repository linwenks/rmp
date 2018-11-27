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
import com.rmp.api.model.CustomerHobbyBean;
import com.rmp.api.service.customer.CustomerHobbyService;
import com.rmp.api.service.customer.CustomerService;
import com.rmp.api.util.CustomerHobbyUtil;
import com.rmp.api.util.SysCodeUtil;
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
	private CustomerService customerService;
	@Autowired
	private CustomerHobbyService customerHobbyService;
	
	/**
	 * 客户 兴趣爱好 配置
	 * 
     * @api {post} /api/customer/hobby/config 客户 兴趣爱好 配置
     * @apiDescription 客户 兴趣爱好 配置
     * @apiName customer_hobby_config
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"}}
     * 
     * @apiSuccess (data) {List} interestCodeList 兴趣 code list
     * @apiSuccess (data) {List} dietCodeList 饮食 code list
     * @apiSuccess (data) {List} tasteCodeList 口味 code list
     * 
     * @apiSuccessExample {json} 成功返回-示例:
     * 		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"msgs":[],"msg":{},"state":"0","data":{"dietCodeList":[{"id":69,"key":"1","value":"川湘菜","pid":68,"sort":1},{"id":70,"key":"2","value":"江浙菜","pid":68,"sort":2},{"id":71,"key":"3","value":"粤菜","pid":68,"sort":3},{"id":72,"key":"4","value":"北方菜","pid":68,"sort":4},{"id":73,"key":"5","value":"日韩料理","pid":68,"sort":5},{"id":74,"key":"6","value":"西餐","pid":68,"sort":6},{"id":75,"key":"7","value":"东南亚菜","pid":68,"sort":7},{"id":76,"key":"8","value":"火锅","pid":68,"sort":8},{"id":77,"key":"9","value":"海鲜","pid":68,"sort":9},{"id":78,"key":"10","value":"素食","pid":68,"sort":10},{"id":79,"key":"11","value":"烧烤","pid":68,"sort":11},{"id":80,"key":"12","value":"甜点","pid":68,"sort":12}],"interestCodeList":[{"id":48,"key":"1","value":"美食","pid":47,"sort":1},{"id":49,"key":"2","value":"旅游","pid":47,"sort":2},{"id":50,"key":"3","value":"美容美发","pid":47,"sort":3},{"id":51,"key":"4","value":"购物","pid":47,"sort":4},{"id":52,"key":"5","value":"按摩温泉","pid":47,"sort":5},{"id":53,"key":"6","value":"影视","pid":47,"sort":6},{"id":54,"key":"7","value":"运动","pid":47,"sort":7},{"id":55,"key":"8","value":"汽车","pid":47,"sort":8},{"id":56,"key":"9","value":"家居装饰","pid":47,"sort":9},{"id":57,"key":"10","value":"宠物","pid":47,"sort":10},{"id":58,"key":"11","value":"KTV","pid":47,"sort":11},{"id":59,"key":"12","value":"社交","pid":47,"sort":12},{"id":60,"key":"13","value":"养生","pid":47,"sort":13},{"id":61,"key":"14","value":"投资理财","pid":47,"sort":14},{"id":62,"key":"15","value":"营销","pid":47,"sort":15},{"id":63,"key":"16","value":"IT互联网","pid":47,"sort":16},{"id":64,"key":"17","value":"演出","pid":47,"sort":17},{"id":65,"key":"18","value":"外语学习","pid":47,"sort":18},{"id":66,"key":"19","value":"体验游戏","pid":47,"sort":19},{"id":67,"key":"20","value":"网络游戏","pid":47,"sort":20}],"tasteCodeList":[{"id":82,"key":"1","value":"甜","pid":81,"sort":1},{"id":83,"key":"2","value":"辣","pid":81,"sort":2},{"id":84,"key":"3","value":"酸","pid":81,"sort":3},{"id":85,"key":"4","value":"苦","pid":81,"sort":4}]}}
     */
	@RequestMapping(value = "/config")
	public RespBean config(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqUtil.buildCheckLogin(body, request);
		return RespUtil.build(request)
				.putData("interestCodeList", SysCodeUtil.getChildSimple(CUSTOMER, CUSTOMER_HOBBY, CUSTOMER_HOBBY_INTEREST))
				.putData("dietCodeList", SysCodeUtil.getChildSimple(CUSTOMER, CUSTOMER_HOBBY, CUSTOMER_HOBBY_DIET))
				.putData("tasteCodeList", SysCodeUtil.getChildSimple(CUSTOMER, CUSTOMER_HOBBY, CUSTOMER_HOBBY_TASTE));
	}
	
	/**
	 * 客户 兴趣爱好 查询
	 * 
     * @api {post} /api/customer/hobby/get 客户 兴趣爱好 查询
     * @apiDescription 客户 兴趣爱好 查询
     * @apiName customer_hobby_get
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParam (CustomerHobbyBean) {Object} customerHobbyBean 兴趣爱好 bean
     * @apiParam (CustomerHobbyBean) {Long} customerHobbyBean.customerId 客户ID
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"customerHobbyBean":{"customerId":2}}
     * 
     * @apiSuccess (data) {Object} customerHobbyBean 兴趣爱好 bean
	 * @apiSuccess (data) {String} customerFamilyBean.interest 兴趣
	 * @apiSuccess (data) {List} customerFamilyBean.interestKeyList 兴趣 键 list
	 * @apiSuccess (data) {List} customerFamilyBean.interestValueList 兴趣 值 list
	 * @apiSuccess (data) {String} customerFamilyBean.diet 饮食
	 * @apiSuccess (data) {List} customerFamilyBean.dietKeyList 饮食 键 list
	 * @apiSuccess (data) {List} customerFamilyBean.dietValueList 饮食 值 list
	 * @apiSuccess (data) {String} customerFamilyBean.taste 口味
	 * @apiSuccess (data) {List} customerFamilyBean.tasteKeyList 口味 键 list
	 * @apiSuccess (data) {List} customerFamilyBean.tasteValueList 口味 值 list
     * 
     * @apiSuccessExample {json} 成功返回-示例:
     * 		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"msgs":[],"msg":{},"state":"0","data":{"customerHobbyBean":{"interestKeyList":["1"],"dietKeyList":["1","3"],"tasteKeyList":["4"],"interestValueList":["美食"],"dietValueList":["川湘菜","粤菜"],"tasteValueList":["苦"],"interest":"1","diet":"1,3","taste":"4"}}}
     */
	@RequestMapping(value = "/get")
	public RespBean get(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		CustomerHobbyBean customerHobbyBean = reqBean.getCustomerHobbyBean();
		if (customerHobbyBean == null) AppException.toThrow(MSG_00003);
		Long customerId = customerHobbyBean.getCustomerId();
		if (customerId == null) AppException.toThrow(MSG_00003);
		
		CustomerBean customerBean = CustomerBean.builder().id(customerId).userId(UserUtil.getCurrentUserId(request)).build();
		Long count = customerService.selectCount(customerBean);
		if (count <= 0) AppException.toThrow(MSG_00003);
		
		CustomerHobbyBean customerHobbyBeanResult = null;
		CustomerHobbyBean customerHobbyBeanTmp = customerHobbyService.selectOne(customerHobbyBean);
		if (customerHobbyBeanTmp != null) {
			customerHobbyBeanResult = CustomerHobbyBean.builder()
			.interest(customerHobbyBeanTmp.getInterest())
			.diet(customerHobbyBeanTmp.getDiet())
			.taste(customerHobbyBeanTmp.getTaste())
			.build();
			CustomerHobbyUtil.assembly(customerHobbyBeanResult);
		}
		return RespUtil.build(request).putData("customerHobbyBean", customerHobbyBeanResult);
	}
	
	/**
	 * 客户 兴趣爱好 修改
	 * 
     * @api {post} /api/customer/hobby/update 客户 兴趣爱好 修改
     * @apiDescription 客户 兴趣爱好 修改
     * @apiName customer_hobby_update
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParam (data) {Object} customerHobbyBean 兴趣爱好 bean
     * @apiParam (data) {List} customerFamilyBean.customerId 客户ID
	 * @apiParam (data) {String} [customerFamilyBean.interest] 兴趣
	 * @apiParam (data) {String} [customerFamilyBean.diet] 饮食
	 * @apiParam (data) {String} [customerFamilyBean.taste] 口味
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"customerHobbyBean":{"customerId":2,"interest":"1","diet":"1,3","taste":"4"}}
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