package gis.insight.e2e.global.methods;

import gis.insight.e2e.tests.configurations.DelayedWebDriver;
import java.io.File;
import com.sun.jna.platform.win32.KnownFolders;
import com.sun.jna.platform.win32.Shell32Util;

public class DownloadChecker {

    private DelayedWebDriver driver;

    // Constructor to initialize WebDriver
    public DownloadChecker(DelayedWebDriver driver) {
        this.driver = driver;
    }

    public String getDownloadsFolderPath() {
        return Shell32Util.getKnownFolderPath(KnownFolders.FOLDERID_Downloads);
    }

    // Method to check if the file has been downloaded successfully and delete it afterwards
    public boolean Download_Check_Delete(String fileName) throws InterruptedException {
        // Set the download directory explicitly
        String downloadDir = System.getProperty("user.home") + "\\Downloads";

        File downloadDirectory = new File(downloadDir);
        System.out.println("Checking directory: " + downloadDirectory.getAbsolutePath());
        System.out.println("Looking for file: " + fileName);
        int attempts = 0;

        // Poll the download directory for the file until the timeout is reached
        while (attempts < 6) {
            File[] dirContents = downloadDirectory.listFiles();

            if (dirContents != null) {

                for (File file : dirContents) {


                    if (file.getName().equalsIgnoreCase(fileName)) {
                        // File found, now attempt to delete it
                        System.out.println("File " + fileName + " found, attempting to delete.");
                        Thread.sleep(2000);  // Small delay to avoid file locks

                        boolean isDeleted = file.delete();
                        if (isDeleted) {
                            System.out.println("File " + fileName + " was successfully deleted.");
                            return true;
                        } else {
                            System.out.println("Failed to delete the file " + fileName);
                            file.deleteOnExit(); // Attempt to delete on exit if normal deletion fails
                            return false;
                        }
                    }
                }
            } else {
                System.out.println("Directory is empty or inaccessible.");
            }

            attempts++;
            Thread.sleep(1000); // Wait 1 second before checking again
        }

        System.out.println("File " + fileName + " not found within the timeout period.");
        return false;
    }


}
