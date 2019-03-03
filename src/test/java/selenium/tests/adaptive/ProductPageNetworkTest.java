package selenium.tests.adaptive;

import context.annotations.Description;
import context.base.AbstractNYXCosmeticsResponsiveTest;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarResponse;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import selenium.pages.UrlFactory;
import selenium.pages.mobile.MainPageResponsivePage;

import java.util.ArrayList;
import java.util.List;

public class ProductPageNetworkTest extends AbstractNYXCosmeticsResponsiveTest
{
    private static final Logger logger = Logger.getLogger(ProductPageNetworkTest.class);

    private MainPageResponsivePage mainPage;

    @Before
    public void init() throws Exception
    {
        super.init(true);
        mainPage = new MainPageResponsivePage(driver);
    }

    @Test
    @Description("Ürün sayfasında min 2 adet görsel var mı kontrolü.")
    public void testAdaptiveProductPageImages()
    {
        navigateToURL(UrlFactory.MAIN_URL);
        pageLongDownScroll();
        pageScroll(0, 0);
        scrollToElement(mainPage.getProductList().get(2));
        clickViaJs(mainPage.getProductList().get(2));
        pageLongDownScroll();

        List<HarEntry> entries = proxy.getHar().getLog().getEntries();

        List<HarResponse> imageCount = new ArrayList<>();

        entries.stream().filter(link -> link.getRequest().getUrl().contains(".png") | link.getRequest().getUrl().contains(".jpg"))
                .forEach(png -> {
                    logger.info("Image : " + png.getRequest().getUrl());
                    imageCount.add(png.getResponse());
                });

        Assert.assertTrue("Product Page expected to be at least 2 photos", imageCount.size() >= 2);
    }
}
