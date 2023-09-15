package com.qa.tutorialsninja.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	public WebDriver driver;
	
	@FindBy(id = "input-email")
	private WebElement emailAddressTextBox;
	
	@FindBy(id = "input-password")
	private WebElement passwordTextBox;
	
	@FindBy(css = "input.btn.btn-primary")
	private WebElement loginButton;
	
	@FindBy(xpath = "//div[contains(@class, 'alert-dismissible')]")
	private WebElement loginWarningMessage;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterEmail(String emailText) {
		emailAddressTextBox.sendKeys(emailText);
	}

	public void enterPassword(String passwordText) {
		passwordTextBox.sendKeys(passwordText);
	}
	
	public void clickOnLoginButton() {
		loginButton.click();
	}
	
	public String retrieveTextOfLoginWarningMessage() {
		String warningMessage = loginWarningMessage.getText();
		return warningMessage;
	}
}
