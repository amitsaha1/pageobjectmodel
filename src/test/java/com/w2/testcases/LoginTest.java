package com.w2.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.utilities.Utilities;
import com.w2a.pages.HomePage;
import com.w2a.pages.loginPage;
import com.w2a.pages.zohoapp;

public class LoginTest extends BaseClass{
    @Test(dataProviderClass=Utilities.class, dataProvider="dp")
	public void loginTest(Hashtable<String,String> data)
	{
    	//sopfdvsdfbgfhc vxfdffdscasszcz
    	 HomePage home=new HomePage();
  	   loginPage lp= home.gotoLogin();
  	   zohoapp zp=lp.doLogin(data.get("UserName"), data.get("Password"));
	}
}
