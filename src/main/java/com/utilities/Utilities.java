package com.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Hashtable;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;
import com.w2a.basepackage.Page;

public class Utilities extends Page {
	public static String screenshotPath;	
	public static String screenshotname;
	
	public static void screenshot() throws IOException
	{   
		Date d=new Date();
		//String x=d.toString().replace(" ", "_").replace(":","_");
		screenshotname=d.toString().replace(" ", "_").replace(":","_")+".jpg";
		File scr=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scr, new File(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\"+screenshotname));
	}
	
	@DataProvider(name="dp")
	public Object[][] getData(Method m) {

		String sheetName = m.getName();
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][1];
		Hashtable<String,String> table=null;
		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			table=new Hashtable<String,String>();			
			for (int colNum = 0; colNum < cols; colNum++) {
				table.put(excel.getCellData(sheetName, colNum, 1),excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum-2][0]=table;
			}

		}

		return data;

	}
	
	
public static boolean isTestRunnable(String testName, ExcelReader excel){
		
		String sheetName="test_suite";
		int rows = excel.getRowCount(sheetName);
		
		
		for(int rNum=2; rNum<=rows; rNum++){
			
			String testCase = excel.getCellData(sheetName, "TCID", rNum);
			
			if(testCase.equalsIgnoreCase(testName)){
				
				String runmode = excel.getCellData(sheetName, "Runmode", rNum);
				
				if(runmode.equalsIgnoreCase("Y"))
					
					return true;
				else
					return false;
			}
			
			
		}
		return false;
	}

	public String random(int length)
	{
		String alphabet="AUTOMATIONABCDEFGHIJLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder();

		// create an object of Random class
		Random random = new Random();


		for(int i = 0; i < length; i++) {

			// generate random index number
			int index = random.nextInt(alphabet.length());

			// get character specified by index
			// from the string
			char randomChar = alphabet.charAt(index);

			// append the character to string builder
			sb.append(randomChar);
		}

		String randomString = sb.toString();
		return randomString;
	}

}
