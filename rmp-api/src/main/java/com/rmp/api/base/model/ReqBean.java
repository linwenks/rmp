package com.rmp.api.base.model;

import javax.servlet.http.HttpServletRequest;

import com.rmp.api.model.CustomerBean;
import com.rmp.api.model.CustomerHobbyBean;
import com.rmp.api.model.CustomerJobBean;
import com.rmp.api.model.CustomerProblemBean;
import com.rmp.api.model.CustomerRelationBean;
import com.rmp.api.model.PhoneMsgBean;
import com.rmp.api.model.UserBean;
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
	
	private HeaderBean header;

	private HttpServletRequest request;
	
	private QueryPage queryPage;
	
	
	private UserBean userBean;
	
	private PhoneMsgBean phoneMsgBean;
	
	private CustomerBean customerBean;
	
	private CustomerRelationBean customerRelationBean;
	
	private CustomerHobbyBean customerHobbyBean;
	
	private CustomerProblemBean customerProblemBean;
	
	private CustomerJobBean customerJobBean;
}