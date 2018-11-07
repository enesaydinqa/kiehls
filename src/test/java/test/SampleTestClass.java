package test;

import Base.BaseClass;
import org.junit.Test;

import org.openqa.selenium.support.PageFactory;

public class SampleTestClass extends BaseClass {

    SamplePage samplePage;


    @Test
    public void sdsad() throws InterruptedException {

        navigateToURL("https://www.w3schools.com/howto/howto_custom_select.asp");
        Thread.sleep(4000);
        samplePage = PageFactory.initElements(driver, SamplePage.class);
        selectOptionIndexViaJs(samplePage.testObject, 2);
    }
}
