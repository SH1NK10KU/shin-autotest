package cn.shin.autotest.testcase;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class AndroidTest {
	private AndroidDriver<AndroidElement> driver;
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
		File appDir = new File(classpathRoot, "src/test/resources/apk");
		File app = new File(appDir, "app-debug.apk");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "ShinAndroid");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.1.2");
		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		capabilities.setCapability(MobileCapabilityType.APP_PACKAGE, "cn.shin.androidstudiotest");
		capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, ".MainActivity");

		driver = new AndroidDriver<AndroidElement>(service.getUrl(), capabilities);
	}

	@Test
	public void findHelloWorld() {
		List<AndroidElement> textList = driver.findElementsByClassName("android.widget.TextView");
		WebElement webElement = textList.get(1);
		assertEquals(webElement.getText(), "Hello World!");
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
