package selenium.tests.web;

import context.annotations.Description;
import context.base.AbstractKiehlsTest;
import context.flag.ParallelExecutable;
import context.flag.ShoppingExecutable;
import context.utils.Constants;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebElement;
import selenium.pages.UrlFactory;
import selenium.pages.web.LoginPage;
import selenium.pages.web.MainPageWebPage;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LoginTest extends AbstractKiehlsTest
{
    private static final Logger logger = Logger.getLogger(LoginTest.class);

    LoginPage loginPage;
    MainPageWebPage mainPageWebPage;

    @Before
    public void before(){

        loginPage = new LoginPage(driver);
        mainPageWebPage = new MainPageWebPage(driver);
    }

    @Test
    @Description("Validasyon kontrolleri")
    @Category({ShoppingExecutable.class, ParallelExecutable.class})
    public void testLogin()
    {

        driver.get(UrlFactory.LOGIN_URL.pageUrl);
        wait(10);
        closePopup();
        click(loginPage.userLoginSubmitBtn);
        login(Constants.TestUser.USER_EMAIL,Constants.TestUser.PASS);
        wait(15);
        closePopup();

        Assert.assertTrue(mainPageWebPage.username.getText().equalsIgnoreCase("test"));

    }


    @Test
    @Description("Validasyon kontrolleri")
    @Category({ShoppingExecutable.class, ParallelExecutable.class})
    public void testLoginMissingUsernameAndPassword()
    {

        driver.get(UrlFactory.LOGIN_URL.pageUrl);
        wait(15);
        closePopup();
        wait(2);
        click(loginPage.userLoginSubmitBtn);
        wait(3);
        List<WebElement> validation = loginPage.labelValidation;

        validation.stream().findFirst().filter(Objects::nonNull).equals(Constants.LoginValidation.USER_NAME);

        validation.stream().collect(Collectors.toList()).get(1).equals(Constants.LoginValidation.PASS);
    }

}
