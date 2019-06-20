package com.test.BBBY_Rest_api.utils.resource;

public class ResourceHelper {
	
	public static String getResourcePath(String path) {
		
		String basePath = System.getProperty("user.dir");
		return basePath + path;
	}
}
