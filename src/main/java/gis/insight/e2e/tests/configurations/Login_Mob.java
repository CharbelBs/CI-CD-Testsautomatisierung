package gis.insight.e2e.tests.configurations;


import gis.insight.e2e.global.methods.WebWait;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.SupportsContextSwitching;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.io.IOException;
import java.util.List;


public class Login_Mob {

	AppiumDriver driver;

    public Login_Mob(DelayedWebAppiumDriver delayedDriver) throws IOException {
                this.driver = delayedDriver;
    }


	public void setServer(String Server) throws InterruptedException, IOException {

		WebWait wait = new WebWait(driver);

		// Cast driver to SupportsContextSwitching if needed
		SupportsContextSwitching contextDriver = (SupportsContextSwitching) driver;

		// Use findElements() which returns a list
		List<WebElement> serverInputs = driver.findElements(By.xpath("//ion-input[@name='server']//..//input"));

        // Check if the list is not empty and then proceed
		if (!serverInputs.isEmpty()) {
			WebElement serverInput = serverInputs.get(0); // Get the first element from the list
			if (serverInput.isDisplayed()) {
				serverInput.sendKeys(Server);
				driver.findElement(By.xpath("//ion-card-content/ion-button")).click();

			}
		}
	}


	public void login() throws InterruptedException, IOException {

		// Load the .env file
		Dotenv dotenv = Dotenv.configure()
				.filename("./.env") // Ensure the path to .env is correct
				.load();

		WebWait wait = new WebWait(driver);

		// Wait for username input to become visible using By locator
		wait.forClickable(By.xpath("//ion-input[@name=\"username\"]//..//input"));

		// Username and password input
		driver.findElement(By.xpath("//ion-input[@name=\"username\"]//..//input")).clear();
		driver.findElement(By.xpath("//ion-input[@name=\"username\"]//..//input"))
				.sendKeys(dotenv.get("E2E_USERNAME"));

		driver.findElement(By.xpath("//ion-input[@name=\"password\"]//..//input")).clear();
		driver.findElement(By.xpath("//ion-input[@name=\"password\"]//..//input"))
				.sendKeys(dotenv.get("E2E_PASSWORD"));

		// Login button click
		driver.findElement(By.xpath("//ion-card-content/ion-button")).click();

		Thread.sleep(3000);
		wait.forClickable(By.cssSelector(".icon-ellipsis"));
	}

	public void login_InsDev() throws InterruptedException, IOException, AWTException {

		// Load the .env file
		Dotenv dotenv = Dotenv.configure()
				.filename("./.env") // Ensure the path to .env is correct
				.load();

		WebWait wait = new WebWait(driver);

		String originalWindow = driver.getWindowHandle();

		wait.forClickable(By.cssSelector("ion-button.ion-color-primary"));
		driver.findElement(By.cssSelector("ion-button.ion-color-primary")).click();

		Thread.sleep(3000);

		// Loop through until we find a new window handle
		for (String windowHandle : driver.getWindowHandles()) {
			if (!originalWindow.contentEquals(windowHandle)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}

		driver.findElement(By.id("username")).sendKeys(dotenv.get("E2E_USERNAME"));
		driver.findElement(By.id("password")).sendKeys(dotenv.get("E2E_PASSWORD"));
		driver.findElement(By.id("kc-form-buttons")).click();
		Thread.sleep(3000);

		driver.switchTo().window(originalWindow);
		wait.Visible(By.cssSelector(".icon-menu"), 15);


	}
	
	
	
}
