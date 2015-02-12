package cn.shin.autotest.util.impl;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import cn.shin.autotest.util.IFileUtil;

/**
 * Provide basic methods for file operations.
 * 
 * @author Shin Feng
 * @date 2014-11-06
 */
public class FileUtilImpl implements IFileUtil {
	private static final Logger LOG = Logger.getLogger(FileUtilImpl.class);

	/**
	 * Create the folder with the specific name in the specific path.
	 * 
	 * @param folderName
	 *            name of the folder
	 * @param path
	 *            path of the folder
	 */
	public void createFolder(String folderName, String path) {
		String formatPath = formatPath(path);
		File file = new File(formatPath + folderName);
		if (!file.exists()) {
			file.mkdirs();
			LOG.info("Create the folder named \"" + folderName
					+ "\" in the path (\"" + formatPath + "\") successfully.");
		} else {
			LOG.error("Fail to create the folder named \"" + folderName
					+ "\" in the path (\"" + formatPath + "\")!");
		}
	}

	/**
	 * Create the file with the specific name in the specific path.
	 * 
	 * @param fileNameWithExtension
	 *            name of the file with extension
	 * @param path
	 *            path of the file
	 */
	public void createFile(String fileNameWithExtension, String path) {
		String formatPath = formatPath(path);
		File file = new File(formatPath + fileNameWithExtension);
		try {
			file.createNewFile();
			LOG.info("Create the file named \"" + fileNameWithExtension
					+ "\" in the path (\"" + formatPath + "\") successfully.");
		} catch (IOException e) {
			LOG.error("Fail to create the file named \""
					+ fileNameWithExtension + "\" in the path (\"" + formatPath
					+ "\")!");
		}
	}

	/**
	 * Delete the file with the specific name in the specific path.
	 * 
	 * @param fileNameWithExtension
	 *            name fothe file with extension
	 * @param path
	 *            path of the file
	 */
	public void deleteFile(String fileNameWithExtension, String path) {
		String formatPath = formatPath(path);
		File file = new File(formatPath + fileNameWithExtension);
		if (file.isFile() && file.exists()) {
			file.delete();
			LOG.info("Delete the file named \"" + fileNameWithExtension
					+ "\" in the path (\"" + formatPath + "\") successfully.");
		} else {
			LOG.error("There is no such file named \""
					+ fileNameWithExtension + "\" in the path (\"" + formatPath
					+ "\")!");
		}
	}

	/**
	 * Delete the folder with the specific name in the specific path.
	 * 
	 * @param folderName
	 *            name of the folder
	 * @param path
	 *            path of the folder
	 */
	public void deleteFolder(String folderName, String path) {
		String formatPath = formatPath(path);
		File file = new File(formatPath + folderName);
		if (file.isDirectory() && file.exists()) {
			File[] filesArray = file.listFiles();

			for (int i = 0; i < filesArray.length; i++) {
				if (filesArray[i].isDirectory()) {
					deleteFolder(filesArray[i].getName(),
							filesArray[i].getParent());
				} else {
					deleteFile(filesArray[i].getName(),
							filesArray[i].getParent());
				}
			}
			file.delete();
			LOG.info("Delete the folder named \"" + folderName
					+ "\" in the path (\"" + formatPath + "\") successfully.");
		} else {
			LOG.error("There is no such folder named \"" + folderName
					+ "\" in the path (\"" + formatPath + "\")!");
		}
	}

	/**
	 * Format the path.
	 * 
	 * @param path
	 * @return String
	 */
	private String formatPath(String path) {
		if (!path.endsWith(File.separator)) {
			path += File.separator;
		}
		return path;
	}
}
