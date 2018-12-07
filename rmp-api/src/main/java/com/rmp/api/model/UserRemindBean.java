package com.rmp.api.model;

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
	public UserRemindBean(Long id, Long userId, Integer type, Long typeId, Integer advanceDate, Integer advanceDay, Integer remindDate, Integer isDelete, Integer version, Long createTime, Long updateTime
			, String content, CustomerBean customerBean, CustomerRelationBean customerRelationBean, String advanceDayStr, String remindDateStr) {
		super(id, userId, type, typeId, advanceDate, advanceDay, remindDate, isDelete, version, createTime, updateTime);
		this.content = content;
		this.customerBean = customerBean;
		this.customerRelationBean = customerRelationBean;
		this.advanceDayStr = advanceDayStr;
		this.remindDateStr = remindDateStr;
	}
	
	private String content;
	
	private CustomerBean customerBean;
	
	private CustomerRelationBean customerRelationBean;
	
	private String advanceDayStr;
	
	private String remindDateStr;
}