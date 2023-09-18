package com.qa.tutorialsninja.TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.tutorialsninja.Pages.LandingPage;
import com.qa.tutorialsninja.Pages.SearchPage;
import com.qa.tutorialsninja.TestBase.TestBase;

public class SearchProductTest extends TestBase{
	
	public SearchProductTest() throws Exception {
		super();
	}

	public WebDriver driver;
	public LandingPage landingpage;
	public SearchPage searchpage;
	
	@BeforeMethod
	public void searchProductSetup() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
	}
	
	@Test(priority=1)
	public void verifySearchWithValidProduct() {
		landingpage = new LandingPage(driver);
		searchpage = landingpage.navigateToSearchPage(dataprop.getProperty("validProduct"));
		Assert.assertTrue(searchpage.displayStatusValidProduct());
		
	}
	
	@Test(priority=2)
	public void verifySearchWithInvalidProduct() {
		landingpage = new LandingPage(driver);
		searchpage = landingpage.navigateToSearchPage(dataprop.getProperty("invalidProduct"));
		//Assert.assertTrue(searchpage.displayStatusInvalidOrNoProduct());
		Assert.assertFalse(searchpage.displayStatusInvalidOrNoProduct());
	}
	
	@Test(priority=3, dependsOnMethods = "verifySearchWithInvalidProduct")
	public void verifySearchWithNoProduct() {
		landingpage = new LandingPage(driver);
		searchpage = landingpage.clickOnSearchButton();
	    Assert.assertTrue(searchpage.displayStatusInvalidOrNoProduct());
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
