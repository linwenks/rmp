package com.rmp.api.base.model;

import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rmp.api.util.constant.Constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {
	
	private HeaderBean header;
	
	// 消息 list
	private List<MsgBean> msgs = Lists.newArrayList();
	
	private MsgBean msg = MsgBean.build();
	
	// 消息状态
	private String state = Constant.Msg.ERROR;
	
	// 数据 map
	private Map<String, Object> data = Maps.newHashMap();
	
	public MsgBean getMsg() {
		if (!CollectionUtils.isEmpty(msgs)) {
			msg = msgs.get(0);
		}
		return msg;
	}
	
	public RespBean putMsg(MsgBean msgBean) {
		msgs.add(msgBean);
		return this;
	}
	
	public RespBean putMsgs(List<MsgBean> msgBeans) {
		msgs.addAll(msgBeans);
		return this;
	}
	
	public RespBean putData(String key, Object value) {
		data.put(key, value);
		return this;
	}
	
	public RespBean setState(String state) {
		this.state = state;
		return this;
	}
}