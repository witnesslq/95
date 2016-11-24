package com.dhcc.common.util;
import java.util.*;

import org.apache.log4j.Logger;


/**
 * 读取配置文件的类
 *
 */
public class SystemConfig {
	private static final Logger logger = Logger.getLogger(SystemConfig.class);
	private static String flag="";
    static String configFile = "SystemConfigResources";
    public static String getConfigInfomation(String itemIndex) {
        try {
            ResourceBundle resource = ResourceBundle.getBundle(flag+configFile);
            return resource.getString(itemIndex);
        } catch (Exception e) {
            return "";
        }
    }
    
    public static String getConfigInfomation(String finename,String itemIndex) {
        try {
            ResourceBundle resource = ResourceBundle.getBundle(finename);
            return resource.getString(itemIndex);
        } catch (Exception e) {
            return "";
        }
    }
    
    
    
    
    
	public static String getFlag() {
		return flag;
	}

	public static void setFlag(String flag) {
		SystemConfig.flag = flag;
	}

	public static void main(String arg[])
    {
    	logger.info(SystemConfig.getConfigInfomation("DATABASE_PASSWORD"));
    }
}


