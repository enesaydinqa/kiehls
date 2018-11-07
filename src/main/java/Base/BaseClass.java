package Base;

import Driver.DriverManager;
import Driver.DriverManagerFactory;
import Recorder.VideoRecorder;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

import java.util.logging.Logger;

import static Properties.LoadProperties.LoadConfigProperty;
import static Properties.SetProperties.SetValueProperties;

public class BaseClass extends AbstractSeleniumCommands {

    private final static Logger LOGGER = Logger.getLogger(BaseClass.class.getName());

    private VideoRecorder videoRecorder;

    @Override
    protected void createDriver() {
    }

    @Rule
    public final TestName testName = new TestName();

    @Before
    public void init() throws Exception {

        SetValueProperties(); // Set Properties File
        LoadConfigProperty(); // init Properties File

        DriverManager driverManager;
        driverManager = DriverManagerFactory.getManager();
        driver = driverManager.getDriver();

        videoRecorder.startRecording(testName.getMethodName()); //  Start Test Video

    }


    @After
    public void tearDown() throws Exception {

        if (driver != null) {
            driver.quit();
        }

        VideoRecorder.stopRecording(); // Stop Test Video

    }

}
