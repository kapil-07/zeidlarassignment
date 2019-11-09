package com.zeidlerassignment.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	private static final Logger LOGGER = Logger.getLogger(HomePage.class);
	private WebDriver driver;
	
	@FindBy(how = How.XPATH , using = "//a[contains(.,'Files')]")
	private WebElement filesTab;
	
	private String xpath_AddImage_Button = "//a[contains(.,'Add Images')]";
	private String xpath_uploadImage_Section = "//input[@type='file']";
	private String xpath_Selector_successMessage = "//div[contains(@class,'dz-success')]";
	private String xpath_Selector_errorMessage = "//div[contains(@class,'dz-error')]";
	private String xpath_Selector_progress = "//div[contains(@class,'dz-progressing')]";
	private String xpath_Error_Message = "//div[@class = 'dz-error-message']/span";
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	public void clickFilesTab() {
		LOGGER.info("**** Clicking on files tab ****");
		filesTab.click();
	}
	public void clickAddImageButton() {
		LOGGER.info("*** Clicking on Add Image button ***");
		WebElement add_ImageElement = driver.findElement(By.xpath(this.xpath_AddImage_Button));
		add_ImageElement.click();
	}
	
	public void uploadImage(String path_To_File) {
		LOGGER.info("*** Clicking on upload image section ****");
		WebElement uploadImageSection = driver.findElement(By.xpath(xpath_uploadImage_Section));
		uploadImageSection.sendKeys(path_To_File);
	}
	public boolean isUploadSuccessfully() {
		LOGGER.info("*** Checking whether the image is uploaded successfully or not");
		new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath_Selector_progress)));
		if(driver.findElement(By.xpath(xpath_Selector_successMessage)).isDisplayed()) {
			return true;
		}else if (driver.findElement(By.xpath(xpath_Selector_errorMessage)).isDisplayed()) {
			return false;
		}else {
			return false;
		}
	}
	
	public String getErrorUploadMessage() {
		return driver.findElement(By.xpath(xpath_Error_Message)).getText();
	}
}
