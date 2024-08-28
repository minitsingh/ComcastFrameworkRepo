package com.comcast.crm.objectrepositoyUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreatingNewOrganizationPage {
	
	WebDriver driver;
	  // Rule-3 Object Initialization
	
	public CreatingNewOrganizationPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@name='accountname']")
	private WebElement orgNameEdit;
	
	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[1]")
	private WebElement saveBtn;
	
	@FindBy(name="industry")
	private WebElement industryDD;
	
	@FindBy(name="accounttype")
	private WebElement typeDD;
	
	@FindBy(id = "phone")
	private WebElement phoneNumEdit;
	
	
	
	

	public WebElement getTypeDD() {
		return typeDD;
	}

	public WebElement getPhoneNumEdit() {
		return phoneNumEdit;
	}

	public WebElement getIndustryDD() {
		return industryDD;
	}

	public WebElement getType() {
		return typeDD;
	}

	public WebElement getOrgNameEdit() {
		return orgNameEdit;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	//method overloading
	
	public void createOrg(String orgName)
	{
		orgNameEdit.sendKeys(orgName);
		saveBtn.click();
	}
	
	public void createOrg(String orgName, String industry)
	{
		
		WebDriverUtility wLib = new WebDriverUtility();
		
		orgNameEdit.sendKeys(orgName);
		wLib.select(industryDD, industry);
		saveBtn.click();
	}
	
	public void createOrg(String orgName,String industry,String type)
	{
		WebDriverUtility wLib = new WebDriverUtility();
		orgNameEdit.sendKeys(orgName);
		wLib.select(industryDD, industry);
		wLib.select(typeDD, type);
		saveBtn.click();
			
	}
	
	public void createOrgWithPhone(String orgName,String phoneNumber)
	{
		orgNameEdit.sendKeys(orgName);
		phoneNumEdit.sendKeys(phoneNumber);
		saveBtn.click();
	}
	
	
	
	
	

}
