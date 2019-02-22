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

    @Override
    public void createDriver(Boolean withProxy) throws Exception
    {
        Local browserStackLocal = new Local();
        HashMap<String, String> bsLocalArgs = new HashMap<>();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-infobars");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browser", "Chrome");
        capabilities.setCapability("os", "Windows");
        capabilities.setCapability("os_version", "10");

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

        String session = (driver).getSessionId().toString();
        logger.info("=================================================================");
        logger.info("This Execute Session ID --> " + session);
        logger.info("=================================================================");
    }

}
