package cn.shin.autotest.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Shin Feng
 * @date 2014-11-12
 * 
 */
@Component
public class WebDriverProperty {
	@Value("${firefox.driver.path}")
	private String firefoxDriverPath;
	@Value("${ie.driver.path}")
	private String ieDriverPath;
	@Value("${chrome.driver.path}")
	private String chromeDriverPath;
	@Value("${default.timeout}")
	private int defaultTimeout;

	public String getFirefoxDriverPath() {
		return firefoxDriverPath;
	}

	public String getIeDriverPath() {
		return ieDriverPath;
	}

	public String getChromeDriverPath() {
		return chromeDriverPath;
	}

	public int getDefaultTimeout() {
		return defaultTimeout;
	}
}
