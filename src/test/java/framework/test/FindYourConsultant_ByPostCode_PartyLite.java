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

	public class FindYourConsultant_ByPostCode_PartyLite  extends Class_initEcomPrac {
		public String country;
		
		public FindYourConsultant_ByPostCode_PartyLite() throws FileNotFoundException, IOException {
			super();
			// TODO Auto-generated constructor stub
		}

		@Parameters("paramCountry")
		@BeforeTest(groups= {"Common"})
		public void setDataFYCPC(@Optional String paramCountry) 
		{
			tcName = "FindYourConsultant_ByPostCode";
			tcDescription = "Select consultant by postcode";
			category = "Smokes_"+paramCountry; //?? commented out to enable category assignment at beforemethod block.
			authors = "Anand";
			testNodes = "Search consultant by postcode";
			ExcelFileName="PartyLite_Smokes_Data";
			sheetName = "SearchConsultant_PL";
			country = paramCountry;
			
		}
		
		
		
		@Test(groups= {"eShop", "Smokes"}, dataProvider="SearchConsultant")
		public void findYourConsultant_thruPostCode(String consFirstName,	String consLastName, String consCity, String consPostcode) {
			
			try {
				
				try {
					new LandingPage_PartyLite(driver, Test)
					.closeDialogSignUpNewsLetter()
					.clickToAllowCookie()
					.clickFindConsultant()
					.clickPostCodeOption()
					.enterPostCodeValue(country, consPostcode)  //multi-lingual support, parallel execution with country parameter
					.clickSearchButton()
					//.verifySearchResult()  --> needs to be refactored 
					.retrieveConsultantName()
					.checkSearchedConsultant();
					//.validateSelectedConsultant();
				
					
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
		
		@DataProvider(name="SearchConsultant")
		public  Object[][] getData(){
			try {
				return ExcelDataReader.getData(ExcelFileName, sheetName);		
			}catch(NullPointerException e) {
				reportStep("Excel sheet or file is not available.","fail", false);
				return null;
			}
	}
		

	}


