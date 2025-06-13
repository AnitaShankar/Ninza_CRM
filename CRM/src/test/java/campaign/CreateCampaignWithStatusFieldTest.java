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

public class CreateCampaignWithStatusFieldTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		// Read the data from Properties file
		FileUtility fLib = new FileUtility();

		String browser = fLib.ReadDataFromPropertiesFile("Browser");
		String url = fLib.ReadDataFromPropertiesFile("Url");
		String username = fLib.ReadDataFromPropertiesFile("Username");
		String password = fLib.ReadDataFromPropertiesFile("Password");

		// Read the data from Excel file
		ExcelUtility eLib = new ExcelUtility();
		String campagin_Name = eLib.ReadDataFromExcelFile("Campaign", 4, 2);
		String target_Size = eLib.ReadDataFromExcelFile("Campaign", 4, 3);
		String status = eLib.ReadDataFromExcelFile("Campaign", 4, 4);

		WebDriver driver = null;
		if (browser.equalsIgnoreCase("Chrome"))
			driver = new ChromeDriver();
		else if (browser.equalsIgnoreCase("Edge"))
			driver = new EdgeDriver();
		else if (browser.equalsIgnoreCase("Firefox"))
			driver = new FirefoxDriver();
		else if (browser.equalsIgnoreCase("Safari"))
			driver = new SafariDriver();

		

		// generating random number

		JavaUtility jLib=new JavaUtility();
		int randomNumber = jLib.getRandonNumber();
		
		WebDriverUtility wLib=new WebDriverUtility();

		String campaignName = campagin_Name + randomNumber;

		// LOgin to Ninza CRM
		LoginPage lp=new LoginPage(driver);
		lp.login(url, username, password);
		
		HomePage hp=new HomePage(driver);
		CampaignPage cp=new CampaignPage(driver);
		

		// Create Campaign
		cp.createCampaign(campaignName, target_Size);
		Thread.sleep(2000);
		cp.getCampaignStatusTF().sendKeys(status);

		//driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		cp.getCreateCampaignSubmitBtn().click();
		// Validating
		//WebElement toastMSG = driver.findElement(By.xpath("//div[@role='alert']"));
		wLib.waitForVisibilityOfWebElement(driver, hp.getToastMsg());
		String msg = hp.getToastMsg().getText();
		System.out.println(msg);
		//driver.findElement(By.xpath("//button[@aria-label='close']")).click();
		hp.getClosetoastMsg().click();
		if (msg.contains(campaignName))
			System.out.println("Campaign created Sucessfully");
		else
			System.out.println("Campaign not created");

		// Logout
		hp.logout();
	}

}
