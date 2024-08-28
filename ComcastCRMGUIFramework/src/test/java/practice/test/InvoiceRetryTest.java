package practice.test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;

@Listeners(com.comcast.crm.listenerUtility.ListernerImpClass.class)
public class InvoiceRetryTest extends BaseClass {
	@Test(retryAnalyzer = com.comcast.crm.listenerUtility.RetryListenerImp.class)
	public void activateSim()
	{
		System.out.println("execute InvoiceRetryTest");
		
		String actTitle = driver.getTitle();
		Assert.assertEquals(actTitle, "Login");
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");

	}
}
