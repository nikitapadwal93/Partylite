package framework.pages;


	import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import framework.appInit.Class_initEcomPrac;

	public class MyParties_ContactsPage extends Class_initEcomPrac  {

		MyParties_ContactsPage(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
			super();
			PageFactory.initElements(driver.get(), this);
			this.driver = driver;
			this.Test = Test;
				
	}
	
	@FindBy(xpath="//a[@class='addcontacts']")
	WebElement btnCreateContact;
	public MyParties_ContactsPage clickCreateContact() {
		btnCreateContact.click();
		try {
			new WebDriverWait(driver.get(),reducedTimeout).until(ExpectedConditions.visibilityOf(txtFirstName));
		}
		catch(Exception e) {
			btnCreateContact.click();
		}
		return this;
	}
		
	@FindBy(id="contact_firstname")
	WebElement txtFirstName;
	public MyParties_ContactsPage typeFirstName(String ContactsFN) {
		type(txtFirstName, ContactsFN);
		return this;
	}
	
	
	@FindBy(id="last_name")
	WebElement txtLastName;
	public MyParties_ContactsPage typeLastName(String ContactsLN) {
		type(txtLastName, ContactsLN);
		return this;
	}
	
	@FindBy(id="contact_email")
	WebElement txtEmail;
	public MyParties_ContactsPage typeEmail(String ContactsEmail) {
		long randomNum = (long)Math.floor(Math.random()*9000000000L)+8888888888888L;
		ContactsEmail= ContactsEmail+randomNum+"@mailinator.com";
		type(txtEmail, ContactsEmail);
		return this;
	}
	
	@FindBy(id="contact_phone")
	WebElement txtPhone;

	public MyParties_ContactsPage typePhone(String Country,String ContactsPhone) {
		type(txtPhone, markets.get(Country).getProperty("phoneNumber", ContactsPhone));

		return this;
	}
	
	@FindBy(xpath = "//label[@for='assign_to']")
	WebElement cboxTAC;

	public MyParties_ContactsPage clickAgreementToAddToParty() {
	
		click(cboxTAC);
		return this;
	}
	
	@CacheLookup
	@FindBy(id="send")
	WebElement btnSaveContact;
	public MyParties_ContactsPage clickSaveContact() {
		click(btnSaveContact);
		try {
		new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.invisibilityOf(btnSaveContact));
		new WebDriverWait(driver.get(),waitTimeout)
		.until(ExpectedConditions.refreshed
				(ExpectedConditions.urlContains("/party/contact/dashboard/")));
		} catch(TimeoutException e) {
			reportStep("Save contacts action took longer than usual.","info");
			
		}
		
				
		return this;
	}
	
	
	@FindBy(id="search-field")
	WebElement txtEmailSearch;
	public MyParties_ContactsPage searchContactEmail(String ContactsEmail) {
		try {
			new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions
					.attributeToBe(By.xpath("//body[@data-container='body']"), "aria-busy", "false"));
	 }
	 catch(Exception e)
	 {
		 
	 }
		type(txtEmailSearch, ContactsEmail);
		return this;
	}
	
	@FindBy(css="a.contact-name")
	WebElement selectContact;
	public Contact_DetailPage selectContact() {
		try {
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			Boolean isPageReady = new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));
			
			//System.out.prinln("Page rendering is complete to click on delivery type selection dropdown.");
		} catch (NoSuchElementException  | TimeoutException e) {
			
			// TODO Auto-generated catch block
		}
		click(selectContact);
		try {
			return new Contact_DetailPage(driver, Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@FindBy(linkText="My Parties")
	WebElement selectMyParties;
	public Party_Dashboard selectMyParties() {
		click(selectMyParties);
		try {
			return new Party_Dashboard(driver, Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	@FindBy(id="address_line_1")
	WebElement ShippingAddress_newAddressLine1;
	public MyParties_ContactsPage typeNewShippingAddressLine1(String Country,String shipAddrLine1) {
		type(ShippingAddress_newAddressLine1, markets.get(Country).getProperty("propAddress1", shipAddrLine1));
		return this;
	}
	
	@FindBy(id="address_line_2")
	WebElement ShippingAddress_newAddressLine2;
	public MyParties_ContactsPage typeNewShippingAddressLine2(String Country,String shipAddrLine2) {
		type(ShippingAddress_newAddressLine2, markets.get(Country).getProperty("propAddress1", shipAddrLine2));
		return this;
	}
	
	
	@FindBy(id="address_city")
	WebElement ShippingAddress_newAddresscity;
	public MyParties_ContactsPage typeNewShippingAddresscity(String Country,String shipAddrcity) {
		type(ShippingAddress_newAddresscity, markets.get(Country).getProperty("propCity", shipAddrcity));
		return this;
	}
	
	
	@FindBy(id="address_country")
	WebElement ShippingAddress_newAddresscountry;
	public MyParties_ContactsPage typeNewShippingAddresscountry(String shipAddrcountry) {
		type(ShippingAddress_newAddresscountry, shipAddrcountry);
		return this;
	}
	
	@FindBy(id="address_postcode")
	WebElement ShippingAddress_newAddresspostcode;
	public MyParties_ContactsPage typeNewShippingAddresspostcode(String Country,String shipAddrpostcode) {
		type(ShippingAddress_newAddresspostcode, markets.get(Country).getProperty("postcode", shipAddrpostcode));
		return this;
	}
	
		
	}
			
