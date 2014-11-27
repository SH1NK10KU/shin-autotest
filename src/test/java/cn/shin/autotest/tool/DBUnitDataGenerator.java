package cn.shin.autotest.tool;

import cn.shin.autotest.util.IDBUnitUtil;
import cn.shin.autotest.util.impl.DBUnitUtilImpl;

/**
 * 
 * 
 * @see http://www.dbunit.org/faq.html#generatedtd
 * 
 * @author Shin Feng
 * @date 2014-11-06
 *
 */
public class DBUnitDataGenerator {
	public static void main(String[] args) throws Exception {
		IDBUnitUtil dbUnitUtil = new DBUnitUtilImpl();

		dbUnitUtil.generateFlatXmlWithDtd();
		dbUnitUtil.generateFlatXmlWithDtdByQuery("SHIN_DBUNIT_TEST", "ACTOR",
				"SELECT * FROM actor");
	}
}
