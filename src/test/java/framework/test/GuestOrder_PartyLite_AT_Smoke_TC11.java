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

//This test case creates an order and apply discount code at the checkout page
public class GuestOrder_PartyLite_AT_Smoke_TC11 extends Class_initEcomPrac{
	public String country;
	
	public GuestOrder_PartyLite_AT_Smoke_TC11() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataGO(@Optional String paramCountry) {
		tcName = "Smoke_TC-11: AT - PWS - eShop - Anonymous Checkout";
		tcDescription = "placing order in eShop by guest";
		category = "Smokes_"+paramCountry;
		authors = "Shashwat";
		testNodes = "guest order in eshop";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "Smoke_TestCase_11";
		country = paramCountry;
		
	}
	
	@Test(groups= {"eShop", "Smokes"}, dataProvider="GuestOrder")
	public void createGuestOrder(String SKU1, String SKU2,String SKU3, String SKU4, String qty, String coupon,String email,String firstname,
			String lastname,String address1,String address2,String zipcode,
			String city,String phone, String PaymentType, String cardNumber,
			String expMonth,String expYear,String cardCVV){
		
		try {
			
			try {
			
			new LandingPage_PartyLite(driver, Test)
			
			//.closeDialogSignUpNewsLetter()
			.closeListrakNewsLetter()
			.clickToAllowCookie()
			.clickBtnSearch()
			.enterSKU(SKU1)
			.clickProductImage()
			.clickBtnAddToCart()
			.clickBtnSearch()
			.enterSKU(SKU4)
			.clickProductImage()
			.clickBtnAddToCart()
			.clickBtnSearch()
			.enterSKU(SKU2)
			.clickProductImageForTwoResults()
			.clickBtnAddToCart()
			.clickBtnSearch()
			.enterSKU(SKU3)
			.clickProductImageForTwoResults()
			.enterQty(qty)
			.clickBtnAddToCart()
			.enterEmailtoSubscribeFooter(email)
			.clickFooterSubscribeButton()
			.clickBtnMiniCart()
			.clickBtnViewBasket()
			.typeCouponCode(coupon)
			.clickCouponApplyButton()
			.clickGoToCheckout()
			.enterCustomerEmail(email)
			.enterCustomerFirstName(firstname)
			.enterCustomerLastName(lastname)
			.enterCustomerAddress1(address1)
			.enterCustomerAddress2(address2)
			//.enterCustomerAddress3(address3)
			.enterCustomerZipCode(zipcode)
			.removeExtraDots(zipcode)
			.enterCustomerCity(city)
			.enterCustomerTelephone(country, phone)
			//.selectState(state)
			.clickDeliveryTypeBox()
			.chooseDeliveryType()
			.clickGuestNextButton()
			.selectAddressFormat()
			.clickNextProceedToPayment()
			.selectTypeOfPayment(PaymentType)
			.clickAcceptTerms()
			//.selectSubscribeCheckox()
			.clickPlaceOrderButton()
			.enterCardNumber(cardNumber)
			.enterCardExpMonth(expMonth)
			.enterCardExpYear(expYear)
			.enterCardCVV(cardCVV)
			.getOrderNumber()
			.clickConfirm()
			.clickAuthenticate()
			.confirmOrderIsSuccess();
//			.getOrderEmail()
//			.getOrderNumber()
//			.openOrderGuestEmail()
//			.clickGoButton()
//			.eShopOrderSuccessMail(country)
//			.verifyOrderNumberInEmail();
			
			} catch(RuntimeException e) {
				System.out.println("Test case name =  " +getClass().getName());
				e.printStackTrace();
				reportStep("The test encountered an exception.","warning");
				
			}
			
			
			
		} catch (FileNotFoundException e) {
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
