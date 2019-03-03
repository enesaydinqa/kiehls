package selenium.tests.web;

import context.base.AbstractNYXCosmeticsTest;
import context.annotations.Description;
import net.lightbody.bmp.core.har.HarEntry;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import selenium.pages.UrlFactory;
import selenium.pages.web.MainPageWebPage;

import java.util.List;
import java.util.stream.IntStream;

public class HomePageNetworkTest extends AbstractNYXCosmeticsTest
{
    private static Logger logger = Logger.getLogger(HomePageNetworkTest.class);

    private MainPageWebPage mainPage;

    @Before
    public void init() throws Exception
    {
        super.init(true);
        mainPage = new MainPageWebPage(driver);
    }

    @Test
    @Description("Anasayfa daki png lerin larının 200 (ok) olduğunun kontrolü")
    public void testHomePageLoadPNG()
    {
        navigateToURL(UrlFactory.MAIN_URL);
        pageLongDownScroll();
        List<HarEntry> entries = proxy.getHar().getLog().getEntries();

        entries.stream().filter(link -> link.getRequest().getUrl().contains(".png") | link.getRequest().getUrl().contains(".jpg"))
                .forEach(png -> {
                    logger.info("Check Response This Url -> " + png.getRequest().getUrl());
                    Assert.assertEquals("This image not load " + png.getRequest().getUrl(), 200, png.getResponse().getStatus());
                });
    }

    @Test
    @Description("Anasayfa yüklenirken yapılan request lerin response larının 400 den küçük olduğunun kontrolü")
    public void testHomePageNetwork()
    {
        navigateToURL(UrlFactory.MAIN_URL);
        pageLongDownScroll();
        List<HarEntry> entries = proxy.getHar().getLog().getEntries();

        entries.stream()
                .forEach(png -> {
                    logger.info("Check Response This Url -> " + png.getRequest().getUrl());
                    Assert.assertTrue("Broken : " + png.getRequest().getUrl(), 400 > png.getResponse().getStatus());
                });
    }

    @Test
    @Description("Anasayfaki ürünlerin fiyatının 0 dan büyük olduğunun kontrolü")
    public void testProductSalePrice()
    {
        navigateToURL(UrlFactory.MAIN_URL);
        IntStream.range(0, mainPage.getProductSalePrices().size())
                .forEach(count -> {
                    Assert.assertNotEquals(0, getText(mainPage.getProductSalePrices().get(count)));
                });
    }

    @Test
    @Description("Anasayfadaki slider ın çalıştığının kontrolü")
    public void testHomePageSlider()
    {
        navigateToURL(UrlFactory.MAIN_URL);
        IntStream.range(0, 3)
                .forEach(i ->
                {
                    waitElementVisible(mainPage.getActiveSliderImage());
                    String dataGtmPromotion = getAttribute(mainPage.getActiveSliderImage(), "data-swiper-slide-index");

                    waitElementToBeClickable(mainPage.getSliderNext().get(i));
                    click(mainPage.getSliderNext().get(i));

                    Assert.assertNotEquals(dataGtmPromotion, getAttribute(mainPage.getActiveSliderImage(), "data" +
                            "-swiper-slide-index"));
                });
    }
}
