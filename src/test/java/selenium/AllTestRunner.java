package selenium;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import selenium.web.NYXCostemicMainPage;
import selenium.web.NYXCostemicProductPage;

@RunWith(Suite.class)
@Suite.SuiteClasses({NYXCostemicMainPage.class, selenium.responsive.NYXCostemicMainPage.class,
        NYXCostemicProductPage.class, selenium.responsive.NYXCostemicProductPage.class})
public class AllTestRunner {
}
