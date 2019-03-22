package context.objects;

import org.apache.commons.lang3.RandomStringUtils;

public class CreditCard
{
    private String firstName;
    private String lastName;
    private Long cardNumber;
    private Integer cvv;

    public CreditCard()
    {
        this.firstName = RandomStringUtils.randomAlphabetic(6);
        this.lastName = RandomStringUtils.randomAlphabetic(6);
        this.cardNumber = Long.valueOf(RandomStringUtils.randomNumeric(16));
        this.cvv = Integer.parseInt(RandomStringUtils.randomNumeric(4));
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

    public Long getCardNumber()
    {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber)
    {
        this.cardNumber = cardNumber;
    }

    public Integer getCvv()
    {
        return cvv;
    }

    public void setCvv(Integer cvv)
    {
        this.cvv = cvv;
    }
}
