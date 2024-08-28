package com.comcast.crm.basetest;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoyUtility.HomePage;
import com.comcast.crm.objectrepositoyUtility.LoginPage;

public class BaseClass {

	/**
	 * Object creation
	 */

	public DataBaseUtility dbLib = new DataBaseUtility();
	public FileUtility fLib = new FileUtility();
	public ExcelUtility eLib = new ExcelUtility();
	public JavaUtility jLib = new JavaUtility();
	public WebDriver driver = null;
	public static WebDriver sdriver = null;

	@BeforeSuite(groups = { "smokeTest", "regressionTest" })
	public void configBS() throws SQLException {
		System.out.println("===Connect to DB,  Report Config=====");
		dbLib.getDbconnection();

	}

	// @Parameters("BROWSER")
	@BeforeClass(groups = { "smokeTest", "regressionTest" })
	public void configBc() throws IOException {
		System.out.println("===Launch the browser===");
		String BROWSER = fLib.getDataFromPropertiesFile("browser");

		// above data fetching from prop file is commented to
		// Receive data from xml file for cross browser testing
		// method is also taking an string argument
		// to return back to old ..uncomment and remove the parameter

		// String BROWSER=browser;

		if (BROWSER.equals("chrome"))
			driver = new ChromeDriver();
		else if (BROWSER.equals("firefox"))
			driver = new FirefoxDriver();
		else if (BROWSER.equals("edge"))
			driver = new EdgeDriver();
		else {
			driver = new ChromeDriver();
		}
		sdriver = driver;
		UtilityClassObject.setDriver(driver);

	}

	@BeforeMethod(groups = { "smokeTest", "regressionTest" })
	public void configBM() throws IOException {
		System.out.println("===Login===");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");

		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(URL, USERNAME, PASSWORD);

	}

	@AfterMethod(groups = { "smokeTest", "regressionTest" })
	public void configAm()

	{
		System.out.println("===Logout===");
		HomePage hp = new HomePage(driver);
		hp.logout();

	}

	@AfterClass(groups = { "smokeTest", "regressionTest" })
	public void config() {
		System.out.println("====Close the Browser ======");
		driver.quit();
	}

	@AfterSuite(groups = { "smokeTest", "regressionTest" })
	public void configAS() throws SQLException {
		System.out.println("=== Close DB,  Report BackUP=====");
		dbLib.closeDbconnection();

		// Backup report

	}

}
