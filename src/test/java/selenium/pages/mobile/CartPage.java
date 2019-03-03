package selenium.pages.mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
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

    @FindAll({
            @FindBy(xpath = "//span[contains(text(),'hediye paketi')]/parent::div/parent::div/parent::div/div[4]"),
            @FindBy(xpath = "//span[contains(text(),'hediye paketi')]/parent::div/parent::div/parent::div//input"),
    })
    public WebElement giftPackageCheckbox;

    @FindBy(xpath = "//div[contains(text(),'Hediye Paketi')]/parent::div/parent::div//div[contains(text(), 'TL')]")
    public WebElement giftPackageFee;

    @FindBy(xpath = "//div[contains(text(),'Toplam Tutar')]/parent::div/parent::div//div[contains(text(), 'TL')]")
    public WebElement totalFee;

    @FindAll({
            @FindBy(xpath = "//a[contains(@href, '/giris?guest_button')]"),
    })
    public WebElement completeShoppingButton;

    @FindBy(xpath = "//a[contains(@href, '/giris?is-guest=1&is-login=')]")
    public WebElement asAGuestButton;

    @FindBy(xpath = "//a[contains(@href, '/checkout-address?i')]")
    public WebElement checkoutAddressButton;
}
