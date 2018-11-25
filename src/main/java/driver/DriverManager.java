package driver;

import net.lightbody.bmp.BrowserMobProxy;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class DriverManager {

    String REMOTE_TEST = System.getProperty("RemoteTest").toLowerCase();
    String USERNAME = System.getProperty("Username");
    String AUTOMATE_KEY = System.getProperty("AutomateKey");
    String BROWSER_STACK_URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";


    String EXEC_COMMAND_BY_JENKINS = System.getProperty("ExecCommandByJenkins").toLowerCase();
    String EXEC_LOCAL_PROXY = System.getProperty("user.dir") + "/browserstacklocal/mac/BrowserStackLocal --key " + AUTOMATE_KEY;
    String EXEC_LOCAL_PROXY_BY_JENKINS = System.getProperty("user.dir") + "/browserstacklocal/linux/BBrowserStackLocal --key " + AUTOMATE_KEY;

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
