package com.sd.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.sd.qa.base.TestBase;

public class ProductsPage extends TestBase {
	
	@FindBy (xpath = "//*[@class = 'product_sort_container']")
	private WebElement sortDropDown;
	
	public ProductsPage() {
		PageFactory.initElements(driver, this);
	}

	String nameOfTheItemString = "//*[@class = 'inventory_item_name ' and text() = 'itemName']";
	String addToCartButtonString = "//*[@class = 'inventory_item_name ' and text() = 'itemName']/../../../div/div/following-sibling::button";

	public boolean checkIfItemDisplayed(String itemToBeChecked) {
		String updatedItemName = nameOfTheItemString.replace("itemName", itemToBeChecked);
		return driver.findElement(By.xpath(updatedItemName)).isDisplayed();
	}

	public void addAnItemToCart(String itemToBeAdded) {
		String updatedElementString = addToCartButtonString.replace("itemName", itemToBeAdded);
		waitForCorrectUrl(10, "inventory.html");
		WebElement updatedElement = driver.findElement(By.xpath(updatedElementString));
		waitForElementToBeClickable(10, updatedElement);
		try {
			updatedElement.click();
		} catch (Exception e1) {
			System.out.println("Unable to click using click.");
			try {
				Actions actions = new Actions(driver);
				actions.moveToElement(updatedElement).click().perform();
			} catch (Exception e2) {
				System.out.println("Unable to click using Actions class.");
				try {
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].click();", updatedElement);
				} catch (Exception e3) {
					System.out.println("Unable to click using JavascriptExecutor.");
				}
			}
		}

	}
	
	public void sortTheItems(String order) {
		waitForElementToBeClickable(10, sortDropDown);
		Select dropdown = new Select(sortDropDown);
		dropdown.selectByVisibleText(order);
	}

}
