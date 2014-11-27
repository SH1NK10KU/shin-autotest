package cn.shin.autotest.testng;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;

import cn.shin.autotest.property.TestngProperty;

/**
 * Analyzer for TestNG to retry.
 * 
 * @author Shin Feng
 * @date 2014-11-04
 *
 */
public class RetryAnalyzerImpl implements IRetryAnalyzer {
	private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
			"classpath*:/applicationContext.xml");
	private static TestngProperty testngProperty = applicationContext
			.getBean(TestngProperty.class);
	public static final Logger logger = Logger
			.getLogger(RetryAnalyzerImpl.class);

	private int retrycount = 1;

	static {
		logger.info("Retry time for TestNG is "
				+ testngProperty.getRetryTimes()
				+ " while the test is failure.");
	}

	@Override
	public boolean retry(ITestResult result) {
		if (retrycount <= testngProperty.getRetryTimes()) {
			String message = "Retry for [" + result.getName() + "]: "
					+ retrycount;
			logger.info(message);
			Reporter.setCurrentTestResult(result);
			Reporter.log("RunCount=" + (retrycount + 1));
			retrycount++;
			return true;
		}
		return false;
	}
}
