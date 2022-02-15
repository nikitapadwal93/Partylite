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

public class LoggedInAffiliate_CA_Regression_TC7 extends Class_initEcomPrac{
	public String country;
	
	public LoggedInAffiliate_CA_Regression_TC7() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataGOGC(@Optional String paramCountry) {
		tcName = "Loggedin affiliate order";
		tcDescription = "placing order in eShop as logged-in affiliate";
		category = "Smokes_"+paramCountry;
		authors = "Nikita";
		testNodes = "Logged-in affiliate in eshop";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "Regression_TC7";
		country = paramCountry;
		
	}
	
	@Test(groups= {"eShop", "Smokes"}, dataProvider="GuestOrder")
	public void guestOrderApplyGC(String ExistingUserEmail,String ExistingUserPassword, String SKU, String coupon,
			String valid_coupon, String num_readExcel, String pin_readExcel) throws InterruptedException{
		
		try {
			try {
			new LandingPage_PartyLite(driver, Test)
			
			.closeListrakNewsLetter()
			.clickToAllowCookie()
			.clickSignin()
			.enterEmailName(ExistingUserEmail)
			.enterPassword(ExistingUserPassword)
			.btnClickSignin()
			.clickBtnSearch()
			.enterSKU(SKU)
			.clickProductImage()
			.clickBtnAddToCart()
			.clickBtnMiniCart()
			.clickBtnViewBasket()
			.typeCouponCode(coupon)
			.clickCouponApplyButton()
			.verifyCouponInvalidMessage()
			.typePPCCoupon(valid_coupon)
			.clickCouponApplyButton()
			.verifyCouponApplyMessage()
			.typeCardNumberExcel(num_readExcel)
			.typeCardPINExcel(pin_readExcel)
			.clickApplyButton()
			.verifyGiftAddedMessage()
			.clickGoToCheckout()
			.AddressSelect()
			.clickDeliveryTypeBox()
			.chooseDeliveryType()
			.clickNextProceedToPayment()
			.clickAcceptTerms()
			.clickPlaceOrderButton()
			.confirmOrderIsSuccess1();	
			} 
			catch(RuntimeException e) {
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
	
	@DataProvider(name="GuestOrder")
	public  Object[][] getData(){
		try {
			return ExcelDataReader.getData(ExcelFileName, sheetName);		
		}catch(NullPointerException e) {
			reportStep("Excel sheet or file is not available.","fail", false);
			return null;
		}
}
	
}
