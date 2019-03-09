package selenium.pages.mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
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


    @FindAll({
            @FindBy(xpath = "//div[@data-component-id='dropdown_city']//input[1]"),
            @FindBy(css = "[data-component-id='dropdown_city'] > div")
    })
    public WebElement citySelectBox;

    @FindBy(css = "[data-title='Adana']")
    public WebElement adanaOption;

    @FindAll({
            @FindBy(xpath = "//div[@data-component-id='dropdown_township']//input[1]"),
            @FindBy(css = "[data-component-id='dropdown_township'] > div")
    })
    public WebElement townshipSelectBox;

    @FindBy(css = "[data-title='Aladağ']")
    public WebElement aladagOption;

    @FindBy(id = "obj-address")
    public WebElement addressTextArea;

    @FindBy(css = ".widget_footer_append > div > div > div:nth-child(3)")
    public WebElement saveButton;

    @FindBy(xpath = "//a[text() = 'TAMAM']")
    public WebElement okButton;

}