package com.qa.tutorialsninja.TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.tutorialsninja.Pages.AccountPage;
import com.qa.tutorialsninja.Pages.LandingPage;
import com.qa.tutorialsninja.Pages.LoginPage;
import com.qa.tutorialsninja.TestBase.TestBase;
import com.qa.tutorialsninja.TestData.ExcelCode;
import com.qa.tutorialsninja.Utilities.Util;

public class LoginTest extends TestBase{
	public LoginTest() throws Exception {
		super();
	}

	public WebDriver driver;
	public LandingPage landingpage;
	public LoginPage loginpage;
	public AccountPage accountpage;
	
	@BeforeMethod
	public void loginSetup() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		landingpage = new LandingPage(driver);
		loginpage = landingpage.navigateToLoginPage();
	}

	@Test(priority=1, dataProvider = "TN", dataProviderClass = ExcelCode.class, enabled=false)
	public void verifyLoginWithValidCredentials(String email, String password) {
		accountpage = loginpage.navigateToAccountPage(email, password);
		Assert.assertTrue(accountpage.validateEditAccountInfoLinkDisplayStatus());	
	}
	
	@Test(priority=2, enabled = false)
	public void verifyLoginWithValidEmailInvalidPassword() {
		loginpage.navigateToAccountPage(prop.getProperty("validEmail"), dataprop.getProperty("invalidPassword"));
		Assert.assertTrue(loginpage.retrieveTextOfLoginWarningMessage().contains(dataprop.getProperty("loginWarningMessage")));	
	}
	
	@Test(priority=3, enabled = false)
	public void verifyLoginWithInvalidEmailValidPassword() {	
		loginpage.navigateToAccountPage(Util.emailWithDateTimeStamp(), prop.getProperty("validPassword"));
		Assert.assertTrue(loginpage.retrieveTextOfLoginWarningMessage().contains(dataprop.getProperty("loginWarningMessage")));
	}
	
	@Test(priority=4, enabled = false)
	public void verifyLoginWithInvalidCredentials() {
		loginpage.navigateToAccountPage(Util.emailWithDateTimeStamp(), dataprop.getProperty("invalidPassword"));
		Assert.assertTrue(loginpage.retrieveTextOfLoginWarningMessage().contains(dataprop.getProperty("loginWarningMessage")));
	}
	
	@Test(priority=5)
	public void verifyLoginWithNoCredentials() {
		loginpage.clickOnLoginButton();
		Assert.assertTrue(loginpage.retrieveTextOfLoginWarningMessage().contains(dataprop.getProperty("loginWarningMessage")));	
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
