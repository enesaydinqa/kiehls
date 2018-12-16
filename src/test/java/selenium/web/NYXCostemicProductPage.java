package selenium.web;

import base.BaseWebTest;
import enums.URLFactory;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.proxy.CaptureType;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.support.PageFactory;
import selenium.pages.MainPageWeb;
import selenium.pages.ProductDetailPage;

import java.util.List;
import java.util.stream.IntStream;

@DisplayName("NYX Costemic Product Page - Web")
public class NYXCostemicProductPage extends BaseWebTest
{
    Logger LOGGER = Logger.getLogger(NYXCostemicProductPage.class.getName());

    @Test
    @Ignore
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
                    LOGGER.info("[Assert] " +png.getRequest().getUrl());
                    Assert.assertTrue(
                            "Broken : " + png.getRequest().getUrl(),
                            400 > png.getResponse().getStatus());
                });
    }

    @Test
    @Ignore
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
