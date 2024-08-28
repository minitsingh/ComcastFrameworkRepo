package com.comcast.crm.objectrepositoyUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	

	WebDriver driver;
	  // Rule-3 Object Initialization
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="Products")
	private WebElement productLink;
	
	@FindBy(linkText = "Organizations" )
	private WebElement orgLink;
	
	@FindBy(linkText = "Contacts" )
	private WebElement contactLink;
	
	@FindBy(linkText = "Campaigns")
	private WebElement campaignlink;
	
	@FindBy(linkText = "More")
	private WebElement morelink;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImg;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signOutLink;
	

	public WebElement getOrgLink() {
		return orgLink;
	}
	
	public WebElement getContactLink() {
		return contactLink;
	}
	
	public WebElement getCampaignlink() {
		return campaignlink;
	}

	public WebElement getMorelink() {
		return morelink;
	}
	
	public WebElement getProductLink() {
		return productLink;
	}

	public WebElement getAdminImg() {
		return adminImg;
	}

	public WebElement getSignOutLink() {
		return signOutLink;
	}

	
	
	/**
	 * Business logic
	 */
	public void navigateToCampaignPage()
	{
		Actions action = new Actions(driver);
		action.moveToElement(campaignlink).perform();;
		campaignlink.click();
	}

	public void logout()
	{
		Actions action = new Actions(driver);
		action.moveToElement(adminImg).perform();
		signOutLink.click();
	}

	
	
	

}
