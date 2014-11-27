package cn.shin.autotest.util.impl;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import cn.shin.autotest.util.IRobotUtil;
import cn.shin.autotest.util.ISystemUtil;
import cn.shin.autotest.util.impl.SystemUtilImpl.OS;

/**
 * Provide basic methods for Robot operations.
 * 
 * @author Shin Feng
 * @date 2014-11-24
 */
public class RobotUtilImpl implements IRobotUtil {
	private static final int AUTO_DELAY = 5;
	private ISystemUtil systemUtil = new SystemUtilImpl();
	private Robot robot;

	/**
	 * Upload the specific file.
	 * 
	 * @param fileNameWithExtension
	 *            name of the file with extension
	 * @param path
	 *            path of the file
	 */
	public void uploadFile(String fileNameWithExtension, String path) {
		try {
			robot = new Robot();
			robot.setAutoDelay(AUTO_DELAY);
			setClipboardContent(path + fileNameWithExtension);

			// Paste the content.
			pasteFromClipboard();
			// Press the key, Enter.
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Set the content into the clip board of the system.
	 * 
	 * @param content
	 */
	private void setClipboardContent(String content) {
		StringSelection stringSelection = new StringSelection(content);

		// Set the content into the clip board of the system.
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
	}

	/**
	 * Paste the content from Clip board.
	 */
	private void pasteFromClipboard() {
		if (systemUtil.getOsName().equals(OS.Windows.name())) {
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		} else if (systemUtil.getOsName().equals(OS.Mac.name())) {
			robot.keyPress(KeyEvent.VK_META);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_META);
		}
		robot.keyRelease(KeyEvent.VK_V);
	}
}
