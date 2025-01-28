package gis.insight.e2e.global.page_objects_github;

import gis.insight.e2e.global.methods.WebWait;
import gis.insight.e2e.tests.configurations.DelayedWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class GlobalGitHub{

    private final DelayedWebDriver driver;
    private final WebWait wait;
    private final String currentTab = "";


    @FindBy(linkText = "Hauptbereich")
    WebElement hauptbereichLink;




    /**
     * Constructor to initialize the elements of the GlobalMethodes using the provided WebDriver instance.
     *
     * @param driver The WebDriver instance used to interact with the web page.
     */
    public GlobalGitHub(DelayedWebDriver driver) {
        this.driver = driver;
        this.wait = new WebWait(driver);
        PageFactory.initElements(driver, this);
    }


    public void openUserMenu() {
        driver.findElement(By.cssSelector(".AppHeader-user button")).click();
    }

    public void spanDoubleClick(String name) throws InterruptedException {
        // Locate the target element (span, div, a, input, etc.) containing the specified text
        WebElement element = driver.findElement(By.xpath("//span[contains(.,'" + name + "')]"));
        wait.forClickable(element);
        driver.scrollToElement(element);
        driver.doubleClick(element);
        Thread.sleep(1000);
    }

    public void spanClick(String name) throws InterruptedException {
        // Locate the target element (span, div, a, input, etc.) containing the specified text
        WebElement element = driver.findElement(By.xpath("//span[contains(.,'" + name + "')]"));
        wait.forClickable(element);
        driver.scrollToElement(element);
        driver.click(element);
        Thread.sleep(1000);
    }

    public String getName() {

       return driver.findElement(By.id("user_profile_name")).getAttribute("value");
    }

    public void setBio(String testBio) {

        driver.findElement(By.id("user_profile_bio")).clear();
        driver.findElement(By.id("user_profile_bio")).sendKeys(testBio);
    }

    public void clickButtonWithText(String value) {

        WebElement element = driver.findElement(By.xpath("//*[normalize-space(text())='" + value + "']"));
        driver.executeJavaScriptClick(element);

    }


    public String getBio() {

        return driver.findElement(By.id("user_profile_bio")).getAttribute("value");
    }


    public int storeXPathCount(String xpath) {
        try {
            // Find all elements matching the XPath
            List<WebElement> elements = driver.findElements(By.xpath(xpath));

            // Get the count
            int count = elements.size();

            // Log the count
            System.out.println("Count of elements matching XPath \"" + xpath + "\": " + count);

            // Return the count
            return count;
        } catch (Exception e) {
            // Log and handle exceptions
            System.err.println("Error occurred while counting XPath elements: " + e.getMessage());
            return 0;
        }
    }


    public boolean verifyNamePresent(String name) {
        try {
            Thread.sleep(1000);
            // Temporarily set implicit wait to 0
            driver.manage().timeouts().implicitlyWait(0, java.util.concurrent.TimeUnit.SECONDS);

            driver.findElement(By.xpath("//*[contains(., '"+ name +"')]"));
            return true;

        } catch (Exception e) {
            return false;
        }
        finally {
            // Temporarily set implicit wait to 0
            driver.manage().timeouts().implicitlyWait(15, java.util.concurrent.TimeUnit.SECONDS);

        }
    }


    public boolean verifyNameNotPresent(String name) {
        try {
            Thread.sleep(1000);
            // Temporarily set implicit wait to 0
            driver.manage().timeouts().implicitlyWait(0, java.util.concurrent.TimeUnit.SECONDS);

            driver.findElement(By.xpath("//*[contains(., '"+ name +"')]"));
            return false;

        } catch (Exception e) {
            return true;
        }
        finally {
            // Temporarily set implicit wait to 0
            driver.manage().timeouts().implicitlyWait(15, java.util.concurrent.TimeUnit.SECONDS);

        }
    }


    public boolean waitForNamePresent(String name, Integer time) {
        try {

            WebElement treeItem = driver.findElement(By.xpath("//*[contains(., '"+ name +"')]"));
            wait.Visible(treeItem,time);
            return false;

        } catch (Exception e) {
            return true;
        }
    }

    public boolean waitForNameNotPresent(String name, Integer time) {
        try {

            WebElement treeItem = driver.findElement(By.xpath("//*[contains(., '"+ name +"')]"));
            wait.forInvisible(treeItem, time);
            return true;

        } catch (Exception e) {
            return false;
        }
    }
}
