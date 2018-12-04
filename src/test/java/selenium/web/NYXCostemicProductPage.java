package selenium.web;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.support.PageFactory;

import base.BaseWebTest;
import enums.URLFactory;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.proxy.CaptureType;
import selenium.pages.MainPageWeb;
import selenium.pages.ProductDetailPage;

@DisplayName("NYX Costemic Product Page - Web")
public class NYXCostemicProductPage extends BaseWebTest
{
    Logger LOGGER = Logger.getLogger(NYXCostemicProductPage.class.getName());

    @Test
    @DisplayName("Product Detail")
    public void testProductDetail()
    {
        MainPageWeb mainPage = PageFactory.initElements(driver, MainPageWeb.class);

        proxy.enableHarCaptureTypes(CaptureType.REQUEST_BINARY_CONTENT);
        proxy.newHar("Ana Sayfa - Product Detail");

        navigateToURL(URLFactory.MAIN_URL);

        waitElementToBeClickable(mainPage.getMainPageAllProduct().get(1));
        scrollToElement(mainPage.getMainPageAllProduct().get(1));
        click(mainPage.getMainPageAllProduct().get(1));


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
    @DisplayName("Product Description Length")
    public void testProductDescriptionLength()
    {
        MainPageWeb mainPage = PageFactory.initElements(driver, MainPageWeb.class);
        ProductDetailPage productDetailPage = PageFactory.initElements(driver, ProductDetailPage.class);

        navigateToURL(URLFactory.MAIN_URL);

        IntStream.range(4, 7)
                .forEach(i ->
                {
                    scrollToElement(mainPage.getMainPageAllProduct().get(i));
                    waitElementToBeClickable(mainPage.getMainPageAllProduct().get(i));
                    click(mainPage.getMainPageAllProduct().get(i));

                    waitElementVisible(productDetailPage.getProductDetail());
                    String description = getText(productDetailPage.getProductDetail());
                    Assert.assertTrue(description.length() >= 20);
                    driver.navigate().back();
                });
    }
}
