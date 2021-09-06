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

public class PaymentMethodsPage extends Class_initEcomPrac  {
	boolean isPageReady;

	PaymentMethodsPage(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
		super();
		PageFactory.initElements(driver.get(), this);
		this.driver = driver;
		this.Test = Test;
			
}
	@FindBy(name="street[0]")
	WebElement txtBoxStreetAddress1;
	public PaymentMethodsPage enterCustomerAddress1(String Country, String address1) {
		try {
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			System.out.println("Waiting for loader to finish page rendering.");
			isPageReady = new WebDriverWait(driver.get(), 30).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		try
		{
			type(txtBoxStreetAddress1,markets.get(Country).getProperty("propAddress1", address1));
		}
		catch(Exception e) {
			System.out.println("Multiple street boxes");
		}
		try {
			txtBoxStreetAddress1=driver.get().findElementByXPath("(//input[@name='street[0]'])[3]");
			type(txtBoxStreetAddress1,markets.get(Country).getProperty("propAddress1", address1));
		}
		catch(Exception e) {
			System.out.println("Multiple street boxes");
		}
		
		try
		{
			txtBoxStreetAddress1=driver.get().findElementByXPath("(//input[@name='street[0]'])[2]");
			type(txtBoxStreetAddress1,markets.get(Country).getProperty("propAddress1", address1));
		}
		catch(Exception e) {
			System.out.println("Multiple street boxes");
		}
		
		return this;
	}
	
	@FindBy(name="postcode")
	WebElement txtBoxZipCode;
	public PaymentMethodsPage enterCustomerZipCode(String Country, String zipcode) {
		
		zipcode = markets.get(Country).getProperty("postcode");//multi-lingual support
		System.out.println("Retrieved postcode value is " + zipcode);
		
		try
		{
			type(txtBoxZipCode,zipcode);
		}
		catch(Exception e) {
			System.out.println("Multiple post boxes");
		}
		
		try
		{
			txtBoxZipCode=driver.get().findElementByXPath("(//input[@name='postcode'])[3]");
			type(txtBoxZipCode,zipcode);
		}
		catch(Exception e) {
			System.out.println("Multiple post boxes");
		}
		
		try
		{
			txtBoxZipCode=driver.get().findElementByXPath("(//input[@name='postcode'])[2]");
			type(txtBoxZipCode,zipcode);
		}
		catch(Exception e) {
			System.out.println("Multiple post boxes");
		}
		return this;
	}
	
	@FindBy(name="city")
	WebElement txtBoxCity;
	public PaymentMethodsPage enterCustomerCity(String Country, String city) {
		
		try
		{
			type(txtBoxCity,markets.get(Country).getProperty("propCity", city));
		}
		catch(Exception e) {
			System.out.println("Multiple city boxes");
		}
		try
		{
			txtBoxCity=driver.get().findElementByXPath("(//input[@name='city'])[3]");
			type(txtBoxCity,markets.get(Country).getProperty("propCity", city));
		}
		catch(Exception e) {
			System.out.println("Multiple city boxes");
		}
		
		try
		{
			txtBoxCity=driver.get().findElementByXPath("(//input[@name='city'])[2]");
			type(txtBoxCity,markets.get(Country).getProperty("propCity", city));
		}
		catch(Exception e) {
			System.out.println("Multiple city boxes");
		}
		return this;
	}
	
	@FindBy(name="telephone")
	WebElement txtBoxTelephone;
	public PaymentMethodsPage enterCustomerTelephone(String Country, String telephone) {
		telephone=markets.get(Country).getProperty("phoneNumber");
		System.out.println("Retrieved phone number value is " + telephone);
		type(txtBoxTelephone,telephone);
		return this;
	}
	
	////*[@id='checkout-payment-method-load']/div/div/div[2]/div[1]/label
	@FindBy(xpath="//div[@class='payment-method']//label[@for='drwp_pp']") //modified to cater cash-on-delivery option
	WebElement radioAllPaymentMethods;
public PaymentMethodsPage clickPaymentMethods() {
		
		try {
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			isPageReady = new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));
			
			//System.out.prinln("Page rendering is complete.");
		} catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.TimeoutException e ) {
			reportStep("Payment method selection failed.","warning");
		}
		if(isPageReady) {
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.urlContains("#payment"));
			try {
		radioAllPaymentMethods.click();
			} catch(org.openqa.selenium.ElementNotVisibleException | org.openqa.selenium.NoSuchElementException e) {
				//do nothing
				return this;
			}
		return this;
	}
		return this;
	}

@FindBy(xpath="//label[@for='checkmo']")
WebElement radioCashorCheck;
public PaymentMethodsPage clickRadioCashOrCheck() {
	
	try {
		WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
		//System.out.prinln("Waiting for loader to finish page rendering.");
		isPageReady = new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
				(ExpectedConditions.attributeContains(loader, "style", "none")));
		
		//System.out.prinln("Page rendering is complete.");
	} catch (NoSuchElementException | TimeoutException e ) {
		reportStep("Payment method selection failed.","warning");
	}
	if(isPageReady) {

		new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.urlContains("#payment"));
		click(radioCashorCheck);
}
	
	try {
		WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
		//System.out.prinln("Waiting for loader to finish page rendering.");
		isPageReady = new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
				(ExpectedConditions.attributeContains(loader, "style", "none")));
		
		//System.out.prinln("Page rendering is complete.");
	} catch (NoSuchElementException | TimeoutException e ) {
		reportStep("No loader in payment selection.","info");
	}
	
	return this;
}


@FindBy(xpath="//div[@id='debit-card']")
WebElement radioDebit;
public PaymentMethodsPage clickRadioDebit() {
	
	try {
		WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
		//System.out.prinln("Waiting for loader to finish page rendering.");
		isPageReady = new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
				(ExpectedConditions.attributeContains(loader, "style", "none")));
		
		//System.out.prinln("Page rendering is complete.");
	} catch (NoSuchElementException | TimeoutException e ) {
		reportStep("Payment method selection failed.","warning");
	}
	if(isPageReady) {

		new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.urlContains("#payment"));
		click(radioDebit);
}
	
	try {
		WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
		//System.out.prinln("Waiting for loader to finish page rendering.");
		isPageReady = new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
				(ExpectedConditions.attributeContains(loader, "style", "none")));
		
		//System.out.prinln("Page rendering is complete.");
	} catch (NoSuchElementException | TimeoutException e ) {
		reportStep("No loader in payment selection.","info");
	}
	
	return this;
}



	
	@FindBy(id="payment_type")
	WebElement selectPaymentType;
	public PaymentMethodsPage selectTypeOfPayment(String PaymentType) {
		String current_url=driver.get().getCurrentUrl();
		System.out.println(current_url+" current url");
		if(current_url.contains("partylite.co.uk"))
		{
			try {
				selectDropDownUsingText(selectPaymentType, PaymentType);
				} catch(org.openqa.selenium.NoSuchElementException e) {  //multi-lingual support added
					reportStep("Payment selection option not available in the screen. Ignoring this step.","info");
					return this;
				}
		}
		return this;
	}
	
	@FindBy(xpath="//label[@for='billing-address-same-as-shipping-drwp_pp']")
	WebElement selectSameasShipping;
	public PaymentMethodsPage selectSameasShipping() {
		try {
		click(selectSameasShipping);
		} catch(org.openqa.selenium.NoSuchElementException e) {  //multi-lingual support added
			reportStep("Same as Shipping option not available in the screen. Ignoring this step.","info");
			return this;
		}
		return this;
	}
	
	@FindBy(css="div.billing-address-details")
	WebElement billing_address_section;
	public PaymentMethodsPage verifyBillingAddress() {
		verifyEnabled(billing_address_section);
		return this;
	}
	
	@FindBy(xpath="//label[@for='termsCash' or @for='termsCredit' or @for='terms1']/span") //multi-lingual support added
	WebElement chkboxAcceptTerms; // xpath updated to identify the second option - all payment memthods available , first being cash on delivery
	public PaymentMethodsPage clickAcceptTerms() {
		try {
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			isPageReady = new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));
		}
		catch(Exception e) {
			System.out.println("No loader");
		}
		
		try {
			WebElement body = driver.get().findElementByXPath("//body");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			isPageReady = new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(body, "aria-busy", "false")));
		}
		catch(Exception e) {
			System.out.println("No aria busy");
		}
		
		try {
			//driver.get().findElementByXPath("(//span[@class='terms-text'])").click();
			click(chkboxAcceptTerms); 
			System.out.println("checkbox clicked in first try");
		} catch(Exception e) {
			System.out.println("checkbox click issue");
			e.printStackTrace();
		}
		WebElement terms_checkbox=driver.get().findElementByXPath("//input[@id='terms1' or @id='termsCash' or @id='termsCredit']");
		if(terms_checkbox.isSelected())
			System.out.println("Terms Checkbox selected");
		else
			click(chkboxAcceptTerms);
		return this;
	}
	
	
	WebElement chkboxTransfer; // xpath updated to identify the second option - all payment memthods available , first being cash on delivery
	public PaymentMethodsPage clickTransferParty() {
		
			try{
				driver.get().findElementByClassName("transfer-text").click();
				return this;
			} catch(Exception e1) {
				e1.printStackTrace();
			}
			
			
		return this;
	}
	
	//@FindBy(xpath="(//span[@class='terms-text'])[2]") //multi-lingual support added
		WebElement chkboxCODAcceptTerms; // xpath updated to identify the second option - all payment memthods available , first being cash on delivery
		public PaymentMethodsPage clickCODAcceptTerms() {
			
				try{
					driver.get().findElementByXPath("(//span[@class='terms-text'])[2]").click();
					return this;
				} catch(Exception e1) {
					e1.printStackTrace();
				}
				
				
			return this;
		}
	
	
	@FindBy(xpath="//button[@id='current_billing']/span")
	WebElement btnPlacePartyOrder;
	public TestPaymentPage clickPlacePartyOrderButton() {
		
			
				
				try {
					click(btnPlacePartyOrder);
				
				} catch(org.openqa.selenium.ElementNotInteractableException | org.openqa.selenium.NoSuchElementException e1) {
					e1.printStackTrace();
						
					}
			
		try {
				return new TestPaymentPage(driver, Test);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		return null;
		
	}
	
	@FindBy(css=".cod .checkout span")
	WebElement btnPlaceCODPartyOrder;
	public OrderCompletionPage clickPlaceCODPartyOrderButton() {
		
			
				
				try {
					click(btnPlaceCODPartyOrder);
				
				} catch(org.openqa.selenium.ElementNotInteractableException | org.openqa.selenium.NoSuchElementException e1) {
					e1.printStackTrace();
						
					}
			
		try {
				return new OrderCompletionPage(driver, Test);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		return null;
		
	}
	
	//@FindBy(xpath="//span[contains(@data-bind,'Place Order')]") //multi-lingual support added
	WebElement btnPlaceOrder;
	public TestPaymentPage clickPlaceOrderButton() {
		try {
			System.out.println("Before WL"+driver.get().getCurrentUrl());
			reportStep("Before WL"+driver.get().getCurrentUrl(),"info");
			driver.get().findElementByXPath("(//span[contains(@data-bind,'Place Order')])[2]").click();
			//chkboxAcceptTerms.click(); 
			try {
			return new TestPaymentPage(driver, Test);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		} catch(org.openqa.selenium.ElementNotInteractableException | org.openqa.selenium.NoSuchElementException e) {
			
			try{
				driver.get().findElementByXPath("//span[contains(@data-bind,'Place Order')]").click();
				try {
					return new TestPaymentPage(driver, Test);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			} catch(org.openqa.selenium.NoSuchElementException e3) {
				reportStep("Identifying the placeorder button for the selected payment option failed.", "warning");
			}
		
	}
	return null;
	
}
	
	@FindBy(xpath="//button[@title='Place Order']")
	WebElement btnPlacePaperOrder;
	public PartyOrderCompletionPage clickPlacePaperOrderButton() {
		try {
			//driver.findElementByXPath("(//span[contains(text(),'Next')])").click();
			WebElement nextBtn=driver.get().findElementByXPath("(//button[@type='submit']/span[@data-bind])[3]");
			/*WebElement checkelement=driver.findElementByXPath("(//button[@disabled])[3]");
			String getattribute=checkelement.getAttribute("disabled");
			System.out.println(getattribute);
			if(getattribute.equals("true"))*/
			nextBtn.click();
		
		} catch(org.openqa.selenium.ElementNotInteractableException | org.openqa.selenium.NoSuchElementException | org.openqa.selenium.TimeoutException e) {
			
				//driver.findElementByXPath("(//span[contains(text(),'Next')])[2]").click();
				WebElement nextBtn=driver.get().findElementByXPath("(//button[@type='submit']/span[@data-bind])[2]");
				WebElement checkelement=driver.get().findElementByXPath("(//button[@disabled])[2]");
				String getattribute=checkelement.getAttribute("disabled");
				System.out.println(getattribute);
				if(getattribute.equals("true"))
				nextBtn.click();
			}
		try {
			return new PartyOrderCompletionPage(driver, Test);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	return null;
	
		
	}
	
		
	//Party Order
	@FindBy(xpath="//button[@data-bind='click: updateAddress()']")
	WebElement updateButton;
	public PaymentMethodsPage clickUpdateButton() {
		click(updateButton);
		return this;
	}
	
	//outside order
	@FindBy(css="button.action.action-update")
	WebElement updateAddressButton;
	public PaymentMethodsPage clickUpdateAddressButton() {
		click(updateAddressButton);
		return this;
	}
	
	//@FindBy(xpath="//label[@for='drwp_pp']")
	@FindBy(id="credit-card")
	WebElement PayByCredit;
	public PaymentMethodsPage clickPayByCredit() {
		try {
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			isPageReady = new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		click(PayByCredit);
		
		try {
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			isPageReady = new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return this;
	}
	
	@FindBy(xpath="//label[@for='cashondelivery']")
	WebElement COD;
	public PaymentMethodsPage selectCOD() {
		WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
		//System.out.prinln("Waiting for loader to finish page rendering.");
		isPageReady = new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
				(ExpectedConditions.attributeContains(loader, "style", "none")));
		click(COD);
		return this;
	}
	
	@FindBy(xpath="//div[@id='paypal']/label")
	WebElement PaybyPaypal;
	public PaymentMethodsPage selectPayPal() {
		WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
		//System.out.prinln("Waiting for loader to finish page rendering.");
		isPageReady = new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
				(ExpectedConditions.attributeContains(loader, "style", "none")));
		click(PaybyPaypal);
		return this;
	}
	
}


