package framework.pages;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import framework.appInit.Class_initEcomPrac;

public class ProductSelectionPage extends Class_initEcomPrac  {
	
	Boolean isPageReady;

	ProductSelectionPage(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
		super();
		PageFactory.initElements(driver.get(), this);
		this.driver = driver;
		this.Test = Test;
			
}
	
	@CacheLookup

	@FindBy(name="items")
	WebElement inputProductDetails;
	public ProductSelectionPage enterProductDetail(String productName_or_SKU) {
		
		try {
			type(inputProductDetails, productName_or_SKU);
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			isPageReady = new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));
		}
		catch(Exception e) {
			System.out.println("Failed to enter the SKU");
		}
	
	return this;
	}
	
	@FindBy(xpath="//div[@class='result']")
	WebElement searchResult;
	public ProductSelectionPage clickSearchResultDisplayed(){
		List<WebElement> resultItems = searchResult.findElements(By.xpath("//div[@class='result']/div"));
		resultItems.get(0).click();
		return this;
	}
	
	@FindBy(xpath="//div[@class='inc qty-btn']")
	WebElement addQuantity;
	public ProductSelectionPage addItemcount() {
		click(addQuantity);
		click(addQuantity);
		return this;
	}
	
	
	@FindBy(xpath="//button[@title]/a")
	WebElement btnAddToBasket;
	public CartSummaryPage clickAddToBasket() {
		click(btnAddToBasket);
		
		try {
			return new CartSummaryPage(driver, Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
}
