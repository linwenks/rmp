package com.rmp.api.base.service;

import java.util.List;

import com.rmp.common.page.QueryPage;
import com.rmp.info.base.model.Model;

public interface BaseService {
	
	public final static String ERROR_MSG = "系统繁忙，请稍后再试！";
	
	// 增加
	public final static String INSERT = "base_insert";
	
	// 增加 批量
	public final static String INSERT_BATCH = "base_insert_batch";
	
	// 增加 选择参数
	public final static String INSERT_SEL = "base_insert_sel";
	
	// 增加 选择参数 批量
	public final static String INSERT_SEL_BATCH = "base_insert_sel_batch";
	
	
	// 修改
	public final static String UPDATE = "base_update";
	
	// 修改 选择参数
	public final static String UPDATE_SEl = "base_update_sel";
	
	// 修改 根据主键
	public final static String UPDATE_PK = "base_update_pk";
	
	// 修改 根据主键 选择参数 
	public final static String UPDATE_PK_SEl = "base_update_pk_sel";
	
	// 修改 根据主键 控制版本
	public final static String UPDATE_PK_VER = "base_update_pk_ver";
	
	// 修改 根据主键 选择参数 控制版本
	public final static String UPDATE_PK_SEl_VER = "base_update_pk_sel_ver";
	
	
	// 逻辑删除 根据主键 
	public final static String DELETE_PK_LOGIC = "base_delete_pk_logic";
	
	// 逻辑删除 根据主键 控制版本
	public final static String DELETE_PK_VER_LOGIC = "base_delete_pk_ver_logic";
	
	
	// 删除
	public final static String DELETE = "base_delete";
	
	// 删除 根据主键 
	public final static String DELETE_PK = "base_delete_pk";
	
	// 删除 根据主键 控制版本
	public final static String DELETE_PK_VER = "base_delete_pk_ver";
	

	public Class<?> getModelClass();
	
	public Class<?> getBeanClass();

	public Class<?> getCriteriaClass();

	public Object getMapper();
	
	public Object exe(String cmd, Object obj);
	
	/**
	 * 查询 by id
	 * @param id
	 * @param classOfT
	 * @return
	 */
	public <T> T selectById(Long id);
	
	/**
	 * 查询 one
	 * @param bean
	 * @param classOfT
	 * @return
	 */
	public <T> T selectOne(Model bean);
	
	/**
	 * 查询 数量
	 * @param bean
	 * @param classOfT
	 * @return
	 */
	public Long selectCount(Model bean);
	
	/**
	 * 查询 list
	 * @param queryPage
	 * @param bean
	 * @param classOfT
	 * @return
	 */
	public <T> List<T> selectList(QueryPage queryPage, Model bean);
}