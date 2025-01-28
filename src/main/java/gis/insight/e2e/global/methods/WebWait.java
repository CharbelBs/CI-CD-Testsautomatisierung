package gis.insight.e2e.global.methods;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Provides methods for waiting on various WebDriver conditions such as element visibility and clickability.
 * This class encapsulates common waiting mechanisms for Selenium WebDriver interactions.
 */
public class WebWait {

    private WebDriver driver;
    private WebDriverWait wait ;// Reduced wait time
    /**
     * Initializes a new instance of the WebWait class.
     *
     * @param driver The WebDriver instance used to interact with the web page.
     */
    public WebWait(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,Duration.ofSeconds(15));
    }

    /**
     * Waits until the specified element or element located by ID becomes invisible.
     *
     * <p>This method supports waiting for both WebElement instances and element IDs (as strings).</p>
     *
     * @param object The WebElement or String (element ID) to wait for invisibility.
     * @throws TimeoutException If the wait times out.
     * @throws IllegalArgumentException If the provided object is neither a String nor a WebElement.
     */
    public void forInvisible(Object object, Integer time) {

        // Create WebDriverWait with the specified timeout
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));

        try {
            if (object instanceof String) {
                // Handle case where object is a String (assuming it's an ID)
                String elementId = (String) object;

                // First, check if the element is present (with a short timeout)
                wait.until(ExpectedConditions.presenceOfElementLocated(By.id(elementId)));

                // Then, wait for the element to become invisible
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(elementId)));

            } else if (object instanceof WebElement) {
                // Handle case where object is a WebElement
                WebElement element = (WebElement) object;

                // First, check if the WebElement is present in the DOM
                wait.until(ExpectedConditions.visibilityOf(element));

                // Then, wait for the WebElement to become invisible
                wait.until(ExpectedConditions.invisibilityOf(element));

            } else {
                throw new IllegalArgumentException("Object must be a String (element ID) or WebElement.");
            }
        } catch (org.openqa.selenium.TimeoutException e) {
            // Handle TimeoutException, for example, log or throw a specific message
            System.out.println("Element either did not appear or did not become invisible in the allotted time.");
            throw e; // Re-throw the exception if needed
        } catch (NoSuchElementException e) {
            // Handle the case where the element is not found in the DOM
            System.out.println("Element not found: " + e.getMessage());
            throw e; // Re-throw or handle accordingly
        }
    }



    /**
     * Waits until the specified element or element located by ID or By locator becomes clickable.
     *
     * <p>This method supports waiting for WebElement instances, element IDs (as strings), and By locators.</p>
     *
     * @param object The WebElement, String (element ID), or By locator to wait for clickability.
     * @throws InterruptedException If the thread is interrupted while waiting.
     * @throws IllegalArgumentException If the provided object is not a String, WebElement, or By locator.
     */
    public void forClickable(Object object) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        if (object instanceof String) {
            // Handle case where object is a String (assuming it's an ID)
            String elementId = (String) object;
            wait.until(ExpectedConditions.elementToBeClickable(By.id(elementId)));
        } else if (object instanceof WebElement) {
            // Handle case where object is a WebElement
            WebElement element = (WebElement) object;
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } else if (object instanceof By) {
            // Handle case where object is a By locator
            By locator = (By) object;
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } else {
            throw new IllegalArgumentException("Object must be a String (element ID), WebElement, or By locator.");
        }
    }

    public void Visible(Object object, Integer time) throws InterruptedException {
        // Create WebDriverWait with the specified timeout
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));

        if (object instanceof String) {
            // Determine the type of locator automatically
            String locator = (String) object;
            By by = inferBy(locator);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } else if (object instanceof WebElement) {
            // Handle case where object is a WebElement
            WebElement element = (WebElement) object;
            wait.until(ExpectedConditions.visibilityOf(element));
        } else if (object instanceof By) {
            // Handle case where object is a By locator
            By locator = (By) object;
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } else {
            // Throw an exception if the object type is unsupported
            throw new IllegalArgumentException("Object must be a String (locator), WebElement, or By locator.");
        }
    }

    // Helper method to infer the By type based on the input string
    public By inferBy(String locator) {
        if (locator.startsWith("//") || locator.startsWith("(")) {
            return By.xpath(locator); // Treat as XPath
        } else if (locator.startsWith(".") || locator.startsWith("#")) {
            return By.cssSelector(locator); // Treat as CSS selector
        } else {
            return By.id(locator); // Default to ID
        }
    }


    /**
     * Tries to click on the specified WebElement up to three times if it is not interactable.
     *
     * <p>This method attempts to click on the element, retrying up to three times with a 3-second delay between retries
     * if the element is not interactable.</p>
     *
     * @param element The WebElement to be clicked.
     * @param driver  The WebDriver instance used to interact with the web page.
     */
    private void retryClick(WebElement element, WebDriver driver) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
                wait.until(ExpectedConditions.elementToBeClickable(element));
                element.click();
                break;  // Exit loop if click is successful
            } catch (ElementNotInteractableException e) {
                attempts++;
                System.out.println("Element not interactable, attempt: " + attempts);
                try {
                    Thread.sleep(3000);  // Wait for 3 seconds before retrying
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }
}
