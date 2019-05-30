package selenium.pages.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.pages.PageObject;

import java.util.List;

public class LoginPage extends PageObject
{

    public LoginPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(css = ".form-control.form-lowercase.validate-element.email.input-email")
    public WebElement userName;

    @FindBy(css = ".form-control.validate-element.input-password")
    public WebElement password;

    @FindBy(css = ".btn.primary-button.pull-right.width-140.button-login")
    public WebElement userLoginSubmitBtn;

    @FindBy(css = "span.error_label.error_label_validation")
    public List<WebElement> labelValidation;


}
