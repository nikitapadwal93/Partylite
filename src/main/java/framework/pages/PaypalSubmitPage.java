package framework.pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import framework.appInit.Class_initEcomPrac;

public class PaypalSubmitPage extends Class_initEcomPrac {
	
	PaypalSubmitPage(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
		super();
		PageFactory.initElements(driver.get(), this);
		this.driver = driver;
		this.Test = Test;
			
}
	
	@FindBy(xpath="//input[@name='process']")
	WebElement buttonPaypalConfirm;
	public OrderCompletionPage clickPaypalSubmit() {
		try {
			click(buttonPaypalConfirm);
		}
		catch(Exception e) {
			System.out.println("Reload button shows instead of paypal submit");
			driver.get().navigate().refresh();
			click(buttonPaypalConfirm);
		}
		
		try {
			return new OrderCompletionPage(driver, Test);
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
