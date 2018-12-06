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
			, List<SysCodeBean> interestCodeList, List<SysCodeBean> dietCodeList, List<SysCodeBean> tasteCodeList) {
		super(id, userId, interest, diet, taste, isDelete, version, createTime, updateTime);
		this.interestCodeList = interestCodeList;
		this.dietCodeList = dietCodeList;
		this.tasteCodeList = tasteCodeList;
	}

	/**
     * 兴趣 SysCodeBean list
     */
    private List<SysCodeBean> interestCodeList;

    /**
     * 饮食 SysCodeBean list
     */
    private List<SysCodeBean> dietCodeList;

    /**
     * 口味 SysCodeBean list
     */
    private List<SysCodeBean> tasteCodeList;
}