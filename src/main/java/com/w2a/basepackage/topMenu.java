package com.w2a.basepackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.w2a.pages.crm.acoounts.accountspage;

public class topMenu {
	
	WebDriver driver;
	public topMenu(WebDriver driver)
	{
		this.driver=driver;
	}


	public void gotohome()
	{
		
	}
	public void gotoLeads()
	{
		
	}
	public void gotoContacts()
	{
		
	}
	public accountspage gotoAccounts()
	{
		driver.findElement(By.xpath("//*[@id='menuContent']//div//a[contains(text(),'Accounts')]")).click();;
		return new accountspage();
	}
	public void gotoDeals()
	{
		
	}
	public void gotoActivities()
	{
		
	}
	public void gotoreports()
	{
		
	}
	
	public void signout()
	{
		
	}
	
}
