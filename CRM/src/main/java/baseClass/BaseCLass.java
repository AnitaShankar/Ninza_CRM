package baseClass;

import org.testng.annotations.Test;

import fileUtility.FileUtility;
import objectRepository.HomePage;
import objectRepository.LoginPage;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class BaseCLass {

	public FileUtility fLib = new FileUtility();
	public WebDriver driver=null;
	public static WebDriver sdriver=null;
	
	@BeforeSuite(groups = {"Smoke","Regression"})
	public void beforeSuite() {
		
		System.out.println("Establish the database connection");
	}

	@BeforeTest(groups = {"Smoke","Regression"})
	public void beforeTest() {
		System.out.println("Precondition");
	}

	//@Parameters("Browser")
	@BeforeClass(groups = {"Smoke","Regression"})
	public void beforeClass() throws IOException {
		 String browser = fLib.ReadDataFromPropertiesFile("Browser");
		if (browser.equalsIgnoreCase("Chrome"))
			driver = new ChromeDriver();
		else if (browser.equalsIgnoreCase("Edge"))
			driver = new EdgeDriver();
		else if (browser.equalsIgnoreCase("Firefox"))
			driver = new FirefoxDriver();
		else if (browser.equalsIgnoreCase("Safari"))
			driver = new SafariDriver();
		
		sdriver=driver;
		System.out.println("Launch the browser");
	}

	@BeforeMethod(groups = {"Smoke","Regression"})
	public void beforeMethod() throws IOException {
		//String url = fLib.ReadDataFromPropertiesFile("Url");
		String url=System.getProperty("url");
		String username=System.getProperty("Username");
		//String username = fLib.ReadDataFromPropertiesFile("Username");
		String password=System.getProperty("Password");
		//String password = fLib.ReadDataFromPropertiesFile("Password");
		LoginPage lp=new LoginPage(driver);
		lp.login(url, username, password);
		System.out.println("Login");
	}

	@AfterMethod(groups = {"Smoke","Regression"})
	public void afterMethod() {
		HomePage hp=new HomePage(driver);
		hp.logout();
		System.out.println("Logout");
	}

	@AfterClass(groups = {"Smoke","Regression"})
	public void afterClass() {
		driver.quit();
		System.out.println("Close the browser");
	}

	@AfterTest(groups = {"Smoke","Regression"})
	public void afterTest() {
		System.out.println("Post condition");
	}

	@AfterSuite(groups = {"Smoke","Regression"})
	public void afterSuite() {
		System.out.println("Close database condition");
	}

}
