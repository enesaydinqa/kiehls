package selenium.web;

import base.BaseWebTest;
import enums.URLFactory;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.proxy.CaptureType;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import selenium.pages.MainPageWeb;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class NYXCostemicMainPage extends BaseWebTest {

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
    public void testMainPageTraffic() {

        proxy.enableHarCaptureTypes(CaptureType.REQUEST_BINARY_CONTENT);
        proxy.newHar("Ana Sayfa - Traffic");

        navigateToURL(URLFactory.MAIN_URL);

        List<HarEntry> entries = proxy.getHar().getLog().getEntries();

        entries
                .forEach(png -> {
                    LOGGER.info(png.getRequest().getUrl());
                    Assert.assertTrue(
                            "Broken : " + png.getRequest().getUrl(),
                            404 > png.getResponse().getStatus());
                });

    }

    @Test
    public void testTheNewestProducts() {

        MainPageWeb mainPage = PageFactory.initElements(driver, MainPageWeb.class);

        proxy.enableHarCaptureTypes(CaptureType.REQUEST_BINARY_CONTENT);
        proxy.newHar("Ana Sayfa - En Yeniler Ürün Testi");

        navigateToURL(URLFactory.MAIN_URL);

        listElementRandomClick(mainPage.getNewestProducts());

        waitElementVisible(mainPage.getProductPricePopup());

    }

    @Test
    public void testProductSalePrice() {

        MainPageWeb mainPage = PageFactory.initElements(driver, MainPageWeb.class);

        proxy.enableHarCaptureTypes(CaptureType.REQUEST_BINARY_CONTENT);
        proxy.newHar("Ana Sayfa - Slider");

        navigateToURL(URLFactory.MAIN_URL);

        IntStream.range(0, mainPage.getProductSalePrices().size())
                .forEach(count -> {
                    Assert.assertNotEquals(0, getText(mainPage.getProductSalePrices().get(count)));
                });

    }


}
