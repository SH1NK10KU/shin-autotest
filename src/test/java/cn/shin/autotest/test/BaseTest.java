package cn.shin.autotest.test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import cn.shin.autotest.selenium.WebDriverDecorator;
import cn.shin.autotest.testng.ExcelDataProvider;
import cn.shin.autotest.util.ISystemUtil;
import cn.shin.autotest.util.impl.SystemUtilImpl;

/**
 * Provide the basic test template.
 * 
 * @author Shin Feng
 * @date 2014-10-31
 *
 */
public class BaseTest {
	private static Logger logger = Logger.getLogger(BaseTest.class);
	private ISystemUtil systemUtil = new SystemUtilImpl();
	protected WebDriverDecorator driver;

	@BeforeTest(groups = "WebUITest")
	public void beforeTest() {
		systemUtil.killDriverProcess();
	}

	@BeforeMethod
	public void beforeMethod(Method method) {

	}

	@AfterMethod
	public void afterMethod(Method method) {

	}

	@AfterTest(groups = "WebUITest")
	public void afterTest() {
		if (driver != null) {
			driver.quit();
		}
	}

	@DataProvider(name = "TestDataProvider")
	public Iterator<Object[]> prepareTestData(Method method) throws IOException {
		return new ExcelDataProvider(this.getClass().getName(),
				method.getName());
	}
}
