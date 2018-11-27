package properties;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

public class SetProperties {

    public static void SetValueProperties() throws Exception {

        Properties prop = new Properties();
        OutputStream output = null;
        output = new FileOutputStream(System.getProperty("user.dir") + "/src/main/resources/properties/config.properties");

        prop.setProperty("forWinChromeDriver", System.getProperty("user.dir") + "/Drivers/chromedriver.exe");
        prop.setProperty("forMacChromeDriver", System.getProperty("user.dir") + "/Drivers/chromedriver");
        prop.setProperty("ImplicitlyWait", "30");
        prop.setProperty("WaitTimeOutSeconds", "30");
        prop.setProperty("PageLoadTimeOut", "20");
        prop.setProperty("BaseURL", "https://www.nyxcosmetics.com.tr/");
        prop.setProperty("HarFilePath", System.getProperty("user.dir") +  "/src/main/resources/");

        prop.store(output, null);

    }
}
