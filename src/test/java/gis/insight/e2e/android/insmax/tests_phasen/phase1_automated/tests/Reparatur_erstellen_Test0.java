package gis.insight.e2e.android.insmax.tests_phasen.phase1_automated.tests;

import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

/**
 * The Login_Test0 class automates the login process for the Insight system
 * using Appium's AndroidDriver and TestNG for execution.
 */
public class Reparatur_erstellen_Test0 extends BaseTest{


    public Reparatur_erstellen_Test0() throws IOException, AWTException {
        super();
    }


    @Test(alwaysRun = true)
    public void Login_Test0() throws Exception {

        String Umgebung = "Insight Mobile Android";
        String VorgängerID = "";
        String User = "Insight";
        String User_Berechtigung = "admin";
        String Action = "Reparatur Erstellen";
        String Id = "";
        String Status = "";
        String AngelegtAm = date.getCurrentDate();
        String TestResult = "FAILED";

        try {


            _global.Initialise_Deustch();
            _global.select_click_Service_Desk();
            _global.clickUebernehmen();

            _global.click_Reparaturen();

            _Reparaturen.Reparature_Erstellen_Auto();

            // Werte für Assertions abrufen
            Id = _Reparaturen.getReapraturNummer();
            Status = _Reparaturen.getStatus();
            User = _Reparaturen.getGemeldetVon();
            String description = _Reparaturen.getReparaturBeschreibung();

            // Assertions
            softAssert.assertNotEquals(Id, "", "Reparaturnummer sollte nicht leer sein.");

            softAssert.assertEquals(Status, "10-WAPPR", "Status sollte 10-WAPPR sein.");

            softAssert.assertEquals(description, "AutoReparaturBeschreibung", "Description stimmt nicht überein.");

            softAssert.assertEquals(User, "Insight", "Reported By stimmt nicht überein.");

            softAssert.assertEquals(AngelegtAm, _Reparaturen.getAngelegtAm(), "Erstellungsdatum stimmt nicht überein.");

            TestResult = "Test-PASSED";
            softAssert.assertAll();

        } catch (Throwable t) {  // Catch both Exception and AssertionError
            t.printStackTrace();
            if (t instanceof AssertionError) {TestResult += " ASSERT-FAILED: " + t.getMessage();}
            else {TestResult = "Test-FAILED: " + t.getMessage();}
            throw t;  // Rethrow the exception or error
        }

        finally {

            DelayedAppiumDriver.InsightHomePage();

            // Testresultate in Excel schreiben
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
