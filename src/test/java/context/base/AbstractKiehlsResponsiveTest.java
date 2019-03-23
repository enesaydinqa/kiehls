package context.base;

import org.apache.log4j.Logger;
import selenium.pages.mobile.MainResponsivePage;

public abstract class AbstractKiehlsResponsiveTest extends AbstractResponsiveTest
{
    private Logger logger = Logger.getLogger(AbstractKiehlsResponsiveTest.class);

    public void campaingClose()
    {
        MainResponsivePage mainResponsivePage = new MainResponsivePage(driver);

        if (isDisplayed(mainResponsivePage.campaingCloseButton)) click(mainResponsivePage.campaingCloseButton);
    }
}
