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
		landingpage.enterProductNameInSearchBox(dataprop.getProperty("validProduct"));
		//driver.findElement(By.xpath("//input[@name = 'search']")).sendKeys(dataprop.getProperty("validProduct"));
		landingpage.clickOnSearchButton();
		//driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
		searchpage = new SearchPage(driver);
		Assert.assertTrue(searchpage.displayStatusValidProduct());
		//Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());
	}
	
	@Test(priority=2)
	public void verifySearchWithInvalidProduct() {
		landingpage = new LandingPage(driver);
		landingpage.enterProductNameInSearchBox(dataprop.getProperty("invalidProduct"));
		//driver.findElement(By.xpath("//input[@name = 'search']")).sendKeys(dataprop.getProperty("invalidProduct"));
		landingpage.clickOnSearchButton();
		//driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
		searchpage = new SearchPage(driver);
		Assert.assertTrue(searchpage.displayStatusInvalidOrNoProduct());
		//Assert.assertTrue(driver.findElement(By.xpath("//p[text() = 'There is no product that matches the search criteria.']")).isDisplayed());
	}
	
	@Test(priority=3)
	public void verifySearchWithNoProduct() {
		landingpage = new LandingPage(driver);
		landingpage.clickOnSearchButton();
		//driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
		searchpage = new SearchPage(driver);
		Assert.assertTrue(searchpage.displayStatusInvalidOrNoProduct());
		//Assert.assertTrue(driver.findElement(By.xpath("//p[text() = 'There is no product that matches the search criteria.']")).isDisplayed());
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
