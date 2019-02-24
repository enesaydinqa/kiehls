package selenium.tests.galen;


import context.base.AbstractWebTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.Dimension;
import selenium.pages.UrlFactory;
import selenium.tests.galen.path.SpecFilePath;

import java.io.IOException;
import java.util.Arrays;

public class NyxCostemicMainPageLayoutTest extends AbstractWebTest
{

    @Before
    public void init() throws Exception
    {
        super.init();
        driver.manage().window().setSize(new Dimension(1200, 800));
    }

    @Test
    @DisplayName("The Main Page Layout Design")
    public void testMainPageLayoutDesign() throws IOException
    {
        navigateToURL(UrlFactory.MAIN_URL);
        wait(5);

        for (int s = 100; s <= 1700; s += 100)
        {
            wait(1);
            pageScroll(0, s);
        }

        checkLayoutDesign(SpecFilePath.MAIN_PAGE.getFilePath(), Arrays.asList(PlatformName.DESKTOP.platformName), this.getClass().getSimpleName());
    }
}
