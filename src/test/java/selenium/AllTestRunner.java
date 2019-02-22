package selenium;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import selenium.tests.galen.NyxCostemicMainPageLayoutTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({NyxCostemicMainPageLayoutTest.class})
public class AllTestRunner
{

    /*
    @Suite.SuiteClasses({selenium.tests.galen.NyxCostemicMainPageLayout.class, selenium.tests.responsive.NYXCostemicMainPage.class,
        selenium.tests.responsive.NYXCostemicProductPage.class, selenium.tests.web.NYXCostemicMainPage.class,
        selenium.tests.web.NYXCostemicProductPage.class})
     */
}
