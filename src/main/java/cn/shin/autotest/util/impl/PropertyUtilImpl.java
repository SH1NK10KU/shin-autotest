package cn.shin.autotest.util.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import cn.shin.autotest.util.IPropertyUtil;

/**
 * Provide basic methods for properties operations.
 * 
 * @author Shin Feng
 * @date 2014-10-24
 */
public class PropertyUtilImpl implements IPropertyUtil {
	private Properties properties = new Properties();

	public PropertyUtilImpl() {
	}

	/**
	 * Get the value of the specific property.
	 * 
	 * @param propertyName
	 *            name of the property
	 * @return
	 */
	public String getPropertyValue(String propertyName) {
		return (String) properties.get(propertyName);
	}

	/**
	 * Load the properties from properties file.
	 * 
	 * @param path
	 *            path of the property file.
	 *            ("/${Folder}/${PropertyName}.properties")
	 * @return Properties
	 * @throws IOException
	 */
	public Properties loadPropertiesFromFile(String path) {

		try {
			InputStream fileInputStream = PropertyUtilImpl.class
					.getResourceAsStream(path);
			properties.load(fileInputStream);
			fileInputStream.close();
		} catch (IOException e) {
			System.out.println("No such properties file.");
			e.printStackTrace();
		}
		return properties;
	}
}