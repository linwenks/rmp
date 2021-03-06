package com.rmp.api.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.rmp.api.base.dao.redis.BaseShardedJedisPoolDao;
import com.rmp.api.base.spring.ApplicationContextUtil;
import com.rmp.api.service.sys.SysCodeService;
import com.rmp.api.service.user.UserService;


/**
 * base
 * @author linw
 *
 */
public class BaseUtil {
	
	protected static Logger log = LoggerFactory.getLogger(BaseUtil.class);
	
	protected static BaseShardedJedisPoolDao jedisDao;
	protected static UserService userService;
	protected static SysCodeService sysCodeService;
	
	public BaseUtil() {
		load();
	}
	
	public static void load() {
		if (userService == null) {
			ApplicationContext ac = ApplicationContextUtil.getContext();
			jedisDao = ac.getBean(BaseShardedJedisPoolDao.class);
			userService = ac.getBean(UserService.class);
			sysCodeService = ac.getBean(SysCodeService.class);
		}
	}
}