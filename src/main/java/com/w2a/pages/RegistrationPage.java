package com.w2a.pages;

import com.utilities.Utilities;
import com.utilities.WebUtilities;
import com.w2a.basepackage.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class RegistrationPage extends Page {
    @FindBy(xpath="//button[@name='SubmitCreate']")
    WebElement createaccount;
    @FindBy(xpath="//input[@value='1' and @id='id_gender1']")
    WebElement selectgender;
    @FindBy(xpath="//input[@name='customer_firstname']")
    WebElement firstname;
    @FindBy(xpath="//input[@name='customer_lastname']")
    WebElement secondname;
    @FindBy(xpath="//select[@id='days']")
    WebElement dobdays;
    @FindBy(xpath="//select[@id='months']")
    WebElement dobmonths;
    @FindBy(xpath="//select[@id='years']")
    WebElement dobyears;
    @FindBy(xpath="//input[@name='address1']")
    WebElement address;
    @FindBy(xpath="//input[@id='city']")
    WebElement city;
    @FindBy(xpath="//select[@id='id_state']")
    WebElement state;
    @FindBy(xpath="//input[@id='phone_mobile']")
    WebElement phone;
    @FindBy(xpath="//p[@class='required postcode form-group']/input")
    WebElement postalcode;
    @FindBy(xpath="//input[@id='passwd']")
    WebElement password;
    @FindBy(xpath="//input[@id='alias']")
    WebElement aliasaddress;
    @FindBy(xpath="//button[@id='submitAccount']")
    WebElement register;
    @FindBy(xpath="//div[@class='header_user_info']/a[@class='account']/span")
    WebElement getuserinfo;
    @FindBy(xpath="//input[@id='email_create']")
    WebElement emailidbox;
    @FindBy(xpath="//div[@class='alert alert-danger']/ol/li")
    WebElement invalidemailaddress;

    WebUtilities util=new WebUtilities();
    Utilities ut=new Utilities();
    public String firstnametext=ut.random(10);

    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void setmailId(String mailid)
    {
        util.settext(driver,emailidbox,mailid);
    }

    public void createaccount()
    {
        util.elementclick(driver,createaccount);
        util.waitforpageload(driver);
        util.elementclick(driver,selectgender);
        util.settext(driver,firstname,firstnametext);
        util.settext(driver,secondname,ut.random(7));
    }

    public void fillalldetails() {
       util.selectbyindex(driver,dobdays,20);
        util.selectbyindex(driver,dobmonths,3);
        util.selectbyindex(driver,dobyears,20);
        util.settext(driver,address,ut.random(15));
       util.settext(driver,city,ut.random(15));
       util.selectbyindex(driver,state,20);
       util.settext(driver,phone,"9434041187");
       util.settext(driver,postalcode,"56010");
       util.settext(driver,password,ut.random(15));
        util.settext(driver,aliasaddress,ut.random(15));
    }

    public void registeruser()
    {
        util.elementclick(driver,register);
        String userdetails=util.gettext(driver,getuserinfo);
        Assert.assertTrue(userdetails.contains(firstnametext));

    }

    public void createaccountwithoutmail()
    {
        util.elementclick(driver,createaccount);
        String str=util.gettext(driver,invalidemailaddress);
        Assert.assertTrue(str.contains("Invalid email address."));
    }

    public void createaccountwithexistingemail() {
        util.settext(driver,emailidbox,"sonyrocks.dgp@gmail.com");
        util.elementclick(driver,createaccount);
        String str=util.gettext(driver,invalidemailaddress);
        Assert.assertTrue(str.contains("An account using this email address has already been registered. Please enter a valid password or request a new one."));
    }

    public void createaccountwithoutfirstname() {
        util.pagerefresh(driver);
        util.settext(driver,emailidbox,ut.random(10)+"@gmail.com");
        util.elementclick(driver,createaccount);
        util.waitforpageload(driver);
        util.elementclick(driver,selectgender);
        util.settext(driver,secondname,ut.random(7));
        util.selectbyindex(driver,dobdays,20);
        util.selectbyindex(driver,dobmonths,3);
        util.selectbyindex(driver,dobyears,20);
        util.settext(driver,address,ut.random(15));
        util.settext(driver,city,ut.random(15));
        util.selectbyindex(driver,state,20);
        util.settext(driver,phone,"9434041187");
        util.settext(driver,postalcode,"56010");
        util.settext(driver,password,ut.random(15));
        util.settext(driver,aliasaddress,ut.random(15));
        util.elementclick(driver,register);
        String str=util.gettext(driver,invalidemailaddress);
        Assert.assertTrue(str.contains("firstname is required."));
    }

    public void createaccountwithoutlastname() {
        util.settext(driver,firstname,firstnametext);
        secondname.clear();
        util.elementclick(driver,register);
        String str=util.gettext(driver,invalidemailaddress);
        Assert.assertTrue(str.contains("lastname is required."));
    }
    public void createaccountwithoutpassword() {
        util.settext(driver,secondname,ut.random(7));
        password.clear();
        util.elementclick(driver,register);
        String str=util.gettext(driver,invalidemailaddress);
        Assert.assertTrue(str.contains("passwd is required."));
    }
    public void createaccountwithoutaddress() {
        util.settext(driver,password,ut.random(7));
        address.clear();
        util.elementclick(driver,register);
        String str=util.gettext(driver,invalidemailaddress);
        Assert.assertTrue(str.contains("address1 is required."));
    }
    public void createaccountwithoutpostalcode() {
        util.settext(driver,password,ut.random(7));
        util.settext(driver,address,ut.random(7));
        postalcode.clear();
        util.elementclick(driver,register);
        String str=util.gettext(driver,invalidemailaddress);
        Assert.assertTrue(str.contains("The Zip/Postal code you've entered is invalid. It must follow this format: 00000"));
    }
}
