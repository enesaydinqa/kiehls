package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class MainPageResponsive {

    @FindBy(css = "[data-gtm-list='EN YENİLER']")
    private List<WebElement> newestProducts;

    @FindBy(xpath = "//div[@data-pro-product-info]")
    private List<WebElement> productSalePrices;


    public List<WebElement> getNewestProducts() {
        return newestProducts;
    }

    public List<WebElement> getProductSalePrices() {
        return productSalePrices;
    }
}
