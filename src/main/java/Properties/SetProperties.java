package Properties;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

public class SetProperties {

    public static void SetValueProperties() throws Exception {

        Properties prop = new Properties();
        OutputStream output = null;
        output = new FileOutputStream(System.getProperty("user.dir") + "//src//main//resources//Properties//config.Properties");

        prop.setProperty("forWinChromeDriver", System.getProperty("user.dir") + "/Drivers/chromedriver.exe");
        prop.setProperty("forMacChromeDriver", System.getProperty("user.dir") + "/Drivers/chromedriver");
        prop.setProperty("forWinFirefoxDriver", System.getProperty("user.dir") + "/Drivers/geckodriver.exe");
        prop.setProperty("forMacFirefoxDriver", System.getProperty("user.dir") + "/Drivers/geckodriver");
        prop.setProperty("ImplicitlyWait", "20");
        prop.setProperty("WaitTimeOutSeconds", "20");
        prop.setProperty("PageLoadTimeOut", "20");
        prop.setProperty("BaseURL", "http://eminevim.com.tr/");

        prop.store(output, null);

    }
}
