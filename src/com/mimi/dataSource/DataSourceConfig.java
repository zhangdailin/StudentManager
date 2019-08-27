package com.mimi.dataSource;

import java.io.IOException;
import java.util.Properties;

public class DataSourceConfig {
	private static Properties prop=new Properties();
	static {
		try {
			prop.load(DataSourceConfig.class.getResourceAsStream("datasource.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static final String INITIAL_CONTEXT=prop.getProperty("initialContext");
	public static final String ENV_CONTEXT=prop.getProperty("envContext");
	
	public static void main(String[]args){
		System.out.println(DataSourceConfig.INITIAL_CONTEXT);
		System.out.println(DataSourceConfig.ENV_CONTEXT);
	}
}
