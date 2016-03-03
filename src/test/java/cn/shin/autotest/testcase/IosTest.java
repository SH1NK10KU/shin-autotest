package cn.shin.autotest.testcase;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class IosTest {
	private IOSDriver<WebElement> driver;
	private static AppiumDriverLocalService service;

	@BeforeClass
	public static void beforeClass() throws Exception {
		service = AppiumDriverLocalService.buildDefaultService();
		service.start();
	}

	@Before
	public void setUp() throws Exception {
		if (service == null || !service.isRunning())
			throw new RuntimeException("An appium server node is not started!");

		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "src/test/resources/app");
		File app = new File(appDir, "HelloWorld.app");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.4");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone Simulator");
		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		driver = new IOSDriver<WebElement>(service.getUrl(), capabilities);
	}

	@Test
	public void findHelloWorld() {
		WebElement webElement = driver.findElement(By.xpath("//UIATextField[1]"));
		webElement.clear();
		webElement.sendKeys("ShinText");
		assertEquals(webElement.getText(), "ShinText");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@AfterClass
	public static void afterClass() {
		if (service != null)
			service.stop();
	}
}
