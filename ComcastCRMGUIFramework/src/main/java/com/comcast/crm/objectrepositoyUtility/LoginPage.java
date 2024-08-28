package com.comcast.crm.objectrepositoyUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

/**
 * 
 * @author MINIT SINGH
 * 
 * Contains Login Page Elements & Business libraries
 * 
 */
public class LoginPage extends WebDriverUtility{  // Rule-1 : Create a seperate java class
						  
	
	WebDriver driver;
	  // Rule-3 Object Initialization
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	                   // Rule-2 : Object creation
	@FindBy(name="user_name")
	private WebElement usernameEdit;
	
	@FindBy(name="user_password")
	private WebElement passwordEdit;
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;
	
	
							//Rule-4 : Object Encapsulation
							
							//Rule-5 : Provide Action
	public WebElement getUsernameEdt() {
		return usernameEdit;
	}

	public WebElement getPasswordnameEdt() {
		return passwordEdit;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	//provided actions
	
	/**
	 * Login to application based on username,password,url arguments
	 * @param url
	 * @param username
	 * @param password
	 */
	
	public void loginToApp(String url, String username, String password)
	{
		waitForPageToLoad(driver);
		driver.get(url);
		driver.manage().window().maximize();
		usernameEdit.sendKeys(username);
		passwordEdit.sendKeys(password);
		loginBtn.click();
	}
	
	
	
	
	

}
