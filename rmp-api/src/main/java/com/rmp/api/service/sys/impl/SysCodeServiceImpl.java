package com.rmp.api.service.sys.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.service.impl.BaseServiceImpl;
import com.rmp.api.model.SysCodeBean;
import com.rmp.api.service.sys.SysCodeService;
import com.rmp.info.mapper.SysCodeMapper;
import com.rmp.info.model.SysCode;
import com.rmp.info.model.SysCodeCriteria;

/**
 * 系统 code service impl
 * @author linw
 *
 */
@Service
public class SysCodeServiceImpl extends BaseServiceImpl implements SysCodeService {
	
	@Autowired
	private SysCodeMapper sysCodeMapper;
	
	@Override
	public Class<?> getModelClass() {
		return SysCode.class;
	}

	@Override
	public Class<?> getBeanClass() {
		return SysCodeBean.class;
	}

	@Override
	public Class<?> getCriteriaClass() {
		return SysCodeCriteria.class;
	}

	@Override
	public Object getMapper() {
		return sysCodeMapper;
	}
	
	@Override
	public Object exe(String cmd, Object obj) {
		try {
			switch (cmd) {
			default: return super.exe(cmd, obj);
			}
		} catch (AppException e) {
			throw e;
		} catch (Exception e) {
			throw new AppException(e);
		}
	}
	
	@Override
	protected void where(Object criteria, Object bean) {
		if (bean == null) {
			return;
		}
		SysCodeCriteria.Criteria criteriaTmp = (SysCodeCriteria.Criteria) criteria;
		SysCodeBean beanTmp = (SysCodeBean) bean;
		if (beanTmp.getId() != null) {
			criteriaTmp.andIdEqualTo(beanTmp.getId());
		}
	}
}