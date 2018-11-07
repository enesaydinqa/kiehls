package test;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SamplePage {

    @FindBy(xpath = "(//select)[1]")
    public WebElement testObject;
}
