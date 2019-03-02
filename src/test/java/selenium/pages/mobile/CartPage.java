package selenium.pages.mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.pages.PageObject;

public class CartPage extends PageObject
{
    public CartPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath = "(//div[contains(@id, 'obj-randomObjId')])[7]/div")
    public WebElement productPrice;
}
