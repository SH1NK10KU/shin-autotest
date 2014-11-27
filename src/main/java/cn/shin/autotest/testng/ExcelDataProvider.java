package cn.shin.autotest.testng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.shin.autotest.property.TestngProperty;
import cn.shin.autotest.util.IExcelUtil;
import cn.shin.autotest.util.impl.ExcelUtilImpl;

/**
 * Provide the Excel data provider for TestNG.
 * 
 * @author Shin Feng
 * @date 2014-10-28
 *
 */
public class ExcelDataProvider implements Iterator<Object[]> {
	private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
			"classpath*:/applicationContext.xml");
	private static TestngProperty testngProperty = applicationContext
			.getBean(TestngProperty.class);
	private static Logger logger = Logger.getLogger(ExcelDataProvider.class);

	private int rowNum = 0;
	private int currentRowNo = 1;
	private int columnNum = 0;
	private ArrayList<String> columnName;
	private IExcelUtil excelUtil = new ExcelUtilImpl();

	/**
	 * Constructor to initialize the Excel attributes.
	 * 
	 * @param className
	 *            name of the test case class
	 * @param caseName
	 *            name of the test case
	 * 
	 */
	public ExcelDataProvider(String className, String caseName) {
		String path = testngProperty.getXlsPath()
				+ className.substring(className.lastIndexOf(".") + 1,
						className.length()) + ".xls";
		logger.info("Load test data from " + path);
		excelUtil.openExcel(path);
		excelUtil.getSheetByName(caseName);
		rowNum = excelUtil.getRowNum();
		columnNum = excelUtil.getColumnNum();
		columnName = excelUtil.getColumnName();
	}

	/**
	 * Whether the Excel has next data or not.
	 */
	public boolean hasNext() {
		if (this.rowNum == 0 || this.currentRowNo >= this.rowNum) {
			excelUtil.closeExcel();
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Fetch next test data from Excel.
	 */
	public Object[] next() {
		ArrayList<String> values = excelUtil.getCellsInRow(this.currentRowNo);
		Map<String, String> data = new HashMap<String, String>();

		for (int index = 0; index < this.columnNum; index++) {
			data.put(this.columnName.get(index), values.get(index));
		}

		this.currentRowNo++;

		Object object[] = new Object[] { data };
		return object;
	}
}
