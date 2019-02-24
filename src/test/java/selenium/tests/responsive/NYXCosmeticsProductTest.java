package selenium.tests.responsive;

import context.base.AbstractResponsiveTest;
import context.base.Description;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import selenium.pages.UrlFactory;
import selenium.pages.mobile.MainPageResponsivePage;

import java.util.stream.IntStream;

@DisplayName("NYX Costemic Product Page - Responsive")
public class NYXCosmeticsProductTest extends AbstractResponsiveTest
{
    private static Logger logger = Logger.getLogger(NYXCosmeticsProductTest.class);

    private MainPageResponsivePage mainPage;

    @Before
    public void init() throws Exception
    {
        super.init();
        mainPage = new MainPageResponsivePage(driver);
    }

    @Test
    @Description("Anasayfa daki ürünlerin fiyatının 0 dan büyük olduğunun kontrolü.")
    public void testProductSalePrice() throws Exception
    {
        navigateToURL(UrlFactory.MAIN_URL);
        IntStream.range(0, mainPage.getProductSalePrices().size())
                .forEach(count -> {
                    Assert.assertNotEquals(0, getText(mainPage.getProductSalePrices().get(count)));
                });

    }
}
