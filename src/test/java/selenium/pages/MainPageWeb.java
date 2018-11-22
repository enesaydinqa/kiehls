package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class MainPageWeb {

    @FindBy(xpath = "(//div[@class='slick-track'])[1]/div")
    private List<WebElement> newestProducts;


    public List<WebElement> getNewestProducts() {
        return newestProducts;
    }
}
