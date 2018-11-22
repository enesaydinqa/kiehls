package driver;

import net.lightbody.bmp.BrowserMobProxy;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class DriverManager {

    String REMOTE_TEST = System.getProperty("RemoteTest").toLowerCase();
    String BS_USERNAME = System.getProperty("BSUsername");
    String BS_AUTOMATEKEY = System.getProperty("BSAutomateKey");

    protected static RemoteWebDriver driver;
    protected static BrowserMobProxy proxy;

    protected abstract void createDriver() throws Exception;

    public RemoteWebDriver getDriver() throws Exception {
        if (null == driver) {
            createDriver();
        }
        return driver;
    }
}
