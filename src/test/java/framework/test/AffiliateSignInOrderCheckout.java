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

//This test case signs in Affiliate and place an order
public class AffiliateSignInOrderCheckout extends Class_initEcomPrac {
	public String country;
	
	public AffiliateSignInOrderCheckout() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataSIGNINAFF(@Optional String paramCountry)  {
		tcName = "Affiliate order checkout";
		tcDescription = "Signing in through homepage as Affiliate using Excel";
		category = "Smokes_"+paramCountry;
		authors = "Shashwat";
		testNodes = "Signing-in using excel datareader";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "Login_ExistingUser_PL";
		country = paramCountry;
		
	}
	@Test(groups= {"eShop", "Smokes"}, dataProvider="LoginDetailsAffiliate")
	public void signInToPartyLiteAff(String uname, String password, String Verification_content, 
			String SKU,String qty,String email,String firstname,
			String lastname,String address1,String address2,String address3,
			String zipcode,String city,String phone,String state,String PaymentType, String cardNumber,
			String expMonth,String expYear,String cardCVV) {
		
try {
			
			try {
			
			new LandingPage_PartyLite(driver, Test)
			
			//.closeDialogSignUpNewsLetter()
			.closeListrakNewsLetter()
			.clickToAllowCookie()
			.clickSignin()
			.enterEmailName(uname)
			.enterPassword(password)
			.btnClickSignin()
			.clickBtnSearch()
			.enterSKU(SKU)
			//.clickSearchButton()
			.clickProductImage()
			.enterQty(qty)
			.clickBtnAddToCart()
			.clickBtnMiniCart()
			.clickBtnViewBasket()
			.clickCouponApplyButton()
			//.verifyCouponApplyMessage()
			//call voucher entry data
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
	
	@DataProvider(name="LoginDetailsAffiliate")
	public  Object[][] getData(){
		try {
			return ExcelDataReader.getData(ExcelFileName, sheetName);		
		}catch(NullPointerException e) {
			reportStep("Excel sheet or file is not available.","fail", false);
			return null;
		}
}
	
}
