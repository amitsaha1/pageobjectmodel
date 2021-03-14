package com.utilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class WebUtilities {

    public void waitforpageload(WebDriver driver)
    {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            Reporter.log("Waiting  until page loads properly");
            wait.until(expectation);

        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
            Reporter.log("Timeout waiting for Page Load Request to complete.");
        }
     }

     public void movetoelement(WebDriver driver, WebElement element)
     {
         try {
             Actions actions = new Actions(driver);
             actions.moveToElement(element).build().perform();
             Reporter.log("Move to element "+element);
         }
         catch(Exception e)
         {
             Reporter.log("failed to move to element "+element);
         }
     }

    public void actionclick(WebDriver driver, WebElement element)
    {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().perform();
            Reporter.log("Action click on element "+element);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Reporter.log("Action click unsuccessful on element  "+element);
        }
    }

    public boolean verifyifelementispresent(WebDriver driver, WebElement element)
    {
        waitforpageload(driver);
        return element.isDisplayed();
    }

    public void elementclick(WebDriver driver, WebElement element)
    {
        try {
            waitforpageload(driver);
            element.click();
            Reporter.log(" Clicked on element "+element);
        } catch(Exception e)

        {
            e.printStackTrace();
            Reporter.log(" Click unsuccessful on element  "+element);
        }
    }

     public void scrolltoelement(WebDriver driver, WebElement element) throws InterruptedException {

        try{((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
         Thread.sleep(500);
            Reporter.log(" scroll to element is successful"+element);
     }catch(Exception e)

         {
             e.printStackTrace();
             Reporter.log(" scroll to element is unsuccessful"+element);
         }
     }

    public String gettext(WebDriver driver, WebElement totalprice) {
        return totalprice.getText();
    }

    public void settext(WebDriver driver, WebElement ele,String text)
    {
        try{
           ele.sendKeys(text);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void selectbyindex(WebDriver driver,WebElement ele,int index)
    {
        try {
            Select sel = new Select(ele);
            sel.selectByIndex(index);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void pagerefresh(WebDriver driver)
    {
        driver.navigate().refresh();
    }
}
