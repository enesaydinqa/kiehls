package selenium.pages.mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import selenium.pages.PageObject;

import java.util.List;


public class MainResponsivePage extends PageObject
{
    public MainResponsivePage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(css = "[data-gtm-list='EN YENİLER']")
    private List<WebElement> newestProducts;

    @FindBy(xpath = "//div[@data-pro-product-info]")
    private List<WebElement> productSalePrices;

    @FindBy(css = "div.swiper-button-next")
    private WebElement sliderNext;

    @FindBy(css = "[data-alias='mobile_carousel'] .swiper-slide-active")
    private WebElement activeSliderImage;

    @FindBy(xpath = "//a[contains(@class, '-mw-gtm-item-visited')]")
    private List<WebElement> productList;

    @FindBy(xpath = "//a[contains(@class, '-mw-gtm-item-visited')]/parent::div//div[contains(text(), ' TL')]")
    private List<WebElement> productPriceList;

    @FindBy(className = "ins-close-button-icon")
    private WebElement popupCloseButton;

    @FindBy(id = "obj-menu")
    public WebElement menuButton;

    @FindBy(xpath = "//a[contains(@href, '/giris')]")
    public WebElement openLoginPopup;

    @FindBy(id = "obj-email")
    public WebElement emailInput;

    @FindBy(id = "obj-password")
    public WebElement passwordInput;

    @FindAll({
            //@FindBy(xpath = "//a[contains(@href, '/giris?is')]/parent::div"),
            @FindBy(xpath = "//a[contains(@href, '/giris?is')]"),
    })
    public WebElement loginButton;

    @FindBy(css = ".ins-side-menu-opened")
    public WebElement discountPopup;

    public List<WebElement> getNewestProducts()
    {
        return newestProducts;
    }

    public List<WebElement> getProductSalePrices()
    {
        return productSalePrices;
    }

    public WebElement getSliderNext()
    {
        return sliderNext;
    }

    public WebElement getActiveSliderImage()
    {
        return activeSliderImage;
    }

    public List<WebElement> getProductList()
    {
        return productList;
    }

    public List<WebElement> getProductPriceList()
    {
        return productPriceList;
    }

    public WebElement getPopupCloseButton()
    {
        return popupCloseButton;
    }
}
