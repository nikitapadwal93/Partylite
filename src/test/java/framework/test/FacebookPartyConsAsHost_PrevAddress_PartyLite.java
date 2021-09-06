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

public class FacebookPartyConsAsHost_PrevAddress_PartyLite extends Class_initEcomPrac{
	public String country;

	public FacebookPartyConsAsHost_PrevAddress_PartyLite() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataFBPCH_PA(@Optional String paramCountry) {
		category = "Smokes_" +paramCountry;
		tcName = "Facebook Party ConsAsHost_PreviousAddress";
		tcDescription = "Signing in of Consultant and creating a facebook party by assigning consultant as host - previous address";
		
		authors = "Anand";
		testNodes = "Consultant signing-in and creating a party";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "Facebook_ConsAsHost_PA_PL";
		country = paramCountry;
	}
	
	@Test(groups= {"PEX", "Smokes"}, dataProvider="CreatePartyDetails")
	public void facebookPartyConsAsHost_PrevAddress(String consultant_uname, String consultant_password, String partyName, 
			 String partyDate, String partyTime){
		
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
			.selectPartyHost_self()
			.clickPreviousShippingAddress()
			.selectPreviousShippingAddress()
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
