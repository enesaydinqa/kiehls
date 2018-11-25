package selenium.responsive;

import base.BaseResponsiveTest;
import enums.URLFactory;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.proxy.CaptureType;
import org.junit.Assert;
import org.junit.Test;

import org.openqa.selenium.support.PageFactory;
import selenium.pages.MainPageResponsive;
import selenium.pages.MainPageWeb;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class NYXCostemicMainPage extends BaseResponsiveTest {

    Logger LOGGER = Logger.getLogger(NYXCostemicMainPage.class.getName());


    @Test
    public void testMainPageLoadPNG() {

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
    public void testMainPageFai() {

        proxy.enableHarCaptureTypes(CaptureType.REQUEST_BINARY_CONTENT);
        proxy.newHar("Ana Sayfa - Traffic");

        navigateToURL(URLFactory.MAIN_URL);

        List<HarEntry> entries = proxy.getHar().getLog().getEntries();

        entries
                .forEach(png -> {
                    LOGGER.info(png.getRequest().getUrl());
                    Assert.assertTrue(
                            "Broken Link " + png.getRequest().getUrl(),
                            404 > png.getResponse().getStatus());
                });

    }

    @Test
    public void testProductSalePrice() {

        MainPageResponsive mainPage = PageFactory.initElements(driver, MainPageResponsive.class);

        proxy.enableHarCaptureTypes(CaptureType.REQUEST_BINARY_CONTENT);
        proxy.newHar("Ana Sayfa - Slider");

        navigateToURL(URLFactory.MAIN_URL);

        IntStream.range(0, mainPage.getProductSalePrices().size())
                .forEach(count -> {
                    Assert.assertNotEquals(0, getText(mainPage.getProductSalePrices().get(count)));
                });

    }

}
