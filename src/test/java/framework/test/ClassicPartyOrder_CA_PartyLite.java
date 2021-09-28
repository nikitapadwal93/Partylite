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

public class ClassicPartyOrder_CA_PartyLite extends Class_initEcomPrac{
	public String country;
	
	public ClassicPartyOrder_CA_PartyLite() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataCPO(@Optional String paramCountry) {
		tcName = "Classic Party Order CA";
		tcDescription = "Creating a final party order for a classic party canada";
		category = "Smokes_"+paramCountry;
		authors = "Anand";
		testNodes = "Party order";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "ClassicPTCA_PL";
		country = paramCountry;
		
	}
	

	@Test(groups= {"PEX", "Smokes"}, dataProvider="CreatePartyDetails")
	public void createPartyOrder(String consultant_uname, String consultant_password, String partyName, 
			 String partyDate, String partyTime, String hostFirstName, String hostLastName, String hostEmail,
			String hostPhone, String shipAddrLine1, String shipAddrLine2,String shipAddrcity , String shipAddrcountry, 
			String shipAddrpostcode, String partyAddrLine1 , String partyAddrLine2, String partyAddrcity, 
			String partyAddrcountry, String partyAddrpostcode,String guestFN, String guestLN, String guestEmail, String guestPhone,String productName_SKU,String cardNumber,
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
			/*.clickSelectPartyType()
			.selectPartyType(partyType)*/
			.clickForDatePicker()
			.clickDate(partyDate)
			.enterPartyTime(partyTime)
			.selectPartyHost_New()
			.typePartyHost_FN(hostFirstName)
			.typePartyHost_LN(hostLastName)
			.typePartyHost_Email(hostEmail)

			.typePartyHost_Phone(country,hostPhone)
			.selectNewShippingAddress()
			.typeNewShippingAddressLine1(country,shipAddrLine1)
			.typeNewShippingAddressLine2(country,shipAddrLine2)
			.typeNewShippingAddresscity(country,shipAddrcity)
			//.typeNewShippingAddresscountry(shipAddrcountry)
			.typeNewShippingAddresspostcode(country,shipAddrpostcode)
			.typePartyShipping_Phone(country,hostPhone)
			/*.selectNewLocationAddress()
			.typeNewPartyAddressLine1(partyAddrLine1)
			.typeNewPartyAddressLine2(partyAddrLine2)
			.typeNewPartyAddresscity(partyAddrcity)
			.typeNewPartyAddresscountry(partyAddrcountry)
			.typeNewPartyAddresspostcode(partyAddrpostcode)
			.typePartyLocation_Phone(hostPhone)*/
			.clickCreatePartyAddressCleansing()
			.selectExperianAddress()
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
			.clickRadioDebit()
			.enterCustomerAddress1(shipAddrLine1)
			.enterCustomerCity(shipAddrcity)
			.enterCustomerZipCode(shipAddrpostcode)
			.clickUpdateButton()
			.clickAcceptTerms()
			.clickPlacePartyOrderButton()
			.enterCardNumber(cardNumber)
			.enterCardExpMonth(expMonth)
			.enterCardExpYear(expYear)
			.enterCardCVV(cardCVV)
			.getOrderNumber()
			.clickPartyConfirm()
			.partyconfirmOrderIsSuccess()
			.closePopup()
			.clickHostBenefits()
			.clickHostCredits()
			.enterHostCreditSKU(productName_SKU)
			.selectProduct()
			.clickHostOrderSummary()
			.selectProceedToPayment()
			.clickShipToParty()
			.clickNextButton(country)
			.clickRadioDebit()
			.enterCustomerAddress1(shipAddrLine1)
			.enterCustomerCity(shipAddrcity)
			.enterCustomerZipCode(shipAddrpostcode)
			.clickUpdateButton()
			.clickAcceptTerms()
			.clickPlacePartyOrderButton()
			.enterCardNumber(cardNumber)
			.enterCardExpMonth(expMonth)
			.enterCardExpYear(expYear)
			.enterCardCVV(cardCVV)
			.getOrderNumber()
			.clickPartyConfirm()
			.partyconfirmOrderIsSuccess()
			.closePopup()
			.clickCompleteOrder()
			.clickPayforParty();
			/*.clickShipToParty()
			.clickDeliveryTypeBox()
			.chooseDeliveryType()
			.clickNextButton(country)
			.enterCustomerFirstName(hostFirstName)
			.enterCustomerLastName(hostLastName)
			.enterCustomerAddress1(shipAddrLine1)
			.enterCustomerCity(shipAddrcity)
			.enterCustomerTelephone(hostPhone)
			.enterCustomerZipCode(shipAddrpostcode)
			.clickPayByCredit()
			.clickUpdateButton()
			//.clickTransferParty()
			.clickAcceptTerms()
			.clickPlacePartyOrderButton()
			.enterCardNumber(cardNumber)
			.enterCardExpMonth(expMonth)
			.enterCardExpYear(expYear)
			.enterCardCVV(cardCVV)
			.getOrderNumber()
			.clickConfirm()
			.partyconfirmOrderIsSuccess();*/
			
			
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
