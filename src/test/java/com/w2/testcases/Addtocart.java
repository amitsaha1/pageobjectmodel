package com.w2.testcases;

import com.w2a.basepackage.page;
import com.w2a.pages.DressPage;
import org.testng.annotations.Test;


public class Addtocart extends page {
	DressPage dp=new DressPage();
    @Test
	public void placingorder() throws InterruptedException {
		dp.gotowomendresses();
		dp.gotowomentops();
		dp.clickaddtocart();
		dp.clickproceedtocart();
		dp.verifycartsummary();
		dp.placeorder();
		dp.makepayment();
	}

}
