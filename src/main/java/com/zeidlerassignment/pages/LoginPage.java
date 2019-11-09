package com.zeidlerassignment.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {
	private static final Logger LOGGER = Logger.getLogger(LoginPage.class);
	private WebDriver driver;
	
	@FindBy(how = How.ID , using = "user")
	private WebElement emailTextBox;
	
	@FindBy(how = How.ID , using = "password")
	private WebElement passTextBox;
	
	@FindBy(how = How.XPATH , using = "//fieldset/input[@value = 'Login']")
	private WebElement loginButton;
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	public void enterCredentials(String userID, String password) {
		LOGGER.info("*** Entering "+userID+" into the email ID text box ***");
		emailTextBox.sendKeys(userID);
		
		LOGGER.info("*** Entering password into password text box ***");
		passTextBox.sendKeys(password);
		
	}
	
	public void clickOnLoginButton() {
		loginButton.click();
	}
	}
