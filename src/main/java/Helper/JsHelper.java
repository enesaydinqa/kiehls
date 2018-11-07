package Helper;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.How;

import static Interfaces.Constants.*;


public class JsHelper {

    public Object executeScriptObject(WebDriver driver, String s, Object... objects) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        return executor.executeScript(s, objects);
    }

    public JavaScriptExecutor executeScript(WebDriver driver, String s, Object... objects) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        return (JavaScriptExecutor) executor.executeScript(s, objects);
    }

    public How getLocatorType(Object object) {

        String locatorType;

        locatorType = object.toString().split(Regex.LOCATOR_TYPE)[1].split((":"))[0];

        switch (locatorType)
        {
            case LocatorType.CLASS_NAME : return How.CLASS_NAME;
            case LocatorType.CSS : return How.CSS;
            case LocatorType.ID : return How.ID;
            case LocatorType.LINK_TEXT : return How.LINK_TEXT;
            case LocatorType.NAME : return How.NAME;
            case LocatorType.PARTIAL_LINK_TEXT : return How.PARTIAL_LINK_TEXT;
            case LocatorType.TAG_NAME : return How.TAG_NAME;
            case LocatorType.XPATH : return How.XPATH;

            default : return null;
        }
    }

    public String getLocatorValue(Object object, String locatorType) {
        String getLocator = object.toString().split(Regex.LOCATOR_VALUE.replace("{LOCATORTYPE}", locatorType))[1];
        return getLocator.substring(0, getLocator.length() - 1);
    }
}
