package com.rmp.api.model;

import com.rmp.info.model.CustomerRelation;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class CustomerRelationBean extends CustomerRelation {
	
	@Builder
	public CustomerRelationBean(Long id, Long customerId, Integer relationship, Integer intimacy, Integer importance,
			Integer isDelete, Integer version, Long createTime, Long updateTime
			, Long userId, String relationshipValue, String intimacyValue, String importanceValue) {
		super(id, customerId, relationship, intimacy, importance, isDelete, version, createTime, updateTime);
		this.userId = userId;
		this.relationshipValue = relationshipValue;
		this.intimacyValue = intimacyValue;
		this.importanceValue = importanceValue;
	}

	private Long userId;
	
	 /**
     * 关系 value
     */
    private String relationshipValue;

    /**
     * 亲密
     */
    private String intimacyValue;

    /**
     * 重要
     */
    private String importanceValue;
}