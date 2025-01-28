package gis.insight.e2e.excel.methods;

import org.apache.poi.ss.usermodel.*;
import java.text.DecimalFormat;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * This class provides methods for performing search optimizations within an Excel file,
 * particularly for retrieving and writing data in the "erwartete Testergebnisse" sheet.
 */
public class Excel_SearchOptimizations {

    /**
     * The path to the Excel file.
     */
    private String excelFilePath;

    /**
     * Constructor for the Excel_SearchOptimizations class.
     *
     * @param ExcelPfad the path to the Excel file.
     */
    public Excel_SearchOptimizations(String ExcelPfad) {
        this.excelFilePath = ExcelPfad;
    }

    /**
     * Retrieves the search term from the specified row index in the Excel sheet.
     * It first tries to get the value from column B, and if that is empty, it tries column D.
     *
     * @param Index the row index to retrieve the search term from.
     * @return the search term as a string, or an empty string if not found.
     */
    public String Suchbegriff(int Index) {
        String value = "";
        try {
            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet("erwartete Testergebnisse");

            Row targetRow = sheet.getRow(Index);

            if (targetRow != null) {
                Cell cell1 = targetRow.getCell(1); // Column B (0-based index)
                Cell cell3 = targetRow.getCell(3); // Column D (0-based index)

                if (cell1 != null && cell1.getCellType() != CellType.BLANK) {
                    value = getCellStringValue(cell1);
                } else if (cell3 != null) {
                    value = getCellStringValue(cell3);
                }
            }
            workbook.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return value;
    }

    /**
     * Retrieves the expected result from the specified row index in the Excel sheet.
     *
     * @param Index the row index to retrieve the expected result from.
     * @return the expected result as a string, or an empty string if not found.
     */
    public String ErwarteteErgebnisse(int Index) {
        String value = "";
        try {
            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet("erwartete Testergebnisse");

            Row targetRow = sheet.getRow(Index);

            if (targetRow != null) {
                Cell cell = targetRow.getCell(4); // Column E (0-based index)

                if (cell != null) {
                    switch (cell.getCellType()) {
                        case STRING:
                            value = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            value = String.valueOf(cell.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            value = String.valueOf(cell.getBooleanCellValue());
                            break;
                        case FORMULA:
                            value = cell.getCellFormula();
                            break;
                        default:
                            value = "Unsupported cell type";
                            break;
                    }
                }
            }
            workbook.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return value;
    }

    /**
     * Writes a position value to a specified column and row index in the Excel sheet.
     *
     * @param Index    the row index to write the position to.
     * @param Column   the column name to write the position to.
     * @param Position the position value to be written.
     */
    public void WritePosition(int Index, String Column, String Position) {
        try {
            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet("erwartete Testergebnisse");

            Row targetRow = sheet.getRow(Index);

            if (targetRow.getCell(getColumnIndex(Column)) != null) {
                targetRow.getCell(getColumnIndex(Column)).setCellValue(Position);
            }

            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            outputStream.close();
            workbook.close();
            inputStream.close();

            System.out.println("Data written to Excel successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Counts the number of non-empty rows in the "erwartete Testergebnisse" sheet.
     *
     * @return the number of non-empty rows.
     */
    public int SuchbegriffeNumber() {
        int nonEmptyRowCount = 0;
        try {
            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet("erwartete Testergebnisse");

            for (Row row : sheet) {
                boolean rowHasValue = false;

                if (row.getCell(0) != null && row.getCell(0).getCellType() != CellType.BLANK) {
                    rowHasValue = true;
                }

                if (rowHasValue) {
                    nonEmptyRowCount++;
                }
            }
            inputStream.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return nonEmptyRowCount;
    }

    /**
     * Converts a column name (e.g., "A", "B", "C", ..., "Z", "AA", etc.) to its corresponding 0-based index.
     *
     * @param columnName the column name to convert.
     * @return the 0-based column index.
     * @throws IllegalArgumentException if the column name is invalid.
     */
    public static int getColumnIndex(String columnName) {
        int columnIndex = -1;
        int base = 'Z' - 'A' + 1; // 26 (for A-Z)

        for (int i = 0; i < columnName.length(); i++) {
            char c = columnName.charAt(i);
            if (c < 'A' || c > 'Z') {
                throw new IllegalArgumentException("Invalid column name: " + columnName);
            }
            if (columnIndex == -1) {
                columnIndex = 0;
            } else {
                columnIndex *= base;
            }
            columnIndex += (c - 'A' + 1);
        }

        return columnIndex - 1; // Convert to 0-based index
    }

    /**
     * Retrieves the string value from a cell, handling different cell types.
     *
     * @param cell the cell to retrieve the value from.
     * @return the cell value as a string.
     */
    private static String getCellStringValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(formatNumber(cell.getNumericCellValue()));
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "Unsupported cell type";
        }
    }

    /**
     * Formats a numeric value, removing fractional digits.
     *
     * @param number the numeric value to format.
     * @return the formatted numeric value as a string.
     */
    private static String formatNumber(double number) {
        DecimalFormat df = new DecimalFormat("0");
        df.setMaximumFractionDigits(0);
        return df.format(number);
    }
}
