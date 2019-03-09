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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ChromeDriverManagerResponsive extends DriverManager
{
    private static final Logger logger = Logger.getLogger(ChromeDriverManagerResponsive.class);

    private static Map<String, String> mobileEmulation;
    private ChromeOptions chromeOptions;
    private DesiredCapabilities desiredCapabilities;
    private boolean remoteTest;

    private static final String USER_AGENT = "Mozilla/5.0 (iPhone; CPU iPhone OS 11_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.0 Mobile/15E148 Safari/604.1";

    @Override
    public void createDriver(Boolean withProxy) throws Exception
    {
        remoteTest = configuration.getRemoteTest();

        if (remoteTest)
        {
            logger.info("This test is browserstack execute ...");

            desiredCapabilities = desiredCapabilities(true, withProxy, true, chromeOptions);

            driver = new RemoteWebDriver(new URL(configuration.getBrowserStackUrl()), desiredCapabilities);
        }
        else
        {
            logger.info("This test is local execute ...");

            mobileEmulation = mobileEmulation();
            chromeOptions = chromeOptions(mobileEmulation);
            desiredCapabilities = desiredCapabilities(false, withProxy, false, chromeOptions);

            if (Platform.getCurrent().is(Platform.MAC))
            {
                System.setProperty("webdriver.chrome.driver", configuration.getMacChromeDriver());
            }
            else if (Platform.getCurrent().is(Platform.WINDOWS))
            {
                System.setProperty("webdriver.chrome.driver", configuration.getWindowsChromeDriver());
            }

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
            Random deviceList = new Random();
            int device = deviceList.nextInt(deviceList().size());

            capabilities.setCapability("realMobile", "true");
            capabilities.setCapability("device", deviceList().get(device));
            capabilities.setCapability("acceptSslCerts", "true");
            capabilities.setCapability("browserstack.networkLogs", "true");
            capabilities.setCapability("browserstack.console", "warnings");
            capabilities.setCapability("browserstack.appiumLogs", "true");
            capabilities.setCapability("browserstack.debug", "true");
            capabilities.setCapability("browserstack.networkLogs", "true");
            capabilities.setCapability("autoAcceptAlerts", true);
        }

        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        return capabilities;
    }

    private ChromeOptions chromeOptions(Map<String, String> mobileEmulation)
    {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-popup-blocking");

        if (!remoteTest)
        {
            chromeOptions.addArguments("--user-agent=" + USER_AGENT);
            chromeOptions.setExperimentalOption("mobileEmulation", null);
        }

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
        browserStackLocalArgs.put("key", accessKey);
        browserStackLocalArgs.put("forcelocal", "true");
        browserStackLocalArgs.put("forceproxy", "true");
        browserStackLocalArgs.put("force", "true");
        browserStackLocalArgs.put("v", "true");
        browserStackLocalArgs.put("-local-proxy-host", host);
        browserStackLocalArgs.put("-local-proxy-port", port);
        browserStackLocal.start(browserStackLocalArgs);
    }

    private List<String> deviceList()
    {
        List<String> deviceList = new ArrayList<>();

        deviceList.add("Samsung Galaxy S8");
        deviceList.add("Samsung Galaxy S9 Plus");
        deviceList.add("Samsung Galaxy S6");
        deviceList.add("iPhone 6");
        deviceList.add("iPhone 7 Plus");
        deviceList.add("iPhone X");

        return deviceList;
    }

}
