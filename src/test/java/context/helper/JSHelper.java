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

    @Override
    public String randomGenerate(int digit)
    {
        return executeScript(String.class, "var text = ''; var possible = 'abcdefghijklmnopqrstuvwxyz0123456789'; for (var i = 0; i < arguments[0]; i++) text += possible.charAt(Math.floor(Math.random() * possible.length)); return text;", digit);
    }

    @Override
    public Number randomNumberGenerate(int digit)
    {
        return executeScript(Number.class, "return Math.floor(Math.random() * Math.floor(Math.pow(10, arguments[0])))", digit);
    }

    @Override
    public String randomStringGenerate(int digit)
    {
        return executeScript(String.class, "return Math.random().toString(36).replace(/[^a-z]+/g, '').substr(0, arguments[0]);", digit);
    }

}
