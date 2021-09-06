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

public class ClassicPartyPrevHost_NewAddress_PartyLite extends Class_initEcomPrac{
	public String country;

	public ClassicPartyPrevHost_NewAddress_PartyLite() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataCPP_NA(@Optional String paramCountry) {
		category = "Smokes_" +paramCountry;
		tcName = "Classic Party PrevHost_NewAddress";
		tcDescription = "Signing in of Consultant and creating a party by previous host - new address";
		
		authors = "Anand";
		testNodes = "ConsultantSigning-in_CreatingParty_"+paramCountry;
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "PartyCreationPrevHost_NA_PL";
		country = paramCountry;
		
	}
	
	@Test(groups= {"PEX", "Smokes"}, dataProvider="CreatePartyDetails")
	public void classicPartyPrevHost_NewAddress(String consultant_uname, String consultant_password, String partyName, 
			 String partyDate, String partyTime, String partyHostEmail, String shipAddrLine1, String shipAddrLine2,String shipAddrcity , String shipAddrcountry, 
			String shipAddrpostcode, String partyAddrLine1 , String partyAddrLine2, String partyAddrcity, 
			String partyAddrcountry, String partyAddrpostcode, String hostPhone){
		
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
			/*.clickSelectPartyType()  -- commented out to allow to use default selection
			.selectPartyType(partyType)
			*/.clickForDatePicker()
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
