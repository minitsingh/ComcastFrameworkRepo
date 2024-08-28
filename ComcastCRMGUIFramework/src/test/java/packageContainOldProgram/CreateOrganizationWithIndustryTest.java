package packageContainOldProgram;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoyUtility.CreatingNewOrganizationPage;

public class CreateOrganizationWithIndustryTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException {
		
		//read common data from property file
		
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new  JavaUtility();
		
		
		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password"); 
		
		String orgName=eLib.getDataFromExcel("org", 4, 2)+jLib.getRandomNumber();
		String industry = eLib.getDataFromExcel("org", 4, 3);
		String type = eLib.getDataFromExcel("org", 4, 4);
				
		WebDriver driver=null;
				
		if(BROWSER.equals("chrome"))
			driver=new ChromeDriver();
		else if (BROWSER.equals("firefox"))
			driver = new FirefoxDriver();
		else if(BROWSER.equals("edge"))
			driver= new EdgeDriver();
		else
			driver=new ChromeDriver();
				
				
		System.out.println(BROWSER);
				
		//step1: login to app
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);
				
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
				
		//step2:navigation to organization module
		driver.findElement(By.linkText("Organizations")).click();
				
		//step3:click on "create organization" module
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
				
		//step4:enter all the details and create new organization
		
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		WebElement indus = driver.findElement(By.name("industry"));
		Select select = new Select(indus);
		select.selectByVisibleText(industry);
				
	    WebElement acc = driver.findElement(By.name("accounttype"));
		Select select1 = new Select(acc);
		select1.selectByVisibleText(type);
				
				
				
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
		//Verify the industry name
				
	    String actIndustries=driver.findElement(By.xpath("//span[@id='dtlview_Industry']")).getText();	
	    if(actIndustries.equals(industry))
	    {
		  	System.out.println(industry + " information is created==PASS");
	    }
	    else
	    {
		 	System.out.println(industry + " information is not craeted==FAIL");
	    }
				
        //Verify the type of Industry
				
	    String actType=driver.findElement(By.xpath("//span[@id='dtlview_Type']")).getText();	
	    if(actType.equals(type))
	    {
	    	System.out.println(type + " information is created==PASS");
	    }
	    else
	    {
	    	System.out.println(type + " information is not craeted==FAIL");
	    }

	    //step5:logout from the application
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.partialLinkText("Sign Out")).click();
				
		driver.quit();

	}

}
