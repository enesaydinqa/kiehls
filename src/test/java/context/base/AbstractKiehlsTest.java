package context.base;

import context.objects.User;
import org.apache.log4j.Logger;
import selenium.pages.UrlFactory;
import selenium.pages.mobile.MainResponsivePage;
import selenium.pages.web.LoginPage;
import selenium.pages.web.MainPageWebPage;
import selenium.pages.web.ShoppingPage;

import java.util.NoSuchElementException;

public abstract class AbstractKiehlsTest extends AbstractWebTest
{
    private Logger logger = Logger.getLogger(AbstractKiehlsTest.class);

    public void campaingClose()
    {
        MainPageWebPage MainPageWebPage = new MainPageWebPage(driver);

        if (isDisplayed(MainPageWebPage.campaingCloseButton)) click(MainPageWebPage.campaingCloseButton);
    }

    public void login(User user){

        LoginPage loginPage = new LoginPage(driver);

        driver.get(UrlFactory.LOGIN_URL.pageUrl);
        wait(15);
        closePopup();
        sendKeys(loginPage.userName,user.getEmail());
        sendKeys(loginPage.password,user.getPassword());
        click(loginPage.userLoginSubmitBtn);
        wait(5);

    }

    public void login(String username,String pass){

        LoginPage loginPage = new LoginPage(driver);

        driver.get(UrlFactory.LOGIN_URL.pageUrl);
        wait(15);
        closePopup();
        sendKeys(loginPage.userName,username);
        sendKeys(loginPage.password,pass);
        click(loginPage.userLoginSubmitBtn);
        wait(5);

    }

    public void closePopup(){

        ShoppingPage shoppingPage = new ShoppingPage(driver);
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
    }

}
