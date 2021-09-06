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

public class HostSendInvitation_PartyLite extends Class_initEcomPrac{

	public String country;
	
	public HostSendInvitation_PartyLite() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataHSI(@Optional String paramCountry) {
		category = "Smokes_" +paramCountry;
		tcName = "Send Invitation";
		tcDescription = "Login as a host and send invitation to host";
		
		authors = "Anand";
		testNodes = "Host Login";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "Login_HostUser_PL";
		country = paramCountry;
		
	}
	
	@Test(groups= {"PEX"}, dataProvider="HostLoginDetails")
	public void hostSendInvitation(String host_uname, String host_password,String ContactsFN, String ContactsLN, String ContactsEmail, String ContactsPhone) {
		
		try {
			try {
			new LandingPage_PartyLite(driver, Test)
			
			.closeDialogSignUpNewsLetter()
			.clickToAllowCookie()
			.clickSignin()
			.enterEmailName(host_uname)
			.enterPassword(host_password)
			.btnClickSignin()
			.clickPartyContacts()
			.clickCreateContact()
			.typeFirstName(ContactsFN)
			.typeLastName(ContactsLN)
			.typeEmail(ContactsEmail)
			.typePhone(country,ContactsPhone)

			.clickAgreementToAddToParty()
			.clickSaveContact()
			.selectMyParties()
			.selectHostOngoingPartyListed()
			.clickSendInvitation()
			.typeEmail(ContactsEmail)
			.selectEmail()
			.clickAddButton()
			.clickCheckBox()
			.clickSendInvitations();
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
	
	@DataProvider(name="HostLoginDetails")
	public  Object[][] getData(){
		try {
			return ExcelDataReader.getData(ExcelFileName, sheetName);		
		}catch(NullPointerException e) {
			reportStep("Excel sheet or file is not available.","fail", false);
			return null;
		}
}
	

}
