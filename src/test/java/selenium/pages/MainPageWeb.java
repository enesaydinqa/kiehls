package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class MainPageWeb {

    @FindBy(xpath = "(//div[@class='slick-track'])[1]/div")
    private List<WebElement> newestProducts;

    @FindBy(xpath = "(//div[@class='fancybox-skin'])[1]")
    private WebElement productPricePopup;

    @FindBy(xpath = "//div[@data-pro-product-info]")
    private List<WebElement> productSalePrices;

    @FindBy(xpath = "//div[contains(@class, 'pro-product-image')]")
    private List<WebElement> mainPageAllProduct;


    public List<WebElement> getNewestProducts() {
        return newestProducts;
    }

    public WebElement getProductPricePopup() {
        return productPricePopup;
    }

    public List<WebElement> getProductSalePrices() {
        return productSalePrices;
    }

    public List<WebElement> getMainPageAllProduct() {
        return mainPageAllProduct;
    }
}
