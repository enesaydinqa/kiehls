package context.driver;

import net.lightbody.bmp.BrowserMobProxy;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Properties;

public abstract class DriverManager
{

    protected static RemoteWebDriver driver;
    protected static BrowserMobProxy proxy;
    public static String session;
    public static Properties prop;

    protected void createDriver(Boolean withProxy) throws Exception
    {
    }

    public RemoteWebDriver getDriver(Boolean withProxy) throws Exception
    {
        if (driver == null)
        {
            prop = new Properties();

            prop.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));

            createDriver(withProxy);

            PropertyConfigurator.configure(this.getClass().getClassLoader().getResource("log4j.properties").getPath());
        }
        return driver;
    }
}
