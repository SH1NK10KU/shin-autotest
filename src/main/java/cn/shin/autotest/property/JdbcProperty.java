package cn.shin.autotest.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Shin Feng
 * @date 2014-11-12
 * 
 */
@Component
public class JdbcProperty {
	@Value("${jdbc.driver}")
	private String jdbcDirver;
	@Value("${jdbc.url}")
	private String jdbcUrl;
	@Value("${jdbc.username}")
	private String jdbcUsername;
	@Value("${jdbc.password}")
	private String jdbcPassword;
	@Value("${dbunit.data.path}")
	private String dbunitDataPath;
	@Value("${dbunit.export.folder}")
	private String dbunitExportFolder;
	@Value("${dbunit.prepare.folder}")
	private String dbunitPrepareFolder;
	@Value("${dbunit.expected.folder}")
	private String dbunitExpectedFoler;

	public String getJdbcDirver() {
		return jdbcDirver;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public String getJdbcUsername() {
		return jdbcUsername;
	}

	public String getJdbcPassword() {
		return jdbcPassword;
	}

	public String getDbunitDataPath() {
		return dbunitDataPath;
	}

	public String getDbunitExportFolder() {
		return dbunitExportFolder;
	}

	public String getDbunitPrepareFolder() {
		return dbunitPrepareFolder;
	}

	public String getDbunitExpectedFoler() {
		return dbunitExpectedFoler;
	}
}
