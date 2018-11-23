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
			, Long userId, List<String> healthKeyList, List<String> lifeKeyList, List<String> healthValueList, List<String> lifeValueList) {
		super(id, customerId, health, life, remark, isDelete, version, createTime, updateTime);
		this.userId = userId;
		this.healthKeyList = healthKeyList;
		this.lifeKeyList = lifeKeyList;
		this.healthValueList = healthValueList;
		this.lifeValueList = lifeValueList;
	}

	private Long userId;
	
	/**
     * 健康问题 key list
     */
    private List<String> healthKeyList;

    /**
     * 生活问题 key list
     */
    private List<String> lifeKeyList;
    
	/**
     * 健康问题 key list
     */
    private List<String> healthValueList;

    /**
     * 生活问题 key list
     */
    private List<String> lifeValueList;

}