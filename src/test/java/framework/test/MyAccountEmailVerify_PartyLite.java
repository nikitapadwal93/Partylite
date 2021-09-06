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

public class MyAccountEmailVerify_PartyLite extends Class_initEcomPrac{
	public String country;
	
	public MyAccountEmailVerify_PartyLite() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataMyAccEmail(@Optional String paramCountry) {
		tcName = "LoggedInCustomerOrder";
		tcDescription = "placing order in eShop by a customer";
		category = "Smokes_"+paramCountry;
		authors = "Anand";
		testNodes = "customer order in eshop";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "MyAccount_Email_PL";
		country = paramCountry;
		
	}
	
	
	@Test(groups= {"eShop", "Smokes"}, dataProvider="CustomerOrder")
	public void loggedInCustomerOrder(String customer_email,String customer_password,String ContentToVerify){
		
		try {
			try {
			new LandingPage_PartyLite(driver, Test)
			//.closeDialogSignUpNewsLetter()
			.clickToAllowCookie()
			.clickSignin()
			.enterEmailName(customer_email)
			.enterPassword(customer_password)
			.btnClickSignin()
			.closeSocialNewAcctDialog()
			.verifyUserLogin(country, ContentToVerify)
			.closeSocialNewAcctDialog()
			.clickMenuExpansionLink()
			.clickMyDashboard()
			.verifyCustomerEmail(customer_email)
			.verifyCustomerEmailBlue(customer_email);
			
			
			
			
			
			} catch(RuntimeException e) {
				System.out.println("Test case name =  " +getClass().getName());
				e.printStackTrace();
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
	
	@DataProvider(name="CustomerOrder",parallel=true)
	public  Object[][] getData(){
		try {
			return ExcelDataReader.getData(ExcelFileName, sheetName);		
		}catch(NullPointerException e) {
			reportStep("Excel sheet or file is not available.","fail", false);
			return null;
		}
}
	
}
