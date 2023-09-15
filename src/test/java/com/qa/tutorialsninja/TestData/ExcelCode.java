package com.qa.tutorialsninja.TestData;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelCode {

	public static FileInputStream ip;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;

	

	@DataProvider(name = "TN")
	public static Object[][] getTNExcelData() throws Exception {
		Object[][] data = ExcelCode.readDataFromExcelforTutorialsNinja("LoginTN");
		return data;
	}
	
	

	

	public static Object[][] readDataFromExcelforTutorialsNinja(String sheetName) throws Exception {
		ip = new FileInputStream("C:\\Users\\sarth\\eclipse-workspace\\CANADA_HybridFramework_June_Sep_2023\\src\\test\\java\\com\\qa\\tutorialsninja\\TestData\\ExcelData.xlsx");
		workbook = new XSSFWorkbook(ip);
		sheet = workbook.getSheet(sheetName);

		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[rows][cols];

		for (int i = 0; i < rows; i++) {
			XSSFRow row = sheet.getRow(i + 1);

			for (int j = 0; j < cols; j++) {
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();

				switch (cellType) {

				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j] = Integer.toString((int) cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;

				}

			}

		}

		return data;

	}
}
