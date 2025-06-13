package campaignTestUsingTestNG;

import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
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
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import baseClass.BaseCLass;
import excelutility.ExcelUtility;
import fileUtility.FileUtility;
import javaUtility.JavaUtility;
import objectRepository.CampaignPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import webDriverUtility.WebDriverUtility;

@Listeners(listenersUtility.ListenersImplementationClass.class)
public class createCampaignTest extends BaseCLass{
	
	
	@Test(groups = "Smoke")
	public void createCampaignWithMandatoryFieldTest() throws IOException {

		ExcelUtility eLib = new ExcelUtility();
		String campaginName = eLib.ReadDataFromExcelFile("Campaign", 1, 2);
		String target_Size = eLib.ReadDataFromExcelFile("Campaign", 1, 3);
		
		JavaUtility jLib=new JavaUtility();
		int randomNumber = jLib.getRandonNumber();
		
		WebDriverUtility wLib=new WebDriverUtility();
		HomePage hp = new HomePage(driver);
		CampaignPage cp=new CampaignPage(driver);

		String campaign_Name = campaginName + randomNumber;

		cp.createCampaign(campaign_Name, target_Size);
		cp.getCreateCampaignSubmitBtn().click();
		
		wLib.waitForVisibilityOfWebElement(driver, hp.getToastMsg());
		String msg = hp.getToastMsg().getText();
		System.out.println(msg);

		
//		if (msg.contains(campaign_Name))
//			System.out.println("Campaign created Sucessfully");
//		else
//			System.out.println("Campaign not created");	
		Assert.assertEquals(msg,"Campaign "+campaign_Name+" Successfully Added");
		hp.getClosetoastMsg().click();

	}
	
	
	@Test(groups = "Regression" )
	public void CreateCampaignWithExpectedCloseDateFieldTest() throws EncryptedDocumentException, IOException {
		
		ExcelUtility eLib = new ExcelUtility();
		String campagin_Name = eLib.ReadDataFromExcelFile("Campaign", 7, 2);
		String target_Size = eLib.ReadDataFromExcelFile("Campaign", 7, 3);
		//String status = eLib.ReadDataFromExcelFile("Campaign", 7, 4);
		
		JavaUtility jLib=new JavaUtility();
		int randomNumber = jLib.getRandonNumber();
		String campaignName = campagin_Name + randomNumber;
		// get the date from current day
		String dateRequired = jLib.getRequiredDate(30);
		System.out.println(dateRequired);
	
		WebDriverUtility wLib=new WebDriverUtility();
		HomePage hp = new HomePage(driver);
		CampaignPage cp=new CampaignPage(driver);
		
		wLib.waitForPageToLoad(driver);
		cp.createCampaign(campaignName, target_Size);
		cp.getExpectedCloseDate().clear();
		wLib.enterInput(driver,cp.getExpectedCloseDate(), dateRequired);
		cp.getCreateCampaignSubmitBtn().click();
		
		wLib.waitForVisibilityOfWebElement(driver, hp.getToastMsg());
		String msg = hp.getToastMsg().getText();
		System.out.println(msg);
		//driver.findElement(By.xpath("//button[@aria-label='close']")).click();
		hp.getClosetoastMsg().click();
		if (msg.contains(campaignName))
			System.out.println("Campaign created Sucessfully");
		else
			System.out.println("Campaign not created");

	}
	
	
	@Test(groups = {"Smoke","Regression"})
	public void CreateCampaignWithStatusFieldTest() throws EncryptedDocumentException, IOException, InterruptedException {
		ExcelUtility eLib = new ExcelUtility();
		String campagin_Name = eLib.ReadDataFromExcelFile("Campaign", 4, 2);
		String target_Size = eLib.ReadDataFromExcelFile("Campaign", 4, 3);
		String status = eLib.ReadDataFromExcelFile("Campaign", 4, 4);
		
		JavaUtility jLib=new JavaUtility();
		int randomNumber = jLib.getRandonNumber();
		
		WebDriverUtility wLib=new WebDriverUtility();

		String campaignName = campagin_Name + randomNumber;
		
		HomePage hp=new HomePage(driver);
		CampaignPage cp=new CampaignPage(driver);
		cp.createCampaign(campaignName, target_Size);
		
		Thread.sleep(2000);
		cp.getCampaignStatusTF().sendKeys(status);
		cp.getCreateCampaignSubmitBtn().click();
		wLib.waitForVisibilityOfWebElement(driver, hp.getToastMsg());
		String msg = hp.getToastMsg().getText();
		System.out.println(msg);
		hp.getClosetoastMsg().click();
		
		if (msg.contains(campaignName))
			System.out.println("Campaign created Sucessfully");
		else
			System.out.println("Campaign not created");
		
	}

}
