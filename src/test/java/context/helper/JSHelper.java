package context.helper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class JSHelper
{
    public static Object executeScriptObject(WebDriver driver, String s, Object... objects)
    {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        return executor.executeScript(s, objects);
    }

}
