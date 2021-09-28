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

public class VirtualRandom_OutsideOrder_PartyLite extends Class_initMailinator{
	public String country;

	public VirtualRandom_OutsideOrder_PartyLite() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataVROO(@Optional String paramCountry) {
		tcName = "Virtual Outside Order";
		tcDescription = "Open mailbox and place outside order for a guest - virtual party";
		category = "Smokes_"+ paramCountry;
		authors = "Anand";
		testNodes = "mailinator link";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "Nanosite_Account_Order_PL";
		country = paramCountry;
	}
	
	@Test(groups= {"PEX"},dataProvider="LoginDetails")
	public void mailinatorTest(String firstname, String lastname,String guest_password,String ContentToVerify,String SKU,String qty,
			String address1,String address2,String address3,
			String zipcode,String city,String phone,String PaymentType, String cardNumber,
			String expMonth,String expYear,String cardCVV) {
			
		try {
			try {
				new LandingPage_Mailinator(driver, Test)
				.openVirtualGuestEmail()
				.clickGoButton()
				.clickPartyLiteMail(country)
				.clickCreateAnAccountMail()
				.clickToAllowCookie()
				.enterVirtualGuestEmail()
				.clickSubmit()
				.enterVirtualNanoCustomerConfEmail()
				.enterFirstname(firstname)
				.typeAccountLastName(lastname)
				.typePhone(country, phone)
				.enterNewPassword(guest_password)
				.enterPasswordConf(guest_password)
				.clickSaveContact()
				.clickBtnCreateOrder()
				.gotoHomepage()
				.closeDialogSignUpNewsLetter()
				.clickOnlineShop()
				.clickFirstCategory()
				.clickfirstProduct()
				.enterQty(qty)
				.clickBtnAddToCart()
				.clickBtnMiniCart()
				.clickBtnViewBasket()
				.clickGoToCheckout()
				.enterCustomerAddress1(address1)
				.enterCustomerAddress2(address2)
				.enterCustomerAddress3(address3)
				.enterCustomerZipCode(zipcode)
				.enterCustomerCity(city)
				.enterCustomerTelephone(country, phone)
				.clickDeliveryTypeBox()
				.chooseHomeDeliveryMethod()
				.clickGuestNextButton()
				.selectAddressFormat()
				.waitAddressFormat()
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
