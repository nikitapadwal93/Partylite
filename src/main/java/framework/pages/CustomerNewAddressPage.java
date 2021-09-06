package framework.pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
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

public class CustomerNewAddressPage extends Class_initEcomPrac {
	Boolean isPageReady;
	 CustomerNewAddressPage(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
			super();
			PageFactory.initElements(driver.get(), this);
			this.driver = driver;
			this.Test = Test;
				
	}
	 
	 @FindBy(id="street_1")
		WebElement txtBoxStreetAddress1;
		public CustomerNewAddressPage enterCustomerAddress1(String Country, String address1) {
			type(txtBoxStreetAddress1,markets.get(Country).getProperty("propAddress1", address1)); //multi-lingual support added
			return this;
		}
		
		@FindBy(id="street_2")
		WebElement txtBoxStreetAddress2;
		public CustomerNewAddressPage enterCustomerAddress2(String address2) {
			type(txtBoxStreetAddress2,address2);
			return this;
		}
		
		@FindBy(id="street_3")
		WebElement txtBoxStreetAddress3;
		public CustomerNewAddressPage enterCustomerAddress3(String address3) {
			String current_url=driver.get().getCurrentUrl();
			System.out.println(current_url+" current url");
			if(current_url.contains("partylite.co.uk")
					|| current_url.contains("partylite.ca"))
			{
				try {
					type(txtBoxStreetAddress3,address3);
					} catch(org.openqa.selenium.NoSuchElementException e) {  //multi-lingual support added
						reportStep("Address 3 field is not shown on the UI","info");
						return this;
					}	
			}
			return this;
		}
		
		@FindBy(name="city")
		WebElement txtBoxCity;
		public CustomerNewAddressPage enterCustomerCity(String Country, String city) {
			
				type(txtBoxCity,markets.get(Country).getProperty("propCity", city));  //multi-lingual support added
			
			
			return this;
		}
		
		@FindBy(name="postcode")
		WebElement txtBoxZipCode;
		public CustomerNewAddressPage enterCustomerZipCode(String Country, String zipcode) {
			zipcode = markets.get(Country).getProperty("postcode");  // multi-lingual support added
			type(txtBoxZipCode,zipcode);
			return this;
		}
		
		@FindBy(id="telephone")
		WebElement txtBoxTelephone;
		public CustomerNewAddressPage enterCustomerTelephone(String Country, String telephone) {
			telephone = markets.get(Country).getProperty("phoneNumber");
			type(txtBoxTelephone,telephone);
			return this;
		}
		
		@FindBy(xpath="//span[contains(text(),'Use as my default billing address')]")
		WebElement checkboxBillingAddress;
		public CustomerNewAddressPage clickCheckBoxBillingAddress() {
			try {
				new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.presenceOfElementLocated(By.xpath
						("//span[contains(text(),'Use as my default billing address')]")));
			click(checkboxBillingAddress);
			return this;
			} catch(org.openqa.selenium.NoSuchElementException | org.openqa.selenium.TimeoutException e) {
				//do nothing
				return this;
			}
			
		}
		
		@FindBy(xpath="//span[contains(text(),'Use as my default shipping address')]")
		WebElement checkboxShippingAddress;
		public CustomerNewAddressPage clickCheckBoxShippingAddress() {
			
			
			try {
				new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.presenceOfElementLocated(By.xpath
						("//span[contains(text(),'Use as my default shipping address')]")));
				click(checkboxShippingAddress);
				return this;
			} catch(org.openqa.selenium.NoSuchElementException | org.openqa.selenium.TimeoutException e) {
				//do nothing
				return this;
			}
			
			
			
			
		}
		
		@FindBy(xpath="//button[contains(@class,'address-validation')]") //multi-lingual support added
		WebElement btnSearchValidate;
		public CustomerNewAddressPage clickbtnSearchValidate() {
			click(btnSearchValidate);
			try {
				WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
				//System.out.prinln("Waiting for loader to finish page rendering.");
				isPageReady = new WebDriverWait(driver.get(), 30).until(ExpectedConditions.refreshed
						(ExpectedConditions.attributeContains(loader, "style", "none")));
				
				//System.out.prinln("Page rendering is complete.");
			} catch (NoSuchElementException  | TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return this;
			}
			return this;
		}
		
		@FindBy(xpath="//label[@for='address_format_0']")
		WebElement selectAddressFormat;
		public CustomerNewAddressPage clickAddressFormat() {
			
			try {
				selectAddressFormat.click();  // multi-lingual support added
				
				WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
				//System.out.prinln("Waiting for loader to finish page rendering.");
				isPageReady = new WebDriverWait(driver.get(), 30).until(ExpectedConditions.refreshed
						(ExpectedConditions.attributeContains(loader, "style", "none")));
				
				//System.out.prinln("Page rendering is complete.");
			} catch (org.openqa.selenium.NoSuchElementException  | org.openqa.selenium.TimeoutException e) {
				// TODO Auto-generated catch block
				System.out.println("Address cleansing not found for the provided address");//multi-lingual support added
				driver.get().findElementByCssSelector(".address-manual span").click();
				System.out.println("Manual address option selected");
				return this;
			}
			return this;
		}
		
		@FindBy(xpath="//button[@data-action='save-address']")  //multi-lingual support added
		WebElement btnSaveAddress;
		public CustomerAddressPage clickbtnSaveAddress() {
			click(btnSaveAddress);
			try {
				return new CustomerAddressPage(driver, Test);
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
