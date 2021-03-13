package com.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.logging.Logger;

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
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
     }

     public void movetoelement(WebDriver driver, WebElement element)
     {
         Actions actions = new Actions(driver);
         actions.moveToElement(element).build().perform();
     }

    public void actionclick(WebDriver driver, WebElement element)
    {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
    }

    public boolean verifyifelementispresent(WebDriver driver, WebElement element)
    {
        waitforpageload(driver);
        return element.isDisplayed();
    }

    public void elementclick(WebDriver driver, WebElement element)
    {
        waitforpageload(driver);
         element.click();
    }

     public void scrolltoelement(WebDriver driver, WebElement element) throws InterruptedException {
         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
         Thread.sleep(500);
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
}
