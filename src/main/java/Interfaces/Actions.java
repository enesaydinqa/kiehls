package Interfaces;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface Actions {

    String getCurrentURL();

    void navigateToURL(String url);

    void openNewTab();

    void click(WebElement element);

    void rightClick(WebElement element);

    void doubleClick(WebElement element);

    void mouseOver(WebElement element);

    void selectOptionIndex(WebElement element, int index);

    void selectOptionValue(WebElement element, String itemValue);

    void selectOptionVisibleText(WebElement element, String visibleText);

    void sendKeys(WebElement element, CharSequence text);

    boolean isDisplayed(WebElement element);

    boolean isAttributePresent(WebElement element, String attribute);

    void waitElementToBeClickable(WebElement element);

    void waitElementVisible(WebElement element);

    void waitElementNotVisible(WebElement element);

    void clearInput(WebElement element);

    void clearMultipleSelectedOption(WebElement element);

    String getText(WebElement element);

    String getSelectedOptionText(WebElement element);

    String getAttribute(WebElement element, String attributeName);

    String selectedOptionGetText(WebElement element);

    String selectedOptionGetValue(WebElement element);

    void wait(int seconds) throws InterruptedException;

    void pageLoad();

    void implicitlyWait();

    void assertEquals(Object actual, Object expected);

    void checkBoxChecked(WebElement element);

    void pageRefresh();

    void keysENTER(WebElement element);

    void switchWindowTab(int tab);

    void switchParentFrame();

    void switchFrame(By element);

    String getWindowHandle();

    void deleteCookie();

    void dragAndDrop(WebElement from, WebElement to) throws Exception;



    //-- Javascript

    void pageZoom(String zoomValue);

    void pageScroll(int x, int y);

    void scrollToElement(WebElement element);

    void clickViaJs(WebElement element);

    String getTextViaJs(WebElement element);

    void selectOptionIndexViaJs(WebElement element, int index);
}
