package cn.shin.autotest.page;

import java.util.Properties;

import cn.shin.autotest.selenium.WebDriverDecorator;
import cn.shin.autotest.util.impl.PropertyUtilImpl;

/**
 * Operations and elements on the baidu page.
 * 
 * @author Shin Feng
 * @date 2014-11-05
 *
 */
public class BaiduPage extends WebDriverDecorator {
	// The location of the properties file.
	private static final String BAIDU_PAGE_PROPERTIES_FILE = "/pages/BaiduPage.properties";
	// Load the properties file.
	private static final Properties BAIDU_PAGE_PROPERTIES = new PropertyUtilImpl()
			.loadPropertiesFromFile(BAIDU_PAGE_PROPERTIES_FILE);

	// Elements on the page.
	public static final String TEXTFIELD_SEARCH = BAIDU_PAGE_PROPERTIES
			.getProperty("TEXTFIELD_SEARCH");
	public static final String BUTTON_SEARCH = BAIDU_PAGE_PROPERTIES
			.getProperty("BUTTON_SEARCH");

	/**
	 * Input the keyword to search.
	 * 
	 * @param keyword
	 *            keyword to be searched
	 */
	public void search(String keyword) {
		super.findElementAndSendKeysByXpath(TEXTFIELD_SEARCH, keyword);
		super.findElementAndClickByXpath(BUTTON_SEARCH);
	}
}
