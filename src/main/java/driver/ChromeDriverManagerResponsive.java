package driver;

import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import properties.LoadProperties;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ChromeDriverManagerResponsive extends DriverManager {

    private Logger LOGGER = Logger.getLogger(ChromeDriverManagerResponsive.class.getName());

    @Override
    public void createDriver() throws IOException {

        if (EXEC_COMMAND_BY_JENKINS.equals("true")) {
            Runtime.getRuntime().exec(EXEC_LOCAL_PROXY_BY_JENKINS);
        } else if (EXEC_COMMAND_BY_JENKINS.equals("false")) {
            Runtime.getRuntime().exec(EXEC_LOCAL_PROXY);
        }

        proxy = new BrowserMobProxyServer();
        proxy.start();
        int port = proxy.getPort();

        LOGGER.info("This Execute Browser Port --> " + port);

        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "Pixel 2");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

        if (REMOTE_TEST.equals("true")) {

            capabilities.setCapability("browserstack.local", "true");
            capabilities.setCapability("browserstack.networkLogs", "true");


            capabilities.setBrowserName("chrome");
            capabilities.setPlatform(Platform.MAC);

            LOGGER.info("BS_USERNAME : '" + USERNAME + "'");
            LOGGER.info("BS_AUTOMATEKEY : '" + AUTOMATE_KEY + "'");

            driver = new RemoteWebDriver(new URL(BROWSER_STACK_URL), capabilities);
            driver.manage().window().setSize(new Dimension(414, 736));

        } else {

            if (Platform.getCurrent().is(Platform.MAC)) {
                System.setProperty("webdriver.chrome.driver", LoadProperties.config.getProperty("forMacChromeDriver"));
            } else if (Platform.getCurrent().is(Platform.WINDOWS)) {
                System.setProperty("webdriver.chrome.driver", LoadProperties.config.getProperty("forWinChromeDriver"));
            }

            driver = new ChromeDriver(capabilities);
            driver.manage().window().setSize(new Dimension(414, 736));

        }

    }

}
