package com.comcast.crm.orgtest;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.listenerUtility.ListernerImpClass;
import com.comcast.crm.objectrepositoyUtility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoyUtility.HomePage;
import com.comcast.crm.objectrepositoyUtility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoyUtility.OrganizationPage;
@Listeners(com.comcast.crm.listenerUtility.ListernerImpClass.class)
public class CreateOrganizationTest extends BaseClass {

	@Test(groups = "smokeTest")
	public void createOrganizationTest() throws EncryptedDocumentException, IOException {

		UtilityClassObject.getTest().log(Status.INFO, "read data from excel");
		// read TestScript data from excel

		String orgName = eLib.getDataFromExcel("org", 7, 2) + jLib.getRandomNumber();

		// step1: login to app
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		// driver.get(URL);

		// navigate to organization module
		UtilityClassObject.getTest().log(Status.INFO, "navigate to Organization page");
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// step3:click on "create organization" module

		UtilityClassObject.getTest().log(Status.INFO, "Navigate to create Org page");
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();

		// step4:enter all the details and create new organization
		UtilityClassObject.getTest().log(Status.INFO, "Create a new Organization");
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName);

		UtilityClassObject.getTest().log(Status.INFO, orgName + " created Sucessfully");

		// Verify header message expected result

		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actualorgName = oip.getHeaderMsg().getText();

		//		Assert.assertEquals(true, actualorgName.contains(orgName));

		if (actualorgName.contains(orgName)) {
			System.out.println(orgName + "name is verified===pass");
		}

		else
			System.out.println(orgName + "name is not verified==fail");

	}

	@Test(groups = "regressionTest")
	public void createOrgWithIndustryAndTypeTest() throws EncryptedDocumentException, IOException {
		// read common data from property file

		String orgName = eLib.getDataFromExcel("org", 4, 2) + jLib.getRandomNumber();
		String industry = eLib.getDataFromExcel("org", 4, 3);
		String type = eLib.getDataFromExcel("org", 4, 4);

		// step2:navigation to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// step3:click on "create organization" module
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();

		// step4:enter all the details and create new organization
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName, industry, type);

		// Verify the industry name

		String actIndustries = driver.findElement(By.xpath("//span[@id='dtlview_Industry']")).getText();
		if (actIndustries.equals(industry)) {
			System.out.println(industry + " information is created==PASS");
		} else {
			System.out.println(industry + " information is not craeted==FAIL");
		}

		// Verify the type of Industry

		String actType = driver.findElement(By.xpath("//span[@id='dtlview_Type']")).getText();
		if (actType.equals(type)) {
			System.out.println(type + " information is created==PASS");
		} else {
			System.out.println(type + " information is not craeted==FAIL");
		}

	}

	@Test(groups = "regressionTest")
	public void createOrgWithPhoneNumTest() throws EncryptedDocumentException, IOException {

		String orgName = eLib.getDataFromExcel("org", 7, 2) + jLib.getRandomNumber();
		String phoneNumber = eLib.getDataFromExcel("org", 7, 3);

		// step2:navigation to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// step3:click on "create organization" module
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();

		// step4:enter all the details and create new organization
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrgWithPhone(orgName, phoneNumber);

		// Validation step to verify phone number with expected result

		String actPhoneNumber = driver.findElement(By.xpath("//span[@id='dtlview_Phone']")).getText();
		if (actPhoneNumber.equals(phoneNumber)) {
			System.out.println(actPhoneNumber + " information is created==PASS");
		} else {
			System.out.println(actPhoneNumber + " information is not craeted==FAIL");
		}

	}
}
