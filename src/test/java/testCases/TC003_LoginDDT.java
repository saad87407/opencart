package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass{
	
	//gettin dataprooovider from diff class
	
	@Test(dataProvider="LoginData",dataProviderClass = DataProviders.class)
	public void verify_loginddt(String email,String pwd,String exp)
	{
			
		logger.info("***starting  TC003Logintestddt ******");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyaccount();
		hp.clickLogin();
		
		Thread.sleep(2000);
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassworrd(pwd);
		lp.clickLogin();
		
		Thread.sleep(2000);
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExists();
		
		if(exp.equalsIgnoreCase("valid"))
		{
			if(targetPage==true)
			{
			
				macc.clickLogout();
				Assert.assertTrue(true);
			}
			else 
			{
				Assert.assertTrue(false);
			}
		}
		if(exp.equalsIgnoreCase("invalid"))
		{
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		
		
		
		Assert.assertEquals(targetPage, true,"login failed");
		}
		catch (Exception e) {
			Assert.fail();
		}
	}

}
