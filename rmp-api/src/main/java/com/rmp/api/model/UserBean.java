package com.rmp.api.model;

import java.math.BigDecimal;

import com.rmp.info.model.User;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class UserBean extends User {

	@Builder
	public UserBean(Long id, String loginName, String loginPwd, Long phone, String realName, String nickName,
			String headPic, Integer sex, Integer birthday, String payPwd, BigDecimal account, Long lastLoginTime,
			String area, String address, String wxId, String token, Integer status, String wxSessionKey,
			Integer isDelete, Integer version, Long createTime, Long updateTime
			, String jsCode, String areaNameAll, String birthdayStr, Long idNotEqualTo) {
		super(id, loginName, loginPwd, phone, realName, nickName, headPic, sex, birthday, payPwd, account, lastLoginTime,
				area, address, wxId, token, status, wxSessionKey, isDelete, version, createTime, updateTime);
		this.jsCode = jsCode;
		this.areaNameAll = areaNameAll;
		this.birthdayStr = birthdayStr;
		this.idNotEqualTo = idNotEqualTo;
	}
	
	/**
	 * 微信 js code
	 */
	private String jsCode;
	
	/**
     * 区域 名称 all
     */
    private String areaNameAll;
    
    /**
     * 生日 str
     */
    private String birthdayStr;
    
    private Long idNotEqualTo;
}