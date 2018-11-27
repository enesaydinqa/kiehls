package driver;


import org.apache.log4j.Logger;

public class DriverResponsiveTestFactory {

    private final static Logger LOGGER = Logger.getLogger(DriverResponsiveTestFactory.class.getName());

    public static DriverManager getManager() {

        DriverManager driverManager;

        String type = System.getProperty("BrowserType").toLowerCase();

        LOGGER.info("BrowserType (DriverResponsiveTestFactory) : " + System.getProperty("BrowserType"));

        switch (type) {

            case "chrome":
                driverManager = new ChromeDriverManagerResponsive();
                break;

            default:
                driverManager = new ChromeDriverManagerResponsive();
                break;
        }

        return driverManager;
    }
}
