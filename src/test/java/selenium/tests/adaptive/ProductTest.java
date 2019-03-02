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
import selenium.pages.mobile.CartPage;
import selenium.pages.mobile.MainPageResponsivePage;
import selenium.pages.mobile.ProductDetailPage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ProductTest extends AbstractNYXCostemicResponsiveTest
{
    private static Logger logger = Logger.getLogger(ProductTest.class);

    private MainPageResponsivePage mainPage;
    private ProductDetailPage productDetailPage;
    private CartPage cartPage;
    private JSHelper jsHelper;

    @Before
    public void init() throws Exception
    {
        super.init();
        mainPage = new MainPageResponsivePage(driver);
        productDetailPage = new ProductDetailPage(driver);
        cartPage = new CartPage(driver);
        jsHelper = new JSHelper(driver);
    }

    @Test
    @Description("Anasayfa ürün fiyatlarının 0 dan büyük olduğunun kontrolü.")
    public void testHomePageProductPrice()
    {
        navigateToURL(UrlFactory.MAIN_URL);

        IntStream.range(1, 8)
                .forEach(i -> {
                    try
                    {
                        WebElement theNewestFrom = driver.findElement(By.xpath("(//div[@class='swiper-wrapper'])[3]/div[" + i + "]"));
                        WebElement theNewestTo = driver.findElement(By.xpath("(//div[@class='swiper-wrapper'])[3]/div[" + (i + 1) + "]"));

                        if (!Boolean.parseBoolean(System.getProperty("remote.test")))
                            dragAndDrop(theNewestFrom, theNewestTo);

                        WebElement dynamicGetProductPrice = driver.findElement(By.xpath("(//div[@class='swiper-wrapper'])[3]/div[" + i + "]//div[contains(text(), ' TL')]"));

                        String result = jsHelper.getText(dynamicGetProductPrice);
                        Double productPrice = Double.parseDouble(result.substring(0, Math.min(result.length(), 5)).replace(",", "."));

                        logger.info("Product Price --> " + productPrice);

                        Assert.assertTrue("Product Price Must Be Greater Than Zero", productPrice > 0);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                });

        IntStream.range(1, 7)
                .forEach(i -> {
                    try
                    {
                        WebElement bestSellersFrom = driver.findElement(By.xpath("(//div[@class='swiper-wrapper'])[4]/div[" + i + "]"));
                        WebElement bestSellersTo = driver.findElement(By.xpath("(//div[@class='swiper-wrapper'])[4]/div[" + (i + 1) + "]"));

                        if (!Boolean.parseBoolean(System.getProperty("remote.test")))
                            dragAndDrop(bestSellersFrom, bestSellersTo);

                        WebElement dynamicGetProductPrice = driver.findElement(By.xpath("(//div[@class='swiper-wrapper'])[3]/div[" + i + "]//div[contains(text(), ' TL')]"));

                        String result = jsHelper.getText(dynamicGetProductPrice);
                        Double productPrice = Double.parseDouble(result.substring(0, Math.min(result.length(), 5)).replace(",", "."));

                        logger.info("Product Price --> " + productPrice);

                        Assert.assertTrue("Product Price Must Be Greater Than Zero", productPrice > 0);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                });
    }

    @Test
    @Description("Ürün fiyatı ile Sepete Ekle dediğimizde çıkan fiyat aynı mı ?")
    public void testProductAndBasketPriceCompare()
    {
        navigateToURL(UrlFactory.MAIN_URL);
        pageLongDownScroll();
        scrollToElement(mainPage.getProductList().get(3));

        String[] result = jsHelper.getText(mainPage.getProductPriceList().get(3)).split("TL");

        Double productPrice = Double.parseDouble(result[0].replace(",", "."));
        logger.info("Product Price --> " + productPrice);

        wait(7);
        clickViaJs(mainPage.getProductList().get(3));

        wait(7);
        pageScroll(0, 300);

        click(productDetailPage.addToBasket);

        wait(7);
        waitElementVisible(cartPage.productPrice);
        wait(7);
        String cartPageProductPrice = jsHelper.getText(cartPage.productPrice);
        Double cartProductPrice = Double.parseDouble(cartPageProductPrice.substring(0, Math.min(cartPageProductPrice.length(), 5)).replace(",", "."));

        logger.info("Cart Page Product Price --> " + cartProductPrice);
        Assert.assertEquals("The price on the payment screen with the product price is not the same", cartProductPrice, productPrice);
    }

    @Test
    @Description("Sepetteki ürünlerin fiyatları toplamı ile Toplam Tutar alanındaki tutar eşit mi ?")
    public void testBasketAndSubTotalCountCompare()
    {
        List<Double> productPrice = new ArrayList<>();

        IntStream.range(1, 3).forEach(i ->
        {
            navigateToURL(UrlFactory.MAIN_URL);

            if (isDisplayed(mainPage.getPopupCloseButton())) click(mainPage.getPopupCloseButton());

            pageLongDownScroll();
            scrollToElement(mainPage.getProductList().get(i));

            String[] result = jsHelper.getText(mainPage.getProductPriceList().get(i)).split(" TL");

            Double price = Double.parseDouble(result[0].replace(",", "."));
            productPrice.add(price);

            logger.info("Product Price --> " + price);

            wait(7);
            clickViaJs(mainPage.getProductList().get(i));

            wait(7);
            pageScroll(0, 300);

            click(productDetailPage.addToBasket);
            wait(7);
        });

        Double totalProductPrice = productPrice.get(0) + productPrice.get(1);

        waitElementVisible(cartPage.subtotalPrice);
        wait(7);
        String[] getSubTotalPrice = jsHelper.getText(cartPage.subtotalPrice).split(" TL");

        Double subTotalPrice = Double.parseDouble(getSubTotalPrice[0].replace(",", "."));

        logger.info("Sub Total Price --> " + subTotalPrice);
        Assert.assertEquals("The price on the payment screen with the product price is not the same", totalProductPrice, subTotalPrice);
    }
}
