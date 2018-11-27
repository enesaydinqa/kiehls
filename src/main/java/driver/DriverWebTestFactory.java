package driver;


import org.apache.log4j.Logger;

public class DriverWebTestFactory {

    private final static Logger LOGGER = Logger.getLogger(DriverWebTestFactory.class.getName());

    public static DriverManager getManager() {

        DriverManager driverManager;

        String type = System.getProperty("BrowserType").toLowerCase();

        LOGGER.info("BrowserType (DriverWebTestFactory) : " + System.getProperty("BrowserType"));

        switch (type) {

            case "chrome":
                driverManager = new ChromeDriverManagerWeb();
                break;

            default:
                driverManager = new ChromeDriverManagerWeb();
                break;
        }

        return driverManager;
    }
}
