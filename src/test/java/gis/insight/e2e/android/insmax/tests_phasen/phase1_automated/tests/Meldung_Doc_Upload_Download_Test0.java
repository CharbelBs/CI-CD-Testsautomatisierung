package gis.insight.e2e.android.insmax.tests_phasen.phase1_automated.tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import java.awt.*;
import java.io.IOException;

/**
 * The Login_Test0 class automates the login process for the Insight system
 * using Appium's AndroidDriver and TestNG for execution.
 */
public class Meldung_Doc_Upload_Download_Test0 extends BaseTest{


    public Meldung_Doc_Upload_Download_Test0() throws IOException, AWTException {
        super();
    }

    @Test(alwaysRun = true)
    public void Doument_Upload_Download_Test() throws Exception {

        String Umgebung = "Insight Mobile Android";
        String VorgängerID = "";
        String User = "Insight";
        String User_Berechtigung = "admin";
        String Action = "Document Upload und Download im Meldung";
        String Id = "";
        String Status = "";
        String AngelegtAm = date.getCurrentDate();
        String TestResult = "FAILED";
        SoftAssert softAssert = new SoftAssert();


        try {


            // Proceed with the test steps
            _global.Initialise_Deustch();
            _global.select_click_Service_Desk();
            _global.clickUebernehmen();

            _global.click_Meldung();

            _global.clickFirstListElements();

            Id = _meldung.getNumber();
            Status = _meldung.getStatus();

            _global.UploadDocumentvonGalerie();
            _global.click_Image();
            _global.click_Schließen();

            TestResult = "Test-PASSED";
            softAssert.assertAll();

        } catch (Throwable t) {
            t.printStackTrace();
            if (t instanceof AssertionError) {
                TestResult += " ASSERT-FAILED: " + t.getMessage();
            } else {
                TestResult = "Test-FAILED: " + t.getMessage();
            }
            throw t;  // Rethrow the exception or error

        } finally {

            DelayedAppiumDriver.InsightHomePage();



            // Write test results to Excel
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