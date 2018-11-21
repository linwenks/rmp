package com.rmp.api.service.area.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class AreaServiceImpl extends BaseServiceImpl<Area, AreaBean, AreaCriteria> implements AreaService {
	
	@Autowired
	private AreaMapper areaMapper;
	
	@Override
	public AreaMapper mapper() {
		return areaMapper;
	}
	
	@Override
	protected void where(Object criteria, AreaBean bean) {
		if (bean == null) {
			return;
		}
		AreaCriteria.Criteria criteriaTmp = (AreaCriteria.Criteria) criteria;
		if (bean.getId() != null) {
			criteriaTmp.andIdEqualTo(bean.getId());
		}
		if (bean.getStartId() != null) {
			criteriaTmp.andIdGreaterThan(bean.getStartId());
		}
	}
}