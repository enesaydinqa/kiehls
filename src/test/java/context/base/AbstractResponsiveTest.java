package context.base;

import context.driver.DriverManager;
import context.driver.DriverResponsiveTestFactory;
import context.properties.SetProperties;
import context.recorder.VideoRecorder;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public abstract class AbstractResponsiveTest extends AbstractSeleniumTest
{
    private Logger logger = Logger.getLogger(AbstractResponsiveTest.class);

    private boolean takeVideo;

    @Rule
    public final TestName testName = new TestName();

    @Override
    protected void createDriver(Boolean withProxy)
    {
    }

    @BeforeClass
    public static void setProp() throws Exception
    {
        SetProperties setProp = new SetProperties();
        setProp.setProperties();
    }

    @Before
    public void init() throws Exception
    {
        init(false);
    }

    public void init(Boolean withProxy) throws Exception
    {
        DriverManager driverManager;
        driverManager = DriverResponsiveTestFactory.getManager();

        if (withProxy)
        {
            Runtime.getRuntime().exec("browserstacklocal/BrowserStackLocal --key " + prop.getProperty("automate.key"));

            proxy = new BrowserMobProxyServer();
            proxy.start();
            proxy.enableHarCaptureTypes(CaptureType.REQUEST_BINARY_CONTENT);
            proxy.newHar();
        }

        driver = driverManager.getDriver(withProxy);

        takeVideo = Boolean.parseBoolean(prop.getProperty("take.a.video"));

        if (takeVideo)
        {
            VideoRecorder.startRecording(testName.getMethodName());
        }
        else
        {
            logger.info("Scenarios will not take video");
        }

        logger.info("=================================================================");
        logger.info("TEST STARTED ... -> " + testName.getMethodName());
        logger.info("=================================================================");
    }


    @After
    public void tearDown() throws Exception
    {
        //setHarFile(testName.getMethodName());
        takeVideo = Boolean.parseBoolean(prop.getProperty("take.a.video"));

        if (takeVideo) VideoRecorder.stopRecording();

        try
        {
            if (proxy != null) proxy.stop();
        }
        catch (IllegalStateException ex)
        {
            logger.info("Already Stopped Proxy");
        }


        if (driver != null)
        {
            driver.close();
            driver.quit();
            driver = null;
        }
    }


    // --------

    private void setHarFile(String harFileName)
    {

        String sFileName = prop.getProperty("har.file.path") + harFileName + ".har";

        try
        {
            Har har = proxy.getHar();
            File harFile = new File(sFileName);
            try
            {
                har.writeTo(harFile);
            }
            catch (IOException ex)
            {
                System.out.println(ex.toString());
                System.out.println("Could not find file " + sFileName);
            }
        }
        catch (Exception ex)
        {
        }
    }
}

