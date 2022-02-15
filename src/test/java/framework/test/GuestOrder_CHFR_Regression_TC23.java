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
public class GuestOrder_CHFR_Regression_TC23 extends Class_initEcomPrac{
	public String country;
	
	public GuestOrder_CHFR_Regression_TC23() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataGO(@Optional String paramCountry) {
		tcName = "Guest Order for CH-FR market";
		tcDescription = "placing order in eShop by guest";
		category = "Smokes_"+paramCountry;
		authors = "Nikita";
		testNodes = "guest order in eshop";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "Regression_TC23";
		country = paramCountry;
		
	}
	
	@Test(groups= {"eShop", "Smokes"}, dataProvider="GuestOrder")
	public void createGuestOrder(String SKU1, String SKU2, String email,String firstname, String lastname,String address1,String address2,String zipcode,
			String city, String phone, String cardNumber, String expMonth, String expYear, String cardCVV,
			String emailaddress, String confemailaddress, String password, String confpassword) throws InterruptedException
	{
		
		try {
			
			try {
			
			new LandingPage_PartyLite(driver, Test)
			
			//.closeDialogSignUpNewsLetter()
			.closeListrakNewsLetter()
			.clickToAllowCookie()
			.enterEmailtoSubscribeFooter(email)
			.clickFooterSubscribeButton()
			.clickContinuebutton_CH()
			.clickBtnSearch()
			.enterSKU(SKU1)
			.clickProductImage()
			.clickBtnAddToCart()
			.clickBtnSearch()
			.enterSKU(SKU2)
			.clickProductImage()
			.clickBtnAddToCart()
			.clickBtnMiniCart()
			.clickBtnViewBasket()
			.clickGoToCheckout()
			.enterCustomerEmail(email)
			.enterCustomerFirstName(firstname)
			.enterCustomerLastName(lastname)
			.enterCustomerAddress1(address1)
			.enterCustomerAddress2(address2)
			.enterCustomerZipCode(zipcode)
			.enterCustomerCity(city)
			.enterCustomerTelephone(country, phone)
			.clickDeliveryTypeBox()
			.chooseDeliveryType()
			.clickGuestNextButton()
			.selectAddressFormat()
			.clickNextProceedToPayment()
			//.selectTypeOfPayment(PaymentType)
			.clickAcceptTerms()
			.clickPlaceOrderButton()
			.enterCardNumber(cardNumber)
			.enterCardExpMonth(expMonth)
			.enterCardExpYear(expYear)
			.enterCardCVV(cardCVV)
			.getOrderNumber()
			.clickConfirm()
			.clickAuthenticate()
			.confirmOrderIsSuccess()
			.clickCreateAccButton() 
			.enterEmailaddress1(emailaddress)
			.enterEmailConfaddress2(confemailaddress)
			.enterPassword(password)
			.enterPasswordConf(confpassword)
			.clickTermsConditions()
			.clickCreateAnAccount()
			.confirmRegistrationText(country);
			
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
