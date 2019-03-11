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
    private static final Logger logger = Logger.getLogger(ChromeDriverManagerWeb.class);

    private ChromeOptions chromeOptions;
    private DesiredCapabilities desiredCapabilities;
    private boolean remoteTest;

    @Override
    public void createDriver(Boolean withProxy) throws Exception
    {
        remoteTest = configuration.getRemoteTest();

        chromeOptions = chromeOptions();

        if (remoteTest)
        {
            logger.info("This test is browserstack execute ...");

            desiredCapabilities = desiredCapabilities(true, withProxy, remoteTest, chromeOptions);

            driver = new RemoteWebDriver(new URL(configuration.getBrowserStackUrl()), desiredCapabilities);
        }
        else
        {
            desiredCapabilities = desiredCapabilities(false, withProxy, false, chromeOptions);

            if (Platform.getCurrent().is(Platform.MAC))
            {
                System.setProperty("webdriver.chrome.driver", configuration.getMacChromeDriver());
            }
            else if (Platform.getCurrent().is(Platform.WINDOWS))
            {
                System.setProperty("webdriver.chrome.driver", configuration.getWindowsChromeDriver());
            }

            logger.info("This test is local execute ...");
            driver = new ChromeDriver(desiredCapabilities);
        }

        session = (driver).getSessionId().toString();
        logger.info("=================================================================");
        logger.info("This Execute Session ID --> " + session);
        logger.info("=================================================================");
    }

    private DesiredCapabilities desiredCapabilities(Boolean remoteTest, Boolean withProxy, Boolean browserStackLocal, ChromeOptions chromeOptions) throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

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

        if (remoteTest)
        {
            capabilities.setCapability("browser", "Chrome");
            capabilities.setCapability("platform", "MAC");
            capabilities.setCapability("acceptSslCerts", "true");
            capabilities.setCapability("browserstack.debug", "true");
            capabilities.setCapability("browserstack.console", "verbose");
            capabilities.setCapability("browserstack.networkLogs", "true");
            capabilities.setCapability("browserstack.networkProfile", "4g-lte-high-latency");
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
        browserStackLocalArgs.put("key", accessKey);
        browserStackLocalArgs.put("forcelocal", "true");
        browserStackLocalArgs.put("forceproxy", "true");
        browserStackLocalArgs.put("force", "true");
        browserStackLocalArgs.put("v", "true");
        browserStackLocalArgs.put("-local-proxy-host", host);
        browserStackLocalArgs.put("-local-proxy-port", port);
        browserStackLocal.start(browserStackLocalArgs);
    }

}
