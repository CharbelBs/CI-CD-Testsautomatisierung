package gis.insight.e2e.global.page_objects_insight;

import gis.insight.e2e.global.methods.WebWait;
import gis.insight.e2e.tests.configurations.DelayedWebDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;

import java.util.List;

public class GlobalInsight {

    private final DelayedWebDriver driver;
    private final WebWait wait;
    private final String currentTab = "//div[contains(@class, 'tab-container ng-scope') and not(@class='tab-container ng-scope ng-hide')]";


    @FindBy(linkText = "Hauptbereich")
    WebElement hauptbereichLink;

    @FindBy(xpath = "//div[@id='tab-list']/a[3]/i[2]")
    WebElement closeTabIcon;

    @FindBy(css = ".icon-menu")
    WebElement menuButton;


    @FindBy(xpath = "//div[@class=\"title active\"]/i")
    WebElement activeDropdownIcon;


    @FindBy(css = ".active .field[insight-attribute='_designation'] input")
    WebElement bezeichnung_in_Dialog;

    @FindBy(css = ".active .field[insight-attribute='_description'] textarea")
    WebElement beschreibung_in_Dialog;

    @FindBy(xpath = currentTab + "//div[@insight-attribute='designation']//input")
    WebElement bezeichnung;

    @FindBy(xpath = currentTab + "//div[@id='details-save-button']")
    WebElement save;

    @FindBy(xpath = currentTab + "//div[@id='details-refresh-button']")
    WebElement refresh;

    @FindBy(xpath = "//div[contains(@class,'visible')]//span[contains(.,'Weiter')]")
    WebElement weiter;

    @FindBy(xpath = "//*[@id='insight-form-dialog']//span[contains(.,'Abbruch')]")
    WebElement abbruch;

    @FindBy(xpath = "//*[normalize-space(text())='Auswahl']")
    WebElement auswahl;

    @FindBy(xpath = "//div[contains(@class, 'visible')]//a")
    List<WebElement> listItems_in_Dialog;

    @FindBy(linkText = "Detaildaten")
    WebElement detaildatenLink;

    @FindBy(css = ".success")
    WebElement success;

    @FindBy(css = ".icon-arrow-left")
    WebElement icon_left;

    @FindBy(css = ".active > .icon-cross2:not(.ng-hide)")
    WebElement currentCloseIcon;

    @FindBy(xpath = "//div[@id='insight-confirm-modal']//span[contains(.,'Ok')]")
    WebElement confirm_OK;

    @FindBy(xpath = "//*[@id=\"insight-error-modal\"]//span[contains(.,'Ok')]")
    WebElement error_OK;

    @FindBy(css = ".gantt-table-row")
    WebElement ganttRow;

    @FindBy(css = ".content input[type='date']")
    WebElement ganttDate;

    @FindBy(css = ".icon-chevron-left")
    WebElement ganttLeft;

    @FindBy(css = ".icon-chevron-right")
    WebElement ganttRight;

    @FindBy(css = ".field[insight-attribute='workingHoursDetail'] input")
    WebElement MAhPlanung;

    @FindBy(css = "div[ng-click='vm.model.api.scrollToDate(vm.viewRange.start)']")
    WebElement ganttStart;

    @FindBy(css = "div[ng-click='vm.model.api.scrollToDate(vm.viewRange.end)']")
    WebElement ganttEnd;

    @FindBy(css = ".icon-ellipsis")
    WebElement languageDropdown;

    @FindBy(css = "#tab-list i.flag.de")
    WebElement deutschSprache;

    @FindBy(css = "#tab-list > a.item.nopadding.ng-scope.ng-isolate-scope.droptarget.label-hidden > i.icon.icon-home")
    WebElement homeButton;


    /**
     * Constructor to initialize the elements of the GlobalMethodes using the provided WebDriver instance.
     *
     * @param driver The WebDriver instance used to interact with the web page.
     */
    public GlobalInsight(DelayedWebDriver driver) {
        this.driver = driver;
        this.wait = new WebWait(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickGanttStart() throws InterruptedException {
        ganttStart.click();
        Thread.sleep(2000);
    }

    public String getMAhPlanung() {
        return MAhPlanung.getAttribute("value");
    }


    public void goHome() {
        homeButton.click();
    }


    public void clickGanttEnd() throws InterruptedException {
        ganttEnd.click();
        Thread.sleep(2000);
    }
    public String getGanttRowData(String column) {
        return driver.findElement(By.cssSelector(".gantt-table-cell-column-"+column)).getText();
    }

    public void clickGanttLeftButton() throws InterruptedException {
        ganttLeft.click();
        Thread.sleep(3000);
    }

    public void closeLeftTab() throws InterruptedException {
        driver.findElement(By.cssSelector(".icon-arrow-left-square"));
        Thread.sleep(1000);
    }

    public void clickGanttRightButton() throws InterruptedException {
        ganttRight.click();
        Thread.sleep(3000);
    }


    public void waitForGanttPresent() throws InterruptedException {
        wait.Visible(ganttRow, 15);
    }

    public void waitForElementVisible(String xpathCssclassId, Integer seconds) throws InterruptedException {
        wait.Visible(xpathCssclassId, seconds);
    }

    public void waitForElementNotVisible(String xpathCssclassId, Integer time) {
        wait.forInvisible(xpathCssclassId, time);
    }

    public boolean verifyElementVisible(String xpathCssclassId) {
        try {
            Thread.sleep(1000);
            // Temporarily set implicit wait to 0
            driver.manage().timeouts().implicitlyWait(0, java.util.concurrent.TimeUnit.SECONDS);
            By by = inferBy(xpathCssclassId);
            driver.findElement(by);
            return true;

        } catch (Exception e) {
            return false;
        }
        finally {
            // Temporarily set implicit wait to 0
            driver.manage().timeouts().implicitlyWait(15, java.util.concurrent.TimeUnit.SECONDS);

        }
    }

    public boolean verifyElementNotVisible(String xpathCssclassId) {
        try {
            Thread.sleep(1000);
            // Temporarily set implicit wait to 0
            driver.manage().timeouts().implicitlyWait(0, java.util.concurrent.TimeUnit.SECONDS);
            By by = inferBy(xpathCssclassId);
            driver.findElement(by);
            return false;

        } catch (Exception e) {
            return true;
        }
        finally {
            // Temporarily set implicit wait to 0
            driver.manage().timeouts().implicitlyWait(15, java.util.concurrent.TimeUnit.SECONDS);

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


    public String getGanttRowText(String position) {
        return driver.findElement(By.cssSelector(".gantt-table-row:nth-child(" + position + ") text")).getText();
    }

    public String getGanttDate() {
        return ganttDate.getAttribute("value");
    }

    public String getTodayDate() {
        return driver.executeScript("return new Date().toISOString().split('T')[0]").toString();
    }

    public void save() throws InterruptedException {

        save.click();
    }

    public void refresh() throws InterruptedException {

        refresh.click();
    }


    public void clickMenu() {
        menuButton.click();
    }

    public void selectDeutschLanguage() throws InterruptedException {
        languageDropdown.click();

        deutschSprache.click();

        driver.InsightHomePage();
    }

    public WebElement getElementMitInsightAttribute(String insightAttribute){

        return driver.findElement(By.xpath(currentTab + "//*[@insight-attribute='"+ insightAttribute +"']"));

    }

    public String getValueMitInsightAttribute(String insightAttribute){

        return driver.findElement(By.xpath(currentTab + "//*[@insight-attribute='"+ insightAttribute +"']//input")).getAttribute("value");

    }

    public void setValueMitInsightAttribute(String insightAttribute, String value){

        driver.findElement(By.xpath(currentTab + "//*[@insight-attribute='"+ insightAttribute +"']//input")).sendKeys(value);

    }

    public void setValueMitInsightAttributeImDialog(String insightAttribute, String value){

        WebElement element = driver.findElement(By.xpath("//*[@id='insight-form-dialog']//div[@insight-attribute='"+ insightAttribute +"']//input"));
        element.clear();
        element.sendKeys(value);
    }


    public void openSearchMitInsightAttribute(String insightAttribute){

        driver.findElement(By.xpath(currentTab + "//*[@insight-attribute='"+ insightAttribute +"']//i[contains(@class, 'search')]")).click();

    }

    public void openSearchMitInsightAttributeInDialog(String insightAttribute){

        driver.findElement(By.xpath("//*[@id='insight-form-dialog']//*[@insight-attribute='"+ insightAttribute +"']//i[contains(@class, 'search')]")).click();

    }

    //div[@id='insight-form-dialog']
    public void clickSearchKategorie() throws InterruptedException {

        openSearchMitInsightAttribute("synchroValuationDesig");
        Thread.sleep(2000);
    }

    public void clickSearchPrioritaet() throws InterruptedException {

        openSearchMitInsightAttribute("prioritaet");
        Thread.sleep(2000);
    }

    public void clickSearchGebaeude() throws InterruptedException {

        openSearchMitInsightAttribute("gebaeudeId");
        Thread.sleep(2000);
    }

    public void clickSearchWerkstoffart() throws InterruptedException {

        openSearchMitInsightAttribute("werkstoffart");
        Thread.sleep(2000);
    }

    public void clickSearchEinheit() throws InterruptedException {

        openSearchMitInsightAttribute("unitId");
        Thread.sleep(2000);
    }

    public void clickSearchAufwandsart() throws InterruptedException {

        openSearchMitInsightAttribute("amountType");
        Thread.sleep(2000);
    }

    public void clickSearchBehaeltertyp() throws InterruptedException {

        openSearchMitInsightAttribute("behaelterType");
        Thread.sleep(2000);
    }

    public String getAufwand() {

        return getValueMitInsightAttribute("amount");
    }


    public void setAufwand(String value) {

        getElementMitInsightAttribute("amount").findElement(By.tagName("input")).clear();
        setValueMitInsightAttribute("amount", value);
    }

    public String getFaktorMasse() {

        return getValueMitInsightAttribute("masseFaktor");
    }


    public void setFaktorMasse(String value) {

        getElementMitInsightAttribute("masseFaktor").findElement(By.tagName("input")).clear();
        setValueMitInsightAttribute("masseFaktor", value);
    }

    public String getFaktorBehaelter() {

        return getValueMitInsightAttribute("behaelterFaktor");
    }


    public void setFaktorBehaelte(String value) {

        getElementMitInsightAttribute("behaelterFaktor").findElement(By.tagName("input")).clear();
        setValueMitInsightAttribute("behaelterFaktor", value);
    }

    public String getNichts0() {

        return getValueMitInsightAttribute("nichts");
    }


    public void setNichts0(String value) {

        getElementMitInsightAttribute("nichts").findElement(By.tagName("input")).clear();
        setValueMitInsightAttribute("nichts", value);
    }

    public String getWenigeA() {

        return getValueMitInsightAttribute("wenig");
    }


    public void setWenigeA(String value) {

        getElementMitInsightAttribute("wenig").findElement(By.tagName("input")).clear();
        setValueMitInsightAttribute("wenig", value);
    }

    public String getStandardB() {

        return getValueMitInsightAttribute("standard");
    }


    public void setStandardB(String value) {

        getElementMitInsightAttribute("standard").findElement(By.tagName("input")).clear();
        setValueMitInsightAttribute("standard", value);
    }

    public String getVielC() {

        return getValueMitInsightAttribute("viel");
    }


    public void setVielC(String value) {

        getElementMitInsightAttribute("viel").findElement(By.tagName("input")).clear();
        setValueMitInsightAttribute("viel", value);
    }

    public String getAnreiz() {

        return getValueMitInsightAttribute("anreiz");
    }


    public void setAnreiz(String value) {

        getElementMitInsightAttribute("anreiz").findElement(By.tagName("input")).clear();
        setValueMitInsightAttribute("anreiz", value);
    }

    public String getGebaeude() {

        return getValueMitInsightAttribute("gebaeudeId");
    }

    public void setGebaeude(String value) {

        setValueMitInsightAttribute("gebaeudeId", value);
    }

    public String getKategorie() {

        return getValueMitInsightAttribute("synchroValuationDesig");
    }

    public void setKategorie(String value) {

        setValueMitInsightAttribute("synchroValuationDesig", value);
    }

    public String getPrioritaet() {

        return getValueMitInsightAttribute("prioritaet");
    }

    public void setPrioritaet(String value) {

        setValueMitInsightAttribute("prioritaet", value);
    }

    public String getSchlagwort() {

        return getValueMitInsightAttribute("schlagwort");
    }

    public void setSchlagwort(String value) {

        setValueMitInsightAttribute("schlagwort", value);
    }

    public void spanDoubleClick(String name) throws InterruptedException {
        // Locate the target element (span, div, a, input, etc.) containing the specified text
        WebElement element = driver.findElement(By.xpath("//span[contains(.,'" + name + "')]"));
        wait.forClickable(element);
        driver.scrollToElement(element);
        driver.doubleClick(element);
        Thread.sleep(1000);
    }


    public void clickPopButton(String buttonName)  {
        driver.findElement(By.xpath("//context-menu-component//a[contains(text(),'"+ buttonName +"')]")).click();
    }

    public void clickConfirmOk() throws InterruptedException {
        confirm_OK.click();
        Thread.sleep(1000);
    }

    public void clickErroOk() throws InterruptedException {
        error_OK.click();
        Thread.sleep(1000);
    }

    public String getStatus() throws InterruptedException {

        return getValueMitInsightAttribute("status");
    }

    public String getStatusDetaildaten()  {
        return getValueMitInsightAttribute("valuationStatus");
    }

    public String getGebaeudeId() {
        return getValueMitInsightAttribute("gebaeudeId");
    }


    public String getPlanStart()  {
        return getValueMitInsightAttribute("planStart");
    }

    public String getPlanEnd()  {
        return getValueMitInsightAttribute("planEnd");
    }

    public String getPlanStartTakt()  {
        return getValueMitInsightAttribute("planStartTakt");
    }

    public String getPlanEndTakt()  {
        return getValueMitInsightAttribute("planEndTakt");
    }

    public String getDauerBaustelle()  {
        return getValueMitInsightAttribute("dauerBaustelle");
    }

    public String getPlanungStartTakt()  {
        return getValueMitInsightAttribute("planungStartTakt");
    }

    public String getBasisPlanEndTakt()  {
        return getValueMitInsightAttribute("basisPlanEndTakt");
    }

    public String getBasisPlanStartTakt()  {
        return getValueMitInsightAttribute("basisPlanStartTakt");
    }

    public String getSummetakte()  {
        return getValueMitInsightAttribute("summetakte");
    }

    public String getSummeMAHST()  {
        return getValueMitInsightAttribute("summeMahST");
    }

    public String getSummeStoffstromTBV() {
        return getValueMitInsightAttribute("summeStoffstromTBV");
    }

    public String getSummeMAZN()  {
        return getValueMitInsightAttribute("summeMahZN");
    }

    public String getSummeMahVO()  {
        return getValueMitInsightAttribute("summeMahVO");
    }

    public String getSummeMahQST()  {
        return getValueMitInsightAttribute("summeMahQST");
    }


    public void closeCurrentTab() throws InterruptedException {

        currentCloseIcon.click();
        Thread.sleep(1000);
    }


    public void closeTab(Integer num) throws InterruptedException {
        // Calculate the correct index
        int index = 2 + num;

        // Use the calculated index in the XPath
        driver.findElement(By.xpath("//*[@id='tab-list']/a[" + index + "]/i[2]")).click();
        Thread.sleep(1000);
    }

    public void openTab(Integer num) throws InterruptedException {
        // Calculate the correct index
        int index = 2 + num;

        // Use the calculated index in the XPath
        driver.findElement(By.xpath("//*[@id='tab-list']/a[" + index + "]")).click();
        Thread.sleep(1000);
    }


    public void verifySuccess() throws InterruptedException {

        wait.forClickable(success);
        success.click();

    }


    public void selectOption(String name) throws InterruptedException {


        driver.findElement(By.xpath(currentTab + "//option[contains(.,'"+ name +"')]")).click();
        Thread.sleep(1000);
    }

    public String getlistSizeInDialog() {

        return String.valueOf(listItems_in_Dialog.size());
    }

    public boolean verifyListContainsInDialog(String name) {
        try {
            return driver.findElement(By.xpath("//div[contains(@class,'visible')]//a[contains(text(),'" + name + "')]")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public void clickListItemInDialog(String name) {

        driver.findElement(By.xpath("//div[contains(@class,'visible')]//a[contains(text(),'"+ name +"')]")).click();
    }

    public void clickListItemInDialog(Integer position) {

        driver.findElement(By.cssSelector(".color-indicator:nth-child("+ position +") .header")).click();
    }

    public String getListItem_in_Dialog(String name) {

        return driver.findElement(By.xpath("//div[contains(@class,'visible')]//a[contains(text(),'"+ name +"')]")).getText();
    }

    public String getListItem_in_Dialog(Integer position) {

        return driver.findElement(By.cssSelector(".color-indicator:nth-child("+ position +") a")).getText();
    }

    public void setBezeichnungInDialog(String Bezeichnung) {

        bezeichnung_in_Dialog.sendKeys(Bezeichnung);
    }

    public void setBeschreibung_in_Dialog(String Beschreinung) {

        beschreibung_in_Dialog.sendKeys(Beschreinung);
    }



    public void clickWeiter(){

        weiter.click();
    }

    public void clickAbbruch(){

        abbruch.click();
    }
    public void clickAuswahl(){

        auswahl.click();
    }



    public String getBezeichnung() {
        try {
            return bezeichnung.getAttribute("value");
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public String getName() {
        try {
            return getElementMitInsightAttribute("name").getAttribute("value");
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public boolean verifyKeineTreffergefunden() {

        List<WebElement> elements = driver.findElements(By.xpath(currentTab + "//td[contains(.,'Keine Treffer gefunden')]"));
        return !elements.isEmpty();
    }

    public void openTableElement(String insightTest, String elementName) throws InterruptedException {
        WebElement element = driver.findElement(By.xpath(currentTab + "//table-component[@insight-test='"+ insightTest +"']//tr//td[contains(.,'" + elementName + "')]"));
        driver.doubleClick(element);
        Thread.sleep(1000);

    }

    public Integer getTabelleAnzahl(String insightTest) {
        try {
            Thread.sleep(1000);
            // Temporarily set implicit wait to 0
            driver.manage().timeouts().implicitlyWait(0, java.util.concurrent.TimeUnit.SECONDS);
            return Integer.parseInt(driver.findElement(By.xpath(currentTab + "//table-component[@insight-test='" + insightTest + "']//pagination-component//div[@class='detail ng-binding']")).getText());
        }catch (NoSuchElementException e) {
            return 0;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            // Temporarily set implicit wait to 0
            driver.manage().timeouts().implicitlyWait(15, java.util.concurrent.TimeUnit.SECONDS);

        }
    }

    public Integer getTabelleAnzahl() {
        try {
            Thread.sleep(1000);
            // Temporarily set implicit wait to 0
            driver.manage().timeouts().implicitlyWait(0, java.util.concurrent.TimeUnit.SECONDS);
            return Integer.parseInt(driver.findElement(By.xpath(currentTab + "//table-component//pagination-component//div[@class='detail ng-binding']")).getText());
        }catch (NoSuchElementException e) {
            return 0;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            // Temporarily set implicit wait to 0
            driver.manage().timeouts().implicitlyWait(15, java.util.concurrent.TimeUnit.SECONDS);

        }
    }

    public WebElement getTableElement(String insightTest, String elementName) throws InterruptedException {
        try {
            Thread.sleep(1000);
            // Temporarily set implicit wait to 0
            driver.manage().timeouts().implicitlyWait(0, java.util.concurrent.TimeUnit.SECONDS);
            return driver.findElement(By.xpath(currentTab + "//table-component[@insight-test='"+ insightTest +"']//tr//td[contains(.,'" + elementName + "')]"));
        } catch (NoSuchElementException e) {
            return null;
        }
        finally {
            // Temporarily set implicit wait to 0
            driver.manage().timeouts().implicitlyWait(15, java.util.concurrent.TimeUnit.SECONDS);

        }
    }


    public WebElement getTableMitInsightTest(String insightTest){

        return driver.findElement(By.xpath(currentTab + "//*[@insight-test='"+ insightTest +"']"));

    }


    public void setInputImTabelle(String insightTest, int row, int position, Object value) {
        WebElement element = driver.findElement(By.xpath(currentTab + "//table-component[@insight-test='"+ insightTest +"']//tr["+ row +"]//td["+ position +"]//input"));
        element.clear();
        element.sendKeys(value.toString());


    }

    public void setInputImTabelle(int row, int position, Object value) {
        WebElement element = driver.findElement(By.xpath(currentTab + "//table-component//tr["+ row +"]//td["+ position +"]//input"));
        element.clear();
        element.sendKeys(value.toString());


    }

    public String getInputImTabelle(String insightTest, int row, int position) {
        WebElement element = driver.findElement(By.xpath(currentTab + "//table-component[@insight-test='"+ insightTest +"']//tr["+ row +"]//td["+ position +"]//input"));
       return element.getAttribute("value");

    }

    public String getInputImTabelle(int row, int position) {
        WebElement element = driver.findElement(By.xpath(currentTab + "//table-component//tr["+ row +"]//td["+ position +"]//input"));
        return element.getAttribute("value");

    }

    public String getTextImTabelle(String insightTest, int row, int position) {
        WebElement element = driver.findElement(By.xpath(currentTab + "//table-component[@insight-test='"+ insightTest +"']//tr["+ row +"]//td["+ position +"]//span"));
        return element.getText();

    }

    public String getTextImTabelle(String insightTest, String contains, int position) {
        WebElement element = driver.findElement(By.xpath(currentTab + "//table-component[@insight-test='"+ insightTest +"']//tr[contains(.,'"+ contains +"')]//td["+ position +"]//span"));
        return element.getText();

    }

    public String getTextImTabelle(String contains, int position) {
        WebElement element = driver.findElement(By.xpath(currentTab + "//table-component//tr[contains(.,'"+ contains +"')]//td["+ position +"]//span"));
        return element.getText();

    }

    public String getTextImTabelle(int row, int position) {
        WebElement element = driver.findElement(By.xpath(currentTab + "//table-component//tr["+ row +"]//td["+ position +"]//span"));
        return element.getText();

    }

    public Integer convertStringToInteger(String number) {
        if (number == null || number.trim().isEmpty()) {
            return null;  // Return null for null or empty input
        }

        try {
            // Replace comma with dot to handle both decimal separators
            number = number.replace(",", ".");

            // Parse as Double first to handle decimals
            double doubleValue = Double.parseDouble(number);

            // Return rounded integer value
            return (int) Math.round(doubleValue);

        } catch (NumberFormatException e) {
            // Handle invalid number format
            System.err.println("Invalid number format: " + number);
            return null;
        }
    }



    public void navigateTo(String name) {
        WebElement element = driver.findElement(By.xpath("//div[@class='entry']//span[normalize-space(text())='" + name + "']"));
        driver.scrollToElement(element);
        driver.click(element);
    }

    public void performRightClick(Object target) {

        driver.performRightClick(target);
    }

    public void clickOptionInDialog(String value) {


        driver.findElement(By.xpath("//div[@id='insight-form-dialog']//option[contains(.,'"+ value  +"')]")).click();
    }


    public void openTabelleItem(String name) throws InterruptedException {
        WebElement workPlanElement = driver.findElement(By.xpath(currentTab + "//tr//td[contains(.,'" + name + "')]"));
        driver.doubleClick(workPlanElement);
        Thread.sleep(1000);
    }

    public void openTreeSectionByName(String name) {

           WebElement treeItem = driver.findElement(By.xpath("//tree-component[contains(@class, 'active')]//div[contains(@class, 'tree-item') and contains(@title, '" + name + "')]//i[contains(@class, 'icon-chevron-right')]"));
           driver.executeJavaScriptClick(treeItem);

    }


    public void closeTreeSectionByName(String name) {
        // Locate the div element with class containing 'tree-item' and a title matching the name

        WebElement treeItem = driver.findElement(By.xpath("//tree-component[contains(@class, 'active')]//div[contains(@class, 'tree-item') and @title='" + name + "']//i[contains(@class, 'icon-chevron-down')]"));
        driver.executeJavaScriptClick(treeItem);

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

            driver.findElement(By.xpath(currentTab + "//*[contains(., '"+ name +"')]"));
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

            driver.findElement(By.xpath(currentTab + "//*[contains(., '"+ name +"')]"));
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

            WebElement treeItem = driver.findElement(By.xpath(currentTab + "//*[contains(., '"+ name +"')]"));
            wait.Visible(treeItem,time);
            return false;

        } catch (Exception e) {
            return true;
        }
    }

    public boolean waitForNameNotPresent(String name, Integer time) {
        try {

            WebElement treeItem = driver.findElement(By.xpath(currentTab + "//*[contains(., '"+ name +"')]"));
            wait.forInvisible(treeItem, time);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public void performRightClickByName(String name) {

            WebElement Item = driver.findElement(By.xpath("//span[contains(., '"+ name +"')]"));
            driver.performRightClick(Item);

    }



    public Integer getTabelleItemsSize(String value) {

        return driver.findElements(By.xpath(currentTab + "//tr//td[contains(.,'" + value + "')]")).size();

    }

    public int getListItemLength() throws InterruptedException {
        Thread.sleep(3000);
        List<WebElement> elements = driver.findElements(By.xpath("//list-item-component"));
        return elements.size();
    }

    public void openDetails() {
        hauptbereichLink.click();
        detaildatenLink.click();

    }

    public void clickButtonWithLinkText(String value) {
        WebElement element;
        try {
            driver.findElement(By.linkText(value)).click();

        } catch (Exception e) {
            System.out.println("Element with link text '" + value + "' not found. Trying fallback...");
            try {
                element = driver.findElement(By.xpath("//*[normalize-space(text())='" + value + "']"));
                driver.click(element);
            } catch (Exception fallbackException) {
                System.out.println("Element with 'translate=" + value + "' also not found.");
                throw fallbackException; // Re-throw exception if fallback also fails
            }
        }
    }

    public void clickButtonWithText(String value) {

        WebElement element = driver.findElement(By.xpath("//*[normalize-space(text())='" + value + "']"));
        driver.executeJavaScriptClick(element);

    }


    public void reload() throws InterruptedException {
        // Execute JavaScript to reload the current page
        driver.executeScript("location.reload()");
        Thread.sleep(3000);
    }

    public void closeAllTabs() {

        while (true) {
            try {
                // Check if the close icon exists
                if (closeTabIcon.isDisplayed()) {
                    closeTabIcon.click();
                }
            } catch (NoSuchElementException e) {
                // Exit the loop if the element doesn't exist
                break;
            }
        }
    }

    public void closeAllDropdownIcons() {
        while (true) {
            try {
                // Check if the close icon exists
                if (activeDropdownIcon.isDisplayed()) {
                    activeDropdownIcon.click();
                }
            } catch (NoSuchElementException e) {
                // Exit the loop if the element doesn't exist
                break;
            }
        }
    }

}
