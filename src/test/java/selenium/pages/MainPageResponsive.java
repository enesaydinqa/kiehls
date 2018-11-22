package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class MainPageResponsive {

    @FindBy(xpath = "(//div[@class='swiper-wrapper'])[2]/div")
    private List<WebElement> newestProducts;


    public List<WebElement> getNewestProducts() {
        return newestProducts;
    }
}
