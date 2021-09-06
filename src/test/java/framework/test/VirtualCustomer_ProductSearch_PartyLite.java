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
import framework.appInit.Class_initMailinator;
import framework.pages.LandingPage_Mailinator;
import framework.pages.LandingPage_PartyLite;
import framework.utils.ExcelDataReader;

public class VirtualCustomer_ProductSearch_PartyLite extends Class_initEcomPrac{
	public String country;

	public VirtualCustomer_ProductSearch_PartyLite() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataVCPS(@Optional String paramCountry) {
		tcName = "Virtual Outside Order";
		tcDescription = "product search party user";
		category = "Smokes_"+ paramCountry;
		authors = "Anand";
		testNodes = "logged in customer";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "VirtualSearch_PL";
		country = paramCountry;
	}
	
	@Test(groups= {"PEX"},dataProvider="LoginDetails",invocationCount = 50)
	public void virtualCustomerPS(String email,String guest_password,String sku) {
			
		try {
			try {
				new LandingPage_PartyLite(driver, Test)
				.closeDialogSignUpNewsLetter()
				.clickToAllowCookie()
				.clickSignin()
				.enterEmailName(email)
				.enterPassword(guest_password)
				.btnClickSignin()
				.clickNextButton()
				.clickBtnSearch()
				.enterSKU(sku)
				.clickProductImage();
			
			
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
	
	@DataProvider(name="LoginDetails",parallel = true)
	public  Object[][] getData(){
		try {
			return ExcelDataReader.getData(ExcelFileName, sheetName);		
		}catch(NullPointerException e) {
			reportStep("Excel sheet or file is not available.","fail", false);
			return null;
		}
}
	
}
