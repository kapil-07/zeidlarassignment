package com.zeidlerassignment.tests;

import java.awt.AWTException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import com.zeidlerassignment.pages.HomePage;
import com.zeidlerassignment.pages.LoginPage;
import com.zeidlerassignment.utils.JSONReaderUtil;
import com.zeidlerassignment.utils.WBSyncUtils;

import junit.framework.Assert;

public class UploadImageTest {

	private static final Logger LOGGER = Logger.getLogger(UploadImageTest.class); 
	private WebDriver driver;
	private JSONObject configDetails;
	@BeforeMethod
	public void init() {
		LOGGER.info("**** Starting with Before Method ***");
		this.configDetails = JSONReaderUtil.INSTANCE.readResourceFile("config.json");
		
		LOGGER.info("As per the Config JSON, environment is "+configDetails.get("environment"));
		
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximised");
		options.addArguments("--disable-notifications");

		driver = new ChromeDriver(options);
		driver.get((String) configDetails.get("URL"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	
	@Test
	public void testUploadImageTest(){
		LOGGER.info("*** Starting with upload Image Test with JPEG file***");
		
		
		
		LOGGER.info("**** Starting of Login Page ****");
		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.enterCredentials((String)this.configDetails.get("userID"), (String)this.configDetails.get("password"));
		loginPage.clickOnLoginButton();
		WBSyncUtils.waitForPageLoad(driver);
		LOGGER.info("*** Starting of Home Page ****");
		HomePage homePage = new HomePage(driver);
		
		homePage.clickFilesTab();
		WBSyncUtils.waitForPageLoad(driver);
		homePage.clickAddImageButton();
		WBSyncUtils.waitForPageLoad(driver);
		homePage.uploadImage(System.getProperty("user.dir") + "/src/main/resources/Earth.jpg");
		WBSyncUtils.waitForPageLoad(driver);
		Assert.assertTrue(homePage.isUploadSuccessfully());
		
	}
	
	@Test
	public void testUploadOtherFile() {
		LOGGER.info("*** Starting with upload Image Test with text file***");
		
		
		
		LOGGER.info("**** Starting of Login Page ****");
		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.enterCredentials((String)this.configDetails.get("userID"), (String)this.configDetails.get("password"));
		loginPage.clickOnLoginButton();
		WBSyncUtils.waitForPageLoad(driver);
		LOGGER.info("*** Starting of Home Page ****");
		HomePage homePage = new HomePage(driver);
		
		homePage.clickFilesTab();
		WBSyncUtils.waitForPageLoad(driver);
		homePage.clickAddImageButton();
		WBSyncUtils.waitForPageLoad(driver);
		homePage.uploadImage(System.getProperty("user.dir") + "/src/main/resources/Earth.txt");
		WBSyncUtils.waitForPageLoad(driver);
		Assert.assertFalse(homePage.isUploadSuccessfully());
		LOGGER.info("Checking whether the actual error text matches with expected Text =====>"+this.configDetails.get("uploadErrorMessageToBeVerifies"));
		Assert.assertEquals(homePage.getErrorUploadMessage(), this.configDetails.get("uploadErrorMessageToBeVerifies"));
	}
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
}
