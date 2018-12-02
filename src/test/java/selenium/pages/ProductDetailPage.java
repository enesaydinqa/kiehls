package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailPage
{
    @FindBy(css = "#pro-product-detail-tab-0 p")
    private WebElement productDetail;

    public WebElement getProductDetail()
    {
        return productDetail;
    }
}
