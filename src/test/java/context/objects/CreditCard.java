package context.objects;

import context.helper.JSHelper;
import org.openqa.selenium.WebDriver;

public class CreditCard
{
    private String firstName;
    private String lastName;
    private Number cardNumber;
    private Number cvv;

    public CreditCard(WebDriver driver)
    {
        JSHelper jsHelper = new JSHelper(driver);

        this.firstName = jsHelper.randomStringGenerate(6);
        this.lastName = jsHelper.randomStringGenerate(6);
        this.cardNumber = jsHelper.randomNumberGenerate(16);
        this.cvv = jsHelper.randomNumberGenerate(4);
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public Number getCardNumber()
    {
        return cardNumber;
    }

    public void setCardNumber(Number cardNumber)
    {
        this.cardNumber = cardNumber;
    }

    public Number getCvv()
    {
        return cvv;
    }

    public void setCvv(Number cvv)
    {
        this.cvv = cvv;
    }
}
