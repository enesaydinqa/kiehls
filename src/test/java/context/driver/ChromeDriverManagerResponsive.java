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

    private String USER_AGENT = "Mozilla/5.0 (Linux; Android 7.0; SM-G930V Build/NRD90M) AppleWebKit/537.36 (KHTML, " +
            "like Gecko) Chrome/59.0.3071.125 Mobile Safari/537.36";

    @Override
    public void createDriver(Boolean withProxy) throws Exception
    {

        Local browserStackLocal = new Local();
        HashMap<String, String> bsLocalArgs = new HashMap<>();

        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("browserName", "iPhone");
        mobileEmulation.put("device", "iPhone 8 Plus");
        mobileEmulation.put("realMobile", "true");
        mobileEmulation.put("version", "70.0");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("--user-agent=" + USER_AGENT);
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

        if (withProxy)
        {
            Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
            capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

            String host = seleniumProxy.getHttpProxy().substring(0, seleniumProxy.getHttpProxy().indexOf(":"));
            String port = seleniumProxy.getHttpProxy().substring(seleniumProxy.getHttpProxy().indexOf(":") + 1, seleniumProxy.getHttpProxy().length());
            bsLocalArgs.put("-local-proxy-host", host);
            bsLocalArgs.put("-local-proxy-port", port);

            logger.info("=================================================================");
            logger.info("This Execute Browser Host --> " + host);
            logger.info("This Execute Browser Port --> " + port);
            logger.info("=================================================================");
        }

        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        /*
        bsLocalArgs.put("key", prop.getProperty("automate.key"));
        bsLocalArgs.put("forcelocal", "true");
        bsLocalArgs.put("forceproxy", "true");
        bsLocalArgs.put("force", "true");
        bsLocalArgs.put("v", "true");
        browserStackLocal.start(bsLocalArgs);
        */

        if (Boolean.parseBoolean(prop.getProperty("remote.test")))
        {
            capabilities.setCapability("browserstack.local", true);
            driver = new RemoteWebDriver(new URL(prop.getProperty("browserstack.url")), capabilities);
        }
        else
        {

            if (Platform.getCurrent().is(Platform.MAC))
            {
                System.setProperty("webdriver.chrome.driver", prop.getProperty("mac.chrome.driver"));
            }
            else if (Platform.getCurrent().is(Platform.WINDOWS))
            {
                System.setProperty("webdriver.chrome.driver", prop.getProperty("windows.chrome.driver"));
            }

            driver = new ChromeDriver(capabilities);
        }

        driver.manage().window().setSize(new Dimension(414, 736));

        String session = (driver).getSessionId().toString();
        logger.info("=================================================================");
        logger.info("This Execute Session ID --> " + session);
        logger.info("=================================================================");

    }

}
