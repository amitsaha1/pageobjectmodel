package com.w2.testcases;

import com.w2a.basepackage.Page;
import com.w2a.pages.DressPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;


public class Addtocart extends Page {
	DressPage dp=new DressPage(driver);
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
	@Test(priority = 4)
	public void signout()
	{
		dp.signout();
	}

}
