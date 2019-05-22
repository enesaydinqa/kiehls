package selenium.tests.web;

import context.annotations.Description;
import context.base.AbstractKiehlsTest;
import context.flag.ParallelExecutable;
import context.flag.ShoppingExecutable;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import selenium.pages.UrlFactory;
import selenium.pages.web.ShoppingPage;

import java.util.NoSuchElementException;

public class ShoppingTest extends AbstractKiehlsTest
{
    private static final Logger logger = Logger.getLogger(ShoppingTest.class);

    ShoppingPage shoppingPage;

    @Before
    public void init() throws Exception
    {
        super.init(true);
        shoppingPage = new ShoppingPage(driver);
    }


    @Test
    @Description("Alişveriş süreci ve satın almak ekranı kontrolu")
    @Category({ShoppingExecutable.class, ParallelExecutable.class})
    public void testShoppingColdCreamCategoryWithGuestLogin(){

        driver.get(UrlFactory.COLD_CREAM.pageUrl);
        wait(10);
        try
        {
            driver.switchTo().frame(shoppingPage.popupFrame);
            jsHelper.click(shoppingPage.popupClose);
            driver.switchTo().defaultContent();

        }
        catch (Exception e)
        {

            logger.debug("userFavoritePopup");
            driver.switchTo().defaultContent();

        }

        waitElementToBeClickable(shoppingPage.productList.get(0));
        click(shoppingPage.productList.get(0));
        wait(10);
        String price = getText(shoppingPage.productPrice).replace(" TL","");
        String title =  getText(shoppingPage.productDetailTitle);
        jsHelper.click(shoppingPage.addToBasket);
        wait(10);
        Assert.assertEquals("Price",price,getText(shoppingPage.popupPrice).replace(" TL",""));
        click(shoppingPage.paymentBtn);
        wait(5);
        Assert.assertTrue("Title",getText(shoppingPage.productTitleFromOrderSummary).contains(title));
        int exPrice = Integer.valueOf(price.replace(",00",""))+10;
        int acPrice = Integer.valueOf(getText(shoppingPage.totalPice).replace(",00", ""));
        Assert.assertEquals("Total Price",exPrice,acPrice);

        click(shoppingPage.gotoPaymentPage);
        click(shoppingPage.guestLoginBtn);

        wait(10);
        Assert.assertTrue(driver.getCurrentUrl().equals(UrlFactory.CHECKOUT_ADD_SAMPLES_PAGE.pageUrl));

        click(shoppingPage.sampleProduct.get(0));
        wait(5);
        jsHelper.click(shoppingPage.next);
        wait(5);
        Assert.assertTrue(driver.getCurrentUrl().equals(UrlFactory.CHECKOUT_PAGE.pageUrl));
        //TODO teslimat adresi kısmından devam edilcek.

    }
}
