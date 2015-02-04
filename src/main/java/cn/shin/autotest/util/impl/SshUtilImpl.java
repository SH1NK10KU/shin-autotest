package cn.shin.autotest.util.impl;

import java.io.InputStream;

import java.util.Properties;

import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * Provide basic methods for SSH operations.
 * 
 * @author Shin Feng
 * @date 2014-11-07
 */
public class SshUtilImpl {
	private Logger LOG = Logger.getLogger(SshUtilImpl.class);
	private static final int DEFAULT_SSH_TIMEOUT = 3000;

	public SshUtilImpl() {

	}

	public Session openSession(String userName, String password, String host,
			int port) {
		Session session = null;
		session = connect(userName, password, host, port);
		return session;
	}

	private Session connect(String userName, String password, String host,
			int port) {
		Session session = null;
		try {
			session = new JSch().getSession(userName, host, port);
			session = skipHostKeyChecking(session);
			session.setPassword(password);
		} catch (JSchException e) {
			e.printStackTrace();
		}
		return session;
	}

	private Session skipHostKeyChecking(Session session) {
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		return session;
	}

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
			int port, String command) {
		int returnCode = 0;
		Session session = null;
		Channel channel = null;

		try {
			session = openSession(userName, password, host, port);
			session.connect(DEFAULT_SSH_TIMEOUT);

			channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(command);
			channel.connect();

			channel.disconnect();
			session.disconnect();
		} catch (JSchException e) {

		} catch (Exception e) {

		} finally {
			if (channel != null) {
				channel.disconnect();
			}
			if (session != null) {
				session.disconnect();
			}
		}

		return returnCode;
	}

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
			String password, String host, int port, String command) {
		String result = null;
		Session session = null;
		Channel channel = null;

		try {
			session = openSession(userName, password, host, port);
			session.connect(DEFAULT_SSH_TIMEOUT);

			channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(command);
			channel.setInputStream(null);
			((ChannelExec) channel).setErrStream(System.err);

			InputStream in = channel.getInputStream();
			channel.connect();
			byte[] tmp = new byte[1024];
			while (true) {
				while (in.available() > 0) {
					int i = in.read(tmp, 0, 1024);
					if (i < 0)
						break;
					result = new String(tmp, 0, i);
				}
				if (channel.isClosed()) {
					break;
				}
				Thread.sleep(1000);
			}
		} catch (JSchException e) {

		} catch (Exception e) {

		} finally {
			if (channel != null) {
				channel.disconnect();
			}
			if (session != null) {
				session.disconnect();
			}
		}

		return result;
	}
}