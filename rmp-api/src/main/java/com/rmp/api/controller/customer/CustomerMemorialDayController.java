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
import com.rmp.api.model.CustomerMemorialDayBean;
import com.rmp.api.service.customer.CustomerMemorialDayService;
import com.rmp.api.service.customer.CustomerService;
import com.rmp.api.util.CustomerMemorialDayUtil;
import com.rmp.api.util.SysCodeUtil;
import com.rmp.api.util.UserUtil;

/**
 * 客户 纪念日 json controller
 * @author linw
 *
 */
@RestController("api_customer_CustomerMemorialDayController")
@RequestMapping(value = "/api/customer/memorialDay", method = RequestMethod.POST, produces="application/json;charset=utf-8")
public class CustomerMemorialDayController extends BaseApiController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private CustomerMemorialDayService customerMemorialDayService;
	
	/**
	 * 客户 纪念日 配置
	 * 
     * @api {post} /api/customer/memorialDay/config 客户 纪念日 配置
     * @apiDescription 客户 纪念日 配置
     * @apiName customer_memorialDay_config
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"}}
     * 
     * @apiSuccess (data) {List} occurTypeCodeList 发生类型 code list
     * @apiSuccess (data) {List} advanceTypeCodeList 提前类型 code list
     * 
     * @apiSuccessExample {json} 成功返回-示例:
     * 		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"msgs":[],"msg":{},"state":"0","data":{"advanceTypeCodeList":[{"id":159,"key":"1","value":"1天","pid":158,"sort":0},{"id":160,"key":"2","value":"2天","pid":158,"sort":0},{"id":161,"key":"3","value":"3天","pid":158,"sort":0},{"id":162,"key":"4","value":"5天","pid":158,"sort":0},{"id":163,"key":"5","value":"1周","pid":158,"sort":0},{"id":164,"key":"6","value":"2周","pid":158,"sort":0},{"id":165,"key":"7","value":"1月","pid":158,"sort":0}],"occurTypeCodeList":[{"id":153,"key":"1","value":"1次","pid":152,"sort":0},{"id":154,"key":"2","value":"每年","pid":152,"sort":0},{"id":155,"key":"3","value":"每月","pid":152,"sort":0},{"id":157,"key":"4","value":"每周","pid":152,"sort":0}]}}
     */
	@RequestMapping(value = "/config")
	public RespBean config(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqUtil.buildCheckLogin(body, request);
		return RespUtil.build(request)
				.putData("occurTypeCodeList", SysCodeUtil.getChildSimple(CUSTOMER, CUSTOMER_MEMORIAL_DAY, CUSTOMER_MEMORIAL_DAY_OCCUR_TYPE))
				.putData("advanceTypeCodeList", SysCodeUtil.getChildSimple(CUSTOMER, CUSTOMER_MEMORIAL_DAY, CUSTOMER_MEMORIAL_DAY_ADVANCE_TYPE));
	}
	
	/**
	 * 客户 纪念日 列表 查询
	 * 
     * @api {post} /api/customer/memorialDay/list 客户 纪念日 列表 查询
     * @apiDescription 客户 纪念日 查询
     * @apiName customer_memorialDay_list
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParam (CustomerMemorialDayBean) {Object} customerMemorialDayBean 纪念日 bean
     * @apiParam (CustomerMemorialDayBean) {Long} customerMemorialDayBean.customerId 客户ID
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"customerMemorialDayBean":{"customerId":2}}
     * 
     * @apiSuccess (data) {List} customerMemorialDayBeanList 家庭 bean list
	 * @apiSuccess (data) {Long} customerMemorialDayBeanList.id ID
	 * @apiSuccess (data) {String} customerMemorialDayBeanList.name 名称
	 * @apiSuccess (data) {Integer} customerMemorialDayBeanList.occurType 发生类型
	 * @apiSuccess (data) {String} customerMemorialDayBeanList.occurTypeValue 发生类型 值
	 * @apiSuccess (data) {Integer} customerMemorialDayBeanList.occurDate 关系日期<br/>occurType=1 yyyyMMdd 年月日<br/>occurType=2 MMdd 月日<br/>occurType=3 {1-31}天<br/>occurType=4 {1-7}周
	 * @apiSuccess (data) {Integer} customerMemorialDayBeanList.advanceType 提前类型
	 * @apiSuccess (data) {String} customerMemorialDayBeanList.advanceTypeValue 提前类型 值
     * 
     * @apiSuccessExample {json} 成功返回-示例:
     * 		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"msgs":[],"msg":{},"state":"0","data":{"customerMemorialDayBeanList":[{"occurTypeValue":"1次","advanceTypeValue":"1天","id":2,"name":"ttttt2","occurType":1,"occurDate":20181030,"advanceType":1},{"occurTypeValue":"1次","advanceTypeValue":"1天","id":1,"name":"ttttt2","occurType":1,"occurDate":20181030,"advanceType":1}]}}
     */
	@RequestMapping(value = "/list")
	public RespBean list(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		CustomerMemorialDayBean customerMemorialDayBean = reqBean.getCustomerMemorialDayBean();
		if (customerMemorialDayBean == null) AppException.toThrow(MSG_00003);
		Long customerId = customerMemorialDayBean.getCustomerId();
		if (customerId == null) AppException.toThrow(MSG_00003);
		
		CustomerBean customerBean = new CustomerBean();
		customerBean.setId(customerId);
		customerBean.setUserId(UserUtil.getCurrentUserId(request));
		Long count = customerService.selectCount(customerBean);
		if (count <= 0) AppException.toThrow(MSG_00003);
		
		List<CustomerMemorialDayBean> customerMemorialDayBeanListResult = null;
		List<CustomerMemorialDayBean> customerMemorialDayBeanListTmp = customerMemorialDayService.selectList(null, customerMemorialDayBean);
		if (!CollectionUtils.isEmpty(customerMemorialDayBeanListTmp)) {
			customerMemorialDayBeanListResult = Lists.newArrayList();
			for (CustomerMemorialDayBean customerMemorialDayBeanTmp : customerMemorialDayBeanListTmp) {
				CustomerMemorialDayBean customerMemorialDayBeanResult = new CustomerMemorialDayBean();
				customerMemorialDayBeanResult.setId(customerMemorialDayBeanTmp.getId());
				customerMemorialDayBeanResult.setName(customerMemorialDayBeanTmp.getName());
				customerMemorialDayBeanResult.setOccurType(customerMemorialDayBeanTmp.getOccurType());
				customerMemorialDayBeanResult.setOccurDate(customerMemorialDayBeanTmp.getOccurDate());
				customerMemorialDayBeanResult.setAdvanceType(customerMemorialDayBeanTmp.getAdvanceType());
				CustomerMemorialDayUtil.assembly(customerMemorialDayBeanResult);
				customerMemorialDayBeanListResult.add(customerMemorialDayBeanResult);
			}
		}
		return RespUtil.build(request).putData("customerMemorialDayBeanList", customerMemorialDayBeanListResult);
	}
	
	/**
	 * 客户 纪念日 查询
	 * 
     * @api {post} /api/customer/memorialDay/get 客户 纪念日 查询
     * @apiDescription 客户 纪念日 查询
     * @apiName customer_memorialDay_get
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParam (CustomerMemorialDayBean) {Object} customerMemorialDayBean 家庭 bean
     * @apiParam (CustomerMemorialDayBean) {Long} customerMemorialDayBean.id ID
     * @apiParam (CustomerMemorialDayBean) {Long} customerMemorialDayBean.customerId 客户ID
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"customerMemorialDayBean":{"id":1,"customerId":2}}
     * 
     * @apiSuccess (data) {Object} customerMemorialDayBean 家庭 bean
	 * @apiSuccess (data) {Long} customerMemorialDayBean.id ID
	 * @apiSuccess (data) {String} customerMemorialDayBean.name 名称
	 * @apiSuccess (data) {Integer} customerMemorialDayBean.occurType 发生类型
	 * @apiSuccess (data) {String} customerMemorialDayBean.occurTypeValue 发生类型 值
	 * @apiSuccess (data) {Integer} customerMemorialDayBean.occurDate 关系日期
	 * @apiSuccess (data) {Integer} customerMemorialDayBean.advanceType 提前类型
	 * @apiSuccess (data) {String} customerMemorialDayBean.advanceTypeValue 提前类型 值
     * 
     * @apiSuccessExample {json} 成功返回-示例:
     * 		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"msgs":[],"msg":{},"state":"0","data":{"customerMemorialDayBean":{"occurTypeValue":"1次","advanceTypeValue":"1天","id":1,"name":"ttttt2","occurType":1,"occurDate":20181030,"advanceType":1}}}
     */
	@RequestMapping(value = "/get")
	public RespBean get(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		CustomerMemorialDayBean customerMemorialDayBean = reqBean.getCustomerMemorialDayBean();
		if (customerMemorialDayBean == null) AppException.toThrow(MSG_00003);
		Long customerId = customerMemorialDayBean.getCustomerId();
		if (customerId == null) AppException.toThrow(MSG_00003);
		
		CustomerBean customerBean = new CustomerBean();
		customerBean.setId(customerId);
		customerBean.setUserId(UserUtil.getCurrentUserId(request));
		Long count = customerService.selectCount(customerBean);
		if (count <= 0) AppException.toThrow(MSG_00003);
		
		CustomerMemorialDayBean customerMemorialDayBeanResult = null;
		CustomerMemorialDayBean customerMemorialDayBeanTmp = customerMemorialDayService.selectOne(customerMemorialDayBean);
		if (customerMemorialDayBeanTmp != null) {
			customerMemorialDayBeanResult = new CustomerMemorialDayBean();
			customerMemorialDayBeanResult.setId(customerMemorialDayBeanTmp.getId());
			customerMemorialDayBeanResult.setName(customerMemorialDayBeanTmp.getName());
			customerMemorialDayBeanResult.setOccurType(customerMemorialDayBeanTmp.getOccurType());
			customerMemorialDayBeanResult.setOccurDate(customerMemorialDayBeanTmp.getOccurDate());
			customerMemorialDayBeanResult.setAdvanceType(customerMemorialDayBeanTmp.getAdvanceType());
			CustomerMemorialDayUtil.assembly(customerMemorialDayBeanResult);
		}
		return RespUtil.build(request).putData("customerMemorialDayBean", customerMemorialDayBeanResult);
	}
	
	/**
	 * 客户 纪念日 添加
	 * 
     * @api {post} /api/customer/memorialDay/save 客户 纪念日 添加
     * @apiDescription 客户 纪念日 添加
     * @apiName customer_memorialDay_save
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParam (CustomerMemorialDayBean) {Object} customerMemorialDayBean 家庭 bean
     * @apiParam (CustomerMemorialDayBean) {Long} customerMemorialDayBean.customerId 客户ID
	 * @apiParam (CustomerMemorialDayBean) {String} customerMemorialDayBean.name 名称
	 * @apiParam (CustomerMemorialDayBean) {Integer} customerMemorialDayBean.occurType 发生类型
	 * @apiParam (CustomerMemorialDayBean) {Integer} customerMemorialDayBean.occurDate 关系日期
	 * @apiParam (CustomerMemorialDayBean) {Integer} customerMemorialDayBean.advanceType 提前类型
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"customerMemorialDayBean":{"customerId":2,"name":"ttttt","occurType":1,"occurDate":20181030,"advanceType":1}}
     * 
     * @apiSuccess (data) {Object} customerMemorialDayBean 家庭 bean
	 * @apiSuccess (data) {Long} customerMemorialDayBean.id ID
	 * @apiSuccess (data) {String} customerMemorialDayBean.name 名称
	 * @apiSuccess (data) {Integer} customerMemorialDayBean.occurType 发生类型
	 * @apiSuccess (data) {String} customerMemorialDayBean.occurTypeValue 发生类型 值
	 * @apiSuccess (data) {Integer} customerMemorialDayBean.occurDate 关系日期
	 * @apiSuccess (data) {Integer} customerMemorialDayBean.advanceType 提前类型
	 * @apiSuccess (data) {String} customerMemorialDayBean.advanceTypeValue 提前类型 值
     * 
     * @apiSuccessExample {json} 成功返回-示例:
     * 		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"msgs":[],"msg":{},"state":"0","data":{"customerMemorialDayBean":{"occurTypeValue":"1次","advanceTypeValue":"1天","id":1,"name":"ttttt","occurType":1,"occurDate":20181030,"advanceType":1}}}
     */
	@RequestMapping(value = "/save")
	public RespBean save(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		CustomerMemorialDayBean customerMemorialDayBean = reqBean.getCustomerMemorialDayBean();
		if (customerMemorialDayBean == null) AppException.toThrow(MSG_00003);
		customerMemorialDayBean.setUserId(UserUtil.getCurrentUserId(request));
		customerMemorialDayService.exe("save", customerMemorialDayBean);
		
		CustomerMemorialDayBean customerMemorialDayBeanResult = new CustomerMemorialDayBean();
		customerMemorialDayBeanResult = new CustomerMemorialDayBean();
		customerMemorialDayBeanResult.setId(customerMemorialDayBean.getId());
		customerMemorialDayBeanResult.setName(customerMemorialDayBean.getName());
		customerMemorialDayBeanResult.setOccurType(customerMemorialDayBean.getOccurType());
		customerMemorialDayBeanResult.setOccurDate(customerMemorialDayBean.getOccurDate());
		customerMemorialDayBeanResult.setAdvanceType(customerMemorialDayBean.getAdvanceType());
		CustomerMemorialDayUtil.assembly(customerMemorialDayBeanResult);
		
		return RespUtil.build(request).putData("customerMemorialDayBean", customerMemorialDayBeanResult);
	}
	
	/**
	 * 客户 纪念日 修改
	 * 
     * @api {post} /api/customer/memorialDay/update 客户 纪念日 修改
     * @apiDescription 客户 纪念日 修改
     * @apiName customer_memorialDay_update
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParam (CustomerMemorialDayBean) {Object} customerMemorialDayBean 家庭 bean
     * @apiParam (CustomerMemorialDayBean) {Long} customerMemorialDayBean.id ID
     * @apiParam (CustomerMemorialDayBean) {Long} customerMemorialDayBean.customerId 客户ID
	 * @apiParam (CustomerMemorialDayBean) {String} customerMemorialDayBean.name 名称
	 * @apiParam (CustomerMemorialDayBean) {Integer} customerMemorialDayBean.occurType 发生类型
	 * @apiParam (CustomerMemorialDayBean) {Integer} customerMemorialDayBean.occurDate 关系日期
	 * @apiParam (CustomerMemorialDayBean) {Integer} customerMemorialDayBean.advanceType 提前类型
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"customerMemorialDayBean":{"id":1,"customerId":2,"name":"ttttt","occurType":1,"occurDate":20181030,"advanceType":1}}
     * 
     */
	@RequestMapping(value = "/update")
	public RespBean update(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		CustomerMemorialDayBean customerMemorialDayBean = reqBean.getCustomerMemorialDayBean();
		if (customerMemorialDayBean == null) AppException.toThrow(MSG_00003);
		customerMemorialDayBean.setUserId(UserUtil.getCurrentUserId(request));
		customerMemorialDayService.exe("update", customerMemorialDayBean);
		return RespUtil.build(request);
	}
	
	/**
	 * 客户 纪念日 删除
	 * 
     * @api {post} /api/customer/memorialDay/delete 客户 纪念日 删除
     * @apiDescription 客户 纪念日 删除
     * @apiName customer_memorialDay_delete
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParam (CustomerMemorialDayBean) {Object} customerMemorialDayBean 家庭 bean
     * @apiParam (CustomerMemorialDayBean) {Long} customerMemorialDayBean.id ID
     * @apiParam (CustomerMemorialDayBean) {Long} customerMemorialDayBean.customerId 客户ID
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"customerMemorialDayBean":{"id":1,"customerId":2}}
     * 
     */
	@RequestMapping(value = "/delete")
	public RespBean delete(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		CustomerMemorialDayBean customerMemorialDayBean = reqBean.getCustomerMemorialDayBean();
		if (customerMemorialDayBean == null) AppException.toThrow(MSG_00003);
		customerMemorialDayBean.setUserId(UserUtil.getCurrentUserId(request));
		customerMemorialDayService.exe("delete", customerMemorialDayBean);
		return RespUtil.build(request);
	}
}