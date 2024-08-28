package com.comcast.crm.objectrepositoyUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {

	WebDriver driver;

	public ProductsPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@alt='Create Product...'")
	private WebElement createProductImgbtn;
	
	@FindBy(name="searchBtn")
	private WebElement ele3;
	
	@FindBy(name="search")
	private WebElement ele2;
	
	
}
