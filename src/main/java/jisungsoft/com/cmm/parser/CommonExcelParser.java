package jisungsoft.com.cmm.parser;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CommonExcelParser {

	public static void excelRead() {
		try {
			FileInputStream fis = new FileInputStream("D:\\DEV\\test\\question.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);

			int rowindex = 0;
			int columnindex = 0;

			XSSFSheet sheet = workbook.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();

			for (rowindex = 1; rowindex < rows; rowindex++) {
				XSSFRow row = sheet.getRow(rowindex);
				if (row != null) {
					int cells = row.getPhysicalNumberOfCells();
					for (columnindex = 0; columnindex <= cells; columnindex++) {
						XSSFCell cell = row.getCell(columnindex);
						String value = "";

						if (cell == null) {
							continue;
						} else {
							switch (cell.getCellType()) {
							case XSSFCell.CELL_TYPE_NUMERIC:
								value = cell.getNumericCellValue() + "";
								break;
							case XSSFCell.CELL_TYPE_STRING:
								value = cell.getStringCellValue() + "";
								break;
							case XSSFCell.CELL_TYPE_BLANK:
								value = cell.getBooleanCellValue() + "";
							case XSSFCell.CELL_TYPE_ERROR:
								value = cell.getErrorCellValue() + "";
							}
						}
						System.out.println(value);
					}
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
