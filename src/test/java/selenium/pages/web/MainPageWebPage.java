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

    @FindBy(xpath = "(//div[@class='slick-track'])[1]/div//div[@class='pro-product-image-wrapper']//img[@class" +
            "='visible -lazy-load-init']")
    private List<WebElement> newestProducts;

    @FindBy(xpath = "(//a[@itemprop='item'])[1]")
    private WebElement breadCrumb;

    @FindBy(xpath = "//div[@data-pro-product-info]")
    private List<WebElement> productSalePrices;

    @FindBy(xpath = "//iframe[contains(@name, 'fancybox-frame')]")
    private WebElement mainPageFancyBoxIFrame;

    @FindBy(xpath = "//i[contains(@class, 'element-close-button')]")
    private WebElement mainPageBeInformed;

    @FindBy(css = "[class='pro-product-image init-swipe'] img[data-src]")
    private List<WebElement> mainPageAllProduct;

    @FindBy(css = ".pro-spotlight-line-pagination-wrapper .swiper-pagination-bullet")
    private List<WebElement> sliderNext;

    @FindBy(css = ".swiper-slide-active")
    private WebElement activeSliderImage;


    public List<WebElement> getNewestProducts()
    {
        return newestProducts;
    }

    public WebElement getBreadCrumb()
    {
        return breadCrumb;
    }

    public List<WebElement> getProductSalePrices()
    {
        return productSalePrices;
    }

    public List<WebElement> getMainPageAllProduct()
    {
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

    public List<WebElement> getSliderNext()
    {
        return sliderNext;
    }

    public WebElement getActiveSliderImage()
    {
        return activeSliderImage;
    }
}
