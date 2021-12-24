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

public class OnlineEnrollment_CA_PartyLiteNew extends Class_initEcomPrac{
	public String country;

	public OnlineEnrollment_CA_PartyLiteNew() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataOECA(@Optional String paramCountry) {
		tcName = "CA Online Enrollment";
		tcDescription = "Add bundle item and update the qty at shopping cart page";
		category = "Smokes_"+paramCountry;
		authors = "Nikita";
		testNodes = "consultant enrollment process";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "OECA_PL";
		country = paramCountry;

	}

	@Test(groups= {"eShop", "Smokes"}, dataProvider="GuestOrder")
	public void createGuestOrder(String password,String address1,
			String zipcode,String city,String phone,String state, String cardNumber,
			String expMonth,String expYear,String cardCVV){

		try {

			try {

				new LandingPage_PartyLite(driver, Test)

				//.closeDialogSignUpNewsLetter()
				.closeListrakNewsLetter()
				.clickToAllowCookie()
				.clickOurMission()
				.bodyContainer()
				.clickEnrollNow()
				.bodyContainer()
				.enterEmail()
				.clickSubmit()
				//.re_enterEmail()
				.enterPassword(password)
				.re_enterPassword(password)
				.enterFname()
				//.enterMname()
				.enterLname()
				//.enterPFname()
				.enterUserDOB()
				.clickNext()
				.enterUserContactPhone(phone)
				.enterUserMobilePhone(phone)
				.enterUserShipAddressLine1(address1)
				.enterUserShipCity(city)
				.enterUserShipPostcode(zipcode)
				.clickStateDropdown(state)
				.clickNext()
				.clickExpResult()
				/*
				 * .clickIDontHaveCons() .clickMySponsor() .verifySponsor()
				 * .clickMySponsorConfirm()
				 */
				.clickKit()
				/*
				 * .clickNext() .clickKit() .clickNext() .enterConsPWS() .clickKit()
				 * .verifyPWSAvailable()
				 */
				.gotoCart()
				.addProductQty()
				.clickUpdateQty()
				.clickGoToCheckout()
				.clickAgree()
				/*
				 * .clickAgreeNext() .clickDeliveryTypeBox() .chooseHomeDeliveryUSMethod()
				 */
				.clickNextProceedToPaymentOE()
				/*
				 * .clickAcceptTerms() .clickPlaceOrderButton()
				 */
				.enterCardNumber(cardNumber)
				.enterCardExpMonth(expMonth)
				.enterCardExpYear(expYear)
				.enterCardCVV(cardCVV)
				.getOrderNumber()
				.clickConfirm()
				.verifyKitSuccessPage();

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
