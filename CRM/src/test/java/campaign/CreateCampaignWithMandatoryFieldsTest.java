package campaign;

import java.io.IOException;
import java.time.Duration;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import excelutility.ExcelUtility;
import fileUtility.FileUtility;
import javaUtility.JavaUtility;
import objectRepository.CampaignPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import webDriverUtility.WebDriverUtility;

public class CreateCampaignWithMandatoryFieldsTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		// Reading the data from properties files
		FileUtility fLib = new FileUtility();

		String browser = fLib.ReadDataFromPropertiesFile("Browser");
		String url = fLib.ReadDataFromPropertiesFile("Url");
		String username = fLib.ReadDataFromPropertiesFile("Username");
		String password = fLib.ReadDataFromPropertiesFile("Password");

		// Reading the data from Excel file
		ExcelUtility eLib = new ExcelUtility();
		String campaginName = eLib.ReadDataFromExcelFile("Campaign", 1, 2);
		String target_Size = eLib.ReadDataFromExcelFile("Campaign", 1, 3);
		
		

		WebDriver driver = null;
		if (browser.equalsIgnoreCase("Chrome"))
			driver = new ChromeDriver();
		else if (browser.equalsIgnoreCase("Edge"))
			driver = new EdgeDriver();
		else if (browser.equalsIgnoreCase("Firefox"))
			driver = new FirefoxDriver();
		else if (browser.equalsIgnoreCase("Safari"))
			driver = new SafariDriver();

		

		// Generating Random number

		JavaUtility jLib=new JavaUtility();
		int randomNumber = jLib.getRandonNumber();
		
		WebDriverUtility wLib=new WebDriverUtility();

		String campaign_Name = campaginName + randomNumber;

		// LOgin to Ninza CRM
		LoginPage lp=new LoginPage(driver);
		lp.login(url, username, password);
		
		HomePage hp=new HomePage(driver);
		CampaignPage cp=new CampaignPage(driver);

		// Create Campaign
		
		cp.createCampaign(campaign_Name, target_Size);
		cp.getCreateCampaignSubmitBtn().click();
		//driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();

		// Validating
		//WebElement toastMSG = driver.findElement(By.xpath("//div[@role='alert']"));
		
		wLib.waitForVisibilityOfWebElement(driver, hp.getToastMsg());
		String msg = hp.getToastMsg().getText();
		System.out.println(msg);
		//driver.findElement(By.xpath("//button[@aria-label='close']")).click();
		hp.getClosetoastMsg().click();
		if (msg.contains(campaign_Name))
			System.out.println("Campaign created Sucessfully");
		else
			System.out.println("Campaign not created");

		// Logout
		hp.logout();
		System.out.println("Hello");

	}

}
