package com.rmp.api.controller.customer;

import static com.rmp.api.util.MsgEnum.*;
import static com.rmp.api.util.constant.Constant.SysCode.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.rmp.api.base.controller.BaseApiController;
import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.model.ReqBean;
import com.rmp.api.base.model.RespBean;
import com.rmp.api.base.util.ReqUtil;
import com.rmp.api.base.util.RespUtil;
import com.rmp.api.model.CustomerBean;
import com.rmp.api.model.CustomerFamilyBean;
import com.rmp.api.model.CustomerHobbyBean;
import com.rmp.api.model.CustomerJobBean;
import com.rmp.api.model.CustomerMaintainBean;
import com.rmp.api.model.CustomerMemorialDayBean;
import com.rmp.api.model.CustomerProblemBean;
import com.rmp.api.model.CustomerRelationBean;
import com.rmp.api.service.customer.CustomerFamilyService;
import com.rmp.api.service.customer.CustomerHobbyService;
import com.rmp.api.service.customer.CustomerJobService;
import com.rmp.api.service.customer.CustomerMaintainService;
import com.rmp.api.service.customer.CustomerMemorialDayService;
import com.rmp.api.service.customer.CustomerProblemService;
import com.rmp.api.service.customer.CustomerRelationService;
import com.rmp.api.service.customer.CustomerService;
import com.rmp.api.util.CustomerFamilyUtil;
import com.rmp.api.util.CustomerHobbyUtil;
import com.rmp.api.util.CustomerJobUtil;
import com.rmp.api.util.CustomerMaintainUtil;
import com.rmp.api.util.CustomerMemorialDayUtil;
import com.rmp.api.util.CustomerProblemUtil;
import com.rmp.api.util.CustomerRelationUtil;
import com.rmp.api.util.CustomerUtil;
import com.rmp.api.util.SysCodeUtil;
import com.rmp.api.util.UserUtil;

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
	@Autowired
	private CustomerJobService customerJobService;
	@Autowired
	private CustomerRelationService customerRelationService;
	@Autowired
	private CustomerFamilyService customerFamilyService;
	@Autowired
	private CustomerHobbyService customerHobbyService;
	@Autowired
	private CustomerMemorialDayService customerMemorialDayService;
	@Autowired
	private CustomerMaintainService customerMaintainService;
	@Autowired
	private CustomerProblemService customerProblemService;
	
	/**
	 * 客户 配置
	 * 
     * @api {post} /api/customer/customer/config 客户 配置
     * @apiDescription 客户 配置
     * @apiName customer_customer_config
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"}}
     * 
     * @apiSuccess (data) {List} importanceCodeList 关系 code list
     * @apiSuccess (data) {List} intimacyCodeList 亲密 code list
     * @apiSuccess (data) {List} relationshipCodeList 重要 code list
     * @apiSuccess (data) {List} tagCodeList 标签 code list 暂无
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
	 * 客户 查询列表
	 * 
     * @api {post} /api/customer/customer/list 客户 列表查询
     * @apiDescription 客户 列表查询 
     * @apiName customer_customer_list
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParam (CustomerBean) {Object} customerBean 客户 bean
     * @apiParam (CustomerBean) {String} customerBean.realName 真实姓名
     * @apiParam (CustomerBean) {String} customerBean.phone 手机
     * @apiParam (CustomerRelationBean) {Object} customerBean.customerRelationBean 客户 关系 bean
     * @apiParam (CustomerRelationBean) {Integer} customerBean.customerRelationBean.relationship 重要
     * @apiParam (CustomerRelationBean) {Integer} customerBean.customerRelationBean.intimacy 亲密
     * @apiParam (CustomerRelationBean) {Integer} customerBean.customerRelationBean.importance 关系
     * 
     * @apiParamExample {json} 请求-示例: 
     * 		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"customerBean":{"realName":"s","phone":15111111112,"customerRelationBean":{"relationship":0,"intimacy":0,"importance":0}}}
     * 
     * @apiSuccess (data) {List} letters 字母 list
     * 
     * @apiSuccess (data) {List} groups 客户 字母分组 list
     * @apiSuccess (data) {String} groups.groupName 分组名称
     * @apiSuccess (data) {List} groups.users 客户 bean list
     * 
	 * @apiSuccess (data) {String} groups.users.realName 真实姓名
	 * @apiSuccess (data) {Long} groups.users.phone 手机
	 * @apiSuccess (data) {Integer} groups.users.sex 性别<br/>0:女<br/>1:男
	 * @apiSuccess (data) {String} groups.users.headPic 头像
	 * @apiSuccess (data) {Long} groups.users.areaId 区域ID
	 * @apiSuccess (data) {String} groups.users.areaNameAll 区域全称
     * @apiSuccess (data) {String} groups.users.address 地址
     * @apiSuccess (data) {Long} groups.users.areaId 区域ID
     * @apiSuccess (data) {String} groups.users.pinyin 拼音
     * @apiSuccess (data) {String} groups.users.pinyinFirst 拼音 首字母
     * 
     * @apiSuccess (data) {Object} groups.users.customerRelationBean 客户 关系 bean
	 * @apiSuccess (data) {Integer} groups.users.customerRelationBean.importance 关系
	 * @apiSuccess (data) {String} groups.users.customerRelationBean.importanceValue 关系 值
	 * @apiSuccess (data) {Integer} groups.users.customerRelationBean.intimacy 亲密
	 * @apiSuccess (data) {String} groups.users.customerRelationBean.intimacyValue 亲密 值
	 * @apiSuccess (data) {Integer} groups.users.customerRelationBean.relationship 重要
	 * @apiSuccess (data) {String} groups.users.customerRelationBean.relationshipValue 重要 值
     * 
     * @apiSuccessExample {json} 成功返回-示例:
     * 		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"msgs":[],"msg":{},"state":"0","data":{"groups":[{"groupName":"a","users":[{"pinyinFirst":"a","realName":"ttt","pinyin":"att","phone":15111111111,"sex":0,"birthday":19900610,"headPic":"https://img.rmp.com/xxx/pic.jpg","address":"aaaaaaaaaaaaaa"}]},{"groupName":"s","users":[{"customerRelationBean":{"relationshipValue":"其他","intimacyValue":"不详","importanceValue":"不重要","relationship":0,"intimacy":0,"importance":0},"pinyinFirst":"s","realName":"ss","pinyin":"ss","phone":15111111112,"sex":1,"birthday":20100302,"headPic":"https://img.rmp.com/img/head_pic/default.jpg","address":"ttt"}]},{"groupName":"t","users":[{"pinyinFirst":"t","realName":"ttt","pinyin":"ttt","phone":15111111113,"sex":0,"birthday":20100101,"headPic":"https://img.rmp.com/xxx/pic.jpg","address":"aaaaaaaaaaaaaa"}]}],"letters":["a","s","t"]}}
     */
	@RequestMapping(value = "/list")
	public RespBean list(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		CustomerBean customerBean = reqBean.getCustomerBean();
		if (customerBean == null) customerBean = CustomerBean.builder().build();
		customerBean.setUserId(UserUtil.getCurrentUserId(request));
		/*
		QueryPage queryPage = reqBean.getQueryPage();
		if (queryPage == null) queryPage = new QueryPage();
		*/
		List<String> letters = null;
		List<Map<String, Object>> groups = null;
		
		List<CustomerBean> customerBeanListResult = null;
		List<CustomerBean> customerBeanList = customerService.selectListCustom(null, customerBean);
		if (!CollectionUtils.isEmpty(customerBeanList)) {
			customerBeanListResult = Lists.newArrayList();
			for (CustomerBean customerBeanTmp : customerBeanList) {
				if (customerBeanTmp == null) continue;
				CustomerBean customerBeanResult = CustomerBean.builder()
				.realName(customerBeanTmp.getRealName())
				.phone(customerBeanTmp.getPhone())
				.birthday(customerBeanTmp.getBirthday())
				.sex(customerBeanTmp.getSex())
				.areaId(customerBean.getAreaId())
				.address(customerBeanTmp.getAddress())
				.headPic(customerBeanTmp.getHeadPic())    // 获取图片域名
				.pinyin(customerBeanTmp.getPinyin())
				.build();
				CustomerUtil.assembly(customerBeanResult);
				
				CustomerRelationBean customerRelationBeanResult = null;
				CustomerRelationBean customerRelationBean = customerBeanTmp.getCustomerRelationBean();
				if (customerRelationBean != null) {
					customerRelationBeanResult = CustomerRelationBean.builder()
					.relationship(customerRelationBean.getRelationship())
					.intimacy(customerRelationBean.getIntimacy())
					.importance(customerRelationBean.getImportance())
					.build();
					CustomerRelationUtil.assembly(customerRelationBeanResult);
					customerBeanResult.setCustomerRelationBean(customerRelationBeanResult);
				}
				customerBeanListResult.add(customerBeanResult);
			}
			
			letters = customerBeanListResult.stream().map(CustomerBean::getPinyinFirst).distinct().sorted().collect(Collectors.toList());
			
			groups = Lists.newArrayList();
			for (Map.Entry<String, List<CustomerBean>> entry : customerBeanListResult.stream().collect(Collectors.groupingBy(CustomerBean::getPinyinFirst, Collectors.toList())).entrySet()) {
				groups.add(ImmutableMap.of("groupName", entry.getKey(), "users", entry.getValue()));
			}
		}
		return RespUtil.build(request).putData("groups", groups).putData("letters", letters);
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
     * @apiParam (CustomerBean) {Object} customerBean 客户 bean
     * @apiParam (CustomerBean) {Long} customerBean.id ID
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"customerBean":{"id":2}}
     * 
     * @apiSuccess (data) {Object} customerBean 客户 bean
     * @apiSuccess (data) {Object} customerJobBean 客户 工作 bean
	 * @apiSuccess (data) {List} customerFamilyBeanList 客户 家庭 bean list
     * @apiSuccess (data) {Object} customerHobbyBean 客户 兴趣爱好 bean
     * @apiSuccess (data) {Object} customerMaintainBean 客户 维护设置 bean
     * @apiSuccess (data) {List} customerMemorialDayBeanList 客户 纪念日 bean list
     * @apiSuccess (data) {Object} customerProblemBean 客户 可能问题 bean
     * @apiSuccess (data) {Object} customerRelationBean 客户 关系 bean
     * 
     * @apiSuccessExample {json} 成功返回-示例:
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"msgs":[],"msg":{},"state":"0","data":{"customerHobbyBean":{"interestKeyList":["1"],"dietKeyList":["1","3"],"tasteKeyList":["4"],"interestValueList":["美食"],"dietValueList":["川湘菜","粤菜"],"tasteValueList":["苦"],"interest":"1","diet":"1,3","taste":"4"},"customerFamilyBeanList":[{"areaNameAll":"江苏省泰州市","relationshipValue":"母亲","id":2,"relationship":2,"realName":"xxx","birthday":20100101,"phone":15111111111,"areaId":321200,"address":"ttt"}],"customerMemorialDayBeanList":[{"occurTypeValue":"1次","advanceTypeValue":"1天","id":2,"name":"ttttt2","occurType":1,"occurDate":20181030,"advanceType":1},{"occurTypeValue":"1次","advanceTypeValue":"1天","id":1,"name":"ttttt2","occurType":1,"occurDate":20181030,"advanceType":1}],"customerJobBean":{"areaNameAll":"江苏省泰州市","industryValue":"互联网/电子商务/网游","positionValue":"IT管理","industry":2,"companyName":"aaaa","departmentName":"bbb","position":3,"phone":15111111111,"areaId":321200,"address":"aaaaaaaaaaaaaa"},"customerProblemBean":{"healthKeyList":["1"],"lifeKeyList":["1","3"],"healthValueList":["心脏病"],"lifeValueList":["资金缺乏","事业发展"],"health":"1","life":"1,3","remark":"xxxxxxxxxTTT"},"customerRelationBean":{"relationshipValue":"其他","intimacyValue":"不详","importanceValue":"不重要","relationship":0,"intimacy":0,"importance":0},"customerBean":{"areaNameAll":"江苏省泰州市","realName":"ss","phone":15111111112,"sex":1,"birthday":20100101,"headPic":"https://img.rmp.com/img/head_pic/default.jpg","areaId":321200,"address":"ttt","vip":0}}}
     * 
     */
	@RequestMapping(value = "/get")
	public RespBean get(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		CustomerBean customerBean = reqBean.getCustomerBean();
		if (customerBean == null) AppException.toThrow(MSG_00003);
		Long id = customerBean.getId();
		if (id == null) AppException.toThrow(MSG_00003);
		
		CustomerBean customerBeanTmp = customerService.selectById(id);
		if (customerBeanTmp == null) AppException.toThrow(MSG_00003);
		
		CustomerBean customerBeanResult = new CustomerBean();
		CustomerJobBean customerJobBeanResult = null;
		List<CustomerFamilyBean> customerFamilyBeanListResult = null;
		CustomerHobbyBean customerHobbyBeanResult = null;
		CustomerMaintainBean customerMaintainBeanResult = null;
		List<CustomerMemorialDayBean> customerMemorialDayBeanListResult = null;
		CustomerProblemBean customerProblemBeanResult = null;
		CustomerRelationBean customerRelationBeanResult = null;
		
		customerBeanResult.setRealName(customerBeanTmp.getRealName());
		customerBeanResult.setPhone(customerBeanTmp.getPhone());
		customerBeanResult.setBirthday(customerBeanTmp.getBirthday());
		customerBeanResult.setSex(customerBeanTmp.getSex());
		customerBeanResult.setAreaId(customerBeanTmp.getAreaId());
		customerBeanResult.setAddress(customerBeanTmp.getAddress());
		customerBeanResult.setHeadPic(customerBeanTmp.getHeadPic());
		customerBeanResult.setVip(customerBeanTmp.getVip());
		CustomerUtil.assembly(customerBeanResult);
		
		// 工作
		CustomerJobBean customerJobBean = new CustomerJobBean();
		customerJobBean.setCustomerId(id);
		CustomerJobBean customerJobBeanTmp = customerJobService.selectOne(customerJobBean);
		if (customerJobBeanTmp != null) {
			customerJobBeanResult = new CustomerJobBean();
			customerJobBeanResult.setIndustry(customerJobBeanTmp.getIndustry());
			customerJobBeanResult.setCompanyName(customerJobBeanTmp.getCompanyName());
			customerJobBeanResult.setDepartmentName(customerJobBeanTmp.getDepartmentName());
			customerJobBeanResult.setPosition(customerJobBeanTmp.getPosition());
			customerJobBeanResult.setPhone(customerJobBeanTmp.getPhone());
			customerJobBeanResult.setAreaId(customerJobBeanTmp.getAreaId());
			customerJobBeanResult.setAddress(customerJobBeanTmp.getAddress());
			CustomerJobUtil.assembly(customerJobBeanResult);
		}
		
		// 家庭
		CustomerFamilyBean customerFamilyBean = CustomerFamilyBean.builder().customerId(id).build();
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
		
		// 兴趣爱好
		CustomerHobbyBean customerHobbyBean = new CustomerHobbyBean();
		customerHobbyBean.setCustomerId(id);
		CustomerHobbyBean customerHobbyBeanTmp = customerHobbyService.selectOne(customerHobbyBean);
		if (customerHobbyBeanTmp != null) {
			customerHobbyBeanResult = new CustomerHobbyBean();
			customerHobbyBeanResult.setInterest(customerHobbyBeanTmp.getInterest());
			customerHobbyBeanResult.setDiet(customerHobbyBeanTmp.getDiet());
			customerHobbyBeanResult.setTaste(customerHobbyBeanTmp.getTaste());
			CustomerHobbyUtil.assembly(customerHobbyBeanResult);
		}
		
		// 维护设置
		CustomerMaintainBean customerMaintainBean = new CustomerMaintainBean();
		customerMaintainBean.setCustomerId(id);
		CustomerMaintainBean customerMaintainBeanTmp = customerMaintainService.selectOne(customerMaintainBean);
		if (customerMaintainBeanTmp != null) {
			customerMaintainBeanResult = new CustomerMaintainBean();
			customerMaintainBeanResult.setMaintain(customerMaintainBeanTmp.getMaintain());
			CustomerMaintainUtil.assembly(customerMaintainBeanResult);
		}
		
		// 纪念日
		CustomerMemorialDayBean customerMemorialDayBean = new CustomerMemorialDayBean();
		customerMemorialDayBean.setCustomerId(id);
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
		
		// 可能问题
		CustomerProblemBean customerProblemBean = new CustomerProblemBean();
		customerProblemBean.setCustomerId(id);
		CustomerProblemBean customerProblemBeanTmp = customerProblemService.selectOne(customerProblemBean);
		if (customerProblemBeanTmp != null) {
			customerProblemBeanResult = new CustomerProblemBean();
			customerProblemBeanResult.setHealth(customerProblemBeanTmp.getHealth());
			customerProblemBeanResult.setLife(customerProblemBeanTmp.getLife());
			customerProblemBeanResult.setRemark(customerProblemBeanTmp.getRemark());
			CustomerProblemUtil.assembly(customerProblemBeanResult);
		}
		
		// 关系
		CustomerRelationBean customerRelationBean = new CustomerRelationBean();
		customerRelationBean.setCustomerId(id);
		CustomerRelationBean customerRelationBeanTmp = customerRelationService.selectOne(customerRelationBean);
		if (customerRelationBeanTmp != null) {
			customerRelationBeanResult = new CustomerRelationBean();
			customerRelationBeanResult.setRelationship(customerRelationBeanTmp.getRelationship());
			customerRelationBeanResult.setIntimacy(customerRelationBeanTmp.getIntimacy());
			customerRelationBeanResult.setImportance(customerRelationBeanTmp.getImportance());
			CustomerRelationUtil.assembly(customerRelationBeanResult);
		}
		
		return RespUtil.build(request).putData("customerBean", customerBeanResult)
				.putData("customerJobBean", customerJobBeanResult)
				.putData("customerFamilyBeanList", customerFamilyBeanListResult)
				.putData("customerHobbyBean", customerHobbyBeanResult)
				.putData("customerMaintainBean", customerMaintainBeanResult)
				.putData("customerMemorialDayBeanList", customerMemorialDayBeanListResult)
				.putData("customerProblemBean", customerProblemBeanResult)
				.putData("customerRelationBean", customerRelationBeanResult);
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
     * @apiParam (CustomerBean) {Object} customerBean 客户 bean
     * @apiParam (CustomerBean) {String} customerBean.realName 真实姓名
     * @apiParam (CustomerBean) {Long} customerBean.phone 手机
     * 
     * @apiParamExample {json} 请求-示例: 
     * 		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"customerBean":{"realName":"ss","phone":"15111111111"}}
     *
     */
	@RequestMapping(value = "/save")
	public RespBean save(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		CustomerBean customerBean = reqBean.getCustomerBean();
		if (customerBean == null) AppException.toThrow(MSG_00003);
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
     * @apiParam (CustomerBean) {Object} customerBean 客户 bean
     * @apiParam (CustomerBean) {Long} customerBean.id ID
     * @apiParam (CustomerBean) {String} customerBean.realName 真实姓名
     * @apiParam (CustomerBean) {Long} customerBean.phone 手机
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"customerBean":{"id":1,"realName":"aa","phone":"15111111116"}}
     * 
     */
	@RequestMapping(value = "/update")
	public RespBean update(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		CustomerBean customerBean = reqBean.getCustomerBean();
		if (customerBean == null) AppException.toThrow(MSG_00003);
		customerBean.setUserId(UserUtil.getCurrentUserId(request));
		customerService.exe("update", customerBean);
		return RespUtil.build(request);
	}
	
	/**
	 * 客户 删除
	 * 
     * @api {post} /api/customer/customer/delete 客户 删除
     * @apiDescription 客户 删除
     * @apiName customer_customer_delete
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParam (CustomerBean) {Object} customerBean 客户 bean
     * @apiParam (CustomerBean) {Long} customerBean.id ID
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"customerBean":{"id":1}}
     * 
     */
	@RequestMapping(value = "/delete")
	public RespBean delete(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		CustomerBean customerBean = reqBean.getCustomerBean();
		if (customerBean == null) AppException.toThrow(MSG_00003);
		customerBean.setUserId(UserUtil.getCurrentUserId(request));
		customerService.exe("delete", customerBean);
		return RespUtil.build(request);
	}
}