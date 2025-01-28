package gis.insight.e2e.global.methods;

import org.openqa.selenium.*;
import gis.insight.e2e.tests.configurations.DelayedWebDriver;

public class URL {

    private DelayedWebDriver delayedWebDriver;

    public URL(DelayedWebDriver driver){
      this.delayedWebDriver = driver;

}


    public boolean testNewWindowOpened(WebElement elementToClick) throws InterruptedException {
        // Store the original window handle
        String originalWindow = delayedWebDriver.getWindowHandle();

        // Perform the action that opens a new window
        elementToClick.click();
        Thread.sleep(3000); // Allow some time for the new window to open

        // Loop through all open window handles and find the new one
        for (String windowHandle : delayedWebDriver.getWindowHandles()) {
            if (!originalWindow.equals(windowHandle)) {
                // Switch to the new window
                delayedWebDriver.switchTo().window(windowHandle);
                System.out.println("New window opened and switched to it.");

                // Close the new window and return to the original window
                delayedWebDriver.close();
                System.out.println("Closed the new window.");

                // Switch back to the original window and return success
                delayedWebDriver.switchTo().window(originalWindow);
                System.out.println("Switched back to the original window.");
                return true;
            }
        }

        // If no new window was found, return to the original window
        System.out.println("No new window was opened.");
        delayedWebDriver.switchTo().window(originalWindow);
        return false;
    }



    public String getCurrentUrl() {
        return delayedWebDriver.getCurrentUrl();
    }


}
