package cn.shin.autotest.util;

/**
 * Interface for FileUtilImpl.
 * 
 * @author Shin Feng
 * @date 2014-11-06
 *
 */
public interface IFileUtil {
	/**
	 * Create the folder with the specific name in the specific path.
	 * 
	 * @param folderName
	 *            name of the folder
	 * @param path
	 *            path of the folder
	 */
	public void createFolder(String folderName, String path);

	/**
	 * Create the file with the specific name in the specific path.
	 * 
	 * @param fileNameWithExtension
	 *            name of the file with extension
	 * @param path
	 *            path of the file
	 */
	public void createFile(String fileNameWithExtension, String path);

	/**
	 * Delete the file with the specific name in the specific path.
	 * 
	 * @param fileNameWithExtension
	 *            name fothe file with extension
	 * @param path
	 *            path of the file
	 */
	public void deleteFile(String fileNameWithExtension, String path);

	/**
	 * Delete the folder with the specific name in the specific path.
	 * 
	 * @param folderName
	 *            name of the folder
	 * @param path
	 *            path of the folder
	 */
	public void deleteFolder(String folderName, String path);
}
