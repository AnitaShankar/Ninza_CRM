package campaign;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import excelutility.ExcelUtility;
import fileUtility.FileUtility;
import javaUtility.JavaUtility;
import objectRepository.CampaignPage;
import objectRepository.ContactsPage;
import objectRepository.CreateContactPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.SelectCampaignPage;
import webDriverUtility.WebDriverUtility;

public class CreateContantWithMandatoryFieldTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		//Read the data from Properties file
//		FileInputStream fis=new FileInputStream("E:\\Online Class Qspider\\Advance Selenium Properties\\NinzaCRMLoginData.Properties");
//		Properties prop=new Properties();
//		prop.load(fis);
//		String browser = prop.getProperty("Browser");
//		String url = prop.getProperty("Url");
//		String username = prop.getProperty("Username");
//		String password = prop.getProperty("Password");
		
		FileUtility fLib=new FileUtility();
		String browser = fLib.ReadDataFromPropertiesFile("Browser");
		String url = fLib.ReadDataFromPropertiesFile("Url");
		String username = fLib.ReadDataFromPropertiesFile("Username");
		String password = fLib.ReadDataFromPropertiesFile("Password");
		
		//Read the data from Excel sheet
		FileInputStream fis1=new FileInputStream("E:\\Online Class Qspider\\Advance Selenium Properties\\CRM_TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("Contant");
		Row r = sh.getRow(1);
		String compaign_Name = r.getCell(2).getStringCellValue();
		String target_Size = r.getCell(3).getStringCellValue();
		
		String organization = r.getCell(4).getStringCellValue();
		String title = r.getCell(5).getStringCellValue();
		String contact_Name = r.getCell(6).getStringCellValue();
		String mobile_Number = r.getCell(7).getStringCellValue();
		
//		ExcelUtility eLib=new ExcelUtility();
//		String compaign_Name = eLib.ReadDataFromExcelFile("Contant", 1, 2);
//								//eLib.readDataFromExcelFile("Contact", 1, 2);
//		String target_Size = eLib.ReadDataFromExcelFile("Contant", 1, 3);
//		String organization = eLib.ReadDataFromExcelFile("Contant", 1, 4);
//		String title = eLib.ReadDataFromExcelFile("Contant", 1, 5);
//		String contact_Name = eLib.ReadDataFromExcelFile("Contant", 1, 6);
//		String mobile_Number = eLib.ReadDataFromExcelFile("Contant", 1, 7);
	
		
		WebDriver driver=null;
		
		if(browser.equalsIgnoreCase("Chrome"))
			driver=new ChromeDriver();
		else if(browser.equalsIgnoreCase("edge"))
			driver=new EdgeDriver();
		else if(browser.equalsIgnoreCase("safari"))
			driver= new SafariDriver();
		else if(browser.equalsIgnoreCase("fireFox"))
			driver=new FirefoxDriver();
		
		//Getting random Number
//		Random ranInt=new Random();
//		int Random_Number = ranInt.nextInt(1000);
		
		JavaUtility jLib=new JavaUtility();
		int randomNumber = jLib.getRandonNumber();
		
		String campaignName = compaign_Name+randomNumber;
		
		//Login to Ninza CRM
//		driver.get(url);
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//		driver.findElement(By.id("username")).sendKeys(username);
//		driver.findElement(By.id("inputPassword")).sendKeys(password);
//		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		
		LoginPage lp=new LoginPage(driver);
		lp.login(url, username, password);
		
		//Create Campaign
//		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
//		driver.findElement(By.name("campaignName")).sendKeys(campaignName);
//		WebElement targetSize = driver.findElement(By.name("targetSize"));
//		targetSize.clear();
//		targetSize.sendKeys(target_Size);
//		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		
		CampaignPage cp=new CampaignPage(driver);
		cp.createCampaign(campaignName, target_Size);
		Thread.sleep(2000);
		cp.getCreateCampaignSubmitBtn().click();
		
		HomePage hp=new HomePage(driver);
		WebDriverUtility wLib=new WebDriverUtility();
		
		//Validating
//		WebElement toastMsg = driver.findElement(By.xpath("//div[@role='alert']"));	
//		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(20));
//		wait.until(ExpectedConditions.visibilityOf(toastMsg));
//		String msg = toastMsg.getText();
//		System.out.println(msg);
//		driver.findElement(By.xpath("//button[@aria-label='close']")).click();	
		
		wLib.waitForVisibilityOfWebElement(driver, hp.getToastMsg());
		String campaignMsg = hp.getToastMsg().getText();
		System.out.println(campaignMsg);
		hp.getClosetoastMsg().click();
		
		//Create Contanct
//		driver.findElement(By.linkText("Contacts")).click();
//		driver.findElement(By.xpath("//span[text()='Create Contact']")).click();
		
		hp.getContactsBtn().click();
		ContactsPage contactPage=new ContactsPage(driver);
		contactPage.getCreateContactBtn().click();
		
		
//		driver.findElement(By.name("organizationName")).sendKeys(organization);
//		driver.findElement(By.name("title")).sendKeys(title);
//		driver.findElement(By.name("contactName")).sendKeys(contact_Name);
//		driver.findElement(By.name("mobile")).sendKeys(mobile_Number);
//		driver.findElement(By.xpath("//*[name()='svg' and @data-icon='plus']")).click();
		
		CreateContactPage createContactPage=new CreateContactPage(driver);
		createContactPage.getOrganizationNameTF().sendKeys(organization);
		createContactPage.getTitleTF().sendKeys(title);
		createContactPage.getContactNameTF().sendKeys(contact_Name);
		createContactPage.getMobileTF().sendKeys(mobile_Number);
		createContactPage.getPlusBtn().click();
		
		String parentID = driver.getWindowHandle();
//		Set<String> allID = driver.getWindowHandles();
//		allID.remove(parentID);
//		for(String id:allID)
//			driver.switchTo().window(id);
		wLib.switchToWindowOnTitle(driver, "Select Campaign");
//			String titleOfChildWindow = driver.getTitle();
//		WebElement campaignDD = driver.findElement(By.id("search-criteria"));
			SelectCampaignPage selectCampaignPage= new SelectCampaignPage(driver);
			WebElement campaignDD = selectCampaignPage.getCampaignDD();
//		Select obj=new Select(campaignDD);
//		obj.selectByIndex(1);
			wLib.select(campaignDD, 1);
//		driver.findElement(By.id("search-input")).sendKeys(campaignName);
		selectCampaignPage.getSearchBar().sendKeys(campaignName);
//		driver.findElement(By.xpath("//button[text()='Select']")).click();
//		Thread.sleep(2000);
		selectCampaignPage.getSelectBtn().click();
		driver.switchTo().window(parentID);
//		driver.findElement(By.xpath("//button[text()='Create Contact']")).click();
		createContactPage.getCreateContactSubmitBtn().click();
		
		//Validation
//		WebElement contactToast = driver.findElement(By.xpath("//div[@role='alert']"));
//		wait.until(ExpectedConditions.visibilityOf(contactToast));
//		String contactToastMsg = contactToast.getText();
//		System.out.println(contactToastMsg);
//		driver.findElement(By.xpath("//button[@aria-label='close']")).click();
		
		wLib.waitForVisibilityOfWebElement(driver, hp.getToastMsg());
		String contactMsg = hp.getToastMsg().getText();
		System.out.println(contactMsg);
		hp.getClosetoastMsg().click();
		
		//Logout
//		WebElement logout = driver.findElement(By.xpath("//div[@class='user-icon']"));
//		Actions action=new Actions(driver);
//		action.moveToElement(logout).perform();
//		WebElement logoutBtn = driver.findElement(By.xpath("//div[text()='Logout ']"));
//		action.moveToElement(logoutBtn).click().perform();
//		
//		driver.quit();
		
		hp.logout();
		
		driver.quit();
		
		
	}

}
