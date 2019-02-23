package context.properties;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

public class SetProperties {

    private Logger logger = Logger.getLogger(SetProperties.class);

    private File filePath = new File("src/test/resources/config.properties");

    public void setProperties() throws Exception {

        logger.info("har.file.path --> " + System.getProperty("user.dir") + "/src/test/resources/");
        logger.info("galen.report.path --> " + System.getProperty("user.dir") + "/target/GalenLayoutReports/");
        logger.info("take.a.video --> " + System.getProperties().getProperty("take.a.video"));
        logger.info("exec.command.by.jenkins --> " + System.getProperties().getProperty("exec.command.by.jenkins"));
        logger.info("jetty.port --> " + System.getProperties().getProperty("jetty.port"));
        logger.info("automate.key --> " + System.getProperties().getProperty("automate.key"));
        logger.info("username --> " + System.getProperties().getProperty("username"));
        logger.info("browser.type --> " + System.getProperties().getProperty("browser.type"));
        logger.info("remote.test --> " + System.getProperties().getProperty("remote.test"));

        Properties prop = new Properties();
        OutputStream output = null;

        output = new FileOutputStream(filePath.getAbsolutePath());

        prop.setProperty("windows.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
        prop.setProperty("mac.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
        prop.setProperty("implicitly.wait", "30");
        prop.setProperty("wait.timeout.seconds", "30");
        prop.setProperty("page.load.timeout", "20");
        prop.setProperty("base.url", "https://www.nyxcosmetics.com.tr/");
        prop.setProperty("har.file.path", System.getProperty("user.dir") + "/src/test/resources/");
        prop.setProperty("galen.report.path", System.getProperty("user.dir") + "/target/GalenLayoutReports/");
        prop.setProperty("take.a.video", System.getProperties().getProperty("take.a.video"));
        prop.setProperty("exec.command.by.jenkins", System.getProperties().getProperty("exec.command.by.jenkins"));
        prop.setProperty("jetty.port", System.getProperties().getProperty("jetty.port"));
        prop.setProperty("automate.key", System.getProperties().getProperty("automate.key"));
        prop.setProperty("username", System.getProperties().getProperty("username"));
        prop.setProperty("browser.type", System.getProperties().getProperty("browser.type"));
        prop.setProperty("remote.test", System.getProperties().getProperty("remote.test"));
        prop.setProperty("browserstack.url", "https://" + System.getProperties().getProperty("username") + ":" + System.getProperties().getProperty("automate.key") + "@hub-cloud.browserstack.com/wd/hub");

        prop.store(output, "NYX Costemics Regression Test Configuration");
    }
}