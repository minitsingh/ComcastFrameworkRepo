package com.comcast.crm.objectrepositoyUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {
			// Rule-1 : Create a separate java class
	
	WebDriver driver;
			// Rule-3 Object Initialization
	
	public ContactPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
			// Rule-2 Object Creation
	
	@FindBy(xpath="//img[@alt='Create Contact...']")
	private WebElement createNewConactBtn; 
	
	@FindBy(xpath="//input[@fdprocessedid='xm5ljf']")
	private WebElement searchEdit;
	
	@FindBy(xpath="//select[@fdprocessedid='xy6mpe']")
	private WebElement searchDD;
	
	@FindBy(name="submit")
	private WebElement searchBtn;
	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement headerMsg;
	
			//Rule-4 : Object Encapsulation
	
			//Rule-5 : Provide Action
	
	

	public WebElement getCreateNewConactBtn() {
		return createNewConactBtn;
	}

	public WebElement getHeaderMsg() {
		return headerMsg;
	}

	public WebElement getSearchEdit() {
		return searchEdit;
	}

	public WebElement getSearchDD() {
		return searchDD;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}
	
	
	

}
