package cn.shin.autotest.test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import cn.shin.autotest.selenium.WebDriverDecorator;
import cn.shin.autotest.testng.ExcelDataProvider;

/**
 * Provide the basic test template.
 * 
 * @author Shin Feng
 * @date 2014-10-31
 *
 */
public class BaseTest {
	private static Logger logger = Logger.getLogger(BaseTest.class);
	protected WebDriverDecorator driver;
	
	@BeforeTest(groups = "WebUITest")
	public void beforeTest() {
		this.killDriverProcess();
	}

	@BeforeMethod
	public void beforeMethod(Method method) {

	}

	private void killDriverProcess() {
		try {
			Properties properties = System.getProperties();
			String os = properties.getProperty("os.name");

			if (os.contains("Windows")) {
				logger.info("Prepare the driver environment.");
				Runtime.getRuntime().exec(
						"taskkill /f /im chromedriver_Win32_2.11.exe");
				// Runtime.getRuntime().exec("taskkill /f /im firefox.exe");
				Runtime.getRuntime().exec(
						"taskkill /f /im IEDriverServer_Win32_2.44.0.exe");
				Runtime.getRuntime().exec(
						"taskkill /f /im IEDriverServer_Win64_2.43.0.exe");
				Runtime.getRuntime().exec("taskkill /f /im chrome.exe");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void afterMethod(Method method) {

	}
	

	@AfterTest(groups = "WebUITest")
	public void AfterTest() {
		if (driver != null) {
			driver.quit();
		}
	}

	@DataProvider(name = "SampleDataProvider")
	public Iterator<Object[]> prepareTestData(Method method) throws IOException {
		return new ExcelDataProvider(this.getClass().getName(),
				method.getName());
	}
}
