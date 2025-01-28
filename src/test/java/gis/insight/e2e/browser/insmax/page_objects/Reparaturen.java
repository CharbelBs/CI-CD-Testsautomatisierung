package gis.insight.e2e.browser.insmax.page_objects;

import gis.insight.e2e.global.page_objects.GlobalInsMax;
import gis.insight.e2e.tests.configurations.DelayedWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;

/**
 * This class represents the creation process (Erstellung) page object for the Insight application.
 * It provides methods to interact with various elements on the page, such as creating new orders, 
 * searching by order number, and retrieving or setting values for different attributes.
 */
public class Reparaturen {

    private GlobalInsMax global;

    @FindBy(xpath = "//ion-label[contains(.,'Erstellen')]")
    WebElement Reparature_Erstellen;

    @FindBy(xpath = "//ion-label[contains(.,'Create')]")
    WebElement Reparature_Create;

    @FindBy(css = "ion-item[insight-attribute-ident=\"prc/DESCRIPTION\"] .icon-magnifier")
    WebElement Art_Search;

    @FindBy(css = "ion-item[insight-attribute-ident=\"DESCRIPTION_LONGDESCRIPTION\"] textarea")
    WebElement ReparaturBeschreibung;

    @FindBy(css = "ion-item[insight-attribute-ident=\"onbehalfof/DISPLAYNAME\"] input")
    WebElement GemeldetVon;

    @FindBy(css = "ion-item[insight-attribute-ident=\"REPORTDATE\"] input")
    WebElement AngelegtAm;
    @FindBy(css = "ion-img")
    WebElement ImageAttacment;

    @FindBy(css = "ion-item[insight-attribute-ident=\"WONUM\"] input")
    WebElement ReapraturNummer;

    @FindBy(css = "ion-item[insight-attribute=\"STATUS\"] input")
    WebElement Status;


    /**
     * Constructor that initializes the ErstellungIns page object.
     * It uses Selenium's PageFactory to initialize WebElements and sets up the global object for further interactions.
     *
     * @param delayedDriver the WebDriver instance used to interact with the web page
     */
    public Reparaturen(DelayedWebDriver delayedDriver) throws AWTException {

        PageFactory.initElements(delayedDriver, this);
        global = new GlobalInsMax(delayedDriver);
    }

    public void ImagePresentCheck() throws InterruptedException {
        ImageAttacment.click();
        Thread.sleep(2000);
        ImageAttacment.click();
    }

    public String getReapraturNummer(){
        return ReapraturNummer.getAttribute("value");
    }

    public String getStatus(){
        return Status.getAttribute("value");
    }

    public String getAngelegtAm(){
        return AngelegtAm.getAttribute("value");
    }

    public String getGemeldetVon(){
        return GemeldetVon.getAttribute("value");
    }

    public String getReparaturBeschreibung(){
        return ReparaturBeschreibung.getAttribute("value");
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

    public void Reparature_Erstellen_Auto() throws InterruptedException {


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
