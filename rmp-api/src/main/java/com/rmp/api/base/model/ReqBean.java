package com.rmp.api.base.model;

import javax.servlet.http.HttpServletRequest;

import com.rmp.api.model.CustomerBean;
import com.rmp.api.model.CustomerDetailBean;
import com.rmp.api.model.CustomerFamilyBean;
import com.rmp.api.model.CustomerHobbyBean;
import com.rmp.api.model.CustomerJobBean;
import com.rmp.api.model.CustomerMaintainBean;
import com.rmp.api.model.CustomerMemorialDayBean;
import com.rmp.api.model.CustomerProblemBean;
import com.rmp.api.model.CustomerRelationBean;
import com.rmp.api.model.PhoneMsgBean;
import com.rmp.api.model.UserBean;
import com.rmp.api.model.UserHobbyBean;
import com.rmp.api.model.UserJobBean;
import com.rmp.api.model.UserRemindBean;
import com.rmp.api.model.WxPhoneNumberReqBean;
import com.rmp.common.page.QueryPage;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class ReqBean {
	
	private HeaderBean header;

	private HttpServletRequest request;
	
	private QueryPage queryPage;
	
	
	private WxPhoneNumberReqBean wxPhoneNumberReqBean;
	
	
	private PhoneMsgBean phoneMsgBean;
	
	
	
	private UserBean userBean;
	
	private UserHobbyBean userHobbyBean;
	
	private UserJobBean userJobBean;
	
	private UserRemindBean userRemindBean;
	
	
	
	private CustomerBean customerBean;
	
	private CustomerDetailBean customerDetailBean;
	
	private CustomerRelationBean customerRelationBean;
	
	private CustomerHobbyBean customerHobbyBean;
	
	private CustomerProblemBean customerProblemBean;
	
	private CustomerJobBean customerJobBean;
	
	private CustomerFamilyBean customerFamilyBean;
	
	private CustomerMemorialDayBean customerMemorialDayBean;
	
	private CustomerMaintainBean customerMaintainBean;
}