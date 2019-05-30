package selenium.pages.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.pages.PageObject;

import java.util.List;

public class ShoppingPage extends PageObject
{

    public ShoppingPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(css = ".pro-product-title.-GTM-product-click-action")
    public List<WebElement> productList;

    @FindBy(id = "close-button-1454703945249")
    public WebElement popupCloseMainPage;

    @FindBy(css = "#body > div.sp-fancybox-wrap.fancybox-desktop.fancybox-type-iframe.sp-advanced-css-1268.sp-fancybox-opened.adaptive-resolution.adaptive-position.adaptive-x-position-center.adaptive-y-position-middle > div.sp-fancybox-skin.adaptive-resolution > div.sp-fancybox-outer.adaptive-resolution > div > iframe")
    public WebElement popupFrameMainPage;

    @FindBy(css = ".display-inline-block")
    public WebElement productPrice;

    @FindBy(id = "add-basket-button")
    public WebElement addToBasket;

    @FindBy(css = "a.btn.btn-block.primary-button")
    public WebElement paymentBtn;

    @FindBy(css = "div > span:nth-child(3) > div.display-inline-block.pro-product-price-sale-price > div")
    public WebElement popupPrice;

    @FindBy(css = ".pro-product-detail-title")
    public WebElement productDetailTitle;

    @FindBy(css = ".display-inline-block.cart-table-product-name")
    public WebElement productTitleFromOrderSummary;

    @FindBy(xpath = "//div[@class='pro-order-summary ']/div[@class='clearfix']//h4[text()='SİPARİŞ ÖZETİ']/" +
            "ancestor::div[@class='clearfix']/following-sibling::div[2]/div[@class='pro-order-summary-total pro-order-summary-table']/" +
            "div[2]/descendant::div[@data-pro-product-info='sale_price']")
    public WebElement totalPice;

    @FindBy(css = ".btn.btn-lg.btn-green.btn-block.bypass-blank")
    public WebElement gotoPaymentPage;

    @FindBy(id = "ca-guest-login")
    public WebElement guestLoginBtn;

    @FindBy(xpath = "//*[text()='DEVAM ET' or text()='Satın Al']")
    public WebElement next;

    @FindBy(xpath = "//div[@class='row pro-checkout-product-samples-row']/div/descendant::div[@class='pro-product-add-button-wrapper']")
    public List<WebElement> sampleProduct;

    @FindBy(css = ".sp-fancybox-iframe.adaptive-resolution")
    public WebElement popupFrameProductDetailPage;

    @FindBy(id = "icon-close-button-1454703945249")
    public WebElement popupCloseProductDetailPage;

    @FindBy(css = "div.col-xs-8 > div > ul > li.input > input")
    public WebElement productQuantity;

    @FindBy(xpath = "//a[text()=' Sepetten Sil']")
    public List<WebElement> proRemove;

    @FindBy(css = ".pay-with-credit-card")
    public WebElement creditCard;

    @FindBy(id = "pay-button")
    public WebElement securePayBtn;
}
