package framework.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriverException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import framework.appInit.Class_initEcomPrac;
import framework.appInit.Class_initMailinator;
import framework.pages.LandingPage_Mailinator;
import framework.pages.LandingPage_PartyLite;
import framework.utils.ExcelDataReader;

public class VirtualCustomer_OutsideOrder_PartyLite extends Class_initEcomPrac{
	public String country;

	public VirtualCustomer_OutsideOrder_PartyLite() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataVCOO(@Optional String paramCountry) {
		tcName = "Virtual Outside Order";
		tcDescription = "place outside order for a guest - virtual party";
		category = "Smokes_"+ paramCountry;
		authors = "Anand";
		testNodes = "logged in customer";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "Virtual_Outside_PL";
		country = paramCountry;
	}
	
	@Test(groups= {"PEX"},dataProvider="LoginDetails")
	public void virtualCustomerOutsideOrder(String guest_password,String qty,
			String address1,String address2,String address3,
			String zipcode,String city,String phone,String PaymentType, String cardNumber,
			String expMonth,String expYear,String cardCVV) {
			
		try {
			try {
				new LandingPage_PartyLite(driver, Test)
				.closeDialogSignUpNewsLetter()
				.clickToAllowCookie()
				.clickSignin()
				.enterEmailName(Virtual_New_Guest_Email)
				.enterPassword(guest_password)
				.btnClickSignin()
				.clickNextButton()
				.clickOnlineShop()
				.clickFirstCategory()
				.clickfirstProduct()
				.enterQty(qty)
				.clickBtnAddToCart()
				.clickBtnMiniCart()
				.clickBtnViewBasket()
				.clickGoToCheckout()
				.enterCustomerAddress1(country,address1)
				.enterCustomerAddress2(address2)
				.enterCustomerAddress3(address3)
				.enterCustomerZipCode(country, zipcode)
				.enterCustomerCity(country, city)
				.enterCustomerTelephone(country, phone)
				.clickDeliveryTypeBox()
				.chooseHomeDeliveryMethod()
				.clickGuestNextButton()
				.selectAddressFormat()
				.clickNextProceedToPayment()
				.verifyBillingAddress()
				.clickAcceptTerms()
				.clickPlaceOrderButton()
				.enterCardNumber(cardNumber)
				.enterCardExpMonth(expMonth)
				.enterCardExpYear(expYear)
				.enterCardCVV(cardCVV)
				.getOrderNumber()
				.clickConfirm()
				.confirmOrderIsSuccess();
			
			
			} catch(WebDriverException e) {
				e.getMessage();
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
	
	@DataProvider(name="LoginDetails")
	public  Object[][] getData(){
		try {
			return ExcelDataReader.getData(ExcelFileName, sheetName);		
		}catch(NullPointerException e) {
			reportStep("Excel sheet or file is not available.","fail", false);
			return null;
		}
}
	
}
