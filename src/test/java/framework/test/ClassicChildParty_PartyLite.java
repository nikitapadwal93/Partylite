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
import framework.pages.LandingPage_PartyLite;
import framework.utils.ExcelDataReader;

public class ClassicChildParty_PartyLite extends Class_initEcomPrac{
	public String country;
	
	
	public ClassicChildParty_PartyLite() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setData_CCP(@Optional String paramCountry) {
		tcName = "Classic Child Party";
		tcDescription = "Consultant creates child party for a guest";
		category = "Smokes_"+paramCountry;
		authors = "Anand";
		testNodes = "Child Party";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "AddGuestDetails_PL";
		country = paramCountry;
		
	}
	
	@Test(groups= {"PEX", "Smokes"}, dataProvider="AddGuestDetails")
			
	public void childParty(String consultant_uname, String consultant_password, String partyName, 
			 String partyDate, String partyTime, String hostFirstName, String hostLastName, String hostEmail,
			String hostPhone, String shipAddrLine1, String shipAddrLine2,String shipAddrcity , String shipAddrcountry, 
			String shipAddrpostcode, String partyAddrLine1 , String partyAddrLine2, String partyAddrcity, 
			String partyAddrcountry, String partyAddrpostcode,String guestFN, String guestLN, String guestEmail, String guestPhone,String childpartyName, 
			 String childpartyDate, String childpartyTime){
		
		try {
			try {
			
			new LandingPage_PartyLite(driver, Test)
			.closeDialogSignUpNewsLetter()
			.clickToAllowCookie()
			.clickSignin()
			.enterEmailName(consultant_uname)
			.enterPassword(consultant_password)
			.ConClickSignin()
			.closeSocialNewAcctDialog()
			.clickCreateParty(country)
			.waitBodyContainer()
			.typePartyName(partyName)
			/*.clickSelectPartyType()
			.selectPartyType(partyType)*/
			.clickForDatePicker()
			.clickDate(partyDate)
			.enterPartyTime(partyTime)
			.selectPartyHost_New()
			.typePartyHost_FN(hostFirstName)
			.typePartyHost_LN(hostLastName)
			.typePartyHost_Email(hostEmail)

			.typePartyHost_Phone(country,hostPhone)
			.selectNewShippingAddress()
			.typeNewShippingAddressLine1(country,shipAddrLine1)
			.typeNewShippingAddressLine2(country,shipAddrLine2)
			.typeNewShippingAddresscity(country,shipAddrcity)
			//.typeNewShippingAddresscountry(shipAddrcountry)
			.typeNewShippingAddresspostcode(country,shipAddrpostcode)
			.typePartyShipping_Phone(country,hostPhone)
			/*.selectNewLocationAddress()
			.typeNewPartyAddressLine1(partyAddrLine1)
			.typeNewPartyAddressLine2(partyAddrLine2)
			.typeNewPartyAddresscity(partyAddrcity)
			.typeNewPartyAddresscountry(partyAddrcountry)
			.typeNewPartyAddresspostcode(partyAddrpostcode)
			.typePartyLocation_Phone(hostPhone)*/
			.clickCreatePartyAddressCleansing()
			.selectExperianAddress()
			.verifyPartyDashboardPageTitle()
			.verifyPartyName(partyName)
			.clickGuestsLink()
			.waitBodyContainer()
			.clickAddGuestLink()
			.typeGuestFN(guestFN)
			.typeGuestLN(guestLN)
			.typeGuestEmail(guestEmail)
			.typeGuestPhone(country,guestPhone)
			.clickAgreementToAddToParty()
			.clickAddGuestButton()
			.clickPartyGuest()
			.bookParty()
			.typePartyName(childpartyName)
			.clickForDatePicker()
			.clickNextMonth()
			.selectChildBookDay()
			//.clickExpireDate(childpartyDate)
			.enterPartyTime(childpartyTime)
			.selectNewShippingAddress()
			.typeNewShippingAddressLine1(country,shipAddrLine1)
			.typeNewShippingAddressLine2(country,shipAddrLine2)
			.typeNewShippingAddresscity(country,shipAddrcity)
			//.typeNewShippingAddresscountry(shipAddrcountry)
			.typeNewShippingAddresspostcode(country,shipAddrpostcode)
			.typePartyShipping_Phone(country,hostPhone)

			/*.selectNewLocationAddress()
			.typeNewPartyAddressLine1(partyAddrLine1)
			.typeNewPartyAddressLine2(partyAddrLine2)
			.typeNewPartyAddresscity(partyAddrcity)
			.typeNewPartyAddresscountry(partyAddrcountry)
			.typeNewPartyAddresspostcode(partyAddrpostcode)
			.typePartyLocation_Phone(hostPhone)*/
			.clickCreateChildPartyAddressCleansing()
			.selectChildExperianAddress()
			.verifyChildPartyMessage();
			//.validateGuestNameAdded(guestFN, guestLN);
			
			} catch(WebDriverException e) {
				e.getMessage();
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
	
	@DataProvider(name="AddGuestDetails")
	public  Object[][] getData(){
		try {
			return ExcelDataReader.getData(ExcelFileName, sheetName);		
		}catch(NullPointerException e) {
			reportStep("Excel sheet or file is not available.","fail", false);
			return null;
		}
}
	

}
