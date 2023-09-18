package com.qa.tutorialsninja.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	public WebDriver driver;
	
	@FindBy(id = "input-firstname")
	private WebElement firstNameTextBox;
	
	@FindBy(id = "input-lastname")
	private WebElement lastNameTextBox;
	
	@FindBy(id = "input-email")
	private WebElement emailTextBox;
	
	@FindBy(id = "input-telephone")
	private WebElement telephoneTextBox;
	
	@FindBy(id = "input-password")
	private WebElement passwordTextBox;
	
	@FindBy(id = "input-confirm")
	private WebElement confirmPasswordTextBox;
	
	@FindBy(css = "label.radio-inline:nth-child(1) > input")
	private WebElement newsletterRadiobutton;
	
	@FindBy(xpath = "//input[@name = 'agree' and @value = '1']")
	private WebElement privacyPolicyCheckbox;
	
	@FindBy(css = "input.btn.btn-primary")
	private WebElement continueButton;
	
	@FindBy(xpath = "//div[contains(@class, 'alert-dismissible')]")
	private WebElement duplicateEmailWarningMessage;
	
	@FindBy(xpath = "//div[contains(@class, 'alert-dismissible')]")
	private WebElement privacyPolicyWarningMessage;
	
	@FindBy(xpath = "//div[text() = 'First Name must be between 1 and 32 characters!']")
	private WebElement firstNameWarningMessage;
	
	@FindBy(xpath = "//div[text() = 'Last Name must be between 1 and 32 characters!']")
	private WebElement lastNameWarningMessage;
	
	@FindBy(xpath = "//div[text() = 'E-Mail Address does not appear to be valid!']")
	private WebElement emailWarningMessage;
	
	@FindBy(xpath = "//div[text() = 'Telephone must be between 3 and 32 characters!']")
	private WebElement telephoneWarningMessage;
	
	@FindBy(xpath = "//div[text() = 'Password must be between 4 and 20 characters!']")
	private WebElement passwordWarningMessage;
	
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterFirstName(String firstNameText) {
		firstNameTextBox.sendKeys(firstNameText);
	}
	
	public void enterLastName(String lastNameText) {
		lastNameTextBox.sendKeys(lastNameText);
	}
	
	public void enterEmail(String emailText) {
		emailTextBox.sendKeys(emailText);
	}
	
	public void enterTelephone(String telephoneText) {
		telephoneTextBox.sendKeys(telephoneText);
	}
	
	public void enterPassword(String passwordText) {
		passwordTextBox.sendKeys(passwordText);
	}
	
	public void enterConfirmPassword(String confirmPasswordText) {
		confirmPasswordTextBox.sendKeys(confirmPasswordText);
	}
	
	public void clickNewsletterRadioButton() {
		newsletterRadiobutton.click();
	}
	
	public void checkPrivacyPolicy() {
		privacyPolicyCheckbox.click();
	}
	
	public void clickOnContinueButton() {
		continueButton.click();
	}
	
	public String retrieveTextofDuplicateEmail() {
		String duplicateEmail = duplicateEmailWarningMessage.getText();
		return duplicateEmail;
	}
	
	public String retrieveTextofPrivacyPolicyWarningMessage() {
		String privacyPolicyWarning = privacyPolicyWarningMessage.getText();
		return privacyPolicyWarning;
	}
	
	public String retrievefirstNameWarningMessage() {
		String firstnameWarning = firstNameWarningMessage.getText();
		return firstnameWarning;
	}
	
	public String retrievelastNameWarningMessage() {
		String lastnameWarning = lastNameWarningMessage.getText();
		return lastnameWarning;
	}
	
	public String retrieveEmailWarningMessage() {
		String emailWarning = emailWarningMessage.getText();
		return emailWarning;
	}
	
	public String retrieveTelephoneWarningMessage() {
		String telephoneWarning = telephoneWarningMessage.getText();
		return telephoneWarning;
	}
	
	public String retrievePasswordWarningMessage() {
		String passwordWarning = passwordWarningMessage.getText();
		return passwordWarning;
	}
	
	public AccountSuccessPage RegisterPageMandatoryFields(String firstNameText, String lastNameText, String emailText, String telephoneText, String passwordText, String confirmPasswordText) {
		firstNameTextBox.sendKeys(firstNameText);
		lastNameTextBox.sendKeys(lastNameText);
		emailTextBox.sendKeys(emailText);
		telephoneTextBox.sendKeys(telephoneText);
		passwordTextBox.sendKeys(passwordText);
		confirmPasswordTextBox.sendKeys(confirmPasswordText);
		privacyPolicyCheckbox.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public AccountSuccessPage RegisterPageAllFields(String firstNameText, String lastNameText, String emailText, String telephoneText, String passwordText, String confirmPasswordText) {
		firstNameTextBox.sendKeys(firstNameText);
		lastNameTextBox.sendKeys(lastNameText);
		emailTextBox.sendKeys(emailText);
		telephoneTextBox.sendKeys(telephoneText);
		passwordTextBox.sendKeys(passwordText);
		confirmPasswordTextBox.sendKeys(confirmPasswordText);
		newsletterRadiobutton.click();
		privacyPolicyCheckbox.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public boolean retrieveAllWarningMessages(String expectedPrivacyPolicyWarning, String expectedFirstNameWarning,
			String expectedLastNameWarning, String expectedEmailWarning, String expectedTelephoneWarning,
			String expectedPaswordWarning) {
		
		boolean privacypolicyWarningStatus = privacyPolicyWarningMessage.getText().contains(expectedPrivacyPolicyWarning);
		boolean firstNameWarningStatus = firstNameWarningMessage.getText().contains(expectedFirstNameWarning);
		boolean lastNameWarningStatus = lastNameWarningMessage.getText().contains(expectedLastNameWarning);
		boolean emailWarningStatus = emailWarningMessage.getText().contains(expectedEmailWarning);
		boolean telephoneWarningStatus = telephoneWarningMessage.getText().contains(expectedTelephoneWarning);
		boolean passwordWarningStatus = passwordWarningMessage.getText().contains(expectedPaswordWarning);
		
		return privacypolicyWarningStatus && firstNameWarningStatus && lastNameWarningStatus && emailWarningStatus &&
				telephoneWarningStatus && passwordWarningStatus;
	}
	


}
