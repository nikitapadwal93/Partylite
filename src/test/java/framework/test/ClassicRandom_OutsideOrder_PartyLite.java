package framework.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriverException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import framework.appInit.Class_initMailinator;
import framework.pages.LandingPage_Mailinator;
import framework.utils.ExcelDataReader;

public class ClassicRandom_OutsideOrder_PartyLite extends Class_initMailinator{
	public String country;

	public ClassicRandom_OutsideOrder_PartyLite() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setData_CROO(@Optional String paramCountry) {
		tcName = "Classic Outside Order";
		tcDescription = "Guest creates outside order in a classic party";
		category = "Smokes_" + paramCountry;
		authors = "Anand";
		testNodes = "mailinator link";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "Nanosite_Account_Order_PL";
		country = paramCountry;
	}
	
	@Test(groups= {"PEX"},dataProvider="LoginDetails")
	public void outsideOrder(String firstname, String lastname,String guest_password,String ContentToVerify,String SKU,String qty,
			String address1,String address2,String address3,
			String zipcode,String city,String phone,String PaymentType, String cardNumber,
			String expMonth,String expYear,String cardCVV) {
			
		try {
			try {
			new LandingPage_Mailinator(driver, Test)
			.openNewGuestEmail()
			.clickGoButton()
			.clickPartyLiteMail(country)
			.clickCreateAnAccountMail()
			.clickToAllowCookie()
			.enterNewGuestEmail()
			.clickSubmit()
			//.closeDialogSignUpNewsLetter()
			//.clickAttendParty()
			.enterNanoCustomerConfEmail()
			.enterFirstname(firstname)
			.typeAccountLastName(lastname)
			.typePhone(country, phone)
			.enterNewPassword(guest_password)
			.enterPasswordConf(guest_password)
			.clickSaveContact()
			.clickBtnCreateOrder()
			.gotoHomepage()
			//.verifyUserLogin(country, ContentToVerify)
//			.gotoCustomerAddress(country)
//			.enterCustomerAddress1(country, address1)
//			.enterCustomerAddress2(address2)
//			.enterCustomerAddress3(address3)
//			.enterCustomerZipCode(country, zipcode)
//			.enterCustomerCity(country, city)
//			.enterCustomerTelephone(country, phone)
//			.clickCheckBoxBillingAddress()
//			.clickCheckBoxShippingAddress()
//			.clickbtnSearchValidate()
//			.clickAddressFormat()
//			.clickbtnSaveAddress()
//			.gotoHomepage()
			.closeDialogSignUpNewsLetter()
			.clickBtnSearch()
			.enterSKU(SKU)
			.clickProductImage()
			.enterQty(qty)
			.clickBtnAddToCart()
			.clickBtnMiniCart()
			.clickBtnViewBasket()
			.clickGoToCheckout()
			.clickNextProceedToPayment()
			//.clickPaymentMethods()
			//.selectTypeOfPayment(PaymentType)
			//.selectSameasShipping()
			.enterCustomerAddress1(address1)
			.enterCustomerCity(city)
			.enterCustomerTelephone(country, phone)
			.enterCustomerZipCode(zipcode)
			.clickUpdateAddressButton()
			.clickAcceptTerms()
			.clickPlaceOrderButton()
			.enterCardNumber(cardNumber)
			.enterCardExpMonth(expMonth)
			.enterCardExpYear(expYear)
			.enterCardCVV(cardCVV)
			.getOrderNumber()
			.clickConfirm()
			.confirmOrderIsSuccess();
			
			
			} catch(WebDriverException outsideOrder) {
				outsideOrder.printStackTrace();
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
