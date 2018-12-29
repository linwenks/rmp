package com.rmp.api.model;

import com.rmp.info.model.CustomerDetail;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class CustomerDetailBean extends CustomerDetail {
	
	@Builder
	public CustomerDetailBean(Long id, Long customerId, String remark, Integer isDelete, Integer version,
			Long createTime, Long updateTime
			, Long userId) {
		super(id, customerId, remark, isDelete, version, createTime, updateTime);
		this.userId = userId;
	}
	
	private Long userId;
}