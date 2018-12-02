package selenium.responsive;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.support.PageFactory;

import base.BaseResponsiveTest;
import base.BaseWebTest;
import enums.URLFactory;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.proxy.CaptureType;
import selenium.pages.MainPageResponsive;
import selenium.pages.MainPageWeb;
import selenium.pages.ProductDetailPage;

@DisplayName("NYX Costemic Product Page - Responsive")
public class NYXCostemicProductPage extends BaseResponsiveTest
{
    Logger LOGGER = Logger.getLogger(NYXCostemicProductPage.class.getName());

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
}
