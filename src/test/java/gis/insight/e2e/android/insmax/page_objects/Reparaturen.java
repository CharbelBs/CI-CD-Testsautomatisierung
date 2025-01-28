package gis.insight.e2e.android.insmax.page_objects;

import gis.insight.e2e.global.methods.WebWait;
import gis.insight.e2e.tests.configurations.*;
import gis.insight.e2e.global.page_objects.GlobalInsMax_Mob;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;

/**
 * This class represents the HomePageIns page object, which provides methods to interact with
 * elements on the home page of the application. The elements are initialized using the Selenium 
 * PageFactory and are located using XPath and CSS selectors.
 */
public class Reparaturen {
    private GlobalInsMax_Mob global;

    @FindBy(xpath = "//ion-label[contains(.,'Erstellen')]")
    WebElement Reparature_Erstellen;

    @FindBy(xpath = "//ion-label[contains(.,'Create')]")
    WebElement Reparature_Create;

    @FindBy(css = "ion-item[insight-attribute=\"prc/DESCRIPTION\"] .icon-magnifier")
    WebElement Art_Search;

    @FindBy(css = "ion-item[insight-attribute-ident=\"DESCRIPTION_LONGDESCRIPTION\"] textarea")
    WebElement ReparaturBeschreibung;

    @FindBy(css = "ion-item[insight-attribute-ident=\"onbehalfof/DISPLAYNAME\"] input")
    WebElement GemeldetVon;

    @FindBy(css = "ion-item[insight-attribute-ident=\"REPORTDATE\"] input")
    WebElement AngelegtAm;

    @FindBy(css = "ion-item[insight-attribute-ident=\"WONUM\"] input")
    WebElement ReparaturNummer;

    @FindBy(css = "ion-item[insight-attribute=\"STATUS\"] input")
    WebElement Status;

    @FindBy(css = "ion-img")
    WebElement ImageAttacment;

    @FindBy(xpath = "//ion-card-title[contains(.,'Service Desk')]//..//i")
    WebElement click_Service_Desk;


    private DelayedWebAppiumDriver delayedWebAppiumDriver;
    private WebWait wait;

    public Reparaturen(DelayedWebAppiumDriver driver) throws AWTException {

        this.delayedWebAppiumDriver = driver;
        this.global = new GlobalInsMax_Mob(driver);
        this.wait = new WebWait(driver);
        PageFactory.initElements(driver, this);
    }

    public void reinitializeElements(DelayedWebAppiumDriver driver) throws AWTException {
        this.delayedWebAppiumDriver = driver;
        this.global = new GlobalInsMax_Mob(driver);
        this.wait = new WebWait(driver);
        PageFactory.initElements(driver, this);
    }


    /**
     * Method to click on the "Instandhaltung" and "Erstellung" sections sequentially.
     * 
     * @throws InterruptedException if the thread is interrupted while performing the actions.
     */
    public void click_Service_Desk() throws InterruptedException {
        click_Service_Desk.click();
    }

    public void ImagePresentCheck() throws InterruptedException {
        ImageAttacment.click();
        Thread.sleep(2000);
        ImageAttacment.click();
    }

    public String getReapraturNummer(){

        return delayedWebAppiumDriver.getAttributeUsingJS(ReparaturNummer,"value");

    }

    public String getStatus(){


        return delayedWebAppiumDriver.getAttributeUsingJS(Status,"value");


    }

    public String getAngelegtAm(){

        return delayedWebAppiumDriver.getAttributeUsingJS(AngelegtAm,"value");

    }

    public String getGemeldetVon(){
        return delayedWebAppiumDriver.getAttributeUsingJS(GemeldetVon,"value");

    }

    public String getReparaturBeschreibung() {

        return delayedWebAppiumDriver.getAttributeUsingJS(ReparaturBeschreibung,"value");


    }


    public void setReparaturBeschreibung(String value){
        ReparaturBeschreibung.sendKeys(value);
    }

    public void Reparature_Erstellen_Auto_Eng() throws InterruptedException {


        Reparature_Create.click();

        Art_Search.click();

        global.clickfirstDialogListElement();
        Thread.sleep(1000);
        global.clickfirstDialogListElement();
        Thread.sleep(1000);
        global.clickfirstDialogListElement();
        Thread.sleep(1000);
        global.click_Next();

        global.auto_SetReparaturBeschreibung();

        global.click_Next();

        global.click_Next();

        global.click_Next();

        global.Fotomachen();

        global.click_Next();

        global.click_Save();

    }

    public void Reparature_Erstellen_Auto() throws InterruptedException, AWTException {


        Reparature_Erstellen.click();

        Art_Search.click();

        global.clickfirstDialogListElement();
        Thread.sleep(1000);
        global.clickfirstDialogListElement();
        Thread.sleep(1000);
        global.clickfirstDialogListElement();
        Thread.sleep(1000);
        global.clickWeiter();

        global.auto_SetReparaturBeschreibung();

        global.clickWeiter();

        global.clickWeiter();

        global.clickWeiter();

        global.Fotomachen();

        global.clickWeiter();

        global.clickSpeichern();


    }
}



