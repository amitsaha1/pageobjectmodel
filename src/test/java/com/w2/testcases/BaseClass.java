package com.w2.testcases;

import org.testng.annotations.AfterSuite;

import com.w2a.basepackage.page;

public class BaseClass  {

	@AfterSuite
	public void tearddown()
	{
		System.out.println("Closing browser");
	 page.quit();	
	}
}
