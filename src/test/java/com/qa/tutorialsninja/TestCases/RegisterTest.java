package com.qa.tutorialsninja.TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.tutorialsninja.Pages.AccountSuccessPage;
import com.qa.tutorialsninja.Pages.LandingPage;
import com.qa.tutorialsninja.Pages.RegisterPage;
import com.qa.tutorialsninja.TestBase.TestBase;
import com.qa.tutorialsninja.Utilities.Util;

public class RegisterTest extends TestBase{
	
	public RegisterTest() throws Exception {
		super();
	}

	public WebDriver driver;
	public LandingPage landingpage;
	public RegisterPage registerpage;
	public AccountSuccessPage accountsuccesspage;
	
	@BeforeMethod
	public void registerSetup() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		landingpage = new LandingPage(driver);
		registerpage = landingpage.navigateToRegisterPage();
	}
	
	@Test(priority=1)
	public void verifyRegisterWithMandatoryDetails() {
		accountsuccesspage = registerpage.RegisterPageMandatoryFields(dataprop.getProperty("firstname"), dataprop.getProperty("lastname"), Util.emailWithDateTimeStamp(), 
				dataprop.getProperty("telephone"),prop.getProperty("validPassword"), prop.getProperty("validPassword"));
		
		Assert.assertTrue(accountsuccesspage.displayStatusOfAccountCreatedSuccessfullyMessage());	
	}
	
	@Test(priority=2, enabled = false)
	public void verifyRegisterWithAllDetails() {
	accountsuccesspage = registerpage.RegisterPageAllFields(dataprop.getProperty("firstname"), dataprop.getProperty("lastname"), 
				Util.emailWithDateTimeStamp(), dataprop.getProperty("telephone"), prop.getProperty("validPassword"),
				prop.getProperty("validPassword"));
	
		Assert.assertTrue(accountsuccesspage.displayStatusOfAccountCreatedSuccessfullyMessage());	
	}
	
	@Test(priority=3, enabled = false)
	public void verifyRegisterWithExistingEmail() {
		registerpage.RegisterPageAllFields(dataprop.getProperty("firstname"), dataprop.getProperty("lastname"), 
				prop.getProperty("validEmail"), dataprop.getProperty("telephone"), prop.getProperty("validPassword"),
				prop.getProperty("validPassword"));
		
		Assert.assertTrue(registerpage.retrieveTextofDuplicateEmail().contains(dataprop.getProperty("emailExistWarningMessage")));	
		
	}
	
	@Test(priority=4, enabled = false)
	public void verifyRegisterWithNoDetails() {
	
		registerpage.clickOnContinueButton();
		Assert.assertTrue(registerpage.retrieveAllWarningMessages(dataprop.getProperty("policyWarningMessage"), dataprop.getProperty("firstNameWarningMessage"),
				dataprop.getProperty("lastNameWarningMessage"), dataprop.getProperty("emailWarningMessage"), dataprop.getProperty("telephoneWarningMessage"), 
				dataprop.getProperty("passwordWarningMessage")));
		
		//Assert.assertTrue(registerpage.retrieveTextofPrivacyPolicyWarningMessage().contains(dataprop.getProperty("policyWarningMessage")));	
		//Assert.assertTrue(registerpage.retrievefirstNameWarningMessage().contains(dataprop.getProperty("firstNameWarningMessage")));	
		//Assert.assertTrue(registerpage.retrievelastNameWarningMessage().contains(dataprop.getProperty("lastNameWarningMessage")));	
		//Assert.assertTrue(registerpage.retrieveEmailWarningMessage().contains(dataprop.getProperty("emailWarningMessage")));
		//Assert.assertTrue(registerpage.retrieveTelephoneWarningMessage().contains(dataprop.getProperty("telephoneWarningMessage")));
		//Assert.assertTrue(registerpage.retrievePasswordWarningMessage().contains(dataprop.getProperty("passwordWarningMessage")));
		
	}
	
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	

}
