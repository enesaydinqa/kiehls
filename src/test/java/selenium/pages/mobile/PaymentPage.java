package selenium.pages.mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.pages.PageObject;

import java.util.List;

public class PaymentPage extends PageObject
{
    public PaymentPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(css = "[data-alias='agreements']")
    public List<WebElement> agreements;

    @FindBy(id = "obj-name")
    public WebElement nameInput;

    @FindBy(id = "obj-cc_number")
    public WebElement ccNumber;

    @FindBy(css = "[data-component-id='cc_date']")
    public WebElement ccDate;

    @FindBy(id = "obj-cvc")
    public WebElement cvcInput;

    @FindBy(xpath = "//a[text() = 'Tamam']")
    public WebElement okButton;
}
