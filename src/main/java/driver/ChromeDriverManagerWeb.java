package driver;

import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import properties.LoadProperties;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

public class ChromeDriverManagerWeb extends DriverManager {

    private Logger LOGGER = Logger.getLogger(ChromeDriverManagerWeb.class.getName());

    protected void createDriver() throws MalformedURLException {

        proxy = new BrowserMobProxyServer();
        proxy.start();
        int port = proxy.getPort();

        LOGGER.info("This Execute Browser Port --> " + port);

        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("--ignore-certificate-errors");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

        if (REMOTE_TEST.equals("true")) {

            capabilities.setBrowserName("chrome");
            capabilities.setPlatform(Platform.MAC);

            LOGGER.info("BS_USERNAME : '" + USERNAME + "'");
            LOGGER.info("BS_AUTOMATEKEY : '" + AUTOMATE_KEY + "'");

            driver = new RemoteWebDriver(new URL(BrowserStackURL), capabilities);

        } else {

            if (Platform.getCurrent().is(Platform.MAC)) {
                System.setProperty("webdriver.chrome.driver", LoadProperties.config.getProperty("forMacChromeDriver"));
            } else if (Platform.getCurrent().is(Platform.WINDOWS)) {
                System.setProperty("webdriver.chrome.driver", LoadProperties.config.getProperty("forWinChromeDriver"));
            }

            driver = new ChromeDriver(capabilities);

        }

    }

}
