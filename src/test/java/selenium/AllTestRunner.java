package selenium;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import selenium.web.NYXCostemicMainPage;
import selenium.web.NYXCostemicProductPage;

@RunWith(Suite.class)
@Suite.SuiteClasses({selenium.galen.NyxCostemicMainPageLayout.class})
public class AllTestRunner {
}
