package com.zeidlerassignment.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WBSyncUtils {
	
	public static void waitForPageLoad(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		wait.until(waitForPageLoad());
	}
	
	public static ExpectedCondition<Boolean> waitForPageLoad() {
		
		return new ExpectedCondition<Boolean>(){
			@Override
			public Boolean apply(WebDriver driver){
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
	}
	
}
