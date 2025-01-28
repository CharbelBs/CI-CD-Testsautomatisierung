package gis.insight.e2e.global.methods;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class provides methods for handling date and time operations, including
 * getting the current date in a specific format and comparing two dates with different formats.
 */
public class Date {

    /**
     * Constructor for the Date class. Initializes a new instance of the Date class.
     */
    public Date() {
    }

    /**
     * Gets the current date and time formatted for 'datetime-local' input.
     *
     * @return the current date and time as a string in the format "yyyy-MM-dd'T'HH:mm".
     */
    public String getCurrentDate() {
        // Define the formatter for 'datetime-local' input
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Format the current date and time to the required format
        String currentDate = now.format(formatter);

        return currentDate.split("T")[0];
    }

    /**
     * Compares two date strings in different formats to determine if they represent the same date and hour.
     * Minutes, seconds, and nanoseconds are ignored during comparison.
     *
     * @param YYYYMMDD the date string in the format "yyyy-MM-dd HH:mm".
     * @param DDMMYY   the date string in the format "dd.MM.yy HH:mm".
     * @return true if the dates match (ignoring minutes), false otherwise.
     */
    public boolean ComparatorDate(String YYYYMMDD, String DDMMYY) {
        // Check for null or empty inputs
        if (YYYYMMDD == null || DDMMYY == null || YYYYMMDD.isEmpty() || DDMMYY.isEmpty()) {
            System.err.println("Error: One of the date strings is null or empty.");
            return false;
        }

        // Define formatters that match the input date formats
        DateTimeFormatter expectedFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter actualFormatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

        try {
            // Parse the dates using their respective formatters
            LocalDateTime expectedDateTime = LocalDateTime.parse(YYYYMMDD, expectedFormatter);
            LocalDateTime actualDateTime = LocalDateTime.parse(DDMMYY, actualFormatter);

            // Normalize both dates to ignore minutes by setting minutes to 00
            LocalDateTime normalizedExpectedDateTime = expectedDateTime.withMinute(0).withSecond(0).withNano(0);
            LocalDateTime normalizedActualDateTime = actualDateTime.withMinute(0).withSecond(0).withNano(0);

            // Compare the normalized dates
            return normalizedExpectedDateTime.equals(normalizedActualDateTime);
        } catch (DateTimeParseException e) {
            System.err.println("Error parsing date: " + e.getMessage());
            return false;
        }
    }

    /**
     * The main method for testing the date comparison functionality.
     *
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        Date dateUtils = new Date();

        // Example test cases
        boolean result1 = dateUtils.ComparatorDate("2024-08-19 13:58", "19.08.24 13:15");
        boolean result2 = dateUtils.ComparatorDate("2024-08-20 16:54", "20.08.24 16:54");

        System.out.println("Result 1: " + result1); // Expected: true (same date and hour, minutes ignored)
        System.out.println("Result 2: " + result2); // Expected: true (same date and hour, minutes ignored)
    }
}
