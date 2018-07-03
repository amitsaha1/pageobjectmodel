package com.w2a.rough;


import org.testng.annotations.Test;

import com.w2.testcases.BaseClass;
import com.w2a.basepackage.page;
import com.w2a.pages.HomePage;
import com.w2a.pages.loginPage;
import com.w2a.pages.zohoapp;
import com.w2a.pages.crm.CRMHomepage;
import com.w2a.pages.crm.acoounts.accountspage;
import com.w2a.pages.crm.acoounts.createaccount;

public class Logintest extends BaseClass {

public static void main(String args[])
    {
	
	   HomePage home=new HomePage();
	   loginPage lp= home.gotoLogin();
	   zohoapp zp=lp.doLogin("sonyrocks.dgp@gmail.com", "Amitsaha@2018");
	   zp.gotoCRM();
	   accountspage account=page.menu.gotoAccounts();	  
	   createaccount cap=account.gotcreateaccount();
	   cap.createaccounts("Amit");
	
	   //driver.close();
	}
	
}
