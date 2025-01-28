package gis.insight.e2e.browser.insmax.tests_phasen.phase1_automated.tests;

import gis.insight.e2e.browser.insmax.page_objects.Meldung;
import gis.insight.e2e.excel.methods.Excel_tests_phases;
import gis.insight.e2e.global.methods.Date;
import gis.insight.e2e.global.methods.DestinationExcelTest;
import gis.insight.e2e.global.methods.URL;
import gis.insight.e2e.global.page_objects.GlobalInsMax;
import gis.insight.e2e.global.page_objects.HomePageInsMax;
import gis.insight.e2e.tests.configurations.DelayedWebDriver;
import gis.insight.e2e.tests.configurations.Login;
import gis.insight.e2e.tests.configurations.SetUp;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class BaseTest {

    protected static DelayedWebDriver DelayedDriver;
    protected static GlobalInsMax _global;
    protected static HomePageInsMax _home;
    protected static Meldung _meldung;
    protected static URL url ;

    public SoftAssert softAssert = new SoftAssert();
    public Date date = new Date();

    public static String destinationTestsergebnisse = DestinationExcelTest.Pfad_Tests_Phasen();
    public static Excel_tests_phases excel = new Excel_tests_phases(destinationTestsergebnisse, "Tabelle1");


    public BaseTest() throws IOException, AWTException {
    }

    @BeforeSuite(alwaysRun = true)  // Change to @BeforeSuite for single setup across the entire suite
    public void setupAndLoginTest() throws IOException, InterruptedException, AWTException {


        if (DelayedDriver == null) {
            // Detect environment: Windows or Docker
            boolean isDocker = System.getenv("RUN_IN_DOCKER") != null && System.getenv("RUN_IN_DOCKER").equalsIgnoreCase("true");

            if (!isDocker) {

                excel.CloseExcelProcess();

                DelayedDriver = new SetUp().SetDriver(); // Windows environment
                System.out.println("Running in Windows environment.");
            } else {
                DelayedDriver = new SetUp().SetDriver_Docker(); // Docker environment
                System.out.println("Running in Docker environment.");
            }


            Login lg = new Login(DelayedDriver);
            lg.login();
            System.out.println("Session initialized and cookies saved.");
        }

        _global = new GlobalInsMax(DelayedDriver);
        _home = new HomePageInsMax(DelayedDriver);
        _meldung = new Meldung(DelayedDriver);
        url = new URL(DelayedDriver);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownSuite() throws InterruptedException {

        System.out.println("Executing @AfterSuite method.");

        // Detect environment: Windows or Docker
        boolean isDocker = System.getenv("RUN_IN_DOCKER") != null && System.getenv("RUN_IN_DOCKER").equalsIgnoreCase("true");

        if (!isDocker) {
            openExcelFile(destinationTestsergebnisse);
        } else {

        }

        DelayedDriver.quit();

        if (DelayedDriver != null) {
            DelayedDriver.quit();  // Close the browser after all tests
        }
    }

    private void openExcelFile(String filePath) {
        try {
            File file = new File(filePath);
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

}
