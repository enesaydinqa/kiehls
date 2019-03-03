package context.objects;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration
{
    private Properties configProps = new Properties();

    private String browserStackUrl;
    private Double prShippingFree;
    private Double shippingFee;
    private Boolean remoteTest;
    private String browserType;
    private String username;
    private Boolean takeAVideo;
    private String galenReportPath;
    private String harFilePath;
    private String baseUrl;
    private Integer pageLoadTimeout;
    private Integer waitLoadTimeout;
    private Integer implicitlyWait;
    private String macChromeDriver;
    private String windowsChromeDriver;

    public Configuration() throws IOException
    {
        loadConfigProperties();

        this.browserStackUrl = configProps.getProperty("browserstack.url");
        this.prShippingFree = Double.valueOf(System.getProperties().getProperty("pr.for.shipping.free"));
        this.shippingFee = Double.valueOf(System.getProperties().getProperty("shipping.fee"));
        this.remoteTest = Boolean.parseBoolean(System.getProperties().getProperty("remote.test"));
        this.browserType = System.getProperties().getProperty("browser.type");
        this.username = configProps.getProperty("username");
        this.takeAVideo = Boolean.valueOf(System.getProperties().getProperty("take.a.video"));
        this.galenReportPath = configProps.getProperty("galen.report.path");
        this.harFilePath = configProps.getProperty("har.file.path");
        this.baseUrl = configProps.getProperty("base.url");
        this.pageLoadTimeout = Integer.valueOf(configProps.getProperty("page.load.timeout"));
        this.waitLoadTimeout = Integer.valueOf(configProps.getProperty("wait.timeout.seconds"));
        this.implicitlyWait = Integer.valueOf(configProps.getProperty("implicitly.wait"));
        this.macChromeDriver = configProps.getProperty("mac.chrome.driver");
        this.windowsChromeDriver = configProps.getProperty("windows.chrome.driver");
    }

    private void loadConfigProperties() throws IOException
    {
        String configFile = "config.properties";
        InputStream in = ClassLoader.getSystemResourceAsStream(configFile);

        configProps.load(in);
    }

    public String getBrowserStackUrl()
    {
        return browserStackUrl;
    }

    public void setBrowserStackUrl(String browserStackUrl)
    {
        this.browserStackUrl = browserStackUrl;
    }

    public Double getPrShippingFree()
    {
        return prShippingFree;
    }

    public void setPrShippingFree(Double prShippingFree)
    {
        this.prShippingFree = prShippingFree;
    }

    public Double getShippingFee()
    {
        return shippingFee;
    }

    public void setShippingFee(Double shippingFee)
    {
        this.shippingFee = shippingFee;
    }

    public Boolean getRemoteTest()
    {
        return remoteTest;
    }

    public void setRemoteTest(Boolean remoteTest)
    {
        this.remoteTest = remoteTest;
    }

    public String getBrowserType()
    {
        return browserType;
    }

    public void setBrowserType(String browserType)
    {
        this.browserType = browserType;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public Boolean getTakeAVideo()
    {
        return takeAVideo;
    }

    public void setTakeAVideo(Boolean takeAVideo)
    {
        this.takeAVideo = takeAVideo;
    }

    public String getGalenReportPath()
    {
        return galenReportPath;
    }

    public void setGalenReportPath(String galenReportPath)
    {
        this.galenReportPath = galenReportPath;
    }

    public String getHarFilePath()
    {
        return harFilePath;
    }

    public void setHarFilePath(String harFilePath)
    {
        this.harFilePath = harFilePath;
    }

    public String getBaseUrl()
    {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl)
    {
        this.baseUrl = baseUrl;
    }

    public Integer getPageLoadTimeout()
    {
        return pageLoadTimeout;
    }

    public void setPageLoadTimeout(Integer pageLoadTimeout)
    {
        this.pageLoadTimeout = pageLoadTimeout;
    }

    public Integer getWaitLoadTimeout()
    {
        return waitLoadTimeout;
    }

    public void setWaitLoadTimeout(Integer waitLoadTimeout)
    {
        this.waitLoadTimeout = waitLoadTimeout;
    }

    public Integer getImplicitlyWait()
    {
        return implicitlyWait;
    }

    public void setImplicitlyWait(Integer implicitlyWait)
    {
        this.implicitlyWait = implicitlyWait;
    }

    public String getMacChromeDriver()
    {
        return macChromeDriver;
    }

    public void setMacChromeDriver(String macChromeDriver)
    {
        this.macChromeDriver = macChromeDriver;
    }

    public String getWindowsChromeDriver()
    {
        return windowsChromeDriver;
    }

    public void setWindowsChromeDriver(String windowsChromeDriver)
    {
        this.windowsChromeDriver = windowsChromeDriver;
    }
}
