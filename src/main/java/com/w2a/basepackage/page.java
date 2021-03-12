package com.w2a.basepackage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.utilities.ExcelReader;
import com.utilities.ExtentManager;
import com.utilities.Utilities;

public class page {
	
	
	public static WebDriver driver = null;
	public static Properties config=new Properties();
	public static Properties OR =new Properties();
	public static FileInputStream fis;
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\com\\oracle\\excel\\DataSheet.xlsx");
	public static Logger log =Logger.getLogger("devpinoylogger");
	public static WebDriverWait wait;
	public ExtentReports rep=ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser=null;
	public static topMenu menu;
	public page() {
	if(driver==null)
	{
		
		try {
			fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\com\\oracle\\properties\\config.properties");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			config.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("configfileloaded");
		
		try {
			fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\com\\oracle\\properties\\or.properties");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			OR.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("object repository file loaded");
		if(System.getenv("browser")!=null && !System.getenv("browser").isEmpty())
		{
			browser=System.getenv("browser");
		}
		else {
			browser=config.getProperty("browser");
		}
		
		System.setProperty("browser", browser);
		
		if (config.getProperty("browser").equals("firefox")) {

			// System.setProperty("webdriver.gecko.driver", "gecko.exe");
			driver = new FirefoxDriver();

		} else if (config.getProperty("browser").equals("chrome")) {

			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\com\\oracle\\executables\\chromedriver.exe");
			driver = new ChromeDriver();
			log.debug("Chrome Launched !!!");
	
			
		   
		   /*setting preferences in chrome*/
		    Map<String,Object> prefs=new HashMap<String,Object>();
		   prefs.put("profile.default_content_setting_values.notifications", 2);
		   prefs.put("credentials_enable_service", false);
		   prefs.put("profile.password_manager_enabled", false);
		   ChromeOptions options=new ChromeOptions();
		   options.setExperimentalOption("prefs", prefs);
		   options.addArguments("--disable-extensions");
		   options.addArguments("--disable-infobars");
			driver.get(config.getProperty("testsiteurl"));
			log.debug("navigated to:"+config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitlywait")), TimeUnit.SECONDS);	
		   menu=new topMenu(driver);
		}
		  // page.menu.gotoAccounts();
	}
	
  }public boolean isElementPresent(By by)
	{
		try {
			driver.findElement(by);
		return true;	
		}
		catch(NoSuchElementException e)
		{
			return false;
		}
		
	}
	
	public void click(String Locator)
	{
		
		driver.findElement(By.xpath(OR.getProperty(Locator))).click();		
		test.log(LogStatus.INFO, "Clicking on :"+Locator);
		
		
	}
	
	public void typetext(String Locator,String value)
	{
			driver.findElement(By.xpath(OR.getProperty(Locator))).sendKeys(value);
		    test.log(LogStatus.INFO, "Typing in : " + Locator + " entered value as " + value);		
	}
	
	static WebElement ele;
	public void select(String Locator,String Value)
	{
		if(Locator.endsWith("_css"))
		{
			ele=driver.findElement(By.cssSelector(OR.getProperty("Locator")));
		}
		if(Locator.endsWith("_id"))
		{
			ele=driver.findElement(By.id(OR.getProperty("Locator")));
		}
		if(Locator.endsWith("_xpath"))
		{
			ele=driver.findElement(By.xpath(OR.getProperty("Locator")));
		}
		Select select=new Select(ele);
		select.selectByVisibleText(Value);
		
		test.log(LogStatus.INFO, "Selecting the locator:"+Locator+"with value:"+Value);
	}
	
	public static void verifyEquals(String expected, String actual) throws IOException
	{
		try {
			Assert.assertEquals(actual,expected);
		}
		catch(Throwable t) 
		{
			Utilities.screenshot();
			
			//reportng
			Reporter.log("<br>"+"Verification Failed !!"+t.getMessage()+"<br>");
			Reporter.log("<a target=\"_blank\" href="+Utilities.screenshotname+">Screenshot</a>");
			Reporter.log("<br>");
			
			//extent report
			test.log(LogStatus.FAIL, "Verification Failed with Exception:"+ t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(Utilities.screenshotname));
		}
	}
	
	public static void quit()
	{
		driver.quit();
	}

}
