package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	
	public HomePage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(xpath="//span[text()='My Account']")
	WebElement linkMyaccount;
	
	@FindBy(xpath="//a[text()='Register']")
	WebElement linkRegister;
	
	@FindBy(xpath="//a[normalize-space()='Login']")
	WebElement linkLogin;
	
	
	public void clickMyaccount()
	{
		linkMyaccount.click();
	}
	
	public void clickRegister()
	{
		linkRegister.click();
		
	}
	
	public void clickLogin()
	{
		linkLogin.click();
		
	}
	
	
	

}
