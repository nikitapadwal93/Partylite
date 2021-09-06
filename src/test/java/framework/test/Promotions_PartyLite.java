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

public class Promotions_PartyLite extends Class_initEcomPrac{
	
	public Promotions_PartyLite() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataPROMO(@Optional String paramCountry) {
		category = "Smokes_" +paramCountry;
		tcName = "Promotions";
		tcDescription = "test promotion for guest";
		
		authors = "Anand";
		testNodes = "promotion check in eshop";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "Promotions_PL";
		
	}
	
	@Test(groups= {"eShop", "Smokes"}, dataProvider="Promotions")
	public void checkPromotions(String X_SKU,String x_qty,String Y_SKU,String y_qty,String Disc_Amount){
		
		try {
			
			try {
			
			new LandingPage_PartyLite(driver, Test)
			
			//.closeDialogSignUpNewsLetter()
			.clickToAllowCookie()
			.clickBtnSearch()
			.enterSKU(X_SKU)
			.clickProductImage()
			.enterQty(x_qty)
			.clickBtnAddToCart()
			.clickBtnSearch()
			.enterSKU(Y_SKU)
			.clickProductImage()
			.enterQty(y_qty)
			.clickBtnAddToCart()
			.clickBtnMiniCart()
			.clickBtnViewBasket()
			.verifyDiscount(Disc_Amount);
			
			} catch(WebDriverException e) {
				e.getMessage();
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
	
	@DataProvider(name="Promotions")
	public  Object[][] getData(){
		try {
			return ExcelDataReader.getData(ExcelFileName, sheetName);		
		}catch(NullPointerException e) {
			reportStep("Excel sheet or file is not available.","fail", false);
			return null;
		}
}
	
}
