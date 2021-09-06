package framework.pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import framework.appInit.Class_initEcomPrac;

public class partyCompleteOrderPage extends Class_initEcomPrac {
	Boolean isPageReady;
	partyCompleteOrderPage(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
		super();
		PageFactory.initElements(driver.get(), this);
		this.driver = driver;
		this.Test = Test;
			
}
	
	@FindBy(xpath="//a[@class='creat-btn']")
	WebElement payforparty;
	public CheckoutPage clickPayforParty() {
		/*try {
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));
			System.out.println("loader before click pay for party");
		} catch (Exception e) {
			System.out.println("no loader before click pay for party");
		}*/ //No loader before click
		
		try {
			WebElement guest_order_info = driver.get().findElementByCssSelector(".guestOrder-info table");
			new WebDriverWait(driver.get(), reducedTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.visibilityOf(guest_order_info)));
			System.out.println("guest order info success");
		} catch (Exception e) {
			System.out.println("guest order info catch");
		}
		
		try {
			click(payforparty);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		/*try {
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));
			
			System.out.println("loader after click pay for party");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("no loader after click pay for party");
		}*/ //unwanted code - currently catched in exception
		
		/*try {
			WebElement replace_pay = driver.get().findElementByXPath("//div[@class='creat-btn-wrap']/a");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			click(replace_pay);
			
			//System.out.prinln("Page rendering is complete to click on delivery type selection dropdown.");
		} catch (Exception e) {
			System.out.println("Stop sell item message is not shown in close party page");
			//e.printStackTrace();
			// TODO Auto-generated catch block
		}*/ //unwanted code
		
		try {
				return new CheckoutPage(driver, Test);
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
