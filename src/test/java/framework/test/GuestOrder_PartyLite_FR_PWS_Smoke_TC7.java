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
public class GuestOrder_PartyLite_FR_PWS_Smoke_TC7 extends Class_initEcomPrac{
	public String country;
	
	public GuestOrder_PartyLite_FR_PWS_Smoke_TC7() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataGO(@Optional String paramCountry) {
		tcName = "Smoke_TC-7: FR - PWS - eShop";
		tcDescription = "placing order in eShop by guest";
		category = "Smokes_"+paramCountry;
		authors = "Shashwat";
		testNodes = "Guest Order PWS";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "Smoke_TestCase_7";
		country = paramCountry;
		
	}
	
	@Test(groups= {"eShop", "Smokes"}, dataProvider="GuestOrder")
	public void createGuestOrder(String SKU,String qty,String email,String firstname,
			String lastname,String address1,String address2,String address3,
			String zipcode,String city, String phone, String addressRC, String cityRC, String zipRC, String PaymentType, String cardNumber,
			String expMonth,String expYear,String cardCVV){
		
		try {
			
			try {
			
			new LandingPage_PartyLite(driver, Test)
			
			//.closeDialogSignUpNewsLetter()
			.closeListrakNewsLetter()
			.clickToAllowCookie()
			.clickBtnSearch()
			.enterSKU(SKU)
			//.clickSearchButton()
			.clickProductImage()
			//.enterQty(qty)
			.clickBtnAddToCart()
			.clickBtnMiniCart()
			.clickBtnViewBasket()
			//.typeCouponCode(coupon)
			//call voucher entry data
			.clickGoToCheckout()
			.enterCustomerEmail(email)
			.enterCustomerFirstName(firstname)
			.enterCustomerLastName(lastname)
			.enterCustomerAddress1(address1)
			.enterCustomerAddress2(address2)
			.enterCustomerAddress3(address3)
			.enterCustomerZipCode(zipcode)
			.enterCustomerCity(city)
			.removeExtraDots(zipcode)
			.enterCustomerTelephone(country, phone)
			//.selectState(state)
			.clickDeliveryTypeBox()
			.chooseRCDeliveryMethod()
			.enterRCAddress(addressRC)
			.enterRCZipCode(zipRC)
			.enterRCCity(cityRC)
			//.removeExtraDots(zipcode)
			.clickRCbutton()
			.clickRClink()
			.clickNextButtonWoPUP()
			.clickGuestNextButton()
			.selectAddressFormat()
			.clickNextProceedToPayment()
			.clickBacktoOrder()
			//.enterEmailtoSubscribeFooter(email)
			//.clickFooterSubscribeButton()
			.clickGoToCheckout()
			.clickRCbutton()
			.clickRClink()
			.clickNextButtonWoPUP()
			.clickGuestNextButton()
			.selectAddressFormat()
			.clickNextProceedToPayment()
			.selectTypeOfPayment(PaymentType)
			/*.selectSameasShipping()
			.enterCustomerFirstName(firstname)
			.enterCustomerLastName(lastname)
			.enterCustomerAddress1(addressbill)
			.enterCustomerZipCode(zipbill)
			.enterCustomerCity(citybill)
			.enterCustomerTelephone(country, phone)
			.selectState(statebill)*/
			//.clickUpdateAddressButton()
			.clickAcceptTerms()
			//.selectSubscribeCheckox()
			.clickPlaceOrderButton()
			.clickeWallet()
			.clickPaypalConfirm()
			.clickWorldlineAccept()
			.getOrderNumber()
			//.clickConfirm()
			//.clickAuthenticate()
			//.enterEmailtoSubscribeFooter(email)
			//.clickFooterSubscribeButton()
			//.clickContinue()
			//.enterEmailtoSubscribeFooter(email)
			//.clickFooterSubscribeButton()
			//.clickContinueShopping()
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
