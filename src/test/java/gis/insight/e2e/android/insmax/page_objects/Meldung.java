package gis.insight.e2e.android.insmax.page_objects;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import gis.insight.e2e.tests.configurations.*;
import gis.insight.e2e.global.page_objects.GlobalInsMax_Mob;

import java.awt.*;

/**
 * This class represents the creation process (Erstellung) page object for the Insight application.
 * It provides methods to interact with various elements on the page, such as creating new orders, 
 * searching by order number, and retrieving or setting values for different attributes.
 */
public class Meldung {

    private GlobalInsMax_Mob global;

    @FindBy(xpath = "//ion-label[contains(.,'Erstellen')]")
    WebElement Reparature_Erstellen;

    @FindBy(css = "ion-item[insight-attribute=\"TICKETID\"] input")
    WebElement Number;

    @FindBy(css = "ion-item[insight-attribute=\"STATUS\"] input")
    WebElement Status;


    /**
     * Constructor that initializes the ErstellungIns page object.
     * It uses Selenium's PageFactory to initialize WebElements and sets up the global object for further interactions.
     *
     * @param driver the WebDriver instance used to interact with the web page
     */
    public Meldung(DelayedWebAppiumDriver driver) throws AWTException {
        PageFactory.initElements(driver, this);
        global = new GlobalInsMax_Mob(driver);
    }


    public String getNumber(){

        return Number.getAttribute("value");
    }

    public String getStatus(){

        return Status.getAttribute("value");
    }
}
