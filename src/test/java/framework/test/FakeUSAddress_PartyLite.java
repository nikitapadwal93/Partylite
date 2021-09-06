package framework.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import framework.appInit.Class_initUSFakeAddress;
import framework.pages.LandingPage_FakeUSAddress;

public class FakeUSAddress_PartyLite extends Class_initUSFakeAddress{
	public String country;
	
	public FakeUSAddress_PartyLite() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataFAKEUSAddress(@Optional String paramCountry)  {
		tcName = "US Address fetch";
		tcDescription = "US Address fetch from Fake Address generator";
		category = "Smokes_"+paramCountry;
		authors = "Anand";
		testNodes = "US address fetch";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "Login_ExistingUser_PL";
		country = paramCountry;
		
	}
	
	
	@Test(groups= {"eShop"})
	public void fetchUSAddress() {
		
		try {
			
			try {
			
			new LandingPage_FakeUSAddress(driver, Test)
			.getStreet()
			.getCity()
			.getState()
			.getZipcode()
			.getPhoneNum()
			.getMobNum();
			
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
	
	/*@DataProvider(name="LoginDetails")
	public  Object[][] getData(){
		try {
			return ExcelDataReader.getData(ExcelFileName, sheetName);		
		}catch(NullPointerException e) {
			reportStep("Excel sheet or file is not available.","fail", false);
			return null;
		}
}*/
	

}
