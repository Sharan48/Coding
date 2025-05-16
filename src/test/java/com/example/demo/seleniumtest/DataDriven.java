package com.example.demo.seleniumtest;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DataDriven {

    public Object[][] getDataFromExcelSheet(String file, String sheetName) throws IOException {

        // Load the Excel file using Apache POI
        FileInputStream fil = new FileInputStream(file);
        Workbook workBook = new XSSFWorkbook(fil);
        Sheet workSheet = workBook.getSheet(sheetName);

        int rowCount = workSheet.getPhysicalNumberOfRows();
        int colCount = workSheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[rowCount - 1][colCount];

        for (int i = 1; i < rowCount; i++) {
            Row row = workSheet.getRow(i);
            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j);
                data[i - 1][j] = cellType(cell);

            }
        }
        workBook.close();
        fil.close();
        return data;

    }

    public static Object cellType(Cell cell) {

        if (cell == null)
            return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return cell.getNumericCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }

    }

    @DataProvider(name = "ExcelDataProvider")
    public Object[][] excelData() throws IOException {
        String filePaht = "src/main/resources/DataProvider.xlsx";
        String sheetName = "loginCredentials";
        return this.getDataFromExcelSheet(filePaht, sheetName);
    }

}
