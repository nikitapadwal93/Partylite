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

public class LoggedInCustomerOrder_PartyLite_Smoke_TC8 extends Class_initEcomPrac{
	public String country;
	
	public LoggedInCustomerOrder_PartyLite_Smoke_TC8() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataLCO(@Optional String paramCountry) {
		tcName = "Smoke_TC-8: DE- DE- CWS - eShop";
		tcDescription = "placing order in eShop by a customer";
		category = "Smokes_"+paramCountry;
		authors = "Shashwat";
		testNodes = "Logged In customer order checkout";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "Smoke_TestCase_8";
		country = paramCountry;
		
	}
	
	
	@Test(groups= {"eShop", "Smokes"}, dataProvider="CustomerOrder")
	public void loggedInCustomerOrder(String subscribername, String emailsubscribe, String email,String password, String ContentForVerification,
			String SKU,String qty,String address1,String address2,String address3,
			String zipcode,String city, String state, String phone,String PaymentType, String cardNumber,
			String expMonth,String expYear,String cardCVV){
		
		try {
			try {
			new LandingPage_PartyLite(driver, Test)
			//.closeDialogSignUpNewsLetter()
			//.closeListrakNewsLetter()
			.enterNewsletterName(subscribername)
			.enterNewsletterEmail(emailsubscribe)
			.clickNewsletterbtn()
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
			.clickGoToCheckout()
			.clickOnSavedAddress()
			.clickDeliveryTypeBox()
			.choosePickUpPointDeliveryMethod()
			.clickNextButtonWoPUP()
			.pupErrorMsg()
			.clickParcelShop()
			.clickZoomOut()
			.clickZoomOut()
			.clickZoomOut()
			.clickStoreImg()
			.selectStoreImg()
		    //.clickNextButtonGuest()
			//.selectAddressFormat()
			.clickNextProceedToPayment()
			.clickBacktoOrder()
			.clickGoToCheckout()
			.clickParcelShop()
			.clickZoomOut()
			.clickZoomOut()
			.clickZoomOut()
			.clickStoreImg()
			.selectStoreImg()
			//.clickAddress()
			//.clickGuestNextButton()
			//.selectAddressFormat()
			.clickNextProceedToPayment()
			.selectTypeOfPayment(PaymentType)
			.clickAcceptTerms()
			.clickPlaceOrderButton()
			.enterCardNumber(cardNumber)
			.enterCardExpMonth(expMonth)
			.enterCardExpYear(expYear)
			.enterCardCVV(cardCVV)
			.getOrderNumber()
			.clickConfirm()
			.clickAuthenticate()
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
