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
import framework.pages.LandingPage_PartyLite;
import framework.utils.ExcelDataReader;

public class Virtual_PartyOrder_PartyLite extends Class_initEcomPrac{
	public String country;
	
	public Virtual_PartyOrder_PartyLite() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataVPO(@Optional String paramCountry) {
		tcName = "Final Party Order - virtual party";
		tcDescription = "Creating a final party order for a virtual party";
		category = "Smokes_"+paramCountry;
		authors = "Anand";
		testNodes = "Final Party order";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "VirtualSubmit_PL";
		country = paramCountry;
		
	}
	
	@Test(groups= {"PEX", "Smokes"}, dataProvider="CreatePartyDetails")
	public void createPartyOrder(String consultant_uname, String consultant_password, 
			String partyName, String productName_SKU,String cardNumber,
			String expMonth,String expYear,String cardCVV){
		
		try {
			try {
			new LandingPage_PartyLite(driver, Test)
			.closeDialogSignUpNewsLetter()
			.clickToAllowCookie()
			.clickSignin()
			.enterEmailName(consultant_uname)
			.enterPassword(consultant_password)
			.ConClickSignin()
			//.closeSocialNewAcctDialog()
			.gotoPartyPage()
			.verifyPartyDashboardPageTitle()
			.verifyPartyName(partyName)
			.clickHostBenefits()
			.clickHostCredits()
			.enterHostCreditSKU(productName_SKU)
			.selectProduct()
			.clickHostOrderSummary()
			.selectProceedToPayment()
			.clickDeliveryTypeBox()
			.chooseHomeDeliveryMethod()
			.clickNextButton(country)
			.clickRadioCashOrCheck()
			.clickAcceptTerms()
			.clickPlacePaperOrderButton()
			.partyconfirmOrderIsSuccess()
			.closePopup()
			.clickCompleteOrder()
			.clickPayforParty()
			.clickShipToParty()
			.clickNextButton(country)
			.clickAcceptTerms()
			.clickPlacePartyOrderButton()
			.enterCardNumber(cardNumber)
			.enterCardExpMonth(expMonth)
			.enterCardExpYear(expYear)
			.enterCardCVV(cardCVV)
			.getOrderNumber()
			.clickConfirm()
			.partyconfirmOrderIsSuccess();
			
			
			} catch(WebDriverException e) {
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
	
	@DataProvider(name="CreatePartyDetails")
	public  Object[][] getData(){
		try {
			return ExcelDataReader.getData(ExcelFileName, sheetName);		
		}catch(NullPointerException e) {
			reportStep("Excel sheet or file is not available.","fail", false);
			return null;
		}
}
	

}
