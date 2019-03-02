package context.helper;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSHelper implements JSExecutor
{
    private static final Logger logger = LoggerFactory.getLogger(JSHelper.class);

    private JavascriptExecutor jExecutor;

    public JSHelper(WebDriver driver)
    {
        this.jExecutor = (JavascriptExecutor) driver;
    }

    public <T> T executeScript(Class<T> clazz, String script, Object... args)
    {
        logger.info("JavaScript Execute Info : { " + script + " }", "Argument Info : { " + args + " }");

        return clazz.cast(jExecutor.executeScript(script, args));
    }

    @Override
    public String getText(WebElement element)
    {
        return executeScript(String.class, "return arguments[0].textContent;", element);
    }

    @Override
    public Long getPageHeight()
    {
        return executeScript(Long.class, "return $(document).height()");
    }

}
