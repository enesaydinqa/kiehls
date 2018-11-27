package base;

import driver.DriverManager;
import driver.DriverWebTestFactory;
import net.lightbody.bmp.core.har.Har;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import properties.LoadProperties;
import recorder.VideoRecorder;

import java.io.File;
import java.io.IOException;

import static properties.LoadProperties.LoadConfigProperty;
import static properties.SetProperties.SetValueProperties;

public abstract class BaseWebTest extends AbstractSeleniumTest {

    private final static Logger LOGGER = Logger.getLogger(BaseWebTest.class.getName());

    private VideoRecorder videoRecorder;
    String TAKEAVIDEO = System.getProperty("TakeVideo").toLowerCase();


    @Override
    protected void createDriver() {
    }

    @BeforeClass
    public static void localProxyStart() throws IOException
    {

        if (EXEC_COMMAND_BY_JENKINS.equals("true") & REMOTE_TEST.equals("true")) {
            LOGGER.info("Execute Terminal Command -> " + EXEC_LOCAL_PROXY_BY_JENKINS);
            Runtime.getRuntime().exec(EXEC_LOCAL_PROXY_BY_JENKINS);
        } else if (EXEC_COMMAND_BY_JENKINS.equals("false")) {
            LOGGER.info("Execute Terminal Command -> " + EXEC_LOCAL_PROXY);
            Runtime.getRuntime().exec(EXEC_LOCAL_PROXY);
        }
    }

    @Rule
    public final TestName testName = new TestName();

    @Before
    public void init() throws Exception {

        SetValueProperties();
        LoadConfigProperty();

        DriverManager driverManager;
        driverManager = DriverWebTestFactory.getManager();
        driver = driverManager.getDriver();

        if (TAKEAVIDEO.equals("true")) {
            videoRecorder.startRecording(testName.getMethodName());
        } else {
            LOGGER.info("Scenarios will not take video");
        }
    }


    @After
    public void tearDown() throws Exception {

        setHarFile(testName.getMethodName());

        if (TAKEAVIDEO.equals("true")) {
            VideoRecorder.stopRecording();
        } else {
        }

        proxy.stop();

        if (driver != null) {
            driver.quit();
            driver = null;
        }

    }

    // --------

    private void setHarFile(String harFileName) {

        String sFileName = LoadProperties.config.getProperty("HarFilePath") + harFileName + ".har";

        try {
            Har har = proxy.getHar();
            File harFile = new File(sFileName);
            try {
                har.writeTo(harFile);
            } catch (IOException ex) {
                System.out.println(ex.toString());
                System.out.println("Could not find file " + sFileName);
            }
        } catch (Exception ex) {
        }
    }

}
