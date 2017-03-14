package com.dhcc.spring.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Application Lifecycle Listener implementation class ConfigApplicationContextServletContextListener
 *
 */
@WebListener
public class ConfigApplicationContextServletContextListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ConfigApplicationContextServletContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    	ApplicationContextConfig.setContext( new AnnotationConfigApplicationContext(ApplicationContextConfig.class));
    }
	
}
