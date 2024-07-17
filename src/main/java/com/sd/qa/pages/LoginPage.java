package com.sd.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sd.qa.base.TestBase;

public class LoginPage extends TestBase {

	@FindBy(id = "user-name")
	private WebElement usernameTextBox;

	@FindBy(id = "password")
	private WebElement passwordTextBox;

	@FindBy(id = "login-button")
	private WebElement loginButton;

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	public void enterUsername(String username) {
		usernameTextBox.sendKeys(username);
	}

	public void enterPassword(String password) {
		passwordTextBox.sendKeys(password);
	}

	public void clickLoginButton() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
    	js.executeScript("arguments[0].click();", loginButton);
	}

	public ProductsPage login(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickLoginButton();
		return new ProductsPage();
	}

}