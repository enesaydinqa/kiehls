package selenium.pages.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import selenium.pages.PageObject;

public class ProductDetailPage extends PageObject
{
    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    @FindAll({
            @FindBy(css = "#pro-product-detail-tab-0 p"),
            @FindBy(css = ".pro-product-detail-tab-content > div:nth-child(1)")
    })
    private WebElement productDetail;

    public WebElement getProductDetail()
    {
        return productDetail;
    }
}
