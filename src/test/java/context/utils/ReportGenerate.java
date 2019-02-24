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

    @Override
    protected void failed(Throwable e, Description description)
    {
        ExtentReports extent = createReport();
        ExtentTest test = extent.startTest(description.getDisplayName(), "Test failed, click here for further details");

        test.log(LogStatus.FAIL,  session +  " -- Failure trace Selenium : "  + e.toString());
        flushReports(extent, test);
    }

    @Override
    protected void succeeded(Description description)
    {
        ExtentReports extent = createReport();
        ExtentTest test = extent.startTest(description.getDisplayName(), "-");

        test.log(LogStatus.PASS, session);
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
