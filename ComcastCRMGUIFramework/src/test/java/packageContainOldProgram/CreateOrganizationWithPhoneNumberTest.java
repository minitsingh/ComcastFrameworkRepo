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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateOrganizationWithPhoneNumberTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException {
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new  JavaUtility();
		
		
		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password"); 
		
		String orgName = eLib.getDataFromExcel("org", 7, 2)+jLib.getRandomNumber();
		String phoneNumber=eLib.getDataFromExcel("org", 7, 3);

		
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
		driver.findElement(By.id("phone")).sendKeys(phoneNumber);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		
		//Validation step to verify phone number with expected result
		
	    String actPhoneNumber=driver.findElement(By.xpath("//span[@id='dtlview_Phone']")).getText();	
	    if(actPhoneNumber.equals(phoneNumber))
	    {
	    	System.out.println(actPhoneNumber + " information is created==PASS");
	    }
	    else
	    {
	    	System.out.println(actPhoneNumber + " information is not craeted==FAIL");
	    }
		
		//step5:logout from the application
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.partialLinkText("Sign")).click();
		
		driver.quit();

	}

}
