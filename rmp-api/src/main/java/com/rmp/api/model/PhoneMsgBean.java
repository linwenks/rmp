package com.rmp.api.model;

import com.rmp.info.model.PhoneMsg;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class PhoneMsgBean extends PhoneMsg {

	@Builder
	public PhoneMsgBean(Long id, Long phone, String code, Integer type, String content, Integer status,
			Integer isDelete, Integer version, Long createTime, Long updateTime
			, Long createTimeStart) {
		super(id, phone, code, type, content, status, isDelete, version, createTime, updateTime);
		this.createTimeStart = createTimeStart;
	}

	private Long createTimeStart;
}