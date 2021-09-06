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
import framework.utils.ExcelDataReader;

	public class Create_ExistingUserAccount_PartyLite  extends Class_initEcomPrac{
		
		public Create_ExistingUserAccount_PartyLite() throws FileNotFoundException, IOException {
			super();
			// TODO Auto-generated constructor stub
		}

		@Parameters("paramCountry")
		@BeforeTest(groups= {"Common"})
		public void setDataCEUA(@Optional String paramCountry) {
			tcName = "Create_ExistingUserAccount";
			tcDescription = "Identify existing user and prevent duplicate account creation";
			category = "Smokes_"+paramCountry;
			authors = "Anand";
			testNodes = "Prompt user of existing account with Partylite";
			ExcelFileName="PartyLite_Smokes_Data";
			sheetName = "PreventDupeAcctCreation_PL";
			
		}
		
		@Test(groups= {"eShop"}, dataProvider="ExistingUserDetails")
		public void create_ExistingUserAccounts(String firstname,	String lastname, String	email,	String confEmail,	
				String password,	String confpassword) {
			
			try {
				try {
				new LandingPage_PartyLite(driver, Test)
				//.closeDialogSignUpNewsLetter()
				.clickToAllowCookie()
				.clickSignin()
				.clickCreateAnAccount()
				.enterFirstname(firstname)
				.enterLastname(lastname)
				.enterExistingEmail(email)
				.enterExistingEmailConf(confEmail)
				.enterPassword(password)
				.enterPasswordConf(password)
				.clickTermsConditions()
				.clickCreateAnAccount()
				.invalidateDoubleRegistration();  //Error message is expected to be displayed for the existing user
				} catch(RuntimeException e) {
					System.out.println("Test case name =  " +getClass().getName());
					System.out.println(e.getMessage());
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
		
		@DataProvider(name="ExistingUserDetails")
		public  Object[][] getData(){
			try {
				return ExcelDataReader.getData(ExcelFileName, sheetName);		
			}catch(NullPointerException e) {
				reportStep("Excel sheet or file is not available.","fail", false);
				return null;
			}
	}
		

	}


