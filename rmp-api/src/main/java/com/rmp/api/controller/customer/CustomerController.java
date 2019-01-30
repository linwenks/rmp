package com.rmp.api.controller.customer;

import static com.rmp.api.util.MsgEnum.*;
import static com.rmp.api.util.constant.Constant.SysCode.*;

import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.rmp.api.base.controller.BaseApiController;
import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.model.HeaderBean;
import com.rmp.api.base.model.ReqBean;
import com.rmp.api.base.model.RespBean;
import com.rmp.api.base.util.ReqUtil;
import com.rmp.api.base.util.RespUtil;
import com.rmp.api.model.CustomerBean;
import com.rmp.api.model.CustomerDetailBean;
import com.rmp.api.model.CustomerFamilyBean;
import com.rmp.api.model.CustomerHobbyBean;
import com.rmp.api.model.CustomerJobBean;
import com.rmp.api.model.CustomerMaintainBean;
import com.rmp.api.model.CustomerMemorialDayBean;
import com.rmp.api.model.CustomerProblemBean;
import com.rmp.api.model.CustomerRelationBean;
import com.rmp.api.model.UserBean;
import com.rmp.api.model.UserRemindBean;
import com.rmp.api.service.customer.CustomerDetailService;
import com.rmp.api.service.customer.CustomerFamilyService;
import com.rmp.api.service.customer.CustomerHobbyService;
import com.rmp.api.service.customer.CustomerJobService;
import com.rmp.api.service.customer.CustomerMaintainService;
import com.rmp.api.service.customer.CustomerMemorialDayService;
import com.rmp.api.service.customer.CustomerProblemService;
import com.rmp.api.service.customer.CustomerRelationService;
import com.rmp.api.service.customer.CustomerService;
import com.rmp.api.service.user.UserRemindService;
import com.rmp.api.util.CustomerDetailUtil;
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
import com.rmp.api.util.constant.Constant;
import com.rmp.api.util.upload.UploadBean;
import com.rmp.api.util.upload.UploadUtils;
import com.rmp.common.util.DateUtil;

/**
 * 客户 json controller
 * @author linw
 *
 */
@RestController("api_customer_CustomerController")
@RequestMapping(value = "/api/customer/customer", method = RequestMethod.POST)
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
	@Autowired
	private CustomerDetailService customerDetailService;
	@Autowired
	private UserRemindService userRemindService;
	
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
				.putData("importanceCodeList", SysCodeUtil.getChildSimple(CUSTOMER, CUSTOMER_RELATION, CUSTOMER_RELATION_IMPORTANCE))
				.putData("intimacyCodeList", SysCodeUtil.getChildSimple(CUSTOMER, CUSTOMER_RELATION, CUSTOMER_RELATION_INTIMACY))
				.putData("relationshipCodeList", SysCodeUtil.getChildSimple(CUSTOMER, CUSTOMER_RELATION, CUSTOMER_RELATION_RELATIONSHIP));
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
     * @apiSuccess (data) {Long} groups.users.id 客户ID
	 * @apiSuccess (data) {String} groups.users.realName 真实姓名
	 * @apiSuccess (data) {Long} groups.users.phone 手机
	 * @apiSuccess (data) {Integer} groups.users.birthday 生日
	 * @apiSuccess (data) {String} groups.users.birthdayStr 生日 yyyy-MM-dd
	 * @apiSuccess (data) {Integer} groups.users.sex 性别<br/>0:女<br/>1:男
	 * @apiSuccess (data) {String} groups.users.headPic 头像
	 * @apiSuccess (data) {String} groups.users.area 区域
	 * @apiSuccess (data) {String} groups.users.areaNameAll 区域全称
     * @apiSuccess (data) {String} groups.users.address 地址
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
     * 		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"msgs":[],"msg":{},"state":"0","data":{"groups":[{"groupName":"s","users":[{"pinyinFirst":"s","id":6,"realName":"ss","pinyin":"ss","phone":15111111115,"headPic":"https://img.rmp.com/img/head_pic/default.jpg"},{"customerRelationBean":{"relationshipValue":"其他","intimacyValue":"不详","importanceValue":"不重要","relationship":0,"intimacy":0,"importance":0},"pinyinFirst":"s","id":2,"realName":"ss","pinyin":"ss","phone":15111111112,"sex":1,"birthday":20100101,"birthdayStr":"2010-01-01","headPic":"https://img.rmp.com/img/head_pic/default.jpg","address":"ttt"}]},{"groupName":"t","users":[{"pinyinFirst":"t","id":5,"realName":"ttt","pinyin":"ttt","phone":15111111113,"sex":0,"birthday":20100101,"birthdayStr":"2010-01-01","headPic":"https://img.rmp.com/xxx/pic.jpg","address":"aaaaaaaaaaaaaa"},{"pinyinFirst":"t","id":4,"realName":"ttt","pinyin":"ttt","phone":15111111111,"sex":0,"headPic":"https://img.rmp.com/xxx/pic.jpg","address":"aaaaaaaaaaaaaa"}]},{"groupName":"d","users":[{"pinyinFirst":"d","id":8,"realName":"ddd","pinyin":"ddd","phone":13658327488,"headPic":"https://img.rmp.com/img/head_pic/default.jpg"}]}],"letters":["d","s","t"]}}
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
				.id(customerBeanTmp.getId())
				.realName(customerBeanTmp.getRealName())
				.phone(customerBeanTmp.getPhone())
				.birthday(customerBeanTmp.getBirthday())
				.sex(customerBeanTmp.getSex())
				.area(customerBean.getArea())
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
     * @apiSuccess (data) {Object} customerDetailBean 客户 明细 bean
     * @apiSuccess (data) {List} beanuserRemindBeanList 客户 提醒 bean
     * 
     * @apiSuccessExample {json} 成功返回-示例:
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"msgs":[],"msg":{},"state":"0","data":{"userRemindBeanList":[{"content":"客户生日","customerBean":{"id":62,"realName":"ttt","sex":0,"headPic":"/xxx/pic.jpg"},"advanceDay":4,"remindDate":20190113},{"content":"客户生日","customerBean":{"id":62,"realName":"ttt","sex":0,"headPic":"/xxx/pic.jpg"},"advanceDay":3,"remindDate":20190113},{"content":"客户生日","customerBean":{"id":62,"realName":"ttt","sex":0,"headPic":"/xxx/pic.jpg"},"advanceDay":2,"remindDate":20190113},{"content":"客户生日","customerBean":{"id":62,"realName":"ttt","sex":0,"headPic":"/xxx/pic.jpg"},"advanceDay":1,"remindDate":20190113},{"content":"客户生日","customerBean":{"id":62,"realName":"ttt","sex":0,"headPic":"/xxx/pic.jpg"},"advanceDay":0,"remindDate":20190113}],"customerHobbyBean":{"interestKeyList":["1"],"dietKeyList":["1","3"],"tasteKeyList":["4"],"interestValueList":["美食"],"dietValueList":["川湘菜","粤菜"],"tasteValueList":["苦"],"interest":"1","diet":"1,3","taste":"4"},"customerFamilyBeanList":[{"areaNameAll":"江苏省泰州市","relationshipValue":"母亲","id":2,"relationship":2,"realName":"xxx","birthday":20100101,"birthdayStr":"2010-01-01","phone":15111111111,"area":"重庆市九龙坡区","address":"ttt"}],"customerMemorialDayBeanList":[{"occurTypeValue":"1次","advanceTypeValue":"1天","id":2,"name":"ttttt2","occurType":1,"occurDate":20181030,"advanceType":1},{"occurTypeValue":"1次","advanceTypeValue":"1天","id":1,"name":"ttttt2","occurType":1,"occurDate":20181030,"advanceType":1}],"customerJobBean":{"areaNameAll":"江苏省泰州市","industryValue":"互联网/电子商务/网游","positionValue":"IT管理","industry":2,"companyName":"aaaa","departmentName":"bbb","position":3,"office":"xxxxxxx","phone":15111111111,"area":"重庆市九龙坡区","address":"aaaaaaaaaaaaaa"},"customerProblemBean":{"healthKeyList":["1"],"lifeKeyList":["1","3"],"healthValueList":["心脏病"],"lifeValueList":["资金缺乏","事业发展"],"health":"1","life":"1,3","remark":"xxxxxxxxxTTT"},"customerRelationBean":{"relationshipValue":"其他","intimacyValue":"不详","importanceValue":"不重要","relationship":0,"intimacy":0,"importance":0},"customerBean":{"areaNameAll":"江苏省泰州市","realName":"ss","phone":15111111112,"sex":1,"birthday":20100101,"birthdayStr":"2010-01-01","headPic":"https://img.rmp.com/img/head_pic/default.jpg","area":"重庆市九龙坡区","address":"ttt","vip":0},"customerDetailBean":{"remark":"xxxxxxxxxTTT"}}}
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
		
		CustomerJobBean customerJobBeanResult = null;
		List<CustomerFamilyBean> customerFamilyBeanListResult = null;
		CustomerHobbyBean customerHobbyBeanResult = null;
		CustomerMaintainBean customerMaintainBeanResult = null;
		List<CustomerMemorialDayBean> customerMemorialDayBeanListResult = null;
		CustomerProblemBean customerProblemBeanResult = null;
		CustomerRelationBean customerRelationBeanResult = null;
		CustomerDetailBean customerDetailBeanResult = null;
		
		CustomerBean customerBeanResult = CustomerBean.builder()
		.realName(customerBeanTmp.getRealName())
		.phone(customerBeanTmp.getPhone())
		.birthday(customerBeanTmp.getBirthday())
		.sex(customerBeanTmp.getSex())
		.area(customerBeanTmp.getArea())
		.address(customerBeanTmp.getAddress())
		.headPic(customerBeanTmp.getHeadPic())
		.vip(customerBeanTmp.getVip())
		.build();
		CustomerUtil.assembly(customerBeanResult);
		
		Date nowDate = DateUtil.now();
		Integer ymd = Integer.valueOf(DateUtil.formatDate(nowDate, DateUtil.yyyyMMdd));
		
		// 提醒
		UserRemindBean userRemindBean = UserRemindBean.builder().advanceDate(ymd).userId(UserUtil.getCurrentUserId(request)).customerId(id).build();
		List<UserRemindBean> userRemindBeanListTmp = userRemindService.selectListCustom(null, userRemindBean);
		if (!CollectionUtils.isEmpty(userRemindBeanListTmp)) {
			userRemindBeanListTmp.forEach(bean -> {
				bean.setAdvanceDate(null);
				CustomerRelationUtil.assembly(bean.getCustomerRelationBean());
			});
		}
		
		// 工作
		CustomerJobBean customerJobBean = CustomerJobBean.builder().customerId(id).build();
		CustomerJobBean customerJobBeanTmp = customerJobService.selectOne(customerJobBean);
		if (customerJobBeanTmp != null) {
			customerJobBeanResult = CustomerJobBean.builder()
			.industry(customerJobBeanTmp.getIndustry())
			.companyName(customerJobBeanTmp.getCompanyName())
			.departmentName(customerJobBeanTmp.getDepartmentName())
			.position(customerJobBeanTmp.getPosition())
			.office(customerJobBeanTmp.getOffice())
			.phone(customerJobBeanTmp.getPhone())
			.area(customerJobBeanTmp.getArea())
			.address(customerJobBeanTmp.getAddress())
			.build();
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
				.area(customerFamilyBeanTmp.getArea())
				.address(customerFamilyBeanTmp.getAddress())
				.build();
				CustomerFamilyUtil.assembly(customerFamilyBeanResult);
				customerFamilyBeanListResult.add(customerFamilyBeanResult);
			}
		}
		
		// 兴趣爱好
		CustomerHobbyBean customerHobbyBean = CustomerHobbyBean.builder().customerId(id).build();
		CustomerHobbyBean customerHobbyBeanTmp = customerHobbyService.selectOne(customerHobbyBean);
		if (customerHobbyBeanTmp != null) {
			customerHobbyBeanResult = CustomerHobbyBean.builder()
			.interest(customerHobbyBeanTmp.getInterest())
			.diet(customerHobbyBeanTmp.getDiet())
			.taste(customerHobbyBeanTmp.getTaste())
			.build();
			CustomerHobbyUtil.assembly(customerHobbyBeanResult);
		}
		
		// 维护设置
		CustomerMaintainBean customerMaintainBean = CustomerMaintainBean.builder().customerId(id).build();
		CustomerMaintainBean customerMaintainBeanTmp = customerMaintainService.selectOne(customerMaintainBean);
		if (customerMaintainBeanTmp != null) {
			customerMaintainBeanResult = CustomerMaintainBean.builder()
			.maintain(customerMaintainBeanTmp.getMaintain())
			.build();
			CustomerMaintainUtil.assembly(customerMaintainBeanResult);
		}
		
		// 纪念日
		CustomerMemorialDayBean customerMemorialDayBean = CustomerMemorialDayBean.builder().customerId(id).build();
		List<CustomerMemorialDayBean> customerMemorialDayBeanListTmp = customerMemorialDayService.selectList(null, customerMemorialDayBean);
		if (!CollectionUtils.isEmpty(customerMemorialDayBeanListTmp)) {
			customerMemorialDayBeanListResult = Lists.newArrayList();
			for (CustomerMemorialDayBean customerMemorialDayBeanTmp : customerMemorialDayBeanListTmp) {
				CustomerMemorialDayBean customerMemorialDayBeanResult = CustomerMemorialDayBean.builder()
				.id(customerMemorialDayBeanTmp.getId())
				.name(customerMemorialDayBeanTmp.getName())
				.occurType(customerMemorialDayBeanTmp.getOccurType())
				.occurDate(customerMemorialDayBeanTmp.getOccurDate())
				.advanceType(customerMemorialDayBeanTmp.getAdvanceType())
				.build();
				CustomerMemorialDayUtil.assembly(customerMemorialDayBeanResult);
				customerMemorialDayBeanListResult.add(customerMemorialDayBeanResult);
			}
		}
		
		// 可能问题
		CustomerProblemBean customerProblemBean = CustomerProblemBean.builder().customerId(id).build();
		CustomerProblemBean customerProblemBeanTmp = customerProblemService.selectOne(customerProblemBean);
		if (customerProblemBeanTmp != null) {
			customerProblemBeanResult = CustomerProblemBean.builder()
			.health(customerProblemBeanTmp.getHealth())
			.life(customerProblemBeanTmp.getLife())
			.remark(customerProblemBeanTmp.getRemark())
			.build();
			CustomerProblemUtil.assembly(customerProblemBeanResult);
		}
		
		// 关系
		CustomerRelationBean customerRelationBean = CustomerRelationBean.builder().customerId(id).build();
		CustomerRelationBean customerRelationBeanTmp = customerRelationService.selectOne(customerRelationBean);
		if (customerRelationBeanTmp != null) {
			customerRelationBeanResult = CustomerRelationBean.builder()
			.relationship(customerRelationBeanTmp.getRelationship())
			.intimacy(customerRelationBeanTmp.getIntimacy())
			.importance(customerRelationBeanTmp.getImportance())
			.build();
			CustomerRelationUtil.assembly(customerRelationBeanResult);
		}
		
		// 明细
		CustomerDetailBean customerDetailBean = CustomerDetailBean.builder().customerId(id).build();
		CustomerDetailBean customerDetailBeanTmp = customerDetailService.selectOne(customerDetailBean);
		if (customerDetailBeanTmp != null) {
			customerDetailBeanResult = CustomerDetailBean.builder()
			.remark(customerDetailBeanTmp.getRemark())
			.build();
			CustomerDetailUtil.assembly(customerDetailBeanResult);
		}
		
		return RespUtil.build(request)
				.putData("customerBean", customerBeanResult)
				.putData("customerJobBean", customerJobBeanResult)
				.putData("customerFamilyBeanList", customerFamilyBeanListResult)
				.putData("customerHobbyBean", customerHobbyBeanResult)
				.putData("customerMaintainBean", customerMaintainBeanResult)
				.putData("customerMemorialDayBeanList", customerMemorialDayBeanListResult)
				.putData("customerProblemBean", customerProblemBeanResult)
				.putData("customerRelationBean", customerRelationBeanResult)
				.putData("customerDetailBean", customerDetailBeanResult)
				.putData("userRemindBeanList", userRemindBeanListTmp);
		
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
     * @apiParam (CustomerBean) {String} [customerBean.headPic] 头像
     * 
     * @apiParamExample {json} 请求-示例: 
     * 		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"customerBean":{"realName":"ss","phone":"15111111111"}}
     *
     * @apiSuccess (data) {Object} customerBean 客户 bean
     * @apiSuccess (data) {Long} customerBean.id ID
     * @apiSuccess (data) {String} customerBean.realName 真实姓名
     * @apiSuccess (data) {Long} customerBean.phone 手机
     * @apiSuccess (data) {String} customerBean.headPic 头像
     * 
     * @apiSuccessExample {json} 成功返回-示例:
	 * 		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"msgs":[],"msg":{},"state":"0","data":{"customerBean":{"id":60,"realName":"ss","phone":15111111113,"headPic":"http://47.94.5.205/img/head_pic/default.jpg"}}}
     *
     */
	@RequestMapping(value = "/save")
	public RespBean save(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		CustomerBean customerBean = reqBean.getCustomerBean();
		if (customerBean == null) AppException.toThrow(MSG_00003);
		customerBean.setUserId(UserUtil.getCurrentUserId(request));
		customerService.exe("save", customerBean);
		CustomerUtil.assembly(customerBean);
		return RespUtil.build(request).putData("customerBean", CustomerBean.builder()
				.id(customerBean.getId())
				.phone(customerBean.getPhone())
				.realName(customerBean.getRealName())
				.headPic(customerBean.getHeadPic())
				.build());
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
     * @apiParam (CustomerBean) {String} [customerBean.headPic] 头像
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"customerBean":{"id":60,"realName":"ss","phone":15111111113,"headPic":"http://47.94.5.205/tmp/customer/head_pic/20181231/1444000280787608578.png"}}
     * 
     * @apiSuccess (data) {Object} customerBean 客户 bean
     * @apiSuccess (data) {Long} customerBean.id ID
     * @apiSuccess (data) {String} customerBean.realName 真实姓名
     * @apiSuccess (data) {Long} customerBean.phone 手机
     * @apiSuccess (data) {String} customerBean.headPic 头像
     * 
     * @apiSuccessExample {json} 成功返回-示例:
	 * 		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"msgs":[],"msg":{},"state":"0","data":{"customerBean":{"id":60,"realName":"ss","phone":15111111113,"headPic":"http://47.94.5.205/customer/head_pic/20181231/1444000280787608578.png"}}}
     *
     */
	@RequestMapping(value = "/update")
	public RespBean update(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		CustomerBean customerBean = reqBean.getCustomerBean();
		if (customerBean == null) AppException.toThrow(MSG_00003);
		customerBean.setUserId(UserUtil.getCurrentUserId(request));
		customerService.exe("update", customerBean);
		CustomerUtil.assembly(customerBean);
		return RespUtil.build(request).putData("customerBean", CustomerBean.builder()
				.id(customerBean.getId())
				.phone(customerBean.getPhone())
				.realName(customerBean.getRealName())
				.headPic(customerBean.getHeadPic())
				.build());
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
	
	/**
	 * 客户 上传 头像
	 * 
     * @api {post} /api/customer/customer/uploadHeadPic 客户 上传 头像
     * @apiDescription 客户 上传 头像 表单提交
     * @apiName customer_customer_uploadHeadPic
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParam (Form) {String} token token
     * @apiParam (Form) {File} headPicFile 头像文件
     * 
     * @apiSuccessExample {json} 成功返回-示例:
	 *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"msgs":[],"msg":{},"state":"0","data":{"customerBean":{"headPic":"http://47.94.5.205/customer/head_pic/20181127/1114000029789874659.jpg"}}}
	 * 
     */
	@RequestMapping(value = "/uploadHeadPic")
	public RespBean uploadHeadPic(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String token = param.getOrDefault("token", "").toString();
		ReqUtil.checkToken(token, request, true);
		request.setAttribute(Constant.CURRENT_REQUEST_HEADER, HeaderBean.builder().token(token).build());
		
		UploadUtils uploadUtils = new UploadUtils();
		uploadUtils.setShowSizeType(UploadUtils.MB);
		// 最大限制
		Long maxSize = Long.valueOf(10 * 1024 * 1024);
		uploadUtils.setMaxSize(maxSize);
		// 后缀名限制
		String suffixType = "jpg,jpeg,png,gif";
		uploadUtils.setSuffixStr(suffixType);
		
		List<UploadBean> uploadBeanList = uploadUtils.getUploadFiles(request, "headPicFile");
		if (CollectionUtils.isEmpty(uploadBeanList)) AppException.toThrow(MSG_00003);
		UploadBean uploadBean = uploadBeanList.get(0);
		if (uploadBean == null) AppException.toThrow(MSG_00003);
		MultipartFile headPicMultipartFile = uploadBean.getMultipartFile();
		if (headPicMultipartFile == null) AppException.toThrow(MSG_00003);
		
		// 上传路径
		uploadUtils.saveUploadFiles(uploadBean, Constant.uploadTopPath(), Constant.uploadPath(Constant.UPLOAD_CUSTOMER_HEAD_PIC_PATH_TMP, DateUtil.yyyyMMdd));
		String headPic = uploadBean.getPath();
		
		return RespUtil.build(request).putData("customerBean", CustomerBean.builder().headPic(Constant.imgDomain() + headPic).build());
	}
	
	/**
	 * 客户 修改 头像
	 * 
     * @api {post} /api/customer/customer/updateHeadPic 客户 修改 头像
     * @apiDescription 客户 修改 头像
     * @apiName customer_customer_updateHeadPic
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParam (UserBean) {Object} customerBean 用户 bean
     * @apiParam (UserBean) {String} customerBean.id 客户ID
     * @apiParam (UserBean) {String} customerBean.headPic 头像
     * 
     * @apiParamExample {json} 请求-示例: 
	 *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"customerBean":{"id":1,"headPic":"http://47.94.5.205/tmp/customer/head_pic/20181127/1114000029789874659.jpg"}}
	 * 
	 * @apiSuccessExample {json} 成功返回-示例:
	 * 		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"msgs":[],"msg":{},"state":"0","data":{"customerBean":{"headPic":"http://47.94.5.205/customer/head_pic/20181127/1114000029789874659.jpg"}}}
     */
	@RequestMapping(value = "/updateHeadPic")
	public RespBean updateHeadPic(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		CustomerBean customerBean = reqBean.getCustomerBean();
		customerBean.setUserId(UserUtil.getCurrentUserId(request));
		customerService.exe("updateHeadPic", customerBean);
		return RespUtil.build(request).putData("customerBean", UserBean.builder().headPic(Constant.imgDomain() + customerBean.getHeadPic()).build());
	}
}