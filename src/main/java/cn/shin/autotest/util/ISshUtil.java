package cn.shin.autotest.util;

/**
 * Interface for SshUtilImpl.
 * 
 * @author Shin Feng
 * @date 2014-11-07
 */
public interface ISshUtil {

	/**
	 * Execute the command on remote server.
	 * 
	 * @param userName
	 * @param password
	 * @param host
	 * @param port
	 * @param command
	 *            command should end with "\n"
	 * @return
	 */
	public int executeCommand(String userName, String password, String host,
			int port, String command);

	/**
	 * Return the result after executing command on remote server.
	 * 
	 * @param userName
	 * @param password
	 * @param host
	 * @param port
	 * @param command
	 *            command should end with "\n"
	 * @return
	 */
	public String returnResultAfterExecuteCommand(String userName,
			String password, String host, int port, String command);
}
