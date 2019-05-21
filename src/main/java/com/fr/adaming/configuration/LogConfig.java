package com.fr.adaming.configuration;

import org.apache.log4j.Logger;

public class LogConfig {
	
	public static Logger logger;

	public static Logger getLogger(Class classe) {
		return Logger.getLogger(classe);
	}
	

	
}
