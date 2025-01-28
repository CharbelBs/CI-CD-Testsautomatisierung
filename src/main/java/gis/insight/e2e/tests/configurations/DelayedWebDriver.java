package gis.insight.e2e.tests.configurations;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.devtools.DevTools;

/**
 * The DelayedWebDriver class is a decorator for the WebDriver interface that adds a delay after
 * each action. This is useful for debugging purposes or to slow down test execution when working
 * with systems that might need more time to process commands.
 *
 * <p>This class also provides additional utility methods for common WebDriver operations,
 * such as waiting for elements to become clickable and handling dropdowns.</p>
 */
public class DelayedWebDriver implements WebDriver, JavascriptExecutor {

    private final WebDriver driver;
    private long delayInMillis;
    private final WebDriverWait wait;

    /**
     * Constructor for the DelayedWebDriver class.
     *
     * @param driver       the WebDriver instance to which this class adds delay functionality.
     * @param delayInMillis the delay in milliseconds to wait after each WebDriver action.
     */
    public DelayedWebDriver(WebDriver driver, long delayInMillis) {
        this.driver = driver;
        this.delayInMillis = delayInMillis;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Now uses Duration for timeout
    }

    // Method to dynamically change the delay
    public void setDelayInMillis(long delayInMillis) {
        this.delayInMillis = delayInMillis;
    }

    public long getDelayInMillis() {
        return this.delayInMillis;
    }

    // Method to return the raw WebDriver instance
    public WebDriver getRawDriver() {
        return this.driver;
    }


    public DevTools getDevTools() {
        if (driver instanceof ChromeDriver) {
            return ((ChromeDriver) driver).getDevTools();
        } else {
            throw new UnsupportedOperationException("DevTools is only supported for ChromeDriver");
        }
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

    /**
     * Waits for an element to be present in the DOM.
     *
     * @param by the locator used to find the element.
     * @return the WebElement once it is present.
     */
    private WebElement waitForElement(By by) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    /**
     * Waits for an element to be clickable.
     *
     * @param by the locator used to find the element.
     * @return the WebElement once it is clickable.
     */
    private WebElement waitForElementToBeClickable(By by) {
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    /**
     * Waits for a WebElement to be clickable.
     *
     * @param element the WebElement to wait for.
     * @return the WebElement once it is clickable.
     */
    public WebElement waitForElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    @Override
    public void get(String url) {
        driver.get(url);
        delay();
    }

    @Override
    public String getCurrentUrl() {
        delay();
        return driver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        delay();
        return driver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        delay();
        return driver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        delay();
        return driver.findElement(by);
    }

    @Override
    public String getPageSource() {
        delay();
        return driver.getPageSource();
    }

    @Override
    public void close() {
        delay();
        driver.close();
    }

    @Override
    public void quit() {
        delay();
        driver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        delay();
        return driver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        delay();
        return driver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        delay();
        return driver.switchTo();
    }

    @Override
    public Navigation navigate() {
        delay();
        return driver.navigate();
    }

    @Override
    public Options manage() {
        delay();
        return driver.manage();
    }

    /**
     * Clicks on the specified WebElement after waiting for it to be clickable.
     * Retries up to three times if a StaleElementReferenceException occurs.
     *
     * @param element the WebElement to click.
     */
    public void click(WebElement element) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                WebElement clickableElement = waitForElementToBeClickable(element);
                clickableElement.click();
                return; // Exit if click is successful
            } catch (StaleElementReferenceException e) {
                attempts++;
                System.out.println("StaleElementReferenceException encountered. Retrying... Attempt " + attempts);
                delay(); // Optional: short delay before retrying
            } catch (ElementClickInterceptedException e) {
                System.out.println("Element click intercepted, attempting JavaScript click as a fallback");
                executeJavaScriptClick(element);
                return;
            }
        }
        throw new RuntimeException("Failed to click element after multiple attempts.");
    }

    /**
     * Sends the specified keys to the given WebElement after waiting for it to be clickable.
     *
     * @param element    the WebElement to send keys to.
     * @param keysToSend the keys to send to the element.
     */
    public void sendKeys(WebElement element, CharSequence... keysToSend) {
        delay();
        WebElement clickableElement = waitForElementToBeClickable(element);
        clickableElement.sendKeys(keysToSend);
    }

    /**
     * Clears the text of the specified WebElement after waiting for it to be clickable.
     *
     * @param element the WebElement to clear.
     */
    public void clear(WebElement element) {
        delay();
        WebElement clickableElement = waitForElementToBeClickable(element);
        clickableElement.clear();
    }

    /**
     * Performs a JavaScript click on an element located by its CSS selector after waiting for it to be clickable.
     *
     * @param cssSelector the CSS selector of the element to click.
     */
    public void javascriptClickByCssSelector(String cssSelector) {
        delay();
        WebElement element = waitForElementToBeClickable(By.cssSelector(cssSelector));
        executeJavaScriptClick(element);
    }


    public void performRightClick(Object target) {
        WebElement element = null;

        if (target instanceof By) {
            element = driver.findElement((By) target);
        } else if (target instanceof WebElement) {
            element = (WebElement) target;
        } else if (target instanceof String) {
            // Determine the type of locator automatically
            String locator = (String) target;
            By by = inferBy(locator);
            element = driver.findElement(by);
        } else {
            throw new IllegalArgumentException("Invalid target type. Must be String, By, or WebElement.");
        }

        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
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
     * Performs a JavaScript click on an element located by its ID after waiting for it to be clickable.
     *
     * @param id the ID of the element to click.
     */
    public void javascriptClickById(String id) {
        delay();
        WebElement element = waitForElementToBeClickable(By.id(id));
        executeJavaScriptClick(element);
    }

    /**
     * Performs a JavaScript click on an element located by its XPath after waiting for it to be clickable.
     *
     * @param xpath the XPath of the element to click.
     */
    public void javascriptClickByXPath(String xpath) {
        delay();
        WebElement element = waitForElementToBeClickable(By.xpath(xpath));
        executeJavaScriptClick(element);
    }

    public void InsightHomePage() {
        this.executeScript("history.replaceState(null, null, document.location.pathname + '#/insight/web/#/'); \n" +
                "  location.reload(); ");
    }

    /**
     * Performs a JavaScript click on an element located by its link text after waiting for it to be clickable.
     *
     * @param linkText the link text of the element to click.
     */
    public void javascriptClickByLinkText(String linkText) {
        delay();
        WebElement element = waitForElementToBeClickable(By.linkText(linkText));
        executeJavaScriptClick(element);
    }

    public void doubleClick(WebElement element) {
        Actions actions = new Actions(driver);
        actions.doubleClick(element).perform();
        delay();
    }

    public void executeJavaScriptClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void executeJavaScriptClick(String cssSelector) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(arguments[0]).click();", cssSelector);
    }

    /**
     * Submits the specified WebElement after waiting for it to be clickable.
     *
     * @param element the WebElement to submit.
     */
    public void submit(WebElement element) {
        delay();
        WebElement clickableElement = waitForElementToBeClickable(element);
        clickableElement.submit();
    }

    /**
     * Selects an option from a dropdown by visible text after waiting for the dropdown to be clickable.
     *
     * @param dropdownElement the WebElement representing the dropdown.
     * @param visibleText     the visible text of the option to select.
     */
    public void selectDropdownByVisibleText(WebElement dropdownElement, String visibleText) {
        delay();
        WebElement clickableElement = waitForElementToBeClickable(dropdownElement);
        new Select(clickableElement).selectByVisibleText(visibleText);
    }

    /**
     * Selects an option from a dropdown by value after waiting for the dropdown to be clickable.
     *
     * @param dropdownElement the WebElement representing the dropdown.
     * @param value           the value of the option to select.
     */
    public void selectDropdownByValue(WebElement dropdownElement, String value) {
        delay();
        WebElement clickableElement = waitForElementToBeClickable(dropdownElement);
        new Select(clickableElement).selectByValue(value);
    }

    /**
     * Selects an option from a dropdown by index after waiting for the dropdown to be clickable.
     *
     * @param dropdownElement the WebElement representing the dropdown.
     * @param index           the index of the option to select.
     */
    public void selectDropdownByIndex(WebElement dropdownElement, int index) {
        delay();
        WebElement clickableElement = waitForElementToBeClickable(dropdownElement);
        new Select(clickableElement).selectByIndex(index);
    }

    /**
     * Scrolls the page until the specified WebElement is in view after waiting for it to be clickable.
     *
     * @param element the WebElement to scroll to.
     */
    public void scrollToElement(WebElement element) {
        delay();

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Waits for an alert to be present and then accepts it.
     */
    public void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    /**
     * Waits for an alert to be present and then dismisses it.
     */
    public void dismissAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().dismiss();
    }

    /**
     * Sends keys to an alert and then accepts it.
     *
     * @param text the text to send to the alert.
     */
    public void sendKeysToAlert(String text) {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(text);
        alert.accept();
    }

    @Override
    public Object executeScript(String script, Object... args) {
        delay();
        return ((JavascriptExecutor) driver).executeScript(script, args);
    }

    @Override
    public Object executeAsyncScript(String script, Object... args) {
        delay();
        return ((JavascriptExecutor) driver).executeAsyncScript(script, args);
    }

}
