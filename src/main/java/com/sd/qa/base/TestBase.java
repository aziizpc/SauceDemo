package com.sd.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;

import com.sd.qa.util.CustomWebDriverListener;
import com.sd.qa.util.TestUtil;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;

	public TestBase() {
		try {
			prop = new Properties();
			Path configPath = Paths.get("src", "main", "java", "com", "sd", "qa", "config", "config.properties");
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + File.separator + configPath.toString());
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initialization() {
		String browserName = prop.getProperty("browser");
		if (browserName.equals("chrome")) {
			Path configPath = Paths.get("drivers", "chromedriver.exe");
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + File.separator + configPath.toString());
			ChromeOptions options = new ChromeOptions();
			options.setPageLoadStrategy(PageLoadStrategy.valueOf(TestUtil.PAGE_LOAD_STRATEGY)); // NORMAL / EAGER / NONE
			options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(options);
		} else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "<Path to ff driver>");
			FirefoxOptions options = new FirefoxOptions();
			options.setPageLoadStrategy(PageLoadStrategy.valueOf(TestUtil.PAGE_LOAD_STRATEGY));
			driver = new FirefoxDriver(options);
		}

		WebDriverListener listener = new CustomWebDriverListener();
		driver = new EventFiringDecorator<>(listener).decorate(driver);

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
		driver.get(prop.getProperty("url"));
	}

	public static void waitForCorrectUrl(int seconds, String resource) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.urlContains(resource));
	}

	public static void waitForElementToBeClickable(int seconds, WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	public static void waitForPresenceOfElement(int seconds, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
}