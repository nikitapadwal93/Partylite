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

public class ClassicOutsideOrder_PartyLite extends Class_initMailinator{
	public String country;

	public ClassicOutsideOrder_PartyLite() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setData_COO(@Optional String paramCountry) {
		tcName = "Classic Outside Order";
		tcDescription = "Guest creates outside order in a classic party";
		category = "Smokes_" + paramCountry;
		authors = "Anand";
		testNodes = "mailinator link";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "Nanosite_Guest_Order_PL";
		country = paramCountry;
	}
	
	@Test(groups= {"PEX"},dataProvider="LoginDetails")
	public void outsideOrder(String guest_password,String ContentToVerify,String SKU,String qty,
			String address1,String address2,String address3,
			String zipcode,String city,String phone,String PaymentType, String cardNumber,
			String expMonth,String expYear,String cardCVV) {
			
		try {
			try {
			new LandingPage_Mailinator(driver, Test)
			.openGuestEmail()
			.clickGoButton()
			.clickPartyLiteMail(country)
			.clickCreateAnAccountMail()
			.clickToAllowCookie()
			.enterGuestEmail()
			.clickSubmit()
			//.closeDialogSignUpNewsLetter()
			//.clickAttendParty()
			.enterPassword(guest_password)
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
			.clickOnlineShop()
			.clickFirstCategory()
			.clickfirstProduct()
//			.clickBtnSearch()
//			.enterSKU(SKU)
//			.clickProductImage()
			.enterQty(qty)
			.clickBtnAddToCart()
			.clickBtnMiniCart()
			.clickBtnViewBasket()
			.clickGoToCheckout()
			.clickNextProceedToPayment()
			//.clickPaymentMethods()
			//.selectTypeOfPayment(PaymentType)
			//.selectSameasShipping()
			.enterCustomerAddress1(country, address1)
			.enterCustomerCity(country, city)
			.enterCustomerTelephone(country, phone)
			.enterCustomerZipCode(country, zipcode)
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
