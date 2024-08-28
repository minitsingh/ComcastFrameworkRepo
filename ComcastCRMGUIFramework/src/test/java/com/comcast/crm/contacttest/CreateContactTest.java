package com.comcast.crm.contacttest;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoyUtility.ContactPage;
import com.comcast.crm.objectrepositoyUtility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoyUtility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoyUtility.HomePage;
import com.comcast.crm.objectrepositoyUtility.OrganizationPage;

/**
 * 
 * @author MINIT SINGH
 * 
 */
public class CreateContactTest extends BaseClass {

	@Test(groups = { "smokeTest" })
	public void createContactTest() throws IOException {

		/* Read TestScript data from excel utility */
		String lastName = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();

		// step2:navigation to contacts module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		// step3:click on "create contact" module
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewConactBtn().click();

		// step4:enter all the details and create new organization

		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.createContact(lastName);

		// Verify header message with expected result

		String actHeader = cp.getHeaderMsg().getText();
		boolean status = actHeader.contains(lastName);
		Assert.assertEquals(status, true);

		String actLastName = driver.findElement(By.xpath("//span[@id='dtlview_Last Name']")).getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actLastName, lastName);

	}

	@Test(groups = "regressionTest")
	public void createContactWithSupportDateTest() throws EncryptedDocumentException, IOException {

		// read TestScript data from excel file
		String lastName = eLib.getDataFromExcel("contact", 4, 2) + jLib.getRandomNumber();

		// step2:navigation to contacts module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		// step3:click on "create contact" module
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewConactBtn().click();

		// step4:enter all the details and create new Contact

		String startDate = jLib.getSystemDateYYYYDDMM();
		String endDate = jLib.getRequiredDateYYYYDDMM(30);

		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.createContactWithSupportDate(lastName, startDate, endDate);

		// Validation for the Start date Header
		String actStartDate = driver.findElement(By.xpath("//span[@id='dtlview_Support Start Date']")).getText();
		if (actStartDate.contains(startDate)) {
			System.out.println(startDate + " is created=pass");
		} else {
			System.out.println(startDate + " is not created=fail");
		}

		// validation of End Date Header
		String actEndDate = driver.findElement(By.xpath("//span[@id='dtlview_Support End Date']")).getText();
		if (actEndDate.contains(endDate)) {
			System.out.println(endDate + " is created=pass");
		} else {
			System.out.println(endDate + " is not created=fail");
		}

	}

	@Test(groups = "regressionTest")
	public void createContactWithOrgTest() throws EncryptedDocumentException, IOException, InterruptedException {

		String orgName = eLib.getDataFromExcel("contact", 7, 2) + jLib.getRandomNumber();
		String lastName = eLib.getDataFromExcel("contact", 7, 3) + jLib.getRandomNumber();

		// step2:navigation to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// step3:click on "create organization" module
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();

		// step4:enter all the details and create new organization
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName);

		// Step 5 : Validation to verify the header message as expected result
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(orgName)) {
			System.out.println(orgName + "is created=pass");
		} else {
			System.out.println(orgName + "is not created=fail");
		}

		// Navigate to contact module

		// Step 6 : :navigation to contacts module
		hp.getContactLink().click();

		// Step 7 : click on "create contact" module
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewConactBtn().click();

		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.createContactwithOrg(lastName, orgName);

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

	}

}
