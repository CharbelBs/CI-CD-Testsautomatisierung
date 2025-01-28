package gis.insight.e2e.tests.configurations;


import java.time.Duration;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SetUp {

    public DelayedWebDriver driver;
    public String WebLink = "";

    public SetUp() {}

    public DelayedWebDriver SetDriver() {

        // Load the .env file
        Dotenv dotenv = Dotenv.configure()
                .filename("./.env") // Ensure the path to .env is correct
                .load();

        WebLink = dotenv.get("E2E_WEBLINK");
        String headlessMode = dotenv.get("E2E_HEADLESSMODE");

        System.setProperty("webdriver.edge.driver", "./drivers/msedgedriver.exe");

        EdgeOptions options = new EdgeOptions();
        if ("true".equalsIgnoreCase(headlessMode)) {
            options.addArguments("headless");
            options.addArguments("disable-gpu");
        } else {
            options.addArguments("no-headless");
        }
        options.addArguments("--lang=de");



        options.setExperimentalOption("prefs", Map.of(
                "profile.default_content_setting_values.media_stream_camera", 1,
                "translate.enabled", false
        ));

        WebDriver rawDriver = new EdgeDriver(options);
        this.driver = new DelayedWebDriver(rawDriver, 1000);

        driver.get(WebLink); // Navigate to initiate domain for cookies
        // loadCookies(driver);  // Load cookies if available to bypass login

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return driver;
    }


    public DelayedWebDriver SetDriver_Docker() {

        // Load the .env file
        Dotenv dotenv = Dotenv.configure()
                .filename("./.env") // Ensure the path to .env is correct
                .load();

        WebLink = dotenv.get("E2E_WEBLINK");

        // Set ChromeDriver path from environment variable or fallback to default
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROMEDRIVER_PATH") != null
                ? System.getenv("CHROMEDRIVER_PATH")
                : "/usr/bin/chromedriver");

        ChromeOptions options = new ChromeOptions();

        // Additional Chrome options
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--start-maximized");
        options.addArguments("--use-fake-ui-for-media-stream");
        options.addArguments("--use-fake-device-for-media-stream");
        options.addArguments("--lang=de");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--headless=new");



        options.setExperimentalOption("prefs", Map.of(
                "profile.default_content_setting_values.media_stream_camera", 1,
                "translate.enabled", false
        ));

        WebDriver rawDriver = new ChromeDriver(options);
        this.driver = new DelayedWebDriver(rawDriver, 1000);

        driver.get(WebLink); // Navigate to the webLink to initiate cookies
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));


        return driver;
    }

}
