package selenium.pages;


public enum UrlFactory
{

    MAIN_URL("https://www.nyxcosmetics.com.tr"),
    THE_NEWEST_0_TO_50_PRICE(MAIN_URL, "/en-yeniler?=undefined&q=&_attr_prices[0-50]=1"),
    THE_NEWEST_50_TO_75_PRICE(MAIN_URL, "/en-yeniler?q=&_attr_prices[50-75]=1"),
    THE_NEWEST_75_TO_100_PRICE(MAIN_URL, "/en-yeniler?=undefined&q=&_attr_prices[75-100]=1"),
    THE_NEWEST_100_TO_150_PRICE(MAIN_URL, "/en-yeniler?=undefined&q=&_attr_prices[100-150]=1"),
    THE_NEWEST_200_GREATER_THEN_PRICE(MAIN_URL, "/en-yeniler?=undefined&q=&_attr_prices[200-*]=1");

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
