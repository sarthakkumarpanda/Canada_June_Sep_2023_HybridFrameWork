package com.qa.tutorialsninja.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	
	public WebDriver driver;
	
	//Define what all Objects are there in this Landing Page
	//I have to initialize Objects
	//Perform actions on those objects
	
	@FindBy(linkText = "My Account")
	private WebElement MyAccountDropDown;
	
	@FindBy(linkText = "Login")
	private WebElement LoginOption;
	
	@FindBy(linkText = "Register")
	private WebElement RegisterOption;
	
	@FindBy(xpath = "//input[@name = 'search']")
	private WebElement searchBox;
	
	@FindBy(css = "button.btn.btn-default.btn-lg")
	private WebElement searchButton;
	
	public LandingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		//PageFactory.initElements(driver, LandingPage.class);	
	}
	
	public void clickOnMyAccount() {
		MyAccountDropDown.click();
	}
	
	public LoginPage selectLoginOption() {
		LoginOption.click();
		return new LoginPage(driver);
	}
	
	public LoginPage navigateToLoginPage() {
		MyAccountDropDown.click();
		LoginOption.click();
		return new LoginPage(driver);
	}
	
	public RegisterPage selectRegisterOption() {
		RegisterOption.click();
		return new RegisterPage(driver);
	}
	
	public RegisterPage navigateToRegisterPage() {
		MyAccountDropDown.click();
		RegisterOption.click();
		return new RegisterPage(driver);
	}
	

	
	public void enterProductNameInSearchBox(String validProductText) {
		searchBox.sendKeys(validProductText);
	}
	
	public SearchPage clickOnSearchButton() {
		searchButton.click();
		return new SearchPage(driver);
	}
	
	public SearchPage navigateToSearchPage(String validProductText) {
		searchBox.sendKeys(validProductText);
		searchButton.click();
		return new SearchPage(driver);
	}

}
