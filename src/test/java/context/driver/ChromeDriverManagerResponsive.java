package context.driver;

import com.browserstack.local.Local;
import net.lightbody.bmp.client.ClientUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ChromeDriverManagerResponsive extends DriverManager
{
    private Logger logger = Logger.getLogger(ChromeDriverManagerResponsive.class);

    private Map<String, String> mobileEmulation;
    private ChromeOptions chromeOptions;
    private DesiredCapabilities desiredCapabilities;
    private boolean remoteTest;

    private String USER_AGENT = "Mozilla/5.0 (Linux; Android 7.0; SM-G930V Build/NRD90M) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.125 Mobile Safari/537.36";

    @Override
    public void createDriver(Boolean withProxy) throws Exception
    {
        remoteTest = Boolean.parseBoolean(System.getProperty("remote.test"));

        if (remoteTest)
        {
            desiredCapabilities = desiredCapabilities(true, withProxy, true, null);

            logger.info("This test is browserstack execute ...");
            driver = new RemoteWebDriver(new URL(prop.getProperty("browserstack.url")), desiredCapabilities);
        }
        else
        {
            mobileEmulation = mobileEmulation();
            chromeOptions = chromeOptions(mobileEmulation);
            desiredCapabilities = desiredCapabilities(false, withProxy, false, chromeOptions);

            if (Platform.getCurrent().is(Platform.MAC))
            {
                System.setProperty("webdriver.chrome.driver", prop.getProperty("mac.chrome.driver"));
            }
            else if (Platform.getCurrent().is(Platform.WINDOWS))
            {
                System.setProperty("webdriver.chrome.driver", prop.getProperty("windows.chrome.driver"));
            }

            logger.info("This test is local execute ...");
            driver = new ChromeDriver(desiredCapabilities);
            driver.manage().window().setSize(new Dimension(414, 736));
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
                capabilities.setCapability("browserstack.local", true);
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
            capabilities.setCapability("realMobile", "true");
            capabilities.setCapability("device", "iPhone 8 Plus");
            //capabilities.setCapability("acceptSslCerts", "true");
            capabilities.setCapability("browserstack.debug", "true");
            //capabilities.setCapability("browserstack.console", "verbose");
            capabilities.setCapability("browserstack.networkLogs", "true");
            capabilities.setCapability("browserstack.networkProfile", "4g-lte-high-latency");
        }


        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        return capabilities;
    }

    private ChromeOptions chromeOptions(Map<String, String> mobileEmulation)
    {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("--user-agent=" + USER_AGENT);
        chromeOptions.setExperimentalOption("mobileEmulation", null);

        return chromeOptions;
    }

    private Map<String, String> mobileEmulation()
    {
        Map<String, String> mobileEmulation = new HashMap<>();

        mobileEmulation.put("browserName", "iPhone");
        mobileEmulation.put("device", "iPhone 8 Plus");
        mobileEmulation.put("realMobile", "true");
        mobileEmulation.put("version", "70.0");

        return mobileEmulation;
    }

    private void browserStackLocalArg(String host, String port) throws Exception
    {
        HashMap<String, String> browserStackLocalArgs = new HashMap<>();

        Local browserStackLocal = new Local();
        browserStackLocalArgs.put("key", System.getProperty("access.key"));
        browserStackLocalArgs.put("forcelocal", "true");
        browserStackLocalArgs.put("forceproxy", "true");
        browserStackLocalArgs.put("force", "true");
        browserStackLocalArgs.put("v", "true");
        browserStackLocalArgs.put("-local-proxy-host", host);
        browserStackLocalArgs.put("-local-proxy-port", port);
        browserStackLocal.start(browserStackLocalArgs);
    }

}
