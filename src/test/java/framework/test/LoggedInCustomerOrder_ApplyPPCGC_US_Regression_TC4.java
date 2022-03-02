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

public class LoggedInCustomerOrder_ApplyPPCGC_US_Regression_TC4 extends Class_initEcomPrac{
	public String country;
	
	public LoggedInCustomerOrder_ApplyPPCGC_US_Regression_TC4() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataLCO(@Optional String paramCountry) {
		tcName = "Regression_TC-4: US -Corporate Site -Apply PPC_GC- Logged in Checkout";
		tcDescription = "placing order in corporate site by a customer";
		category = "Smokes_"+paramCountry;
		authors = "Saravanan";
		testNodes = "Logged In customer order checkout by Applying PPC and GC";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "Regression_TC4";
		country = paramCountry;
		
	}
	
	

	@Test(groups= {"eShop", "Regression"}, dataProvider="CustomerOrder")
	public void loggedInCustomerOrder(String email,String password, String ContentForVerification,
			String SKU,String qty,String PaymentType,String cardNumber,String expMonth,String expYear,String cardCVV,
			String GCNumber_1,String GCPIN_1,String GCNumber_2,String GCPIN_2){
		
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
				.enterSKU(SKU)
				.clickProductImage()
				//.enterQty(qty)
				.clickBtnAddToCart()
				.clickBtnMiniCart()
				.clickBtnViewBasket()
				.typeGiftCardNumber(country, GCNumber_1)
				.typeGiftCardPIN(country, GCPIN_1)
				.clickApplyButton()
				.verifyGiftAddedMessage()
				.typeGiftCardNumber2(country, GCNumber_2)
				.typeGiftCardPIN2(country, GCPIN_2)
				.clickApplyButton()
				.verifyGiftAddedMessage()
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
