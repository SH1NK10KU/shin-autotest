package cn.shin.autotest.util;

/**
 * Interface for ElementVerifyUtilImpl.
 * 
 * @author Shin Feng
 * @date 2014-10-30
 * 
 */
public interface IElementVerifyUtil {

	/**
	 * Verify the element should be displayed.
	 * 
	 * @param xpath
	 *            xpath of the expected element
	 */
	public void verifyElementIsDisplayedByXpath(String xpath);

	/**
	 * Verify the element should not be displayed.
	 * 
	 * @param xpath
	 *            xpath of the expected element
	 */
	public void verifyElementIsNotDisplayedByXpath(String xpath);

	/**
	 * Verify the element should be displayed.
	 * 
	 * @param xpath
	 *            xpath of the element
	 * @param displayed
	 *            true: it means that the element should be displayed. false: it
	 *            means that the element should not be displayed.
	 */
	public void verifyElementIsDisplayedByXpath(String xpath, boolean displayed);
}
