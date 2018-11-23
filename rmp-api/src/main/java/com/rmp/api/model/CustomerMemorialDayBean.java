package com.rmp.api.model;

import com.rmp.info.model.CustomerMemorialDay;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class CustomerMemorialDayBean extends CustomerMemorialDay {

	@Builder
	public CustomerMemorialDayBean(Long id, Long customerId, String name, Integer occurType, Integer occurDate,
			Integer advanceType, String remark, Integer isDelete, Integer version, Long createTime, Long updateTime
			, Long userId, String occurTypeValue, String advanceTypeValue) {
		super(id, customerId, name, occurType, occurDate, advanceType, remark, isDelete, version, createTime, updateTime);
		this.userId = userId;
		this.occurTypeValue = occurTypeValue;
		this.advanceTypeValue = advanceTypeValue;
	}
	
	private Long userId;
	
	/**
     * 类型 value
     */
    private String occurTypeValue;

    /**
     * 提前类型 value
     */
    private String advanceTypeValue;
}