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

public class GuestOrderViaCategory_PartyLite extends Class_initEcomPrac{
	public String country;
	
	public GuestOrderViaCategory_PartyLite() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataGOVC(@Optional String paramCountry) {
		tcName = "GuestOrder";
		tcDescription = "placing order in eShop by guest";
		category = "Smokes_"+paramCountry;
		authors = "Anand";
		testNodes = "guest order in eshop";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "GuestOrderVC_PL";
		country = paramCountry;
		
	}
	
	@Test(groups= {"eShop", "Smokes"}, dataProvider="GuestOrder")
	public void createGuestOrder(String qty,String email,String firstname,
			String lastname,String address1,String address2,String address3,
			String zipcode,String city,String phone,String state,String PaymentType, String cardNumber,
			String expMonth,String expYear,String cardCVV){
		
		try {
			
			try {
			
			new LandingPage_PartyLite(driver, Test)
			
			//.closeDialogSignUpNewsLetter()
			.clickToAllowCookie()
			.clickOnlineShop()
			.clickFirstCategory()
			.clickfirstProduct()
			.enterQty(qty)
			.clickBtnAddToCart()
			.clickBtnMiniCart()
			.clickBtnViewBasket()
			.clickGoToCheckout()
			.enterCustomerEmail(email)
			.enterCustomerFirstName(firstname)
			.enterCustomerLastName(lastname)
			.enterCustomerAddress1(country, address1)
			.enterCustomerAddress2(address2)
			.enterCustomerAddress3(address3)
			.enterCustomerZipCode(country, zipcode)
			.enterCustomerCity(country, city)
			.enterCustomerTelephone(country, phone)
			.selectState(country, state)
			.clickDeliveryTypeBox()
			.chooseDeliveryType()
			.clickGuestNextButton()
			.selectAddressFormat()
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
			return ExcelDataReader.getData("PartyLite_Smokes_Data", "GuestOrderVC_PL");		
		}catch(NullPointerException e) {
			reportStep("Excel sheet or file is not available.","fail", false);
			return null;
		}
}
	
}
