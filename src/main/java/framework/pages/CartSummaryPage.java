package framework.pages;


import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import framework.appInit.Class_initEcomPrac;

public class CartSummaryPage extends Class_initEcomPrac {


	CartSummaryPage(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
		super();
		PageFactory.initElements(driver.get(), this);
		this.driver = driver;
		this.Test = Test;

	}
	
	public CartSummaryPage bodyContainer() {
		try {
			WebElement body = driver.get().findElementByXPath("//body");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			new WebDriverWait(driver.get(), reducedTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(body, "aria-busy", "false")));
			System.out.println("body container pass in cart page");
		} catch (Exception e ) {
			System.out.println("body container catch cart page");
		}
		return this;
	}
	
	public CartSummaryPage loaderCheck() {
		try {
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			//System.out.prinln("Waiting for loader to finish page rendering.");
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed
					(ExpectedConditions.attributeContains(loader, "style", "none")));
			System.out.println("loader pass");
		} catch (Exception e ) {
			System.out.println("loader catch");
		}
		return this;
	}
	
	@FindBy(xpath = "//*[@id='block-discount-heading']") //
	WebElement coupon_translation1;
	public CartSummaryPage verifyCoupon1_Translation() {
	
		boolean coupontxt= verifyExactText(coupon_translation1, "Apply Coupon");
		
		if(!coupontxt) {
			reportStep("Coupon translaiton is incorrect= "+coupon_translation1 ,"FAIL");	
			System.out.println("Coupon text is incorrect");
			}
		else
		System.out.println("Coupon text is correct");
		reportStep("Coupon translaiton is correct= "+coupon_translation1 ,"PASS");
	
		return this;
	}
	
	@FindBy(xpath = "//*[@class='field']/label")
	WebElement coupon_translation2;
	public CartSummaryPage verifyCoupon2_Translation() {
		verifyExactText(coupon_translation2, "Enter Coupon");
		reportStep("Coupon translaiton is correct= "+coupon_translation2 ,"PASS");
		return this;
	}

	@FindBy(id="coupon_code_fake")
	WebElement txtboxCouponCode;
	public CartSummaryPage typeCouponCode(String coupon) {
		type(txtboxCouponCode, coupon);
		return this;

	}
	
	@FindBy(id="coupon_code_fake")
	WebElement txtbox_PPC_Coupon;
	public CartSummaryPage typePPCCoupon(String valid_coupon) {
		type(txtbox_PPC_Coupon, valid_coupon);
		return this;

	}
	
	@FindBy(id = "coupon_code_fake")
	WebElement enterFREECoupon;
	public CartSummaryPage enterFREECoupon() {
		//Thread.sleep(2000);
		try {
			WebElement bodyContainer = driver.get().findElementByXPath("//body[@data-container='body']");
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.refreshed(ExpectedConditions.attributeToBe(bodyContainer, "aria-busy", "false")));
			System.out.println("Aria busy success");
		}
	
		catch (Exception e) {
			e.printStackTrace();
				}
		try {
		verifyDisplayed(enterFREECoupon);
		//FREEENROLL : This free-enrollment coupon for pexdev-server
		if (driver.get().getCurrentUrl().contains("https://pexdev.partylite.co.uk") || 
			driver.get().getCurrentUrl().contains("https://pexdev.partylite.com") ||
			driver.get().getCurrentUrl().contains("https://pexdev.partylite.com.au")||
			driver.get().getCurrentUrl().contains("https://pexdev.partylite.ca"))
			type(enterFREECoupon, "FREEENROLL");
		//Below free-enrollment coupons for staging-server
		else if (driver.get().getCurrentUrl().contains("https://mcstaging.partylite.co.uk") || driver.get().getCurrentUrl().contains("https://mcstaging.partylite.com.au"))
			type(enterFREECoupon, "FAITH100");
		
		else if (driver.get().getCurrentUrl().contains("https://mcstaging.partylite.com"))
				type(enterFREECoupon, "KRISTIN100");
		
		else if (driver.get().getCurrentUrl().contains("https://mcstaging.partylite.ca"))
				type(enterFREECoupon, "CA_KRISTIN100");
		//Below free-enrollment coupons for preprod-server
		else if (driver.get().getCurrentUrl().contains("https://mcpreprod.partylite.com.au") || driver.get().getCurrentUrl().contains("https://mcpreprod.partylite.co.uk"))
			type(enterFREECoupon, "SHAREIT");
		
		else if (driver.get().getCurrentUrl().contains("https://mcpreprod.partylite.com") || driver.get().getCurrentUrl().contains("https://mcpreprod.partylite.ca"))
			type(enterFREECoupon, "NAI90K66Y9");
		
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	@FindBy(xpath="//button[@class='action apply primary']")
	WebElement apply_btn_coupon;
	public CartSummaryPage clickCouponApplyButton() {
		bodyContainer();
		click(apply_btn_coupon);
		return this;

	}

	//@FindBy(xpath = "//div[@data-ui-id='message-success']")
	@FindBy(xpath = "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")
	WebElement coupon_apply_msg;
	public CartSummaryPage verifyCouponApplyMessage() {
		String coupon_validation= coupon_apply_msg.getText();
		System.out.println(coupon_validation);
		//verifyPartialText(coupon_apply_msg, "You used coupon code");
		reportStep("Coupon applied successfully= "+coupon_validation ,"PASS");
		return this;
	}
	
	@FindBy(xpath = "//div[@data-ui-id='message-error']")
	WebElement coupon_invalid_msg;
	public CartSummaryPage verifyCouponInvalidMessage() {
		verifyPartialText(coupon_invalid_msg, "The coupon code");
		reportStep("Coupon is not applied= "+coupon_invalid_msg ,"PASS");
		return this;
	}

	@FindBy(xpath="//td[@data-th='Discount']")
	WebElement discount;
	public CartSummaryPage verifyDiscount(String discount_amount) {
		System.out.println(discount.getText());

		boolean verifyPartialText = discount.getText().contains(discount_amount);

		if(!verifyPartialText)
			reportStep("The given data for the promotion is incorrect or the promotion is not active in the admin", "warning");
		else
			reportStep("The discount is applied correctly", "PASS");

		return this;

	}

	@FindBy(xpath="//div[text()[contains(., 'You added')]]")
	WebElement msgConfirmProductionAddition;
	public CartSummaryPage validateProductAdditionToCart() {
		verifyPartialText(msgConfirmProductionAddition, "You added");
		return this;
	}

	@FindBy(xpath="//li[@class='customer data']/child::a")
	WebElement txtCustName;
	public CartSummaryPage validateCustomername() {
		String HostName = getText(txtCustName);
		//System.out.prinln("The order has following hostname " + HostName);
		return this;
	}

	@FindBy(xpath="//li[@class='order data']/child::span")
	WebElement txtOrderType;
	public CartSummaryPage validateOrderType() {
		verifyPartialText(txtOrderType, "Paper Order");
		return this;

	}

	@FindBy(id="giftcard-code")
	WebElement txtboxGiftCardNumber;
	public CartSummaryPage typeGiftCardNumber(String Country, String gift_card_number) {
		gift_card_number= markets.get(Country).getProperty("GC1"); //multi-lingual support
		type(txtboxGiftCardNumber, gift_card_number);
		return this;

	}
	
	@FindBy(id="giftcard-code")
	WebElement GiftCardNumberfromExcel;
	public CartSummaryPage typeCardNumberExcel(String cardnum_readExcel) { //multi-lingual support
		type(GiftCardNumberfromExcel, cardnum_readExcel);
		return this;

	}
	
	@FindBy(id="giftcard-pin")
	WebElement GiftCardPINfromExcel;
	public CartSummaryPage typeCardPINExcel(String cardpin_readExcel) { //multi-lingual support
		type(GiftCardPINfromExcel, cardpin_readExcel);
		return this;
	}
	
	@FindBy(id="giftcard-code")
	WebElement txtboxGCNumber;
	public CartSummaryPage enterGCNumber(String gcno) {
		loaderCheck();
		bodyContainer();
		type(txtboxGCNumber, gcno);
		return null;	
	}
	
	@FindBy(xpath="//*[@id='giftcard-pin']")
	WebElement enterGCPin;
	public CartSummaryPage enterGCPinforCH(String gcpin) {
		//loaderCheck();
		//bodyContainer();
		type(enterGCPin, gcpin);
		return null;	
	}
	
	@FindBy(xpath="//button[@class='action add primary']")
	WebElement clickGCApply;
	public CartSummaryPage clickGCApplyButton() {
		click(clickGCApply);
		return null;	
	}

	@FindBy(id="giftcard-pin")
	WebElement txtboxGiftCardPIN;
	public CartSummaryPage typeGiftCardPIN(String Country, String gift_card_pin) {
		gift_card_pin= markets.get(Country).getProperty("GC2"); //multi-lingual support
		type(txtboxGiftCardPIN, gift_card_pin);
		return this;
	}
	
	@FindBy(id="giftcard-code")
	WebElement txtboxGiftCardNumber2;
	public CartSummaryPage typeGiftCardNumber2(String Country, String gift_card_number) {
		gift_card_number= markets.get(Country).getProperty("GC3");
		type(txtboxGiftCardNumber, gift_card_number);
		return this;
	}
	
	@FindBy(id="giftcard-pin")
	WebElement txtboxGiftCardPIN2;
	public CartSummaryPage typeGiftCardPIN2(String Country, String gift_card_pin) {
		gift_card_pin= markets.get(Country).getProperty("GC4"); 
		type(txtboxGiftCardPIN, gift_card_pin);
		return this;
	}

	@FindBy(xpath="//*[@id=\"newsletter\"]")
	WebElement newsletterFooter;
	public CartSummaryPage enterEmailtoSubscribeFooter(String email) {
		type(newsletterFooter, email);
		return this;

	}
	
	@FindBy(xpath="//*[@id=\"newsletter-validate-detail\"]/div/div/div[1]/button")
	WebElement clickSubscribefromFooter;
	public CartSummaryPage clickFooterSubscribeButton() {
		click(clickSubscribefromFooter);
		return this;

	}

	
	@FindBy(xpath="//button[@value='Add Gift Card']")
	WebElement apply_btn;
	public CartSummaryPage clickApplyButton() {
		click(apply_btn);
		return this;

	}
	
	@FindBy(xpath="//button[@value=\"Ajouter un bon d'achat\"]")
	WebElement apply_btnFR;
	public CartSummaryPage clickApplyButtonFR() {
		click(apply_btnFR);
		return this;

	}

	//@FindBy(xpath="//div[contains(@data-ui-id, 'success')]")  //div[contains(@data-ui-id, 'success')]
	WebElement gc_apply_msg;
	public CartSummaryPage verifyGiftAddedMessage() {

		try {
			WebElement gc_apply_msg = driver.get().findElementByXPath("//div[contains(@data-ui-id, 'success')]");
			if(gc_apply_msg.isDisplayed()) {

				reportStep("Gift certificate validation was successful.","pass");
				return this;	
			}
			else {
				if(driver.get().findElementByXPath("//div[contains(@data-ui-id, 'error')]").isDisplayed())
					reportStep("Gift certificate details failed to validate. Check entered code/pin.","warning");

			}


		} catch(org.openqa.selenium.NoSuchElementException e) {
			reportStep("Gift certification validations could not be verified.", "info");
			return this;
		}
		return null;	
	}

	@FindBy(id="coupon_code_fake")
	WebElement entercouponforuk;
	public CartSummaryPage ukEnterCoupon() {
		verifyDisplayed(entercouponforuk);
		if(driver.get().getCurrentUrl().contains("https://pexdev.partylite.co.uk"))
			type(entercouponforuk, "FREEENROLL");
		else if(driver.get().getCurrentUrl().contains("https://magentoqa.partylite.co.uk"))
			type(entercouponforuk, "FAITH100");
		return this;
	}
	
	
	@FindBy(xpath="//button[@data-role='proceed-to-checkout']")
	WebElement btnGoToCheckout;
	public CheckoutPage clickGoToCheckout() {
		try {
			WebElement total_cart_page=driver.get().findElementByCssSelector("#cart-totals table .grand");
			new WebDriverWait(driver.get(), reducedTimeout).until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(total_cart_page)));
			System.out.println("Totals table wait success");
		}
		catch(Exception e) {
			System.out.println("Aria busy false check in cart page");
		}

		loaderCheck();
		try {
			new WebDriverWait(driver.get(), reducedTimeout).until(ExpectedConditions.elementToBeClickable(btnGoToCheckout));
			click(btnGoToCheckout);
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
	
	@FindBy(xpath ="//*[@class='qty-increase']")
	WebElement addProductQty;
	public CartSummaryPage addProductQty() {
		click(addProductQty);
		System.out.println("Clicked on + to add more qty");
		reportStep("Cicked on '+' to add more qty", "pass");
		return this;
	}
	
	@FindBy(xpath ="//*[@class='qty-increase']") //Added for AU-Regression TC_17. This can be used for all AFF-markets.
	WebElement addMultipleQty;
	public CartSummaryPage addMultipleProductQty(String count) {
		int Qty = Integer.parseInt(count);
		for (int i=1; i<Qty; i++)
			{
			click(addMultipleQty);
			}
		System.out.println("Added multiple quantities");
		reportStep("Added multiple quantities","pass");
		return this;
	}
	
	@FindBy(xpath ="//*[@id='form-validate']/div[2]/button")
	WebElement updateProductQty;
	public CartSummaryPage clickUpdateQty() {
		click(updateProductQty);
		System.out.println("Clicked on 'Update qty' button");
		reportStep("Clicked on 'Update qty' button", "pass");
	return this;
	}
	
	
	@FindBy(xpath ="//*[@id='shopping-cart-table']/tbody[2]/tr[2]/td/div/a")
	WebElement removeItem;
	public CartSummaryPage clickRemoveItem() {
		click(removeItem);
		System.out.println("Clicked on 'Remove item link");
		reportStep("Item has been removed from the cart", "pass");
		return this;
	}


	public CartSummaryPage cartPageLoader() {
		try {
			WebElement total_cart_page=driver.get().findElementByCssSelector("#cart-totals table .grand");
			new WebDriverWait(driver.get(), reducedTimeout).until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(total_cart_page)));
			System.out.println("Totals table wait success");
		}
		catch(Exception e) {
			System.out.println("Aria busy false check in cart page");
		}

		loaderCheck();
		return this;
	}
	
	@FindBy(xpath="//td[@data-th='Discount']")
	WebElement discount_row;
	public CartSummaryPage verifyDiscountRow() {
		verifyEnabled(discount_row);
		System.out.println(discount_row.getText());
		reportStep(discount_row.getText(), "pass");
		return this;
	}
	
	@FindBy(className ="rule-name")
	WebElement discount_rule_name;
	public CartSummaryPage verifyDiscountRuleName() {
		verifyEnabled(discount_rule_name);
		System.out.println(discount_rule_name.getText());
		reportStep(discount_rule_name.getText(), "pass");
		return this;
	}
	
	
	@FindBy(xpath="//button[@data-role]")
	WebElement btnProceedToCheckout;
	public CheckoutPage btnProceedToCheckout() {

		try {
			WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
			new WebDriverWait(driver.get(), waitTimeout).until
			(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(loader)));
			System.out.println("loader staleness check");
		}
		catch(Exception e) {
			e.printStackTrace();
		}


		try {
			WebElement bodyContainer = driver.get().findElementByXPath("//body[@data-container='body']");
			new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.
					refreshed(ExpectedConditions.attributeToBe(bodyContainer, "aria-busy", "false")));
			System.out.println("Aria busy success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement party_data = driver.get().findElementByClassName("quote-container");
		WebDriverWait party_data_wait=new WebDriverWait(driver.get(), waitTimeout);
		party_data_wait.until(ExpectedConditions.visibilityOf(party_data));
		System.out.println(party_data.getText());
		reportStep(party_data.getText(), "INFO");

		WebElement cart_totals = driver.get().findElementByCssSelector("#cart-totals table .grand");
		WebDriverWait display_wait=new WebDriverWait(driver.get(), reducedTimeout);
		display_wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(cart_totals)));
		reportStep(cart_totals.getText(), "INFO");

		WebDriverWait wait=new WebDriverWait(driver.get(), waitTimeout);
		wait.until(ExpectedConditions.elementToBeClickable(btnProceedToCheckout));

		try {
			click(btnProceedToCheckout);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}


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

	
	@FindBy(xpath="//*[@id=\"html-body\"]/div[7]/aside[3]/div[2]/footer/button/span")
	WebElement clickContinue;
	public ProductDescriptionPage clickContinueShopping() {
		click(clickContinue);
		return null;
	}

}