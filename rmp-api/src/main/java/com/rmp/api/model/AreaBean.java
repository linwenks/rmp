package com.rmp.api.model;

import java.util.List;

import com.rmp.info.model.Area;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AreaBean extends Area {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2088956218423446207L;
	
	private Long startId;
	
	private AreaBean parent;
	
	private List<AreaBean> childList;
	
	/**
	 * 地址全称
	 */
	private String nameAll;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AreaBean other = (AreaBean) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	} 
}