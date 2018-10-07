package com.rmp.info.util.mybatis;

import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;  
  
/**
 * lombok
 * @author linw
 *
 */
public class Mybatis3PluginByLombok extends BaseMybatis3Plugin {
	
	@Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    	
        //添加domain的import
//        topLevelClass.addImportedType(Data.class.getName());
        topLevelClass.addImportedType(Getter.class.getName());
        topLevelClass.addImportedType(Setter.class.getName());
        topLevelClass.addImportedType(Builder.class.getName());
        topLevelClass.addImportedType(NoArgsConstructor.class.getName());
        topLevelClass.addImportedType(AllArgsConstructor.class.getName());
        
//        topLevelClass.addImportedType(ApiModelProperty.class.getName());

        //添加domain的注解
//        topLevelClass.addAnnotation("@Data");
        topLevelClass.addAnnotation("@Getter");
        topLevelClass.addAnnotation("@Setter");
        topLevelClass.addAnnotation("@Builder");
        topLevelClass.addAnnotation("@NoArgsConstructor");
        topLevelClass.addAnnotation("@AllArgsConstructor");
        
        return true;
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        //不生成getter
        return false;
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        //不生成setter
        return false;
    }
}  