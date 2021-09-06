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

public class CollectivePartyOrder_EmptyHostOrder_PartyLite extends Class_initEcomPrac{
	public String country;
	
	public CollectivePartyOrder_EmptyHostOrder_PartyLite() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataCPO(@Optional String paramCountry) {
		tcName = "Collective Party Order";
		tcDescription = "Creating a final party order for a collective party with empty host order";
		category = "Smokes_"+paramCountry;
		authors = "Anand";
		testNodes = "Party order";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "CollectivePT_PL";
		country = paramCountry;
		
	}
	
	@Test(groups= {"PEX", "Smokes"}, dataProvider="CreatePartyDetails")
	public void createPartyOrder(String consultant_uname, String consultant_password, String partyName, 
			String partyDate, String expireDate,String partyTime,String partyExpireTime,String guestFN, String guestLN, String guestEmail, String guestPhone,String productName_SKU,String cardNumber,
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
			.clickCreateParty(country)
			.waitBodyContainer()
			.typePartyName(partyName)
			.clickSelectPartyType()
			.clickCollectiveParty(country)
			/*.selectPartyType(partyType)*/
			.clickForDatePicker()
			.clickDate(partyDate)
			.clickExpireDate()
			.clickExpireDate(expireDate)
			.enterPartyTime(partyTime)
			.enterPartyExpireTime(partyExpireTime)
			.selectHostShippingAddress()
			.clickCreateParty()
			.verifyPartyDashboardPageTitle()
			.verifyPartyName(partyName)
			.copyPartyUrl()
			.clickGuestsLink()
			.waitBodyContainer()
			.clickAddGuestLink()
			.typeGuestFN(guestFN)
			.typeGuestLN(guestLN)
			.typeGuestEmail(guestEmail)
			.typeGuestPhone(country,guestPhone)
			.clickAgreementToAddToParty()
			.clickAddGuestButton()
			.clickPartyGuest()
			.clickCreatePaperOrder()
			.enterProductDetail(productName_SKU)
			.clickSearchResultDisplayed()
			.addItemcount()
			.clickAddToBasket()
			//.validateProductAdditionToCart()

			.btnProceedToCheckout()
			.clickShipToParty()
			//.clickDeliveryTypeBox()
			//.chooseDeliveryType()
			.clickNextButton(country)
			.clickRadioCashOrCheck()
			.clickAcceptTerms()
			.clickPlacePaperOrderButton()
			.partyconfirmOrderIsSuccess()
			.closePopup()
			.clickHostBenefits()
			.clickHostOrderSummary()
			.selectProceedToPayment()
			.clickShipToParty()
			.clickNextButton(country)
			.clickPlacePaperOrderButton()
			.partyconfirmOrderIsSuccess()
			.closePopup()
			.clickCompleteOrder()
			.clickPayforParty()
			.clickShipToParty()
			.clickDeliveryTypeBox()
			.chooseDeliveryType()
			.clickNextButton(country)
			/*.enterCustomerFirstName(hostFirstName)
			.enterCustomerLastName(hostLastName)
			.enterCustomerAddress1(shipAddrLine1)
			.enterCustomerCity(shipAddrcity)
			.enterCustomerTelephone(hostPhone)
			.enterCustomerZipCode(shipAddrpostcode)*/
			.clickPayByCredit()
			.clickUpdateButton()
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
