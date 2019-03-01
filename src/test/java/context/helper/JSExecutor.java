package context.helper;

import org.openqa.selenium.WebElement;

public interface JSExecutor
{
    <T> T executeScript(Class<T> clazz,String script, Object... args);

    String getText(WebElement element);

}
