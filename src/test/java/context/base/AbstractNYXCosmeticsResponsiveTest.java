package context.base;

import context.helper.JSHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

public abstract class AbstractNYXCosmeticsResponsiveTest extends AbstractResponsiveTest
{
    private Logger logger = Logger.getLogger(AbstractNYXCosmeticsResponsiveTest.class);

    private JSHelper jsHelper;

    protected Double getPrice(WebElement element)
    {
        jsHelper = new JSHelper(driver);

        waitElementVisible(element);
        String[] productPrice = jsHelper.getText(element).split(" TL");
        return Double.parseDouble(productPrice[0].replace(",", "."));
    }
}
