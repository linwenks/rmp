package com.rmp.api.base.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.model.HeaderBean;
import com.rmp.api.base.model.MsgBean;
import com.rmp.api.base.model.RespBean;
import com.rmp.api.util.constant.Constant;
import com.rmp.common.util.JsonUtil;

public class BaseApiController extends BaseController {
	
	/**
	 * 通用异常处理
	 * @param t
	 * @param request
	 * @param response
	 */
	@ExceptionHandler(value = Throwable.class)
	@ResponseBody
	public String exceptionHandler(Throwable t, HttpServletRequest request, HttpServletResponse response) {
		
		List<MsgBean> msgList = new ArrayList<>();
		
		if (t instanceof HttpMessageNotReadableException) {
			msgList.add(new MsgBean(Constant.Msg.Api._00003));
		} else if (t instanceof AppException) {
			AppException appException = (AppException) t;
			msgList = appException.getMsgList();
		} else {
			log.error(t.getMessage(), t);
		}
		
		if (msgList.isEmpty()) {
			msgList.add(new MsgBean(Constant.Msg.Api._00002));
		} else {
			log.error(msgList.get(0).getCode() + " " + msgList.get(0).getDes());
		}
		/*
		respJsonBean.setMsgList(msgList);
		respJsonBean.setMsg(msgList.get(0));
		respJsonBean.setState(Constant.Msg.ERROR);
		return JsonUtil.toJson(respJsonBean);
		*/
		return null;
	}
	/*
	*//**
	 * 当前用户ID
	 * @return
	 *//*
	protected Long getCurrentUserId() {
		MemberBean memberBean = (MemberBean) request.getAttribute(Constant.CURRENT_LOGIN_USER);
		return memberBean.getId();
	}
	
	*//**
	 * 当前用户
	 * @return
	 *//*
	protected MemberBean getCurrentUser() {
		MemberBean memberBean = (MemberBean) request.getAttribute(Constant.CURRENT_LOGIN_USER);
		return memberBean;
	}
	*/
	/*
	*//**
	 * get Header
	 * @return
	 *//*
	protected HeaderJsonBean getHeader() {
		return (HeaderJsonBean) request.getAttribute(Constant.CURRENT_REQUEST_HEADER);
	}
	
	*//**
	 * set Header
	 * @return
	 *//*
	protected void setHeader(HeaderJsonBean headerJsonBean) {
		request.setAttribute(Constant.CURRENT_REQUEST_HEADER, headerJsonBean);
	}
	*/
	/*
	protected void checkFromLogin(String token
			, BaseShardedJedisPoolDao baseShardedJedisPoolDao
			, MemberService memberService
			, MemberCategoryService memberCategoryService) {
		token = StringUtils.trim(token);
		if (StringUtils.isEmpty(token)) throw new AppException(Constant.Msg.Api._00006);
		
		// redis 查询
		int index = Constant.Redis.Member.INDEX;
		
		String key = MemberUtil.key(token);
		Map<String, String> member2Map = baseShardedJedisPoolDao.hgetAll(index, key);
		if (member2Map == null || member2Map.isEmpty()) {
			
			// mysql 查询
			MemberBean member2 = new MemberBean();
			member2.setToken(token);
			Member member = memberService.selectOne(member2);
			if (member == null) throw new AppException(Constant.Msg.Api._00007);
			
			MemberCategoryBean memberCategoryBean = new MemberCategoryBean();
			memberCategoryBean.setMemberId(member.getId());
			MemberCategory memberCategory = memberCategoryService.selectOne(memberCategoryBean);
			String readTimeJson = memberCategory.getReadTimeJson();
			
			MemberBean member2Tmp = new MemberBean();
			BeanUtil.copyNotNullProperties(member2Tmp, member);
			member2Tmp.setReadTimeJson(readTimeJson);
			member2Map = MemberUtil.get(member2Tmp);
			
			baseShardedJedisPoolDao.hmset(index, key, member2Map);
		}
		
		// 重新设置过期时间
		baseShardedJedisPoolDao.expire(index, key, Constant.Redis.Member.KEY_SECONDS);
		
		HeaderJsonBean headerJsonBean = new HeaderJsonBean();
		headerJsonBean.setToken(token);

		// 用于返回
		request.setAttribute(Constant.CURRENT_REQUEST_HEADER, headerJsonBean);
		request.setAttribute(Constant.CURRENT_LOGIN_USER, MemberUtil.get(member2Map));
		
	}
	*/
}
