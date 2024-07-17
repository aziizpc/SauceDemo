package com.sd.qa.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sd.qa.base.TestBase;
import com.sd.qa.pages.LoginPage;
import com.sd.qa.pages.ProductsPage;
import com.sd.qa.util.TestUtil;

public class ProductsPageTest extends TestBase {
	
	private static final Logger logger = LogManager.getLogger(ProductsPageTest.class);	// Check imports

	LoginPage loginPage;
	ProductsPage productsPage;
	TestUtil testUtil;

	public ProductsPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		testUtil = new TestUtil();
		loginPage = new LoginPage();
		productsPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void validateSauceLabsBackpackIsAvailable() {
		logger.info("Started the test case : validateSauceLabsBackpackIsAvailable");
		Assert.assertTrue(productsPage.checkIfItemDisplayed("Sauce Labs Backpack"),
				"Sauce Labs Backpack is not displayed");
	}

	@Test(priority = 2)
	public void addAnItemToShoppingCart() throws InterruptedException {
		productsPage.addAnItemToCart("Sauce Labs Backpack");
	}

	@Test(priority = 3)
	public void addMultipleItemsToShoppingCart() throws InterruptedException {
		productsPage.addAnItemToCart("Sauce Labs Backpack");
		productsPage.addAnItemToCart("Sauce Labs Bike Light");
		productsPage.addAnItemToCart("Sauce Labs Bolt T-Shirt");
		productsPage.addAnItemToCart("Sauce Labs Fleece Jacket");
		productsPage.addAnItemToCart("Sauce Labs Onesie");
		productsPage.addAnItemToCart("Test.allTheThings() T-Shirt (Red)");
	}

	@DataProvider
	public Object[][] getItemsDataFromExcel_1() {				// Sheet with single column
		return TestUtil.getTestData("AddToShoppingCart");
	}

	@Test(priority = 4, dataProvider = "getItemsDataFromExcel_1")
	public void addToShoppingCartUsingDp_1(String item) throws InterruptedException {
		productsPage.addAnItemToCart(item);
	}

	@DataProvider
	public Object[][] getItemsDataFromExcel_2() {				// Sheet with multiple columns
		return TestUtil.getTestData("AddAllToShoppingCart");
	}

	@Test(priority = 5, dataProvider = "getItemsDataFromExcel_2")
	public void addToShoppingCartUsingDp_2(String item1, String item2, String item3, String item4, String item5,
			String item6) throws InterruptedException {
		productsPage.addAnItemToCart(item1);
		productsPage.addAnItemToCart(item2);
		productsPage.addAnItemToCart(item3);
		productsPage.addAnItemToCart(item4);
		productsPage.addAnItemToCart(item5);
		productsPage.addAnItemToCart(item6);
	}

	@Test(priority = 6)
	public void applySorting() {
		productsPage.sortTheItems("Price (low to high)");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}