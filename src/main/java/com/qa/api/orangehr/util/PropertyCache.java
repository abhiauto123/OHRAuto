package com.qa.api.orangehr.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class PropertyCache {
	
	public static FileInputStream loadPropertiesPathInput(String fileName) throws FileNotFoundException {
		StringBuilder filenameElementProperties = new StringBuilder();
		filenameElementProperties.append("src").append(File.separator);
		filenameElementProperties.append("test").append(File.separator);
		filenameElementProperties.append("resources").append(File.separator);
		filenameElementProperties.append(fileName);
		return new FileInputStream(
				System.getProperty("user.dir") + File.separator + filenameElementProperties.toString());
		 
	 }
	
	public static OutputStream loadPropertiesPathOutput(String fileName) throws FileNotFoundException {
		StringBuilder filenameElementProperties = new StringBuilder();
		filenameElementProperties.append("src").append(File.separator);
		filenameElementProperties.append("test").append(File.separator);
		filenameElementProperties.append("resources").append(File.separator);
		filenameElementProperties.append(fileName);
		return new FileOutputStream (
				System.getProperty("user.dir") + File.separator + filenameElementProperties.toString());
		 
	 }


	
	public static Properties propertyFileReader(String fileName){
		Properties prop = new Properties();
		
			try {
				prop.load(loadPropertiesPathInput(fileName));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		return prop;
	}
	
	
	public static String getProperty(String fileName,String key){
	    return propertyFileReader(fileName).getProperty(key);
	}
	
	public static void setProperty(String fileName,String key,String value){
		Properties prop = propertyFileReader(fileName);
		prop.setProperty(key, value);
		
		try {
			prop.store(loadPropertiesPathOutput(fileName), "");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
