package base;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;

import com.galenframework.api.Galen;
import com.galenframework.config.GalenConfig;
import com.galenframework.config.GalenProperty;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.model.LayoutReport;

import properties.LoadProperties;

public abstract class AbstractLayoutDesignTest extends AbstractSeleniumTest
{
    protected void checkLayoutDesign(String specFile, List<String> includedTags, String className) throws IOException
    {
        GalenConfig.getConfig().setProperty(GalenProperty.SCREENSHOT_FULLPAGE, "true");

        LayoutReport layoutReport = Galen.checkLayout(driver, specFile, includedTags);

        List<GalenTestInfo> tests = new LinkedList<>();

        GalenTestInfo test = GalenTestInfo.fromString(className);
        test.getReport().layout(layoutReport, "check layout on desktop");
        tests.add(test);

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
        Date date = new Date();

        new HtmlReportBuilder().build(tests,
                LoadProperties.config.getProperty("GalenReportPath") + className + dateFormat.format(date));

        if (layoutReport.errors() > 0)
        {
            Assert.fail("Incorrect layout: " + specFile);
        }

    }

    public enum PlatformName
    {
        DESKTOP("desktop"),
        MOBILE("mobile");

        public final String platformName;

        PlatformName(String platformName)
        {
            this.platformName = platformName;
        }
    }


}
