package com.qa.tutorialsninja.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	
	public WebDriver driver;
	
	@FindBy(linkText = "HP LP3065")
	private WebElement validProduct;
	
	@FindBy(xpath = "//p[text() = 'There is no product that matches the search criteria.']")
	private WebElement noOrInvalidProductMessage;
	
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean displayStatusValidProduct() {
		boolean displayStatus = validProduct.isDisplayed();
		return displayStatus;
	}
	
	public boolean displayStatusInvalidOrNoProduct() {
		boolean displayStatus = noOrInvalidProductMessage.isDisplayed();
		return displayStatus;
	}

}
