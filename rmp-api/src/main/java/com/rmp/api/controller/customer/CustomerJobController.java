package com.rmp.api.controller.customer;

import static com.rmp.api.util.MsgEnum.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;
import com.rmp.api.base.controller.BaseApiController;
import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.model.ReqBean;
import com.rmp.api.base.model.RespBean;
import com.rmp.api.base.util.ReqUtil;
import com.rmp.api.base.util.RespUtil;
import com.rmp.api.model.AreaBean;
import com.rmp.api.model.CustomerBean;
import com.rmp.api.model.CustomerJobBean;
import com.rmp.api.service.customer.CustomerJobService;
import com.rmp.api.service.customer.CustomerService;
import com.rmp.api.util.AreaUtil;
import com.rmp.api.util.UserUtil;

/**
 * 客户 工作 json controller
 * @author linw
 *
 */
@RestController("api_customer_CustomerJobController")
@RequestMapping(value = "/api/customer/job", method = RequestMethod.POST, produces="application/json;charset=utf-8")
public class CustomerJobController extends BaseApiController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private CustomerJobService customerJobService;
	
	/**
	 * 客户 工作 查询
	 * 
     * @api {post} /api/customer/job/get 客户 工作 查询
     * @apiDescription 客户 工作 查询
     * @apiName customer_job_get
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"customerJobBean":{"customerId":2}}
     * 
     * @apiSuccessExample {json} 成功返回-示例:
     * 		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"msgs":[],"msg":{},"state":"0","data":{"customerJobBean":{"interest":"1","diet":"1,3","taste":"5"}}}
     */
	@RequestMapping(value = "/get")
	public RespBean get(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		CustomerJobBean customerJobBean = reqBean.getCustomerJobBean();
		
		Long customerId = null;
		if (customerJobBean != null) {
			customerId = customerJobBean.getCustomerId();
		}
		
		CustomerBean customerBeanResult = null;
		CustomerJobBean customerJobBeanResult = null;
		
		if (customerId != null) {
			CustomerBean customerBean = new CustomerBean();
			customerBean.setId(customerId);
			customerBean.setUserId(UserUtil.getCurrentUserId(request));
			customerBean = customerService.selectOne(customerBean);
			if (customerBean == null) AppException.toThrow(MSG_00003);
			
			customerBeanResult = new CustomerBean();
			customerBean.setRealName(customerBean.getRealName());
			customerBean.setPhone(customerBean.getPhone());
			customerBean.setBirthday(customerBean.getBirthday());
			customerBean.setSex(customerBean.getSex());
			Long areaId = customerBean.getAreaId();
			customerBean.setAreaId(areaId);
			customerBean.setAreaNameAll(AreaUtil.getNameAll(areaId));
			customerBean.setAddress(customerBean.getAddress());
			customerBean.setHeadPic(customerBean.getHeadPic());    // 获取图片域名
			
			CustomerJobBean customerJobBeanTmp = customerJobService.selectOne(customerJobBean);
			if (customerJobBeanTmp != null) {
				customerJobBeanResult = new CustomerJobBean();
				customerJobBeanResult.setIndustry(customerJobBeanTmp.getIndustry());
				customerJobBeanResult.setCompanyName(customerJobBeanTmp.getCompanyName());
				customerJobBeanResult.setDepartmentName(customerJobBeanTmp.getDepartmentName());
				customerJobBeanResult.setPosition(customerJobBeanTmp.getPosition());
				customerJobBeanResult.setPhone(customerJobBeanTmp.getPhone());
				Long jobAreaId = customerJobBeanTmp.getAreaId();
				customerJobBeanResult.setAreaId(jobAreaId);
				customerJobBeanResult.setAreaNameAll(AreaUtil.getNameAll(jobAreaId));
				customerJobBeanResult.setAddress(customerJobBeanTmp.getAddress());
			}
		}
		return RespUtil.build(request).putData("customerBean", customerBeanResult).putData("customerJobBean", customerJobBeanResult);
	}
	
	/**
	 * 客户 工作 修改
	 * 
     * @api {post} /api/customer/job/update 客户 工作 修改
     * @apiDescription 客户 工作 修改
     * @apiName customer_job_update
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"customerJobBean":{"customerId":2,"interest":"1","diet":"1,3","taste":null}}
     * 
     */
	@RequestMapping(value = "/update")
	public RespBean update(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		CustomerBean customerBean = reqBean.getCustomerBean();
		CustomerJobBean customerJobBean = reqBean.getCustomerJobBean();
		if (customerBean == null) AppException.toThrow(MSG_00003);
		if (customerJobBean == null) customerJobBean = new CustomerJobBean();
		Long currentUserId = UserUtil.getCurrentUserId(request);
		customerBean.setUserId(currentUserId);
		customerJobBean.setUserId(currentUserId);
		customerJobService.exe("update", ImmutableMap.of("customerBean", customerBean, "customerJobBean", customerJobBean));
		return RespUtil.build(request);
	}
}