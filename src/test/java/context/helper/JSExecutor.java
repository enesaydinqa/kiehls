package context.helper;

import org.openqa.selenium.WebElement;

public interface JSExecutor
{
    <T> T executeScript(Class<T> clazz,String script, Object... args);

    Long getPageHeight();

    String randomGenerate(int digit);

    Number randomNumberGenerate(int digit);

    String randomStringGenerate(int digit);

    void openNewTab(String links);

    void doubleClick(WebElement element);

    void navigateToUrl(String links);

    void click(WebElement element);

    String getText(WebElement element);

    void selectByIndex(WebElement element, Integer index);

    void selectByValue(WebElement element, String value);

    void sendKeys(WebElement element, String value);

    boolean isDisplayed(WebElement element);

    void clear(WebElement element);

    String getSelectedOptionText(WebElement element);

    Long getSelectedOptionIndex(WebElement element);

    String getSelectedOptionValue(WebElement element);

    void scrollToElement(WebElement element);
}
