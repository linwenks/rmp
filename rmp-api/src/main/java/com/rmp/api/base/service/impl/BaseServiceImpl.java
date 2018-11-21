package com.rmp.api.base.service.impl;

import static com.rmp.api.util.MsgEnum.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.service.BaseService;
import com.rmp.common.page.QueryPage;
import com.rmp.info.base.mapper.BaseMapper;
import com.rmp.info.base.model.Model;

@SuppressWarnings("unchecked")
public abstract class BaseServiceImpl<T extends Model, B, E> implements BaseService<B> {
	
	protected static Logger log = LoggerFactory.getLogger(BaseServiceImpl.class);
	
	private static final String orderBy = " id desc ";
	
	public abstract BaseMapper<T, E> mapper();
	
	public Type[] types() {
		return ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
	}
	
	public Class<T> modelClass() {
		return (Class<T>) types()[0];
	}
	
	public Class<B> beanClass() {
        return (Class<B>) types()[1];
    }
	
	public Class<E> criteriaClass() {
        return (Class<E>) types()[2];
    }
	
	private B castBean(T obj) {
		Class<B> bean = beanClass();
		B objBean = null;
		if (obj != null) {
			try {
				objBean = bean.newInstance();
			} catch (InstantiationException e) {
				log.error(e.getMessage(), e);
			} catch (IllegalAccessException e) {
				log.error(e.getMessage(), e);
			}
			if (objBean != null) {
				BeanUtils.copyProperties(obj, objBean);
			}
		}
		return objBean;
	}
	
	/**
	 * 查询 by id
	 * @param id
	 * @return
	 */
	@Override
	public B selectById(Long id) {
		T obj = null;
		try {
			obj = mapper().selectByPrimaryKey(id);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return castBean(obj);
	}
	
	@Override
	public B selectOne(B bean) {
		Class<E> criteriaClass = criteriaClass();
		
		T obj = null;
		try {
			E criteria = criteriaClass.newInstance();
			Object criteriaDetail = criteriaClass.getMethod("createCriteria", null).invoke(criteria, null);
			where(criteriaDetail, bean);
			obj = mapper().selectByExampleForOne(criteria);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return castBean(obj);
	}
	
	@Override
	public Long selectCount(B bean) {
		Class<E> criteriaClass = criteriaClass();
		
		Long count = 0L;
		try {
			E criteria = criteriaClass.newInstance();
			Object criteriaDetail = criteriaClass.getMethod("createCriteria", null).invoke(criteria, null);
			where(criteriaDetail, bean);
			count = mapper().countByExample(criteria);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return count;
	}
	
	@Override
	public List<B> selectList(QueryPage queryPage, B bean) {
		Class<E> criteriaClass = criteriaClass();
		
		List<T> list = null;
		List<B> beanList = null;
		
		Integer limitStart = null;
		Integer limitEnd = null;
		if (queryPage != null) {
			limitStart = queryPage.getLimitStart();
			limitEnd = queryPage.getLimitEnd();
		}
		try {
			E criteria = criteriaClass.newInstance();
			String orderByTmp = orderBy;
			if (bean != null) {
				Object orderBy = bean.getClass().getMethod("getOrderBy", null).invoke(bean, null);
				if (orderBy != null) {
					orderByTmp = orderBy.toString();
				}
				/*
				String orderBy = bean.getOrderBy();
				if (!StringUtils.isEmpty(orderBy)) {
					orderByTmp = orderBy;
				}
				*/
			}
			criteriaClass.getMethod("setOrderByClause", new Class[]{String.class}).invoke(criteria, new Object[]{orderByTmp});
			if (queryPage != null) {
				criteriaClass.getMethod("setLimitStart", new Class[]{Integer.class}).invoke(criteria, new Object[]{limitStart});
				criteriaClass.getMethod("setLimitEnd", new Class[]{Integer.class}).invoke(criteria, new Object[]{limitEnd});
			}
			Object criteriaDetail = criteriaClass.getMethod("createCriteria", null).invoke(criteria, null);
			where(criteriaDetail, bean);
			list = mapper().selectByExample(criteria);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		if (!CollectionUtils.isEmpty(list)) {
			if (queryPage != null) {
				long recordCount = this.selectCount(bean);
				queryPage.setRecordCount(recordCount);
			}
			beanList = list.stream().map(obj -> {
				return castBean(obj);
			}).collect(Collectors.toList());
		}
		return beanList;
	}
	
	protected void where(Object criteria, B bean) {
		if (bean == null) return;
	}
	
	public Object exe(String cmd, Object obj) {
		try {
			switch (cmd) {
			case INSERT: return insert((B) obj);
			case INSERT_SEL: return insertSel((B) obj);
			case UPDATE_PK: return updatePk((B) obj);
			case UPDATE_PK_SEl: return updatePkSel((B) obj);
			case UPDATE_PK_VER: return updatePkVer((B) obj);
			case UPDATE_PK_SEl_VER: return updatePkSelVer((B) obj);
			case DELETE: return delete((B) obj);
			case DELETE_PK: return deletePk((Long) obj);
			default: break;
			}
		} catch (AppException e) {
			throw e;
		} catch (Exception e) {
			AppException.toThrow(e);
		}
		return null;
	}
	
	protected int insert(B obj) {
		int row = 0;
		try {
			row = mapper().insert((T) obj);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		if (row == 0) AppException.toThrow(MSG_00010);
		return row;
	}
	
	protected int insertSel(B obj) {
		int row = 0;
		try {
			row = mapper().insertSelective((T) obj);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		if (row == 0) AppException.toThrow(MSG_00010);
		return row;
	}
	
	protected int updatePk(B obj) {
		if (obj == null) AppException.toThrow(MSG_00003);
		int row = 0;
		try {
			row = mapper().updateByPrimaryKey((T) obj);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		if (row == 0) AppException.toThrow(MSG_00010);
		return row;
	}
	
	protected int updatePkSel(B obj) {
		if (obj == null) AppException.toThrow(MSG_00003);
		int row = 0;
		try {
			row = mapper().updateByPrimaryKeySelective((T) obj);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		if (row == 0) AppException.toThrow(MSG_00010);
		return row;
	}
	
	protected int updatePkVer(B obj) {
		if (obj == null) AppException.toThrow(MSG_00003);
		int row = 0;
		try {
			row = mapper().updateByPrimaryKeyVer((T) obj);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		if (row == 0) AppException.toThrow(MSG_00010);
		return row;
	}
	
	protected int updatePkSelVer(B obj) {
		if (obj == null) AppException.toThrow(MSG_00003);
		int row = 0;
		try {
			row = mapper().updateByPrimaryKeySelectiveVer((T) obj);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		if (row == 0) AppException.toThrow(MSG_00010);
		return row;
	}
	
	protected int delete(B obj) {
		Class<E> criteriaClass = criteriaClass();
		
		if (obj == null) AppException.toThrow(MSG_00003);
		int row = 0;
		try {
			E criteria = criteriaClass.newInstance();
			Object criteriaDetail = criteriaClass.getMethod("createCriteria", null).invoke(criteria, null);
			where(criteriaDetail, obj);
			row = mapper().deleteByExample(criteria);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		if (row == 0) AppException.toThrow(MSG_00010);
		return row;
	}
	
	protected int deletePk(Long id) {
		if (id == null) AppException.toThrow(MSG_00003);
		int row = 0;
		try {
			row = mapper().deleteByPrimaryKey(id);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		if (row == 0) AppException.toThrow(MSG_00010);
		return row;
	}
}