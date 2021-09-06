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

public class FacebookPartyPrevHost_NewAddress_PartyLite extends Class_initEcomPrac{
	public String country;

	public FacebookPartyPrevHost_NewAddress_PartyLite() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataFBPPH_NA(@Optional String paramCountry) {
		category = "Smokes_" +paramCountry;
		tcName = "Facebook Party PreviousHost_NewAddress";
		tcDescription = "Signing in of Consultant and creating a facebook party by assigning previous host new address";
		
		authors = "Anand";
		testNodes = "Consultant signing-in and creating a party";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "Facebook_PrevHost_NA_PL";
		country = paramCountry;
	}
	
	@Test(groups= {"PEX", "Smokes"}, dataProvider="CreatePartyDetails")
	public void facebookPartyPrevHost_NewAddress(String consultant_uname, String consultant_password, String partyName, 
			 String partyDate, String partyTime,String partyHostEmail, String shipAddrLine1, String shipAddrLine2,String shipAddrcity , String shipAddrcountry, 
			String shipAddrpostcode,String hostPhone){
		
		try {
			try {
			
			new LandingPage_PartyLite(driver, Test)
			
			.closeDialogSignUpNewsLetter()
			.clickToAllowCookie()
			.clickSignin()
			.enterEmailName(consultant_uname)
			.enterPassword(consultant_password)
			.ConClickSignin()
			//.closeSocialNewAcctDialog()
			.clickCreateParty(country)
			.typePartyName(partyName)
			.clickSelectPartyType()  
			/*.selectPartyType(partyType) -- to allow the default selection to be used
			*/
			.clickFacebookParty(country)
			.clickForDatePicker()
			.clickDate(partyDate)
			.enterPartyTime(partyTime)
			.enterHostEmail(partyHostEmail)
			.selectHostEmail(partyHostEmail)
			.selectNewShippingAddress()

			.typeNewShippingAddressLine1(country,shipAddrLine1)
			.typeNewShippingAddressLine2(country,shipAddrLine2)
			.typeNewShippingAddresscity(country,shipAddrcity)
			//.typeNewShippingAddresscountry(shipAddrcountry)
			.typeNewShippingAddresspostcode(country,shipAddrpostcode)
			.typePartyShipping_Phone(country,hostPhone)

			.clickCreatePartyAddressCleansing()
			.selectExperianAddress()
			.verifyPartyDashboardPageTitle()
			.verifyPartyName(partyName);
			
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
	
	@DataProvider(name="CreatePartyDetails")
	public  Object[][] getData(){
		try {
			return ExcelDataReader.getData(ExcelFileName, sheetName);		
		}catch(NullPointerException e) {
			reportStep("Excel sheet or file is not available.","fail", false);
			return null;
		}
}
	
}
