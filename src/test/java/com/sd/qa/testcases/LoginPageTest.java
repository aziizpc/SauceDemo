package com.sd.qa.testcases;

import com.sd.qa.pages.LoginPage;
import com.sd.qa.pages.ProductsPage;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sd.qa.base.TestBase;

public class LoginPageTest extends TestBase {

	LoginPage loginPage;
	ProductsPage productsPage;
	
	public LoginPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp(){
		initialization();
		loginPage = new LoginPage();	
	}
	
	@Test(priority = 1)
	public void loginTest(){
		productsPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

}