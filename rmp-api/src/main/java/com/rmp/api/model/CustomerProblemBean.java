package com.rmp.api.model;

import java.util.List;

import com.rmp.info.model.CustomerProblem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerProblemBean extends CustomerProblem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2088956218423446207L;
	
	private Long userId;
	
	/**
     * 健康问题 key list
     */
    private List<String> healthKeyList;

    /**
     * 生活问题 key list
     */
    private List<String> lifeKeyList;
    
	/**
     * 健康问题 key list
     */
    private List<String> healthValueList;

    /**
     * 生活问题 key list
     */
    private List<String> lifeValueList;

}