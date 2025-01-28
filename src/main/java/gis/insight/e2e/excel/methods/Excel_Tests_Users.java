package gis.insight.e2e.excel.methods;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.IOException;


import java.io.FileOutputStream;


public class Excel_Tests_Users {

	private  String excelFilePath;
	private  String ExcelTabele;


	public Excel_Tests_Users(String excelFilePath, String ExcelTabele) {

		this.excelFilePath = excelFilePath;
		this.ExcelTabele = ExcelTabele;

	}


	
	    public  void filterUserDescription() throws IOException {
	        // Load the Excel file
	        FileInputStream fileInputStream = new FileInputStream(excelFilePath);
	        Workbook workbook = new XSSFWorkbook(fileInputStream);
	        Sheet sheet = workbook.getSheetAt(0);

	        // Create a new workbook for the filtered data
	        Workbook filteredWorkbook = new XSSFWorkbook();
	        Sheet filteredSheet = filteredWorkbook.createSheet("Filtered Data");

	        int filteredRowIndex = 0;

	        // Iterate through the rows of the sheet
	        for (Row row : sheet) {
	            Cell stepNameCell = row.getCell(5);  // DS_STEP_NAME is in the 6th column (index 5)
	            Cell descriptionCell = row.getCell(6); // DS_DESCRIPTION is in the 7th column (index 6)

	            // Check if DS_STEP_NAME contains "Vorbedingung"
	            if (stepNameCell != null && stepNameCell.getStringCellValue().contains("Vorbedingung")) {
	                // Copy the row to the new sheet
	                Row filteredRow = filteredSheet.createRow(filteredRowIndex++);
	                for (int i = 0; i < row.getLastCellNum(); i++) {
	                    Cell newCell = filteredRow.createCell(i);
	                    Cell originalCell = row.getCell(i);

	                    if (originalCell != null) {
	                        switch (originalCell.getCellType()) {
	                            case STRING:
	                                newCell.setCellValue(originalCell.getStringCellValue());
	                                break;
	                            case NUMERIC:
	                                newCell.setCellValue(originalCell.getNumericCellValue());
	                                break;
	                            case BOOLEAN:
	                                newCell.setCellValue(originalCell.getBooleanCellValue());
	                                break;
	                            default:
	                                newCell.setCellValue(originalCell.toString());
	                        }
	                    }
	                }
	            }
	        }

	        // Write the filtered data to a new Excel file
	        FileOutputStream fileOutputStream = new FileOutputStream("./User-Description");
	        filteredWorkbook.write(fileOutputStream);
	        fileOutputStream.close();

	        // Close the workbooks
	        workbook.close();
	        filteredWorkbook.close();

	        System.out.println("Filtered data written to " + "./User-Description");
	    }




	 public List<String> getUserData() {
	        List<String> users = new ArrayList<>();
	        try (FileInputStream fis = new FileInputStream(excelFilePath);
	             Workbook workbook = new XSSFWorkbook(fis)) {

	            Sheet sheet = workbook.getSheet(ExcelTabele);

	            if (sheet == null) {
	                System.err.println("Sheet not found: " + ExcelTabele);
	                return users;
	            }

	            boolean isHeader = true;
	            for (Row row : sheet) {
	                if (isHeader) {
	                    isHeader = false; // Skip the header row
	                    continue;
	                }

	                Cell cell = row.getCell(0); // Assumes user data is in the first column
	                if (cell != null) {
	                    String cellValue = cell.getStringCellValue();
	                    if (cellValue != null && !cellValue.trim().isEmpty()) {
	                        users.add(cellValue);
	                    }
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return users;
	    }

	
	public static void main(String[] args) {

	}
}
