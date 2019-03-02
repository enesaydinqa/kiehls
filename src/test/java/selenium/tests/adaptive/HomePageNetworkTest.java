package selenium.tests.adaptive;

import context.base.AbstractNYXCostemicResponsiveTest;
import net.lightbody.bmp.core.har.HarEntry;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import selenium.pages.UrlFactory;
import selenium.pages.mobile.MainPageResponsivePage;

import java.util.List;

public class HomePageNetworkTest extends AbstractNYXCostemicResponsiveTest
{
    private static Logger logger = Logger.getLogger(HomePageNetworkTest.class);

    private MainPageResponsivePage mainPage;

    @Before
    public void init() throws Exception
    {
        super.init(true);
        mainPage = new MainPageResponsivePage(driver);
    }

    @Test
    @DisplayName("Main Page Load PNG")
    public void testAdaptiveHomePageLoadPNG() throws InterruptedException
    {
        navigateToURL(UrlFactory.MAIN_URL);
        pageLongDownScroll();
        List<HarEntry> entries = proxy.getHar().getLog().getEntries();

        entries.stream().filter(link -> link.getRequest().getUrl().contains(".png") | link.getRequest().getUrl().contains(".jpg"))
                .forEach(png -> {
                    logger.info("Check Response this url -> " + png.getRequest().getUrl());
                    Assert.assertEquals("This image not load " + png.getRequest().getUrl(), 200, png.getResponse().getStatus());
                });
    }

    @Test
    @DisplayName("The Main Page Traffic")
    public void testAdaptiveHomePageNetwork()
    {
        navigateToURL(UrlFactory.MAIN_URL);
        pageLongDownScroll();
        List<HarEntry> entries = proxy.getHar().getLog().getEntries();

        entries.forEach(png -> {
            logger.info("Check Response This Url -> " + png.getRequest().getUrl());
            Assert.assertTrue("Broken : " + png.getRequest().getUrl(), 400 > png.getResponse().getStatus());
        });
    }
}
