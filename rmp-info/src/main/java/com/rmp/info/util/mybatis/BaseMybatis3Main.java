package com.rmp.info.util.mybatis;

import org.mybatis.generator.api.ShellRunner;  
  
/**
 * 
 * @author linw
 *
 */
public class BaseMybatis3Main {
	
	private static String XML_NAME_GH = "rmp";
	
	public static void generate(String xmlName) {  
        String config = ClassLoader.getSystemResource("config/db/mybatis/MyBatisCodeGenerator-" + xmlName + ".xml").getFile();  
        String[] arg = { "-configfile", config, "-overwrite" };  
        ShellRunner.main(arg);
    }
	
	public static void main(String[] args) {  
		generate(XML_NAME_GH);
	}
}  