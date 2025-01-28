package gis.insight.e2e.android.insmax.tests_phasen.phase1_automated.tests;

import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

/**
 * The Login_Test0 class automates the login process for the Insight system
 * using Appium's AndroidDriver and TestNG for execution.
 */
public class Reparatur_erstellen_Eng_Test0 extends BaseTest{

    public Reparatur_erstellen_Eng_Test0() throws IOException, AWTException {
        super();
    }


    @Test(alwaysRun = true)
    public void Login_Test0() throws Exception {

        String Umgebung = "Insight Mobile Android";
        String VorgängerID = "";
        String User = "Insight";
        String User_Berechtigung = "admin";
        String Action = "Reparatur Erstellen Eng";
        String Id = "";
        String Status = "";
        String AngelegtAm = date.getCurrentDate();
        String TestResult = "FAILED";

        try {

            _global.Initialise_Englich();
            _global.select_click_Service_Desk();
            _global.clickAccept();

            _global.click_Repairs();

            _Reparaturen.Reparature_Erstellen_Auto_Eng();

            Id = _Reparaturen.getReapraturNummer();
            softAssert.assertNotEquals(Id,"");

            Status = _Reparaturen.getStatus();
            softAssert.assertNotEquals(Id,"");

            softAssert.assertEquals(_Reparaturen.getReparaturBeschreibung(), "AutoReparaturBeschreibung");
            softAssert.assertEquals(_Reparaturen.getGemeldetVon(), "Insight");

            AngelegtAm = _Reparaturen.getAngelegtAm();
            softAssert.assertTrue(date.getCurrentDate().contains(AngelegtAm));

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
