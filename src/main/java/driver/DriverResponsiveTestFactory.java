package driver;

public class DriverResponsiveTestFactory {

    public static DriverManager getManager() {

        DriverManager driverManager;

        String type = System.getProperty("BrowserType").toLowerCase();

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
