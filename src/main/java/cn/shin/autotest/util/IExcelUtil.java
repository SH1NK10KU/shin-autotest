package cn.shin.autotest.util;

import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * Interface for ExcelUtilImpl
 * 
 * @author Shin Feng
 * @date 2014-10-27
 */
public interface IExcelUtil {
	/**
	 * Open the Excel file in the specific path.
	 * 
	 * @param path
	 *            path of the xls file.("/${XLS_PATH}/[TestClassName].xls")
	 * @return Workbook
	 * @throws BiffException
	 *             , IOException
	 */
	public Workbook openExcel(String path);

	/**
	 * Get the sheet via the name.
	 * 
	 * @param sheetName
	 *            name of the sheet
	 * @return Sheet
	 */
	public Sheet getSheetByName(String sheetName);

	/**
	 * Get the number of the rows.
	 */
	public int getRowNum();

	/**
	 * Get the number of the columns.
	 */
	public int getColumnNum();

	/**
	 * Get the name of the columns. (Usually the first row contains all the
	 * names.)
	 * 
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getColumnName();

	/**
	 * Get the values of the cells in the specific row.
	 * 
	 * return ArrayList<String>
	 */
	public ArrayList<String> getCellsInRow(int rowNum);

	/**
	 * Close the Excel file.
	 */
	public void closeExcel();
}
