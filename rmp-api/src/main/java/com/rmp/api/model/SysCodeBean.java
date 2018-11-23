package com.rmp.api.model;

import java.util.List;

import com.rmp.info.model.SysCode;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class SysCodeBean extends SysCode {

	@Builder
	public SysCodeBean(Long id, String key, String value, Long pid, Integer sort, String remark, Integer isDelete,
			Integer version, Long createTime, Long updateTime, SysCodeBean parent, List<SysCodeBean> childList) {
		super(id, key, value, pid, sort, remark, isDelete, version, createTime, updateTime);
		this.parent = parent;
		this.childList = childList;
	}
	
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