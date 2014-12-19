package com.charmyin.cmstudio.basic.initial;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

/**
 * 
 * @author charmyin
 * @since 2013-7-8
 *
 */
public class ContextLoadedInitialListener implements ServletContextListener {
	Logger logger = Logger.getLogger(ContextLoadedInitialListener.class);
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//Get the web context(application scope) properties
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		logger.info("------------Starting loading web context properties!--------"); 
		InputStream is = cl.getResourceAsStream("servletcontext.properties");
		Properties webContextProperties = new Properties();
		try{
			webContextProperties.load(is);
		}catch(IOException ioe){
			ioe.printStackTrace();
			logger.error(ioe.getMessage());
			return;
		}finally{
			try{
				is.close();
			}catch(IOException ioe1){
				ioe1.printStackTrace();
				logger.error(ioe1.getMessage());
			}
		}
		Enumeration e = webContextProperties.propertyNames();
	    while (e.hasMoreElements()) {
	      String key = (String) e.nextElement();
	      sce.getServletContext().setAttribute(key, webContextProperties.getProperty(key));
	      logger.info("Loading web context properties : " + key + " -- " + webContextProperties.getProperty(key));
	    }
	  
	    
	    logger.info("------------ Loading web context properties ended!--------"); 
	    logger.warn("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+sce.getServletContext().getInitParameter("contextConfigLocation"));
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
