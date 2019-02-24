package selenium.tests.galen.mobile;


import context.base.AbstractResponsiveTest;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import selenium.pages.UrlFactory;
import selenium.tests.galen.path.SpecFilePath;

import java.io.IOException;
import java.util.Arrays;

public class NyxCostemicsMainPageLayoutTest extends AbstractResponsiveTest
{
    @Test
    @DisplayName("The Main Page Layout Design")
    public void testMainPageLayoutDesign() throws IOException
    {
        navigateToURL(UrlFactory.MAIN_URL);
        wait(5);
        pageLongDownScroll();
        checkLayoutDesign(SpecFilePath.MAIN_PAGE_RESPONSIVE.getFilePath(), Arrays.asList(PlatformName.MOBILE.platformName), this.getClass().getSimpleName());
    }
}
