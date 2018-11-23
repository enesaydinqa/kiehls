package driver;

public class DriverWebTestFactory {

    public static DriverManager getManager() {

        DriverManager driverManager;

        System.out.println("BrowserType (DriverWebTestFactory) : " + System.getProperty("BrowserType"));
        String type = System.getProperty("BrowserType").toLowerCase();

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
