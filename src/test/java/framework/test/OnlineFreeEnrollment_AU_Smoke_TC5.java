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

public class OnlineFreeEnrollment_AU_Smoke_TC5 extends Class_initEcomPrac{
	public String country;
	
	public OnlineFreeEnrollment_AU_Smoke_TC5() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataOEUS(@Optional String paramCountry) {
		tcName = "Smoke_TC-5- OnlineFreeEnrollment_AU_Smoke_TC5";
		tcDescription = "Apply FREE-Enrollment Coupon";
		category = "Smokes_"+paramCountry;
		authors = "Nikita";
		testNodes = "consultant enrollment process";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "Smoke_TestCase_5";
		country = paramCountry;
		
	}
	
	@Test(groups= {"eShop", "Smokes"}, dataProvider="GuestOrder")
	public void createGuestOrder(String password,String address1,String zipcode,String city,String cphone, String mphone, String state, 
			String billaddress1,String billcity, String state1,String billzipcode, String cardNumber,String expMonth,String expYear,String cardCVV) throws InterruptedException{
		
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
			.enterPassword(password)
			.re_enterPassword(password)
			.enterFname()
			.enterLname()
			.enterUserDOB()
			.clickNext()
			.enterUserContactPhone(cphone)
			.enterUserMobilePhone(mphone)
			.enterUserShipAddressLine1(address1)
			.enterUserShipCity(city)
			.enterUserShipPostcode(zipcode)
			.clickStateDropdown(state)
			.clickBillingCheckbox()
			.enterUserBillAddressLine1(billaddress1)
			.enterUserBillCity(billcity)
			.clickStateDropdown1(state1)
			.enterUserBillPostcode(billzipcode)
			.clickNext()
			.clickShipExpResult()
			.clickBillExpResult()
			.gotoCart()
			.enterFREECoupon()
			.clickCouponApplyButton()
			.verifyCouponApplyMessage()
			.clickGoToCheckout()
			.verifyOrderTotal()
			.clickAgree()
			.clickDeliveryTypeBox()
			.chooseDeliveryType()
			.clickNextProceedToPaymentOE()		
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
