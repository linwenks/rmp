package com.rmp.api.model;

import java.util.List;

import com.rmp.info.model.CustomerProblem;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class CustomerProblemBean extends CustomerProblem {

	@Builder
	public CustomerProblemBean(Long id, Long customerId, String health, String life, String remark, Integer isDelete,
			Integer version, Long createTime, Long updateTime
			, Long userId, List<SysCodeBean> healthSysCodeBeanList, List<SysCodeBean> lifeSysCodeBeanList) {
		super(id, customerId, health, life, remark, isDelete, version, createTime, updateTime);
		this.userId = userId;
		this.healthSysCodeBeanList = healthSysCodeBeanList;
		this.lifeSysCodeBeanList = lifeSysCodeBeanList;
	}

	private Long userId;
	
	/**
     * 健康问题 SysCodeBean list
     */
    private List<SysCodeBean> healthSysCodeBeanList;

    /**
     * 生活问题 SysCodeBean list
     */
    private List<SysCodeBean> lifeSysCodeBeanList;
}