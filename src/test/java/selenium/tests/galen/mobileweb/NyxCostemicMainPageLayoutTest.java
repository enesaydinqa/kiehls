package selenium.tests.galen.mobileweb;


import context.base.AbstractResponsiveTest;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import selenium.pages.UrlFactory;
import selenium.tests.galen.path.SpecFilePath;

import java.io.IOException;
import java.util.Arrays;

public class NyxCostemicMainPageLayoutTest extends AbstractResponsiveTest
{
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
