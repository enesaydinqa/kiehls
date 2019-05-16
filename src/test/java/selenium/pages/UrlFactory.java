package selenium.pages;


public enum UrlFactory
{

    MAIN_URL("https://www.kiehls.com.tr"),
    COLD_CREAM("https://www.kiehls.com.tr/nemlendiriciler"),
    CHECKOUT_ADD_SAMPLES_PAGE("https://www.kiehls.com.tr/checkout-add-samples"),
    CHECKOUT_PAGE("https://www.kiehls.com.tr/checkout");

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
