package com.rmp.api.controller.user;

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
import com.rmp.api.base.model.ReqBean;
import com.rmp.api.base.model.RespBean;
import com.rmp.api.base.util.ReqUtil;
import com.rmp.api.base.util.RespUtil;
import com.rmp.api.model.UserBean;
import com.rmp.api.model.UserJobBean;
import com.rmp.api.service.user.UserJobService;
import com.rmp.api.service.user.UserService;
import com.rmp.api.util.UserJobUtil;
import com.rmp.api.util.UserUtil;
import com.rmp.api.util.SysCodeUtil;

/**
 * 用户 工作 json controller
 * @author linw
 *
 */
@RestController("api_user_UserJobController")
@RequestMapping(value = "/api/user/job", method = RequestMethod.POST, produces="application/json;charset=utf-8")
public class UserJobController extends BaseApiController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserJobService userJobService;
	
	/**
	 * 工作 配置
	 * 
     * @api {post} /api/user/job/config 工作 配置
     * @apiDescription 客户 工作 配置
     * @apiName user_job_config
     * @apiGroup group_user
     * @apiVersion 1.0.0
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"}}
     *
     * @apiSuccess (data) {List} industryCodeList 行业 code list
     * @apiSuccess (data) {List} positionCodeList 职位 code list
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
	 * 工作 查询
	 * 
     * @api {post} /api/user/job/get 工作 查询
     * @apiDescription 工作 查询
     * @apiName user_job_get
     * @apiGroup group_user
     * @apiVersion 1.0.0
     *
     * @apiSuccess (data) {Object} userBean 客户 bean
	 * @apiSuccess (data) {String} userBean.realName 真实姓名
	 * @apiSuccess (data) {Long} userBean.phone 手机
	 * @apiSuccess (data) {Integer} userBean.sex 性别<br/>0:女<br/>1:男
	 * @apiSuccess (data) {String} userBean.headPic 头像
	 * @apiSuccess (data) {Long} userBean.areaId 区域ID
	 * @apiSuccess (data) {String} userBean.areaNameAll 区域全称
     * @apiSuccess (data) {String} userBean.address 地址
     *
     * @apiSuccess (data) {Object} userJobBean 工作 bean
	 * @apiSuccess (data) {Integer} userJobBean.industry 行业
	 * @apiSuccess (data) {String} userJobBean.industryValue 行业 值
	 * @apiSuccess (data) {String} userJobBean.companyName 公司
	 * @apiSuccess (data) {String} userJobBean.departmentName 部门
	 * @apiSuccess (data) {Integer} userJobBean.position 职位
	 * @apiSuccess (data) {String} userJobBean.positionValue 职位 值
	 * @apiSuccess (data) {String} userJobBean.phone 手机
	 * @apiSuccess (data) {Long} userJobBean.areaId 区域ID
	 * @apiSuccess (data) {String} userJobBean.areaNameAll 区域全称
     * @apiSuccess (data) {String} userJobBean.address 地址
     * 
     * @apiSuccessExample {json} 成功返回-示例:
     * 		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"msgs":[],"msg":{},"state":"0","data":{"userJobBean":{"areaNameAll":"江苏省泰州市","industryValue":"互联网/电子商务/网游","positionValue":"工薪族","industry":2,"companyName":"aaaa","departmentName":"bbb","position":1,"phone":15111111111,"areaId":321200,"address":"aaaaaaaaaaaaaa"},"userBean":{"areaNameAll":"江苏省泰州市","realName":"ttt","phone":15111111111,"sex":0,"headPic":"/xxx/pic.jpg","areaId":321200,"address":"aaaaaaaaaaaaaa"}}}
     */
	@RequestMapping(value = "/get")
	public RespBean get(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqUtil.buildCheckLogin(body, request);
		
		Long userId = UserUtil.getCurrentUserId(request);
		
		UserBean userBeanResult = null;
		UserJobBean userJobBeanResult = null;
		
		UserBean userBean = UserBean.builder().id(userId).build();
		userBean = userService.selectOne(userBean);
		
		userBeanResult = UserBean.builder()
		.realName(userBean.getRealName())
		.phone(userBean.getPhone())
		.birthday(userBean.getBirthday())
		.sex(userBean.getSex())
		.areaId(userBean.getAreaId())
		.address(userBean.getAddress())
		.headPic(userBean.getHeadPic())
		.build();
		UserUtil.assembly(userBeanResult);
		
		UserJobBean userJobBean = UserJobBean.builder().userId(userId).build();
		UserJobBean userJobBeanTmp = userJobService.selectOne(userJobBean);
		if (userJobBeanTmp != null) {
			userJobBeanResult = UserJobBean.builder()
			.industry(userJobBeanTmp.getIndustry())
			.companyName(userJobBeanTmp.getCompanyName())
			.departmentName(userJobBeanTmp.getDepartmentName())
			.position(userJobBeanTmp.getPosition())
			.phone(userJobBeanTmp.getPhone())
			.areaId(userJobBeanTmp.getAreaId())
			.address(userJobBeanTmp.getAddress())
			.build();
			UserJobUtil.assembly(userJobBeanResult);
		}
		return RespUtil.build(request).putData("userBean", userBeanResult).putData("userJobBean", userJobBeanResult);
	}
	
	/**
	 * 工作 修改
	 * 
     * @api {post} /api/user/job/update 工作 修改
     * @apiDescription 工作 修改
     * @apiName user_job_update
     * @apiGroup group_user
     * @apiVersion 1.0.0
     * 
     * @apiParam (UserJobBean) {Object} userBean 客户 bean
	 * @apiParam (UserJobBean) {String} userBean.realName 真实姓名
	 * @apiParam (UserJobBean) {Integer} [userBean.sex] 性别<br/>0:女<br/>1:男
	 * @apiParam (UserJobBean) {String} [userBean.headPic] 头像
	 * @apiParam (UserJobBean) {Long} [userBean.areaId] 区域ID
     * @apiParam (UserJobBean) {String} [userBean.address] 地址
     *
     * @apiParam (UserJobBean) {Object} userJobBean 工作 bean
	 * @apiParam (UserJobBean) {Integer} [userJobBean.industry] 行业
	 * @apiParam (UserJobBean) {String} [userJobBean.companyName] 公司
	 * @apiParam (UserJobBean) {String} [userJobBean.departmentName] 部门
	 * @apiParam (UserJobBean) {Integer} [userJobBean.position] 职位
	 * @apiParam (UserJobBean) {String} [userJobBean.phone] 手机
	 * @apiParam (UserJobBean) {Long} [userJobBean.areaId] 区域ID
     * @apiParam (UserJobBean) {String} [userJobBean.address] 地址
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"userBean":{"realName":"ttt","sex":0,"birthday":20100101,"headPic":"/xxx/pic.jpg","areaId":321200,"address":"aaaaaaaaaaaaaa"},"userJobBean":{"userId":4,"industry":2,"companyName":"aaaa","departmentName":"bbb","position":3,"phone":"15111111111","areaId":321200,"address":"aaaaaaaaaaaaaa"}}
     * 
     */
	@RequestMapping(value = "/update")
	public RespBean update(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		UserBean userBean = reqBean.getUserBean();
		UserJobBean userJobBean = reqBean.getUserJobBean();
		Long currentUserId = UserUtil.getCurrentUserId(request);
		if (userBean == null) userBean = UserBean.builder().id(currentUserId).build();
		if (userJobBean == null) userJobBean = UserJobBean.builder().userId(currentUserId).build();
		userJobService.exe("update", ImmutableMap.of("userBean", userBean, "userJobBean", userJobBean));
		return RespUtil.build(request);
	}
}