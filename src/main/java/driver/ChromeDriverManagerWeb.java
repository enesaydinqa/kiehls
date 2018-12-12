package driver;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.browserstack.local.Local;

import net.lightbody.bmp.client.ClientUtil;
import properties.LoadProperties;

public class ChromeDriverManagerWeb extends DriverManager {
    private Logger LOGGER = Logger.getLogger(ChromeDriverManagerResponsive.class.getName());

    @Override
    public void createDriver() throws Exception {
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        Local browserStackLocal = new Local();
        HashMap<String, String> browserStackLocalArgs = new HashMap<>();
        browserStackLocalArgs.put("key", AUTOMATE_KEY);
        browserStackLocalArgs.put("forcelocal", "true");
        browserStackLocalArgs.put("forceproxy", "true");
        browserStackLocalArgs.put("force", "true");
        browserStackLocalArgs.put("v", "true");
        String host = seleniumProxy.getHttpProxy().substring(0, seleniumProxy.getHttpProxy().indexOf(":"));
        String port = seleniumProxy.getHttpProxy().substring(seleniumProxy.getHttpProxy().indexOf(":") + 1);
        browserStackLocalArgs.put("-local-proxy-host", host);
        browserStackLocalArgs.put("-local-proxy-port", port);
        browserStackLocal.start(browserStackLocalArgs);

        LOGGER.info("=================================================================");
        LOGGER.info("This Execute Browser Host --> " + host);
        LOGGER.info("This Execute Browser Port --> " + port);
        LOGGER.info("=================================================================");

        String[] switches = {"--ignore-certificate-errors"};

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-infobars");

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("chrome.switches", Arrays.asList(switches));
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

        if (REMOTE_TEST.equals("true")) {
            capabilities.setCapability("browserstack.local", "true");
            capabilities.setBrowserName("chrome");

            driver = new RemoteWebDriver(new URL(BROWSER_STACK_URL), capabilities);

        } else {

            if (Platform.getCurrent().is(Platform.MAC)) {
                System.setProperty("webdriver.chrome.driver", LoadProperties.config.getProperty("forMacChromeDriver"));
            } else if (Platform.getCurrent().is(Platform.WINDOWS)) {
                System.setProperty("webdriver.chrome.driver", LoadProperties.config.getProperty("forWinChromeDriver"));
            }

            driver = new ChromeDriver(capabilities);

        }

        String session = (driver).getSessionId().toString();
        LOGGER.info("=================================================================");
        LOGGER.info("This Execute Session ID --> " + session);
        LOGGER.info("=================================================================");
    }

}
