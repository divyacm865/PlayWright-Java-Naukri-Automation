package co.Naukri.DataReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataReader {

	public static Object[][] readData(String fileName, String sheetName) throws FileNotFoundException, IOException {
		// List<Object[]> data = new ArrayList<>();
		try (FileInputStream fis = new FileInputStream(fileName); XSSFWorkbook wb = new XSSFWorkbook(fis)) {
			XSSFSheet sheet = wb.getSheet(sheetName);
			if (sheet == null) {
				throw new IllegalArgumentException("Sheet not found: " + sheet);
			}
			DataFormatter formatter = new DataFormatter();
			int rowCount = sheet.getPhysicalNumberOfRows();
			int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
			Object[][] data = new Object[rowCount - 1][colCount];
			// Skip header row
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				if (row == null) {
					continue;
				}
				for (int j = 0; j < colCount; j++) {
					Cell cell = row.getCell(j, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
					data[i - 1][j] = cell == null ? "" : formatter.formatCellValue(cell);
				}
			}
			return data;
//			 int rowCount = sheet.getPhysicalNumberOfRows();
//		        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
//		        
//		        Object[][] data = new Object[rowCount - 1][colCount]; // To ignore the header row
//		        
//		        for (int i = 1; i < rowCount; i++) { // Start from 1 to skip the header row
//		            for (int j = 0; j < colCount; j++) {
//		                data[i - 1][j] = sheet.getRow(i).getCell(j).toString();
//		            }
//		        }
//		        wb.close();
//		        return data;
//			DataFormatter formatter = new DataFormatter();
//			// Skip the header row
//			int lastRow = sheet.getLastRowNum();
//			int maxColumns = 0;
//			// Find the maximum number of columns in the sheet
//			for (int i = 0; i <= lastRow; i++) {
//				Row row = sheet.getRow(i);
//				if (row != null && row.getLastCellNum() > maxColumns) {
//					maxColumns = row.getLastCellNum();
//				}
//			}
//			// Read data, skipping the header row
//			for (int i = 1; i <= lastRow; i++) {
//				Row row = sheet.getRow(i);
//				if (row == null) {
//					continue;
//				}
//				Object[] rowData = new Object[maxColumns];
//				boolean isEmptyRow = true;
//				for (int j = 0; j < maxColumns; j++) {
//					Cell cell = row.getCell(j, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
//
//					String value = cell == null ? "" : formatter.formatCellValue(cell);
//					rowData[j] = value;
//					if (!value.trim().isEmpty()) {
//						isEmptyRow = false;
//					}
//				}
//				if (!isEmptyRow) {
//					data.add(rowData);
//				}
//			}
//		}
//		return data.toArray(new Object[0][]);
		}

	}
}

//	====================================================================
//
//	public static Object[][] readExcelData(String filePath, String sheetName) throws IOException {
//		List<Object[]> data = new ArrayList<>();
//		try (FileInputStream fis = new FileInputStream(filePath); Workbook workbook = new XSSFWorkbook(fis)) {
//			Sheet sheet = workbook.getSheet(sheetName);
//			if (sheet == null) {
//				throw new IllegalArgumentException("Sheet not found: " + sheetName);
//			}
//			DataFormatter formatter = new DataFormatter();
//			// Skip the header row
//			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
//				Row row = sheet.getRow(i);
//				if (row == null) {
//					continue;
//				}
//				String username = formatter.formatCellValue(row.getCell(0)).trim();
//				String password = formatter.formatCellValue(row.getCell(1));
//				if (username.isEmpty()) {
//					continue;
//				}
//				data.add(new Object[] { username, password });
//			}
//		}
//		return data.toArray(new Object[0][]);
//	}
//}
