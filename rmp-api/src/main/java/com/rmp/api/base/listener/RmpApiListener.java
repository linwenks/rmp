package com.rmp.api.base.listener;

import java.util.TimeZone;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;

@WebListener
public class RmpApiListener extends ContextLoaderListener {
	
	protected static Logger log = LoggerFactory.getLogger(RmpApiListener.class);
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
		
	}
}
