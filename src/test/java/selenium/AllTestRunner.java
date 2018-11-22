package selenium;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import selenium.web.NYXCostemicMainPage;

@RunWith(Suite.class)
@Suite.SuiteClasses({NYXCostemicMainPage.class, selenium.responsive.NYXCostemicMainPage.class})
public class AllTestRunner {
}
