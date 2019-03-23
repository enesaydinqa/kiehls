package context.base;

import org.apache.log4j.Logger;
import selenium.pages.mobile.MainResponsivePage;
import selenium.pages.web.MainPageWebPage;

import java.util.NoSuchElementException;

public abstract class AbstractKiehlsTest extends AbstractWebTest
{
    private Logger logger = Logger.getLogger(AbstractKiehlsTest.class);

    public void campaingClose()
    {
        MainPageWebPage MainPageWebPage = new MainPageWebPage(driver);

        if (isDisplayed(MainPageWebPage.campaingCloseButton)) click(MainPageWebPage.campaingCloseButton);
    }

}
