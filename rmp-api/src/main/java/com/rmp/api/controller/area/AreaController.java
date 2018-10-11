package com.rmp.api.controller.area;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rmp.api.base.controller.BaseApiController;
import com.rmp.api.base.model.ReqBean;
import com.rmp.api.base.model.RespBean;
import com.rmp.api.base.util.ReqUtil;
import com.rmp.api.base.util.RespUtil;
import com.rmp.api.model.AreaBean;
import com.rmp.api.service.area.AreaService;
import com.rmp.api.util.AreaUtil;

/**
 * 地域 json controller
 * @author linw
 *
 */
@Controller("api_area_AreaController")
@RequestMapping(value = "/api/area/area", method = RequestMethod.POST, produces="application/json;charset=utf-8")
public class AreaController extends BaseApiController {

	@Autowired
	private AreaService areaService;
	
	/**
	 * 
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public RespBean list(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) {
		ReqBean reqBean = ReqUtil.build(body, request);
		
		List<AreaBean> areaBeanList = AreaUtil.getListTreeSimple();
		return RespUtil.build(request).putData("areaList", areaBeanList);
	}
}