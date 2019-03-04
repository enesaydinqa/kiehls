package context.utils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import context.annotations.TestDescription;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.lang.reflect.Method;

import static context.driver.DriverManager.session;

public class ReportGenerate extends TestWatcher
{
    private String filenameOfReport = System.getProperty("user.dir") + "/target/nyxcostemicstestresult.html";

    @Override
    protected void failed(Throwable e, Description description)
    {
        ExtentReports extent = createReport();
        ExtentTest test = extent.startTest(description.getDisplayName(), "Test failed, getText here for further details");

        String testDescription = getDescription(description);

        test.log(LogStatus.INFO, "Description : " + testDescription);
        test.log(LogStatus.FAIL, "Session id : " + session);
        test.log(LogStatus.FAIL, "Stack Trace : " + e.toString());
        test.log(LogStatus.FAIL, "Page Source File : " + System.getProperty("user.dir") + "/target/PageSource/" + description.getMethodName() + "-DOM.txt");
        flushReports(extent, test);
    }

    @Override
    protected void succeeded(Description description)
    {
        ExtentReports extent = createReport();
        ExtentTest test = extent.startTest(description.getDisplayName(), "-");

        String testDescription = getDescription(description);

        test.log(LogStatus.INFO, "Description : " + testDescription);
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

    private String getDescription(Description description)
    {
        try
        {
            Method method = description.getTestClass().getMethod(description.getMethodName());

            TestDescription testDescription = method.getAnnotation(TestDescription.class);

            return testDescription.value();
        }
        catch (NoSuchMethodException e1)
        {
            // No Action
        }

        return null;
    }
}
