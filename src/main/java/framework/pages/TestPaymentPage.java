package framework.pages;


import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
	
}

