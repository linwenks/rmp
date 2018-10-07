package com.rmp.info.util.mybatis;

import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;  
  
/**
 * update pk sel ver
 * @author linw
 *
 */
public class Mybatis3PluginByUpdatePkSelVer extends BaseMybatis3Plugin {
	
	private String methodName = "updateByPrimaryKeySelectiveVer";
	
	@Override
	public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		String name = "record";
		Method method = new Method(methodName);
		method.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()), name));
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(new FullyQualifiedJavaType("int"));
		interfaze.addMethod(method);
		return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
	}
	
	@Override
	public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
		
		String tableName = getTatbleName(introspectedTable);
		
		IntrospectedColumn verColumn = introspectedTable.getColumn(VERSION);
		if (verColumn == null) {
			log.warn(" Mybatis3PluginByUpdatePkSelVer : [table=" + tableName + "][column=" + VERSION + "] 字段不存在");
			return super.sqlMapDocumentGenerated(document, introspectedTable);
		}
		String verJdbcType = getJdbcType(VERSION, introspectedTable);
		String verJavaName = getJavaName(VERSION, introspectedTable);
		
		
		XmlElement parentElement = document.getRootElement();
		
		XmlElement update = new XmlElement("update");
		update.addAttribute(new Attribute("id", methodName));
		update.addAttribute(new Attribute("parameterType", introspectedTable.getBaseRecordType()));
		
		update.addElement(new TextElement("update " + tableName));
		
		
		XmlElement set = new XmlElement("set");
		
		List<IntrospectedColumn> columnList = introspectedTable.getNonPrimaryKeyColumns();
		if (columnList != null && !columnList.isEmpty()) {
			for (IntrospectedColumn column : columnList) {
				String javaName = column.getJavaProperty();
				String jdbcName = column.getActualColumnName();
				String jdbcType = column.getJdbcTypeName();
				
				if (VERSION.equalsIgnoreCase(jdbcName)) {
					continue;
				}
				
				XmlElement setIf = new XmlElement("if");
				setIf.addAttribute(new Attribute("test", javaName + " != null"));
				setIf.addElement(new TextElement("  " + jdbcName + " = #{" + javaName + ",jdbcType=" + jdbcType + "},"));
				set.addElement(setIf);
			}
		}
		set.addElement(new TextElement("  " + VERSION + " = " + VERSION + " + 1"));
		update.addElement(set);
		
		
		String pkJdbcName = getPkJdbcName(introspectedTable);
    	String pkJavaName = getPkJavaName(introspectedTable);
    	String pkJdbcType = getPkJdbcType(introspectedTable);
    	
		
    	update.addElement(new TextElement("where " + pkJdbcName + " = #{" + pkJavaName + ",jdbcType=" + pkJdbcType + "}"));
		update.addElement(new TextElement("and " + VERSION + " = #{" + verJavaName + ",jdbcType=" + verJdbcType + "}"));
		
		parentElement.addElement(update);

		return super.sqlMapDocumentGenerated(document, introspectedTable);
	}
}  