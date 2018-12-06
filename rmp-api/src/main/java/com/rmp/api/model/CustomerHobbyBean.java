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
			, Long userId, List<SysCodeBean> interestCodeList, List<SysCodeBean> dietCodeList, List<SysCodeBean> tasteCodeList) {
		super(id, customerId, interest, diet, taste, isDelete, version, createTime, updateTime);
		this.userId = userId;
		this.interestCodeList = interestCodeList;
		this.dietCodeList = dietCodeList;
		this.tasteCodeList = tasteCodeList;
	}
	
	private Long userId;
	
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