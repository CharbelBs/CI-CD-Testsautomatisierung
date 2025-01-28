package gis.insight.e2e.excel.methods;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

import java.io.BufferedReader;
import java.io.InputStreamReader;


/**
 * This class provides methods for interacting with an Excel file, particularly for writing test results and other data.
 * It utilizes the Apache POI library to manage Excel files and perform read/write operations.
 */
public class Excel_tests_phases {

    /**
     * The path to the Excel file.
     */
    public String excelFilePath;

    /**
     * The name of the Excel sheet to be accessed or modified.
     */
    public String ExcelTabele;

    /**
     * Constructor for the Excel_tests_phases class.
     *
     * @param excelFilePath the path to the Excel file.
     * @param ExcelTabele   the name of the Excel sheet to be accessed or modified.
     */
    public Excel_tests_phases(String excelFilePath, String ExcelTabele) {
        this.excelFilePath = excelFilePath;
        this.ExcelTabele = ExcelTabele;
    }

    // Helper method to open Excel file using Desktop
    public void openExcelFile(String filePath) {
        try {

            File file = new File(filePath).getAbsoluteFile();
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                if (file.exists()) {
                    desktop.open(file);
                }
            } else {
                System.out.println("Desktop is not supported.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ExcelDateiErstellen(String nameTemplate, String nameResult) {
        // Quellpfad der Datei
        Path sourcePath = Paths.get("./"+nameTemplate);

        // Aktuelles Datum abrufen
        LocalDate currentDate = LocalDate.now();

        // Datum im Format "YYYY-MM-DD" formatieren
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyMMdd");
        String formattedDate = currentDate.format(dateFormatter);

        // Zielpfad mit Datum im Dateinamen konstruieren
        String destinationFileName = nameResult + formattedDate + ".xlsx";
        Path destinationPath = Paths.get("./results/" + destinationFileName);

        try {
            // Überprüfen, ob die Datei bereits existiert
            if (Files.exists(destinationPath)) {
                System.out.println("Die Datei existiert bereits: " + destinationFileName);
                return; // Vorgang abbrechen
            }

            // Datei kopieren, falls sie noch nicht existiert
            Files.copy(sourcePath, destinationPath);
            System.out.println("Datei wurde erfolgreich kopiert.");
        } catch (Exception e) {
            System.out.println("Fehler beim Kopieren der Datei: " + e.getMessage());
        }
    }

    public void CloseExcelProcess() {
        try {
            // Check if Excel is running
            String line;
            Process p = Runtime.getRuntime().exec("tasklist");
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            
            boolean isExcelRunning = false;
            while ((line = input.readLine()) != null) {
                if (line.contains("EXCEL.EXE")) {  // Check for the Excel process
                    isExcelRunning = true;
                    break;
                }
            }
            input.close();

            // If Excel is running, close it
            if (isExcelRunning) {
                Runtime.getRuntime().exec("taskkill /F /IM EXCEL.EXE");
                System.out.println("Excel process closed successfully.");
            } else {
                System.out.println("Excel is not running.");
            }

            // Proceed with your test, now that Excel is closed

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes test results to the specified Excel sheet with automatic generation of the "Testfall ID".
     * The method dynamically determines the column positions based on the headers provided and
     * writes the corresponding values into the Excel sheet. Additionally, it automatically generates
     * the next "Testfall ID" based on the highest existing ID in the sheet.
     *
     * <p>This method is designed to be flexible, allowing the addition of new columns without requiring
     * changes to the method signature. The method uses header-value pairs, where each header represents
     * a column in the Excel sheet and its corresponding value is the data to be written.</p>
     *
     * @param headerValuePairs A varargs parameter where each pair represents a column header and its
     *                         corresponding value. The first element in each pair is the header name,
     *                         and the second element is the value to be written under that header.
     *                         Example usage: "App", "MyApp", "Users", "JohnDoe", "Status", "Passed"
     */
    public void Write_Test_Result(String... headerValuePairs) {
        try (FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
             Workbook workbook = WorkbookFactory.create(inputStream);
             FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {

            Sheet sheet = workbook.getSheet(ExcelTabele);
            int nextRowNum = sheet.getLastRowNum() + 1;
            Row row = sheet.createRow(nextRowNum);

            // Generate and write the next "Testfall ID"
            String newTestfallId = findNextHighestTestId(sheet);
            row.createCell(0).setCellValue(newTestfallId); // Assuming "Testfall ID" is in the first column

            for (int i = 0; i < headerValuePairs.length; i += 2) {
                String header = headerValuePairs[i];
                String value = headerValuePairs[i + 1];
                int columnIndex = getColumnIndexByName(sheet, header);
                row.createCell(columnIndex).setCellValue(value);
            }

            createTableAppearance(sheet, nextRowNum);
            workbook.write(outputStream);
            System.out.println("Data written to Excel successfully with Testfall ID: " + newTestfallId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    /**
     * Creates a table-like appearance in the Excel sheet, with optional borders.
     * This method can also apply filters to the columns.
     *
     * @param sheet      the Excel sheet to modify.
     * @param lastRowNum the last row number to apply the table formatting.
     */
    private void createTableAppearance(Sheet sheet, int lastRowNum) {
        int numCols = sheet.getRow(1).getLastCellNum(); // Headers
        CellRangeAddress range = new CellRangeAddress(1, lastRowNum, 0, numCols - 1);
        sheet.setAutoFilter(range);

        // Optional: Add styling to make it look like a table (borders, etc.)
        // You can also apply styles to the header row here if needed
    }

    /**
     * Finds the next empty row in the Excel sheet.
     *
     * @param sheet the Excel sheet to search.
     * @return the first empty row found, or a new row if none are empty.
     */
    private Row findEmptyRow(Sheet sheet) {
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null || row.getCell(0) == null || isCellEmpty(row.getCell(0))) {
                return (row == null) ? sheet.createRow(i) : row;
            }
        }
        return sheet.createRow(sheet.getLastRowNum() + 1);
    }

    /**
     * Checks if a cell is empty.
     *
     * @param cell the cell to check.
     * @return true if the cell is empty, false otherwise.
     */
    private boolean isCellEmpty(Cell cell) {
        if (cell == null) {
            return true;
        }
        if (cell.getCellType() == CellType.BLANK) {
            return true;
        }
        if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().isEmpty()) {
            return true;
        }
        if (cell.getCellType() == CellType.NUMERIC && Double.isNaN(cell.getNumericCellValue())) {
            return true;
        }
        return false;
    }

    /**
     * Finds the next highest test ID in the Excel sheet.
     *
     * @param sheet the Excel sheet to search.
     * @return the next highest test ID as a string.
     * @throws IOException if an I/O error occurs.
     */
    private String findNextHighestTestId(Sheet sheet) throws IOException {

        int maxTestfallID = 0;
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next(); // Skip header row

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Cell testfallIdCell = row.getCell(0); // Assuming Testfall ID is in the first column

            if (testfallIdCell != null && testfallIdCell.getCellType() == CellType.NUMERIC) {
                int currentTestfallID = (int) testfallIdCell.getNumericCellValue();
                if (currentTestfallID > maxTestfallID) {
                    maxTestfallID = currentTestfallID;
                }
            } else if (testfallIdCell != null && testfallIdCell.getCellType() == CellType.STRING) {
                try {
                    int currentTestfallID = Integer.parseInt(testfallIdCell.getStringCellValue().replace(",", ""));
                    if (currentTestfallID > maxTestfallID) {
                        maxTestfallID = currentTestfallID;
                    }
                } catch (NumberFormatException e) {
                    // Ignore parse exceptions
                }
            }
        }

        return String.valueOf(maxTestfallID + 1);
    }

    /**
     * Filters the Excel sheet based on action and status, returning the Testfall ID and ID.
     *
     * @param action the action to filter by.
     * @param status the status to filter by.
     * @return an array containing the Testfall ID and ID, or null if not found.
     */
    public String[] filterTestfallIdUndId(String action, String status) {
        try (FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
             Workbook workbook = WorkbookFactory.create(inputStream)) {

            Sheet sheet = workbook.getSheet(ExcelTabele);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Skip header row
                Row row = sheet.getRow(i);
                if (row != null) {
                    Cell actionCell = row.getCell(getColumnIndexByName(sheet, "Actions"));
                    Cell statusCell = row.getCell(getColumnIndexByName(sheet, "Status"));
                    if (actionCell != null && statusCell != null && action.equals(actionCell.getStringCellValue())
                            && status.equals(statusCell.getStringCellValue())) {

                        String testfallId = row.getCell(0).toString();
                        String id = row.getCell(getColumnIndexByName(sheet, "Id")).toString();
                        return new String[]{testfallId, id};
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Filters the value in a specified column based on the Testfall ID.
     *
     * @param TestfallID the Testfall ID to filter by.
     * @param Column     the column to retrieve the value from.
     * @return the value from the specified column, or null if not found.
     */
    public String filterColumnValueWithTestfallID(String TestfallID, String Column) {
        try (FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
             Workbook workbook = WorkbookFactory.create(inputStream)) {

            Sheet sheet = workbook.getSheet(ExcelTabele);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Skip header row
                Row row = sheet.getRow(i);
                if (row != null) {
                    Cell TestfallIDCell = row.getCell(getColumnIndexByName(sheet, "Testfall ID"));

                    if (TestfallIDCell != null) {
                        String TestfallIDValue = null;

                        // Handle different cell types
                        if (TestfallIDCell.getCellType() == CellType.STRING) {
                            TestfallIDValue = TestfallIDCell.getStringCellValue();
                        } else if (TestfallIDCell.getCellType() == CellType.NUMERIC) {
                            TestfallIDValue = String.valueOf((long) TestfallIDCell.getNumericCellValue());
                        }

                        // Compare the TestfallID value with the cell value
                        if (TestfallIDValue != null && TestfallID.equals(TestfallIDValue)) {
                            String result = row.getCell(getColumnIndexByName(sheet, Column)).toString();
                            return result;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Updates a cell value based on the Testfall ID.
     *
     * @param testfallId the Testfall ID to search for.
     * @param columnName the column name to update.
     * @param newValue   the new value to set in the specified column.
     * @return true if the cell was successfully updated, false otherwise.
     */
    public boolean updateCellByTestfallId(String testfallId, String columnName, String newValue) {
        try (FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
             Workbook workbook = WorkbookFactory.create(inputStream)) {

            Sheet sheet = workbook.getSheet(ExcelTabele);
            if (sheet == null) {
                System.out.println("Sheet not found.");
                return false;
            }

            int columnIndex = getColumnIndexByName(sheet, columnName);

            boolean isUpdated = false;

            for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Skip header row
                Row row = sheet.getRow(i);
                if (row != null) {
                    Cell cell = row.getCell(0); // Assuming Testfall ID is in the first column

                    if (cell != null) {
                        String cellValue = null;

                        // Handle numeric and string cell types
                        if (cell.getCellType() == CellType.STRING) {
                            cellValue = cell.getStringCellValue().trim();
                        } else if (cell.getCellType() == CellType.NUMERIC) {
                            cellValue = String.valueOf((int) cell.getNumericCellValue()); // Assuming ID is an integer
                        }

                        // Compare with the provided testfallId
                        if (cellValue != null && cellValue.equals(testfallId)) {
                            Cell targetCell = row.getCell(columnIndex);
                            if (targetCell == null) {
                                targetCell = row.createCell(columnIndex);
                            }
                            targetCell.setCellValue(newValue);
                            isUpdated = true;
                            break;
                        }
                    }
                }
            }

            if (isUpdated) {
                try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
                    workbook.write(outputStream);
                    System.out.println("Cell updated successfully.");
                    return true;
                }
            } else {
                System.out.println("Testfall ID not found.");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Writes initialization results to the Excel file in a specified sheet.
     * This method adds a new row with the provided initialization data.
     *
     * @param umgebung             the environment name.
     * @param user                 the user name.
     * @param user_Berechtigung    the user permissions.
     * @param action               the action performed.
     * @param besonderheit         the special feature.
     * @param initialisierungsDatum the initialization date.
     * @param testResult           the result of the initialization test.
     */
    public void Write_Initialisierung_Result(String umgebung, String user, String user_Berechtigung, String action, String besonderheit,
                                             String initialisierungsDatum, String testResult) {
        try (FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
             Workbook workbook = WorkbookFactory.create(inputStream);
             FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {

            Sheet sheet = workbook.getSheet(ExcelTabele);

            // Find the next empty row
            int nextRowNum = sheet.getLastRowNum() + 1;
            Row row = sheet.createRow(nextRowNum);

            // Write the data to the new row
            row.createCell(getColumnIndexByName(sheet, "App")).setCellValue(umgebung);
            row.createCell(getColumnIndexByName(sheet, "Users")).setCellValue(user);
            row.createCell(getColumnIndexByName(sheet, "User Berechtigung")).setCellValue(user_Berechtigung);
            row.createCell(getColumnIndexByName(sheet, "Actions")).setCellValue(action);
            row.createCell(getColumnIndexByName(sheet, "Besonderheit")).setCellValue(besonderheit);
            row.createCell(getColumnIndexByName(sheet, "Datum")).setCellValue(initialisierungsDatum);
            row.createCell(getColumnIndexByName(sheet, "Tests Result")).setCellValue(testResult);

            // Optionally, create a table-like appearance with borders
            createTableAppearance(sheet, nextRowNum);

            // Write the workbook to the output stream
            workbook.write(outputStream);

            System.out.println("Data written to Excel successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Retrieves the column index in a sheet by column name.
     * If the column does not exist, it will create the column at the end of the header row.
     *
     * @param sheet      the Excel sheet to search.
     * @param columnName the column name to find.
     * @return the index of the column with the specified name.
     * @throws IllegalArgumentException if the header row is not found.
     */
    private static int getColumnIndexByName(Sheet sheet, String columnName) {
        Row headerRow = sheet.getRow(1); // Headers are at index 1
        if (headerRow != null) {
            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                Cell cell = headerRow.getCell(i);
                if (cell != null && cell.getCellType() == CellType.STRING) {
                    String cellValue = cell.getStringCellValue().trim(); // Trim spaces
                    if (columnName.equalsIgnoreCase(cellValue)) {
                        return i;
                    }
                }
            }

            // If the column doesn't exist, add it at the end of the header row
            int newColumnIndex = headerRow.getLastCellNum();
            Cell newCell = headerRow.createCell(newColumnIndex);
            newCell.setCellValue(columnName);
            return newColumnIndex;
        } else {
            throw new IllegalArgumentException("Header row not found.");
        }
    }

}
