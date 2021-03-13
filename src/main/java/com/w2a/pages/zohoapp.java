package com.w2a.pages;

import org.openqa.selenium.By;

import com.w2a.basepackage.Page;
import com.w2a.pages.crm.CRMHomepage;

public class zohoapp extends Page{
	
	public void gotosalesIQ()
	{
		driver.findElement(By.xpath("//*[@id='zl-myapps']/div[1]/div/div/a/div")).click();
	}
	
	public CRMHomepage gotoCRM()
	{
		click("gotocrm_xpath");
		return new CRMHomepage();
	}

}
