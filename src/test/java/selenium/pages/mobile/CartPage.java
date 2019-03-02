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

    @FindBy(xpath = "(//div[contains(text(), ' TL')])[2]")
    public WebElement productPrice;

    @FindBy(xpath = "//div[contains(text(),'Ara Toplam')]/parent::div/parent::div//div[contains(text(), 'TL')]")
    public WebElement subtotalPrice;
}
