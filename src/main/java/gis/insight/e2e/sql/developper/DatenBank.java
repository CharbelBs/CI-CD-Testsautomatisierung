package gis.insight.e2e.sql.developper;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DatenBank {

    public DatenBank() {

    }



    /**
     * Executes the given SQL query and returns all values of the specified column.
     *
     * @param sqlQuery   The SQL query string to execute.
     * @return A list of values from the specified column.
     */
    public List<String> executeSqlQuery(String sqlQuery) {

        // Load the .env file
        Dotenv dotenv = Dotenv.configure()
                .filename("./.env") // Ensure the path to .env is correct
                .load();


        List<String> rows = new ArrayList<>();
        String connectionUrl = String.format(
                "jdbc:sqlserver://%s;databaseName=%s;user=%s;password=%s;encrypt=true;trustServerCertificate=true;",
                dotenv.get("E2E_HOST"), dotenv.get("E2E_DATABASE_NAME"), dotenv.get("E2E_SQLUSERNAME"), dotenv.get("E2E_SQLPASSWORD")
        );

        try (Connection conn = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = conn.prepareStatement(sqlQuery);
             ResultSet rs = stmt.executeQuery()) {

            int columnCount = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                StringBuilder row = new StringBuilder();
                for (int i = 1; i <= columnCount; i++) {
                    row.append(rs.getString(i)).append(" | ");
                }
                // Remove trailing " | " and trim the row
                rows.add(row.toString().replaceAll("\\s+\\|\\s*$", "").trim());
            }
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Query result: " + rows);
        return rows;
    }




    /**
     * Fetches all unique values from a specific column in a table.
     *
     * @param tableName  The name of the table to query.
     * @param columnName The column whose values need to be returned.
     * @return A list of unique values in the specified column.
     */
    public List<String> getAllValuesInColumn(String tableName, String columnName) {
        List<String> columnValues = new ArrayList<>();
        String sqlQuery = String.format("SELECT DISTINCT %s FROM %s", columnName, tableName);

        try {
            List<String> rows = executeSqlQuery(sqlQuery); // Get raw rows
            for (String row : rows) {
                // Extract the value of the first column since the query returns one column
                columnValues.add(row.split("\\|")[0].trim());
            }
        } catch (Exception e) {
            System.err.println("Error fetching values from column: " + e.getMessage());
            e.printStackTrace();
        }

        return columnValues;
    }


    /**
     * Main method to test the DBeaver class functionality.
     */
    public static void main(String[] args) {

        // Create a DBeaver instance
        DatenBank db = new DatenBank();

        String sqlQuery = """
            SELECT bundleWorkplanDesignation, taskWorkplanDesignation, materialDesignation
            FROM business_suite.dbo.Component
            WHERE site = 'Block 2'
            AND workplanDesignation = 'SD'
            AND systemID = '20JRC';
        """;

        List<String> results = db.executeSqlQuery(sqlQuery);

        // Step 3: Process results
        Set<String> uniqueResultsSet = new HashSet<>(results);
        String AnzahlSQL = String.valueOf(uniqueResultsSet.size());
        System.out.println(AnzahlSQL);

        // Example: Fetch all unique values from a column
        String tableName = "business_suite.dbo.Component";
        String columnName = "materialDesignation";

        // Get all unique values in the column
        List<String> values = db.getAllValuesInColumn(tableName, columnName);

        // Print the results
        System.out.println("Unique values in the column '" + columnName + "':");
        for (String value : values) {
            System.out.println(value);
        }

        sqlQuery = """
                SELECT sum(rr.workingHoursDetail)
                    FROM business_suite.dbo.ResourceRequest rr
                    JOIN business_suite.dbo.Activity a
                    ON rr.locationId = a.locationId
                        AND rr.workplanId = a.workplanId
                    WHERE rr.locationId = 'M-TRb-2N504'
                        AND rr.fieldId = 'AUFW-ST'
                        AND a.parent LIKE '%61%';  
            """;

        List<String> sqlResults = db.executeSqlQuery(sqlQuery);

        if (sqlResults.isEmpty()) {
            throw new AssertionError("No result returned from SQL query.");
        }

        // Assuming the SQL result is a single value for the sum
        String mahSql = sqlResults.get(0);
        System.out.println(mahSql);
    }
}
