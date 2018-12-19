package selenium;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({selenium.galen.NyxCostemicMainPageLayout.class, selenium.responsive.NYXCostemicMainPage.class,
        selenium.responsive.NYXCostemicProductPage.class, selenium.web.NYXCostemicMainPage.class,
        selenium.web.NYXCostemicProductPage.class})
public class AllTestRunner
{
}
