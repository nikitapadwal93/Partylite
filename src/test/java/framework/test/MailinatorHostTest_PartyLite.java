package framework.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriverException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import framework.appInit.Class_initMailinator;
import framework.pages.LandingPage_Mailinator;
import framework.utils.ExcelDataReader;

public class MailinatorHostTest_PartyLite extends Class_initMailinator{
	public String country;
	
	public MailinatorHostTest_PartyLite() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("paramCountry")
	@BeforeTest(groups= {"Common"})
	public void setDataMHT(@Optional String paramCountry) {
		category = "Smokes_" +paramCountry;
		tcName = "MailinatorTest";
		tcDescription = "Open host mail box and click the invitation link";
		
		authors = "Anand";
		testNodes = "mailinator link";
		ExcelFileName="PartyLite_Smokes_Data";
		sheetName = "Nanosite_Guest_Check_PL";
		country = paramCountry;
	}
	
	@Test(groups= {"PEX"},dataProvider="LoginDetails")
	public void mailinatorTest(String host_contact) {
			
		try {
			try {
			new LandingPage_Mailinator(driver, Test)
			.openContactEmail(host_contact)
			.clickGoButton()
			.clickPartyLiteMail(country);
			//.clickAttendParty()
			
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
