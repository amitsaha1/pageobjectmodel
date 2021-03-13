package com.w2a.pages;

import com.utilities.WebUtilities;
import com.w2a.basepackage.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DressPage extends Page {
   public String referencenumber;
    WebUtilities utils=new WebUtilities();
//    public void gotoSignUp() {
//    }

//    public loginPage gotoLogin() {
//        return new loginPage();
//    }

    public void gotowomendresses() {
        driver.findElement(By.xpath("//a[@title='Women']")).click();
    }

    public void gotowomentops() {
        driver.findElement(By.xpath("//div[@id='subcategories']//li[1]//div[@class='subcategory-image']")).click();
    }

    public void clickaddtocart() throws InterruptedException {
        utils.waitforpageload(driver);
        WebElement ele=driver.findElement(By.xpath("//ul[@class='product_list grid row']//li[1]//div[@itemtype='http://schema.org/Product']"));
        utils.scrolltoelement(driver,ele);
        utils.waitforpageload(driver);
        WebElement shotsleeve=driver.findElement(By.xpath("//a[contains(text(),'Faded Short Sleeve T-shirts')]"));
        utils.movetoelement(driver,shotsleeve);
        driver.findElement(By.xpath("//ul[@class='product_list grid row']//li[1]//span[contains(text(),'Add to cart')]")).click();
        utils.waitforpageload(driver);
        WebElement proceedtocheckoutbutton=driver.findElement(By.xpath("//div[@class='button-container']//a[@title='Proceed to checkout']"));
        Assert.assertTrue(utils.verifyifelementispresent(driver,proceedtocheckoutbutton));
    }
    public void clickproceedtocart() throws InterruptedException {
        WebElement proceedtocheckoutbutton=driver.findElement(By.xpath("//div[@class='button-container']//a[@title='Proceed to checkout']"));
        proceedtocheckoutbutton.click();
    }

    public void verifycartsummary()
    {
        WebElement cartsummarypage=driver.findElement(By.xpath("//h1[@id='cart_title']"));
        Assert.assertTrue(utils.verifyifelementispresent(driver,cartsummarypage));
        WebElement totalprice=driver.findElement(By.xpath("//span[@id='total_price']"));
        Assert.assertTrue(utils.verifyifelementispresent(driver,totalprice));
        Assert.assertEquals("$18.51",utils.gettext(driver,totalprice));
    }

    public void placeorder()
    {
        WebElement proceedtocheckout=driver.findElement(By.xpath("//p[@class='cart_navigation clearfix']//span[contains(text(),'Proceed to checkout')]"));
        proceedtocheckout.click();
        utils.waitforpageload(driver);
        WebElement confirmaddressandcheckout=driver.findElement(By.xpath("//button[@name='processAddress']/span"));
        utils.movetoelement(driver,confirmaddressandcheckout);
        confirmaddressandcheckout.click();
        utils.waitforpageload(driver);
        WebElement agreetoterms=driver.findElement(By.xpath("//input[@type='checkbox']"));
        agreetoterms.click();
        WebElement proceedtocheckoutshipping=driver.findElement(By.xpath("//button[@name='processCarrier']/span"));

        proceedtocheckoutshipping.click();
    }

    public void makepayment()
    {
        utils.waitforpageload(driver);
        WebElement makepaymentbycheck=driver.findElement(By.xpath("//a[@title='Pay by check.']"));
        makepaymentbycheck.click();
        utils.waitforpageload(driver);
        WebElement confirmorder=driver.findElement(By.xpath("//p[@class='cart_navigation clearfix']//button[@type='submit']"));
        confirmorder.click();
        utils.waitforpageload(driver);

        WebElement getsuccesmessage=driver.findElement(By.xpath("//p[@class='alert alert-success']"));
                utils.verifyifelementispresent(driver,getsuccesmessage);
                WebElement orderreferencenumber=driver.findElement(By.xpath("//div[@class='box order-confirmation']"));
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
        WebElement myorders=driver.findElement(By.xpath("//a[@title='My orders']"));
        myorders.click();
        utils.waitforpageload(driver);
        WebElement Orderid= driver.findElement(By.xpath("//table[@id='order-list']/tbody//tr[1]/td[1]/a"));
        Assert.assertEquals(Orderid.getText().trim(),referencenumber);
    }
}
