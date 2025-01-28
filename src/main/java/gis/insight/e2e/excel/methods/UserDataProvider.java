package gis.insight.e2e.excel.methods;

import org.testng.annotations.DataProvider;

import java.util.List;

public class UserDataProvider {

    
     private static Excel_Tests_Users excelUsers = new Excel_Tests_Users("", "Tabelle1");
	 
    @DataProvider(name = "userData", parallel = false)
    public static Object[][] getUsers() {
        List<String> users = excelUsers.getUserData();
        Object[][] data = new Object[users.size()][1];
        for (int i = 0; i < users.size(); i++) {
            data[i][0] = users.get(i);
        }
        return data;
    }
}
