package com.rmp.auto.model;

import com.rmp.info.model.UserRemind;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class UserRemindBean extends UserRemind {
	
	@Builder
	public UserRemindBean(Long id, Long userId, Integer type, Long typeId, Integer advanceDate, Integer advanceDay, Integer remindDate, Integer isDelete, Integer version, Long createTime, Long updateTime) {
		super(id, userId, type, typeId, advanceDate, advanceDay, remindDate, isDelete, version, createTime, updateTime);
	}
}
