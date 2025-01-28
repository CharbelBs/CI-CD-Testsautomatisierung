package gis.insight.e2e.android.insmax.tests_phasen.phase1_automated.tests;

import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

/**
 * The Login_Test0 class automates the login process for the Insight system
 * using Appium's AndroidDriver and TestNG for execution.
 */
public class Reparatur_erstellen_offline_Test0 extends BaseTest{


    public Reparatur_erstellen_offline_Test0() throws IOException, AWTException {
        super();
    }

    @Test(alwaysRun = true)
    public void Login_Test0() throws Exception {
        // Test data and variables
        String User = "Insight";
        String Umgebung = "Insight Mobile Android";
        String Action = "Reparaturen Erstelllen Offline";
        String VorgängerID = "";
        String User_Berechtigung = "Admin";
        String Id = null;
        String Status = null;
        String AngelegtAm = null;
        String TestResult = "FAILED";

        try {


            // Navigate through the application and initialize settings in German
            _global.Initialise_Deustch();
            _global.select_click_Service_Desk();
            _global.clickUebernehmen();

            // Switch the system to offline mode
            _global.Offline_schalten();

            // Navigate to the "click_Reparaturen" section
            _global.click_Reparaturen();

            // Create a new repair order in offline mode
            _Reparaturen.Reparature_Erstellen_Auto();

            // Verify that the repair order has been created with expected details

            Id = _Reparaturen.getReapraturNummer();
            softAssert.assertEquals(Id, "" );

            Status = _Reparaturen.getStatus();
            softAssert.assertEquals(Status, "" );

            AngelegtAm = _Reparaturen.getAngelegtAm();
            softAssert.assertTrue(Objects.equals(AngelegtAm, ""));

            softAssert.assertEquals(_Reparaturen.getReparaturBeschreibung(), "AutoReparaturBeschreibung" );

            softAssert.assertEquals(_Reparaturen.getGemeldetVon(), "Insight" );


            softAssert.assertTrue(url.getCurrentUrl().contains("/NEW"));

            // Navigate back to the main screen
            _global.click_Icon_left();

            softAssert.assertTrue(_global.getListElements_Anzahl() == 1);

            // Go back to the homepage and switch the system back online
            _global.click_Icon_home();
            _global.Online_schalten();

            // Navigate to "click_Reparaturen" again after going online
            _global.click_Reparaturen();


            softAssert.assertTrue(_global.getListElements_Anzahl() > 1 );

            // Select the first element from the list
            _global.clickFirstListElements();

            softAssert.assertFalse(url.getCurrentUrl().contains("/NEW") );

            Id = _Reparaturen.getReapraturNummer();
            softAssert.assertNotEquals(Id, "");

            Status = _Reparaturen.getStatus();
            softAssert.assertNotEquals(Status, "");

            AngelegtAm = _Reparaturen.getAngelegtAm();
            softAssert.assertTrue(date.getCurrentDate().contains(AngelegtAm) );

            // If all assertions pass, mark the test as passed
            TestResult = "Test-PASSED";
            softAssert.assertAll();  // Assert all collected results

        } catch (Throwable t) {  // Catch both Exception and AssertionError
            t.printStackTrace();
            if (t instanceof AssertionError) {TestResult += " ASSERT-FAILED: " + t.getMessage();}
            else {TestResult = "Test-FAILED: " + t.getMessage();}
            throw t;  // Rethrow the exception or error
        }

        finally {

            DelayedAppiumDriver.InsightHomePage();



            excel.Write_Test_Result(
                    "App", Umgebung,
                    "Vorgänger ID", VorgängerID,
                    "Users", User,
                    "User Berechtigung", User_Berechtigung,
                    "Actions", Action,
                    "Id", Id,
                    "Status", Status,
                    "Datum", AngelegtAm,
                    "Tests Result", TestResult
            );


        }
    }
}
