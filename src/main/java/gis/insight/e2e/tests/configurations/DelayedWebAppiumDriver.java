package gis.insight.e2e.tests.configurations;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.SupportsContextSwitching;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * The DelayedWebDriver class is a decorator for the WebDriver interface that adds a delay after
 * each action. This is useful for debugging purposes or to slow down test execution when working
 * with systems that might need more time to process commands.
 *
 * <p>This class also provides additional utility methods for common WebDriver operations,
 * such as waiting for elements to become clickable and handling dropdowns.</p>
 */
public class DelayedWebAppiumDriver extends AndroidDriver implements SupportsContextSwitching {

    private long delayInMillis;
    private final WebDriverWait wait;

    /**
     * Constructor for the DelayedWebDriver class.
     *
     * @param remoteAddress the URL of the Appium server
     * @param options       the UiAutomator2Options instance for configuring capabilities
     * @param delayInMillis the delay in milliseconds to wait after each WebDriver action.
     */
    public DelayedWebAppiumDriver(URL remoteAddress, UiAutomator2Options options, long delayInMillis) {
        super(remoteAddress, options);  // Use options directly
        this.delayInMillis = delayInMillis;
        this.wait = new WebDriverWait(this, Duration.ofSeconds(delayInMillis));  // Initialize with 'this'
    }


    /**
     * Sets a new delay value.
     *
     * @param delayInMillis new delay value in milliseconds
     */
    public void setDelayInMillis(long delayInMillis) {
        this.delayInMillis = delayInMillis;
    }

    /**
     * Gets the current delay value.
     *
     * @return delay in milliseconds
     */
    public long getDelayInMillis() {
        return this.delayInMillis;
    }

    /**
     * Pauses execution for the specified delay time.
     */
    private void delay() {
        try {
            Thread.sleep(delayInMillis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    @Override
    public void get(String url) {
        super.get(url);  // Use 'super' to call the parent class method
        delay();
    }


    @Override
    public String getCurrentUrl() {
        delay();
        return super.getCurrentUrl();  // Use 'super' to call the parent class method
    }

    @Override
    public String getTitle() {
        delay();
        return super.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        delay();
        return super.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        delay();
        return super.findElement(by);
    }

    @Override
    public String getPageSource() {
        delay();
        return super.getPageSource();
    }

    @Override
    public void close() {
        delay();
        super.close();
    }

    @Override
    public void quit() {
        delay();
        super.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        delay();
        return super.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        delay();
        return super.getWindowHandle();
    }

    public void scrollToElement(DelayedWebAppiumDriver delayedWebAppiumDriver, WebElement element) {
        WebDriver driver = delayedWebAppiumDriver;

        // Sicherstellen, dass der Treiber eine Instanz von JavascriptExecutor ist
        if (driver instanceof JavascriptExecutor) {
            try {
                // Verwende das mobile Scroll-Skript mit 'toVisible'
                HashMap<String, Object> params = new HashMap<>();
                if (element instanceof RemoteWebElement) {
                    params.put("element", ((RemoteWebElement) element).getId());
                }
                params.put("toVisible", true);

                // Führe das mobile Scroll-Skript aus
                ((JavascriptExecutor) driver).executeScript("mobile: scroll", params);

                System.out.println("Successfully scrolled to the element using 'mobile: scroll'.");
            } catch (Exception e) {

                // Fallback: Verwende JavaScript scrollIntoView, wenn 'mobile: scroll' fehlschlägt
                try {
                    ((JavascriptExecutor) delayedWebAppiumDriver).executeScript("arguments[0].scrollIntoView(true);", element);
                    System.out.println("Successfully scrolled to the element using 'scrollIntoView'.");
                } catch (Exception fallbackException) {
                    System.err.println("Failed to scroll using 'scrollIntoView'.");
                    fallbackException.printStackTrace();
                    throw fallbackException;  // Wirf die Ausnahme erneut, falls beide Methoden fehlschlagen
                }
            }
        } else {
            throw new IllegalStateException("This driver does not support JavaScript execution.");
        }
    }






    @Override
    public TargetLocator switchTo() {
        delay();
        return super.switchTo();
    }

    @Override
    public  Navigation navigate() {
        delay();
        return super.navigate();
    }

    @Override
    public Options manage() {
        delay();
        return super.manage();
    }

    /**
     * Clicks on a WebElement after waiting for it to be clickable.
     * Retries up to three times if a StaleElementReferenceException occurs.
     *
     * @param element the WebElement to click
     */
    public void click(WebElement element) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                attempts++;
                WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
                clickableElement.click();
                delay();  // Add delay after click
                return;
            } catch (StaleElementReferenceException e) {
                attempts++;
                System.out.println("StaleElementReferenceException encountered. Retrying... Attempt " + attempts);
                delay(); // Optional: short delay before retrying
            } catch (ElementClickInterceptedException e) {
                System.out.println("Element click intercepted, attempting JavaScript click as a fallback");
                executeJavaScriptClick(element);
                return;
            }
            attempts++;
        }

        throw new RuntimeException("Failed to click element after multiple attempts.");
    }

    public String getAttributeUsingJS(WebElement element, String attribute) {
        try {
            return (String) this.executeScript("return arguments[0]." + attribute + ";", element);
        } catch (Exception e) {
            System.err.println("Failed to retrieve attribute '" + attribute + "' using JavaScript: " + e.getMessage());
            return null;
        }
    }


    // Other utility methods using 'super' to refer to the parent class methods

    public void executeJavaScriptClick(WebElement element) {
        this.executeScript("arguments[0].click();", element);  // Use 'this' to refer to executeScript in this class
    }

    public void InsightHomePage() throws InterruptedException {
        this.executeScript("history.replaceState(null, null, document.location.pathname + '#/app/home'); \n" +
                "  location.reload(); ");

        Thread.sleep(5000);

    }


}
