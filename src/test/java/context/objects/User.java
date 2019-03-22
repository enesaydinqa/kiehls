package context.objects;

import org.apache.commons.lang3.RandomStringUtils;

public class User
{
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;

    public User()
    {
        this.firstName = RandomStringUtils.randomAlphabetic(6);
        this.lastName = RandomStringUtils.randomAlphabetic(6);
        this.email = RandomStringUtils.randomAlphabetic(6) + "@gmail.com";
        this.phone = "34" + RandomStringUtils.randomNumeric(7);
        this.address = RandomStringUtils.randomAlphanumeric(30);
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

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }
}
