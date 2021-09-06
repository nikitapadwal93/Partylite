package framework.pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import framework.appInit.Class_initEcomPrac;

public class CustomerAddressPage extends Class_initEcomPrac{
	
	 CustomerAddressPage(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
			super();
			PageFactory.initElements(driver.get(), this);
			this.driver = driver;
			this.Test = Test;
				
	}
	
	@FindBy(xpath="//button[@title='Add New Address']")
	WebElement add_new_address;
	public CustomerNewAddressPage clickaddNewAddress() {
		click(add_new_address);
		try {
			return new CustomerNewAddressPage(driver,Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@FindBy(xpath="//div[@data-ui-id='message-success']")
	WebElement verify_address_msg;
	public CustomerAddressPage verifyNewAddressMsg() {
		verifyDisplayed(verify_address_msg);
		return this;
	}
	
	@FindBy(xpath="//a[@title='PartyLite']")
	WebElement partylite_logo;
	public LandingPage_PartyLite gotoHomepage() {
		click(partylite_logo);
		try {
			return new LandingPage_PartyLite(driver,Test);
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
