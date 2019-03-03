package context.base;

import com.galenframework.api.Galen;
import com.galenframework.config.GalenConfig;
import com.galenframework.config.GalenProperty;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.model.LayoutReport;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public abstract class AbstractLayoutDesignTest extends AbstractSeleniumTest
{
    private static final Logger logger = Logger.getLogger(AbstractLayoutDesignTest.class);

    private List<GalenTestInfo> tests;
    private LayoutReport layoutReport;
    private String reportPath;

    protected void checkLayoutDesign(String specFile, List<String> includedTags, String className) throws IOException
    {
        try
        {
            GalenConfig.getConfig().setProperty(GalenProperty.SCREENSHOT_FULLPAGE, "true");

            layoutReport = Galen.checkLayout(driver, specFile, includedTags);

            tests = new LinkedList<>();

            GalenTestInfo test = GalenTestInfo.fromString(className);
            test.getReport().layout(layoutReport, "check layout on desktop");
            tests.add(test);

            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
            Date date = new Date();

            reportPath = configuration.getGalenReportPath() + className + "-" + dateFormat.format(date);

            new HtmlReportBuilder().build(tests,
                    reportPath);

            if (layoutReport.errors() > 0)
            {
                logger.info("Galen Report Path : " + reportPath);
                Assert.fail("Incorrect layout: " + specFile);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            File folder = new File(reportPath);
            File[] listOfFiles = folder.listFiles();

            List<String> srcFiles = new ArrayList<>();

            IntStream.range(0, listOfFiles.length)
                    .forEach(i -> srcFiles.add(listOfFiles[i].getPath()));

            FileOutputStream fos = new FileOutputStream(reportPath + "/" + className + ".zip");
            ZipOutputStream zipOut = new ZipOutputStream(fos);

            for (String srcFile : srcFiles)
            {
                File fileToZip = new File(srcFile);
                FileInputStream fis = new FileInputStream(fileToZip);
                ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                zipOut.putNextEntry(zipEntry);

                byte[] bytes = new byte[1024];
                int length;
                while ((length = fis.read(bytes)) >= 0)
                {
                    zipOut.write(bytes, 0, length);
                }
                fis.close();
            }

            zipOut.close();
            fos.close();

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
