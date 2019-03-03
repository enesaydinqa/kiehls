package context.objects;

import context.helper.JSHelper;
import org.openqa.selenium.WebDriver;

public class User
{
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;

    public User(WebDriver driver)
    {
        JSHelper jsHelper = new JSHelper(driver);

        this.firstName = jsHelper.randomStringGenerate(6);
        this.lastName = jsHelper.randomStringGenerate(6);
        this.email = jsHelper.randomGenerate(6) + "@gmail.com";
        this.phone = "34" + jsHelper.randomNumberGenerate(7);
        this.address = jsHelper.randomGenerate(30);
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
