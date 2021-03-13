package com.w2a.basepackage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.utilities.ExcelReader;
import com.utilities.ExtentManager;

public class Page {
	public static WebDriver driver = null;
	public static Properties config=new Properties();
	public static Properties OR =new Properties();
	public static FileInputStream fis;
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\resources\\excel\\DataSheet.xlsx");
	public static WebDriverWait wait;
	public ExtentReports rep=ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser=null;
	public Page() {
	if(driver==null)
	{
		try {
			fis=new FileInputStream(System.getProperty("user.dir")+"\\resources\\properties\\config.properties");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			config.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(System.getenv("browser")!=null && !System.getenv("browser").isEmpty())
		{
			browser=System.getenv("browser");
		}
		else {
			browser=config.getProperty("browser");
		}
		System.setProperty("browser", browser);
		if (config.getProperty("browser").equals("firefox")) {
			driver = new FirefoxDriver();

		} else if (config.getProperty("browser").equals("chrome")) {

			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\resources\\executables\\chromedriver.exe");
			driver = new ChromeDriver();
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
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitlywait")), TimeUnit.SECONDS);
		}
	}
  }
	public static void quit()
	{
		driver.quit();
	}

}
