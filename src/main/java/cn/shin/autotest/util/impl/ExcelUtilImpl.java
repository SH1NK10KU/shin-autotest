package cn.shin.autotest.util.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import cn.shin.autotest.testng.ExcelDataProvider;
import cn.shin.autotest.util.IExcelUtil;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

/**
 * Provide the basic methods for Excel operation.
 * 
 * @author Shin Feng
 * @date 2014-10-27
 *
 */
public class ExcelUtilImpl implements IExcelUtil {
	private Workbook workbook = null;
	private Sheet sheet = null;

	/**
	 * Open the Excel file in the specific path.
	 * 
	 * @param path
	 *            path of the xls file.("/${XLS_PATH}/[TestClassName].xls")
	 * @return Workbook
	 * @throws BiffException
	 *             , IOException
	 */
	public Workbook openExcel(String path) {
		InputStream inputStream = ExcelDataProvider.class
				.getResourceAsStream(path);

		try {
			WorkbookSettings ws = new WorkbookSettings();
			ws.setEncoding("UTF-8");
			workbook = Workbook.getWorkbook(inputStream);
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return workbook;
	}

	/**
	 * Get the sheet via the name.
	 * 
	 * @param sheetName
	 *            name of the sheet
	 * @return Sheet
	 */
	public Sheet getSheetByName(String sheetName) {
		sheet = workbook.getSheet(sheetName);
		return sheet;
	}

	/**
	 * Get the number of the rows.
	 */
	public int getRowNum() {
		return sheet.getRows();
	}

	/**
	 * Get the number of the columns.
	 */
	public int getColumnNum() {
		return sheet.getColumns();
	}

	/**
	 * Get the name of the columns.
	 * 
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getColumnName() {
		ArrayList<String> columnName = new ArrayList<String>();
		if (this.getRowNum() > 0) {
			columnName = this.getCellsInRow(0);
		}
		return columnName;
	}

	/**
	 * Get the values of the cells in the specific row.
	 * 
	 * return ArrayList<String>
	 */
	public ArrayList<String> getCellsInRow(int rowNum) {
		ArrayList<String> values = new ArrayList<String>();
		Cell[] cells = sheet.getRow(rowNum);
		for (int i = 0; i < cells.length; i++) {
			values.add(cells[i].getContents().toString());
		}
		return values;
	}

	/**
	 * Close the Excel file.
	 */
	public void closeExcel() {
		if (workbook != null) {
			workbook.close();
		}
	}
}