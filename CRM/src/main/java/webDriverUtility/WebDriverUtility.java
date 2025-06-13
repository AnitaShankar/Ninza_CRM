package webDriverUtility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	
	//wait methods
	
	public void waitForPageToLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	public void waitForVisibilityOfWebElement(WebDriver driver, WebElement element)
	{
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
				
	}
	
	//switch to frame 
	
	
	public void switchToFrame(WebDriver driver, int index)
	{
		driver.switchTo().frame(index);
	}
	
	public void switchToFrame(WebDriver driver, String nameOrID)
	{
		driver.switchTo().frame(nameOrID);
	}
	
	public void switchToFrame(WebDriver driver, WebElement frameElement)
	{
		driver.switchTo().frame(frameElement);
	}
	
	//Alert popUps
	
	
	public void switchToAlertAndAccept(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	public void switchToAlertAndDismiss(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	public String switchToAlertAndgetText(WebDriver driver)
	{
		String text=driver.switchTo().alert().getText();
		return text;
	}
	
	public void switchToAlertAndSendKeys(WebDriver driver, String text) 
	{
		driver.switchTo().alert().sendKeys(text);
	}
	
	
	//Select class 
	
	public void select(WebElement element, int index)
	{
		Select obj=new Select(element);
		obj.selectByIndex(index);
		
	}
	
	public void select(WebElement element, String value)
	{
		Select obj=new Select(element);
		obj.selectByValue(value);
	}
	
	public void select(String text, WebElement element)
	{
		Select obj=new Select(element);
		obj.selectByVisibleText(text);
	}
	
	
	//Action Class
	
	public void mouseHoverOnWebElement(WebDriver driver, WebElement element) {
		Actions action=new Actions(driver);
		action.moveToElement(element).perform();
		}
	
	public void clickOnWebElement(WebDriver driver, WebElement element) {
		Actions action=new Actions(driver);
		action.moveToElement(element).click().perform();
	}
	
	public void doubleClickOnWebElement(WebDriver driver,WebElement element) {
		Actions action=new Actions(driver);
		action.doubleClick(element).perform();
	}
	
	public void rightClick(WebDriver driver,WebElement element) {
		Actions action=new Actions(driver);
		action.contextClick(element).perform();
	}
	
	public void enterInput(WebDriver driver,WebElement element, String text) {
		Actions action=new Actions(driver);
		action.click(element).sendKeys(text).build().perform();
	}
	
	
	//Switch to Windows
	
	public void switchToWindowOnTitle(WebDriver driver, String expectedTitle) {
		Set<String> set = driver.getWindowHandles();
		for(String id:set) {
			driver.switchTo().window(id);
			if(driver.getTitle().contains(expectedTitle))
				break;
		}
		
	}
	
	public void switchToWindowOnCurrentURL(WebDriver driver, String expectedURL) {
		Set<String> set = driver.getWindowHandles();
		for(String id:set) {
			driver.switchTo().window(id);
			if(driver.getCurrentUrl().contains(expectedURL))
				break;
		}
		
	}
	
	//Taking ScreenShot of WebPage
	
	public void takeScreenshotOfPage(WebDriver driver, String filename) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./Screenshot"+filename+".png");
		FileHandler.copy(src, dest);
		
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
