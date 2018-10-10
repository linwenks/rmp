package com.rmp.api.base.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private List<MsgBean> msgs = new ArrayList<>();
	
	private MsgBean msg = new MsgBean();
	
	// 消息状态
	private String state = Constant.Msg.ERROR;
	
	// 数据 map
	private Map<String, Object> data = new HashMap<>();
	
	public MsgBean getMsg() {
		if (msgs != null) {
			msg = msgs.get(0);
		}
		return msg;
	}
	
	public RespBean putData(String key, Object value) {
		data.put(key, value);
		return this;
	}
}