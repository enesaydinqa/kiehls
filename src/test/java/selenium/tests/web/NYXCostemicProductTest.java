package selenium.tests.web;

import context.base.AbstractNYXCostemicTest;
import net.lightbody.bmp.core.har.HarEntry;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import selenium.pages.UrlFactory;
import selenium.pages.web.MainPageWebPage;
import selenium.pages.web.ProductDetailPage;

import java.util.List;
import java.util.stream.IntStream;

@DisplayName("NYX Costemic Product Page - Web")
public class NYXCostemicProductTest extends AbstractNYXCostemicTest
{
    private static Logger logger = Logger.getLogger(NYXCostemicProductTest.class);

    private MainPageWebPage mainPage;
    private ProductDetailPage productDetailPage;

    @Before
    public void init() throws Exception
    {
        super.init(true);
        mainPage = new MainPageWebPage(driver);
        productDetailPage = new ProductDetailPage(driver);
    }

    @Test
    @DisplayName("Product Detail")
    public void testProductDetail()
    {
        navigateToURL(UrlFactory.MAIN_URL);
        waitElementToBeClickable(mainPage.getMainPageAllProduct().get(1));
        scrollToElement(mainPage.getMainPageAllProduct().get(1));
        click(mainPage.getMainPageAllProduct().get(1));

        List<HarEntry> entries = proxy.getHar().getLog().getEntries();

        entries
                .forEach(png -> {
                    logger.info("Check Response this url -> " + png.getRequest().getUrl());
                    Assert.assertTrue(
                            "Broken : " + png.getRequest().getUrl(),
                            400 > png.getResponse().getStatus());
                });
    }

    @Test
    @DisplayName("Product Description Length")
    public void testProductDescriptionLength()
    {
        navigateToURL(UrlFactory.MAIN_URL);
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
