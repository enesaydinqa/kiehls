package driver;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.remote.RemoteWebDriver;

import net.lightbody.bmp.BrowserMobProxy;

public abstract class DriverManager
{

    protected static String REMOTE_TEST = System.getProperty("RemoteTest").toLowerCase();
    protected static String USERNAME = System.getProperty("Username");
    protected static String AUTOMATE_KEY = System.getProperty("AutomateKey");
    String BROWSER_STACK_URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    protected static String EXEC_COMMAND_BY_JENKINS = System.getProperty("ExecCommandByJenkins").toLowerCase();
    protected static String EXEC_LOCAL_PROXY = System.getProperty("user.dir") + "/browserstacklocal/mac" +
            "/BrowserStackLocal --key " + AUTOMATE_KEY + " --force-local -local-proxy-port 8090";
    protected static String EXEC_LOCAL_PROXY_BY_JENKINS = System.getProperty("user.dir") + "/browserstacklocal/linux" +
            "/BrowserStackLocal --key " + AUTOMATE_KEY + " --force-local -local-proxy-port 8090";


    protected static RemoteWebDriver driver;
    protected static BrowserMobProxy proxy;

    protected abstract void createDriver() throws Exception;

    public RemoteWebDriver getDriver() throws Exception
    {
        if (null == driver)
        {
            createDriver();
            PropertyConfigurator.configure(System.getProperty("user.dir") + "/src/main/resources/log4j.properties");
        }
        return driver;
    }
}
