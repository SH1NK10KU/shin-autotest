package cn.shin.autotest.util;

/**
 * Interface for SystemUtilImpl
 * 
 * @author Shin Feng
 * @date 2014-11-24
 */
public interface ISystemUtil {
	/**
	 * Get the OS name
	 * 
	 * @return
	 */
	public String getOsName();

	/**
	 * Whether the architecture is 32-bit or 64-bit.
	 * 
	 * @return true shows the architecture is 32-bit; otherwise, the
	 *         architecture is 64-bit.
	 */
	public boolean isArch32bit();

	/**
	 * Kill all the process of the driver.
	 */
	public void killDriverProcess();
}
