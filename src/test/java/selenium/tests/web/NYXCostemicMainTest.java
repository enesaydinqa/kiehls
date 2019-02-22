package selenium.tests.web;

import context.base.AbstractNYXCostemicTest;
import selenium.pages.UrlFactory;
import net.lightbody.bmp.core.har.HarEntry;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import selenium.pages.web.MainPageWebPage;

import java.util.List;
import java.util.stream.IntStream;

@DisplayName("NYX Costemic Main Page - Web")
public class NYXCostemicMainTest extends AbstractNYXCostemicTest
{
    private static Logger logger = Logger.getLogger(NYXCostemicMainTest.class);

    private MainPageWebPage mainPage;

    @Before
    public void init() throws Exception
    {
        super.init(true);
        mainPage = new MainPageWebPage(driver);
    }

    @Test
    @DisplayName("Main Page Load PNG")
    public void testMainPageLoadPNG()
    {
        navigateToURL(UrlFactory.MAIN_URL);

        List<HarEntry> entries = proxy.getHar().getLog().getEntries();

        entries.stream().filter(link -> link.getRequest().getUrl().contains(".png"))
                .forEach(png -> {
                    logger.info("Check this Url " + png.getRequest().getUrl());
                    Assert.assertEquals("This png not load",200, png.getResponse().getStatus());
                });
    }

    @Test
    @DisplayName("The Main Page Traffic")
    public void testMainPageTraffic()
    {
        navigateToURL(UrlFactory.MAIN_URL);

        List<HarEntry> entries = proxy.getHar().getLog().getEntries();

        entries.stream()
                .forEach(png -> {
                    logger.info("[Assert] " + png.getRequest().getUrl());
                    Assert.assertTrue(
                            "Broken : " + png.getRequest().getUrl(),
                            400 > png.getResponse().getStatus());
                });
    }

    @Test
    @DisplayName("The Newest Products")
    public void testTheNewestProducts()
    {
        navigateToURL(UrlFactory.MAIN_URL);
        wait(3);
        waitElementVisible(mainPage.getMainPageFancyBoxIFrame());
        switchFrame(mainPage.getMainPageFancyBoxIFrame());
        waitElementToBeClickable(mainPage.getMainPageBeInformed());
        click(mainPage.getMainPageBeInformed());
        driver.switchTo().parentFrame();
        listElementRandomClick(mainPage.getNewestProducts());
        waitElementVisible(mainPage.getBreadCrumb());
        mainPage.getBreadCrumb().isDisplayed();
    }

    @Test
    @DisplayName("Product Sale Price")
    public void testProductSalePrice()
    {
        navigateToURL(UrlFactory.MAIN_URL);
        IntStream.range(0, mainPage.getProductSalePrices().size())
                .forEach(count -> {
                    Assert.assertNotEquals(0, getText(mainPage.getProductSalePrices().get(count)));
                });
    }

    @Test
    @DisplayName("Main Page Slider")
    public void testMainPageSlider()
    {
        navigateToURL(UrlFactory.MAIN_URL);
        IntStream.range(0, 3)
                .forEach(i ->
                {
                    waitElementVisible(mainPage.getActiveSliderImage());
                    String dataGtmPromotion = getAttribute(mainPage.getActiveSliderImage(), "data-swiper-slide-index");

                    waitElementToBeClickable(mainPage.getSliderNext().get(i));
                    click(mainPage.getSliderNext().get(i));

                    Assert.assertNotEquals(dataGtmPromotion, getAttribute(mainPage.getActiveSliderImage(), "data" +
                            "-swiper-slide-index"));
                });
    }


}
