package com.w2a.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.w2a.basepackage.page;

public class loginPage extends page {
	public zohoapp doLogin(String username,String password)
	{
		typetext("username_xpath",username);
		typetext("password_xpath",password);
		click("sign_xpath");
		return new zohoapp();
	}


 
	
}
