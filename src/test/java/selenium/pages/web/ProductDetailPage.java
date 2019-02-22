package selenium.pages.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.pages.PageObject;

public class ProductDetailPage extends PageObject
{
    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#pro-product-detail-tab-0 p")
    private WebElement productDetail;

    public WebElement getProductDetail()
    {
        return productDetail;
    }
}
