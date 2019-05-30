package selenium.pages;


public enum UrlFactory
{

    MAIN_URL("https://www.kiehls.com.tr"),
    LOGIN_URL("https://www.kiehls.com.tr/giris"),
    COLD_CREAM("https://www.kiehls.com.tr/nemlendiriciler"),
    SHAMPOO("https://www.kiehls.com.tr/sampuanlar"),
    CHECKOUT_ADD_SAMPLES_PAGE("https://www.kiehls.com.tr/checkout-add-samples"),
    CHECKOUT_PAGE("https://www.kiehls.com.tr/checkout"),
    CART("https://www.kiehls.com.tr/cart"),
    PAYMENT_PAGE("https://www.kiehls.com.tr/checkout-payment");

    //-----

    public final String pageUrl;

    UrlFactory(String pageUrl)
    {
        this.pageUrl = pageUrl;
    }

    UrlFactory(UrlFactory baseUrl, String pageUrl)
    {
        this.pageUrl = baseUrl.pageUrl + pageUrl;
    }

}
