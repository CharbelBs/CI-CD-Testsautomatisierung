package gis.insight.e2e.global.methods;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class provides methods for generating file paths for test results Excel files.
 * The file paths include the current date in the format "YYYY-MM-DD".
 */
public class DestinationExcelTest {

    /**
     * Generates a file path for "Tests-Phasen-Ergebnisse" Excel file, including the current date.
     *
     * @return the file path as a string in the format "./results/Tests-Phasen-Ergebnisse-YYYY-MM-DD.xlsx".
     */
    public static String Pfad_Tests_Phasen() {
        // Get the current date
        LocalDate currentDate = LocalDate.now();
        
        // Format the date as "YYYY-MM-DD"
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyMMdd");
        String formattedDate = currentDate.format(dateFormatter);
        
        // Construct the destination file path with the date included
        return "./results/Tests-Phasen-Ergebnisse-" + formattedDate + ".xlsx";
    }

    /**
     * Generates a file path for "Tests-Intialisierung-Ergebnisse" Excel file, including the current date.
     *
     * @return the file path as a string in the format "./Tests-Ergebnisse/Tests-Intialisierung-Ergebnisse-YYYY-MM-DD.xlsx".
     */
    public static String Pfad_Tests_Initalisierung() {
        // Get the current date
        LocalDate currentDate = LocalDate.now();
       
        // Format the date as "YYYY-MM-DD"
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(dateFormatter);
       
        // Construct the destination file path with the date included
        return "./results/Tests-Intialisierung-Ergebnisse-" + formattedDate + ".xlsx";
    }
}
