package com.rmp.api.base.service.impl;

import static com.rmp.api.util.MsgEnum.*;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.google.gson.internal.Primitives;
import com.rmp.api.base.exception.AppException;
import com.rmp.api.base.service.BaseService;
import com.rmp.common.page.QueryPage;
import com.rmp.info.base.model.Model;

public abstract class BaseServiceImpl implements BaseService {
	
	protected static Logger log = LoggerFactory.getLogger(BaseServiceImpl.class);
	
	private static final String orderBy = " id desc ";
	
	private <T> T castBean(Object obj, Class<T> classOfT) {
		Object objBean = null;
		if (obj != null) {
			try {
				objBean = classOfT.newInstance();
			} catch (InstantiationException e) {
				log.error(e.getMessage(), e);
			} catch (IllegalAccessException e) {
				log.error(e.getMessage(), e);
			}
			if (objBean != null) {
				BeanUtils.copyProperties(obj, objBean);
				return Primitives.wrap(classOfT).cast(objBean);
			}
		}
		return null;
	}
	
	/**
	 * 查询 by id
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T selectById(Long id) {
		Object mapper = getMapper();
		Class<?> modelBeanClass = getBeanClass();
		
		Object obj = null;
		try {
			obj = mapper.getClass().getMethod("selectByPrimaryKey", new Class[]{Long.class}).invoke(mapper, new Object[]{id});
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return (T) castBean(obj, modelBeanClass);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T selectOne(Model bean) {
		Class<?> modelBeanClass = getBeanClass();
		Class<?> criteriaClass = getCriteriaClass();
		Object mapper = getMapper();
		
		Object obj = null;
		try {
			Object criteria = criteriaClass.newInstance();
			Object criteriaDetail = criteriaClass.getMethod("createCriteria", null).invoke(criteria, null);
			where(criteriaDetail, bean);
			obj = mapper.getClass().getMethod("selectByExampleForOne", new Class[]{criteriaClass}).invoke(mapper, new Object[]{criteria});
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return (T) castBean(obj, modelBeanClass);
	}
	
	@Override
	public Long selectCount(Model bean) {
		Class<?> criteriaClass = getCriteriaClass();
		Object mapper = getMapper();
		
		Long count = 0L;
		try {
			Object criteria = criteriaClass.newInstance();
			Object criteriaDetail = criteriaClass.getMethod("createCriteria", null).invoke(criteria, null);
			where(criteriaDetail, bean);
			count = (Long) mapper.getClass().getMethod("countByExample", new Class[]{criteriaClass}).invoke(mapper, new Object[]{criteria});
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return count;
	}
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public <T> List<T> selectList(QueryPage queryPage, Model bean) {
		Class<?> modelBeanClass = getBeanClass();
		Class<?> criteriaClass = getCriteriaClass();
		Object mapper = getMapper();
		
		List<?> list = null;
		List<Object> beanList = null;
		
		Integer limitStart = null;
		Integer limitEnd = null;
		if (queryPage != null) {
			limitStart = queryPage.getLimitStart();
			limitEnd = queryPage.getLimitEnd();
		}
		try {
			Object criteria = criteriaClass.newInstance();
			String orderByTmp = orderBy;
			if (bean != null) {
				String orderBy = bean.getOrderBy();
				if (!StringUtils.isEmpty(orderBy)) {
					orderByTmp = orderBy;
				}
			}
			criteriaClass.getMethod("setOrderByClause", new Class[]{String.class}).invoke(criteria, new Object[]{orderByTmp});
			if (queryPage != null) {
				criteriaClass.getMethod("setLimitStart", new Class[]{Integer.class}).invoke(criteria, new Object[]{limitStart});
				criteriaClass.getMethod("setLimitEnd", new Class[]{Integer.class}).invoke(criteria, new Object[]{limitEnd});
			}
			Object criteriaDetail = criteriaClass.getMethod("createCriteria", null).invoke(criteria, null);
			where(criteriaDetail, bean);
			list = (List<?>) mapper.getClass().getMethod("selectByExample", new Class[]{criteriaClass}).invoke(mapper, new Object[]{criteria});
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		if (!CollectionUtils.isEmpty(list)) {
			if (queryPage != null) {
				long recordCount = this.selectCount(bean);
				queryPage.setRecordCount(recordCount);
			}
			beanList = list.stream().map(obj -> {
				return castBean(obj, modelBeanClass);
			}).collect(Collectors.toList());
		}
		return (List<T>) beanList;
	}
	
	protected abstract void where(Object criteria, Object bean);
	
	public Object exe(String cmd, Object obj) {
		try {
			switch (cmd) {
			case INSERT: return insert(obj);
			case INSERT_SEL: return insertSel(obj);
			case UPDATE_PK: return updatePk(obj);
			case UPDATE_PK_SEl: return updatePkSel(obj);
			case UPDATE_PK_VER: return updatePkVer(obj);
			case UPDATE_PK_SEl_VER: return updatePkSelVer(obj);
			case DELETE: return delete(obj);
			case DELETE_PK: return deletePk((Long) obj);
			default: break;
			}
		} catch (AppException e) {
			throw e;
		} catch (Exception e) {
			throw new AppException(e);
		}
		return null;
	}
	
	protected int insert(Object obj) {
		Class<?> model = getModelClass();
		Object mapper = getMapper();
		
		int row = 0;
		try {
			row = (int) mapper.getClass().getMethod("insert", new Class[]{model}).invoke(mapper, new Object[]{obj});
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		if (row == 0) throw new AppException(MSG_00010);
		return row;
	}
	
	protected int insertSel(Object obj) {
		Class<?> model = getModelClass();
		Object mapper = getMapper();
		
		int row = 0;
		try {
			row = (int) mapper.getClass().getMethod("insertSelective", new Class[]{model}).invoke(mapper, new Object[]{obj});
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		if (row == 0) throw new AppException(MSG_00010);
		return row;
	}
	
	protected int updatePk(Object obj) {
		Class<?> model = getModelClass();
		Object mapper = getMapper();
		
		if (obj == null) throw new AppException(MSG_00003);
		int row = 0;
		try {
			row = (int) mapper.getClass().getMethod("updateByPrimaryKey", new Class[]{model}).invoke(mapper, new Object[]{obj});
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		if (row == 0) throw new AppException(MSG_00010);
		return row;
	}
	
	protected int updatePkSel(Object obj) {
		Class<?> model = getModelClass();
		Object mapper = getMapper();
		
		if (obj == null) throw new AppException(MSG_00003);
		int row = 0;
		try {
			row = (int) mapper.getClass().getMethod("updateByPrimaryKeySelective", new Class[]{model}).invoke(mapper, new Object[]{obj});
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		if (row == 0) throw new AppException(MSG_00010);
		return row;
	}
	
	protected int updatePkVer(Object obj) {
		Class<?> model = getModelClass();
		Object mapper = getMapper();
		
		if (obj == null) throw new AppException(MSG_00003);
		int row = 0;
		try {
			row = (int) mapper.getClass().getMethod("updateByPrimaryKeyVer", new Class[]{model}).invoke(mapper, new Object[]{obj});
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		if (row == 0) throw new AppException(MSG_00010);
		return row;
	}
	
	protected int updatePkSelVer(Object obj) {
		Class<?> model = getModelClass();
		Object mapper = getMapper();
		
		if (obj == null) throw new AppException(MSG_00003);
		int row = 0;
		try {
			row = (int) mapper.getClass().getMethod("updateByPrimaryKeySelectiveVer", new Class[]{model}).invoke(mapper, new Object[]{obj});
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		if (row == 0) throw new AppException(MSG_00010);
		return row;
	}
	
	protected int delete(Object obj) {
		Class<?> criteriaClass = getCriteriaClass();
		Object mapper = getMapper();
		
		if (obj == null) throw new AppException(MSG_00003);
		int row = 0;
		try {
			Object criteria = criteriaClass.newInstance();
			Object criteriaDetail = criteriaClass.getMethod("createCriteria", null).invoke(criteria, null);
			where(criteriaDetail, obj);
			row = (int) mapper.getClass().getMethod("deleteByExample", new Class[]{criteriaClass}).invoke(mapper, new Object[]{criteria});
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		if (row == 0) throw new AppException(MSG_00010);
		return row;
	}
	
	protected int deletePk(Long id) {
		Object mapper = getMapper();
		
		if (id == null) throw new AppException(MSG_00003);
		int row = 0;
		try {
			row = (int) mapper.getClass().getMethod("deleteByPrimaryKey", new Class[]{id.getClass()}).invoke(mapper, new Object[]{id});
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		if (row == 0) throw new AppException(MSG_00010);
		return row;
	}
}