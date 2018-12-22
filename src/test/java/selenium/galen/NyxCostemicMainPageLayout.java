package selenium.galen;


import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import base.BaseWebTest;
import enums.URLFactory;

public class NyxCostemicMainPageLayout extends BaseWebTest
{
    @Test
    @DisplayName("The Main Page Layout Design")
    public void testMainPageLayoutDesign() throws IOException
    {
        navigateToURL(URLFactory.MAIN_URL);
        wait(5);

        for (int s = 100; s <= 1700; s += 100) {
            wait(1);
            pageScroll(0, s);
        }

        checkLayoutDesign(SpecFilePath.MAIN_PAGE.getFilePath(), Arrays.asList(PlatformName.DESKTOP.platformName),
                this.getClass().getSimpleName());
    }
}
