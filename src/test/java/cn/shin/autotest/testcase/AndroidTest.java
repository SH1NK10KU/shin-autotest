package cn.shin.autotest.testcase;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class AndroidTest {
	private AndroidDriver driver;

	@Before
	public void setUp() throws Exception {
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "src/test/resources/apk");
		File app = new File(appDir, "app-debug.apk");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "ShinAndroid");

		capabilities.setCapability("platformVersion", "4.1.2");
		capabilities.setCapability("app", app.getAbsolutePath());

		capabilities.setCapability("appPackage", "cn.shin.androidstudiotest");
		capabilities.setCapability("appActivity", ".MainActivity");

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}

	@Test
	public void findHelloWorld() {
		List<WebElement> textList = driver.findElementsByClassName("android.widget.TextView");
		WebElement webElement = textList.get(1);
		assertEquals(webElement.getText(), "Hello World!");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
