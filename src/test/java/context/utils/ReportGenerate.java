package context.utils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import static context.driver.DriverManager.session;

public class ReportGenerate extends TestWatcher
{
    private String filenameOfReport = System.getProperty("user.dir") + "/target/nyxcostemicstestresult.html";
    private Boolean remoteTest = Boolean.parseBoolean(System.getProperty("remote.test"));

    @Override
    protected void failed(Throwable e, Description description)
    {
        ExtentReports extent = createReport();
        ExtentTest test = extent.startTest(description.getDisplayName(), "Test failed, click here for further details");

        test.log(LogStatus.FAIL, "Session id : " + session);
        test.log(LogStatus.FAIL, e.toString());
        if (remoteTest)
            test.log(LogStatus.FAIL, "Page Source File : http://strong.kroppa.com:8080/job/Run_Single_Test/ws/target/PageSource/" + description.getMethodName() + "-DOM.txt");
        if (!remoteTest)
            test.log(LogStatus.FAIL, "Page Source File : " + System.getProperty("user.dir") + "/target/PageSource/" + description.getMethodName() + "-DOM.txt");
        flushReports(extent, test);
    }

    @Override
    protected void succeeded(Description description)
    {
        ExtentReports extent = createReport();
        ExtentTest test = extent.startTest(description.getDisplayName(), "-");

        test.log(LogStatus.PASS, "Session id : " + session);
        flushReports(extent, test);
    }


    private ExtentReports createReport()
    {
        ExtentReports extent = new ExtentReports(filenameOfReport, false);
        extent.config().reportName("NYX Costemics Regression Tests");
        extent.config().reportHeadline("Regression Test Results");
        return extent;
    }

    private void flushReports(ExtentReports extent, ExtentTest test)
    {
        extent.endTest(test);
        extent.flush();
    }
}
