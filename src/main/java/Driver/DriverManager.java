package Driver;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager {

    public static WebDriver driver;

    protected abstract void createDriver() throws Exception;

    public WebDriver getDriver() throws Exception {
        if (null == driver) {
            createDriver();
        }
        return driver;
    }
}
