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

    @FindBy(css = "[data-component-id='dropdown_city']")
    public WebElement citySelectBox;

    @FindBy(css = "[data-title='Adana']")
    public WebElement adanaOption;

    @FindBy(css = "[data-component-id='dropdown_township']")
    public WebElement townshipSelectBox;

    @FindBy(css = "[data-title='Aladağ']")
    public WebElement aladagOption;

    @FindBy(id = "obj-address")
    public WebElement addressTextArea;

    @FindBy(xpath = "//div[contains(text(),'KAYDET')]/parent::div/parent::div")
    public WebElement saveButton;

    @FindBy(xpath = "//a[text() = 'TAMAM']/parent::div")
    public WebElement okButton;

}
