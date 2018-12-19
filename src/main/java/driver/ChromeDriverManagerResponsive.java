package driver;

import java.net.URL;
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

public class ChromeDriverManagerResponsive extends DriverManager {
    private Logger LOGGER = Logger.getLogger(ChromeDriverManagerResponsive.class.getName());
    private String USER_AGENT = "Mozilla/5.0 (Linux; Android 7.0; SM-G930V Build/NRD90M) AppleWebKit/537.36 (KHTML, " +
            "like Gecko) Chrome/59.0.3071.125 Mobile Safari/537.36";

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
        String port = seleniumProxy.getHttpProxy().substring(seleniumProxy.getHttpProxy().indexOf(":") + 1, seleniumProxy.getHttpProxy().length());
        browserStackLocalArgs.put("-local-proxy-host", host);
        browserStackLocalArgs.put("-local-proxy-port", port);
        browserStackLocal.start(browserStackLocalArgs);

        LOGGER.info("=================================================================");
        LOGGER.info("This Execute Browser Host --> " + host);
        LOGGER.info("This Execute Browser Port --> " + port);
        LOGGER.info("=================================================================");

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
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        if (REMOTE_TEST.equals("true")) {
            capabilities.setCapability("browserstack.local", true);
            driver = new RemoteWebDriver(new URL(BROWSER_STACK_URL), capabilities);
        } else {

            if (Platform.getCurrent().is(Platform.MAC)) {
                System.setProperty("webdriver.chrome.driver", LoadProperties.config.getProperty("forMacChromeDriver"));
            } else if (Platform.getCurrent().is(Platform.WINDOWS)) {
                System.setProperty("webdriver.chrome.driver", LoadProperties.config.getProperty("forWinChromeDriver"));
            }

            driver = new ChromeDriver(capabilities);
        }

        driver.manage().window().setSize(new Dimension(414, 736));

        String session = (driver).getSessionId().toString();
        LOGGER.info("=================================================================");
        LOGGER.info("This Execute Session ID --> " + session);
        LOGGER.info("=================================================================");

    }

}
