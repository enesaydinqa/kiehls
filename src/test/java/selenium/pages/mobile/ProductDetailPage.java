package selenium.pages.mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.pages.PageObject;

public class ProductDetailPage extends PageObject
{
    public ProductDetailPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath = "//a[contains(@href, '=mobile_product_detail')]")
    public WebElement addToBasket;

    @FindBy(xpath = "(//div[@data-alias='product_component'])[1]//div[contains(text(), ' TL')]")
    public WebElement productPrice;

}
