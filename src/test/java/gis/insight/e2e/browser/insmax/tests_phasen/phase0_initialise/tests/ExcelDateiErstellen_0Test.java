package gis.insight.e2e.browser.insmax.tests_phasen.phase0_initialise.tests;

import gis.insight.e2e.excel.methods.Excel_tests_phases;
import gis.insight.e2e.global.methods.DestinationExcelTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

@Test
public class ExcelDateiErstellen_0Test {

    @BeforeSuite
    public void ExcelDateiErstellen_0Test() {

        String destinationTestsergebnisse = DestinationExcelTest.Pfad_Tests_Phasen();
        Excel_tests_phases excel = new Excel_tests_phases(destinationTestsergebnisse, "Tabelle1");

        excel.ExcelDateiErstellen("Excel-Tests-Phasen-Template.xlsx", "Tests-Phasen-Ergebnisse-");

    }
}