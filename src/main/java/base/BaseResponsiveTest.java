package base;

import static properties.LoadProperties.LoadConfigProperty;
import static properties.SetProperties.SetValueProperties;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

import driver.DriverManager;
import driver.DriverResponsiveTestFactory;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.core.har.Har;
import properties.LoadProperties;
import recorder.VideoRecorder;

public abstract class BaseResponsiveTest extends AbstractSeleniumTest
{

    private final static Logger LOGGER = Logger.getLogger(BaseResponsiveTest.class.getName());


    private VideoRecorder videoRecorder;
    String TAKEAVIDEO = System.getProperty("TakeVideo").toLowerCase();

    @Rule
    public final TestName testName = new TestName();

    @Override
    protected void createDriver() {
    }

    @Before
    public void init() throws Exception
    {
        proxy = new BrowserMobProxyServer();
        proxy.start(0);

        SetValueProperties();
        LoadConfigProperty();

        DriverManager driverManager;
        driverManager = DriverResponsiveTestFactory.getManager();
        driver = driverManager.getDriver();


        if (TAKEAVIDEO.equals("true"))
        {
            videoRecorder.startRecording(testName.getMethodName());
        }
        else
        {
            LOGGER.info("Scenarios will not take video");
        }

    }


    @After
    public void tearDown() throws Exception
    {

        setHarFile(testName.getMethodName());

        if (TAKEAVIDEO.equals("true"))
        {
            VideoRecorder.stopRecording();
        }
        else
        {
        }

        proxy.stop();

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

        String sFileName = LoadProperties.config.getProperty("HarFilePath") + harFileName + ".har";

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
