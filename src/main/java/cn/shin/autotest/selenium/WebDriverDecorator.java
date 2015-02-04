package cn.shin.autotest.selenium;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Provide basic methods for web driver operations.
 * 
 * @author Shin Feng
 * @date 2014-10-24
 */
public class WebDriverDecorator implements WebDriver {
	private static Logger LOG = Logger.getLogger(WebDriverDecorator.class);
	private static EventFiringWebDriver driver;
	private static WebDriverEventListenerImpl eventListener = new WebDriverEventListenerImpl();
	private WebDriverFactory webDriverFactory = new WebDriverFactory();

	/**
	 * Default constructor
	 */
	public WebDriverDecorator() {
	}

	/**
	 * Constructor with the specific type of the driver.
	 * 
	 * @param driverType
	 *            type of the driver
	 */
	public WebDriverDecorator(String driverType) {
		driver = new EventFiringWebDriver(
				webDriverFactory.setDriver(driverType));
		driver.register(eventListener);
		this.maximizeWindow();
	}

	/**
	 * Maximize the window of the browser.
	 */
	public void maximizeWindow() {
		driver.manage().window().maximize();
	}

	public static WebDriver getDriver() {
		return driver;
	}

	/**
	 * Navigate to the specific URL.
	 * 
	 * @param url
	 */
	public void get(String url) {
		driver.get(url);
	}

	public WebElement findElement(By by) {
		waitForElementDisplayed(by, webDriverFactory.getDefaultTimeout());
		return driver.findElement(by);
	}

	/**
	 * Find the element via XPath.
	 * 
	 * @param xpath
	 *            XPath of the element
	 * @return WebElement
	 */
	public WebElement findElementByXpath(String xpath) {
		By by = By.xpath(xpath);
		waitForElementDisplayed(by, webDriverFactory.getDefaultTimeout());
		return driver.findElement(by);
	}

	/**
	 * Click the element via XPath.
	 * 
	 * @param xpath
	 *            XPath of the element
	 */
	public void findElementAndClickByXpath(String xpath) {
		By by = By.xpath(xpath);
		waitForElementDisplayed(by, webDriverFactory.getDefaultTimeout())
				.click();
		wait(1);
	}

	/**
	 * Type the text into the element via XPath.
	 * 
	 * @param xpath
	 *            XPath of the element
	 * @param text
	 *            text to type
	 */
	public void findElementAndSendKeysByXpath(String xpath, String text) {
		By by = By.xpath(xpath);
		waitForElementDisplayed(by, webDriverFactory.getDefaultTimeout())
				.sendKeys(text);
		wait(1);
	}

	/**
	 * Clear the text in the element via XPath.
	 * 
	 * @param xpath
	 *            xpath of the element
	 * @param text
	 *            text to type
	 */
	public void findElementAndClearByXpath(String xpath, String text) {
		By by = By.xpath(xpath);
		waitForElementDisplayed(by, webDriverFactory.getDefaultTimeout())
				.clear();
		wait(1);
	}

	public List<WebElement> findElements(By by) {
		return driver.findElements(by);
	}

	public List<WebElement> findElementsByXpath(String xpath) {
		By by = By.xpath(xpath);
		return driver.findElements(by);
	}

	/**
	 * Click the element with the specific index.
	 * 
	 * @param xpath
	 *            XPath of the element
	 * @param index
	 *            index of the element (from 0)
	 */
	public void findElementsAndClickByXpath(String xpath, int index) {
		By by = By.xpath(xpath);
		driver.findElements(by).get(index).click();
		wait(1);
	}

	/**
	 * Wait for the element displayed in specific duration via locator.
	 * 
	 * @param by
	 *            locator of the element
	 * @param second
	 *            duration for waiting
	 * @return WebElement
	 */
	public WebElement waitForElementDisplayed(By by, int second) {
		WebDriverWait wait = new WebDriverWait(driver, second);
		WebElement element = wait.until(ExpectedConditions
				.visibilityOfElementLocated(by));
		return element;
	}

	/**
	 * Wait for the element displayed in specific duration via XPath.
	 * 
	 * @param xpath
	 *            XPath of the element
	 * @param second
	 *            duration for waiting
	 * @return WebElement
	 */
	public WebElement waitForElementDisplayedByXpath(String xpath, int second) {
		WebDriverWait wait = new WebDriverWait(driver, second);
		WebElement element = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(xpath)));
		return element;
	}

	/**
	 * Is the element displayed or not via XPath.
	 * 
	 * @param xpath
	 *            XPath of the element
	 * @return boolean
	 */
	public boolean isElementDisplayedByXpath(String xpath) {
		By by = By.xpath(xpath);
		try {
			waitForElementDisplayed(by, webDriverFactory.getDefaultTimeout())
					.isDisplayed();
			LOG.info("The element, " + by + " is displayed.");
			return true;
		} catch (Exception e) {
			LOG.info("The element, " + by + " is not displayed!");
			return false;
		}
	}

	/**
	 * Switch to the default frame.
	 */
	public void switchToDefaultFrame() {
		driver.switchTo().defaultContent();
	}

	/**
	 * Switch the frame via XPath.
	 * 
	 * @param xpath
	 *            XPath of the element
	 */
	public void switchToFrame(String xpath) {
		By by = By.xpath(xpath);
		LOG.info("Switch to the frame: " + by);
		driver.switchTo().frame(waitForElementDisplayed(by, 10));
	}

	@Override
	public TargetLocator switchTo() {
		return driver.switchTo();
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public String getPageSource() {
		return driver.getPageSource();
	}

	public void close() {
		driver.close();
	}

	public void quit() {
		driver.quit();
	}

	/**
	 * Confirm the alert.
	 */
	public void confirmAlert() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	/**
	 * Cancel the alert.
	 */
	public void cancelAlert() {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	/**
	 * Prompt the alert.
	 */
	public void promptAlert(String content) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(content);
		alert.accept();
	}

	public void back() {
		driver.navigate().back();
	}

	public void forward() {
		driver.navigate().forward();
	}

	public void refresh() {
		driver.navigate().refresh();
	}

	@Override
	public Navigation navigate() {
		return driver.navigate();
	}

	/**
	 * Select the elements according to the XPaths.
	 * 
	 * @param xpaths
	 */
	public void selectElementsByXpath(String[] xpaths) {
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL);
		for (String xpath : xpaths) {
			actions.click(findElementByXpath(xpath));
		}
		actions.keyUp(Keys.CONTROL);
		actions.perform();
	}

	/**
	 * Move mouse to the specific element via XPath.
	 * 
	 * @param xpath
	 *            XPath of the element
	 */
	public void moveToElementByXpath(String xpath) {
		Actions actions = new Actions(driver);
		actions.moveToElement(findElementByXpath(xpath)).build().perform();
	}

	/**
	 * Move mouse to the element by offset.
	 * 
	 * @param xpath
	 *            XPath of the element
	 * @param xOffset
	 * @param yOffset
	 */
	public void moveByOffsetByXpath(String xpath, int xOffset, int yOffset) {
		Point point = getLocationByXpath(xpath);
		Actions actions = new Actions(driver);
		actions.moveByOffset(point.getX() + xOffset, point.getY() + yOffset)
				.build().perform();
	}

	/**
	 * Move mouse to the element by offset and click.
	 * 
	 * @param xpath
	 *            XPath of the element
	 * @param xOffset
	 * @param yOffset
	 */
	public void moveByOffsetAndClickByXpath(String xpath, int xOffset,
			int yOffset) {
		Point point = getLocationByXpath(xpath);
		Actions actions = new Actions(driver);
		actions.moveByOffset(point.getX() + xOffset, point.getY() + yOffset)
				.click().build().perform();
	}

	/**
	 * Drag the element to another element and drop.
	 * 
	 * @param srcXpath
	 *            XPath of the dragged element
	 * @param destXpath
	 */
	public void dragAndDropToElementByXpath(String srcXpath, String destXpath) {
		Point srcPoint = getLocationByXpath(srcXpath);
		int srcPointX = srcPoint.getX();
		int srcPointY = srcPoint.getY();
		Actions actions = new Actions(driver);
		actions.moveByOffset(srcPointX, srcPointY)
				.clickAndHold()
				.moveByOffset(srcPointX + getLocationByXpath(destXpath).getX(),
						srcPointY + getLocationByXpath(destXpath).getY())
				.release().perform();
	}

	/**
	 * Drag the element by offset and drop.
	 * 
	 * @param point
	 *            location of the element
	 * @param xOffset
	 * @param yOffset
	 */
	public void dragAndDropByOffsetByXpath(String xpath, int xOffset,
			int yOffset) {
		Point point = getLocationByXpath(xpath);
		int pointX = point.getX();
		int pointY = point.getY();
		Actions actions = new Actions(driver);
		actions.moveByOffset(pointX, pointY).clickAndHold()
				.moveByOffset(pointX + xOffset, pointY + yOffset).release()
				.perform();
	}

	/**
	 * Double click the mouse via XPath.
	 * 
	 * @param xpath
	 *            XPath of the element
	 */
	public void doubleClickByXpath(String xpath) {
		Actions actions = new Actions(driver);
		actions.doubleClick(findElementByXpath(xpath)).build().perform();
	}

	/**
	 * Get the location of the element
	 * 
	 * @return
	 */
	public Point getLocationByXpath(String xpath) {
		return findElementByXpath(xpath).getLocation();
	}

	/**
	 * Scroll the page to the specific element via XPath.
	 * 
	 * @param xpath
	 *            XPath of the element
	 */
	public void scrollToElement(String xpath) {
		WebElement target = driver.findElement(By.xpath(xpath));
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView();", target);
	}

	public Set<String> getWindowHandles() {
		return driver.getWindowHandles();
	}

	public String getWindowHandle() {
		return driver.getWindowHandle();
	}

	@Override
	public Options manage() {
		return driver.manage();
	}

	/**
	 * Capture the screen as PNG file.
	 */
	public void captureScreen() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = formatter.format(new Date());
		String fileName = date;
		File srcFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		File destFile = new File("test-classes/Screenshots" + File.separator
				+ fileName + ".png");
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Wait for the element to display after the specific second.
	 * 
	 * @param second
	 *            the duration for waiting
	 */
	public void wait(int second) {
		try {
			Thread.sleep((long) second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
