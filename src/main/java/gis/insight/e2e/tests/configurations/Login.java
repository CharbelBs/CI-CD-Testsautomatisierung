	package gis.insight.e2e.tests.configurations;


	import org.openqa.selenium.By;
	import gis.insight.e2e.global.methods.WebWait;
	import io.github.cdimascio.dotenv.Dotenv;
	import java.awt.*;
	import java.io.FileInputStream;
	import java.io.IOException;
	import java.util.Properties;



	public class Login {

		private DelayedWebDriver DelayedDriver;


		public Login(DelayedWebDriver delayedDriver) throws IOException {
			this.DelayedDriver = delayedDriver;

		}


		public void login() throws InterruptedException, IOException {

			// Load the .env file
			Dotenv dotenv = Dotenv.configure()
					.filename("./.env") // Ensure the path to .env is correct
					.load();

			 WebWait wait = new WebWait(DelayedDriver);

			DelayedDriver.findElement(new By.ByXPath("//ion-input[@name=\"username\"]//..//input")).clear();
			DelayedDriver.findElement(new By.ByXPath("//ion-input[@name=\"password\"]//..//input") ).clear();

			DelayedDriver.findElement(new By.ByXPath("//ion-input[@name=\"username\"]//..//input")).sendKeys(dotenv.get("E2E_USERNAME"));
			DelayedDriver.findElement(new By.ByXPath("//ion-input[@name=\"password\"]//..//input") ).sendKeys(dotenv.get("E2E_PASSWORD"));

			DelayedDriver.findElement(new  By.ByXPath("//ion-button[@type=\"submit\"]")).click();
			Thread.sleep(3000);
			wait.Visible(By.cssSelector(".icon-menu"), 15);


		}


		public void login_NW(DelayedWebDriver driver) throws InterruptedException, IOException, AWTException {

			// Load the .env file
			Dotenv dotenv = Dotenv.configure()
					.filename("./.env") // Ensure the path to .env is correct
					.load();

			WebWait wait = new WebWait(driver);

			String originalWindow = driver.getWindowHandle();

			driver.findElement(By.cssSelector("#loginComponent button")).click();

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


		public void login_GitHub() throws InterruptedException, IOException {

			// Load the .env file
			Dotenv dotenv = Dotenv.configure()
					.filename("./.env") // Ensure the path to .env is correct
					.load();

			WebWait wait = new WebWait(DelayedDriver);

			DelayedDriver.findElement(new By.ByXPath("//*[@id='login_field']")).clear();
			DelayedDriver.findElement(new By.ByXPath("//*[@id='login_field']") ).clear();

			DelayedDriver.findElement(new By.ByXPath("//*[@id='login_field']")).sendKeys(dotenv.get("E2E_USERNAME"));
			DelayedDriver.findElement(new By.ByXPath("//*[@id='password']") ).sendKeys(dotenv.get("E2E_PASSWORD"));

			DelayedDriver.findElement(new  By.ByXPath("//input[@type='submit']")).click();
			Thread.sleep(3000);
			wait.Visible(By.cssSelector(".AppHeader"), 15);


		}


	}
