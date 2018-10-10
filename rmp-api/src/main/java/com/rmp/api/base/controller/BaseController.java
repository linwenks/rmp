package com.rmp.api.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

public class BaseController {
	
	protected static Logger log = LoggerFactory.getLogger(BaseController.class);
	/*
	protected HttpServletRequest request = null;
	
	protected HttpServletResponse response = null;
	
	
	*//**
	 * 注入请求
	 * @param request
	 * @param response
	 *//*
	@ModelAttribute
	protected void setReqAndRes(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		this.request = request;
		this.response = response;
		
		String base = request.getContextPath();
		modelMap.addAttribute("base", base);
	}
	*/
	
}