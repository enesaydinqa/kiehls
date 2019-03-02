package selenium.tests.adaptive;

import context.base.AbstractNYXCostemicResponsiveTest;
import context.base.Description;
import context.helper.JSHelper;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.pages.UrlFactory;
import selenium.pages.mobile.MainPageResponsivePage;

import java.util.stream.IntStream;

public class NYXCosmeticsProductTest extends AbstractNYXCostemicResponsiveTest
{
    private static Logger logger = Logger.getLogger(NYXCosmeticsProductTest.class);

    private MainPageResponsivePage mainPage;
    private JSHelper jsHelper;

    @Before
    public void init() throws Exception
    {
        super.init();
        mainPage = new MainPageResponsivePage(driver);
        jsHelper = new JSHelper(driver);
    }

    @Test
    @Description("Anasayfa daki ürünlerin fiyatının 0 dan büyük olduğunun kontrolü.")
    public void testAdaptiveProductPriceGreaterThanZero()
    {
        navigateToURL(UrlFactory.MAIN_URL);
        IntStream.range(0, mainPage.getProductSalePrices().size())
                .forEach(count -> {
                    Assert.assertNotEquals(0, getText(mainPage.getProductSalePrices().get(count)));
                });
    }

    @Test
    @Description("Anasayfa ürün fiyatlarının 0 dan büyük olduğunun kontrolü.")
    public void testHomePageProductPrice()
    {
        navigateToURL(UrlFactory.MAIN_URL);
        wait(10);

        IntStream.range(1, 8)
                .forEach(i -> {
                    try
                    {
                        WebElement theNewestFrom = driver.findElement(By.xpath("(//div[@class='swiper-wrapper'])[3]/div[" + i + "]"));
                        WebElement theNewestTo = driver.findElement(By.xpath("(//div[@class='swiper-wrapper'])[3]/div[" + (i + 1) + "]"));
                        dragAndDrop(theNewestFrom, theNewestTo);

                        WebElement dynamicGetProductPrice = driver.findElement(By.xpath("(//div[@class='swiper-wrapper'])[3]/div[" + i + "]//div[contains(text(), ' TL')]"));

                        String result = jsHelper.getText(dynamicGetProductPrice);
                        logger.info("Product Price --> " + result);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                });

        IntStream.range(1, 5)
                .forEach(i -> {
                    try
                    {
                        WebElement bestSellersFrom = driver.findElement(By.xpath("(//div[@class='swiper-wrapper'])[4]/div[" + i + "]"));
                        WebElement bestSellersTo = driver.findElement(By.xpath("(//div[@class='swiper-wrapper'])[4]/div[" + (i + 1) + "]"));
                        dragAndDrop(bestSellersFrom, bestSellersTo);

                        WebElement dynamicGetProductPrice = driver.findElement(By.xpath("(//div[@class='swiper-wrapper'])[3]/div[" + i + "]//div[contains(text(), ' TL')]"));

                        String result = jsHelper.getText(dynamicGetProductPrice);
                        logger.info("Product Price --> " + result);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                });
    }
}
