package com.rmp.api.model;

import com.rmp.info.model.Customer;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class CustomerBean extends Customer {
	
	@Builder
	public CustomerBean(Long id, Long userId, String realName, String pinyin, Long phone, Integer sex, Integer birthday,
			String headPic, Long areaId, String address, Integer vip, String tag, Integer isDelete, Integer version,
			Long createTime, Long updateTime
			, Long idNot, String areaNameAll, CustomerRelationBean customerRelationBean) {
		super(id, userId, realName, pinyin, phone, sex, birthday, headPic, areaId, address, vip, tag, isDelete, version,
				createTime, updateTime);
		this.idNot = idNot;
		this.areaNameAll = areaNameAll;
		this.customerRelationBean = customerRelationBean;
	}
	
	private Long idNot;
	
	/**
     * 区域 名称 all
     */
    private String areaNameAll;
    
    private CustomerRelationBean customerRelationBean;
}