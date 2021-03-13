package com.w2.testcases;

import com.w2a.basepackage.Page;
import com.w2a.pages.DressPage;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;


public class Addtocart extends Page {
	DressPage dp=new DressPage();
    @Test(priority = 3)
	public void placingorder() throws InterruptedException {
		dp.gotowomendresses();
		dp.gotowomentops();
		dp.clickaddtocart();
		dp.clickproceedtocart();
		dp.verifycartsummary();
		dp.placeorder();
		dp.makepayment();
		dp.validateorderreferencenumber();
	}
	@AfterSuite
	public void tearddown()
	{
		System.out.println("Closing browser");
	 	Page.quit();
	}

}
