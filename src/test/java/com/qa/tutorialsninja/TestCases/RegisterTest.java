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
		landingpage.clickOnMyAccount();
		//driver.findElement(By.linkText("My Account")).click();
		landingpage.selectRegisterOption();
		//driver.findElement(By.linkText("Register")).click();	
	}
	
	@Test(priority=1)
	public void verifyRegisterWithMandatoryDetails() {
		registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataprop.getProperty("firstname"));
		//driver.findElement(By.id("input-firstname")).sendKeys(dataprop.getProperty("firstname"));
		registerpage.enterLastName(dataprop.getProperty("lastname"));
		//driver.findElement(By.id("input-lastname")).sendKeys(dataprop.getProperty("lastname"));
		registerpage.enterEmail(Util.emailWithDateTimeStamp());
		//driver.findElement(By.id("input-email")).sendKeys(Util.emailWithDateTimeStamp());
		registerpage.enterTelephone(dataprop.getProperty("telephone"));
		//driver.findElement(By.id("input-telephone")).sendKeys(dataprop.getProperty("telephone"));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		//driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		//driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		registerpage.checkPrivacyPolicy();
		//driver.findElement(By.xpath("//input[@name = 'agree' and @value = '1']")).click();
		registerpage.clickOnContinueButton();
		//driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		accountsuccesspage = new AccountSuccessPage(driver);
		Assert.assertTrue(accountsuccesspage.displayStatusOfAccountCreatedSuccessfullyMessage());
		//Assert.assertTrue(driver.findElement(By.xpath("//p[text() = 'Congratulations! Your new account has been successfully created!']")).isDisplayed());
		
	}
	
	@Test(priority=2)
	public void verifyRegisterWithAllDetails() {
		registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataprop.getProperty("firstname"));
		//driver.findElement(By.id("input-firstname")).sendKeys(dataprop.getProperty("firstname"));
		registerpage.enterLastName(dataprop.getProperty("lastname"));
		//driver.findElement(By.id("input-lastname")).sendKeys(dataprop.getProperty("lastname"));
		registerpage.enterEmail(Util.emailWithDateTimeStamp());
		//driver.findElement(By.id("input-email")).sendKeys(Util.emailWithDateTimeStamp());
		registerpage.enterTelephone(dataprop.getProperty("telephone"));
		//driver.findElement(By.id("input-telephone")).sendKeys(dataprop.getProperty("telephone"));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		//driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		//driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		registerpage.clickNewsletterRadioButton();
		//driver.findElement(By.cssSelector("label.radio-inline:nth-child(1) > input")).click();
		registerpage.checkPrivacyPolicy();
		//driver.findElement(By.xpath("//input[@name = 'agree' and @value = '1']")).click();
		registerpage.clickOnContinueButton();
		//driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		accountsuccesspage = new AccountSuccessPage(driver);
		Assert.assertTrue(accountsuccesspage.displayStatusOfAccountCreatedSuccessfullyMessage());
		//Assert.assertTrue(driver.findElement(By.xpath("//p[text() = 'Congratulations! Your new account has been successfully created!']")).isDisplayed());
		
	}
	
	@Test(priority=3)
	public void verifyRegisterWithExistingEmail() {
		
		registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataprop.getProperty("firstname"));
		//driver.findElement(By.id("input-firstname")).sendKeys(dataprop.getProperty("firstname"));
		registerpage.enterLastName(dataprop.getProperty("lastname"));
		//driver.findElement(By.id("input-lastname")).sendKeys(dataprop.getProperty("lastname"));
		registerpage.enterEmail(prop.getProperty("validEmail"));
		//driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		registerpage.enterTelephone(dataprop.getProperty("telephone"));
		//driver.findElement(By.id("input-telephone")).sendKeys(dataprop.getProperty("telephone"));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		//driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		//driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		registerpage.clickNewsletterRadioButton();
		//driver.findElement(By.cssSelector("label.radio-inline:nth-child(1) > input")).click();
		registerpage.checkPrivacyPolicy();
		//driver.findElement(By.xpath("//input[@name = 'agree' and @value = '1']")).click();
		registerpage.clickOnContinueButton();
		//driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualDuplicateEmailWarningMessage = registerpage.retrieveTextofDuplicateEmail();
		//String actualDuplicateEmailWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedDuplicateEmailWarningMessage = dataprop.getProperty("emailExistWarningMessage");
		Assert.assertTrue(actualDuplicateEmailWarningMessage.contains(expectedDuplicateEmailWarningMessage));	
		
	}
	
	@Test(priority=4)
	public void verifyRegisterWithNoDetails() {
		registerpage = new RegisterPage(driver);
		registerpage.clickOnContinueButton();
		//driver.findElement(By.cssSelector("input.btn.btn-primary")).click();	
		
		String actualPrivacyPolicyWarningMessage = registerpage.retrieveTextofPrivacyPolicyWarningMessage();
		//String actualPrivacyPolicyWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedPrivacyPolicyWarningMessage = dataprop.getProperty("policyWarningMessage");
		Assert.assertTrue(actualPrivacyPolicyWarningMessage.contains(expectedPrivacyPolicyWarningMessage));	
		
		String actualFirstNameWarningMessage = registerpage.retrievefirstNameWarningMessage();
		//String actualFirstNameWarningMessage = driver.findElement(By.xpath("//div[text() = 'First Name must be between 1 and 32 characters!']")).getText();
		String expectedFirstNameWarningMessage = dataprop.getProperty("firstNameWarningMessage");
		Assert.assertTrue(actualFirstNameWarningMessage.contains(expectedFirstNameWarningMessage));	
		
		
		String actualLastNameWarningMessage = registerpage.retrievelastNameWarningMessage();
		//String actualLastNameWarningMessage = driver.findElement(By.xpath("//div[text() = 'Last Name must be between 1 and 32 characters!']")).getText();
		String expectedLastNameWarningMessage = dataprop.getProperty("lastNameWarningMessage");
		Assert.assertTrue(actualLastNameWarningMessage.contains(expectedLastNameWarningMessage));	
		
		
		String actualEmailWarningMessage = registerpage.retrieveEmailWarningMessage();
		//String actualEmailWarningMessage = driver.findElement(By.xpath("//div[text() = 'E-Mail Address does not appear to be valid!']")).getText();
		String expectedEmailWarningMessage = dataprop.getProperty("emailWarningMessage");
		Assert.assertTrue(actualEmailWarningMessage.contains(expectedEmailWarningMessage));
		
		String actualTelephoneWarningMessage = registerpage.retrieveTelephoneWarningMessage();
		//String actualTelephoneWarningMessage = driver.findElement(By.xpath("//div[text() = 'Telephone must be between 3 and 32 characters!']")).getText();
		String expectedTelephoneWarningMessage = dataprop.getProperty("telephoneWarningMessage");
		Assert.assertTrue(actualTelephoneWarningMessage.contains(expectedTelephoneWarningMessage));
		
		String actualPasswordWarningMessage = registerpage.retrievePasswordWarningMessage();
		//String actualPasswordWarningMessage = driver.findElement(By.xpath("//div[text() = 'Password must be between 4 and 20 characters!']")).getText();
		String expectedPasswordWarningMessage = dataprop.getProperty("passwordWarningMessage");
		Assert.assertTrue(actualPasswordWarningMessage.contains(expectedPasswordWarningMessage));
		
	}
	
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	

}
