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

public class CategoryNavigation_PartyLite extends Class_initEcomPrac{
	public String country;
	
	public CategoryNavigation_PartyLite() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataNTP(@Optional String paramCountry) {
		tcName = "NavigateTo_PartyLite";
		tcDescription = "Navigation to landing page and closing signup letter dialog";
		category = "Smokes_" + paramCountry;
		authors = "Anand";
		testNodes = "NavigationPartyLite_closeDialogSignup";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "CategoryURL_PL";
		country = paramCountry;
	}
	
	@Test(groups= {"eShop"}, dataProvider="CategotyDetails", invocationCount=10)
	public void navigateTo_PL(String category_url) {
		
			
			try {
				
				try {
				new LandingPage_PartyLite(driver, Test)
				.verifyPLTitle(country)
				.gotoCategory(category_url);
				
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
	
	
	@DataProvider(name="CategotyDetails")
	public  Object[][] getData(){
		try {
			return ExcelDataReader.getData(ExcelFileName, sheetName);		
		}catch(NullPointerException e) {
			reportStep("Excel sheet or file is not available.","fail", false);
			return null;
		}
}
	

}
