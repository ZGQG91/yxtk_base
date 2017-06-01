package com.yidu.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Comm {
	// 系统可变参数通过配置文件配置
	public static Properties properties;
	public static String FILE_PATH="";
	public static String ABSOLUTE_PATH="";
	public static String MODULE_LOG_PATH="";
	public static String MODULE_ZIP_PATH="";
	static {
		properties = new Properties();
		InputStream stream = null;
		try {
			stream = Comm.class.getClassLoader().getResourceAsStream("properties/config.properties");
			properties.load(stream);
			// 默认取测试环境配置
			FILE_PATH = getProperty("file_path", "http://192.168.4.40/");
			ABSOLUTE_PATH = getProperty("absolute_path", "d:/tmp/");
			MODULE_LOG_PATH = getProperty("module_log_path", "d:/tmp/modulelog");
			MODULE_ZIP_PATH = getProperty("module_zip_path", "d:/tmp/modulepath");
		} catch (Exception e) {

		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static String getProperty(String key, String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}

	public static String getProperty(String key) {
		return getProperty(key, null);
	}

}
