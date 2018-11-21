package com.rmp.auto.base.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.rmp.auto.service.user.UserRemindService;

/**
 * base action
 * @author linw
 *
 */
public class BaseAction {

	protected static Log log = LogFactory.getLog(BaseAction.class);
	
	protected static JdbcTemplate jdbcTemplateRmp;
	protected static UserRemindService userRemindService;
	
	@SuppressWarnings("resource")
	protected static void init() {
		try {
			ApplicationContext context = new FileSystemXmlApplicationContext("classpath*:/config/spring/applicationContext-*.xml");
			jdbcTemplateRmp = (JdbcTemplate) context.getBean("jdbcTemplateRmp");
			userRemindService = context.getBean(UserRemindService.class);
		} catch (Exception e) {
			log.error(BaseAction.class.getName(), e);
			throw e;
		}
	}
}