package com.rmp.api.controller.customer;

import static com.rmp.api.util.MsgEnum.*;
import static com.rmp.api.util.constant.Constant.SysCode.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.rmp.api.base.controller.BaseApiController;
import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.model.ReqBean;
import com.rmp.api.base.model.RespBean;
import com.rmp.api.base.util.ReqUtil;
import com.rmp.api.base.util.RespUtil;
import com.rmp.api.model.CustomerBean;
import com.rmp.api.model.CustomerFamilyBean;
import com.rmp.api.service.customer.CustomerFamilyService;
import com.rmp.api.service.customer.CustomerService;
import com.rmp.api.util.CustomerFamilyUtil;
import com.rmp.api.util.SysCodeUtil;
import com.rmp.api.util.UserUtil;

/**
 * 客户 家庭 json controller
 * @author linw
 *
 */
@RestController("api_customer_CustomerFamilyController")
@RequestMapping(value = "/api/customer/family", method = RequestMethod.POST, produces="application/json;charset=utf-8")
public class CustomerFamilyController extends BaseApiController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private CustomerFamilyService customerFamilyService;
	
	/**
	 * 客户 家庭 配置
	 * 
     * @api {post} /api/customer/family/config 客户 家庭 配置
     * @apiDescription 客户 家庭 配置
     * @apiName customer_family_config
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"}}
     * 
     * @apiSuccess (data) {List} relationshipCodeList 关系 code list
	 * @apiSuccess (data) {String} relationshipCodeList.key 键
	 * @apiSuccess (data) {String} relationshipCodeList.value 值
     * 
     * @apiSuccessExample {json} 成功返回-示例:
     * 		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"msgs":[],"msg":{},"state":"0","data":{"relationshipCodeList":[{"id":145,"key":"1","value":"父亲","pid":144,"sort":0},{"id":146,"key":"2","value":"母亲","pid":144,"sort":0},{"id":147,"key":"3","value":"老公","pid":144,"sort":0},{"id":148,"key":"4","value":"老婆","pid":144,"sort":0},{"id":149,"key":"5","value":"儿子","pid":144,"sort":0},{"id":150,"key":"6","value":"女儿","pid":144,"sort":0}]}}
     */
	@RequestMapping(value = "/config")
	public RespBean config(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqUtil.buildCheckLogin(body, request);
		return RespUtil.build(request)
				.putData("relationshipCodeList", SysCodeUtil.getListSimple(CUSTOMER_FAMILY_RELATIONSHIP));
	}
	
	/**
	 * 客户 家庭 列表 查询
	 * 
     * @api {post} /api/customer/family/list 客户 家庭 列表 查询
     * @apiDescription 客户 家庭 查询
     * @apiName customer_family_list
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParam (CustomerFamilyBean) {Object} customerFamilyBean 家庭 bean
     * @apiParam (CustomerFamilyBean) {Long} customerFamilyBean.customerId 客户ID
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"customerFamilyBean":{"customerId":2}}
     * 
     * @apiSuccess (data) {List} customerFamilyBeanList 家庭 bean list
	 * @apiSuccess (data) {Long} customerFamilyBeanList.id ID
	 * @apiSuccess (data) {Integer} customerFamilyBeanList.relationship 关系
	 * @apiSuccess (data) {String} customerFamilyBeanList.relationshipValue 关系 值
	 * @apiSuccess (data) {String} customerFamilyBeanList.realName 真实姓名
	 * @apiSuccess (data) {Integer} customerFamilyBeanList.birthday 生日
	 * @apiSuccess (data) {Long} customerFamilyBeanList.phone 手机
	 * @apiSuccess (data) {Long} customerFamilyBeanList.areaId 区域
	 * @apiSuccess (data) {String} customerFamilyBeanList.areaNameAll 区域全名 
	 * @apiSuccess (data) {String} customerFamilyBeanList.address 地址 
	 * 
     * @apiSuccessExample {json} 成功返回-示例:
     * 		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"msgs":[],"msg":{},"state":"0","data":{"customerFamilyBeanList":[{"areaNameAll":"江苏省泰州市","relationshipValue":"母亲","id":2,"relationship":2,"realName":"xxx","birthday":20100101,"phone":15111111111,"areaId":321200,"address":"ttt"},{"areaNameAll":"江苏省泰州市","relationshipValue":"母亲","id":1,"relationship":2,"realName":"xxx","birthday":20100101,"phone":15111111111,"areaId":321200}]}}
     */
	@RequestMapping(value = "/list")
	public RespBean list(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		CustomerFamilyBean customerFamilyBean = reqBean.getCustomerFamilyBean();
		if (customerFamilyBean == null) AppException.toThrow(MSG_00003);
		Long customerId = customerFamilyBean.getCustomerId();
		if (customerId == null) AppException.toThrow(MSG_00003);
		
		CustomerBean customerBean = new CustomerBean();
		customerBean.setId(customerId);
		customerBean.setUserId(UserUtil.getCurrentUserId(request));
		Long count = customerService.selectCount(customerBean);
		if (count <= 0) AppException.toThrow(MSG_00003);
		
		List<CustomerFamilyBean> customerFamilyBeanListResult = null;
		List<CustomerFamilyBean> customerFamilyBeanListTmp = customerFamilyService.selectList(null, customerFamilyBean);
		if (!CollectionUtils.isEmpty(customerFamilyBeanListTmp)) {
			customerFamilyBeanListResult = Lists.newArrayList();
			for (CustomerFamilyBean customerFamilyBeanTmp : customerFamilyBeanListTmp) {
				CustomerFamilyBean customerFamilyBeanResult = CustomerFamilyBean.builder()
						.id(customerFamilyBeanTmp.getId())
						.relationship(customerFamilyBeanTmp.getRelationship())
						.realName(customerFamilyBeanTmp.getRealName())
						.birthday(customerFamilyBeanTmp.getBirthday())
						.phone(customerFamilyBeanTmp.getPhone())
						.areaId(customerFamilyBeanTmp.getAreaId())
						.address(customerFamilyBeanTmp.getAddress())
						.build();
				CustomerFamilyUtil.assembly(customerFamilyBeanResult);
				customerFamilyBeanListResult.add(customerFamilyBeanResult);
			}
		}
		return RespUtil.build(request).putData("customerFamilyBeanList", customerFamilyBeanListResult);
	}
	
	/**
	 * 客户 家庭 查询
	 * 
     * @api {post} /api/customer/family/get 客户 家庭 查询
     * @apiDescription 客户 家庭 查询
     * @apiName customer_family_get
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParam (CustomerFamilyBean) {Object} customerFamilyBean 家庭 bean
     * @apiParam (CustomerFamilyBean) {Long} customerFamilyBean.id ID
     * @apiParam (CustomerFamilyBean) {Long} customerFamilyBean.customerId 客户ID
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"customerFamilyBean":{"id":1,"customerId":2}}
     * 
     * @apiSuccess (data) {Object} customerFamilyBean 家庭 bean
	 * @apiSuccess (data) {Long} customerFamilyBean.id ID
	 * @apiSuccess (data) {Integer} customerFamilyBean.relationship 关系
	 * @apiSuccess (data) {String} customerFamilyBean.relationshipValue 关系 值
	 * @apiSuccess (data) {String} customerFamilyBean.realName 真实姓名
	 * @apiSuccess (data) {Integer} customerFamilyBean.birthday 生日
	 * @apiSuccess (data) {Long} customerFamilyBean.phone 手机
	 * @apiSuccess (data) {Long} customerFamilyBean.areaId 区域
	 * @apiSuccess (data) {String} customerFamilyBean.areaNameAll 区域全名 
	 * @apiSuccess (data) {String} customerFamilyBean.address 地址 
     * 
     * @apiSuccessExample {json} 成功返回-示例:
     * 		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"msgs":[],"msg":{},"state":"0","data":{"customerFamilyBean":{"areaNameAll":"江苏省泰州市","relationshipValue":"母亲","id":1,"relationship":2,"realName":"xxx","birthday":20100101,"phone":15111111111,"areaId":321200}}}
     */
	@RequestMapping(value = "/get")
	public RespBean get(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		CustomerFamilyBean customerFamilyBean = reqBean.getCustomerFamilyBean();
		if (customerFamilyBean == null) AppException.toThrow(MSG_00003);
		Long customerId = customerFamilyBean.getCustomerId();
		if (customerId == null) AppException.toThrow(MSG_00003);
		
		CustomerBean customerBean = new CustomerBean();
		customerBean.setId(customerId);
		customerBean.setUserId(UserUtil.getCurrentUserId(request));
		Long count = customerService.selectCount(customerBean);
		if (count <= 0) AppException.toThrow(MSG_00003);
		
		CustomerFamilyBean customerFamilyBeanResult = null;
		CustomerFamilyBean customerFamilyBeanTmp = customerFamilyService.selectOne(customerFamilyBean);
		if (customerFamilyBeanTmp != null) {
			customerFamilyBeanResult = CustomerFamilyBean.builder()
					.id(customerFamilyBeanTmp.getId())
					.relationship(customerFamilyBeanTmp.getRelationship())
					.realName(customerFamilyBeanTmp.getRealName())
					.birthday(customerFamilyBeanTmp.getBirthday())
					.phone(customerFamilyBeanTmp.getPhone())
					.areaId(customerFamilyBeanTmp.getAreaId())
					.address(customerFamilyBeanTmp.getAddress())
					.build();
			CustomerFamilyUtil.assembly(customerFamilyBeanResult);
		}
		return RespUtil.build(request).putData("customerFamilyBean", customerFamilyBeanResult);
	}
	
	/**
	 * 客户 家庭 添加
	 * 
     * @api {post} /api/customer/family/save 客户 家庭 添加
     * @apiDescription 客户 家庭 添加
     * @apiName customer_family_save
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParam (CustomerFamilyBean) {Object} customerFamilyBean 家庭 bean
     * @apiParam (CustomerFamilyBean) {Long} customerFamilyBean.customerId 客户ID
	 * @apiParam (CustomerFamilyBean) {Integer} customerFamilyBean.relationship 关系
	 * @apiParam (CustomerFamilyBean) {String} customerFamilyBean.realName 真实姓名
	 * @apiParam (CustomerFamilyBean) {Integer} [customerFamilyBean.birthday] 生日
	 * @apiParam (CustomerFamilyBean) {Long} [customerFamilyBean.phone] 手机
	 * @apiParam (CustomerFamilyBean) {Long} [customerFamilyBean.areaId] 区域
	 * @apiParam (CustomerFamilyBean) {String} [customerFamilyBean.address] 地址 
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"customerFamilyBean":{"customerId":2,"realName":"xxx","phone":15111111111,"relationship":2,"birthday":20100101,"areaId":321200,"address":"ttt"}}
     * 
     * @apiSuccess (data) {Object} customerFamilyBean 家庭 bean
	 * @apiSuccess (data) {Long} customerFamilyBeanList.id ID
	 * @apiSuccess (data) {Integer} customerFamilyBeanList.relationship 关系
	 * @apiSuccess (data) {String} customerFamilyBeanList.relationshipValue 关系 值
	 * @apiSuccess (data) {String} customerFamilyBeanList.realName 真实姓名
	 * @apiSuccess (data) {Integer} customerFamilyBeanList.birthday 生日 yyyyMMdd
	 * @apiSuccess (data) {Long} customerFamilyBeanList.phone 手机
	 * @apiSuccess (data) {Long} customerFamilyBeanList.areaId 区域
	 * @apiSuccess (data) {String} customerFamilyBeanList.areaNameAll 区域全名 
	 * @apiSuccess (data) {String} customerFamilyBeanList.address 地址 
     * 
     * @apiSuccessExample {json} 成功返回-示例:
     * 		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"msgs":[],"msg":{},"state":"0","data":{"customerFamilyBean":{"areaNameAll":"江苏省泰州市","relationshipValue":"母亲","id":2,"relationship":2,"realName":"xxx","birthday":20100101,"phone":15111111111,"areaId":321200,"address":"ttt"}}}
     */
	@RequestMapping(value = "/save")
	public RespBean save(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		CustomerFamilyBean customerFamilyBean = reqBean.getCustomerFamilyBean();
		if (customerFamilyBean == null) AppException.toThrow(MSG_00003);
		customerFamilyBean.setUserId(UserUtil.getCurrentUserId(request));
		customerFamilyService.exe("save", customerFamilyBean);
		
		CustomerFamilyBean customerFamilyBeanResult = CustomerFamilyBean.builder()
				.id(customerFamilyBean.getId())
				.relationship(customerFamilyBean.getRelationship())
				.realName(customerFamilyBean.getRealName())
				.birthday(customerFamilyBean.getBirthday())
				.phone(customerFamilyBean.getPhone())
				.areaId(customerFamilyBean.getAreaId())
				.address(customerFamilyBean.getAddress())
				.build();
		CustomerFamilyUtil.assembly(customerFamilyBeanResult);
		
		return RespUtil.build(request).putData("customerFamilyBean", customerFamilyBeanResult);
	}
	
	/**
	 * 客户 家庭 修改
	 * 
     * @api {post} /api/customer/family/update 客户 家庭 修改
     * @apiDescription 客户 家庭 修改
     * @apiName customer_family_update
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParam (CustomerFamilyBean) {Object} customerFamilyBean 家庭 bean
     * @apiParam (CustomerFamilyBean) {Long} customerFamilyBean.id ID
     * @apiParam (CustomerFamilyBean) {Long} customerFamilyBean.customerId 客户ID
	 * @apiParam (CustomerFamilyBean) {Integer} customerFamilyBean.relationship 关系
	 * @apiParam (CustomerFamilyBean) {String} customerFamilyBean.realName 真实姓名
	 * @apiParam (CustomerFamilyBean) {Integer} [customerFamilyBean.birthday] 生日 yyyyMMdd
	 * @apiParam (CustomerFamilyBean) {Long} [customerFamilyBean.phone] 手机
	 * @apiParam (CustomerFamilyBean) {Long} [customerFamilyBean.areaId] 区域
	 * @apiParam (CustomerFamilyBean) {String} [customerFamilyBean.address] 地址 
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"customerFamilyBean":{"id":1,"customerId":2,"realName":"xxx","phone":15111111111,"relationship":2,"birthday":20100101,"areaId":321200,"address":"ttt"}}
     * 
     */
	@RequestMapping(value = "/update")
	public RespBean update(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		CustomerFamilyBean customerFamilyBean = reqBean.getCustomerFamilyBean();
		if (customerFamilyBean == null) AppException.toThrow(MSG_00003);
		customerFamilyBean.setUserId(UserUtil.getCurrentUserId(request));
		customerFamilyService.exe("update", customerFamilyBean);
		return RespUtil.build(request);
	}
	
	/**
	 * 客户 家庭 删除
	 * 
     * @api {post} /api/customer/family/delete 客户 家庭 删除
     * @apiDescription 客户 家庭 删除
     * @apiName customer_family_delete
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParam (CustomerFamilyBean) {Object} customerFamilyBean 家庭 bean
     * @apiParam (CustomerFamilyBean) {Long} customerFamilyBean.id ID
     * @apiParam (CustomerFamilyBean) {Long} customerFamilyBean.customerId 客户ID
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"customerFamilyBean":{"id":1,"customerId":2}}
     * 
     */
	@RequestMapping(value = "/delete")
	public RespBean delete(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		CustomerFamilyBean customerFamilyBean = reqBean.getCustomerFamilyBean();
		if (customerFamilyBean == null) AppException.toThrow(MSG_00003);
		customerFamilyBean.setUserId(UserUtil.getCurrentUserId(request));
		customerFamilyService.exe("delete", customerFamilyBean);
		return RespUtil.build(request);
	}
}