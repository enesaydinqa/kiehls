package selenium.pages.mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.pages.PageObject;

public class MainResponsivePage extends PageObject
{
    public MainResponsivePage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath = "(//div[contains(@id, 'close-button-')])[1]")
    public WebElement campaingCloseButton;


}
