package packageContainOldProgram;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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

public class CreateContactWithSupportDateTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException {
		
		
		
	FileUtility fLib = new FileUtility();
	ExcelUtility eLib = new ExcelUtility();
	JavaUtility jLib = new  JavaUtility();
	
						
	String BROWSER = fLib.getDataFromPropertiesFile("browser");		
	String URL = fLib.getDataFromPropertiesFile("url");
	String USERNAME = fLib.getDataFromPropertiesFile("username");
	String PASSWORD = fLib.getDataFromPropertiesFile("password");		
	String lastName = eLib.getDataFromExcel("contact", 4, 2)+jLib.getRandomNumber();
				
		WebDriver driver=null;
			
		if(BROWSER.equals("chrome"))
			driver=new ChromeDriver();
		else if (BROWSER.equals("firefox"))
			driver = new FirefoxDriver();
		else if(BROWSER.equals("edge"))
			driver= new EdgeDriver();
		else
			driver=new ChromeDriver();
				
				
		//step1: login to app
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);
				
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
				
		//step2:navigation to contacts module
		driver.findElement(By.linkText("Contacts")).click();
				
		//step3:click on "create organization" module
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				
		//step4:enter all the details and create new organization
				
		String startDate = jLib.getSystemDateYYYYDDMM();
		String endDate = jLib.getRequiredDateYYYYDDMM(30);
		
		System.out.println(startDate);
		System.out.println(endDate);
				
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startDate);
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
		//Validation for the Start date Header
		String actStartDate = driver.findElement(By.xpath("//span[@id='dtlview_Support Start Date']")).getText();
		if(actStartDate.contains(startDate))
		{
			System.out.println(startDate + " is created=pass");
		}
		else
		{
			System.out.println(startDate + " is not created=fail" );
		}
				
		//validation of End Date Header
		String actEndDate = driver.findElement(By.xpath("//span[@id='dtlview_Support End Date']")).getText();
		if(actEndDate.contains(endDate))
		{
			System.out.println(endDate + " is created=pass");
		}
		else
		{
			System.out.println(endDate + " is not created=fail" );
		}
				
				
		//step5:logout from the application
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.partialLinkText("Sign")).click();
				
		driver.quit();


	}

}
