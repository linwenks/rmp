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
			, List<SysCodeBean> interestSysCodeBeanList, List<SysCodeBean> dietSysCodeBeanList, List<SysCodeBean> tasteSysCodeBeanList) {
		super(id, userId, interest, diet, taste, isDelete, version, createTime, updateTime);
		this.interestSysCodeBeanList = interestSysCodeBeanList;
		this.dietSysCodeBeanList = dietSysCodeBeanList;
		this.tasteSysCodeBeanList = tasteSysCodeBeanList;
	}

	/**
     * 兴趣 SysCodeBean list
     */
    private List<SysCodeBean> interestSysCodeBeanList;

    /**
     * 饮食 SysCodeBean list
     */
    private List<SysCodeBean> dietSysCodeBeanList;

    /**
     * 口味 SysCodeBean list
     */
    private List<SysCodeBean> tasteSysCodeBeanList;
}