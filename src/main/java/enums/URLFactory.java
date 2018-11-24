package enums;


public enum URLFactory {

    MAIN_URL("https://www.nyxcosmetics.com.tr/");

    //-----

    public final String pageUrl;

    URLFactory(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    URLFactory(URLFactory baseUrl, String pageUrl) {
        this.pageUrl = baseUrl.pageUrl + pageUrl;
    }

}
