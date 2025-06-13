package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import webDriverUtility.WebDriverUtility;

public class CampaignPage {
	
    WebDriver driver;
    
   
	
    public CampaignPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
    
    @FindBy(name="campaignName")
    private WebElement campaignNameTF;
    
    @FindBy(name="targetSize")
    private WebElement targetSizeTF;
    
    @FindBy(name="campaignStatus")
    private WebElement campaignStatusTF;
    
    @FindBy(name="expectedCloseDate")
    private WebElement expectedCloseDate;
    
    @FindBy(xpath = "//button[text()='Create Campaign']")
    private WebElement createCampaignSubmitBtn;

	public WebElement getCampaignNameTF() {
		return campaignNameTF;
	}

	public WebElement getTargetSizeTF() {
		return targetSizeTF;
	}

	public WebElement getCampaignStatusTF() {
		return campaignStatusTF;
	}

	public WebElement getExpectedCloseDate() {
		return expectedCloseDate;
	}

	public WebElement getCreateCampaignSubmitBtn() {
		return createCampaignSubmitBtn;
	}
	
	
	public void createCampaign(String campaign_Name, String target_Size) {
		 HomePage hp=new HomePage(driver);
		hp.getCreateCampagin().click();
		campaignNameTF.sendKeys(campaign_Name);
		targetSizeTF.clear();
		targetSizeTF.sendKeys(target_Size);
	}

}
