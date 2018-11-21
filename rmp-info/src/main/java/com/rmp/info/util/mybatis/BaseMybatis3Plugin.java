package com.rmp.info.util.mybatis;

import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.logging.Log;
import org.mybatis.generator.logging.LogFactory;

import com.rmp.info.base.mapper.BaseMapper;  
  
/**
 * 
 * @author linw
 *
 */
public abstract class BaseMybatis3Plugin extends PluginAdapter {
	
	protected static Log log = LogFactory.getLog(BaseMybatis3Plugin.class);
	
	protected static final String DB_MYSQL = "mysql";
	protected static final String DB_ORACLE = "oracle";
	protected static final String DB_TYPE = DB_MYSQL;
	
	protected static final String VERSION = "version";
	protected static final String LIMIT_START = "limitStart";
	protected static final String LIMIT_END = "limitEnd";
	
	@Override
	public boolean validate(List<String> warnings) {
		// TODO Auto-generated method stub
		return true;
	}
	
	/**
	 * 表名
	 * @param introspectedTable
	 * @return
	 */
	protected String getTatbleName(IntrospectedTable introspectedTable) {
		String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();
		return tableName;
	}
	
	/**
	 * 主键（只取一个）
	 * @param introspectedTable
	 * @return
	 */
	protected IntrospectedColumn getPkColumn(IntrospectedTable introspectedTable) {
		IntrospectedColumn introspectedColumn = null;
		List<IntrospectedColumn> primaryKeyColumnList = introspectedTable.getPrimaryKeyColumns();
    	if (primaryKeyColumnList != null && primaryKeyColumnList.size() > 0) {
    		introspectedColumn = primaryKeyColumnList.get(0);
    	}
    	return introspectedColumn;
	}
	
	protected String getPkJavaName(IntrospectedTable introspectedTable) {
    	return getPkColumn(introspectedTable).getJavaProperty();
	}
	
	protected String getPkJavaType(IntrospectedTable introspectedTable) {
    	return getPkColumn(introspectedTable).getFullyQualifiedJavaType().getFullyQualifiedName();
	}
	
	protected String getPkJdbcName(IntrospectedTable introspectedTable) {
    	return getPkColumn(introspectedTable).getActualColumnName();
	}
	
	protected String getPkJdbcType(IntrospectedTable introspectedTable) {
    	return getPkColumn(introspectedTable).getJdbcTypeName();
	}
	
	/**
	 * 查询 column
	 * @param columnName
	 * @param introspectedTable
	 * @return
	 */
	protected IntrospectedColumn getColumn(String columnName, IntrospectedTable introspectedTable) {
    	return introspectedTable.getColumn(columnName);
	}
	
	protected String getJavaName(String columnName, IntrospectedTable introspectedTable) {
    	return getColumn(columnName, introspectedTable).getJavaProperty();
	}
	
	protected String getJavaType(String columnName, IntrospectedTable introspectedTable) {
    	return getColumn(columnName, introspectedTable).getFullyQualifiedJavaType().getFullyQualifiedName();
	}
	
	protected String getJdbcName(String columnName, IntrospectedTable introspectedTable) {
    	return getColumn(columnName, introspectedTable).getActualColumnName();
	}
	
	protected String getJdbcType(String columnName, IntrospectedTable introspectedTable) {
    	return getColumn(columnName, introspectedTable).getJdbcTypeName();
	}
	
	/**
	 * 生成dao
	 */
	@Override
	public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		/**
		 * 主键默认采用java.lang.Integer
		 */
		FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType("BaseMapper<" + introspectedTable.getBaseRecordType()
				+ "," + introspectedTable.getExampleType() + ">");
		FullyQualifiedJavaType imp = new FullyQualifiedJavaType(BaseMapper.class.getName());
		/**
		 * 添加 extends MybatisBaseMapper
		 */
		interfaze.addSuperInterface(fqjt);
		/**
		 * 添加import my.mabatis.example.base.MybatisBaseMapper;
		 */
		interfaze.addImportedType(imp);
		/**
		 * 方法不需要
		 */
		interfaze.getMethods().clear();
		interfaze.getAnnotations().clear();
		return true;
	}
}  