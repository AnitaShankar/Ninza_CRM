package objectRepository;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id="username")
	private WebElement userNameTF;
	
	@FindBy(id = "inputPassword")
	private WebElement passWordTF;
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement loginBtn;

	public WebElement getUserNameTF() {
		return userNameTF;
	}

	public WebElement getPassWordTF() {
		return passWordTF;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	
	public void login(String url, String username, String password) {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(url);
		userNameTF.sendKeys(username);
		passWordTF.sendKeys(password);
		loginBtn.click();
		
	}

}
