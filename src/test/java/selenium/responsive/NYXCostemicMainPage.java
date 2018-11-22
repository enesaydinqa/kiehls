package selenium.responsive;

import base.BaseResponsiveTest;
import enums.UrlFactory;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.proxy.CaptureType;
import org.junit.Assert;
import org.junit.Test;

import org.openqa.selenium.support.PageFactory;
import selenium.pages.MainPageResponsive;

import java.util.List;
import java.util.logging.Logger;

public class NYXCostemicMainPage extends BaseResponsiveTest {

    Logger LOGGER = Logger.getLogger(NYXCostemicMainPage.class.getName());


    @Test
    public void testMainPageLoadPNG() {

        proxy.enableHarCaptureTypes(CaptureType.REQUEST_BINARY_CONTENT);
        proxy.newHar("Ana Sayfa - Request PNG Link");

        navigateToURL(UrlFactory.MAIN_URL);

        List<HarEntry> entries = proxy.getHar().getLog().getEntries();

        entries.stream().filter(link -> link.getRequest().getUrl().contains(".png"))
                .forEach(png -> {
                    LOGGER.info(png.getRequest().getUrl());
                    Assert.assertEquals(200, png.getResponse().getStatus());
                });

    }

    @Test
    public void testMainPageBrokenLink() {

        proxy.enableHarCaptureTypes(CaptureType.REQUEST_BINARY_CONTENT);
        proxy.newHar("Ana Sayfa - Request Link");

        navigateToURL(UrlFactory.MAIN_URL);

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
    public void testTheNewestProducts() {

        MainPageResponsive mainPage = PageFactory.initElements(driver, MainPageResponsive.class);

        proxy.enableHarCaptureTypes(CaptureType.REQUEST_BINARY_CONTENT);
        proxy.newHar("Ana Sayfa - En Yeniler Ürün Testi");

        navigateToURL(UrlFactory.MAIN_URL);

        listElementRandomClick(mainPage.getNewestProducts());

    }

}
