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

public class LoggedInCustomerOrder_CHPWS_GC_Coupon_Regression_TC24 extends Class_initEcomPrac{
	public String country;
	
	public LoggedInCustomerOrder_CHPWS_GC_Coupon_Regression_TC24() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataLCO(@Optional String paramCountry) {
		tcName = "LoggedInCustomerOrder_CHPWS_GC_Coupon_Regression_TC24";
		tcDescription = "placing order in eShop by a Loggedin customer (Apply GC + Coupon + CC Payment)";
		category = "Smokes_"+paramCountry;
		authors = "Shashwat";
		testNodes = "Logged In customer order checkout";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "Regression_TC24";
		country = paramCountry;
		
	}
	
	
	@Test(groups= {"eShop", "Smokes"}, dataProvider="CustomerOrder")
	public void loggedInCustomerOrder(String email, String password,String SKU1, String qty, String SKU2, 
			String SKU3,String coupon,String GCNumber, String GCPIN, String firstname, String lastname,String address1,
			String zipcode,String city,String phone, String cardNumber,
			String expMonth,String expYear,String cardCVV) throws Exception{
		
		try {
			try {
			new LandingPage_PartyLite(driver, Test)
			//.closeDialogSignUpNewsLetter()
			.closeListrakNewsLetter()
			//.clickContinueShopping()
			.clickToAllowCookie()
			.clickBtnSearch()
			.enterSKU(SKU1)
			.clickProductImage()
			.enterQty(qty)
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
			.bodyContainer()
			.typeCouponCode(coupon)
			.clickCouponApplyButton()
			.bodyContainer()
			.verifyCouponApplyMessage()
			.typeGiftCardNumber(country, GCNumber)
			.typeGiftCardPIN(country, GCPIN)
			.clickApplyButton()
			//.verifyGiftAddedMessage()
			.clickGoToCheckout()
			.enterCustomerEmail(email)
			.enterPasswordCheckout(password)
			//.btnClickSigninCheckout()
			//.enterCustomerFirstName(firstname)
			//.enterCustomerLastName(lastname)
			.enterCustomerAddress1(address1)
			.enterCustomerCity(city)
			.enterCustomerZipCode(zipcode)
			.enterCustomerTelephone(country, phone)
			.clickDeliveryTypeBox()
			.chooseDeliveryType()
			.clickGuestNextButton()
			.selectAddressFormat()
			.clickNextProceedToPayment()
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
			
			
			System.out.println("Order is placed successfully for CH PWS site");
			
			
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
