package api.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {

    public static String getCellData(String filePath, String sheetName, int rowNum, int colNum) {
        String data = "";

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(rowNum);
            Cell cell = row.getCell(colNum);

            DataFormatter formatter = new DataFormatter();
            data = formatter.formatCellValue(cell);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    public static int getRowCount(String filePath, String sheetName) {
        int rowCount = 0;

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            rowCount = sheet.getLastRowNum();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return rowCount;
    }

    public static int getCellCount(String filePath, String sheetName, int rowNum) {
        int cellCount = 0;

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(rowNum);
            cellCount = row.getLastCellNum();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return cellCount;
    }
}
