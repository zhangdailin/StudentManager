package com.mimi.dataSource;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

/**
 * Application Lifecycle Listener implementation class BasicDataSourceListener
 *
 */
public class BasicDataSourceListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public BasicDataSourceListener() {
        // TODO Auto-generated constructor stub
    	super();
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    	BasicDataSource datasource=DataSourceProvider.getInstance().getDatasource();
    	try {
			datasource.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    	try {
    		System.out.println("进入listener!");
			Context ctx=new InitialContext();
			Context envContext=(Context) ctx.lookup(DataSourceConfig.INITIAL_CONTEXT);
			BasicDataSource ds=(BasicDataSource) envContext.lookup(DataSourceConfig.ENV_CONTEXT);
			if(ds!=null){
				System.out.println("ds is created!");
			}else{
				System.out.println("ds is error");
			}
			DataSourceProvider provider=DataSourceProvider.getInstance();
			provider.setDatasource(ds);
    	} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
	
}
