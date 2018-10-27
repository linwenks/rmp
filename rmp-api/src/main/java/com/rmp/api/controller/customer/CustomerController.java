package com.rmp.api.controller.customer;

import static com.rmp.api.util.MsgEnum.MSG_00003;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rmp.api.base.controller.BaseApiController;
import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.model.ReqBean;
import com.rmp.api.base.model.RespBean;
import com.rmp.api.base.util.ReqUtil;
import com.rmp.api.base.util.RespUtil;
import com.rmp.api.model.CustomerBean;
import com.rmp.api.service.customer.CustomerService;
import com.rmp.api.util.UserUtil;
import com.rmp.common.page.QueryPage;

/**
 * 客户 json controller
 * @author linw
 *
 */
@RestController("api_customer_CustomerController")
@RequestMapping(value = "/api/customer/customer", method = RequestMethod.POST, produces="application/json;charset=utf-8")
public class CustomerController extends BaseApiController {

	@Autowired
	private CustomerService customerService;
	
	/**
	 * 客户 查询列表
	 * 
     * @api {post} /api/customer/customer/list 客户 列表查询
     * @apiDescription 客户 列表查询 
     * @apiName customer_customer_list
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     */
	@RequestMapping(value = "/list")
	public RespBean list(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		CustomerBean customerBean = reqBean.getCustomerBean();
		if (customerBean == null) throw new AppException(MSG_00003);
		
		QueryPage queryPage = reqBean.getQueryPage();
		if (queryPage == null) queryPage = new QueryPage();
		
		List<CustomerBean> customerBeanList = customerService.selectList(queryPage, customerBean);
		
		
		return RespUtil.build(request).putData("customerBeanList", customerBeanList);
	}
	
	/**
	 * 客户 查询
	 * 
     * @api {post} /api/customer/customer/get 客户 查询
     * @apiDescription 客户 查询
     * @apiName customer_customer_get
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     */
	@RequestMapping(value = "/get")
	public RespBean get(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		CustomerBean customerBean = reqBean.getCustomerBean();
		if (customerBean == null) throw new AppException(MSG_00003);
		Long id = customerBean.getId();
		if (id == null) throw new AppException(MSG_00003);
		
		CustomerBean customerBeanTmp = customerService.selectById(id);
		return RespUtil.build(request).putData("customerBean", customerBeanTmp);
	}
	
	/**
	 * 客户 新增
	 * 
     * @api {post} /api/customer/customer/save 客户 新增
     * @apiDescription 客户 新增
     * @apiName customer_customer_save
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     */
	@RequestMapping(value = "/save")
	public RespBean save(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		CustomerBean customerBean = reqBean.getCustomerBean();
		if (customerBean == null) throw new AppException(MSG_00003);
		customerBean.setUserId(UserUtil.getCurrentUserId(request));
		customerService.exe("save", customerBean);
		return RespUtil.build(request);
	}
	
	/**
	 * 客户 修改
	 * 
     * @api {post} /api/customer/customer/update 客户 修改
     * @apiDescription 客户 修改
     * @apiName customer_customer_update
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     */
	@RequestMapping(value = "/update")
	public RespBean update(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		CustomerBean customerBean = reqBean.getCustomerBean();
		if (customerBean == null) throw new AppException(MSG_00003);
		customerBean.setUserId(UserUtil.getCurrentUserId(request));
		customerService.exe("update", customerBean);
		return RespUtil.build(request);
	}
}