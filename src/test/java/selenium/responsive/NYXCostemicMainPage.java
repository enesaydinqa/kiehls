package selenium.responsive;

import base.BaseResponsiveTest;
import enums.URLFactory;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.proxy.CaptureType;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.support.PageFactory;

import selenium.pages.MainPageResponsive;
import selenium.pages.MainPageWeb;

import java.util.List;
import java.util.stream.IntStream;

@DisplayName("NYX Costemic Main Page - Responsive")
public class NYXCostemicMainPage extends BaseResponsiveTest
{

    Logger LOGGER = Logger.getLogger(NYXCostemicMainPage.class.getName());


    @Test
    @DisplayName("Main Page Load PNG")
    public void testMainPageLoadPNG()
    {

        proxy.enableHarCaptureTypes(CaptureType.REQUEST_BINARY_CONTENT);
        proxy.newHar("Ana Sayfa - Request PNG Link");

        navigateToURL(URLFactory.MAIN_URL);

        List<HarEntry> entries = proxy.getHar().getLog().getEntries();

        entries.stream().filter(link -> link.getRequest().getUrl().contains(".png"))
                .forEach(png -> {
                    LOGGER.info(png.getRequest().getUrl());
                    Assert.assertEquals(200, png.getResponse().getStatus());
                });

    }

    @Test
    @DisplayName("The Main Page Traffic")
    public void testMainPageTraffic()
    {

        proxy.enableHarCaptureTypes(CaptureType.REQUEST_BINARY_CONTENT);
        proxy.newHar("Ana Sayfa - Traffic");

        navigateToURL(URLFactory.MAIN_URL);

        List<HarEntry> entries = proxy.getHar().getLog().getEntries();

        entries
                .forEach(png -> {
                    LOGGER.info(png.getRequest().getUrl());
                    Assert.assertTrue(
                            "Broken : " + png.getRequest().getUrl(),
                            400 > png.getResponse().getStatus());
                });

    }

    @Test
    @DisplayName("Product Sale Price")
    public void testProductSalePrice()
    {

        MainPageResponsive mainPage = PageFactory.initElements(driver, MainPageResponsive.class);

        navigateToURL(URLFactory.MAIN_URL);

        IntStream.range(0, mainPage.getProductSalePrices().size())
                .forEach(count -> {
                    Assert.assertNotEquals(0, getText(mainPage.getProductSalePrices().get(count)));
                });

    }

    @Test
    @DisplayName("Main Page Slider")
    public void testMainPageSlider()
    {

        MainPageResponsive mainPage = PageFactory.initElements(driver, MainPageResponsive.class);

        navigateToURL(URLFactory.MAIN_URL);


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

                    try
                    {
                        wait(1);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                });
    }

}
