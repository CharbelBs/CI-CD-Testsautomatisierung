# Selenium Test Project Notes

## **1. Insight Project**

### **Standard Global Selector Methods**
- **`navigateTo(String name)`**: Used on the Home page to easily navigate to a specific section.
- **`currentTab` (variable)**: Ensures that the selector focuses on the currently open tab, not others.

- **`getElementMitInsightAttribute(String insightAttribute)`**: Retrieves a web element using the `insightAttribute` and allows actions to be performed on it.
- **`getValueMitInsightAttribute(String insightAttribute)`**: Retrieves the value of a web element using the `insightAttribute`.
- **`setValueMitInsightAttribute(String insightAttribute, String value)`**: Sets a value for a web element using the `insightAttribute`.
- **`setValueMitInsightAttributeImDialog(String insightAttribute, String value)`**: Sets a value for a web element in a dialog using the `insightAttribute`.
- **`openSearchMitInsightAttribute(String insightAttribute)`**: Opens the search icon of a web element using the `insightAttribute`.

- **`openTreeSectionByName(String name)`**: Expands a tree section to reveal its child elements.
- **`closeTreeSectionByName(String name)`**: Collapses a tree section to hide its child elements.
- **`spanDoubleClick(String name)`**: Performs a double-click action on an element identified by its name.
- **`click_pop_button(String buttonName)`**: Clicks a button displayed after a right-click, using the button's name.
- **`clickButtonWithLinkText(String value)`**: Attempts to click a button identified by its link text.
- **`verifyElementVisible(String xpathCssClassId, Integer seconds)`**: Waits for an element to become visible using XPath, CSS, class, or ID.
- **`verifyElementNotVisible(String xpathCssClassId, Integer seconds)`**: Waits for an element to become invisible using XPath, CSS, class, or ID.
- **`selectOption(String name)`**: Selects an option by name in a dropdown field.

---

## **2. Standard Methods**
- **`closeLeftTab()`**: Closes the left tab that displays the tree or menu.
- **softAssert**: Class used to run assertion tests without stopping execution.
- **`closeCurrentTab()`**: Closes the currently opened tab.
- **`closeAllTabs()`**: Closes all open tabs.
- **`closeAllDropdownIcons()`**: Closes all active dropdown icons.
- **`closeTab(Integer num)`**: Closes a specific tab identified by its index.
- **`selectDeutschLanguage()`**: Ensures that the application language is set to German at the start of a test.
- **`reload()`**: Reloads the current page.
- **`save()`**: Clicks the save button in the current tab.
- **`verifySuccess()`**: Verifies if the save operation was successful.
- **`refresh()`**: Clicks the refresh button in the current tab.
- **`clickWeiter()`**: Clicks the "Weiter" (Next) button.
- **`convertStringToInteger(String number)`**: Converts a string value into an integer for better test processing.
- **`DelayedDriver.InsightHomePage()`**: Reloads the entire application and navigates back to the Home page.
- **`verifyKeineTrefferGefunden()`**: Returns a boolean indicating whether no elements are found in a table.

---

## **3. Methods for Tables**
- **`openTableElement(String insightTest, String elementName)`**: Double-clicks on an element in a table with the specified name.
- **`getTableElementExist(String insightTest, String elementName)`**: Returns the count of existing elements in the table.
- **`getTableMitInsightTest(String insightTest)`**: Retrieves the table with the specified `insightTest`.
- **`setInputImTabelle(String insightTest, int row, int position, Object value)`**: Sets input in a specific table cell.
- **`getInputImTabelle(String insightTest, int row, int position)`**: Retrieves input from a specific table cell.
- **`getTabelleItemsSize(String value)`**: Returns the count of table items matching a specific value.

---

## **4. Methods for Dialogs**
- **`clickOptionInDialog(String value)`**: Clicks an option in a select field within a dialog.
- **`verifyListContainsInDialog(String name)`**: Verifies if a list in a dialog contains a specific name.
- **`clickListItemInDialog(String name)`**: Clicks a list item in a dialog by name.
- **`clickListItemInDialog(Integer position)`**: Clicks on an element at the specified position in a dialog list.
- **`getListItemInDialog(String name)`**: Retrieves the text of a dialog list item by name.
- **`getListItemInDialog(Integer position)`**: Retrieves the text of a dialog list item by position.

---

## **5. Methods for Gantt Charts**
- **`waitForGanttPresent()`**: Waits until the Gantt chart is present.
- **`clickGanttLeftButton()`**: Clicks the "left" button in a Gantt chart to navigate left.
- **`clickGanttRightButton()`**: Clicks the "right" button in a Gantt chart to navigate right.
- **`getGanttRowText(String position)`**: Returns the text of an element at a specific position in the Gantt chart.
- **`getGanttDate()`**: Returns the current date from the Gantt chart.
- **`getTodayDate()`**: Returns today's date.
- **`clickGanttStart()`**: Clicks the "Start" button in the Gantt chart.
- **`clickGanttEnd()`**: Clicks the "End" button in the Gantt chart.

---

## **6. Database Operations**
- **`List<String> sqlResults = db.executeSqlQuery(sqlQuery)`**: Executes an SQL query and retrieves the results as a list.
- **`String mahSql = sqlResults.get(0)`**: Retrieves the first result from the SQL query.
