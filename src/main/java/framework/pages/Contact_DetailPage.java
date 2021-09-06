package framework.pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import framework.appInit.Class_initEcomPrac;

public class Contact_DetailPage extends Class_initEcomPrac {
	
	 

	Contact_DetailPage(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
		super();
		PageFactory.initElements(driver.get(), this);
		this.driver = driver;
		this.Test = Test;
			
}
	
	@FindBy(css="li.ordertab")
	WebElement selectOrderTab;
	public Contact_DetailPage selectOrderTab() {
		click(selectOrderTab);
		return this;
	}
	
	//updated click action to bypass framework to enable this method's catch to trigger.
	@FindBy(css=".ordersDashboard a.action.view")
	WebElement selectContactOrder;
	public Order_DetailPage selectContactOrder() {
		
		try {
			selectContactOrder.click();
			return new Order_DetailPage(driver, Test);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			reportStep("No Orders associated to that contact", "Info");
			System.out.println("No orders associated and hence no element found as follows - " +e.getMessage());
		}
		return null;
	}
	
	
}
