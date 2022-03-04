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

public class Guest_CreateAccountSucccessPage_Regression_TC22 extends Class_initEcomPrac{
	public String country;
	
	public Guest_CreateAccountSucccessPage_Regression_TC22() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataGOGC(@Optional String paramCountry) {
		tcName = "Regression_TC-22: GuestOrderApply_Coupon_GC_RegressionTC11";
		tcDescription = "placing order in eShop by guest (Apply Coupon + GC in the cart page)";
		category = "Smokes_"+paramCountry;
		authors = "Nikhil";
		testNodes = "guest order in eshop";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "Regression_TC22";
		country = paramCountry;
		
	}
	
	@Test(groups= {"eShop", "Smokes"}, dataProvider="GuestOrder")
	public void guestOrderApplyGC(String SKU,String SKU2,String SKU3, 
			String GCNumber,String GCPIN, String email,String firstname,
			String lastname,String address1,String zipcode,String city, String phone, 
			String PaymentType, String cardNumber,String expMonth,String expYear,String cardCVV,
			String email2, String confemail,String password ){
		
		try {
			try {
			new LandingPage_PartyLite(driver, Test)
			
			//.closeDialogSignUpNewsLetter()
			.closeListrakNewsLetter()
			.clickToAllowCookie()
			.clickBtnSearch()
			.enterSKU(SKU)
			.clickProductImage()
			//.enterQty(qty)
			.clickBtnAddToCart()
			.clickBtnSearch()
			.enterSKU(SKU2)
			.clickProductImage()
			.clickBtnAddToCart()
			.clickBtnSearch()
			.enterSKU(SKU3)
			.clickProductImage()
			.clickBtnAddToCart()
			.clickBtnMiniCart()
			.clickBtnViewBasket()
		 /* .bodyContainer()
			.typeCouponCode(coupon_code)
			.clickCouponApplyButton()
			.bodyContainer()
			.verifyCouponApplyMessage() */
			.typeGiftCardNumber(country, GCNumber)
			.typeGiftCardPIN(country, GCPIN)
			.clickApplyButtonFR()
			.verifyGiftAddedMessage() 
			.clickGoToCheckout()
			.enterCustomerEmail(email)
			.enterCustomerFirstName(firstname)
			.enterCustomerLastName(lastname)
			.enterCustomerAddress1(address1)
			//.enterCustomerAddress2(address2)
			//.enterCustomerAddress3(address3)
			.enterCustomerZipCode(zipcode)
			.enterCustomerCity(city)
			//.removeExtraDots(zipcode)
			.enterCustomerTelephone(country, phone)
			//.selectState(state)
			//.clickDeliveryTypeBox()
			//.chooseHomeDeliveryMethod()
			.clickGuestNextButton()
			.selectAddressFormat()
			.clickNextProceedToPayment()
			//.selectTypeOfPayment(PaymentType)
			.clickAcceptTerms()
			.selectSubscribeCheckox()
			.clickPlaceOrderButton()
		/*	.createAccountSuccessPage()
			.enterEmailConf(confemail)
			.enterPassword(password)
			.enterPasswordConf(password)
			.clickTermsConditions()
			.clickCreateAnAccount(); */
			.enterCardNumber(cardNumber)
			.enterCardExpMonth(expMonth)
			.enterCardExpYear(expYear)
			.enterCardCVV(cardCVV)
			.getOrderNumber()
			.clickConfirm()
			.clickAuthenticate()
			.confirmOrderIsSuccess()
			.createAccountSuccessPage()
			.enterEmail(email2)
			.enterEmailConf(confemail)
			.enterPassword(password)
			.enterPasswordConf(password)
			.clickTermsConditions()
			.clickCreateAnAccount()
			.verifyaccountCreatedMessage();			
			
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
	
	@DataProvider(name="GuestOrder")
	public  Object[][] getData(){
		try {
			return ExcelDataReader.getData(ExcelFileName, sheetName);		
		}catch(NullPointerException e) {
			reportStep("Excel sheet or file is not available.","fail", false);
			return null;
		}
}
	
}
