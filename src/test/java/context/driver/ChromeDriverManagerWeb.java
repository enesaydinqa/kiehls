package context.driver;

import com.browserstack.local.Local;
import net.lightbody.bmp.client.ClientUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;

public class ChromeDriverManagerWeb extends DriverManager
{
    private Logger logger = Logger.getLogger(ChromeDriverManagerWeb.class);

    private ChromeOptions chromeOptions;
    private DesiredCapabilities desiredCapabilities;
    private boolean remoteTest;

    @Override
    public void createDriver(Boolean withProxy) throws Exception
    {
        remoteTest = Boolean.parseBoolean(prop.getProperty("remote.test"));

        chromeOptions = chromeOptions();
        desiredCapabilities = desiredCapabilities(withProxy, remoteTest, chromeOptions);

        if (Platform.getCurrent().is(Platform.MAC))
        {
            System.setProperty("webdriver.chrome.driver", prop.getProperty("mac.chrome.driver"));
        }
        else if (Platform.getCurrent().is(Platform.WINDOWS))
        {
            System.setProperty("webdriver.chrome.driver", prop.getProperty("windows.chrome.driver"));
        }

        if (remoteTest)
        {
            driver = new RemoteWebDriver(new URL(prop.getProperty("browserstack.url")), desiredCapabilities);
        }
        else
        {
            driver = new ChromeDriver(desiredCapabilities);

        }

        String session = (driver).getSessionId().toString();
        logger.info("=================================================================");
        logger.info("This Execute Session ID --> " + session);
        logger.info("=================================================================");
    }

    private DesiredCapabilities desiredCapabilities(Boolean withProxy, Boolean browserStackLocal, ChromeOptions chromeOptions) throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browser", "Chrome");
        capabilities.setCapability("os", "Windows");
        capabilities.setCapability("os_version", "10");

        if (withProxy)
        {
            Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
            String host = seleniumProxy.getHttpProxy().substring(0, seleniumProxy.getHttpProxy().indexOf(":"));
            String port = seleniumProxy.getHttpProxy().substring(seleniumProxy.getHttpProxy().indexOf(":") + 1);

            if (browserStackLocal)
            {
                capabilities.setCapability("browserstack.local", browserStackLocal);
                browserStackLocalArg(host, port);
            }

            capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

            logger.info("=================================================================");
            logger.info("This Execute Browser Host --> " + host);
            logger.info("This Execute Browser Port --> " + port);
            logger.info("=================================================================");
        }

        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        return capabilities;
    }

    private ChromeOptions chromeOptions()
    {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-infobars");

        return chromeOptions;
    }

    private void browserStackLocalArg(String host, String port) throws Exception
    {
        HashMap<String, String> browserStackLocalArgs = new HashMap<>();

        Local browserStackLocal = new Local();
        browserStackLocalArgs.put("key", prop.getProperty("automate.key"));
        browserStackLocalArgs.put("forcelocal", "true");
        browserStackLocalArgs.put("forceproxy", "true");
        browserStackLocalArgs.put("force", "true");
        browserStackLocalArgs.put("v", "true");
        browserStackLocalArgs.put("-local-proxy-host", host);
        browserStackLocalArgs.put("-local-proxy-port", port);
        browserStackLocal.start(browserStackLocalArgs);
    }

}
