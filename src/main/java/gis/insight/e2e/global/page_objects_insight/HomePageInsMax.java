package gis.insight.e2e.global.page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class represents the HomePageIns page object, which provides methods to interact with
 * elements on the home page of the application. The elements are initialized using the Selenium 
 * PageFactory and are located using XPath and CSS selectors.
 */
public class HomePageInsMax {

    @FindBy(xpath = "//ion-card-title[contains(.,'Service Desk')]//..//i")
    WebElement click_Service_Desk;

    @FindBy(xpath = "//div[contains(.,'Reparaturen')]")
    WebElement Reparaturen;

    @FindBy(xpath = "//div[contains(.,'Repairs')]")
    WebElement Repairs;

    @FindBy(xpath = "//ion-label[contains(.,'Meldung')]")
    WebElement Meldung;

    @FindBy(xpath = "//ion-label[contains(.,'Test Cordova')]")
    WebElement Test_Cordova;

    /**
     * Constructor to initialize the elements of the HomePageIns using the provided WebDriver instance.
     * 
     * @param driver The WebDriver instance used to interact with the web page.
     */
    public HomePageInsMax(WebDriver driver) {


        PageFactory.initElements(driver, this);
    }


    public void click_Meldung() throws InterruptedException {
        Meldung.click();
    }


    public void click_Test_Cordova() throws InterruptedException {
        Test_Cordova.click();
    }

    /**
     * Method to click on the "Instandhaltung" and "Erstellung" sections sequentially.
     * 
     * @throws InterruptedException if the thread is interrupted while performing the actions.
     */
    public void click_Service_Desk() throws InterruptedException {
        click_Service_Desk.click();
    }

    /**
     * Method to click on the "Instandhaltung" and "Desktopanforderungen" sections sequentially.
     * 
     * @throws InterruptedException if the thread is interrupted while performing the actions.
     */
    public void click_Reparaturen() throws InterruptedException {
        Reparaturen.click();
    }

    public void click_Repairs() throws InterruptedException {
        Repairs.click();
    }

}
