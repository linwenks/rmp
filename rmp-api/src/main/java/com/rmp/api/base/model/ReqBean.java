package com.rmp.api.base.model;

import javax.servlet.http.HttpServletRequest;

import com.rmp.common.page.QueryPage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 请求 json bean
 * @author linw
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReqBean {
	
	private HeaderBean headerBean;

	private HttpServletRequest request;
	
	private QueryPage queryPage;
	
}