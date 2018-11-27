package com.rmp.api.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.rmp.api.base.util.BaseUtil;
import com.rmp.api.model.SysCodeBean;


@Component
public class SysCodeUtil extends BaseUtil {
	
	public SysCodeUtil() {
		load();
	}
	
	private static List<SysCodeBean> sysCodeBeanListTree;
	private static List<SysCodeBean> sysCodeBeanListTreeSimple;
	
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
			
		} else {
			sysCodeBeanListTree = null;
			sysCodeBeanListTreeSimple = null;
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
}