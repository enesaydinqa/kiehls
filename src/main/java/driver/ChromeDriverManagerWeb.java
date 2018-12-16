package driver;

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
import properties.LoadProperties;

import java.net.URL;
import java.util.HashMap;

public class ChromeDriverManagerWeb extends DriverManager {
    private Logger LOGGER = Logger.getLogger(ChromeDriverManagerResponsive.class.getName());

    @Override
    public void createDriver() throws Exception {
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        Local browserStackLocal = new Local();
        HashMap<String, String> browserStackLocalArgs = new HashMap<String, String>();
        browserStackLocalArgs.put("key", AUTOMATE_KEY);
        browserStackLocalArgs.put("forcelocal", "true");
        browserStackLocalArgs.put("forceproxy", "true");
        browserStackLocalArgs.put("force", "true");
        browserStackLocalArgs.put("v", "true");
        String host = seleniumProxy.getHttpProxy().substring(0, seleniumProxy.getHttpProxy().indexOf(":"));
        String port = seleniumProxy.getHttpProxy().substring(seleniumProxy.getHttpProxy().indexOf(":") + 1, seleniumProxy.getHttpProxy().length());
        browserStackLocalArgs.put("-local-proxy-host", host);
        browserStackLocalArgs.put("-local-proxy-port", port);
        browserStackLocal.start(browserStackLocalArgs);

        LOGGER.info("=================================================================");
        LOGGER.info("This Execute Browser Host --> " + host);
        LOGGER.info("This Execute Browser Port --> " + port);
        LOGGER.info("=================================================================");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-infobars");

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "62.0");
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability(CapabilityType.PROXY, seleniumProxy);
        caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        if (REMOTE_TEST.equals("true")) {
            caps.setCapability("browserstack.local", true);

            driver = new RemoteWebDriver(new URL(BROWSER_STACK_URL), caps);

        } else {

            if (Platform.getCurrent().is(Platform.MAC)) {
                System.setProperty("webdriver.chrome.driver", LoadProperties.config.getProperty("forMacChromeDriver"));
            } else if (Platform.getCurrent().is(Platform.WINDOWS)) {
                System.setProperty("webdriver.chrome.driver", LoadProperties.config.getProperty("forWinChromeDriver"));
            }

            driver = new ChromeDriver(caps);

        }

        String session = (driver).getSessionId().toString();
        LOGGER.info("=================================================================");
        LOGGER.info("This Execute Session ID --> " + session);
        LOGGER.info("=================================================================");
    }

}
