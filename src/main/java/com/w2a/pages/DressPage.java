package com.w2a.pages;

import com.utilities.WebUtilities;
import com.w2a.basepackage.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DressPage extends Page {

    @FindBy(xpath="//a[@title='Women']")
    WebElement womendresses;
    @FindBy(xpath="//div[@id='subcategories']//li[1]//div[@class='subcategory-image']")
    WebElement womendtops;
    @FindBy(xpath="//ul[@class='product_list grid row']//li[1]//div[@itemtype='http://schema.org/Product']")
    WebElement productlistgrid;
    @FindBy(xpath="//a[contains(text(),'Faded Short Sleeve T-shirts')]")
    WebElement shotsleeve;
    @FindBy(xpath="//ul[@class='product_list grid row']//li[1]//span[contains(text(),'Add to cart')]")
    WebElement addtocart;
    @FindBy(xpath="//div[@class='button-container']//a[@title='Proceed to checkout']")
    WebElement proceedtocheckoutbutton;
    @FindBy(xpath="//h1[@id='cart_title']")
    WebElement cartsummarypage;
    @FindBy(xpath="//span[@id='total_price']")
    WebElement totalprice;
    @FindBy(xpath="//p[@class='cart_navigation clearfix']//span[contains(text(),'Proceed to checkout')]")
    WebElement proceedtocheckout;
    @FindBy(xpath="//button[@name='processAddress']/span")
    WebElement confirmaddressandcheckout;
    @FindBy(xpath="//input[@type='checkbox']")
    WebElement agreetoterms;
    @FindBy(xpath="//button[@name='processCarrier']/span")
    WebElement proceedtocheckoutshipping;

    @FindBy(xpath="//a[@title='Pay by check.']")
    WebElement makepaymentbycheck;
    @FindBy(xpath="//p[@class='cart_navigation clearfix']//button[@type='submit']")
    WebElement confirmorder;
    @FindBy(xpath="//p[@class='alert alert-success']")
    WebElement getsuccesmessage;
    @FindBy(xpath="//div[@class='box order-confirmation']")
    WebElement orderreferencenumber;
    @FindBy(xpath="//a[@title='My orders']")
    WebElement myorders;
    @FindBy(xpath="//table[@id='order-list']/tbody//tr[1]/td[1]/a")
    WebElement Orderid;
    @FindBy(xpath="//a[@class='logout']")
    WebElement logout;

   public String referencenumber;
    WebUtilities utils=new WebUtilities();

    public DressPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void gotowomendresses() {
        utils.elementclick(driver,womendresses);
    }

    public void gotowomentops() {
        utils.elementclick(driver,womendtops);
   }

    public void clickaddtocart() throws InterruptedException {
        utils.waitforpageload(driver);
       utils.scrolltoelement(driver,productlistgrid);
        utils.waitforpageload(driver);
        utils.movetoelement(driver,shotsleeve);
        utils.elementclick(driver,addtocart);
        utils.waitforpageload(driver);
        Assert.assertTrue(utils.verifyifelementispresent(driver,proceedtocheckoutbutton));
    }
    public void clickproceedtocart() throws InterruptedException {
        proceedtocheckoutbutton.click();
    }

    public void verifycartsummary()
    {
       Assert.assertTrue(utils.verifyifelementispresent(driver,cartsummarypage));
       Assert.assertTrue(utils.verifyifelementispresent(driver,totalprice));
        Assert.assertEquals("$18.51",utils.gettext(driver,totalprice));
    }

    public void placeorder()
    {
       proceedtocheckout.click();//p[@class='cart_navigation clearfix']//span[contains(text(),'Proceed to checkout')]
        utils.waitforpageload(driver);
       utils.movetoelement(driver,confirmaddressandcheckout);
        confirmaddressandcheckout.click();
        utils.waitforpageload(driver);
       agreetoterms.click();
        proceedtocheckoutshipping.click();
    }

    public void makepayment()
    {
        utils.waitforpageload(driver);
       makepaymentbycheck.click();
        utils.waitforpageload(driver);
        confirmorder.click();
        utils.waitforpageload(driver);
                utils.verifyifelementispresent(driver,getsuccesmessage);
               referencenumber=getreferencenumber(utils.gettext(driver,orderreferencenumber));
    }

    public String getreferencenumber(String s)
    {
        List<String> words = new ArrayList<>();
        Pattern pattern = Pattern.compile("[A-Z][A-Z]+");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            words.add(matcher.group());
        }

        System.out.println(words.get(4));
        return words.get(4);
    }

    public void validateorderreferencenumber() {
       myorders.click();
        utils.waitforpageload(driver);
       Assert.assertEquals(Orderid.getText().trim(),referencenumber);
    }

    public void signout() {
        utils.elementclick(driver,logout);
    }
}
