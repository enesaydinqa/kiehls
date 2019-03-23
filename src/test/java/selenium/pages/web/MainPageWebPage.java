package selenium.pages.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.pages.PageObject;

import java.util.List;


public class MainPageWebPage extends PageObject
{

    public MainPageWebPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath = "//i[contains(@id, 'icon-close-button')]")
    public WebElement campaingCloseButton;
}
