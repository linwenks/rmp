package com.rmp.api.model;

import java.util.List;

import com.rmp.info.model.UserHobby;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class UserHobbyBean extends UserHobby {
	
	@Builder
	public UserHobbyBean(Long id, Long userId, String interest, String diet, String taste, Integer isDelete,
			Integer version, Long createTime, Long updateTime
			, List<String> interestKeyList, List<String> dietKeyList, List<String> tasteKeyList, List<String> interestValueList, List<String> dietValueList, List<String> tasteValueList) {
		super(id, userId, interest, diet, taste, isDelete, version, createTime, updateTime);
		this.interestKeyList = interestKeyList;
		this.dietKeyList = dietKeyList;
		this.tasteKeyList = tasteKeyList;
		this.interestValueList = interestValueList;
		this.dietValueList = dietValueList;
		this.tasteValueList = tasteValueList;
	}

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