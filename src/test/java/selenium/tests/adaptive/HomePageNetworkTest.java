package selenium.tests.adaptive;

import context.annotations.Description;
import context.base.AbstractKiehlsResponsiveTest;
import context.flag.NetworkExecutable;
import context.flag.ParallelExecutable;
import net.lightbody.bmp.core.har.HarEntry;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import selenium.pages.UrlFactory;
import selenium.pages.mobile.MainResponsivePage;

import java.util.List;

public class HomePageNetworkTest extends AbstractKiehlsResponsiveTest
{
    private static final Logger logger = Logger.getLogger(HomePageNetworkTest.class);

    private MainResponsivePage mainPage;

    @Before
    public void init() throws Exception
    {
        super.init(true);

        mainPage = new MainResponsivePage(driver);
    }

    @Test
    @Description("Adaptive - Anasayfa yüklenirken yapılan png/jpg request lerin 200 (ok) olduğunun kontrolü")
    @Category({NetworkExecutable.class, ParallelExecutable.class})
    public void testAdaptiveHomePageLoadPNG()
    {
        campaingClose();

        pageLongDownScroll();
        wait(DEFAULT_WAIT_A_MOMENT_SECONDS);

        List<HarEntry> entries = proxy.getHar().getLog().getEntries();

        entries.stream().filter(link -> link.getRequest().getUrl().contains(".png") | link.getRequest().getUrl().contains(".jpg"))
                .forEach(png -> {
                    logger.info("Check Response this url -> " + png.getRequest().getUrl());
                    Assert.assertTrue("HTTP Request Error :" + png.getRequest().getUrl(), png.getResponse().getStatus()>200);
                });
    }

    @Test
    @Description("Adaptive - Anasayfa yüklenirken yapılan request lerin response larının 400 den küçük olduğunun kontrolü")
    @Category(ParallelExecutable.class)
    public void testAdaptiveHomePageNetwork()
    {
        campaingClose();

        pageLongDownScroll();
        wait(DEFAULT_WAIT_SECONDS);
        List<HarEntry> entries = proxy.getHar().getLog().getEntries();

        entries.forEach(png -> {
            logger.info("Check Response This Url -> " + png.getRequest().getUrl());
            Assert.assertTrue("HTTP Request Error : " + png.getRequest().getUrl(), 400 > png.getResponse().getStatus());
        });
    }
}
