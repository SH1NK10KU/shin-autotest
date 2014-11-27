package cn.shin.autotest.util.impl;

import org.apache.log4j.Logger;

import cn.shin.autotest.exception.ElementIsDisplayedException;
import cn.shin.autotest.exception.ElementIsNotDisplayedException;
import cn.shin.autotest.selenium.WebDriverDecorator;
import cn.shin.autotest.util.IElementVerifyUtil;

/**
 * 
 * @author Shin Feng
 * @date 2014-10-30
 * 
 */
public class ElementVerifyUtilImpl implements IElementVerifyUtil {
	private static Logger logger = Logger
			.getLogger(ElementVerifyUtilImpl.class);
	private WebDriverDecorator webDriverDecorator = new WebDriverDecorator();

	/**
	 * Verify the element should be displayed.
	 * 
	 * @param xpath
	 *            xpath of the expected element
	 */
	public void verifyElementIsDisplayedByXpath(String xpath) {
		if (!webDriverDecorator.isElementDisplayedByXpath(xpath)) {
			logger.error("The element should be displayed!");
			throw new ElementIsNotDisplayedException();
		}
	}

	/**
	 * Verify the element should not be displayed.
	 * 
	 * @param xpath
	 *            xpath of the expected element
	 */
	public void verifyElementIsNotDisplayedByXpath(String xpath) {
		if (webDriverDecorator.isElementDisplayedByXpath(xpath)) {
			logger.error("The element should not be displayed!");
			throw new ElementIsDisplayedException();
		}
	}

	/**
	 * Verify the element should be displayed.
	 * 
	 * @param xpath
	 *            xpath of the expected element
	 * @param displayed
	 *            true means that the element should be displayed; otherwise, it
	 *            means that the element should not be displayed.
	 */
	public void verifyElementIsDisplayedByXpath(String xpath, boolean displayed) {
		if (displayed) {
			if (!webDriverDecorator.isElementDisplayedByXpath(xpath)) {
				logger.error("The element should be displayed!");
				throw new ElementIsNotDisplayedException();
			}
		} else {
			if (webDriverDecorator.isElementDisplayedByXpath(xpath)) {
				logger.error("The element should not be displayed!");
				throw new ElementIsDisplayedException();
			}
		}
	}
}