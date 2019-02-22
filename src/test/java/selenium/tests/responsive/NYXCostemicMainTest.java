package selenium.tests.responsive;

import context.base.AbstractResponsiveTest;
import context.enums.UrlFactory;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.proxy.CaptureType;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import selenium.pages.mobileWeb.MainPageResponsivePage;

import java.util.List;
import java.util.stream.IntStream;

@DisplayName("NYX Costemic Main Page - Responsive")
public class NYXCostemicMainTest extends AbstractResponsiveTest
{

    private static Logger logger = Logger.getLogger(NYXCostemicMainTest.class);

    MainPageResponsivePage mainPage;

    @Before
    public void init() throws Exception
    {
        super.init();
        mainPage = new MainPageResponsivePage(driver);
    }

    @Test
    @DisplayName("Main Page Load PNG")
    public void testMainPageLoadPNG()
    {
        proxy.enableHarCaptureTypes(CaptureType.REQUEST_BINARY_CONTENT);
        proxy.newHar("Ana Sayfa - Request PNG Link");

        navigateToURL(UrlFactory.MAIN_URL);

        List<HarEntry> entries = proxy.getHar().getLog().getEntries();

        entries.stream().filter(link -> link.getRequest().getUrl().contains(".png"))
                .forEach(png -> {
                    logger.info("[Assert] " + png.getRequest().getUrl());
                    Assert.assertEquals(200, png.getResponse().getStatus());
                });

    }

    @Test
    @DisplayName("The Main Page Traffic")
    public void testMainPageTraffic()
    {
        proxy.enableHarCaptureTypes(CaptureType.REQUEST_BINARY_CONTENT);
        proxy.newHar("Ana Sayfa - Traffic");

        navigateToURL(UrlFactory.MAIN_URL);

        List<HarEntry> entries = proxy.getHar().getLog().getEntries();

        entries
                .forEach(png -> {
                    logger.info("[Assert] " + png.getRequest().getUrl());
                    Assert.assertTrue(
                            "Broken : " + png.getRequest().getUrl(),
                            400 > png.getResponse().getStatus());
                });

    }


    @Test
    @Ignore
    @DisplayName("Main Page Slider")
    public void testMainPageSlider()
    {
        navigateToURL(UrlFactory.MAIN_URL);
        IntStream.range(0, 3)
                .forEach(i ->
                {
                    waitElementVisible(mainPage.getActiveSliderImage());
                    String dataGtmPromotion1 = getAttribute(mainPage.getActiveSliderImage(), "data-swiper-slide-index");

                    mouseOver(mainPage.getSliderNext());
                    waitElementToBeClickable(mainPage.getSliderNext());
                    click(mainPage.getSliderNext());

                    Assert.assertNotEquals(dataGtmPromotion1, getAttribute(mainPage.getActiveSliderImage(), "data" +
                            "-swiper-slide-index"));
                    wait(1);
                });
    }

}
