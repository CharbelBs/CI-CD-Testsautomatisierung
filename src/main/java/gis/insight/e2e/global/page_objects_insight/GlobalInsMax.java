package gis.insight.e2e.global.page_objects;

import gis.insight.e2e.global.methods.DownloadChecker;
import gis.insight.e2e.global.methods.WebWait;
import gis.insight.e2e.tests.configurations.DelayedWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class GlobalInsMax {

    @FindBy(css = "select-from-tree-dialog list ion-list ion-item-sliding")
    WebElement firstDialogListElement;

    @FindBy(css = "list ion-list ion-item-sliding")
    WebElement firstListElement;

    @FindBy(css = "list ion-list ion-item-sliding")
    List<WebElement> ListElements;

    @FindBy(xpath = "//ion-button[contains(.,'Weiter')]")
    WebElement weiterButton;

    @FindBy(xpath = "//ion-button[contains(.,'Next')]")
    WebElement NextButton;

    @FindBy(css = ".item[insight-attribute-ident=\"DESCRIPTION_LONGDESCRIPTION\"] textarea")
    WebElement ReparaturBeschreibung;

    @FindBy(css = ".icon-home4")
    WebElement homeIcon;

    @FindBy(css = ".icon-exit")
    WebElement click_Logout;

    @FindBy(css = ".icon-menu")
    WebElement menuIcon;

    @FindBy(css = ".icon-ellipsis")
    WebElement ellipsisIcon;

    @FindBy(css = "ion-popover .icon-cog")
    WebElement settingsIcon;

    @FindBy(css = ".select-ltr")
    WebElement selectLtr;

    @FindBy(xpath = "//div[@class=\"alert-radio-label sc-ion-alert-ios\"][contains(.,'Deutsch')]")
    WebElement germanOption;

    @FindBy(xpath = "//div[@class=\"alert-radio-label sc-ion-alert-ios\"][contains(.,'English')]")
    WebElement englichOption;

    @FindBy(xpath = "//button[contains(.,'OK')]")
    WebElement okButton;

    @FindBy(xpath = "//i[@class=\"icon icon-checkmark-circle\"]")
    List<WebElement> selectedIcons;

    @FindBy(xpath = "//ion-label[contains(.,'Allgemein')]")
    List<WebElement> Deutsch_Element;

    @FindBy(xpath = "//ion-label[contains(.,'Allgemein')]")
    WebElement Allgemein;

    @FindBy(xpath = "//ion-card-title[contains(.,'Service Desk')]//..//i")
    WebElement Select_click_Service_Desk;

    @FindBy(xpath = "//ion-card-title[contains(.,'Test')]//..//i")
    WebElement Test_Section;

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

    @FindBy(xpath = "//ion-button[.//i[@class='icon icon-picture2']]"   )
    WebElement Galerie;

    @FindBy(xpath = "//ion-button[i[contains(@class, 'icon-paperclip')]]//span[contains(.,'Datei auswählen')]")
    WebElement Datei_Auswählen;

    @FindBy(css = "document-item ion-img")
    WebElement Image;

    @FindBy(css = "photo-dialog ion-content .camera-controls .shutter-button")
    WebElement Fotomachen;

    @FindBy(css = ".icon-camera2")
    WebElement CameraIcon;

    @FindBy(xpath = "//ion-button[contains(.,'Speichern')]")
    WebElement Speichern;

    @FindBy(css = "ion-loading")
    WebElement Loading;

    @FindBy(xpath = "//ion-button[contains(.,'Save')]")
    WebElement Save;

    @FindBy(css = "ion-toast.ion-color-success")
    WebElement Speicher_Success;

    @FindBy(css = "ion-toast.ion-color-success")
    WebElement Cordona_Success;

  @FindBy(xpath = "//ion-label[contains(.,'Offline schalten')]")
    WebElement  Offline_schalten;

    @FindBy(xpath = "//ion-label[contains(.,'Online schalten')]")
    WebElement   Online_schalten ;

    @FindBy(css = ".icon-wifi-blocked")
    WebElement  Wifi_blocked;

    @FindBy(css = "details-page .icon-chevron-left")
    WebElement  icon_left;

    @FindBy(css = ".icon-home4")
    WebElement  Icon_home;

    @FindBy(css = "document-item ion-thumbnail")
    WebElement  Document;

    @FindBy(css = ".icon.icon-trash2")
    WebElement  Document_Löschen;

    @FindBy(xpath = "//upload-status-dialog//ion-card-content/ion-list/ion-item/ion-label[contains(.,'Entfernen')]")
    WebElement  Document_Entfernen_im_Status;


    @FindBy(xpath = "//document-item//ion-button//i[contains(@class, 'icon-menu')]")
    WebElement  Document_Menu;

    @FindBy(xpath = "//document[1]/ion-card/ion-card-content/ion-buttons/action-button[3]/ion-button")
    WebElement  Image_Laden;

    @FindBy(xpath = "//document[2]/ion-card/ion-card-content/ion-buttons/action-button[2]/ion-button")
    WebElement  Document_Laden;

    @FindBy(css = "ion-popover .item-has-start-slot.ion-activatable")
    WebElement   Datei_anzeigen;


    @FindBy(css = "ion-popover ion-item.ion-activatable:not(.item-has-start-slot)")
    WebElement   Document_Status;

    @FindBy(css = ".alert-button.alert-button-role-confirm")
    WebElement AlertOk;

    @FindBy(css = ".alert-button.alert-button-role-cancel")
    WebElement AlertAbbrechen;

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


    private DelayedWebDriver delayedWebDriver;
    private WebWait wait;
    private DownloadChecker downloadChecker;


    public GlobalInsMax(DelayedWebDriver driver) throws AWTException {
        this.delayedWebDriver = driver;
        this.wait = new WebWait(driver);
        downloadChecker = new DownloadChecker(driver);
        PageFactory.initElements(driver, this);
    }


    public String getID(){

        return Id.getAttribute("value").trim();
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


    public Integer getListElements_Anzahl() throws InterruptedException {
        delayedWebDriver.executeScript("location.reload();");
        wait.Visible(firstListElement, 15);
        return  ListElements.size();
    }

    public void closeFileDialog() throws Exception {
        // Create an instance of Robot class
        Robot robot = new Robot();

        // Simulate pressing the "Escape" key to close the file upload dialog
        robot.keyPress(KeyEvent.VK_ESCAPE);
        robot.keyRelease(KeyEvent.VK_ESCAPE);
    }

    public void click_Image(){
        delayedWebDriver.scrollToElement(Image);
        Image.click();
    }

    public void switch_Window(){
        // Store the current window handle


// Perform actions on the new page
// For example, login or authorization steps here

// Switch back to the original window
        //delayedWebDriver.switchTo().window(originalWindow);

    }


    public void click_Document(){
        delayedWebDriver.scrollToElement(Document);
        Document.click();
    }

    public boolean Document_Exists() throws InterruptedException {
        try {
            // Wait for the element to be visible (adjust selector as needed)
            Thread.sleep(3000);

            delayedWebDriver.findElement(By.cssSelector("document-item ion-thumbnail"));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }


    public void click_okButton(){

        if(okButton.isEnabled())
         okButton.click();
    }

    public void Photo_Löschen_VorSpeichern(){

        try {
            Image_Laden.click();
        } catch (org.openqa.selenium.NoSuchElementException e) {

        }

        delayedWebDriver.scrollToElement(Image);

        delayedWebDriver.executeJavaScriptClick(Document_Menu);

        delayedWebDriver.executeJavaScriptClick(Document_Löschen);


    }

    public void Document_Entfernen_im_Status(){

        try {
            delayedWebDriver.scrollToElement(Document_Laden);
            Document_Laden.click();
        } catch (org.openqa.selenium.NoSuchElementException e) {

        }

        delayedWebDriver.scrollToElement(Document);

        delayedWebDriver.executeJavaScriptClick(Document_Menu);

        Document_Status.click();

        Document_Entfernen_im_Status.click();


    }


    public void Document_anzeigen() throws InterruptedException {

        delayedWebDriver.executeJavaScriptClick(Document_Menu);
        delayedWebDriver.executeJavaScriptClick(Datei_anzeigen);

    }

    public void click_Schließen(){

        delayedWebDriver.executeJavaScriptClick(Schließen);
    }

    public void clickFirstListElements(){

          firstListElement.click();
    }

    public void clickListElement(Object param) throws InterruptedException {
        if (param instanceof Integer) {
            // Locate element by position (integer)
            int value = (Integer) param;
            String cssSelector = "list ion-list ion-item-sliding:nth-child(" + value + ")";
            delayedWebDriver.findElement(By.cssSelector(cssSelector)).click();
        } else if (param instanceof String) {
            // Locate element by matching text (string)
            String searchString = (String) param;
            String xpath = "//list//ion-list//ion-item-sliding//ion-label[contains(.,'" + searchString + "')]";
            delayedWebDriver.findElement(By.xpath(xpath)).click();
        } else {
            throw new IllegalArgumentException("Parameter must be an Integer (position) or a String (text).");
        }

        // Wait for 3 seconds (optional, replace with explicit waits if necessary)
        Thread.sleep(3000);
    }



    public void click_Icon_home(){
        Icon_home.click();
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

        long originalDelay = delayedWebDriver.getDelayInMillis();
        delayedWebDriver.setDelayInMillis(0);  // Set to 0 for temporary speed


        // Check if 'Allgemein' (German for General) is present on the page
        int deutschElements = Deutsch_Element.size();

        if (deutschElements != 1) {
            // Open settings and change language to German (Deutsch)
            ellipsisIcon.click();
            settingsIcon.click();


            selectLtr.click();
            germanOption.click();
            okButton.click();

            homeIcon.click();

        }

        // Open the menu
        Thread.sleep(3000);
        menuIcon.click();

        // Uncheck all selected items
        int selectedCount = selectedIcons.size();

        while (selectedCount != 0) {
            selectedIcons.get(0).click();
            selectedCount--;
        }


        // Restore the original delay after the initialization
        delayedWebDriver.setDelayInMillis(originalDelay);
    }

    public void Initialise_Englich() throws InterruptedException, IOException {

        long originalDelay = delayedWebDriver.getDelayInMillis();
        delayedWebDriver.setDelayInMillis(0);  // Set to 0 for temporary speed


        // Check if 'Allgemein' (German for General) is present on the page
        int deutschElements = Deutsch_Element.size();

        if (deutschElements >= 1) {
            // Open settings and change language to German (Deutsch)
            ellipsisIcon.click();
            settingsIcon.click();


            selectLtr.click();
            englichOption.click();
            okButton.click();

            homeIcon.click();

        }

        Thread.sleep(3000);
        menuIcon.click();
        Thread.sleep(1000);
        // Uncheck all selected items
        int selectedCount = selectedIcons.size();

        while (selectedCount != 0) {
            selectedIcons.get(0).click();
            Thread.sleep(500);
            selectedCount--;
        }


        // Restore the original delay after the initialization
        delayedWebDriver.setDelayInMillis(originalDelay);
    }



    public void clickSpeichern() throws InterruptedException {

        delayedWebDriver.scrollToElement(Speichern);
        Speichern.click();
        Thread.sleep(1000);
    }

    public void click_Save(){

        delayedWebDriver.scrollToElement(Save);
        Save.click();

    }


    public void Fotomachen() throws InterruptedException {

        delayedWebDriver.scrollToElement(CameraIcon);
        delayedWebDriver.executeJavaScriptClick(CameraIcon);

        Thread.sleep(3000);

        delayedWebDriver.executeJavaScriptClick(Fotomachen);

        Thread.sleep(2000);


    }
    public void UploadDocument(String Path) throws Exception {

        delayedWebDriver.scrollToElement(Galerie);
        delayedWebDriver.executeJavaScriptClick(Galerie);
        // Convert relative path to absolute path
        String filePath = new File(Path).getAbsolutePath();
        fileInput.sendKeys(filePath);

        closeFileDialog();
    }

    public void Datei_Auswählen(String Path) throws Exception {


// Now locate the specific button by its text
        WebElement Datei_Auswählen = delayedWebDriver.findElement(By.xpath("//document[2]/ion-card/ion-card-content/ion-buttons/action-button[1]/ion-button"));

        delayedWebDriver.scrollToElement(Datei_Auswählen);
        delayedWebDriver.executeJavaScriptClick(Datei_Auswählen);
        // Convert relative path to absolute path
        String filePath = new File(Path).getAbsolutePath();
        fileInput.sendKeys(filePath);

        closeFileDialog();
    }

    public void auto_SetReparaturBeschreibung(){

        ReparaturBeschreibung.sendKeys("AutoReparaturBeschreibung");
    }





    public void select_click_Service_Desk() {

        Select_click_Service_Desk.click();
    }

    public void select_Test_Section() {

        Test_Section.click();
        clickUebernehmen();
    }
    
    public void clickUebernehmen() {

        Uebernehmen.click();
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

    public void Abmelden() throws InterruptedException {

        click_Logout.click();
        Thread.sleep(3000);
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
}
