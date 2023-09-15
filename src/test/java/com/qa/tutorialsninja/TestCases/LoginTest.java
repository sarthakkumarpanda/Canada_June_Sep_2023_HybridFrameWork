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
		landingpage.clickOnMyAccount();
		//driver.findElement(By.linkText("My Account")).click();
		landingpage.selectLoginOption();
		//driver.findElement(By.linkText("Login")).click();	 --> this will re-direct to LoginPage
	}

	@Test(priority=1, dataProvider = "TN", dataProviderClass = ExcelCode.class)
	public void verifyLoginWithValidCredentials(String email, String password) {
		loginpage = new LoginPage(driver);
		loginpage.enterEmail(email);
		//driver.findElement(By.id("input-email")).sendKeys(email);
		loginpage.enterPassword(password);
		//driver.findElement(By.id("input-password")).sendKeys(password);
		loginpage.clickOnLoginButton();
		//driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		accountpage = new AccountPage(driver);
		Assert.assertTrue(accountpage.validateEditAccountInfoLinkDisplayStatus());
		//Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
	}
	
	@Test(priority=2)
	public void verifyLoginWithValidEmailInvalidPassword() {
		loginpage = new LoginPage(driver);
		loginpage.enterEmail(prop.getProperty("validEmail"));
		//driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		loginpage.enterPassword(dataprop.getProperty("invalidPassword"));
		//driver.findElement(By.id("input-password")).sendKeys(dataprop.getProperty("invalidPassword"));
		loginpage.clickOnLoginButton();
		//driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = loginpage.retrieveTextOfLoginWarningMessage();
		// String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedWarningMessage = dataprop.getProperty("loginWarningMessage");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));	
	}
	
	@Test(priority=3)
	public void verifyLoginWithInvalidEmailValidPassword() {
		loginpage = new LoginPage(driver);
		loginpage.enterEmail(Util.emailWithDateTimeStamp());
		//driver.findElement(By.id("input-email")).sendKeys(Util.emailWithDateTimeStamp());
		loginpage.enterPassword(prop.getProperty("validPassword"));
		//driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		loginpage.clickOnLoginButton();
		//driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = loginpage.retrieveTextOfLoginWarningMessage();
		//String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedWarningMessage = dataprop.getProperty("loginWarningMessage");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
	}
	
	@Test(priority=4)
	public void verifyLoginWithInvalidCredentials() {
		loginpage = new LoginPage(driver);
		loginpage.enterEmail(Util.emailWithDateTimeStamp());
		//driver.findElement(By.id("input-email")).sendKeys(Util.emailWithDateTimeStamp());
		loginpage.enterPassword(dataprop.getProperty("invalidPassword"));
		//driver.findElement(By.id("input-password")).sendKeys(dataprop.getProperty("invalidPassword"));
		loginpage.clickOnLoginButton();
		//driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = loginpage.retrieveTextOfLoginWarningMessage();
		//String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedWarningMessage = dataprop.getProperty("loginWarningMessage");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
	}
	
	@Test(priority=5)
	public void verifyLoginWithNoCredentials() {
		loginpage = new LoginPage(driver);
		loginpage.clickOnLoginButton();
		//driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = loginpage.retrieveTextOfLoginWarningMessage();
		//String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedWarningMessage = dataprop.getProperty("loginWarningMessage");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));	
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
