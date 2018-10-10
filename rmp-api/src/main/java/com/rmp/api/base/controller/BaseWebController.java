package com.rmp.api.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

public class BaseWebController extends BaseController {
	
	/**
	 * 绑定参数前缀
	 * @param binder
	 */
	@InitBinder("queryPage")    
    public void initBinderQueryPage(WebDataBinder binder) {    
		binder.setFieldDefaultPrefix("queryPage.");    
    }
	
	/**
	 * 生成 ModelandView  供返回页面 
	 * @param page
	 * @return
	 */
	protected ModelAndView forwardPage(String page) {
		return new ModelAndView(page);
	}
	
	/**
	 * 通用异常处理
	 * @param t
	 * @param request
	 * @param response
	 */
	@ExceptionHandler(value = Throwable.class)
	public ModelAndView exceptionHandler(Throwable t, HttpServletRequest request, HttpServletResponse response) {
		
		log.error(t.getMessage(), t);
		
		return forwardPage("/error");
	}
}
