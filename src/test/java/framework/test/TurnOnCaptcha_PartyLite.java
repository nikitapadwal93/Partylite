package framework.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriverException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import framework.appInit.Class_initMagentoAdmin;
import framework.pages.AdminLandingPage;
import framework.utils.ExcelDataReader;

public class TurnOnCaptcha_PartyLite extends Class_initMagentoAdmin {

	
	public TurnOnCaptcha_PartyLite() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@BeforeTest(groups= {"Common"})
	public void setDataOnCaptcha() {
		tcName = "Turn On reCaptcha";
		tcDescription = "Open Magento Admin and Switch On the reCaptcha";
		category = "Smokes";
		authors = "Anand";
		testNodes = "Switch On Captcha";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "Login_AdminUser_PL";
		
	}
	
	@Test(groups= {"Captcha"}, 
			dataProvider="LoginDetails", priority=1000, alwaysRun=true)
	public void turnOnCaptcha(String uname,String password) {
			
		try {
			
			try {
				
			
			new AdminLandingPage(driver, Test)
			.enterUserName(uname)
			.enterPassWord(password)
			.clickSignIn()
			.clickStoreMenu()
			.clickConfigurationMenu()
			.clickSecuritySuiteMenu()
			.clickGooglereCaptchaMenu()
			.verifyFrontEndEnabled()
			.enableLoginCheckBox()
			.enableForgotPasswordCheckBox()
			.enableContactCheckBox()
			.enableCreateUserCheckBox()
			.enableConsultantCheckBox()
			.clickSaveConfig()
			.verifyMessage()
			.clickUserAccount()
			.clickSignOut();
			
			} catch(WebDriverException e) {
				System.out.println("Test case name =  " +getClass().getName());
				System.out.println(e.getMessage());
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
