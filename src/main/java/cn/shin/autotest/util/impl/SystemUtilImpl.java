package cn.shin.autotest.util.impl;

import java.io.IOException;
import java.util.Properties;

import cn.shin.autotest.util.ISystemUtil;

/**
 * Provide basic methods for Robot operations.
 * 
 * @author Shin Feng
 * @date 2014-11-24
 */
public class SystemUtilImpl implements ISystemUtil {
	public enum OS {
		Windows, Mac
	}

	/**
	 * Get the OS name
	 * 
	 * @return
	 */
	public String getOsName() {
		Properties prop = System.getProperties();
		String osName = prop.getProperty("os.name");
		if (osName.contains(OS.Windows.name())) {
			osName = OS.Windows.name();
		} else if (osName.contains(OS.Mac.name())) {
			osName = OS.Mac.name();
		}
		return osName;
	}

	/**
	 * Whether the architecture is 32-bit or 64-bit.
	 * 
	 * @return true shows the architecture is 32-bit; otherwise, the
	 *         architecture is 64-bit.
	 */
	public boolean isArch32bit() {
		Properties prop = System.getProperties();
		String osArch = prop.getProperty("os.arch");

		boolean isArch32bit = false;
		if (osArch.contains("32")) {
			isArch32bit = true;
		} else if (osArch.contains("64")) {
			isArch32bit = false;
		}

		return isArch32bit;
	}

	/**
	 * Kill all the process of the driver.
	 */
	public void killDriverProcess() {
		try {
			if (getOsName().contains("Windows")) {
				Runtime.getRuntime().exec(
						"taskkill /f /im chromedriver_Win32_2.11.exe");
				// Runtime.getRuntime().exec("taskkill /f /im firefox.exe");
				Runtime.getRuntime().exec(
						"taskkill /f /im IEDriverServer_Win32_2.44.0.exe");
				Runtime.getRuntime().exec(
						"taskkill /f /im IEDriverServer_Win64_2.43.0.exe");
				Runtime.getRuntime().exec("taskkill /f /im chrome.exe");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
