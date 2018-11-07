package Enums;


public enum UrlFactory {

    MAIN_URL("https://google.com");


    //-----

    public final String pageUrl;

    UrlFactory(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    UrlFactory(UrlFactory baseUrl, String pageUrl) {
        this.pageUrl = baseUrl.pageUrl + pageUrl;
    }

}
