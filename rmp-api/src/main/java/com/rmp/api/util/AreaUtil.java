package com.rmp.api.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.rmp.api.base.util.BaseUtil;
import com.rmp.api.model.AreaBean;

@Component
public class AreaUtil extends BaseUtil {
	
	public AreaUtil() {
		load();
	}
	
	// 树
	private static List<AreaBean> areaBeanListTree;
	// 树 简化
	private static List<AreaBean> areaBeanListTreeSimple;
	// map
	private static Map<Long, AreaBean> areaBeanMap;
	
	public static void load() {
		AreaBean areaBean = new AreaBean();
		areaBean.setOrderBy(" id ");
		areaBean.setStartId(0L);
		List<AreaBean> areaBeanList = areaService.selectList(null, areaBean);
		if (!CollectionUtils.isEmpty(areaBeanList)) {
			areaBeanListTree = assemblyTreeList(areaBeanList);
			areaBeanListTreeSimple = assemblyTreeListSimple(areaBeanListTree);
			areaBeanMap = areaBeanList.stream().collect(Collectors.toMap(AreaBean::getId, Function.identity()));
		} else {
			areaBeanListTree = null;
			areaBeanListTreeSimple = null;
			areaBeanMap = null;
		}
	}
	
	/**
	 * 组装 树 list
	 * @param beanList
	 * @return
	 */
	private static List<AreaBean> assemblyTreeList(List<AreaBean> beanList) {
		List<AreaBean> newBeanList = null;
		if (!CollectionUtils.isEmpty(beanList)) {
			newBeanList = new ArrayList<AreaBean>();
			for (AreaBean bean : beanList) {
				if (bean != null) {
					// 父级类型 当前
					Long pid = bean.getPid();
					bean.setNameAll(bean.getName());
					if (pid == 1) {
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
	 * 组装 树 list 2
	 * @param beanList
	 * @param bean
	 */
	private static void addToTree(List<AreaBean> beanList, AreaBean bean) {
		if (!CollectionUtils.isEmpty(beanList)) {
			for (AreaBean beanTmp : beanList) {
				if (beanTmp != null && bean != null) {
					if (beanTmp.getId().longValue() == bean.getPid().longValue()) {
						bean.setNameAll(beanTmp.getNameAll() + bean.getName());
						bean.setParent(beanTmp);
						if (CollectionUtils.isEmpty(beanTmp.getChildList())) beanTmp.setChildList(new ArrayList<>());
						beanTmp.getChildList().add(bean);
					}
				}
			}
		}
	}
	
	/**
	 * 组装 树 list 简化
	 * @param beanList
	 * @return
	 */
	private static List<AreaBean> assemblyTreeListSimple(List<AreaBean> beanList) {
		List<AreaBean> newBeanList = null;
		if (!CollectionUtils.isEmpty(beanList)) {
			newBeanList = new ArrayList<AreaBean>();
			for (AreaBean bean : beanList) {
				if (bean != null) {
					AreaBean newBean = new AreaBean();
					BeanUtils.copyProperties(bean, newBean);
					newBean.setParent(null);
					newBean.setChildList(assemblyTreeListSimple(newBean.getChildList()));
					newBeanList.add(newBean);
				}
			}
		}
		return newBeanList;
	}
	
	public static List<AreaBean> getListTree() {
		return areaBeanListTree;
	}
	
	public static List<AreaBean> getListTreeSimple() {
		return areaBeanListTreeSimple;
	}
	
	/**
	 * 获取 bean
	 * @param id
	 * @return
	 */
	public static AreaBean getBean(Long id) {
		if (!CollectionUtils.isEmpty(areaBeanMap)) {
			AreaBean areaBean = areaBeanMap.get(id);
			if (areaBean != null) {
				return areaBean;
			}
		}
		return null;
	}
	
	/**
	 * 获取 全称
	 * @param id
	 * @return
	 */
	public static String getNameAll(Long id) {
		AreaBean areaBean = getBean(id);
		if (areaBean != null) {
			return areaBean.getNameAll();
		}
		return null;
	}
}