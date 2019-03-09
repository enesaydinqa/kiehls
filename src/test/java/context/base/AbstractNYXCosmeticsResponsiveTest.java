package context.base;

import context.helper.JSHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import selenium.pages.UrlFactory;
import selenium.pages.mobile.CartPage;
import selenium.pages.mobile.MainResponsivePage;
import selenium.pages.mobile.ProductDetailPage;

public abstract class AbstractNYXCosmeticsResponsiveTest extends AbstractResponsiveTest
{
    private Logger logger = Logger.getLogger(AbstractNYXCosmeticsResponsiveTest.class);

    private JSHelper jsHelper;
    private MainResponsivePage mainPage;
    private ProductDetailPage productDetailPage;

    protected Double getPrice(WebElement element)
    {
        jsHelper = new JSHelper(driver);

        waitElementVisible(element);
        String[] productPrice = jsHelper.getText(element).split(" TL");
        return Double.parseDouble(productPrice[0].replace(",", "."));
    }

    protected void randomProductSelectAndAddBasket()
    {
        jsHelper = new JSHelper(driver);
        mainPage = new MainResponsivePage(driver);
        productDetailPage = new ProductDetailPage(driver);

        while (true)
        {
            navigateToURL(UrlFactory.THE_NEWEST_0_TO_50_PRICE);
            wait(5);

            if (isDisplayed(mainPage.getPopupCloseButton()))
            {
                click(mainPage.getPopupCloseButton());
                wait(3);
            }

            Long pageHeight = jsHelper.getPageHeight();
            secureScrollPage(0, pageHeight.intValue());
            pageScroll(0, 0);
            listElementRandomClick(mainPage.getProductList());
            wait(7);
            jsHelper.scrollToElement(productDetailPage.addToBasket);

            if (isDisplayed(productDetailPage.addToBasket))
            {
                click(productDetailPage.addToBasket);
                break;
            }
        }

        wait(7);
    }


    protected void login()
    {
        String email = "enesaydin611998@hotmail.com";
        String password = "testuser123";

        JSHelper jsHelper = new JSHelper(driver);

        MainResponsivePage mainResponsivePage = new MainResponsivePage(driver);

        navigateToURL(UrlFactory.MAIN_URL);
        wait(5);

        if (isDisplayed(mainResponsivePage.discountPopup))
        {
            click(mainResponsivePage.discountPopup);
            wait(3);
        }

        click(mainResponsivePage.menuButton);
        wait(3);
        clickViaJs(mainResponsivePage.openLoginPopup);
        wait(3);
        jsHelper.sendKeys(mainResponsivePage.emailInput, email);
        jsHelper.sendKeys(mainResponsivePage.passwordInput, password);
        jsHelper.click(mainResponsivePage.loginButton);
        wait(10);
        pageRefresh();

        logger.info("User Logged In --> " + email);
    }

    protected void allBasketProductRemove()
    {
        CartPage cartPage = new CartPage(driver);

        navigateToURL(UrlFactory.CART);
        wait(10);

        try
        {
            while (isDisplayed(cartPage.removeProductButton))
            {
                wait(3);
                click(cartPage.removeProductButton);
            }
        }
        catch (Exception ex)
        {
            // No Action
        }

    }
}
