package cn.shin.autotest.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.shin.autotest.property.WebDriverProperty;

/**
 * Provide the factory method for web driver.
 * 
 * @author Shin Feng
 * @date 2014-10-24
 */
public class WebDriverFactory {
	private static WebDriver driver;

	private ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
			"classpath*:/applicationContext.xml");
	private WebDriverProperty webDriverProperty = applicationContext
			.getBean(WebDriverProperty.class);

	public enum Browser {
		firefox, ie, chrome
	}

	/**
	 * Default constructor
	 */
	public WebDriverFactory() {
	}

	/**
	 * Constructor with the specific type of the driver.
	 * 
	 * @param driverType
	 *            type of the driver
	 * @return
	 */
	public WebDriver setDriver(String driverType) {
		if (driverType.equals(Browser.firefox.name())) {
			driver = setFirefoxDriver();
		} else if (driverType.equals(Browser.ie.name())) {
			driver = setInternetExplorerDriver();
		} else if (driverType.equals(Browser.chrome.name())) {
			driver = setChromeDriver();
		}
		driver.manage()
				.timeouts()
				.implicitlyWait(webDriverProperty.getDefaultTimeout(),
						TimeUnit.SECONDS);
		return driver;
	}

	/**
	 * Set the driver as Firefox.
	 * 
	 * @return WebDriver
	 */
	public WebDriver setFirefoxDriver() {
		System.setProperty("webdriver.firefox.bin",
				webDriverProperty.getFirefoxDriverPath());
		driver = new FirefoxDriver();
		return driver;
	}

	/**
	 * Set the driver as Internet Explorer.
	 * 
	 * @return WebDriver
	 */
	public WebDriver setInternetExplorerDriver() {
		System.setProperty("webdriver.ie.driver",
				getClass().getResource(webDriverProperty.getIeDriverPath())
						.getPath());
		DesiredCapabilities ieCapability = DesiredCapabilities
				.internetExplorer();
		ieCapability
				.setCapability(
						InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
		driver = new InternetExplorerDriver(ieCapability);
		return driver;
	}

	/**
	 * Set the driver as Chrome.
	 * 
	 * @return WebDriver
	 */
	public WebDriver setChromeDriver() {
		System.setProperty("webdriver.chrome.driver",
				getClass().getResource(webDriverProperty.getChromeDriverPath())
						.getPath());
		ChromeOptions options = new ChromeOptions();
		options.addArguments("test-type");
		DesiredCapabilities chromeCapability = new DesiredCapabilities();
		chromeCapability.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver();
		return driver;
	}

	/**
	 * Get the default time for timeout.
	 * 
	 * @return int
	 */
	public int getDefaultTimeout() {
		return webDriverProperty.getDefaultTimeout();
	}
}