package com.w2a.pages.crm.acoounts;

import org.openqa.selenium.By;

import com.w2a.basepackage.page;

public class createaccount extends page{

	public void createaccounts(String accountname)
	{
		typetext(".//*[@id='Crm_Accounts_ACCOUNTNAME']",accountname);
		
	}
}
