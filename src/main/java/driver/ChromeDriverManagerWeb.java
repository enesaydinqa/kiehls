package driver;

import java.net.URL;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.browserstack.local.Local;

import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import properties.LoadProperties;

public class ChromeDriverManagerWeb extends DriverManager
{

    private Logger LOGGER = Logger.getLogger(ChromeDriverManagerWeb.class.getName());

    protected void createDriver() throws Exception
    {
        int port = proxy.getPort();

        LOGGER.info("This Execute Browser Port --> " + port);

        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        Local browserStackLocal = new Local();
        HashMap<String, String> browserStackLocalArgs = new HashMap<>();
        browserStackLocalArgs.put("key", AUTOMATE_KEY);
        browserStackLocalArgs.put("forcelocal", "true");
        browserStackLocalArgs.put("forceproxy", "true");
        browserStackLocalArgs.put("force", "true");
        browserStackLocalArgs.put("v", "true");
        String host = seleniumProxy.getHttpProxy().substring(0, seleniumProxy.getHttpProxy().indexOf(":"));
        String browserMobPort = seleniumProxy.getHttpProxy().substring(seleniumProxy.getHttpProxy().indexOf(":") + 1);
        browserStackLocalArgs.put("-local-proxy-host", host);
        browserStackLocalArgs.put("-local-proxy-port", browserMobPort);
        browserStackLocal.start(browserStackLocalArgs);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-infobars");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
        capabilities.setCapability("browser", "Chrome");
        capabilities.setCapability("browser_version", "70.0");

        if (REMOTE_TEST.equals("true"))
        {
            capabilities.setCapability("browserstack.local", "true");
            capabilities.setCapability("browserstack.networkLogs", "true");
            capabilities.setCapability("browserstack.debug", "true");
            capabilities.setCapability("browserstack.seleniumLogs", "true");

            capabilities.setBrowserName("chrome");

            driver = new RemoteWebDriver(new URL(BROWSER_STACK_URL), capabilities);

        }
        else
        {

            if (Platform.getCurrent().is(Platform.MAC))
            {
                System.setProperty("webdriver.chrome.driver", LoadProperties.config.getProperty("forMacChromeDriver"));
            }
            else if (Platform.getCurrent().is(Platform.WINDOWS))
            {
                System.setProperty("webdriver.chrome.driver", LoadProperties.config.getProperty("forWinChromeDriver"));
            }

            driver = new ChromeDriver(capabilities);

        }

    }

}
