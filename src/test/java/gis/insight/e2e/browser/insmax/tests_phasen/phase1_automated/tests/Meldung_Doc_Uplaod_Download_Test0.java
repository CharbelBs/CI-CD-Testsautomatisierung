package gis.insight.e2e.browser.insmax.tests_phasen.phase1_automated.tests;

import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;



    public class Meldung_Doc_Uplaod_Download_Test0 extends BaseTest{


        public Meldung_Doc_Uplaod_Download_Test0() throws AWTException, IOException {}

        @Test(alwaysRun = true)
        public void Meldung_DocUplaod_Test0() throws Exception {

            String User = "Insight";
            String Umgebung = "InsMax";
            String Action = "Meldung Doc Upload Download";
            String VorgängerID = "";
            String User_Berechtigung = "Admin";
            String Id = null;
            String Status = "";
            String AngelegtAm = date.getCurrentDate();
            String TestResult = "Test-FAILED";

            try {

                _global.Initialise_Deustch();
                _global.select_click_Service_Desk();
                _global.clickUebernehmen();

                _home.click_Meldung();

                _global.clickFirstListElements();

                Id = _meldung.getNumber();
                Status = _meldung.getStatus();

                _global.UploadDocument("testdata/Photo_TestData.png");
                _global.click_Image();
                _global.click_Schließen();

                _global.UploadDocument("testdata/Doc_TestData.pub");
                _global.click_Document();

                _global.Document_anzeigen();

                TestResult = "Test-PASSED";

            } catch (Throwable t) {  // Catch both Exception and AssertionError
                t.printStackTrace();
                if (t instanceof AssertionError) {TestResult += " ASSERT-FAILED: " + t.getMessage();}
                else {TestResult = "Test-FAILED: " + t.getMessage();}
                throw t;  // Rethrow the exception or error
            } finally {

                DelayedDriver.InsightHomePage();

                excel.Write_Test_Result(
                        "App", Umgebung,
                        "Vorgänger ID", VorgängerID,
                        "Users", User,
                        "User Berechtigung", User_Berechtigung,
                        "Actions", Action,
                        "Id", Id,
                        "Status", Status,
                        "Datum", AngelegtAm,
                        "Tests Result", TestResult,
                        "Test column", "test column"
                    );

            }
        }


    }