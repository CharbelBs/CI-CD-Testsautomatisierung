package gis.insight.e2e.browser.github.tests_phasen.phase1_automated.tests;

import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;



    public class ProfileBioUpdate_Test0 extends BaseTest {


        public ProfileBioUpdate_Test0() throws AWTException, IOException {}

        @Test(alwaysRun = true)
        public void ProfileBioUpdate_Test0() throws Exception {

            String User = "CharbelBs";
            String Umgebung = "GitHub";
            String Action = "Profile Bio Update";
            String VorgängerID = "";
            String User_Berechtigung = "Admin";
            String Id = null;
            String Status = "";
            String AngelegtAm = date.getCurrentDate();
            String TestResult = "Test-FAILED";

            try {

            _global.openUserMenu();
            _global.spanClick("Settings");

            softAssert.assertEquals(_global.getName(),"Charbel Bsaibess");

            _global.setBio("TestBio");

            _global.clickButtonWithText("Update profile");

            _global.waitForNamePresent("Profile updated successfully",2);
            softAssert.assertEquals(_global.getBio(),"TestBio");


            _global.setBio("");

            _global.clickButtonWithText("Update profile");

            _global.waitForNamePresent("Profile updated successfully",2);
            softAssert.assertEquals(_global.getBio(),"");


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