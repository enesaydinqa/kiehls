package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class MainPageResponsive {

    @FindBy(css = "[data-gtm-list='EN YENİLER']")
    private List<WebElement> newestProducts;


    public List<WebElement> getNewestProducts() {
        return newestProducts;
    }
}
