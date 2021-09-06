package framework.pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import framework.appInit.Class_initEcomPrac;

public class PartyCreationPage extends Class_initEcomPrac  {

	PartyCreationPage(ThreadLocal<RemoteWebDriver> driver, ThreadLocal<ExtentTest> Test) throws FileNotFoundException, IOException {
		super();
		PageFactory.initElements(driver.get(), this);
		this.driver = driver;
		this.Test = Test;
			
}

	Boolean isPageReady;
	
	//Body Container Aria-Busy attribute Wait Check
			public PartyCreationPage waitBodyContainer() {
				try {
					WebElement bodyContainer = driver.get().findElementByXPath("//body[@data-container='body']");
					new WebDriverWait(driver.get(),reducedTimeout).until(ExpectedConditions.
							refreshed(ExpectedConditions.attributeToBe(bodyContainer, "aria-busy", "false")));
					reportStep("Body container reached false", "info");
				}
				catch(Exception e) {
					System.out.println("Body container wait failed");
				}
				return this;
			}
	
	@FindBy(id="party-name")
	WebElement txtBoxPartyName;
	public PartyCreationPage typePartyName(String NameOfParty) {
		try {
			 WebElement bodyContainer = driver.get().findElementByXPath("//body[@data-container='body']");
				Boolean isDOMReady = new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.
						refreshed(ExpectedConditions.attributeToBe(bodyContainer, "aria-busy", "false")));
				
				WebElement loader = driver.get().findElementByXPath("//div[@class='loading-mask']");
				System.out.println("Waiting for loader to finish page rendering.");
				isPageReady = new WebDriverWait(driver.get(), 30).until(ExpectedConditions.refreshed
						(ExpectedConditions.attributeContains(loader, "style", "none")));
				
				//System.out.prinln("Page rendering is complete.");
			} catch (NoSuchElementException  | TimeoutException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		
		try {
			driver.get().findElementById("btn-cookie-allow").click();
			System.out.println("Cookie is shown in create party page");
			reportStep("Cookie is shown in create party page", "info");
		}
		catch(Exception e) {
			System.out.println("Cookie button is not shown");
			//e.printStackTrace();
		}
		
		/*try {
			WebElement SignUpToNewsLetterText = driver.get().findElementByXPath("//div[@class='newsletter-subscribe-modal']");
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.visibilityOf(SignUpToNewsLetterText));
			WebElement closeSignUpDialog = driver.get().findElementByXPath("//button[@data-role='action']");
			driver.get().executeScript("arguments[0].click()", closeSignUpDialog);
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.invisibilityOf(SignUpToNewsLetterText));
			System.out.println("newsletter closed in party creation page");
			reportStep("newsletter closed in party creation page", "info");
		}
		catch(Exception e) {
			System.out.println("No newsletter in party creation page");
			reportStep("No newsletter in party creation page", "info");
		}
		try
		{
	WebElement modalCurtain = driver.get().findElementByXPath("//aside");
	new WebDriverWait(driver.get(),waitTimeout).until(ExpectedConditions.invisibilityOf(modalCurtain));
	reportStep("model curtain displayed", "info");
		}catch (Exception e3) {
			reportStep("no model curtain displayed", "info");
		}*/
		type(txtBoxPartyName, NameOfParty);
		return this;
	}
	
	
	@FindBy(xpath="//label[@class='select_expandLabel']")
	WebElement partyTypSelector;
	
	public PartyCreationPage clickSelectPartyType() {
		click(partyTypSelector);
		return this;
	}
	
	

	@FindBy(xpath="//li[@class='select_items']//ul")
	WebElement selPartyType;
	public PartyCreationPage selectPartyType(String typeOfParty) {
		/*List<WebElement> partyTypeOptions = driver.findElements(By.xpath("//li[@class='select_items']//ul/li/input"));
		//System.out.prinln(partyTypeOptions.size());
		for(WebElement type : partyTypeOptions) {
			//System.out.prinln(type.getAttribute("value"));
			//System.out.prinln("data read from excel " + typeOfParty);
			if(Integer.parseInt(type.getAttribute("value")) == (int)Double.parseDouble(typeOfParty)) {
				//System.out.prinln("PartyType Text matched");
				int value = Integer.parseInt(type.getAttribute("value"));
				
				WebElement typeLocated = locateElement("xpath", "(//li[@class='select_items']//ul/li)"+"["+value+"]");
				new Actions(driver).moveToElement(typeLocated, 4, 5).click().build().perform();
				return this;
			}
				
		}
		return this;*/
		try {
			WebElement dropDownPartyTypes = (new WebDriverWait(driver.get(),waitTimeout)).until(new ExpectedCondition<WebElement>(){
		

			@Override
			public WebElement apply(WebDriver d) {
				return d.findElement(By.xpath(("//li[@class='select_items']//ul)")));
			}
			
		});
			dropDownPartyTypes.findElement(By.xpath((".(/li))"+"["+typeOfParty+"]"))).click();
			return this;
		}catch(NoSuchElementException e) {
			reportStep("Party types list is not displayed or not selected properly","warning");
			return null;
		}
		
	}

	@FindBy(xpath="//span[contains(text(),'Classic')]")
	 WebElement ClassicParty;
	 public PartyCreationPage clickClassicParty(String country) {
		 ClassicParty=driver.get().findElementByXPath("//span[contains(text(),'"+markets.get(country).getProperty("Classic") + "')]");
		 //click(ClassicParty);
		 System.out.println("about to click party type");
		 driver.get().executeScript("arguments[0].click()", ClassicParty);
		 System.out.println("party type selection method done");
		 return this;
	 }
	 
	 @FindBy(xpath="//span[contains(text(),'Facebook / Virtual')]")
	 WebElement FacebookParty;
	 public PartyCreationPage clickFacebookParty(String country) {
		 FacebookParty=driver.get().findElementByXPath("//span[contains(text(),'"+markets.get(country).getProperty("Facebook") + "')]");
		 //click(FacebookParty);
		 System.out.println("about to click party type");
		 driver.get().executeScript("arguments[0].click()", FacebookParty);
		 System.out.println("party type selection method done");
		 return this;
	 }
	 
	 @FindBy(xpath="//span[contains(text(),'Collective')]")
	 WebElement CollectiveParty;
	 public PartyCreationPage clickCollectiveParty(String country) {
		 CollectiveParty=driver.get().findElementByXPath("//span[contains(text(),'"+markets.get(country).getProperty("Collective") + "')]");
		 //click(CollectiveParty);
		 System.out.println("about to click party type");
		 driver.get().executeScript("arguments[0].click()", CollectiveParty);
		 System.out.println("party type selection method done");
		 return this;
	 }
	 
	 @FindBy(xpath="//span[contains(text(),'Find Your Signature')]")
	 WebElement FYSParty;
	 public PartyCreationPage clickFYSParty(String country) {
		 FYSParty=driver.get().findElementByXPath("//span[contains(text(),'"+markets.get(country).getProperty("FYS") + "')]");
		 //click(FYSParty);
		 System.out.println("about to click party type");
		 driver.get().executeScript("arguments[0].click()", FYSParty);
		 System.out.println("party type selection method done");
		 return this;
	 }
	
	@FindBy(id="party-date")
	WebElement dateForParty;
	public PartyCreationPage clickForDatePicker() {
		click(dateForParty);
		return this;
	}
	
	@FindBy(id="party-expiredate")
	WebElement expiredateForParty;
	public PartyCreationPage clickExpireDate() {
		click(expiredateForParty);
		return this;
	}
	
	/*@FindBy(className="ui-datepicker-calendar")
	WebElement datepicker;
	public PartyCreationPage clickDate(String userEnteredDate) {
		boolean dateFound =false;
		String[] parsedDateValue = userEnteredDate.split("/");
		String date = parsedDateValue[0];
		System.out.println("date entered in excel - "+parsedDateValue[0]);
		String month = parsedDateValue[1];
		//System.out.prinln("month entered in excel - "+parsedDateValue[1]);
		String year = parsedDateValue[2];
		//System.out.prinln("year entered in excel - "+parsedDateValue[2]);
		
		List<WebElement> dateRows = datepicker.findElements(By.tagName("tr"));
		for(WebElement dateRow : dateRows) {
			List<WebElement> cellDateValues = dateRow.findElements(By.xpath("//td[@data-handler='selectDay']"));
			for(WebElement cellDate : cellDateValues) {
				if(date.equals(cellDate.getText().trim())) {
					if(cellDate.getAttribute("data-event").contains("click")) {
						click(cellDate);
						dateFound = true;
					}
				}
				
			}
			if(dateFound) {
				break;
		}
		}
		if(!dateFound) {
			throw new RuntimeException("Date entered by user in the excel is incorrect/or refers to past date");
		}
	
		return this;
		}*/
	
	@FindBy(css="a.ui-state-default.ui-state-highlight")
	WebElement datepicker;
	public PartyCreationPage clickDate(String userEnteredDate) {
		click(datepicker);
		return this;
		}
	
	@FindBy(xpath="//a[@data-handler='next']")
	WebElement selectNextMonth;
	public PartyCreationPage clickNextMonth() {
		click(selectNextMonth);
		return this;
		}
	
	@FindBy(xpath="//td[@data-handler='selectDay']")
	WebElement childbookDay;
	public PartyCreationPage selectChildBookDay() {
		click(childbookDay);
		return this;
		}
	
	@FindBy(className="ui-datepicker-calendar")
	WebElement enddatepicker;
	public PartyCreationPage clickExpireDate(String userEnteredDate) {
		boolean dateFound =false;
		String[] parsedDateValue = userEnteredDate.split("/");
		String date = parsedDateValue[0];
		System.out.println("date entered in excel - "+parsedDateValue[0]);
		String month = parsedDateValue[1];
		//System.out.prinln("month entered in excel - "+parsedDateValue[1]);
		String year = parsedDateValue[2];
		//System.out.prinln("year entered in excel - "+parsedDateValue[2]);
		
		List<WebElement> dateRows = enddatepicker.findElements(By.tagName("tr"));
		for(WebElement dateRow : dateRows) {
			List<WebElement> cellDateValues = dateRow.findElements(By.xpath("//td[@data-handler='selectDay']"));
			for(WebElement cellDate : cellDateValues) {
				if(date.equals(cellDate.getText().trim())) {
					if(cellDate.getAttribute("data-event").contains("click")) {
						click(cellDate);
						dateFound = true;
					}
				}
				
			}
			if(dateFound) {
				break;
		}
		}
		if(!dateFound) {
			throw new RuntimeException("Date entered by user in the excel is incorrect/or refers to past date");
		}
	
		return this;
		}
	
	@CacheLookup
	@FindBy(id="party-time")
	WebElement txtBoxpartyTime;
	public PartyCreationPage enterPartyTime(String partyTime) {
		
		if(p.getProperty("browserApp").equalsIgnoreCase("firefox")) {
			/*System.out.println("Inside actions class for firefox - Create party - time picker field");
			new Actions(driver).click(txtBoxpartyTime).sendKeys
			(Keys.chord(Keys.NUMPAD1, Keys.NUMPAD1,Keys.NUMPAD3, Keys.NUMPAD1)).build();*/
			driver.get().manage().timeouts().setScriptTimeout(12, TimeUnit.SECONDS);
			driver.get().executeScript("arguments[0].value='11:22';", txtBoxpartyTime);
			return this;
			
		}
		clear(txtBoxpartyTime);
		click(txtBoxpartyTime);
		SimpleDateFormat gmtDateFormat = new SimpleDateFormat("HH:mm");
		gmtDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+00:02"));
		partyTime=gmtDateFormat.format(new Date());
		System.out.println(gmtDateFormat.format(new Date()));
		type(txtBoxpartyTime, partyTime);
		return this;
	}
	
	@CacheLookup
	@FindBy(id="party-expiretime")
	WebElement txtBoxpartyExpireTime;
	public PartyCreationPage enterPartyExpireTime(String partyExpireTime) {
		
		if(p.getProperty("browserApp").equalsIgnoreCase("firefox")) {
			/*System.out.println("Inside actions class for firefox - Create party - time picker field");
			new Actions(driver).click(txtBoxpartyTime).sendKeys
			(Keys.chord(Keys.NUMPAD1, Keys.NUMPAD1,Keys.NUMPAD3, Keys.NUMPAD1)).build();*/
			driver.get().manage().timeouts().setScriptTimeout(12, TimeUnit.SECONDS);
			driver.get().executeScript("arguments[0].value='11:22';", txtBoxpartyExpireTime);
			return this;
			
		}
		clear(txtBoxpartyExpireTime);
		click(txtBoxpartyExpireTime);
		type(txtBoxpartyExpireTime, partyExpireTime);
		return this;
	}
	
	
	@FindBy(xpath="//label[contains(@data-bind, 'Previous Host')]")
	WebElement partyHost_PrevHost;
	public PartyCreationPage selectPartyHost_Prev() {
		click(partyHost_PrevHost);
		return this;
	}
	
	
	@FindBy(xpath="//label[contains(@data-bind, 'I will host')]")
	WebElement partyHost_Self;
	public PartyCreationPage selectPartyHost_self() {
		try {
			partyHost_Self.click();
		} catch (org.openqa.selenium.ElementNotInteractableException e) {
			System.out.println("I will host option click did not work at first attempt.");
			click(partyHost_Self);
		}
		return this;
	}
	
	@FindBy(xpath="//label[contains(@data-bind, 'New Host')]")
	WebElement partyHost_New;
	public PartyCreationPage selectPartyHost_New() {
		click(partyHost_New);
		return this;
	}
	
	@FindBy(id="customer-search")
	WebElement txtBoxHostEmail;
	public PartyCreationPage enterHostEmail(String partyHostEmail) {
		type(txtBoxHostEmail, partyHostEmail);
		return this;
	}
	
	@FindBy(css=".customer-list li")
	WebElement suggestedHostEmail;
	public PartyCreationPage selectHostEmail(String partyHostEmail) {
		try {
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.visibilityOf(suggestedHostEmail));
			click(suggestedHostEmail);
			return this;
		}
		catch(RuntimeException e) {
			reportStep("The suggestion of consultant contacts was not displayed initially, email to be re-entered", "warning");
			try {
			txtBoxHostEmail.clear();
			type(txtBoxHostEmail, partyHostEmail);
			}catch(ElementNotInteractableException e11) {
				reportStep("The hostEmail textbox could not be re-entered to initialize auto-suggestion", "warning");
			}
			try {
			new WebDriverWait(driver.get(), waitTimeout).until(ExpectedConditions.visibilityOf(suggestedHostEmail));
			click(suggestedHostEmail);
			return this;
			}
			catch(RuntimeException e2) {
				reportStep("The suggestion of consultant contacts was not displayed again", "warning");
			}	
		}
		return null;
	}
	
	@FindBy(xpath="(//label[contains(@data-bind,'Host Address')])[1]")
	WebElement partyShippingHostAddress;
	public PartyCreationPage selectHostShippingAddress() {
		click(partyShippingHostAddress);
		return this;
				}	
	
	@FindBy(xpath="(//label[contains(@data-bind,'Host Address')])[2]")
	WebElement partyLocationHostAddress;
	public PartyCreationPage selectHostLocationAddress() {
		click(partyLocationHostAddress);
		return this;
				}
	
	@FindBy(xpath="(//label[contains(@data-bind,'Previous Address')])[1]")
	WebElement partyRadioShippingPreviousAddress;
	public PartyCreationPage radioPreviousShippingAddress() {
		click(partyRadioShippingPreviousAddress);
		return this;
				}	
	
	@FindBy(xpath="(//label[contains(@data-bind,'Previous Address')])[2]")
	WebElement partyRadioLocationPreviousAddress;
	public PartyCreationPage radioPreviousLocationAddress() {
		click(partyRadioLocationPreviousAddress);
		return this;
				}
	
	@FindBy(css=".party_shipping_address .select")
	WebElement clickPartyShippingPreviousAddress;
	public PartyCreationPage clickPreviousShippingAddress() {
		click(clickPartyShippingPreviousAddress);
		return this;
				}
	
	@FindBy(css=".party_location .select")
	WebElement clickPartyLocationPreviousAddress;
	public PartyCreationPage clickPreviousLocationAddress() {
		click(clickPartyLocationPreviousAddress);
		return this;
				}
	
	@FindBy(css=".party_shipping_address .select_label span:nth-child(1)")
	WebElement partyShippingPreviousAddress;
	public PartyCreationPage selectPreviousShippingAddress() {
		driver.get().executeScript("arguments[0].click()", partyShippingPreviousAddress);
		//click(partyShippingPreviousAddress);
		return this;
				}
	
	@FindBy(css=".party_location .select_label span:nth-child(1)")
	WebElement partyLocationPreviousAddress;
	public PartyCreationPage selectPreviousLocationAddress() {
		click(partyLocationPreviousAddress);
		return this;
				}
	
	@FindBy(id="contact_firstname")
	WebElement host_contactFirstName;
	public PartyCreationPage typePartyHost_FN(String hostFirstName) {
		type(host_contactFirstName, hostFirstName);
		return this;
	}
	

	@FindBy(id="last_name")
	WebElement host_contactLastName;
	public PartyCreationPage typePartyHost_LN(String hostLastName) {
		
		type(host_contactLastName, hostLastName);
		return this;
	}
	
	
	@FindBy(id="contact_email")
	WebElement host_contactEmail;
	public PartyCreationPage typePartyHost_Email(String hostEmail) {
		long randomNum = (long)Math.floor(Math.random()*9000000000L)+8888888888888L;
		String hostrandomEmail = hostEmail+randomNum+"@mailinator.com";
		type(host_contactEmail, hostrandomEmail);
		return this;
	}
	
	@FindBy(id="contact_phone")
	WebElement host_contactPhone;
	public PartyCreationPage typePartyHost_Phone(String Country,String hostPhone) {
		type(host_contactPhone, markets.get(Country).getProperty("phoneNumber", hostPhone));
		return this;
	}
	
	
			
	@FindBy(xpath="(//label[contains(@data-bind,'New Address')])[1]")
	WebElement partyShippingNewAddress;
	public PartyCreationPage selectNewShippingAddress() {
	click(partyShippingNewAddress);
	return this;
			}		
	
	
	@FindBy(id="address_line_1")
	WebElement ShippingAddress_newAddressLine1;
	public PartyCreationPage typeNewShippingAddressLine1(String Country,String shipAddrLine1) {
		clear(ShippingAddress_newAddressLine1);
		type(ShippingAddress_newAddressLine1, markets.get(Country).getProperty("propAddress1", shipAddrLine1));
		return this;
	}
	
	@FindBy(id="address_line_2")
	WebElement ShippingAddress_newAddressLine2;
	public PartyCreationPage typeNewShippingAddressLine2(String Country,String shipAddrLine2) {
		clear(ShippingAddress_newAddressLine2);
		type(ShippingAddress_newAddressLine2, markets.get(Country).getProperty("propAddress1", shipAddrLine2));
		return this;
	}
	
	
	@FindBy(id="address_city")
	WebElement ShippingAddress_newAddresscity;
	public PartyCreationPage typeNewShippingAddresscity(String Country,String shipAddrcity) {
		clear(ShippingAddress_newAddresscity);
		type(ShippingAddress_newAddresscity, markets.get(Country).getProperty("propCity", shipAddrcity));
		return this;
	}
	
	
	@FindBy(id="address_country")
	WebElement ShippingAddress_newAddresscountry;
	public PartyCreationPage typeNewShippingAddresscountry(String shipAddrcountry) {
		type(ShippingAddress_newAddresscountry, shipAddrcountry);
		return this;
	}
	
	@FindBy(id="address_postcode")
	WebElement ShippingAddress_newAddresspostcode;
	public PartyCreationPage typeNewShippingAddresspostcode(String Country,String shipAddrpostcode) {
		clear(ShippingAddress_newAddresspostcode);
		type(ShippingAddress_newAddresspostcode, markets.get(Country).getProperty("postcode", shipAddrpostcode));
		String getPostCode=ShippingAddress_newAddresspostcode.getText();
		System.out.println("New Address Post Code : "+getPostCode);
		return this;
	}
	
	
	
	
	@FindBy(id="party_address_line_1")
	WebElement newPartyAddressLine1;
	public PartyCreationPage typeNewPartyAddressLine1(String partyAddrLine1) {
		type(newPartyAddressLine1, partyAddrLine1);
		return this;
	}
	
	@FindBy(id="party_address_line_2")
	WebElement newPartyAddressLine2;
	public PartyCreationPage typeNewPartyAddressLine2(String partyAddrLine2) {
		type(newPartyAddressLine2, partyAddrLine2);
		return this;
	}
	

	@FindBy(id="party_address_city")
	WebElement newPartyAddresscity;
	public PartyCreationPage typeNewPartyAddresscity(String partyAddrcity) {
		type(newPartyAddresscity, partyAddrcity);
		return this;
	}
	
	
	@FindBy(id="party_address_country")
	WebElement newPartyAddresscountry;
	public PartyCreationPage typeNewPartyAddresscountry(String partyAddrcountry) {
		type(newPartyAddresscountry, partyAddrcountry);
		return this;
	}
	
	@FindBy(id="party_address_postcode")
	WebElement newPartyAddresspostcode;
	public PartyCreationPage typeNewPartyAddresspostcode(String partyAddrpostcode) {
		type(newPartyAddresspostcode, partyAddrpostcode);
		return this;
	}
	
	@FindBy(id="address_phone")
	WebElement party_shipping_Phone;
	public PartyCreationPage typePartyShipping_Phone(String Country,String hostPhone) {
		clear(party_shipping_Phone);
		type(party_shipping_Phone, markets.get(Country).getProperty("phoneNumber", hostPhone));

		return this;
	}
	
	@FindBy(id="party_address_phone")
	WebElement party_location_Phone;
	public PartyCreationPage typePartyLocation_Phone(String hostPhone) {
		type(party_location_Phone, hostPhone);
		return this;
	}
	
	
	
	@FindBy(xpath="(//label[contains(@data-bind,'New Address')])[2]")
	WebElement partyLocationNewAddress;
	public PartyCreationPage selectNewLocationAddress() {
	click(partyLocationNewAddress);
	return this;
	}
	
	
	@FindBy(xpath="//label[@for='address_format_0']")
	WebElement partyAddressCleansingFormat;
	public partyDetailsPage selectExperianAddress() {
	try {
		click(partyAddressCleansingFormat);
		new WebDriverWait(driver.get(),waitTimeout).until(
				ExpectedConditions.attributeContains(By.xpath("//div[@class='loading-mask']"), "style", "none"));
	}
	catch(Exception e) {
		System.out.println("Address cleansing not happnened");
	}
	click(btnCreatePartyAddCleansing);	
	try {
		return new partyDetailsPage(driver, Test);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	}

	@FindBy(xpath="//button[@class='action save primary']")
	WebElement btnCreatePartyAddCleansing;
	public  PartyCreationPage  clickCreatePartyAddressCleansing() {
		click(btnCreatePartyAddCleansing);
		try {
			 new WebDriverWait(driver.get(),waitTimeout).until(
						ExpectedConditions.attributeContains(By.xpath("//div[@class='loading-mask']"), "style", "none"));
		 }
		 catch(Exception e)
		 {
			 
		 }
	return this;
	}
	
	
	@FindBy(xpath="//button[@class='action save primary']")
	WebElement btnCreateParty;
	public  partyDetailsPage  clickCreateParty() {
		click(btnCreateParty);
		try {
			return new partyDetailsPage(driver, Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	return null;
	}
	
	@FindBy(xpath="//button[@class='action save primary']")
	WebElement btnCreateChildParty;
	public  partyHostPage  clickCreateChildParty() {
		click(btnCreateChildParty);
		try {
			return new partyHostPage(driver, Test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	return null;
	}
	
	@FindBy(xpath="//label[@for='address_format_0']")
	WebElement childpartyAddressCleansingFormat;
	public partyHostPage selectChildExperianAddress() {
	try {
		click(childpartyAddressCleansingFormat);
		new WebDriverWait(driver.get(),waitTimeout).until(
				ExpectedConditions.attributeContains(By.xpath("//div[@class='loading-mask']"), "style", "none"));
	}
	catch(Exception e) {
		System.out.println("Address cleansing not happnened");
	}
	click(btnCreateChildPartyAddCleansing);	
	try {
		return new partyHostPage(driver, Test);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	}

	@FindBy(xpath="//button[@class='action save primary']")
	WebElement btnCreateChildPartyAddCleansing;
	public  PartyCreationPage  clickCreateChildPartyAddressCleansing() {
		click(btnCreateChildPartyAddCleansing);
		try {
			 new WebDriverWait(driver.get(),waitTimeout).until(
						ExpectedConditions.attributeContains(By.xpath("//div[@class='loading-mask']"), "style", "none"));
		 }
		 catch(Exception e)
		 {
			 
		 }
	return this;
	}
}
	
