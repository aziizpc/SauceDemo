package com.sd.qa.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

public class CustomWebDriverListener implements WebDriverListener {

	private static final Logger logger = LogManager.getLogger(CustomWebDriverListener.class);

	@Override
	public void beforeClick(WebElement element) {
		logger.info("Before clicking on: " + element.toString());
	}

	@Override
	public void afterClick(WebElement element) {
		logger.info("After clicking on: " + element.toString());
	}

	@Override
	public void beforeQuit(WebDriver driver) {
		logger.info("Quit started");
		// WebDriverListener.super.beforeQuit(driver);
	}

	@Override
	public void afterQuit(WebDriver driver) {
		logger.info("Quit completed");
		// WebDriverListener.super.afterQuit(driver);
	}

	@Override
	public void beforeMaximize(Window window) {
		logger.info("Maximize started : " + window.getSize());
		// WebDriverListener.super.beforeMaximize(window);
	}

	@Override
	public void afterMaximize(Window window) {
		logger.info("Maximize completed : " + window.getSize());
		// WebDriverListener.super.afterMaximize(window);
	}

}