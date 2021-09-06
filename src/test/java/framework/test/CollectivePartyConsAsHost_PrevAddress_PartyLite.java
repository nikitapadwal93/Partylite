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

public class CollectivePartyConsAsHost_PrevAddress_PartyLite extends Class_initEcomPrac{
	public String country;

	public CollectivePartyConsAsHost_PrevAddress_PartyLite() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataCoPCPA(@Optional String paramCountry) {
		category = "Smokes_" +paramCountry;
		tcName = "Collective Party ConsAsHost_PrevAddress";
		tcDescription = "Signing in of Consultant and creating a collective party by assigning consultant as host - previous address";
		
		authors = "Anand";
		testNodes = "Consultant signing-in and creating a party";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "Collective_ConsAsHost_PA_PL";
		country = paramCountry;
	}
	
	@Test(groups= {"PEX", "Smokes"}, dataProvider="CreatePartyDetails")
	public void collectiveParty_PrevAddress(String consultant_uname, String consultant_password, String partyName, 
			 String partyDate, String expireDate,String partyTime,String partyExpireTime){
		
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
			.clickCollectiveParty(country)
			.clickForDatePicker()
			.clickDate(partyDate)
			.clickExpireDate()
			.clickExpireDate(expireDate)
			.enterPartyTime(partyTime)
			.enterPartyExpireTime(partyExpireTime)
			.selectPartyHost_self()
			.clickPreviousShippingAddress()
			.selectPreviousShippingAddress()
			/*.clickPreviousLocationAddress()
			.selectPreviousLocationAddress()*/
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
