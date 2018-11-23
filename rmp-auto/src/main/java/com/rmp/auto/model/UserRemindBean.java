package com.rmp.auto.model;

import com.rmp.info.model.UserRemind;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRemindBean extends UserRemind {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5401334442586157683L;
	
	@Builder
	public UserRemindBean(Long id, Long userId, Integer type, Long typeId, Integer advanceDate, Integer advanceDay, Integer remindDate, Integer isDelete, Integer version, Long createTime, Long updateTime, Integer ymd, Integer md, Integer d, Integer w) {
		super(id, userId, type, typeId, advanceDate, advanceDay, remindDate, isDelete, version, createTime, updateTime);
		this.ymd = ymd;
		this.md = md;
		this.d = d;
		this.w = w;
	}

	private Integer ymd;
	
	private Integer md;
	
	private Integer d;
	
	private Integer w;
}
