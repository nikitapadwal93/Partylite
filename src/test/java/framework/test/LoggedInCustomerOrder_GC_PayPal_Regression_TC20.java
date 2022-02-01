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

public class LoggedInCustomerOrder_GC_PayPal_Regression_TC20 extends Class_initEcomPrac{
	public String country;
	
	public LoggedInCustomerOrder_GC_PayPal_Regression_TC20() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataLCO(@Optional String paramCountry) {
		tcName = "LoggedInCustomerOrder_GC_PayPal_Regression_TC20";
		tcDescription = "placing order in eShop by a customer (Apply GC + Paypal Payment)";
		category = "Smokes_"+paramCountry;
		authors = "Nikhil";
		testNodes = "Logged In customer order checkout";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "Regression_TC20";
		country = paramCountry;
		
	}
	
	
	@Test(groups= {"eShop", "Smokes"}, dataProvider="CustomerOrder")
	public void loggedInCustomerOrder(String subs_email,
			String email, String password,  String ContentForVerification,
			String SKU,String qty,String GCNumber, String GCPIN,
			String PaymentType) throws Exception{
		
		try {
			try {
			new LandingPage_PartyLite(driver, Test)
			//.closeDialogSignUpNewsLetter()
			.closeListrakNewsLetter()
			//.clickContinueShopping()
			.clickToAllowCookie()
			.enterEmailtoSubscribeFooter(subs_email)
			.clickFooterSubscribeButton()
			.clickSignin()
			.enterEmailName(email)
			.enterPassword(password)
			.btnClickSignin()
			.closeSocialNewAcctDialog()
			.verifyUserLogin(country, ContentForVerification)
			.clickBtnSearch()
			.enterSKU(SKU)
			.clickProductImage()
			.enterQty(qty)
			.clickBtnAddToCart()
			.clickBtnMiniCart()
			.clickBtnViewBasket()
			.typeGiftCardNumber(country, GCNumber)
			.typeGiftCardPIN(country, GCPIN)
			.clickApplyButton()
			.typeGiftCardNumber2(country, GCNumber)
			.typeGiftCardPIN2(country, GCPIN)
			.clickApplyButton()
			.verifyGiftAddedMessage()
			.clickGoToCheckout()
			.clickOnSavedAddress()
			.clickDeliveryTypeBox()
			.choosePickUpPointDeliveryMethod()
			//.chooseParcelShop()
			.clickNextButtonWoPUP()
			.pupErrorMsg()
			.chooseParcelShop()
			.clickZoomOut()
			.clickStoreImg()
			.selectStoreImg()
			//.clickNextButtonGuest()
			//.selectAddressFormat()
			.clickNextProceedToPayment()
			//.clickPaymentType()
			.clickAcceptTerms()
			//.selectSubscribeCheckox()
			.clickPlaceOrderButton()
			//.selectTypeOfPaymentpaypal()
			//.selectTypeOfPayment(PaymentType)
			//.clickAcceptTerms()
			//.selectSubscribeCheckox()
			//.clickPlaceOrderButton()
			.clickeWallet()
			.getOrderNumber()
			.clickPaypalConfirm()
			//.clickWorldlineAccept()
			.loginPaypal()
			.payNow()
			//.clickConfirm()
			//.clickAuthenticate()
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
