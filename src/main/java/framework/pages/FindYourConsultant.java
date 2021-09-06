	package framework.pages;

	import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import framework.appInit.Class_initEcomPrac;

	public class FindYourConsultant extends Class_initEcomPrac { 
		
		
		
		public FindYourConsultant(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
			super();
			PageFactory.initElements(driver.get(), this);
			this.driver = driver;
			this.Test = Test;
				
	}
		@CacheLookup
		@FindBy(id="mode_name")
		WebElement radioButtonName;
		public FindYourConsultant clickNameOption() {
			radioButtonName.click();
			
			return this;
		}
		
		@FindBy(id="firstname")
		WebElement consultantFirstName;
		public FindYourConsultant typeFirstName(String firstName) {
			type(consultantFirstName, firstName);
			return this;
		}
		
		@FindBy(id="lastname")
		WebElement consultantLastName;
		public FindYourConsultant typeLastName(String lastName) {
			type(consultantFirstName, lastName);
			
			return this;
		}
		
		@FindBy(id="city")
		WebElement consultantCity;
		public FindYourConsultant typeCity(String city) {
			type(consultantFirstName, city);
			return this;
		}
		
		@CacheLookup
		@FindBy(xpath="//label[@for='mode_postcode']")
		WebElement radioButtonPostcode;
		public FindYourConsultant clickPostCodeOption() {
			click(radioButtonPostcode);
			try {
				WebElement elemPostCode = driver.get().findElementByXPath("//input[@name='postcode']"); //enabled multi-lingual support
				if(elemPostCode.isDisplayed()) {
					System.out.println("Post code displayed");
					reportStep("Post code displayed", "pass");
					return this;
				}
			
			} catch(org.openqa.selenium.NoSuchElementException | org.openqa.selenium.ElementNotVisibleException e) {
				reportStep("Screen did not populate the postcode textbox field correctly","warning");
			}
		return null;
		}
		
		@FindBy(xpath ="//div[@class='field postcode']//input") // multi-lingual support added
		WebElement txtBoxPostcode;
		public FindYourConsultant enterPostCodeValue(String Country, String ConsultantPostCode) {
			new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.
					presenceOfElementLocated(By.xpath("//div[@class='field postcode']")));
			try {
				System.out.println("The passed country value from Test is - " + Country);
				ConsultantPostCode = markets.get(Country).getProperty("postcode");	
			} catch(NullPointerException e) {
				System.out.println("Exception has occurred in data structure, its size is " + markets.size());
			}
			
			System.out.println("ConsultantPostCode value retrieved from prop file is = " + ConsultantPostCode);
			//new WebDriverWait(driver,waitTimeout).until(ExpectedConditions.visibilityOf(txtBoxPostcode));
			type(txtBoxPostcode, ConsultantPostCode.toString());
			return this;
		}
		
		@CacheLookup
		@FindBy(xpath="//*[@id=\"consultant-locator-form-postcode\"]/div[2]/div/button")
		WebElement buttonSearch;
		public FindYourConsultant clickSearchButton() {
			buttonSearch.click();
			try {
				//************TO DO ::: multi-lingual support feature pending.*****************
				
			if(new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions
					.textToBePresentInElementLocated
					(By.xpath("//div[text()[contains(.,'Consultant create failed')]]"),"Consultant create failed"))) 
			
			{
				//System.out.prinln("Intermittent error scenario identified");
				WebElement searchButton2 = locateElement("xpath", "//*[@id='consultant-locator-form-postcode']/div[2]/div/button");
				//System.out.prinln("About to click on the search button again");
				searchButton2 .click();
				//System.out.prinln("Clicked the search button again");
				return this;
			}
			} catch(TimeoutException e) {
				return this;
			}
			return this;
		}
		
		
		
		@FindBy(xpath="//div[@class='consultant-info']/p[1]")
		WebElement txtConsultantName;
		public FindYourConsultant retrieveConsultantName() {
			
			WebDriverWait wait = new WebDriverWait(driver.get(), waitTimeout);
			wait.until(ExpectedConditions.visibilityOf(txtConsultantName));
			
			String consultantName = getText(txtConsultantName);
			StringTokenizer st = new StringTokenizer(consultantName);
			if(st.hasMoreTokens()) {
			ConsultantName = st.nextToken();
			}
			//System.out.prinln("First name of consultant  = "  + ConsultantName);
			return this;
		}
		
		@CacheLookup
		@FindBy(xpath="//span[@class='consultant-name']")
		WebElement findConsultantName;
		public FindYourConsultant validateSelectedConsultant() {
			
			/*try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			ConsultantName = ConsultantName.toUpperCase();
			System.out.println("ConsultantName retrieved in search - " + ConsultantName);
			
			WebDriverWait wait = new WebDriverWait(driver.get(), waitTimeout);
			wait.until(ExpectedConditions.
					textToBe(By.xpath("//span[@class='consultant-name']"), ConsultantName));
			
			System.out.println("Actual displayed text for name is " +findConsultantName.getText());

			
//			WebDriverWait wait = new WebDriverWait(driver, waitTimeout);
//			wait.until(ExpectedConditions.
//					textToBe(By.xpath("//span[@class='consultant-name']"), ConsultantName));
//			
//			ExpectedCondition<Boolean> boolConText = new ExpectedCondition<Boolean>() {
//			      @Override
//			      public Boolean apply(WebDriver driver) {
//			        try {
//			          String elementText = findConsultantName.getText();
//			          return elementText.contains(ConsultantName);
//			        } catch (StaleElementReferenceException e) {
//			          return null;
//			        }
//			      }
//			    
//			    };
//		
//		new WebDriverWait(driver, waitTimeout).until(boolConText);
			   
			
			verifyPartialText(driver.get().findElementByXPath("//span[@class='consultant-name']"), ConsultantName);
			return this;
		
		}
		
		
		
		@FindAll({@FindBy(css=".consultant-box .actions-toolbar a")})
		List<WebElement> buttonSelect;
		public FindYourConsultant checkSearchedConsultant() {
			System.out.println(buttonSelect.size());
			if(buttonSelect.size()==2)
			{
				System.out.println("Pass 2 buttons after cons locator search");
				reportStep("Pass 2 buttons after cons locator search", "pass");
			}
			else
			{
				System.out.println("Fail more than 2 buttons in cons locator search");
				reportStep("Fail more than 2 buttons in cons locator search", "pass");	
			}
			return this;
		
	}

	}
