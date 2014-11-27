package cn.shin.autotest.util;

import java.io.IOException;
import java.util.Properties;

/**
 * Interface for PropertyUtilImpl
 * 
 * @author Shin Feng
 * @date 2014-10-27
 */
public interface IPropertyUtil {
	/**
	 * Get the value of the specific property.
	 * 
	 * @param propertyName
	 *            name of the property
	 * @return
	 */
	public String getPropertyValue(String propertyName);

	/**
	 * Load the properties from properties file.
	 * 
	 * @param path
	 *            path of the property file.
	 *            ("/[Folder]/[PropertyName].properties")
	 * @return Properties
	 * @throws IOException
	 */
	public Properties loadPropertiesFromFile(String path);
}
