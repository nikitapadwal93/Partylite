package framework.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriverException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import framework.appInit.Class_initMagentoAdmin;
import framework.pages.AdminLandingPage;
import framework.utils.ExcelDataReader;

public class SmokeTestNine_PartyLite extends Class_initMagentoAdmin {

	
	public SmokeTestNine_PartyLite() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataSmokeT9(@Optional String paramCountry) {
		category = "Smokes_" +paramCountry;
		tcName = "SmokeTestNine_PartyLite";
		tcDescription = "Open Magento Admin and click the party module";
		
		authors = "Anand";
		testNodes = "view parties";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "Login_AdminUser_PL";
		
	}
	
	@Test(groups= {"PEX", "Smokes"}, dataProvider="LoginDetails")
	public void smokeTestNine(String uname,String password) {
			
		try {
			try {
			new AdminLandingPage(driver, Test)
			.enterUserName(uname)
			.enterPassWord(password)
			.clickSignIn()
			.clickPartyMenu()
			.clickManageParties()
			.verifyPartyGrid()
			.clickUserAccount()
			.clickSignOut();
			} catch(WebDriverException e) {
				e.getMessage();
				reportStep("The test encountered an exception.","warning");
				
			}
	}
		catch (FileNotFoundException e) {
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
