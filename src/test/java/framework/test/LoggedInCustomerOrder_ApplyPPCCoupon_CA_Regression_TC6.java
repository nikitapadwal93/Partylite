package framework.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import framework.appInit.Class_initEcomPrac;
import framework.pages.LandingPage_PartyLite;
import framework.utils.ExcelDataReader;

public class LoggedInCustomerOrder_ApplyPPCCoupon_CA_Regression_TC6 extends Class_initEcomPrac{
	public String country;
	
	public LoggedInCustomerOrder_ApplyPPCCoupon_CA_Regression_TC6() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataLCO(@Optional String paramCountry) {
		tcName = "Regression_TC-6: CA -Corporate Site -Apply Coupon and PPC- Logged in Checkout";
		tcDescription = "placing order in Corporate site by a customer";
		category = "Regression_"+paramCountry;
		authors = "Saravanan";
		testNodes = "Logged In customer order checkout by Applying Coupon and PPC";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "Regression_TC6";
		country = paramCountry;
		
	}
	
	
	@Test(groups= {"eShop", "Regression"}, dataProvider="CustomerOrder")
	public void loggedInCustomerOrder(String email,String password, String ContentForVerification,
			String SKU1,String SKU2,String qty,String PaymentType,String cardNumber,String expMonth,String expYear,String cardCVV,
			String GCNumber,String GCPIN,String coupon_code){
		
		try {
			try {
				new LandingPage_PartyLite(driver, Test)
				//.closeDialogSignUpNewsLetter()
				.closeListrakNewsLetter()
				//.clickContinueShopping()
				.clickToAllowCookie()
				.clickSignin()
				.enterEmailName(email)
				.enterPassword(password)
				.btnClickSignin()
				//.closeSocialNewAcctDialog()
				.verifyUserLogin(country, ContentForVerification)			
				.clickBtnSearch()
				.enterSKU(SKU1)
				.clickProductImage()
				//.enterQty(qty)
				.clickBtnAddToCart()
				.clickBtnSearch()
				.enterSKU(SKU2)
				.clickProductImage()
				//.enterQty(qty)
				.clickBtnAddToCart()
				.clickBtnMiniCart()
				.clickBtnViewBasket()
				.typeGiftCardNumber(country, GCNumber)
				.typeGiftCardPIN(country, GCPIN)
				.clickApplyButton()
				.verifyGiftAddedMessage()
				.typeCouponCode(coupon_code)
				.clickCouponApplyButton()
				.verifyCouponApplyMessage()
				.clickGoToCheckout()
				//.clickOnSavedAddress()
				.clickDeliveryTypeBox()
				.chooseDeliveryType()
				//.clickGuestNextButton()
				//.selectAddressFormat()
				.clickNextProceedToPayment()
				//.selectTypeOfPayment(PaymentType)
				.clickAcceptTerms()
				//.selectSubscribeCheckox()
				.clickPlaceOrderButton()
				.enterCardNumber(cardNumber)
				.enterCardExpMonth(expMonth)
				.enterCardExpYear(expYear)
				.enterCardCVV(cardCVV)
				.getOrderNumber()
				.clickConfirm()
				.confirmOrderIsSuccess();
			
			
			System.out.println("Order is placed successfully");
			
			
			} catch(RuntimeException e) {
				System.out.println("Test case name =  " +getClass().getName());
				e.printStackTrace();
				reportStep("The test encountered an exception.","warning");
				
			}
	}
	catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
			
		
	}
	
	@DataProvider(name="CustomerOrder")
	public  Object[][] getData(){
		try {
			return ExcelDataReader.getData(ExcelFileName, sheetName);		
		}catch(NullPointerException e) {
			reportStep("Excel sheet or file is not available.","fail", false);
			return null;
		}
}
	
}
