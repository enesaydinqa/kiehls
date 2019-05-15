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

    @FindBy(css = ".row .pro-product-list-items > div")
    public List<WebElement> productList;

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

    @FindBy(css = ".pro-product-detail-title")
    public WebElement productTitleFromOrderSummary;

    @FindBy(xpath = "//div[@class='pro-order-summary ']/div[@class='clearfix']//h4[text()='SİPARİŞ ÖZETİ']/" +
            "ancestor::div[@class='clearfix']/following-sibling::div[2]/div[@class='pro-order-summary-total pro-order-summary-table']/" +
            "div[2]/descendant::div[@data-pro-product-info='sale_price']")
    public WebElement totalPice;

    @FindBy(css = ".btn.btn-lg.btn-green.btn-block.bypass-blank")
    public WebElement gotoPaymentPage;

    @FindBy(id = "ca-guest-login")
    public WebElement guestLoginBtn;

    @FindBy(xpath = "//*[text()='DEVAM ET']")
    public WebElement next;

    @FindBy(xpath = "//div[@class='row pro-checkout-product-samples-row']/div/descendant::div[@class='pro-product-add-button-wrapper']")
    public List<WebElement> sampleProduct;
}
