package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import webDriverUtility.WebDriverUtility;

public class HomePage {
	
	WebDriver driver;
	
	WebDriverUtility wLib=new WebDriverUtility();
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	//span[text()='Create Campaign']
	@FindBy(xpath="//span[text()='Create Campaign']")
	private WebElement createCampagin;
	
	@FindBy(linkText = "Contacts")
	private WebElement contactsBtn;
	
	@FindBy(xpath = "//div[@class='user-icon']")
	private WebElement userIcon;
	
	@FindBy(xpath = "//div[text()='Logout ']")
	private WebElement logoutBtn;
	
	@FindBy(xpath = "//div[@role='alert']")
	private WebElement toastMsg;
	
	@FindBy(xpath = "//button[@aria-label='close']")
	private WebElement closetoastMsg;

	public WebElement getToastMsg() {
		return toastMsg;
	}

	public void setToastMsg(WebElement toastMsg) {
		this.toastMsg = toastMsg;
	}

	public WebElement getClosetoastMsg() {
		return closetoastMsg;
	}

	public void setClosetoastMsg(WebElement closetoastMsg) {
		this.closetoastMsg = closetoastMsg;
	}

	public WebElement getCreateCampagin() {
		return createCampagin;
	}

	public WebElement getContactsBtn() {
		return contactsBtn;
	}

	public WebElement getUserIcon() {
		return userIcon;
	}

	public WebElement getLogoutBtn() {
		return logoutBtn;
	}
	
	public void logout() {
		wLib.mouseHoverOnWebElement(driver, userIcon);
		wLib.clickOnWebElement(driver, logoutBtn);
	}

}
