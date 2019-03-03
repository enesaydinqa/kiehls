package selenium.tests.adaptive;

import context.annotations.Description;
import context.base.AbstractNYXCosmeticsResponsiveTest;
import context.helper.JSHelper;
import context.objects.CreditCard;
import context.objects.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import selenium.pages.mobile.CartPage;
import selenium.pages.mobile.NewAddressRegistrationPage;
import selenium.pages.mobile.PaymentPage;

import java.util.stream.IntStream;

public class PaymentTest extends AbstractNYXCosmeticsResponsiveTest
{
    private JSHelper jsHelper;
    private CartPage cartPage;
    private NewAddressRegistrationPage newAddressRegistrationPage;
    private PaymentPage paymentPage;
    private User user;
    private CreditCard creditCard;

    @Before
    public void init() throws Exception
    {
        super.init();
        jsHelper = new JSHelper(driver);
        cartPage = new CartPage(driver);
        newAddressRegistrationPage = new NewAddressRegistrationPage(driver);
        paymentPage = new PaymentPage(driver);
    }

    @Test
    @Ignore
    @Description("Misafir olarak alışveriş tamamlama senaryosu.")
    public void testUserNotLoggedAgreementVisible()
    {
        user = new User(driver);
        creditCard = new CreditCard(driver);

        randomProductSelectAndAddBasket();
        wait(5);
        click(cartPage.completeShoppingButton);
        wait(7);
        click(cartPage.asAGuestButton);
        newAddressRegistrationFormFilling(user);
        wait(5);
        click(cartPage.checkoutAddressButton);
        wait(7);
        enterCreditCard(creditCard);
        Long pageHeight = jsHelper.getPageHeight();
        secureScrollPage(0, pageHeight.intValue());

        Assert.assertEquals("Agreement Smooth or Ever Not Showing !", 8, paymentPage.agreements.size());

        IntStream.range(0, 8)
                .forEach(i -> isDisplayed(paymentPage.agreements.get(i)));
    }


    private void newAddressRegistrationFormFilling(User user)
    {
        wait(10);
        sendKeys(newAddressRegistrationPage.emailInput, user.getEmail());
        sendKeys(newAddressRegistrationPage.firstNameInput, user.getLastName());
        sendKeys(newAddressRegistrationPage.lastNameInput, user.getLastName());
        sendKeys(newAddressRegistrationPage.phoneInput, user.getPhone());
        click(newAddressRegistrationPage.citySelectBox);
        wait(3);
        clickViaJs(newAddressRegistrationPage.okButton);
        wait(5);
        click(newAddressRegistrationPage.townshipSelectBox);
        wait(3);
        clickViaJs(newAddressRegistrationPage.okButton);
        wait(5);
        click(newAddressRegistrationPage.addressTextArea);
        sendKeys(newAddressRegistrationPage.addressTextArea, user.getAddress());
        click(newAddressRegistrationPage.saveButton);
        wait(5);
    }

    private void enterCreditCard(CreditCard creditCard)
    {
        wait(10);
        sendKeys(paymentPage.nameInput, creditCard.getFirstName() + " " + creditCard.getLastName());
        sendKeys(paymentPage.ccNumber, String.valueOf(creditCard.getCardNumber()));
        click(paymentPage.nameInput);
        wait(10);
        click(paymentPage.ccDate);
        wait(3);
        click(paymentPage.okButton);
        sendKeys(paymentPage.cvcInput, String.valueOf(creditCard.getCvv()));
    }
}
