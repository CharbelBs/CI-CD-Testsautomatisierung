package gis.insight.e2e.global.page_objects;

import gis.insight.e2e.global.methods.DownloadChecker;
import gis.insight.e2e.global.methods.WebWait;
import gis.insight.e2e.tests.configurations.*;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;


public class GlobalInsMax_Mob {


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


    @FindBy(css = "select-from-tree-dialog list ion-list ion-item-sliding")
    WebElement firstDialogListElement;

    @FindBy(css = "ion-toast.ion-color-success")
    WebElement Speicher_Success;

    @FindBy(css = "list ion-list ion-item-sliding")
    WebElement firstListElement;

    @FindBy(css = "list ion-list ion-item-sliding")
    List<WebElement> ListElements;

    @FindBy(xpath = "//ion-button[contains(.,'Weiter')]")
    WebElement weiterButton;

    @FindBy(xpath = "//ion-button[contains(.,'Next')]")
    WebElement NextButton;

    @FindBy(css = ".item[insight-attribute=\"DESCRIPTION_LONGDESCRIPTION\"] textarea")
    WebElement ReparaturBeschreibung;

    @FindBy(css = ".icon-home4")
    WebElement homeIcon;

    @FindBy(css = ".icon-menu")
    WebElement menuIcon;

    @FindBy(css = ".icon-ellipsis")
    WebElement ellipsisIcon;

    @FindBy(css = "ion-popover .icon-cog")
    WebElement settingsIcon;

    @FindBy(css = "ion-item ion-select")
    WebElement select_Language;

    @FindBy(xpath = "//div[@class=\"alert-radio-label sc-ion-alert-ios\"][contains(.,'Deutsch')]")
    WebElement germanOption;

    @FindBy(xpath = "//div[@class=\"alert-radio-label sc-ion-alert-ios\"][contains(.,'English')]")
    WebElement englichOption;

    @FindBy(css = "button.alert-button:not(.alert-button-role-cancel)")
    WebElement OK_Button;

    @FindBy(css = "ion-alert button")
    WebElement Alert_Button;

    @FindBy(xpath = "//i[@class=\"icon icon-checkmark-circle\"]")
    List<WebElement> selectedIcons;

    @FindBy(xpath = "//ion-label[contains(.,'Allgemein')]")
    List<WebElement> Deutsch_Element;

    @FindBy(css = ".icon-exit")
    WebElement Abmelden;

    @FindBy(xpath = "//ion-label[contains(.,'Allgemein')]")
    WebElement Allgemein;

    @FindBy(xpath = "//ion-card-title[contains(.,'Service Desk')]//..//i")
    WebElement Select_click_Service_Desk;

    @FindBy(xpath = "//ion-button[contains(.,'Übernehmen')]")
    WebElement Uebernehmen;

    @FindBy(xpath = "//ion-button[contains(.,'Accept')]")
    WebElement Accept;

    @FindBy(xpath = "//ion-button[contains(.,'Schließen')]")
    WebElement Schließen;

    @FindBy(xpath = "//ion-button[contains(.,'Kamera')]")
    WebElement kameraButton;

    @FindBy(xpath = "//input[@type='file']")
    WebElement fileInput;

    @FindBy(xpath = "//ion-button[.//i[@class='icon icon-picture2']]")
    WebElement Galerie;

    @FindBy(css = "document-item ion-img")
    WebElement Image;

    @FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Shutter\"]")
    WebElement Fotomachen;

    @FindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Done\"]")
    WebElement Foto_bestätigen;

    @FindBy(css = ".icon-camera2")
    WebElement CameraIcon;

    @FindBy(xpath = "//ion-button[contains(.,'Speichern')]")
    WebElement Speichern;

    @FindBy(xpath = "//ion-button[contains(.,'Save')]")
    WebElement Save;

    @FindBy(css = "details-page #ion-overlay-18 .ion-color-success")
    WebElement Gespeichert;

    @FindBy(xpath = "//ion-label[contains(.,'Offline schalten')]")
    WebElement Offline_schalten;

    @FindBy(xpath = "//ion-label[contains(.,'Online schalten')]")
    WebElement Online_schalten;

    @FindBy(css = ".icon-wifi-blocked")
    WebElement Wifi_blocked;

    @FindBy(css = "details-page .icon-chevron-left")
    WebElement icon_left;

    @FindBy(css = ".icon-home4")
    List<WebElement> Icon_home;

    @FindBy(css = "document-item ion-thumbnail")
    WebElement Document;

    @FindBy(css = "document-item ion-button .icon-menu")
    WebElement Document_Menu;

    @FindBy(css = "ion-list .icon-eye")
    WebElement Document_Zeigen;

    @FindBy(css = ".alert-button.alert-button-role-confirm")
    WebElement AlertOk;

    @FindBy(css = ".alert-button.alert-button-role-cancel")
    WebElement AlertAbbrechen;

    @FindBy(xpath = "//ion-card-title[contains(.,'Test')]//..//i")
    WebElement Test_Section;

    @FindBy(css = ".icon-icons2")
    WebElement Calculator_icon;

    @FindBy(css = "integer-input-dialog")
    WebElement Calculator_Dialog;

    @FindBy(css = "integer-input-dialog ion-button.button-outline")
    WebElement Calculator_Button_Eins;

    @FindBy(xpath = "//integer-input-dialog/ion-content/ion-grid/ion-row[6]/ion-col[3]/ion-button")
    WebElement Calculator_Button_Delete;

    @FindBy(css = "integer-input-dialog ion-button[color='success']")
    WebElement Calculator_Button_Save;

    @FindBy(css = ".icon-barcode")
    WebElement Barcode_icon;

    @FindBy(css = "#qr-shaded-region")
    WebElement Barcode_Scanner;

    @FindBy(css = ".icon-broadcast")
    WebElement Broadcast_icon;

    @FindBy(css = ".icon-envelope")
    WebElement Envelope_icon;

    @FindBy(css = ".icon-telephone")
    WebElement Telephone_icon;

    @FindBy(css = ".icon-bubble")
    WebElement Bubble_icon;

    @FindBy(css = "ion-item[insight-attribute=\"id\"] input")
    WebElement Id;

    @FindBy(css = "ion-item[insight-attribute=\"mail\"] input")
    WebElement Mail;
    @FindBy(css = "ion-item[insight-attribute=\"phone\"] input")
    WebElement Phone;
    @FindBy(css = "ion-item[insight-attribute=\"sms\"] input")
    WebElement SMS;

    @FindBy(xpath = "//document[1]/ion-card/ion-card-content/ion-buttons/action-button[3]/ion-button")
    WebElement  Image_Laden;

    @FindBy(css = ".icon.icon-trash2")
    WebElement  Document_Löschen;

    @FindBy(xpath = "//upload-status-dialog//ion-card-content/ion-list/ion-item/ion-label[contains(.,'Entfernen')]")
    WebElement  Document_Entfernen_im_Status;

    @FindBy(xpath = "//document[1]/ion-card/ion-card-content/ion-buttons/action-button[2]/ion-button")
    WebElement  Foto_Laden;

    @FindBy(xpath = "//document[2]/ion-card/ion-card-content/ion-buttons/action-button[2]/ion-button")
    WebElement  Document_Laden;

    @FindBy(css = "ion-popover .item-has-start-slot.ion-activatable")
    WebElement   Datei_anzeigen;


    @FindBy(css = "ion-popover ion-item.ion-activatable:not(.item-has-start-slot)")
    WebElement   Document_Status;

    private DelayedWebAppiumDriver delayedWebAppiumDriver;
    private WebWait wait;

    public GlobalInsMax_Mob(DelayedWebAppiumDriver driver) throws AWTException {
        this.delayedWebAppiumDriver = driver;
        this.wait = new WebWait(driver);
        PageFactory.initElements(driver, this);
    }

    public void click_Alert_Button(){
        try {
            wait.forClickable(Alert_Button);
            delayedWebAppiumDriver.executeJavaScriptClick(Alert_Button);
            System.out.println("Alert button was present and clicked.");

        } catch (Exception e) {

        }
    }

    public void Document_Entfernen_im_Status(){

        try {
            Document_Laden.click();
        } catch (org.openqa.selenium.NoSuchElementException e) {

        }

        delayedWebAppiumDriver.scrollToElement(delayedWebAppiumDriver,Document);

        delayedWebAppiumDriver.executeJavaScriptClick(Document_Menu);

        Document_Status.click();

        Document_Entfernen_im_Status.click();


    }

    public void Foto_Entfernen_im_Status(){

        try {
            Foto_Laden.click();
        } catch (org.openqa.selenium.NoSuchElementException e) {

        }

        delayedWebAppiumDriver.scrollToElement(delayedWebAppiumDriver,Document);

        delayedWebAppiumDriver.executeJavaScriptClick(Document_Menu);

        Document_Status.click();

        Document_Entfernen_im_Status.click();


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


    public String getID(){

        return delayedWebAppiumDriver.getAttributeUsingJS(Id,"value");
    }
    public String getPhone(){

        return Phone.getAttribute("value");
    }

    public String getMail(){

        return Mail.getAttribute("value");
    }
    public String getSMS(){

        return SMS.getAttribute("value");
    }

    public void click_Barcode_icon() throws InterruptedException {
        Barcode_icon.click();
    }

    public void click_Barcode_Scanner() throws InterruptedException {
        Barcode_Scanner.click();
    }
    public void click_Calculator_icon() throws InterruptedException {
        Calculator_icon.click();
    }

    public void click_Calculator_Button_Eins() throws InterruptedException {
        Calculator_Button_Eins.click();
    }
    public void click_Calculator_Button_Delete() throws InterruptedException {
        Calculator_Button_Delete.click();
    }

    public void click_Calculator_Button_Save() throws InterruptedException {
        Calculator_Button_Save.click();
    }

    public void click_Telephone_icon() throws InterruptedException {
        Telephone_icon.click();
    }

    public void click_Broadcast_icon() throws InterruptedException {
        Broadcast_icon.click();
    }
    public void click_Envelope_icon() throws InterruptedException {
        Envelope_icon.click();
    }

    public void click_Bubble_icon() throws InterruptedException {
        Bubble_icon.click();
    }

    public String getEnvelopeHref() {
        return Envelope_icon.getAttribute("href");
    }

    /**
     * Gets the href attribute of the telephone icon (tel link).
     *
     * @return the href value.
     */
    public String getTelephoneHref() {
        return Telephone_icon.getAttribute("href");
    }

    /**
     * Gets the href attribute of the bubble icon (sms link).
     *
     * @return the href value.
     */
    public String getBubbleHref() {
        return Bubble_icon.getAttribute("href");
    }

    public void Photo_Löschen_VorSpeichern(){

        try {
            Image_Laden.click();
        } catch (org.openqa.selenium.NoSuchElementException e) {

        }

        delayedWebAppiumDriver.scrollToElement(delayedWebAppiumDriver, Image);

        delayedWebAppiumDriver.executeJavaScriptClick(Document_Menu);

        delayedWebAppiumDriver.executeJavaScriptClick(Document_Löschen);


    }

    public boolean Document_Exists() throws InterruptedException {
        try {
            // Wait for the element to be visible (adjust selector as needed)
            Thread.sleep(3000);

            delayedWebAppiumDriver.findElement(By.cssSelector("document-item ion-thumbnail"));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void clickListElement(Integer value) throws InterruptedException {

        delayedWebAppiumDriver.findElement(By.cssSelector("list ion-list ion-item-sliding:nth-child("+value+")")).click();
        Thread.sleep(3000);
    }

    public void select_Test_Section() {

        delayedWebAppiumDriver.scrollToElement(delayedWebAppiumDriver, Test_Section);
        Test_Section.click();
        Uebernehmen.click();

    }


    public boolean Speicher_Success() throws InterruptedException {


        try {

            wait.Visible(Speicher_Success,  6);
            wait.forInvisible(Speicher_Success,5);
            return true;

        } catch (Exception e) {
            return false;
        }

    }

    // Method for clicking 'Weiter' button
    public void firstDialogListElement() throws InterruptedException {
        wait.forClickable(firstDialogListElement);
        firstDialogListElement.click();
    }

    // Method for waiting 'Gespeichert' button
    public void Gespeichert_Success() throws InterruptedException {
        wait.Visible(Gespeichert, 5);

    }



    public Integer getListElements_Anzahl() throws InterruptedException {
        delayedWebAppiumDriver.executeScript("location.reload();");
        wait.Visible(firstListElement, 5);
        return ListElements.size();
    }

    public void closeFileDialog() throws Exception {
        // Create an instance of Robot class
        Robot robot = new Robot();

        // Simulate pressing the "Escape" key to close the file upload dialog
        robot.keyPress(KeyEvent.VK_ESCAPE);
        robot.keyRelease(KeyEvent.VK_ESCAPE);
    }

    public void click_Image() {
        delayedWebAppiumDriver.scrollToElement(delayedWebAppiumDriver, Image);
        Image.click();
    }

    public void click_Document() {
        delayedWebAppiumDriver.scrollToElement(delayedWebAppiumDriver, Document);
        Document.click();
    }

    public void click_OK_Button() {

        if (OK_Button.isDisplayed())
            delayedWebAppiumDriver.executeJavaScriptClick(OK_Button);
    }



    public void Abmelden() {

        delayedWebAppiumDriver.executeJavaScriptClick(Abmelden);
    }


    public void click_Schließen() {

        Schließen.click();
    }

    public void clickFirstListElements() {

        firstListElement.click();
    }

    public void click_Icon_home() {
        Icon_home.get(0).click();
    }

    public void click_Icon_left() throws InterruptedException {
        icon_left.click();

    }



    public void  Online_schalten() throws TimeoutException {

        click_Allgemein();
        Online_schalten.click();

    }

    public void  Offline_schalten() throws InterruptedException {

        click_Allgemein();
        Offline_schalten.click();

        wait.Visible(Wifi_blocked, 5);
    }

    public void click_Allgemein(){

        Allgemein.click();
    }

    public void Initialise_Deustch() throws InterruptedException, IOException {

        //click_Alert_Button();

        long originalDelay = delayedWebAppiumDriver.getDelayInMillis();
        delayedWebAppiumDriver.setDelayInMillis(0);  // Set to 0 for temporary speed


        // Check if 'Allgemein' (German for General) is present on the page
        int deutschElements = Deutsch_Element.size();

        if (deutschElements != 1) {
            // Open settings and change language to German (Deutsch)
            delayedWebAppiumDriver.executeJavaScriptClick(ellipsisIcon);
            settingsIcon.click();


            wait.forClickable(select_Language);
            select_Language.click();

            germanOption.click();
            OK_Button.click();

            Thread.sleep(5000);
            //click_Alert_Button();
            delayedWebAppiumDriver.executeJavaScriptClick(homeIcon);

        }


        delayedWebAppiumDriver.executeJavaScriptClick(menuIcon);
        Thread.sleep(1000);

        // Uncheck all selected items
        int selectedCount = selectedIcons.size() - 1;

        while (selectedCount > 0) {
            delayedWebAppiumDriver.executeJavaScriptClick(selectedIcons.get(selectedCount));
            selectedCount--;
        }


        // Restore the original delay after the initialization
        delayedWebAppiumDriver.setDelayInMillis(originalDelay);
        Thread.sleep(3000);
    }

    public void Initialise_Englich() throws InterruptedException, IOException {

        long originalDelay = delayedWebAppiumDriver.getDelayInMillis();
        delayedWebAppiumDriver.setDelayInMillis(0);  // Set to 0 for temporary speed


        // Check if 'Allgemein' (German for General) is present on the page
        int deutschElements = Deutsch_Element.size();

        if (deutschElements >= 1) {
            // Open settings and change language to German (Deutsch)
            ellipsisIcon.click();
            settingsIcon.click();


            select_Language.click();
            englichOption.click();
            OK_Button.click();

            homeIcon.click();

        }

        Thread.sleep(3000);
        menuIcon.click();

        // Uncheck all selected items
        int selectedCount = selectedIcons.size() - 1;
      //  wait.forClickable(Sections.get(0));
        while (selectedCount > 0) {
            delayedWebAppiumDriver.executeJavaScriptClick(selectedIcons.get(selectedCount));
            selectedCount--;
        }

        // Restore the original delay after the initialization
        delayedWebAppiumDriver.setDelayInMillis(originalDelay);
        Thread.sleep(3000);

    }



    public void clickSpeichern() throws InterruptedException, AWTException {


        delayedWebAppiumDriver.executeJavaScriptClick(Speichern);
        wait.forInvisible(Gespeichert,4);
        Thread.sleep(5000);

    }

    public void click_Save() throws InterruptedException {

        delayedWebAppiumDriver.executeJavaScriptClick(Save);
        Thread.sleep(5000);

    }


    public void Fotomachen() throws InterruptedException {

        delayedWebAppiumDriver.scrollToElement(delayedWebAppiumDriver,CameraIcon);
        CameraIcon.click();
        Thread.sleep(3000);

        delayedWebAppiumDriver.context("NATIVE_APP");

        var el11 = delayedWebAppiumDriver.findElement(AppiumBy.accessibilityId("Shutter"));
        el11.click();
        Thread.sleep(3000);
        var el12 = delayedWebAppiumDriver.findElement(AppiumBy.accessibilityId("Done"));
        el12.click();

        delayedWebAppiumDriver.context("WEBVIEW_gis.insight.mobile.v2");


    }

    public void printAllElements() {
        List<WebElement> elements = delayedWebAppiumDriver.findElements(By.xpath("//*"));  // Fetch all elements in the current context
        System.out.println("Number of elements found: " + elements.size());
        for (WebElement element : elements) {
            System.out.println("Element tag: " + element.getTagName() + ", text: " + element.getText());
        }
    }


    public void UploadDocumentvonGalerie() throws Exception {
        // Scroll to and click on the Gallery button
        delayedWebAppiumDriver.scrollToElement(delayedWebAppiumDriver, Galerie);
        Galerie.click();

        // Switch context to NATIVE_APP to interact with the gallery
        delayedWebAppiumDriver.context("NATIVE_APP");

        // Corrected XPath to select the first image in the gallery
        var First_Foto = delayedWebAppiumDriver.findElement(AppiumBy.xpath("(//android.widget.ImageView[@resource-id=\"com.google.android.providers.media.module:id/icon_thumbnail\"])[1]"));
        First_Foto.click();

        // Switch back to WebView context after selecting the image
        delayedWebAppiumDriver.context("WEBVIEW_gis.insight.mobile.v2");
    }

    public void auto_SetReparaturBeschreibung(){

        ReparaturBeschreibung.sendKeys("AutoReparaturBeschreibung");
    }





    public void select_click_Service_Desk() {

        delayedWebAppiumDriver.executeJavaScriptClick(Select_click_Service_Desk);
    }

    public void clickUebernehmen() throws InterruptedException {

        Uebernehmen.click();
        Thread.sleep(3000);
    }

    public void clickAccept() {

        Accept.click();
    }

    // Method for clicking the first list element
    public void clickfirstDialogListElement() throws InterruptedException {
        wait.forClickable(firstDialogListElement);
        firstDialogListElement.click();
    }

    // Method for clicking 'Weiter' button
    public void clickWeiter() throws InterruptedException {
        wait.forClickable(weiterButton);
        weiterButton.click();
    }

    // Method for clicking 'Weiter' button
    public void click_Next() throws InterruptedException {
        wait.forClickable(NextButton);
        NextButton.click();
    }
}
