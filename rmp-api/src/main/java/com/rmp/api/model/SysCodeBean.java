package com.rmp.api.model;

import java.util.List;

import com.rmp.info.model.SysCode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysCodeBean extends SysCode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2088956218423446207L;
	
	private SysCodeBean parent;
	
	private List<SysCodeBean> childList;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getKey() == null) ? 0 : getKey().hashCode());
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
		SysCodeBean other = (SysCodeBean) obj;
		if (getKey() == null) {
			if (other.getKey() != null)
				return false;
		} else if (!getKey().equals(other.getKey()))
			return false;
		return true;
	}
}