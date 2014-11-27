package cn.shin.autotest.testng;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import cn.shin.autotest.selenium.WebDriverDecorator;

/**
 * Customized test result listener for TestNG to retry.
 * 
 * @author Shin Feng
 * @date 2014-11-04
 *
 */
public class TestResultListener extends TestListenerAdapter {
	private static Logger logger = Logger.getLogger(TestResultListener.class);

	@Override
	public void onTestFailure(ITestResult tr) {
		super.onTestFailure(tr);
		logger.info("[" + tr.getName() + "] is failure.\n");
		Reporter.log("[" + tr.getName() + "] is failure.\n");
		captureScreen(tr);
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		super.onTestSkipped(tr);
		logger.info("[" + tr.getName() + "] is skipped.\n");
		Reporter.log("[" + tr.getName() + "] is skipped.\n");
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		super.onTestSuccess(tr);
		logger.info("[" + tr.getName() + "] is success.\n");
		Reporter.log("[" + tr.getName() + "] is success.\n");
	}

	@Override
	public void onTestStart(ITestResult tr) {
		super.onTestStart(tr);
		logger.info("[" + tr.getName() + "] start");
		Reporter.log("[" + tr.getName() + "] start");
	}

	@Override
	public void onFinish(ITestContext testContext) {
		super.onFinish(testContext);

		// List of test results which we will delete later
		ArrayList<ITestResult> testsToBeRemoved = new ArrayList<ITestResult>();
		// collect all id's from passed test
		Set<Integer> passedTestIds = new HashSet<Integer>();
		for (ITestResult passedTest : testContext.getPassedTests()
				.getAllResults()) {
			passedTestIds.add(getTestHashCode(passedTest));
		}

		// Eliminate the repeat methods
		Set<Integer> skipTestIds = new HashSet<Integer>();
		for (ITestResult skipTest : testContext.getSkippedTests()
				.getAllResults()) {
			// id = class + method + dataprovider
			int skipTestId = getTestHashCode(skipTest);

			if (skipTestIds.contains(skipTestId)
					|| passedTestIds.contains(skipTestId)) {
				testsToBeRemoved.add(skipTest);
			} else {
				skipTestIds.add(skipTestId);
			}
		}

		// Eliminate the repeat failed methods
		Set<Integer> failedTestIds = new HashSet<Integer>();
		for (ITestResult failedTest : testContext.getFailedTests()
				.getAllResults()) {
			// id = class + method + dataprovider
			int failedTestId = getTestHashCode(failedTest);

			// if we saw this test as a failed test before we mark as to be
			// deleted
			// or delete this failed test if there is at least one passed
			// version
			if (failedTestIds.contains(failedTestId)
					|| passedTestIds.contains(failedTestId)
					|| skipTestIds.contains(failedTestId)) {
				testsToBeRemoved.add(failedTest);
			} else {
				failedTestIds.add(failedTestId);
			}
		}

		// finally delete all tests that are marked
		for (Iterator<ITestResult> iterator = testContext.getFailedTests()
				.getAllResults().iterator(); iterator.hasNext();) {
			ITestResult testResult = iterator.next();
			if (testsToBeRemoved.contains(testResult)) {
				iterator.remove();
			}
		}
	}

	/**
	 * Generate hashcode for test case.
	 * 
	 * @param result
	 * @return
	 */
	private int getTestHashCode(ITestResult result) {
		return result.getTestClass().getName().hashCode()
				+ result.getMethod().getMethodName().hashCode()
				+ (result.getParameters() != null ? Arrays.hashCode(result
						.getParameters()) : 0);
	}

	/**
	 * Capture the screen as PNG file.
	 * 
	 * @param tr
	 */
	public void captureScreen(ITestResult tr) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			String date = formatter.format(new Date());
			String fileName = "[" + date + "][" + tr.getTestClass().getName()
					+ "." + tr.getName() + "]";

			if (WebDriverDecorator.getDriver() != null) {
				File srcFile = ((TakesScreenshot) WebDriverDecorator
						.getDriver()).getScreenshotAs(OutputType.FILE);
				File destFile = new File("test-classes/screenshots"
						+ File.separator + fileName + ".png");
				Reporter.setCurrentTestResult(tr);
				Reporter.log(destFile.getName());
				Reporter.log("<img src=\"" + destFile.getAbsolutePath()
						+ "\" width=\"640\" height=\"360\" />", false);
				FileUtils.copyFile(srcFile, destFile);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
