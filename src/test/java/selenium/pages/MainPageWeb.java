package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class MainPageWeb {

    @FindBy(xpath = "(//div[@class='slick-track'])[1]/div//div[@class='pro-product-image-wrapper']//img[@class='visible -lazy-load-init']")
    private List<WebElement> newestProducts;

    @FindBy(xpath = "(//a[@itemprop='item'])[1]")
    private WebElement breadCrumb;

    @FindBy(xpath = "//div[@data-pro-product-info]")
    private List<WebElement> productSalePrices;

    @FindBy(xpath = "//iframe[contains(@name, 'fancybox-frame')]")
    private WebElement mainPageFancyBoxIFrame;

    @FindBy(xpath = "//i[contains(@class, 'element-close-button')]")
    private WebElement mainPageBeInformed;

    @FindBy(xpath = "//div[contains(@class, 'pro-product-image')]")
    private List<WebElement> mainPageAllProduct;

    public List<WebElement> getNewestProducts() {
        return newestProducts;
    }

    public WebElement getBreadCrumb() {
        return breadCrumb;
    }

    public List<WebElement> getProductSalePrices() {
        return productSalePrices;
    }

    public List<WebElement> getMainPageAllProduct() {
        return mainPageAllProduct;
    }

    public WebElement getMainPageFancyBoxIFrame()
    {
        return mainPageFancyBoxIFrame;
    }

    public WebElement getMainPageBeInformed()
    {
        return mainPageBeInformed;
    }
}
