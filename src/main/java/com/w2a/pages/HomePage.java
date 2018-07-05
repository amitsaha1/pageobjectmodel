package com.w2a.pages;
import org.openqa.selenium.*;

import com.w2a.basepackage.page;
public class HomePage extends page {

	public void gotoSignUp()
	{}
	
	public loginPage gotoLogin()
	{
		click("loginlink_xpath");
	    return new loginPage();
	}
	
	public void gotoSupport()
	{
		driver.findElement(By.xpath("//*[@class='zh-support']")).click();
		//*[@id='block-system-main']/div[1]/div[1]/a
	}
	
	public void gotoExploreMore()
	{
		driver.findElement(By.xpath("//*[@id='block-system-main']/div[1]/div[1]/a")).click();
	}
	
	public void validateFooterLinks()
	{
		driver.findElement(By.xpath("//*[@id='block-zoho-navigation-links-product-pages-footer-links']/div/div/div/div[2]/ul"));
	}
}
