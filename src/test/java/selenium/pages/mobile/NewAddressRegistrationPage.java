package selenium.pages.mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.pages.PageObject;

public class NewAddressRegistrationPage extends PageObject
{
    public NewAddressRegistrationPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(id = "obj-email")
    public WebElement emailInput;

    @FindBy(id = "obj-first_name")
    public WebElement firstNameInput;

    @FindBy(id = "obj-last_name")
    public WebElement lastNameInput;

    @FindBy(id = "obj-phone")
    public WebElement phoneInput;

    @FindBy(xpath = "//div[@data-component-id='dropdown_city']//input[1]")
    public WebElement citySelectBox;

    @FindBy(css = "[data-title='Adana']")
    public WebElement adanaOption;

    @FindBy(xpath = "//div[@data-component-id='dropdown_township']//input[1]")
    public WebElement townshipSelectBox;

    @FindBy(css = "[data-title='Aladağ']")
    public WebElement aladagOption;

    @FindBy(id = "obj-address")
    public WebElement addressTextArea;

    @FindBy(xpath = "//a[contains(@href, '/new-address?')]/parent::div")
    public WebElement saveButton;

    @FindBy(xpath = "//a[text() = 'TAMAM']")
    public WebElement okButton;

}
