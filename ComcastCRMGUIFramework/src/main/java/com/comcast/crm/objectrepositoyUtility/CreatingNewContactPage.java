package com.comcast.crm.objectrepositoyUtility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;


public class CreatingNewContactPage {
			//rule-1  create a separate class
	
	WebDriver driver;
			// Rule-3 Object Initialization
	
	public CreatingNewContactPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
			// Rule-2 Object Creation
	
	@FindBy(name="lastname")
	private WebElement lastNameEdit;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(xpath="//input[@id='jscal_field_support_start_date']")
	private WebElement startDateEdit;

	@FindBy(xpath="//input[@id='jscal_field_support_end_date']")
	private WebElement endDateEdit;
	
	@FindBy(xpath="(//img[@alt='Select'])[1]")
	private WebElement orgLookUp;
	
	@FindBy(name = "search_text")
	private WebElement searchOrg;
	
	@FindBy(name = "search")
	private WebElement searchOrgBtn;
	
	
	
			//Rule-4 : Object Encapsulation
	
			//Rule-5 : Provide Action

	public WebElement getOrgLookUp() {
		return orgLookUp;
	}

	public WebElement getSearchOrg() {
		return searchOrg;
	}

	public WebElement getSearchOrgBtn() {
		return searchOrgBtn;
	}

	public WebElement getStartDateEdit() {
		return startDateEdit;
	}

	public WebElement getEndDateEdit() {
		return endDateEdit;
	}

	public WebElement getLastNameEdit() {
		return lastNameEdit;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
			// object utilization
	
	public void createContact(String lastname)
	{
		lastNameEdit.sendKeys(lastname);
		saveBtn.click();
	}
	
	public void createContactWithSupportDate(String lastName,String startDate,String endDate)
	{
		lastNameEdit.sendKeys(lastName);
		startDateEdit.sendKeys(startDate);
		endDateEdit.sendKeys(endDate);
		saveBtn.click();	
	}
	
	public void createContactwithOrg(String lastName,String orgName) throws InterruptedException
	{
		lastNameEdit.sendKeys(lastName);
		orgLookUp.click();
		WebDriverUtility wLib = new WebDriverUtility();
		wLib.switchToTabOnURL(driver, "Accounts&action");
		searchOrg.sendKeys(orgName);
		searchOrgBtn.click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		wLib.switchToTabOnURL(driver, "Contacts&action");
		saveBtn.click();
	}
	
	
	

}
