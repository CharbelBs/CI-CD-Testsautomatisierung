package gis.insight.e2e.android.insmax.tests_phasen.phase1_automated.tests;

import gis.insight.e2e.excel.methods.Excel_tests_phases;
import gis.insight.e2e.global.methods.Date;
import gis.insight.e2e.global.methods.DestinationExcelTest;
import gis.insight.e2e.global.methods.URL;
import gis.insight.e2e.global.page_objects.GlobalInsMax_Mob;
import gis.insight.e2e.android.insmax.page_objects.*;
import gis.insight.e2e.global.page_objects.HomePageInsMax_Mob;
import gis.insight.e2e.tests.configurations.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class BaseTest {

    protected static DelayedWebAppiumDriver DelayedAppiumDriver;
    protected static HomePageInsMax_Mob _home;
    protected static GlobalInsMax_Mob _global;
    protected static Reparaturen _Reparaturen;
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


        if (DelayedAppiumDriver == null) {
            // Detect environment: Windows or Docker
            boolean isDocker = System.getenv("RUN_IN_DOCKER") != null && System.getenv("RUN_IN_DOCKER").equalsIgnoreCase("true");

            if (!isDocker) {

                excel.CloseExcelProcess();

                DelayedAppiumDriver = SetUp_Mob.SetMobileDriver();

                Login_Mob loginMob = new Login_Mob(DelayedAppiumDriver);
                loginMob.setServer("https://insmax.cloud.rodias.de/");
                loginMob.login();

                System.out.println("Running in Windows environment.");
            }

            else {
                System.out.println("Running in Docker environment.");
            }

        }

        _home = new HomePageInsMax_Mob(DelayedAppiumDriver);
        _global = new GlobalInsMax_Mob(DelayedAppiumDriver);
        _Reparaturen = new Reparaturen(DelayedAppiumDriver);
        _meldung = new Meldung(DelayedAppiumDriver);


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

        DelayedAppiumDriver.quit();

        if (DelayedAppiumDriver != null) {
            DelayedAppiumDriver.quit();  // Close the browser after all tests
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
