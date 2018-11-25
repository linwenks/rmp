package com.rmp.api.controller.user;

import static com.rmp.api.util.MsgEnum.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
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
import com.rmp.api.model.UserRemindBean;
import com.rmp.api.service.user.UserRemindService;
import com.rmp.api.util.CustomerRelationUtil;
import com.rmp.api.util.UserUtil;

/**
 * 用户 提醒 json controller
 * @author linw
 *
 */
@RestController("api_user_UserRemindController")
@RequestMapping(value = "/api/user/remind", method = RequestMethod.POST, produces="application/json;charset=utf-8")
public class UserRemindController extends BaseApiController {

	@Autowired
	private UserRemindService userRemindService;
	
	/**
	 * 提醒 列表 查询
	 * 
     * @api {post} /api/user/remind/list 提醒 列表 查询
     * @apiDescription 提醒 列表 查询
     * @apiName user_remind_list
     * @apiGroup group_user
     * @apiVersion 1.0.0
     * 
     * @apiParam (UserRemindBean) {Object} userRemindBean 提醒 bean
     * @apiParam (UserRemindBean) {Integer} userRemindBean.advanceDate 时间 yyyyMMdd
     * 
     * @apiParam (CustomerRelationBean) {Object} customerRelationBean 客户关系 bean
     * @apiParam (CustomerRelationBean) {Integer} [customerRelationBean.importance] 关系<br/>1:重要
     * 
     * @apiParamExample {json} 请求-示例: 
     *		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"userRemindBean":{"advanceDate":20181125,"customerRelationBean":{"importance":1}}}
     * 
     * @apiSuccess (data) {List} userRemindBeanList 提醒 bean list
	 * @apiSuccess (data) {Integer} userRemindBeanList.advanceDay 提前 天 
	 * @apiSuccess (data) {Integer} userRemindBeanList.advanceDayStr 提前 天
	 * @apiSuccess (data) {Integer} userRemindBeanList.remindDate 提醒 时间 yyyyMMdd
	 * @apiSuccess (data) {String} userRemindBeanList.content 内容
	 * @apiSuccess (data) {Object} userRemindBeanList.customerBean 客户 bean
	 * @apiSuccess (data) {String} userRemindBeanList.customerRelationBean 客户关系 bean
	 * 
     * @apiSuccessExample {json} 成功返回-示例:
     * 		{"header":{"token":"2661f2cac9754c98873aa9ce431b8012"},"msgs":[],"msg":{},"state":"0","data":{"userRemindBeanList":[{"content":"客户生日,亲戚生日","customerBean":{"id":2,"realName":"ss","sex":1,"headPic":"https://img.rmp.com/img/head_pic/default.jpg"},"customerRelationBean":{"relationshipValue":"其他","relationship":0},"advanceDayStr":"0天后","advanceDate":20181125,"advanceDay":0,"remindDate":20181125},{"content":"客户生日","customerBean":{"id":5,"realName":"ttt","sex":0,"headPic":"https://img.rmp.com/xxx/pic.jpg"},"advanceDayStr":"0天后","advanceDate":20181125,"advanceDay":0,"remindDate":20181125},{"content":"ttttt4,ttttt3,ttttt2,ttttt1","customerBean":{"id":2,"realName":"ss","sex":1,"headPic":"https://img.rmp.com/img/head_pic/default.jpg"},"customerRelationBean":{"relationshipValue":"其他","relationship":0},"advanceDayStr":"明天","advanceDate":20181125,"advanceDay":1,"remindDate":20181126},{"content":"亲戚生日","customerBean":{"id":2,"realName":"ss","sex":1,"headPic":"https://img.rmp.com/img/head_pic/default.jpg"},"customerRelationBean":{"relationshipValue":"其他","relationship":0},"advanceDayStr":"5天后","advanceDate":20181125,"advanceDay":5,"remindDate":20181130},{"content":"客户生日","customerBean":{"id":4,"realName":"ttt","sex":0,"headPic":"https://img.rmp.com/xxx/pic.jpg"},"advanceDayStr":"5天后","advanceDate":20181125,"advanceDay":5,"remindDate":20181130}]}}
     */
	@RequestMapping(value = "/list")
	public RespBean list(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.buildCheckLogin(body, request);
		UserRemindBean userRemindBean = reqBean.getUserRemindBean();
		if (userRemindBean == null) AppException.toThrow(MSG_00003);
		userRemindBean.setUserId(UserUtil.getCurrentUserId(request));
		
		List<UserRemindBean> userRemindBeanListTmp = userRemindService.selectListCustom(null, userRemindBean);
		if (!CollectionUtils.isEmpty(userRemindBeanListTmp)) {
			userRemindBeanListTmp.forEach(bean -> {
				bean.setAdvanceDate(null);
				CustomerRelationUtil.assembly(bean.getCustomerRelationBean());
			});
		}
		return RespUtil.build(request).putData("userRemindBeanList", userRemindBeanListTmp);
	}
}