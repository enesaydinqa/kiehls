package Driver;

public class DriverManagerFactory {

    public static DriverManager getManager() {

        DriverManager driverManager;

        String type = System.getProperty("BrowserType");

        switch (type) {

            case "Chrome":
                driverManager = new ChromeDriverManager();
                break;

            case "Firefox":
                driverManager = new FirefoxDriverManager();
                break;

            default:
                driverManager = new ChromeDriverManager();
                break;
        }

        return driverManager;
    }
}
