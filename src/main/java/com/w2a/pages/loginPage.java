package com.w2a.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.w2a.basepackage.Page;

public class loginPage extends Page {
	public zohoapp doLogin(String username,String password)
	{
		typetext("email_xpath",username);
		typetext("password_xpath",password);
		click("signin_xpath");
		return new zohoapp();
	}


 
	
}
