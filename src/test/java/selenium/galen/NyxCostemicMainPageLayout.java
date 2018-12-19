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
    public void testMainPageLayoutDesign() throws IOException, InterruptedException
    {
        navigateToURL(URLFactory.MAIN_URL);
        Thread.sleep(10000);
        checkLayoutDesign(SpecFilePath.MAIN_PAGE.getFilePath(), Arrays.asList("desktop"),
                this.getClass().getSimpleName());
    }
}
