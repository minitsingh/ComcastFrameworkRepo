package practice.test;
/**
 * test class for Contact module 
 * @author MINIT SINGH 
 * 
 */

import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoyUtility.LoginPage;

public class SearchContactTest extends BaseClass {

	/**
	 *Scenario :  Login()==> navigateContact==>createcontact()==verify
	 */
	
	@Test
	public void searchcontactTest()
	{
		/* Step 1 : Login to app */
		
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp("url", "username", "password");
	}
}
