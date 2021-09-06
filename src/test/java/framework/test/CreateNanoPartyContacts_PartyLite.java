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


public class CreateNanoPartyContacts_PartyLite extends Class_initEcomPrac{
	public String country;
	
	public CreateNanoPartyContacts_PartyLite() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataCNPCon(@Optional String paramCountry) {
		category = "Smokes_" +paramCountry;
		tcName = "CreatePartyContacts";
		tcDescription = "Signing in as Consultant and creating party contact - customer created from nanosite";
		
		authors = "Anand";
		testNodes = "Consultant signing-in and creating party contacts";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "PartyContactCreationNano_PL";
		country = paramCountry;
		
	}
	
	@Test(groups= {"PEX", "Smokes"}, dataProvider="CreatePartyContactDetails")
	public void createPartyContacts(String consultant_uname, String consultant_password, 
			String ContactsFN, String ContactsLN,String ContactsPhone){
		
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
			.clickPartyContacts(country)
			.clickCreateContact()
			.typeFirstName(ContactsFN)
			.typeLastName(ContactsLN)
			.typeEmail(Guest_Email)

			.typePhone(country,ContactsPhone)

			.clickAgreementToAddToParty()
			.clickSaveContact();
			
			
			} catch(WebDriverException e) {
				e.getMessage();
				reportStep("The test encountered an exception.","warning");
				
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		
				
			
		
	}
	
	@DataProvider(name="CreatePartyContactDetails")
	public  Object[][] getData(){
		try {
			return ExcelDataReader.getData(ExcelFileName, sheetName);		
		}catch(NullPointerException e) {
			reportStep("Excel sheet or file is not available.","fail", false);
			return null;
		}
}

}
