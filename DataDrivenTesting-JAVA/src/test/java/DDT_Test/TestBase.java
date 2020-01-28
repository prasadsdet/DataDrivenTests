package DDT_Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class TestBase 
{
	public static WebDriver driver;
	public static Properties prop;


	public TestBase()
	{
		prop = new Properties();
		try 
		{
			FileInputStream ip = new FileInputStream("D:\\Automation\\Selenium\\Java\\GitHub\\DataDrivenTesting-JAVA\\src\\main\\java\\DDT_Helpers\\config.properties");			
			prop.load(ip);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	@BeforeTest
	public static void initialization()
	{
		String browserName = prop.getProperty("browser");		
		if(browserName.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Projects\\AutomationTesting\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else 
			if(browserName.equals("FF"))
			{
				System.setProperty("webdriver.gecko.driver","C:\\Projects\\AutomationTesting\\Drivers\\geckodriver.exe");
				driver = new FirefoxDriver();			
			}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);		
		driver.get(prop.getProperty("URL"));
	}
	
	@AfterTest
	public void CleanUp()
	{
		driver.close();
		driver.quit();
	}
}
