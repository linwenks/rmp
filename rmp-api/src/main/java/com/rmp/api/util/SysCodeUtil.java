package com.rmp.api.util;

import static com.rmp.api.util.constant.Constant.SysCode.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Maps;
import com.rmp.api.base.util.BaseUtil;
import com.rmp.api.model.SysCodeBean;


@Component
public class SysCodeUtil extends BaseUtil {
	
	public SysCodeUtil() {
		load();
	}
	
	private static List<SysCodeBean> sysCodeBeanListTree;
	private static List<SysCodeBean> sysCodeBeanListTreeSimple;
	
	private static Map<String, List<SysCodeBean>> sysCodeBeanMap;
	
	public static void load() {
		SysCodeBean sysCodeBean = new SysCodeBean();
		sysCodeBean.setOrderBy(" sort, id ");
		List<SysCodeBean> sysCodeBeanList = sysCodeService.selectList(null, sysCodeBean);
		
		if (!CollectionUtils.isEmpty(sysCodeBeanList)) {
			
			sysCodeBeanList.forEach(o -> {
				o.setIsDelete(null);
				o.setVersion(null);
				o.setCreateTime(null);
				o.setUpdateTime(null);
			});
			
			sysCodeBeanListTree = assemblyTreeList(sysCodeBeanList);
			sysCodeBeanListTreeSimple = assemblyTreeListSimple(sysCodeBeanListTree);
			
			sysCodeBeanMap = Maps.newHashMap();
			sysCodeBeanMap.put(CUSTOMER_RELATION_IMPORTANCE, getChildSimple(CUSTOMER, CUSTOMER_RELATION, CUSTOMER_RELATION_IMPORTANCE));
			sysCodeBeanMap.put(CUSTOMER_RELATION_INTIMACY, getChildSimple(CUSTOMER, CUSTOMER_RELATION, CUSTOMER_RELATION_INTIMACY));
			sysCodeBeanMap.put(CUSTOMER_RELATION_RELATIONSHIP, getChildSimple(CUSTOMER, CUSTOMER_RELATION, CUSTOMER_RELATION_RELATIONSHIP));
					
			sysCodeBeanMap.put(CUSTOMER_PROBLEM_HEALTH, getChildSimple(CUSTOMER, CUSTOMER_PROBLEM, CUSTOMER_PROBLEM_HEALTH));
			sysCodeBeanMap.put(CUSTOMER_PROBLEM_LIFE, getChildSimple(CUSTOMER, CUSTOMER_PROBLEM, CUSTOMER_PROBLEM_LIFE));
					
			sysCodeBeanMap.put(CUSTOMER_HOBBY_DIET, getChildSimple(CUSTOMER, CUSTOMER_HOBBY, CUSTOMER_HOBBY_DIET));
			sysCodeBeanMap.put(CUSTOMER_HOBBY_INTEREST, getChildSimple(CUSTOMER, CUSTOMER_HOBBY, CUSTOMER_HOBBY_INTEREST));
			sysCodeBeanMap.put(CUSTOMER_HOBBY_TASTE, getChildSimple(CUSTOMER, CUSTOMER_HOBBY, CUSTOMER_HOBBY_TASTE));
					
			sysCodeBeanMap.put(CUSTOMER_JOB_INDUSTRY, getChildSimple(CUSTOMER, CUSTOMER_JOB, CUSTOMER_JOB_INDUSTRY));
			sysCodeBeanMap.put(CUSTOMER_JOB_POSITION, getChildSimple(CUSTOMER, CUSTOMER_JOB, CUSTOMER_JOB_POSITION));
			
			sysCodeBeanMap.put(CUSTOMER_FAMILY_RELATIONSHIP, getChildSimple(CUSTOMER, CUSTOMER_FAMILY, CUSTOMER_FAMILY_RELATIONSHIP));
			
			sysCodeBeanMap.put(CUSTOMER_MEMORIAL_DAY_OCCUR_TYPE, getChildSimple(CUSTOMER, CUSTOMER_MEMORIAL_DAY, CUSTOMER_MEMORIAL_DAY_OCCUR_TYPE));
			sysCodeBeanMap.put(CUSTOMER_MEMORIAL_DAY_ADVANCE_TYPE, getChildSimple(CUSTOMER, CUSTOMER_MEMORIAL_DAY, CUSTOMER_MEMORIAL_DAY_ADVANCE_TYPE));
			
			sysCodeBeanMap.put(CUSTOMER_MAINTAIN_MAINTAIN, getChildSimple(CUSTOMER, CUSTOMER_MAINTAIN, CUSTOMER_MAINTAIN_MAINTAIN));
			
		} else {
			sysCodeBeanListTree = null;
			sysCodeBeanListTreeSimple = null;
			
			sysCodeBeanMap = null;
		}
	}
	
	/**
	 * 组装 tree list
	 * @param menuBizBeanList
	 * @return
	 */
	private static List<SysCodeBean> assemblyTreeList(List<SysCodeBean> beanList) {
		List<SysCodeBean> newBeanList = null;
		if (beanList != null && !beanList.isEmpty()) {
			newBeanList = new ArrayList<SysCodeBean>();
			for (SysCodeBean bean : beanList) {
				if (bean != null) {
					// 父级类型 当前
					Long pid = bean.getPid();
					if (pid == 0) {
						newBeanList.add(bean);
						continue;
					} else {
						addToTree(beanList, bean);
					}
				}
			}
		}
		return newBeanList;
	}
	
	/**
	 * 组装 tree list 2
	 * @param beanList
	 * @param bean
	 */
	private static void addToTree(List<SysCodeBean> beanList, SysCodeBean bean) {
		if (!CollectionUtils.isEmpty(beanList)) {
			for (SysCodeBean beanTmp : beanList) {
				if (beanTmp != null && bean != null) {
					if (beanTmp.getId().longValue() == bean.getPid().longValue()) {
						bean.setParent(beanTmp);
						if (CollectionUtils.isEmpty(beanTmp.getChildList())) beanTmp.setChildList(new ArrayList<>());
						beanTmp.getChildList().add(bean);
					}
				}
			}
		}
	}
	
	/**
	 * 组装 tree list 简化
	 * @param beanList
	 * @return
	 */
	private static List<SysCodeBean> assemblyTreeListSimple(List<SysCodeBean> beanList) {
		List<SysCodeBean> newBeanList = null;
		if (!CollectionUtils.isEmpty(beanList)) {
			newBeanList = new ArrayList<SysCodeBean>();
			for (SysCodeBean bean : beanList) {
				if (bean != null) {
					SysCodeBean newBean = new SysCodeBean();
					BeanUtils.copyProperties(bean, newBean);
					newBean.setParent(null);
					newBean.setChildList(assemblyTreeListSimple(newBean.getChildList()));
					newBeanList.add(newBean);
				}
			}
		}
		return newBeanList;
	}
	
	
	public static SysCodeBean getSimple(String... keys) {
		SysCodeBean bean = null;
		
		List<SysCodeBean> beanList = sysCodeBeanListTreeSimple;
		
		if (keys != null && keys.length > 0) {
			for (String key : keys) {
				if (StringUtils.isEmpty(key)) {
					continue;
				}
				SysCodeBean beanTmp = new SysCodeBean();
				beanTmp.setKey(key);
				
				bean = getSimple(beanList, beanTmp);
				if (bean != null) {
					beanList = bean.getChildList();
				}
			}
		}
		return bean;
	}
	
	public static SysCodeBean getSimple(List<SysCodeBean> beanList, SysCodeBean bean) {
		SysCodeBean beanTmp = null;
		if (!CollectionUtils.isEmpty(beanList)) {
			int index = beanList.indexOf(bean);
			if (index >= 0) {
				beanTmp = beanList.get(index);
			}
		}
		return beanTmp;
	}
	
	public static List<SysCodeBean> getChildSimple(String... keys) {
		SysCodeBean bean = getSimple(keys);
		if (bean != null) {
			return bean.getChildList();
		}
		return null;
	}
	
	public static String getValue(String... keys)	{
		SysCodeBean bean = getSimple(keys);
		if (bean != null) {
			return bean.getValue();
		}
		return null;
	}
	
	public static String getValue(String key1, String key2)	{
		SysCodeBean beanTmp = new SysCodeBean();
		beanTmp.setKey(key2);
		SysCodeBean bean = getSimple(getListSimple(key1), beanTmp);
		if (bean != null) {
			return bean.getValue();
		}
		return null;
	}
	
	public static List<SysCodeBean> getListSimple(String key) {
		if (!CollectionUtils.isEmpty(sysCodeBeanMap)) {
			return sysCodeBeanMap.get(key);
		}
		return null;
	}
}