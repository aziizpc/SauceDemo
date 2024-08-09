package com.sd.qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage extends Page {

	public BasePage(WebDriver driver, WebDriverWait wait) {		// Auto
		super(driver, wait);
	}
	
	public void doClick(By locator) {
		driver.findElement(locator).click();
	}
	
	public void doSendKeys(By locator, String text) {
		driver.findElement(locator).sendKeys(text);
	}
	
	public String doGetText(By locator) {
		return driver.findElement(locator).getText();
	}
	
	public void waitForCorrectUrl(int seconds, String resource) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.urlContains(resource));
    }

    public void waitForElementToBeClickable(int seconds, WebElement ele) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(ele));
    }

    public void waitForPresenceOfElement(int seconds, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
	
	

}