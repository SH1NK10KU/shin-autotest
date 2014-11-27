package cn.shin.autotest.util;

/**
 * Interface for DBUnitUtilImpl.
 * 
 * @author Shin Feng
 * @date 2014-11-06
 *
 */
public interface IDBUnitUtil {
	public enum Operation {
		insert, delete
	}

	/**
	 * Generate the Flat XML file and DTD file for the whole database in
	 * ${DBUNIT_EXPORT_PATH}.
	 * 
	 */
	public void generateFlatXmlWithDtd();

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
			String tableName, String query);

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
			String... includeColumn);

	/**
	 * Operate the database with the data in the specific XML file.
	 * 
	 * @param operation
	 * @param xmlName
	 *            name of the XML file with the data to be operated
	 */
	public void operateDatabase(Operation operation, String xmlName);
}
