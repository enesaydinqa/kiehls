package selenium.pages.mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import selenium.pages.PageObject;

public class ProductDetailPage extends PageObject
{
    public ProductDetailPage(WebDriver driver)
    {
        super(driver);
    }


    @FindAll({
            @FindBy(xpath = "(//div[contains(@id, 'obj-randomObjId')])[6]"),
            @FindBy(xpath = "//div[text() ='SEPETE EKLE']")
    })
    public WebElement addToBasket;

}
