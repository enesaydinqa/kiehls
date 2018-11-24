package driver;

import net.lightbody.bmp.BrowserMobProxy;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class DriverManager {

    String REMOTE_TEST = System.getProperty("RemoteTest").toLowerCase();
    String USERNAME = System.getProperty("Username");
    String AUTOMATE_KEY = System.getProperty("AutomateKey");
    String BrowserStackURL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

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
