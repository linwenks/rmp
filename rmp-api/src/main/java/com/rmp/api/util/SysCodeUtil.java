package com.rmp.api.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
	
	private static Map<String, SysCodeBean> sysCodeBeanMap;
	private static Map<String, SysCodeBean> sysCodeBeanMapSimple;
	
	public static void load() {
		SysCodeBean sysCodeBean = new SysCodeBean();
		List<SysCodeBean> sysCodeBeanList = sysCodeService.selectList(null, sysCodeBean);
		
		if (!CollectionUtils.isEmpty(sysCodeBeanList)) {
			sysCodeBeanListTree = assemblyTreeList(sysCodeBeanList);
			sysCodeBeanListTreeSimple = assemblyTreeListSimple(sysCodeBeanListTree);
			sysCodeBeanMap = sysCodeBeanListTree.stream().collect(Collectors.toMap(SysCodeBean::getKey, Function.identity()));
			sysCodeBeanMapSimple = sysCodeBeanListTreeSimple.stream().collect(Collectors.toMap(SysCodeBean::getKey, Function.identity()));
			
			for (Map.Entry<String, SysCodeBean> entry : sysCodeBeanMap.entrySet()) {
				SysCodeBean value = entry.getValue();
				List<SysCodeBean> childSysCodeBeanList = value.getChildList();
				if (!CollectionUtils.isEmpty(childSysCodeBeanList)) {
					Map<String, SysCodeBean> childSysCodeBeanMap = childSysCodeBeanList.stream().collect(Collectors.toMap(SysCodeBean::getKey, Function.identity()));
					value.setChildMap(childSysCodeBeanMap);
				}
			}
			
			for (Map.Entry<String, SysCodeBean> entry : sysCodeBeanMapSimple.entrySet()) {
				SysCodeBean value = entry.getValue();
				List<SysCodeBean> childSysCodeBeanList = value.getChildList();
				if (!CollectionUtils.isEmpty(childSysCodeBeanList)) {
					Map<String, SysCodeBean> childSysCodeBeanMap = childSysCodeBeanList.stream().collect(Collectors.toMap(SysCodeBean::getKey, Function.identity()));
					value.setChildMap(childSysCodeBeanMap);
				}
			}
		} else {
			sysCodeBeanListTree = null;
			sysCodeBeanListTreeSimple = null;
			sysCodeBeanMap = null;
			sysCodeBeanMapSimple = null;
		}
		
		if (!CollectionUtils.isEmpty(sysCodeBeanMap)) {
			
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
		if (beanList != null && !beanList.isEmpty()) {
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
	
	public static SysCodeBean getBean(String codeKey) {
		SysCodeBean sysCodeBean = null;
		if (!CollectionUtils.isEmpty(sysCodeBeanMap) && !StringUtils.isEmpty(codeKey)) {
			sysCodeBean = sysCodeBeanMap.get(codeKey);
		}
		return sysCodeBean;
	}
	
	public static SysCodeBean getBeanSimple(String codeKey) {
		SysCodeBean sysCodeBean = null;
		if (!CollectionUtils.isEmpty(sysCodeBeanMapSimple) && !StringUtils.isEmpty(codeKey)) {
			sysCodeBean = sysCodeBeanMapSimple.get(codeKey);
		}
		return sysCodeBean;
	}
	
	public static SysCodeBean getBean(String pCodeKey, String codeKey) {
		SysCodeBean sysCodeBean = null;
		SysCodeBean pSysCodeBean = getBean(pCodeKey);
		if (pSysCodeBean != null && !StringUtils.isEmpty(codeKey)) {
			Map<String, SysCodeBean> sysCodeBeanMap = pSysCodeBean.getChildMap();
			if (!CollectionUtils.isEmpty(sysCodeBeanMap)) {
				sysCodeBean = sysCodeBeanMap.get(codeKey);
			}
		}
		return sysCodeBean;
	}
	
	public static SysCodeBean getBeanSimple(String pCodeKey, String codeKey) {
		SysCodeBean sysCodeBean = null;
		SysCodeBean pSysCodeBean = getBeanSimple(pCodeKey);
		if (pSysCodeBean != null && !StringUtils.isEmpty(codeKey)) {
			Map<String, SysCodeBean> sysCodeBeanMap = pSysCodeBean.getChildMap();
			if (!CollectionUtils.isEmpty(sysCodeBeanMap)) {
				sysCodeBean = sysCodeBeanMap.get(codeKey);
			}
		}
		return sysCodeBean;
	}
	
	public static String getValue(String codeKey) {
		String codeValue = null;
		SysCodeBean sysCodeBean = getBean(codeKey);
		if (sysCodeBean != null) {
			codeValue = sysCodeBean.getValue();
		}
		return codeValue;
	}
	
	public static String getValueSimple(String codeKey) {
		String codeValue = null;
		SysCodeBean sysCodeBean = getBeanSimple(codeKey);
		if (sysCodeBean != null) {
			codeValue = sysCodeBean.getValue();
		}
		return codeValue;
	}
	
	public static String getValue(String pCodeKey, String codeKey) {
		String codeValue = null;
		SysCodeBean sysCodeBean = getBean(pCodeKey, codeKey);
		if (sysCodeBean != null) {
			codeValue = sysCodeBean.getValue();
		}
		return codeValue;
	}
	
	public static String getValueSimple(String pCodeKey, String codeKey) {
		String codeValue = null;
		SysCodeBean sysCodeBean = getBeanSimple(pCodeKey, codeKey);
		if (sysCodeBean != null) {
			codeValue = sysCodeBean.getValue();
		}
		return codeValue;
	}
	
	public static List<SysCodeBean> getChildList(String codeKey) {
		SysCodeBean sysCodeBean = getBean(codeKey);
		if (sysCodeBean != null) {
			return sysCodeBean.getChildList();
		}
		return null;
	}
	
	public static List<SysCodeBean> getChildListSimple(String codeKey) {
		SysCodeBean sysCodeBean = getBeanSimple(codeKey);
		if (sysCodeBean != null) {
			return sysCodeBean.getChildList();
		}
		return null;
	}
}