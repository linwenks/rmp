package com.rmp.info.util.mybatis;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.annotations.Param;
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
 * insert batch
 * @author linw
 *
 */
public class Mybatis3PluginByInsertBatch extends BaseMybatis3Plugin {
	
	private String methodName = "insertBatch";
	private String parameterName = "recordColl";
	
	@Override
	public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		
		Method method = new Method(methodName);
		
		method.addParameter(new Parameter(new FullyQualifiedJavaType("@" + Param.class.getSimpleName() + "(\"" + parameterName + "\") " + Collection.class.getName() + "<" + introspectedTable.getBaseRecordType() + ">"), parameterName));
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(new FullyQualifiedJavaType("int"));
		interfaze.addMethod(method);
		return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
	}
	
	@Override
	public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
		
		String tableName = getTatbleName(introspectedTable);
		
		XmlElement parentElement = document.getRootElement();
		
		XmlElement insert = new XmlElement("insert");
		insert.addAttribute(new Attribute("id", methodName));
		insert.addElement(new TextElement("insert into " + tableName + " ( "));
		
		List<IntrospectedColumn> columnList = introspectedTable.getAllColumns();
		if (columnList != null && !columnList.isEmpty()) {
			for (Iterator<IntrospectedColumn> iterator = columnList.iterator(); iterator.hasNext();) {
				IntrospectedColumn column = iterator.next();
				if (column != null) {
					String jdbcName = column.getActualColumnName();
					
					if (iterator.hasNext()) {
						insert.addElement(new TextElement(jdbcName + ","));
					} else {
						insert.addElement(new TextElement(jdbcName));
					}
				}
			}
		}
		
		insert.addElement(new TextElement(" ) values "));
		
		String itemName = "record";
		
		XmlElement foreach = new XmlElement("foreach");
		foreach.addAttribute(new Attribute("collection", parameterName));
		foreach.addAttribute(new Attribute("item", itemName));
		foreach.addAttribute(new Attribute("separator", ","));
		
		foreach.addElement(new TextElement("("));
		
		XmlElement trim2 = new XmlElement("trim");
		trim2.addAttribute(new Attribute("suffixOverrides", ","));
		
		if (columnList != null && !columnList.isEmpty()) {
			for (Iterator<IntrospectedColumn> iterator = columnList.iterator(); iterator.hasNext();) {
				IntrospectedColumn column = iterator.next();
				if (column != null) {
					String javaName = column.getJavaProperty();
					String jdbcType = column.getJdbcTypeName();
					
					trim2.addElement(new TextElement(" #{" + itemName + "." + javaName + ",jdbcType=" + jdbcType + "}, "));
				}
			}
		}
		
		foreach.addElement(trim2);
		
		foreach.addElement(new TextElement(")"));
		
		insert.addElement(foreach);
		
		parentElement.addElement(insert);

		return super.sqlMapDocumentGenerated(document, introspectedTable);
	}
}  