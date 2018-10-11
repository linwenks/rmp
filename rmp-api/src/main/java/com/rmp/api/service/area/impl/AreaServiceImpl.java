package com.rmp.api.service.area.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.service.impl.BaseServiceImpl;
import com.rmp.api.model.AreaBean;
import com.rmp.api.service.area.AreaService;
import com.rmp.info.mapper.AreaMapper;
import com.rmp.info.model.Area;
import com.rmp.info.model.AreaCriteria;

/**
 * 地域 service impl
 * @author linw
 *
 */
@Service
public class AreaServiceImpl extends BaseServiceImpl implements AreaService {
	
	@Autowired
	private AreaMapper areaMapper;
	
	@Override
	public Class<?> getModelClass() {
		return Area.class;
	}

	@Override
	public Class<?> getModelBeanClass() {
		return AreaBean.class;
	}

	@Override
	public Class<?> getCriteriaClass() {
		return AreaCriteria.class;
	}

	@Override
	public Object getMapper() {
		return areaMapper;
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
		AreaCriteria.Criteria criteriaTmp = (AreaCriteria.Criteria) criteria;
		AreaBean beanTmp = (AreaBean) bean;
		if (beanTmp.getId() != null) {
			criteriaTmp.andIdEqualTo(beanTmp.getId());
		}
		if (beanTmp.getStartId() != null) {
			criteriaTmp.andIdGreaterThan(beanTmp.getStartId());
		}
	}
}