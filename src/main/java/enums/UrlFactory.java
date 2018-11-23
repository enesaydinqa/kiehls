package enums;


public enum UrlFactory {

    MAIN_URL("https://www.nyxcosmetics.com.tr/");

    //-----

    public final String pageUrl;

    UrlFactory(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    UrlFactory(UrlFactory baseUrl, String pageUrl) {
        this.pageUrl = baseUrl.pageUrl + pageUrl;
    }

}
