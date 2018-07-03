package com.w2a.pages.crm.acoounts;

import org.openqa.selenium.By;

import com.w2a.basepackage.page;

public class accountspage extends page{
	
	public createaccount gotcreateaccount()
	{
		click(".//*[@id='topRightIcons']/span[1]/link-to/button");
		return new createaccount();
	}

	public void gotoimportacoount()
	{
		
	}
}
