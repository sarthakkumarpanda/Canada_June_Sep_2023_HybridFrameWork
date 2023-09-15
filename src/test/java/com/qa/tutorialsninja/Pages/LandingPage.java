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
	
	public void selectLoginOption() {
		LoginOption.click();
	}
	
	public void selectRegisterOption() {
		RegisterOption.click();
	}
	
	public void enterProductNameInSearchBox(String validProductText) {
		searchBox.sendKeys(validProductText);
	}
	
	public void clickOnSearchButton() {
		searchButton.click();
	}

}
