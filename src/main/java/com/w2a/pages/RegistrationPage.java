package com.w2a.pages;

import com.utilities.Utilities;
import com.utilities.WebUtilities;
import com.w2a.basepackage.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class RegistrationPage extends Page {
    @FindBy(xpath="//button[@name='SubmitCreate']")
    WebElement createaccount;

    WebUtilities util=new WebUtilities();
    Utilities ut=new Utilities();
    public String firstnametext=ut.random(10);

    public void setmailId(String mailid)
    {
        WebElement emailidbox=driver.findElement(By.xpath("//input[@id='email_create']"));
        util.settext(driver,emailidbox,mailid);
    }

    public void createaccount()
    {
//        WebElement createaccount=driver.findElement(By.xpath("//button[@name='SubmitCreate']"));
        util.elementclick(driver,createaccount);
        util.waitforpageload(driver);
        WebElement selectgender=driver.findElement(By.xpath("//input[@value='1' and @id='id_gender1']"));
        selectgender.click();
        WebElement firstname=driver.findElement(By.xpath("//input[@name='customer_firstname']"));
        util.settext(driver,firstname,firstnametext);
        WebElement secondname=driver.findElement(By.xpath("//input[@name='customer_lastname']"));
        util.settext(driver,secondname,ut.random(7));
    }

    public void fillalldetails() {
        WebElement dobdays=driver.findElement(By.xpath("//select[@id='days']"));
        util.selectbyindex(driver,dobdays,20);
        WebElement dobmonths=driver.findElement(By.xpath("//select[@id='months']"));
        util.selectbyindex(driver,dobmonths,3);
        WebElement dobyears=driver.findElement(By.xpath("//select[@id='years']"));
        util.selectbyindex(driver,dobyears,20);
        WebElement address=driver.findElement(By.xpath("//input[@name='address1']"));
        util.settext(driver,address,ut.random(15));
        WebElement city=driver.findElement(By.xpath("//input[@id='city']"));
        util.settext(driver,city,ut.random(15));
        WebElement state=driver.findElement(By.xpath("//select[@id='id_state']"));
        util.selectbyindex(driver,state,20);
        WebElement phone=driver.findElement(By.xpath("//input[@id='phone_mobile']"));
        util.settext(driver,phone,"9434041187");
        WebElement postalcode=driver.findElement(By.xpath("//p[@class='required postcode form-group']/input"));
        util.settext(driver,postalcode,"56010");
        WebElement password=driver.findElement(By.xpath("//input[@id='passwd']"));
        util.settext(driver,password,ut.random(15));
        WebElement aliasaddress=driver.findElement(By.xpath("//input[@id='alias']"));
        util.settext(driver,aliasaddress,ut.random(15));
    }

    public void registeruser()
    {
        WebElement register=driver.findElement(By.xpath("//button[@id='submitAccount']"));
        util.elementclick(driver,register);
        WebElement getuserinfo=driver.findElement(By.xpath("//div[@class='header_user_info']/a[@class='account']/span"));
        String userdetails=util.gettext(driver,getuserinfo);
        Assert.assertTrue(userdetails.contains(firstnametext));

    }

    public void createaccountwithoutmail()
    {

    }
}
