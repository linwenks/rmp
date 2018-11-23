package com.rmp.api.model;

import java.util.List;

import com.rmp.info.model.CustomerHobby;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class CustomerHobbyBean extends CustomerHobby {
	
	@Builder
	public CustomerHobbyBean(Long id, Long customerId, String interest, String diet, String taste, Integer isDelete,
			Integer version, Long createTime, Long updateTime
			, Long userId, List<String> interestKeyList, List<String> dietKeyList, List<String> tasteKeyList, List<String> interestValueList, List<String> dietValueList, List<String> tasteValueList) {
		super(id, customerId, interest, diet, taste, isDelete, version, createTime, updateTime);
		this.userId = userId;
		this.interestKeyList = interestKeyList;
		this.dietKeyList = dietKeyList;
		this.tasteKeyList = tasteKeyList;
		this.interestValueList = interestValueList;
		this.dietValueList = dietValueList;
		this.tasteValueList = tasteValueList;
	}
	
	private Long userId;
	
	/**
     * 兴趣 key list
     */
    private List<String> interestKeyList;

    /**
     * 饮食 key list
     */
    private List<String> dietKeyList;

    /**
     * 口味 key list
     */
    private List<String> tasteKeyList;
    
    /**
     * 兴趣 value list
     */
    private List<String> interestValueList;

    /**
     * 饮食 value list
     */
    private List<String> dietValueList;

    /**
     * 口味 value list
     */
    private List<String> tasteValueList;
}