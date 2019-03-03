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

    @FindBy(xpath = "//div[contains(text(),'Kargo')]/parent::div/parent::div//div[contains(text(), 'TL')]")
    public WebElement shippingFee;

    @FindBy(xpath = "//span[contains(text(),'hediye paketi')]/parent::div/parent::div/parent::div/div[4]")
    public WebElement giftPackageCheckbox;

    @FindBy(xpath = "//div[contains(text(),'Hediye Paketi')]/parent::div/parent::div//div[contains(text(), 'TL')]")
    public WebElement giftPackageFee;
}
