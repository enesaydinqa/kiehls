package context.helper;

import org.openqa.selenium.WebElement;

public interface JSExecutor
{
    <T> T executeScript(Class<T> clazz,String script, Object... args);

    String getText(WebElement element);

    Long getPageHeight();

    String randomGenerate(int digit);

    Number randomNumberGenerate(int digit);

    String randomStringGenerate(int digit);
}
