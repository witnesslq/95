package com.dhcc.spring.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.dhcc.PackageInfo;

@Configuration
@ImportResource("classpath:spring-hibernate.xml")
@ComponentScan(basePackageClasses=PackageInfo.class)
public class ApplicationContextConfig {
	private static ApplicationContext context;

	/**
	 * @return the context
	 */
	public static final ApplicationContext getContext() {
		return context;
	}

	/**
	 * @param context the context to set
	 */
	 static final void setContext(ApplicationContext context) {
		ApplicationContextConfig.context = context;
	} 
	
	
}
