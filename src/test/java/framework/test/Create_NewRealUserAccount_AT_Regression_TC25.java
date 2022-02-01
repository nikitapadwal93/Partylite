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

public class Create_NewRealUserAccount_AT_Regression_TC25 extends Class_initEcomPrac{
	public String country;
	
	public Create_NewRealUserAccount_AT_Regression_TC25() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataLCO(@Optional String paramCountry) {
		tcName = "Create_NewRealUserAccount_AT_Regression_TC25";
		tcDescription = "Create a new real account & place the eShop order";
		category = "Smokes_"+paramCountry;
		authors = "Nikhil";
		testNodes = "Logged In customer order checkout";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "Regression_TC25";
		country = paramCountry;
		
	}
	
	
	@Test(groups= {"eShop", "Smokes"}, dataProvider="CustomerOrder")
	public void loggedInCustomerOrder(String fname, String lname,String email, String confemail, 
			String password,  String ContentForVerification,String SKU,String qty,
			String address1,String zipcode,String city, String state, String phone, 
			String PaymentType,String cardNumber,String expMonth,String expYear,String cardCVV) throws Exception{
		
		try {
			try {
			new LandingPage_PartyLite(driver, Test)
			//.closeDialogSignUpNewsLetter()
			.closeListrakNewsLetter()
			//.clickContinueShopping()
			.clickToAllowCookie()
			.clickSignin()
			.clickCreateAnAccount()
			.enterFirstname(fname)
			.enterLastname(lname)
			.enterEmail(email)
			.enterEmailConf(confemail)
			.enterPassword(password)
			.enterPasswordConf(password)
			.clickTermsConditions()
			.clickCreateAnAccount()
			/*.clickMenuExpansionLink()
			.clickOnDashboard()
			.closeSocialNewAcctDialog()
			.clickAddressBookLink()
			.clickNewAddressLink()
			.enterStreetAddress1(address1)
			.entercity(city)
			.enterPostalCode(zipcode)
			.enterTelephone(phone)
			.clickSearchAndValidate()
			.selectAddressFormat()
			.clickSaveAddressLink()*/
			.clickBtnSearch()
			.enterSKU(SKU)
			.clickProductImage()
			.enterQty(qty)
			.clickBtnAddToCart()
			.clickBtnMiniCart()
			.clickBtnViewBasket()
			//.typeCouponCode(coupon)
			//.clickCouponApplyButton()
			//.verifyCouponApplyMessage()
			.clickGoToCheckout()
			.enterCustomerAddress1(address1)
			.enterCustomerZipCode(zipcode)
			.enterCustomerCity(city)
			.enterCustomerTelephone(country, phone)
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
		    .clickNextButtonGuest()
			.selectAddressFormat()
			.clickNextProceedToPayment()
			//.verifyBillingAddress()
			//.clickPaymentType()
			//.selectTypeOfPaymentpaypal()
			.clickAcceptTerms()
			.selectSubscribeCheckox()
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
