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

import com.google.common.collect.ImmutableMap;
import com.rmp.api.base.controller.BaseApiController;
import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.model.ReqBean;
import com.rmp.api.base.model.RespBean;
import com.rmp.api.base.util.ReqUtil;
import com.rmp.api.base.util.RespUtil;
import com.rmp.api.model.CustomerBean;
import com.rmp.api.model.CustomerJobBean;
import com.rmp.api.service.customer.CustomerJobService;
import com.rmp.api.service.customer.CustomerService;
import com.rmp.api.util.CustomerJobUtil;
import com.rmp.api.util.CustomerUtil;
import com.rmp.api.util.SysCodeUtil;
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
	 * 客户 工作 配置
	 * 
     * @api {post} /api/customer/job/config 客户 工作 配置
     * @apiDescription 客户 工作 配置
     * @apiName customer_job_config
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"}}
     *
     * @apiSuccess (data) {List} industryCodeList 行业 code list
     * @apiSuccess (data) {List} positionCodeList 职业 code list
     * 
     * @apiSuccessExample {json} 成功返回-示例:
     * 		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"msgs":[],"msg":{},"state":"0","data":{"industryCodeList":[{"id":88,"key":"1","value":"计算机硬软件","pid":87,"sort":1},{"id":89,"key":"2","value":"互联网/电子商务/网游","pid":87,"sort":2},{"id":90,"key":"3","value":"IT管理","pid":87,"sort":3},{"id":91,"key":"4","value":"通信","pid":87,"sort":4},{"id":92,"key":"5","value":"电子/电器/半导体","pid":87,"sort":5},{"id":93,"key":"6","value":"财务/审计/税务","pid":87,"sort":6},{"id":94,"key":"7","value":"金融/投资","pid":87,"sort":7},{"id":95,"key":"8","value":"银行/保险","pid":87,"sort":8},{"id":96,"key":"9","value":"工程/机械","pid":87,"sort":9},{"id":97,"key":"10","value":"能源/原材料","pid":87,"sort":10},{"id":98,"key":"11","value":"汽车及零配件制造","pid":87,"sort":11},{"id":99,"key":"12","value":"汽车销售服务","pid":87,"sort":12},{"id":100,"key":"13","value":"服装/纺织","pid":87,"sort":13},{"id":101,"key":"14","value":"轻工产品制造","pid":87,"sort":14},{"id":102,"key":"15","value":"食品生产","pid":87,"sort":15},{"id":103,"key":"16","value":"贸易","pid":87,"sort":16},{"id":104,"key":"17","value":"物流/仓储","pid":87,"sort":17},{"id":105,"key":"18","value":"生物/制药","pid":87,"sort":18},{"id":106,"key":"19","value":"化工","pid":87,"sort":19},{"id":107,"key":"20","value":"医院/医疗/护理","pid":87,"sort":20},{"id":108,"key":"21","value":"广告媒体","pid":87,"sort":21},{"id":109,"key":"22","value":"市场/营销","pid":87,"sort":22},{"id":110,"key":"23","value":"影视","pid":87,"sort":23},{"id":111,"key":"24","value":"编辑出版","pid":87,"sort":24},{"id":112,"key":"25","value":"艺术/设计","pid":87,"sort":25},{"id":113,"key":"26","value":"建筑与装潢","pid":87,"sort":26},{"id":114,"key":"27","value":"房地产开发","pid":87,"sort":27},{"id":115,"key":"28","value":"房地产销售与中介","pid":87,"sort":28},{"id":116,"key":"29","value":"物业","pid":87,"sort":29},{"id":117,"key":"30","value":"人力资源","pid":87,"sort":30},{"id":118,"key":"31","value":"咨询/顾问","pid":87,"sort":31},{"id":119,"key":"32","value":"律师/法务","pid":87,"sort":32},{"id":120,"key":"33","value":"教师/培训","pid":87,"sort":33},{"id":121,"key":"34","value":"科研","pid":87,"sort":34},{"id":122,"key":"35","value":"餐饮服务","pid":87,"sort":35},{"id":123,"key":"36","value":"酒店旅游","pid":87,"sort":36},{"id":124,"key":"37","value":"美容保健","pid":87,"sort":37},{"id":125,"key":"38","value":"百货零售","pid":87,"sort":38},{"id":126,"key":"39","value":"交通运输","pid":87,"sort":39},{"id":127,"key":"40","value":"家政/生活服务","pid":87,"sort":40},{"id":128,"key":"41","value":"政府/公务员","pid":87,"sort":41},{"id":129,"key":"42","value":"翻译","pid":87,"sort":42},{"id":130,"key":"43","value":"农林牧渔","pid":87,"sort":43},{"id":131,"key":"44","value":"印刷包装","pid":87,"sort":44},{"id":132,"key":"45","value":"运动健身","pid":87,"sort":45},{"id":133,"key":"46","value":"休闲娱乐","pid":87,"sort":46},{"id":134,"key":"47","value":"其他","pid":87,"sort":47}],"positionCodeList":[{"id":136,"key":"1","value":"工薪族","pid":135,"sort":1},{"id":137,"key":"2","value":"个体户","pid":135,"sort":2},{"id":138,"key":"3","value":"企业主","pid":135,"sort":3},{"id":139,"key":"4","value":"学生","pid":135,"sort":4},{"id":140,"key":"5","value":"公务员","pid":135,"sort":5},{"id":141,"key":"6","value":"自由职业","pid":135,"sort":6},{"id":142,"key":"7","value":"无业","pid":135,"sort":7}]}}
     */
	@RequestMapping(value = "/config")
	public RespBean config(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqUtil.buildCheckLogin(body, request);
		return RespUtil.build(request)
				.putData("industryCodeList", SysCodeUtil.getChildSimple(CUSTOMER, CUSTOMER_JOB, CUSTOMER_JOB_INDUSTRY))
				.putData("positionCodeList", SysCodeUtil.getChildSimple(CUSTOMER, CUSTOMER_JOB, CUSTOMER_JOB_POSITION));
	}
	
	/**
	 * 客户 工作 查询
	 * 
     * @api {post} /api/customer/job/get 客户 工作 查询
     * @apiDescription 客户 工作 查询
     * @apiName customer_job_get
     * @apiGroup group_customer
     * @apiVersion 1.0.0
     * 
     * @apiParam (CustomerJobBean) {Object} customerJobBean 工作 bean
     * @apiParam (CustomerJobBean) {Long} [customerJobBean.customerId] 客户ID
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"customerJobBean":{"customerId":2}}
     *
     * @apiSuccess (data) {Object} customerBean 客户 bean
	 * @apiSuccess (data) {String} customerBean.realName 真实姓名
	 * @apiSuccess (data) {Long} customerBean.phone 手机
	 * @apiSuccess (data) {Integer} customerBean.sex 性别<br/>0:女<br/>1:男
	 * @apiSuccess (data) {String} customerBean.headPic 头像
	 * @apiSuccess (data) {String} customerBean.area 区域
	 * @apiSuccess (data) {String} customerBean.areaNameAll 区域全称
     * @apiSuccess (data) {String} customerBean.address 地址
     *
     * @apiSuccess (data) {Object} customerJobBean 工作 bean
	 * @apiSuccess (data) {Integer} customerJobBean.industry 行业
	 * @apiSuccess (data) {String} customerJobBean.industryValue 行业 值
	 * @apiSuccess (data) {String} customerJobBean.companyName 公司
	 * @apiSuccess (data) {String} customerJobBean.departmentName 部门
	 * @apiSuccess (data) {Integer} customerJobBean.position 职业
	 * @apiSuccess (data) {String} customerJobBean.positionValue 职业 值
	 * @apiSuccess (data) {String} customerJobBean.office 职位
	 * @apiSuccess (data) {String} customerJobBean.phone 手机
	 * @apiSuccess (data) {String} customerJobBean.area 区域
	 * @apiSuccess (data) {String} customerJobBean.areaNameAll 区域全称
     * @apiSuccess (data) {String} customerJobBean.address 地址
     * 
     * @apiSuccessExample {json} 成功返回-示例:
     * 		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"msgs":[],"msg":{},"state":"0","data":{"customerJobBean":{"areaNameAll":"江苏省泰州市","industryValue":"互联网/电子商务/网游","positionValue":"工薪族","industry":2,"companyName":"aaaa","departmentName":"bbb","position":1,"office":"xxxxxxxxxx","phone":15111111111,"area":"重庆市九龙坡区","address":"aaaaaaaaaaaaaa"},"customerBean":{"areaNameAll":"江苏省泰州市","realName":"ttt","phone":15111111111,"sex":0,"headPic":"/xxx/pic.jpg","area":"重庆市九龙坡区","address":"aaaaaaaaaaaaaa"}}}
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
			CustomerBean customerBean = CustomerBean.builder().id(customerId).userId(UserUtil.getCurrentUserId(request)).build();
			customerBean = customerService.selectOne(customerBean);
			if (customerBean == null) AppException.toThrow(MSG_00003);
			
			customerBeanResult = CustomerBean.builder()
			.realName(customerBean.getRealName())
			.phone(customerBean.getPhone())
			.birthday(customerBean.getBirthday())
			.sex(customerBean.getSex())
			.area(customerBean.getArea())
			.address(customerBean.getAddress())
			.headPic(customerBean.getHeadPic())
			.build();
			CustomerUtil.assembly(customerBeanResult);
			
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
     * @apiParam (CustomerJobBean) {Object} customerBean 客户 bean
	 * @apiParam (CustomerJobBean) {String} customerBean.realName 真实姓名
	 * @apiParam (CustomerJobBean) {Long} customerBean.phone 手机
	 * @apiParam (CustomerJobBean) {Integer} customerBean.birthday 生日 yyyyMMdd
	 * @apiParam (CustomerJobBean) {Integer} [customerBean.sex] 性别<br/>0:女<br/>1:男
	 * @apiParam (CustomerJobBean) {String} [customerBean.headPic] 头像
	 * @apiParam (CustomerJobBean) {String} [customerBean.area] 区域
     * @apiParam (CustomerJobBean) {String} [customerBean.address] 地址
     *
     * @apiParam (CustomerJobBean) {Object} customerJobBean 工作 bean
     * @apiParam (CustomerJobBean) {Integer} [customerJobBean.customerId] 客户ID
	 * @apiParam (CustomerJobBean) {Integer} [customerJobBean.industry] 行业
	 * @apiParam (CustomerJobBean) {String} [customerJobBean.companyName] 公司
	 * @apiParam (CustomerJobBean) {String} [customerJobBean.departmentName] 部门
	 * @apiParam (CustomerJobBean) {Integer} [customerJobBean.position] 职业
	 * @apiParam (CustomerJobBean) {String} [customerJobBean.office] 职位
	 * @apiParam (CustomerJobBean) {String} [customerJobBean.phone] 手机
	 * @apiParam (CustomerJobBean) {String} [customerJobBean.area] 区域
     * @apiParam (CustomerJobBean) {String} [customerJobBean.address] 地址
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"customerBean":{"realName":"ttt","phone":15111111111,"sex":0,"birthday":20100101,"headPic":"/xxx/pic.jpg","area":"重庆市九龙坡区","address":"aaaaaaaaaaaaaa"},"customerJobBean":{"customerId":4,"industry":2,"companyName":"aaaa","departmentName":"bbb","position":3,"office":"xxxxxxxxxx","phone":"15111111111","area":"重庆市九龙坡区","address":"aaaaaaaaaaaaaa"}}
     * 
     * @apiSuccess (data) {Object} customerBean 客户 bean
	 * @apiSuccess (data) {Integer} customerBean.id ID
	 * @apiSuccess (data) {String} customerBean.headPic 头像
     * 
     * @apiSuccessExample {json} 成功返回-示例:
     * 		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"msgs":[],"msg":{},"state":"0","data":{"customerBean":{"id":59,"headPic":"http://47.94.5.205/customer/head_pic/20181231/1236000391194045151.png"}}}
     */
	@RequestMapping(value = "/update")
	public RespBean update(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		CustomerBean customerBean = reqBean.getCustomerBean();
		CustomerJobBean customerJobBean = reqBean.getCustomerJobBean();
		
		Long currentUserId = UserUtil.getCurrentUserId(request);
		
		if (customerBean == null) customerBean = CustomerBean.builder().build();
		if (customerJobBean == null) customerJobBean = CustomerJobBean.builder().build();
		customerBean.setUserId(currentUserId);
		customerJobBean.setUserId(currentUserId);
		customerJobService.exe("update", ImmutableMap.of("customerBean", customerBean, "customerJobBean", customerJobBean));
		CustomerBean customerBeanResult = CustomerBean.builder().id(customerBean.getId()).headPic(customerBean.getHeadPic()).build();
		CustomerUtil.assembly(customerBeanResult);
		return RespUtil.build(request).putData("customerBean", customerBeanResult);
	}
}