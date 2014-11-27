package cn.shin.autotest.util.impl;

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
}
