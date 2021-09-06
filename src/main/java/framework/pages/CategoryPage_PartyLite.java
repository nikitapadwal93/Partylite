package framework.pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import framework.appInit.Class_initEcomPrac;

public class CategoryPage_PartyLite extends Class_initEcomPrac{
	
	public CategoryPage_PartyLite(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
		super();
		PageFactory.initElements(driver.get(), this);
		this.driver = driver;
		this.Test = Test;
			
}
	
	@FindBy(xpath="//img[@class]")
	WebElement firstProduct;
	public ProductDescriptionPage clickfirstProduct() {
		try {
			firstProduct.click();
			System.out.println("product clicked from plp");
			reportStep("product clicked from plp", "pass");
		}
		catch(Exception e) {
			System.out.println("element not interactable in plp during product selection");
			reportStep("element not interactable in plp during product selection", "info");
			driver.get().executeScript("arguments[0].click()", firstProduct);
			System.out.println("product clicked from plp catch");
			reportStep("product clicked from plp catch", "pass");
		}
		try {
			return new ProductDescriptionPage(driver, Test);
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
