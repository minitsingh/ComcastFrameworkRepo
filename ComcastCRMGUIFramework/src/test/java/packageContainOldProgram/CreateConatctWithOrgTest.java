package packageContainOldProgram;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateConatctWithOrgTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		//Integration test case
		//precondition--atleast one organization module should be created
		
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();

		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");

		String orgName = eLib.getDataFromExcel("contact", 7, 2) + jLib.getRandomNumber();
		String lastName = eLib.getDataFromExcel("contact", 7, 3);

		WebDriver driver = null;

		if (BROWSER.equals("chrome"))
			driver = new ChromeDriver();
		else if (BROWSER.equals("firefox"))
			driver = new FirefoxDriver();
		else if (BROWSER.equals("edge"))
			driver = new EdgeDriver();
		else
			driver = new ChromeDriver();

		System.out.println(BROWSER);

		// step1: login to app

		// hardcoded wait
		wLib.waitForPageToLoad(driver);
		driver.get(URL);

		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		// step2:navigation to organization module
		driver.findElement(By.linkText("Organizations")).click();

		// step3:click on "create organization" module
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		// step4:enter all the details and create new organization
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Step 5 : Validation to verify the header message as expected result
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(orgName)) {
			System.out.println(orgName + "is created=pass");
		} else {
			System.out.println(orgName + "is not created=fail");
		}

		// Navigate to contact module

		// Step 6 : :navigation to contacts module
		driver.findElement(By.linkText("Contacts")).click();

		// Step 7 : click on "create contact" module
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// Step 8 : enter all the details and create new contact
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();

		// String parentWindowID = driver.getWindowHandle();
		// solution by action sir ....var name was incorrect

		// Switch to child window(lookup window)

		wLib.switchToTabOnTitle(driver, "module=Accounts");

		driver.findElement(By.name("search_text")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();

		// driver.switchTo().window(parentWindowID);

		// switch to parent window(main window--- create contact module)

		wLib.switchToTabOnTitle(driver, "Contacts&action");

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Verify header message expected result

		headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(lastName)) {
			System.out.println(lastName + "is created=pass");
		} else {
			System.out.println(lastName + "is not created=fail");
		}

		// verify Header orgName info expected result

		String actOrgName = driver.findElement(By.linkText(orgName)).getText();
		if (actOrgName.equals(orgName)) {
			System.out.println(orgName + "information is created==PASS");
		} else {
			System.out.println(orgName + "information is not craeted==FAIL");
		}

		// here this was used as deepak sir unable to take correct xpath so he used trim
		// the text n
//
		// String actOrgName=driver.findElement(By.linkText(orgName)).getText();
		// if(actOrgName.trim().equals(orgName))
//	    {
//	    	System.out.println(orgName + "information is created==PASS");
//	    }
//	    else
//	    {
//	    	System.out.println(orgName + "information is not created==FAIL");
//	    }
//	    

		// step5:logout from the application
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.partialLinkText("Sign")).click();

		driver.quit();


	}

}
