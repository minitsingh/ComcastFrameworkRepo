package packageContainOldProgram;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoyUtility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoyUtility.HomePage;
import com.comcast.crm.objectrepositoyUtility.LoginPage;
import com.comcast.crm.objectrepositoyUtility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoyUtility.OrganizationPage;

public class DeleteOrgTest {
	
	// in this test script we are trying to delete the dynamic element using pom
	
public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException {
		
		//read commom data from property file
		
		/*
		 * Create Object
		 */
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new  JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		
						
		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		//String USERNAME = fLib.getDataFromPropertiesFile("username");
		//String PASSWORD = fLib.getDataFromPropertiesFile("password"); 
				

		String orgName=eLib.getDataFromExcel("org", 10, 2)+ jLib.getRandomNumber();
		WebDriver driver = null;
		
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
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		//driver.get(URL);
		
		LoginPage lp= new LoginPage(driver);
		
		lp.loginToApp(URL ,"admin", "admin");
		
		//step2:navigation to organization module
		
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click(); 	//used for single action getters/setters enough
		
		
		//step3:click on "create organization" module
		
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();
		
		
		//step4:enter all the details and create new organization
		
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName);
		
		
		
		//Verify header message expected result
		
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actualorgName = oip.getHeaderMsg().getText();
		
		if(actualorgName.contains(orgName))
		{
			System.out.println(orgName+"name is verified===pass");
		}
		
		else
			System.out.println(orgName+"name is not verified==fail");
		
		
		//go back to Organization page 
		 hp.getOrgLink().click();
		 
		// search for organisation 
		 op.getSearchEdit().sendKeys(orgName);
		 
		 Thread.sleep(2000);
		 wLib.select(op.getSearchDD(), "Organization Name");
		op.getSearchBtn().click();
		 
		 
		//in dynamic webtable select and delete org
		 driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();
		 driver.switchTo().alert().accept();
		
		//step5:logout from the application
		hp.logout();
		
		driver.quit();

	}


}
