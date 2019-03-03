package context.base;

import context.helper.JSHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import selenium.pages.UrlFactory;
import selenium.pages.mobile.MainPageResponsivePage;
import selenium.pages.mobile.ProductDetailPage;

public abstract class AbstractNYXCosmeticsResponsiveTest extends AbstractResponsiveTest
{
    private Logger logger = Logger.getLogger(AbstractNYXCosmeticsResponsiveTest.class);

    private JSHelper jsHelper;
    private MainPageResponsivePage mainPage;
    private ProductDetailPage productDetailPage;

    protected Double getPrice(WebElement element)
    {
        jsHelper = new JSHelper(driver);

        waitElementVisible(element);
        String[] productPrice = jsHelper.getText(element).split(" TL");
        return Double.parseDouble(productPrice[0].replace(",", "."));
    }

    protected void randomProductSelectAndAddBasket()
    {
        jsHelper = new JSHelper(driver);
        mainPage = new MainPageResponsivePage(driver);
        productDetailPage = new ProductDetailPage(driver);

        while (true)
        {
            navigateToURL(UrlFactory.THE_NEWEST_0_TO_50_PRICE);

            Long pageHeight = jsHelper.getPageHeight();
            secureScrollPage(0, pageHeight.intValue());
            pageScroll(0, 0);
            listElementRandomClick(mainPage.getProductList());
            wait(7);
            pageScroll(0, 300);

            if (isDisplayed(productDetailPage.addToBasket))
            {
                click(productDetailPage.addToBasket);
                break;
            }
        }

        wait(7);
    }
}
