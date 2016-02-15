package com.connectedio.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;


public class ReadFile {
	public static String pValue;
	public static String ConfigPathLocation="./config.properties";
	public static String readFile(String File,String pName) {
		try {
			Properties pro = new Properties();
			FileInputStream in = new FileInputStream(File);
			pro.load(in);
			// getting values from property file
			 pValue = pro.getProperty(pName);
			 System.out.println("Value is:" + pValue );
			 return pValue;
		
		} catch (IOException e) {
			System.out.println("Error is:" + e.getMessage());
			e.printStackTrace();
		}
		return pValue;

	}
	
	 public static String getConfigPropertyVal(final String key) {
		    String ConfigPropertyVal = readFile(ConfigPathLocation,key);
			return ConfigPropertyVal != null ? ConfigPropertyVal.trim() : ConfigPropertyVal;
		}
	
}

