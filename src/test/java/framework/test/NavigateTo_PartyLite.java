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

public class NavigateTo_PartyLite extends Class_initEcomPrac{
	public String country;
	
	public NavigateTo_PartyLite() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataNTP(@Optional String paramCountry) {
		tcName = "NavigateTo_PartyLite";
		tcDescription = "Navigation to landing page and closing signupu letter dialog";
		category = "Smokes_" + paramCountry;
		authors = "Anand";
		testNodes = "NavigationPartyLite_closeDialogSignup";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "MyAccount_Email_PL";
		country = paramCountry;
	}
	
	@Test(groups= {"eShop"},invocationCount=75,threadPoolSize=25)
	public void navigateTo_PL() {
		
			
			try {
				
				try {
				new LandingPage_PartyLite(driver, Test)
				.closeDialogSignUpNewsLetter()
				.clickToAllowCookie();
				
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
