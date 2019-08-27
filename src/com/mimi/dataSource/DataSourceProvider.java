package com.mimi.dataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

public class DataSourceProvider {
	private static DataSourceProvider instance;
	private static BasicDataSource datasource;

	public BasicDataSource getDatasource() {
		return datasource;
	}

	public void setDatasource(BasicDataSource datasource) {
		DataSourceProvider.datasource = datasource;
	}
	
	public static DataSourceProvider getInstance(){
		if(instance==null){
			return new DataSourceProvider();
		}else{
			return instance;
		}
	}
}
