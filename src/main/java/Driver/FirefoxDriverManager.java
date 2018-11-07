package Driver;

import Properties.LoadProperties;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FirefoxDriverManager extends DriverManager {

    @Override
    public void createDriver() {


        FirefoxProfile firefoxOptions = new FirefoxProfile();
        firefoxOptions.setPreference("dom.webnotifications.enabled", false);

        if (Platform.getCurrent().is(Platform.MAC)) {
            System.setProperty("webdriver.gecko.driver", LoadProperties.config.getProperty("forMacFirefoxDriver"));
        } else if (Platform.getCurrent().is(Platform.WINDOWS)) {
            System.setProperty("webdriver.gecko.driver", LoadProperties.config.getProperty("forWinFirefoxDriver"));
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, firefoxOptions);

        driver = new FirefoxDriver(capabilities);
        driver.manage().window().maximize();
    }
}
