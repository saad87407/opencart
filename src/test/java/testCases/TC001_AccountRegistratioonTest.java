package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationpage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistratioonTest  extends BaseClass{
	
	
	@Test(groups= {"Regression","Master"})
	public void verify_account_registration() 
	{
		logger.info("*****starting TC_AccountRegistrationpage******");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyaccount();
		logger.info("clicked on MyAccount link");
		
		hp.clickRegister();
		logger.info("clicked on Register link");
		
		AccountRegistrationpage regpage= new AccountRegistrationpage(driver);
		
		logger.info("Providing customer details.....");
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString()+"@gmail.com");
		regpage.setTelephone(randomNumber());
		
		String password=randomAlphaNumeric();
		
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("validating expected msg...");
		String confmsg=regpage.getConfirmationMsg();
		if(confmsg.equals("Your Account Has Been Created!"))
				{
					Assert.assertTrue(true);
				}
		else
		{
			logger.error("test failed");
			logger.debug("debug logs");
			Assert.assertTrue(false);
		}
		
	//	Assert.assertEquals(confmsg,"Your Account Has Been Created!");
		}
		catch (Exception e) {
			Assert.fail();
		}
		
		logger.info("*****Finished TC_AccountRegistrationpage******");
		
	}
	
}
