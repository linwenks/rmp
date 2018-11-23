package com.rmp.api.model;

import java.math.BigDecimal;

import com.rmp.info.model.CustomerMaintain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class CustomerMaintainBean extends CustomerMaintain {
	
	@Builder
	public CustomerMaintainBean(Long id, Long customerId, Integer maintain, Integer frequency, Integer count,
			BigDecimal budget, Integer isDelete, Integer version, Long createTime, Long updateTime
			, Long userId, String maintainValue) {
		super(id, customerId, maintain, frequency, count, budget, isDelete, version, createTime, updateTime);
		this.userId = userId;
		this.maintainValue = maintainValue;
	}
	
	private Long userId;
	
	/**
	 * 维护 value
	 */
	private String maintainValue;
}