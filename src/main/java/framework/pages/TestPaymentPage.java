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

public class TestPaymentPage extends Class_initEcomPrac  {

	TestPaymentPage(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
		super();
		PageFactory.initElements(driver.get(), this);
		this.driver = driver;
		this.Test = Test;
			
}
	
	@FindBy(xpath="//div[@id='price']")  // multi-lingual support added
	WebElement OrderNumber;
	public TestPaymentPage getOrderNumber() {
		System.out.println(OrderNumber.getText());
		reportStep("Order Number" + OrderNumber.getText() +" retrieved successfully","pass");
		System.out.println("In WL "+driver.get().getCurrentUrl());
		reportStep("In WL"+driver.get().getCurrentUrl(),"info");
		return this;
	}
	
	@FindBy(xpath="//fieldset[@class='cardNumber']//input")  // multi-lingual support added
	WebElement txtBoxCardNumber;
	public TestPaymentPage enterCardNumber(String cardNumber) {
		type(txtBoxCardNumber, cardNumber);
		return this;
	}
	
	@FindBy(xpath="//fieldset[@class='cardNumber']//input")  // multi-lingual markets to be paid by Mastercard
	WebElement MC_CardNumber;
	public TestPaymentPage enterMasterCardNum() {
		type(MC_CardNumber, "5555555555554444");
		return this;
	}
	
	@FindBy(xpath="//fieldset[@class='cardNumber']//input")  // Used only for UK
	WebElement Debit_CardNumber;
	public TestPaymentPage enterVisaDebit_UK() {
		type(Debit_CardNumber, "4462030000000000");
		return this;
	}
	
	@FindBy(xpath="//fieldset[@class='cardNumber']//input")  // Used only for US
	WebElement BoxCardNumber;
	public TestPaymentPage enterDiscoverCardNum() {
		type(BoxCardNumber, "6011000400000000");
		return this;
	}
	
	@FindBy(name="expdatemonth")
	WebElement selectExpMonth;
	public TestPaymentPage enterCardExpMonth(String expMonth) {
		type(selectExpMonth, expMonth);
		return this;
	}
	
	
	
	@FindBy(name="expdateyear")
	WebElement selectExpYear;
	public TestPaymentPage enterCardExpYear(String expYear) {
		type(selectExpYear, expYear);
		return this;
	}
	
	@FindBy(xpath="//fieldset[@class='cvv']/input")
	WebElement txtBoxCVV;
	public TestPaymentPage enterCardCVV(String cardCVV) {
		type(txtBoxCVV, cardCVV);
		return this;
	}
	
	@FindBy(xpath="//label[@for='rbtnPayPal']") 
	WebElement e_wallet;
	public TestPaymentPage clickeWallet() {
		click(e_wallet);
		return this;
	}
	
	@FindBy(id="submitCC")
	WebElement buttonConfirmCardDetails;
	public OrderCompletionPage clickConfirm() {
		click(buttonConfirmCardDetails);
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
	
	
	@FindBy(id="submitCC")
	WebElement buttonPartyConfirmCardDetails;
	public PartyOrderCompletionPage clickPartyConfirm() {
		click(buttonPartyConfirmCardDetails);
		try {
			return new PartyOrderCompletionPage(driver, Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@FindBy(id="submitPP")
	WebElement buttonConfirmPaypal;
	public PaypalSubmitPage clickPaypalConfirm() {
		click(buttonConfirmPaypal);
		try {
			return new PaypalSubmitPage(driver, Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	@FindBy(className="kit-success_wrap_border")
	WebElement kit_banner;
	public TestPaymentPage verifyKitSuccessPage() {
		/*
		 * try { WebElement body=driver.get().findElementByXPath("//body"); new
		 * WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
		 * (ExpectedConditions.attributeContains(body, "aria-busy", "false"))); }
		 * catch(Exception e) { e.printStackTrace(); }
		 */
		verifyDisplayed(kit_banner);
		if(driver.get().getCurrentUrl().contains("success"))
			reportStep("Success page for OE "+ driver.get().getCurrentUrl(), "pass");
		else
			reportStep("failure page for OE "+ driver.get().getCurrentUrl(), "fail");
		return this;
	}
	
	@FindBy(xpath="//input[@name='authenticate']")
	WebElement buttonauthenticate;
	public TestPaymentPage clickauthenticate() {
		try {
		click(buttonauthenticate);
		System.out.println("Authenticate button clicked");
		}catch(Exception e) {
			System.out.println("Authenticate button not find");
		}
		return this;
	}
	
	@FindBy(xpath="//div[@class='success-text']/h2")
	WebElement confirmOrderSuccess;
	public TestPaymentPage confirmOrderIsSuccess1() {
		//String successMessage=confirmOrderSuccess.getText(); 
		try {
			System.out.println("After submit card details  "+driver.get().getCurrentUrl());
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//section[@class='details']"))));
		/*	if(successMessage.contains("Thanks for shopping!")
					|| driver.get().getCurrentUrl().contains("onepage/success")) {
				reportStep(driver.get().getCurrentUrl()+"The order is successfully placed with text - " + successMessage, "pass");
			return this; */
			if(driver.get().getCurrentUrl().contains("onepage/success")){
				reportStep(driver.get().getCurrentUrl()+"The order is successfully placed", "pass");
				return this;
		}
			else {
				reportStep("The order failed to complete"+driver.get().getCurrentUrl(), "warning");
				return this;
			}
		}
		catch(TimeoutException | NullPointerException | NoSuchElementException e) {
			
			reportStep(driver.get().getCurrentUrl()+"Order Failed", "warning");
			return this;
		}
		
	}

	
}

