package cn.shin.autotest.util;

/**
 * Interface for RobotUtilImpl
 * 
 * @author Shin Feng
 * @date 2014-11-24
 */
public interface IRobotUtil {
	/**
	 * Upload the specific file.
	 * 
	 * @param fileNameWithExtension
	 *            name of the file with extension
	 * @param path
	 *            path of the file
	 */
	public void uploadFile(String fileNameWithExtension, String path);
}
