package com.rmp.api.service.sys.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class SysCodeServiceImpl extends BaseServiceImpl<SysCode, SysCodeBean, SysCodeCriteria> implements SysCodeService {
	
	@Autowired
	private SysCodeMapper sysCodeMapper;
	
	@Override
	public SysCodeMapper mapper() {
		return sysCodeMapper;
	}
	
	@Override
	protected void where(Object criteria, SysCodeBean bean) {
		if (bean == null) {
			return;
		}
		SysCodeCriteria.Criteria criteriaTmp = (SysCodeCriteria.Criteria) criteria;
		if (bean.getId() != null) {
			criteriaTmp.andIdEqualTo(bean.getId());
		}
	}
}