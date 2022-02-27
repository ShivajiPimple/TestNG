import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CrossBrowser 
{
	WebDriver driver;
	
	@BeforeSuite()
	@Parameters({"browser"})
	public void OpenBrowser()
	{
		System.out.println("Browser Opened");
		System.setProperty("webdriver.chrome.driver", "H:\\New folder\\Shivaji Automation\\AUTOMATION SEKE\\chromedriver_win32\\chromedriver.exe");
		driver= new ChromeDriver();
		
	}
	@BeforeTest()
	public void EnterURL()
	{
		System.out.println("URL Entered");
		driver.get("https://conceptkart.com/");
	}
	@BeforeClass()
	public void MaximizeBrowser()
	{
		System.out.println("Browser Maximized");
		driver.manage().window().maximize();
	}
	@BeforeMethod()
	public void GetCookies()
	{
		System.out.println("Got Cookies");
	}
	@Test
	@Parameters({"username","password"})
	public void LogIN(String user,String pass)
	{
		System.out.println("Logged Into Account");
		driver.findElement(By.xpath("//a[@class='header__individual-icon']")).click();
		  driver.findElement(By.id("CustomerEmail")).sendKeys(user);
		  driver.findElement(By.id("CustomerPassword")).sendKeys(pass);
		  driver.findElement(By.xpath("(//input[@type='submit'])[1]")).click();
		  driver.findElement(By.xpath("//a[@class='header__individual-icon']")).click();
		  driver.findElement(By.xpath("(//a[@href='/account/logout'])[2]")).click();
	}
	@AfterMethod()
	public void GetScreenshot() throws IOException
	{
		System.out.println("Screenshot Captured");
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFileToDirectory(src, new File("C:\\Users\\Admin\\eclipse-workspace\\TestNG\\ss"));
	}
	@AfterClass()
	public void DeleteCookies()
	{
		System.out.println("Cookies Deleted");
	}
	@AfterTest()
	public void DbConnectionClose()
	{
		System.out.println("Closed DB Connection");
	}
	@AfterSuite()
	public void CloseBrowser()
	{
		System.out.println("Browser Closed");
		driver.close();
	}
}
