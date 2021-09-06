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

public class Create_NewRealUserAccount_AddAddress_PartyLite extends Class_initEcomPrac {
	public String country;

	public Create_NewRealUserAccount_AddAddress_PartyLite() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataCNRUAAD(@Optional String paramCountry) {
		tcName = "Create_NewRealUserAccount";
		tcDescription = "New real user account creation";
		category = "Smokes_"+paramCountry;
		authors = "Anand";
		testNodes = "Account creation - new real user & add address";
		ExcelFileName = "PartyLite_Smokes_Data";
		sheetName = "CreateAccount_Address_PL";
		country = paramCountry;

	}

	@Test(groups= {"eShop", "Smokes"}, dataProvider="NewUserDetails")
		public void create_NewUserAccounts(String firstname,	String lastname, String	email,	String confEmail,	
				String password,	String confpassword,String address1,String address2,String address3,
				String zipcode,String city,String phone) {
			
			try {
				try {
				new LandingPage_PartyLite(driver, Test)
				.closeDialogSignUpNewsLetter()
				.clickToAllowCookie()
				.clickSignin()
				.clickCreateAnAccount()
				.enterFirstname(firstname)
				.enterLastname(lastname)
				.enterRealEmail(email)
				.enterRealEmailConf(email)
				.enterPassword(password)
				.enterPasswordConf(password)
				.clickTermsConditions()
				.clickCreateAnAccount()
				.confirmRegistrationText(country)
				.gotoCustomerAddress(country) 
				//.clickaddNewAddress()// removed navigated to the add new address page in gotoCustomerAddress method itself.
				.enterCustomerAddress1(country, address1)
				.enterCustomerAddress2(address2)
				.enterCustomerAddress3(address3)
				.enterCustomerZipCode(country, zipcode)
				.enterCustomerCity(country, city)
				.enterCustomerTelephone(country, phone)
				.clickCheckBoxBillingAddress()
				.clickCheckBoxShippingAddress()
				.clickbtnSearchValidate()
				.clickAddressFormat()
				.clickbtnSaveAddress();
				
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

	@DataProvider(name = "NewUserDetails",parallel=true)
	public Object[][] getData() {
		try {
			return ExcelDataReader.getData(ExcelFileName, sheetName);		
		}catch(NullPointerException e) {
			reportStep("Excel sheet or file is not available.","fail", false);
			return null;
		}
}

}
