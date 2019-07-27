package selenium.tests.web;

import context.annotations.Description;
import context.base.AbstractKiehlsTest;
import context.flag.ParallelExecutable;
import context.flag.ShoppingExecutable;
import context.objects.User;
import context.utils.Constants;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebElement;
import selenium.pages.UrlFactory;
import selenium.pages.web.ShoppingPage;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class ShoppingTest extends AbstractKiehlsTest
{
    private static final Logger logger = Logger.getLogger(ShoppingTest.class);

    User user;
    ShoppingPage shoppingPage;

    @Before
    public void init() throws Exception
    {
        super.init(true);
        user = new User();
        shoppingPage = new ShoppingPage(driver);
    }


    @Test
    @Description("Alişveriş süreci ve satın alma ekranı kontrolu")
    @Category({ShoppingExecutable.class, ParallelExecutable.class})
    public void testShoppingColdCreamCategoryWithGuestLogin()
    {

        driver.get(UrlFactory.COLD_CREAM.pageUrl);
        wait(15);
        try
        {
            driver.switchTo().frame(shoppingPage.popupFrameProductDetailPage);
            jsHelper.click(shoppingPage.popupCloseProductDetailPage);
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
        String price = getText(shoppingPage.productPrice).replace(" TL", "");
        String title = getText(shoppingPage.productDetailTitle);
        jsHelper.click(shoppingPage.addToBasket);
        wait(10);
        Assert.assertEquals("Price", price, getText(shoppingPage.popupPrice).replace(" TL", ""));
        click(shoppingPage.paymentBtn);
        wait(5);
        Assert.assertTrue("Title", getText(shoppingPage.productTitleFromOrderSummary).contains(title));
        int exPrice = Integer.valueOf(price.replace(",00", "")) + 10;
        int acPrice = Integer.valueOf(getText(shoppingPage.totalPice).replace(",00", ""));
        Assert.assertEquals("Total Price", exPrice, acPrice);

        click(shoppingPage.gotoPaymentPage);
        click(shoppingPage.guestLoginBtn);

        wait(10);
        Assert.assertTrue(driver.getCurrentUrl().equals(UrlFactory.CHECKOUT_ADD_SAMPLES_PAGE.pageUrl));

        int sampleProduct = Integer.valueOf(shoppingPage.sampleProductText.getText().replaceAll("[^0-9]+",""));

        IntStream.range(0, sampleProduct).forEach(value -> {

            click(shoppingPage.sampleProduct.get(value));
            wait(5);

        });

        wait(5);

        jsHelper.click(shoppingPage.next);
        wait(5);
        Assert.assertTrue(driver.getCurrentUrl().equals(UrlFactory.CHECKOUT_PAGE.pageUrl));
        //TODO teslimat adresi kısmından devam edilcek.

    }

    @Test
    @Description("Alişveriş süreci ve satın alma ekranı kontrolu Login ile")
    @Category({ShoppingExecutable.class, ParallelExecutable.class})
    public void testShoppingShampooCategoryWithLogin()
    {
        user.setEmail(Constants.TestUser.USER_EMAIL);
        user.setPassword(Constants.TestUser.PASS);

        login(user);

        driver.get(UrlFactory.SHAMPOO.pageUrl);
        wait(15);

        closePopup();

        waitElementToBeClickable(shoppingPage.productList.get(0));
        click(shoppingPage.productList.get(0));
        wait(10);

        String price = getText(shoppingPage.productPrice).replace(" TL", "");
        String title = getText(shoppingPage.productDetailTitle);

        jsHelper.click(shoppingPage.addToBasket);
        wait(10);
        Assert.assertEquals("Price", price, getText(shoppingPage.popupPrice).replace(" TL", ""));

        click(shoppingPage.paymentBtn);
        wait(5);
        Assert.assertTrue("Title", getText(shoppingPage.productTitleFromOrderSummary).contains(title));

        int exPrice = Integer.valueOf(price.replace(",00", "")) + 10;
        int acPrice = Integer.valueOf(getText(shoppingPage.totalPice).replace(",00", ""));
        Assert.assertEquals("Total Price", exPrice, acPrice);

        click(shoppingPage.gotoPaymentPage);

        wait(10);
        Assert.assertTrue(driver.getCurrentUrl().equals(UrlFactory.CHECKOUT_ADD_SAMPLES_PAGE.pageUrl));

        int sampleProduct = Integer.valueOf(shoppingPage.sampleProductText.getText().replaceAll("[^0-9]+",""));

        IntStream.range(0, sampleProduct).forEach(value -> {

            click(shoppingPage.sampleProduct.get(value));
            wait(5);

        });


        wait(5);
        jsHelper.click(shoppingPage.next);
        wait(5);
        Assert.assertTrue(driver.getCurrentUrl().equals(UrlFactory.CHECKOUT_PAGE.pageUrl));

        jsHelper.click(shoppingPage.next);
        wait(5);
        Assert.assertTrue(driver.getCurrentUrl().equals(UrlFactory.PAYMENT_PAGE.pageUrl));
        Assert.assertTrue(shoppingPage.creditCard.isDisplayed());
        Assert.assertTrue(shoppingPage.securePayBtn.isDisplayed());

    }

    @After
    public void after()
    {

        driver.get(UrlFactory.CART.pageUrl);

        wait(10);
        List<WebElement> proRemove = shoppingPage.proRemove;

        //IntStream.range(0, proRemove.size()-1)
        IntStream.range(0, 1)
                .mapToObj(proRemove::get)
                .filter(Objects::nonNull)
                .forEach(el -> {
                    wait(3);
                    click(el);
                });

    }
}
