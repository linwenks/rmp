package com.rmp.api.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.rmp.api.base.spring.ApplicationContextUtil;
import com.rmp.api.service.area.AreaService;


/**
 * base
 * @author linw
 *
 */
public class BaseUtil {
	
	protected static Logger log = LoggerFactory.getLogger(BaseUtil.class);
	
	protected static AreaService areaService;
	
	public BaseUtil() {
		load();
	}
	
	public static void load() {
		if (areaService == null) {
			ApplicationContext applicationContext = ApplicationContextUtil.getContext();
			areaService = applicationContext.getBean(AreaService.class);
		}
	}
	
}