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
public class GuestOrder_DE_RegressionTC19 extends Class_initEcomPrac{
	public String country;
	
	public GuestOrder_DE_RegressionTC19() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataGO(@Optional String paramCountry) {
		tcName = "Regression_TC-19: DE -CWS-Site-GuestOrder";
		tcDescription = "placing order in eShop by guest";
		category = "Smokes_"+paramCountry;
		authors = "Saravanan";
		testNodes = "guest order in eshop";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "Regression_TC19";
		country = paramCountry;
		
	}
	
	@Test(groups= {"eShop", "Regression"}, dataProvider="GuestOrder")
	public void createGuestOrderLT(String SKU1,String SKU2,String SKU3,
			String SKU4,String SKU5,String qty,
			String email,String firstname,String lastname,
			String address1,String address2,String address3,
			String zipcode,String city,String state,String phone,
			String PaymentType, String cardNumber,
			String expMonth,String expYear,String cardCVV){
		
		try {
			
			try {
			
			new LandingPage_PartyLite(driver, Test)
			
			//.closeDialogSignUpNewsLetter()
			.enterNamePopup()
			.enterEmailPopup()
			.LtkSubmitpopup()
			//.closeListrakNewsLetter()
			.clickToAllowCookie()
			.clickBtnSearch()
			.enterSKU(SKU1)
			.clickProductImage()
			//.enterQty(qty1)
			.clickBtnAddToCart()
			.clickBtnSearch()
			.enterSKU(SKU2)
			.clickProductImage()
			//.enterQty(qty2)
				.clickBtnAddToCart()
			.clickBtnSearch()
			.enterSKU(SKU3)
			.clickProductImage()
			//.enterQty(qty3)
			.clickBtnAddToCart()
			.clickBtnSearch()
			.enterSKU(SKU4)
			.clickProductImage()
			//.enterQty(qty4)
			.clickBtnAddToCart()
			.clickBtnSearch()
			.enterSKU(SKU5)
			.clickProductImage()
			.clickBtnAddToCart()
			.clickBtnMiniCart()
			.clickBtnViewBasket()
			.bodyContainer()
			.clickGoToCheckout()
			.enterCustomerEmail(email)
			.enterCustomerFirstName(firstname)
			.enterCustomerLastName(lastname)
			.enterCustomerAddress1(address1)
			//.enterCustomerAddress2(address2)
			//.enterCustomerAddress3(address3)
			.enterCustomerCity(city)
			.enterCustomerZipCode(zipcode)
			.enterCustomerTelephone(country, phone)
			//.selectState(state)
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
