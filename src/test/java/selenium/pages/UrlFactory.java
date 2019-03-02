package selenium.pages;


public enum UrlFactory
{

    MAIN_URL("https://www.nyxcosmetics.com.tr"),
    THE_NEWEST_0_TO_50_PRICE(MAIN_URL, "/en-yeniler?=undefined&q=&_attr_prices[0-50]=1");

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
