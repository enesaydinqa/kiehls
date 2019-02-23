package selenium.tests.responsive;

import context.base.AbstractResponsiveTest;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import selenium.pages.UrlFactory;
import selenium.pages.mobileweb.MainPageResponsivePage;

import java.util.stream.IntStream;

@DisplayName("NYX Costemic Product Page - Responsive")
public class NYXCostemicProductTest extends AbstractResponsiveTest
{
    private static Logger logger = Logger.getLogger(NYXCostemicProductTest.class);

    private MainPageResponsivePage mainPage;

    @Before
    public void init() throws Exception
    {
        super.init();
        mainPage = new MainPageResponsivePage(driver);
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
}
