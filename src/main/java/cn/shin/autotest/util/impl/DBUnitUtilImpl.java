package cn.shin.autotest.util.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.AmbiguousTableNameException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatDtdDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.xml.sax.InputSource;

import cn.shin.autotest.property.JdbcProperty;
import cn.shin.autotest.util.IDBUnitUtil;

/**
 * Provide the basic methods for DBUnit.
 * 
 * @author Shin Feng
 * @date 2014-11-06
 *
 */
public class DBUnitUtilImpl implements IDBUnitUtil {
	private static final Logger LOG = Logger.getLogger(DBUnitUtilImpl.class);
	private static IDatabaseConnection connection;
//	private static FileUtilImpl fileUtil = new FileUtilImpl();

	private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
			"classpath*:/applicationContext.xml");
	private static JdbcProperty jdbcProperty = applicationContext
			.getBean(JdbcProperty.class);

	static {
		try {
			Class<?> driverClass = Class.forName(jdbcProperty.getJdbcDirver());
			java.sql.Connection jdbcConnection = DriverManager.getConnection(
					jdbcProperty.getJdbcUrl(), jdbcProperty.getJdbcUsername(),
					jdbcProperty.getJdbcPassword());
			connection = new DatabaseConnection(jdbcConnection);
			DatabaseConfig config = connection.getConfig();
			config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
					new MySqlDataTypeFactory());

			// Create the folder.
//			fileUtil.createFolder(
//					jdbcProperty.getDbunitExportFolder(),
//					new File(System.getProperty("user.dir")
//							+ jdbcProperty.getDbunitDataPath())
//							.getAbsolutePath());
//			fileUtil.createFolder(
//					jdbcProperty.getDbunitPrepareFolder(),
//					new File(System.getProperty("user.dir")
//							+ jdbcProperty.getDbunitDataPath())
//							.getAbsolutePath());
//			fileUtil.createFolder(
//					jdbcProperty.getDbunitExpectedFoler(),
//					new File(System.getProperty("user.dir")
//							+ jdbcProperty.getDbunitDataPath())
//							.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Generate the Flat XML file and DTD file for the whole database in
	 * ${DBUNIT_EXPORT_PATH}.
	 * 
	 */
	public void generateFlatXmlWithDtd() {
		try {
			IDataSet fullDataSet = connection.createDataSet();
			String xml = System.getProperty("user.dir")
					+ jdbcProperty.getDbunitDataPath() + "/"
					+ jdbcProperty.getDbunitExportFolder() + "/"
					+ "DATABASE_FULL.xml";
			String dtd = System.getProperty("user.dir")
					+ jdbcProperty.getDbunitDataPath() + "/"
					+ jdbcProperty.getDbunitExportFolder() + "/"
					+ "DATABASE_FULL.dtd";
			FlatXmlDataSet.write(fullDataSet, new FileOutputStream(
					new File(xml).getAbsolutePath()));
			FlatDtdDataSet.write(fullDataSet, new FileOutputStream(
					new File(dtd).getAbsolutePath()));
			LOG.info("DATABASE_FULL.xml is generated in the path, "
					+ System.getProperty("user.dir")
					+ jdbcProperty.getDbunitDataPath()
					+ jdbcProperty.getDbunitExportFolder() + "/");
			LOG.info("DATABASE_FULL.dtd is generated in the path, "
					+ System.getProperty("user.dir")
					+ jdbcProperty.getDbunitDataPath()
					+ jdbcProperty.getDbunitExportFolder() + "/");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataSetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Generate the Flat XML file and DTD file for the specific table according
	 * to query in ${DBUNIT_EXPORT_PATH}.
	 * 
	 * @param testName
	 *            name of the test
	 * @param tableName
	 *            name of the table which you want to define.
	 * @param query
	 * 
	 */
	public void generateFlatXmlWithDtdByQuery(String testName,
			String tableName, String query) {
		try {
			QueryDataSet partialDataSet = new QueryDataSet(connection);
			partialDataSet.addTable(tableName, query);
			String xml = System.getProperty("user.dir")
					+ jdbcProperty.getDbunitDataPath() + "/"
					+ jdbcProperty.getDbunitExportFolder() + "/" + testName
					+ "_" + tableName + "_PARTIAL.xml";
			String dtd = System.getProperty("user.dir")
					+ jdbcProperty.getDbunitDataPath() + "/"
					+ jdbcProperty.getDbunitExportFolder() + "/" + testName
					+ "_" + tableName + "_PARTIAL.dtd";
			FlatXmlDataSet.write(partialDataSet, new FileOutputStream(new File(
					xml).getAbsolutePath()));
			FlatDtdDataSet.write(partialDataSet, new FileOutputStream(new File(
					dtd).getAbsolutePath()));
			LOG.info("DATABASE_FULL.xml is generated in the path, "
					+ System.getProperty("user.dir")
					+ jdbcProperty.getDbunitDataPath()
					+ jdbcProperty.getDbunitExportFolder() + "/");
			LOG.info("DATABASE_FULL.dtd is generated in the path, "
					+ System.getProperty("user.dir")
					+ jdbcProperty.getDbunitDataPath()
					+ jdbcProperty.getDbunitExportFolder() + "/");
		} catch (AmbiguousTableNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataSetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Assert whether the data in the specific table equals the expected data in
	 * the specific XML file.
	 * 
	 * @param tableName
	 *            name of the table which you want to assert
	 * @param xmlName
	 *            name of the XML file in the path, ${DBUNIT_EXPECTED_PATH}.
	 * @param includeColumn
	 *            name of the include column
	 */
	public void assertTableEqualsXml(String tableName, String xmlName,
			String... includeColumn) {
		IDataSet databaseDataSet;
		try {
			databaseDataSet = connection.createDataSet();
			ITable actualTable = databaseDataSet.getTable(tableName);
			IDataSet expectedDataSet = new FlatXmlDataSet(new FlatXmlProducer(
					new InputSource(new File(System.getProperty("user.dir")
							+ jdbcProperty.getDbunitDataPath() + "/"
							+ jdbcProperty.getDbunitExpectedFoler() + "/"
							+ xmlName + ".xml").getAbsolutePath()), true, true));
			ITable expectedTable = expectedDataSet.getTable(tableName);

			if (includeColumn.length > 0) {
				actualTable = DefaultColumnFilter.includedColumnsTable(
						actualTable, includeColumn);
				expectedTable = DefaultColumnFilter.includedColumnsTable(
						expectedTable, includeColumn);
			}

			Assertion.assertEquals(expectedTable, actualTable);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataSetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseUnitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Operate the database with the data in the specific XML file.
	 * 
	 * @param operation
	 * @param xmlName
	 *            name of the XML file with the data to be operated
	 */
	public void operateDatabase(Operation operation, String xmlName) {
		try {
			IDataSet dataSet = new FlatXmlDataSetBuilder().build((new File(
					System.getProperty("user.dir")
							+ jdbcProperty.getDbunitDataPath() + "/"
							+ jdbcProperty.getDbunitPrepareFolder() + "/"
							+ xmlName + ".xml")));
			if (operation.equals(Operation.insert.name())) {
				DatabaseOperation.INSERT.execute(connection, dataSet);
			} else if (operation.equals(Operation.delete.name())) {
				DatabaseOperation.DELETE.execute(connection, dataSet);
			}
		} catch (DatabaseUnitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
