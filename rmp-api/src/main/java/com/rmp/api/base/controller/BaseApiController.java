package com.rmp.api.base.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.model.MsgBean;
import com.rmp.api.base.model.RespBean;
import com.rmp.api.base.util.RespUtil;
import com.rmp.api.util.MsgEnum;

public class BaseApiController extends BaseController {
	
	/**
	 * 通用异常处理
	 * @param t
	 * @param request
	 * @param response
	 */
	@ExceptionHandler(value = Throwable.class)
	@ResponseBody
	public RespBean exceptionHandler(Throwable t, HttpServletRequest request, HttpServletResponse response) {
		
		List<MsgBean> msgs = new ArrayList<>();
		
		if (t instanceof HttpMessageNotReadableException) {
			msgs.add(MsgBean.build(MsgEnum.MSG_00003));
		} else if (t instanceof AppException) {
			AppException appException = (AppException) t;
			msgs = appException.getMsgList();
		} else {
			log.error(t.getMessage(), t);
		}
		
		if (CollectionUtils.isEmpty(msgs)) {
			msgs.add(MsgBean.build(MsgEnum.MSG_00002));
		} else {
			msgs.forEach(msg -> {
				if (msg != null) {
					String code = msg.getCode();
					String des = msg.getDes();
					if (MsgEnum.MSG_00002.getKey().equals(code) 
							|| MsgEnum.MSG_00003.getKey().equals(code)
							|| MsgEnum.MSG_00004.getKey().equals(code)) {
						log.error(code + " " + des, t);
					} else {
						log.error(code + " " + des);
					}
					
				}
			});
		}
		
		return RespUtil.buildError(request).putMsgs(msgs).putMsg(msgs.get(0));
	}	
}