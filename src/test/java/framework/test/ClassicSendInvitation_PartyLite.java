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

public class ClassicSendInvitation_PartyLite extends Class_initEcomPrac{
	public String country;

	public ClassicSendInvitation_PartyLite() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataCSI(@Optional String paramCountry) {
		category = "Smokes_" +paramCountry;
		tcName = "ConsultantSendInvitation";
		tcDescription = "consultant and send invitation to guest in a classic party";
		
		authors = "Anand";
		testNodes = "send invitation";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "Cons_Send_Invitation_PL";
		country = paramCountry;
		
	}
	

			
			@Test(groups= {"PEX"}, dataProvider="LoginDetails")
			public void consultantSendInvitation(String consultant_uname,String consultant_password, String partyName, 
					 String partyDate, String partyTime, String guest_fname,String guest_lname,String phone) {
					
				try {
					try {
					new LandingPage_PartyLite(driver, Test)
					//.closeDialogSignUpNewsLetter()
					.clickToAllowCookie()
					.clickSignin()
					.enterEmailName(consultant_uname)
					.enterPassword(consultant_password)
					.ConClickSignin()
					//.closeSocialNewAcctDialog()
					.clickCreateParty(country)
					.waitBodyContainer()
					.typePartyName(partyName)
					/*.clickSelectPartyType()  -- to allow the default selection to be used
					.selectPartyType(partyType)
					*/.clickForDatePicker()
					.clickDate(partyDate)
					.enterPartyTime(partyTime)
					.selectPartyHost_self()
					.selectHostShippingAddress()
					// new CR .selectHostLocationAddress()
					.clickCreateParty()
					.verifyPartyDashboardPageTitle()
					.copyPartyUrl()
					.verifyPartyName(partyName)
					.clickGuestsLink()
					.waitBodyContainer()
					.clickSendInvite()
					.enterEmail(Guest_Email)
					.enterFname(guest_fname)
					.enterLname(guest_lname)
					.enterPhone(country,phone)
					.clickAddButton()
					.verifyGuestEmail(Guest_Email)
					.clickCheckBox()
					.clickSendInvitations()
					.verifyNoGuest();
					
				}
				catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				} catch(WebDriverException e) {
					e.getMessage();
					reportStep("The test encountered an exception.","warning");
					
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
