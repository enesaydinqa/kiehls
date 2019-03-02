package selenium.tests.galen.adaptive;


import context.base.AbstractResponsiveTest;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import selenium.pages.UrlFactory;
import selenium.tests.galen.path.SpecFilePath;

import java.io.IOException;
import java.util.Arrays;

public class HomePageLayoutTest extends AbstractResponsiveTest
{
    @Test
    @Ignore
    @DisplayName("The Main Page Layout Design")
    public void testAdaptiveHomePageLayoutDesign() throws IOException
    {
        navigateToURL(UrlFactory.MAIN_URL);
        wait(5);
        pageLongDownScroll();
        checkLayoutDesign(SpecFilePath.HOME_PAGE_RESPONSIVE.getFilePath(), Arrays.asList(PlatformName.MOBILE.platformName), this.getClass().getSimpleName());
    }
}
