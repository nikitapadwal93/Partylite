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
import framework.pages.OnlineEnrollmentLandingPage_PartyLite;
import framework.utils.ExcelDataReader;

public class OnlineEnrollment_CA_Regression_TC16 extends Class_initEcomPrac{
	public String country;

	public OnlineEnrollment_CA_Regression_TC16() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataOECA(@Optional String paramCountry) {
		tcName = "OnlineEnrollment_CA_Regression_TC16";
		tcDescription = "consultant enrollment process cananda";
		category = "Smokes_"+paramCountry;
		authors = "Nikhil";
		testNodes = "consultant enrollment process";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "Regression_TC16";
		country = paramCountry;

	}

	@Test(groups= {"eShop", "Smokes"}, dataProvider="GuestOrder")
	public void createGuestOrder(String fname, String lname,String email, String confemail,String password,
			String address1,String city,String zipcode,String phone,String state,
			String password2,String phone2,String address2,String zipcode2, String city2,String state2,
			String cardNumber,String expMonth,String expYear,String cardCVV){

		try {

			try {

				new LandingPage_PartyLite(driver, Test)

				//.closeDialogSignUpNewsLetter()
				.closeListrakNewsLetter()
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
				.clickMenuExpansionLink()
				.clickOnDashboard()
				.clickAddressBookLink()
				.enterStreetAddress1(address1)
				.entercity(city)
				.enterPostalCode(zipcode)
				.enterTelephone(phone)
				.selectStateFromDropdown(state)
				.clickSearchAndValidate()
				.selectAddressFormat()
				.selectStateFromDropdown(state)
				.clickSaveAddressLink()
				.clickOurMission()
				.bodyContainer()
				.clickEnrollNow()
				.bodyContainer()
				//.enterEmail()
				//.clickSubmit()
				//.enterPassword(password2)
				//.re_enterPassword(password2)
				//.enterFname()
				//.enterLname()
				.enterUserDOB()
				.clickNext()
				.enterUserContactPhone(phone2)
				.enterUserMobilePhone(phone2)
				.enterUserShipAddressLine1(address2)
				.enterUserShipPostcode(zipcode2)
				.enterUserShipCity(city2)
				.clickStateDropdown(state2)
				.clickNext()
				.clickExpResult()
				.clickKit_BudleItems()
				.clickKit()
				.gotoCart()
				.clickGoToCheckout()
				.verifyshipping()
				.clickAgree()
				.clickNextProceedToPaymentOE()		
				.enterCardNumber(cardNumber)
				.enterCardExpMonth(expMonth)
				.enterCardExpYear(expYear)
				.enterCardCVV(cardCVV)
				.getOrderNumber()
				.clickConfirm()
				.verifyKitSuccessPage();
				
				/*
				 * .clickIDontHaveCons() .clickMySponsor() .verifySponsor()
				 * .clickMySponsorConfirm()
				 */
				//.clickKit()
				/*
				 * .clickNext() .clickKit() .clickNext() .enterConsPWS() .clickKit()
				 * .verifyPWSAvailable()
				 */
				
				
				
				/*
				 * .clickAgreeNext() .clickDeliveryTypeBox() .chooseHomeDeliveryUSMethod()
				 */
				
				/*
				 * .clickAcceptTerms() .clickPlaceOrderButton()
				 */
				
				
				
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
