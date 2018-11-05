package com.rmp.api.model;

import java.util.List;

import com.rmp.info.model.UserHobby;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserHobbyBean extends UserHobby {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2088956218423446207L;
	
	/**
     * 兴趣 key list
     */
    private List<String> interestKeyList;

    /**
     * 饮食 key list
     */
    private List<String> dietKeyList;

    /**
     * 口味 key list
     */
    private List<String> tasteKeyList;
    
    /**
     * 兴趣 value list
     */
    private List<String> interestValueList;

    /**
     * 饮食 value list
     */
    private List<String> dietValueList;

    /**
     * 口味 value list
     */
    private List<String> tasteValueList;
}